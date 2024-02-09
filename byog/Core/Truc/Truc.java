package byog.Core.Truc;


import byog.Core.Idle.Position;
import byog.Core.Operator.G;
import byog.Core.Operator.Game;
import byog.Core.Operator.GameView;
import byog.TileEngine.TETile;

import java.awt.*;
import java.io.Serializable;

public class Truc implements Serializable, TrucController {
    public Position position;
    public TETile trucTile;
    public String description;

    public final int NUM_NORMAL_PER_LEVEL = 4;
    public final int NORMAL = 0;
    public final int ANGRY = 1;
    public final int WEAK = 2;

    public int trucCount = 4;

    public TrucsAction actions;

    public OriTruc TrucCurrentAI;

    public OriTruc TrucPreviousAI;

    public long stateChangeCurrentTime;

    public long stateChangeShiftTime;

    public int numberOfAngryOccured;
    public int numberOfNormalOccured;

    public TrucAngryState AngryHandler;
    public TrucNormalState NormalHandler;
    public TrucWeakState WeakHandler;

    public int currentGlobalState;

    public boolean debugging = false;

    int X;
    int Y;

    /**
     * [RED, PINK, ORANGE, BLUE]
     */
    int[] TrucTarget;

    public int[] TrucState;

    public Truc() {this(4, true);}

    public Truc(int trucCount) {this((trucCount < 0 ? 0 : (trucCount > 4 ? 4 : trucCount)), false); }


    public Truc(boolean debugging) { this(4, debugging);}

    public Truc(int trucCount, boolean debugging) {
        this.trucCount = trucCount;
        this.debugging = debugging;
        actions = new TrucsAction(trucCount);
    }

    @Override
    public int getTrucCount() {
        return trucCount;
    }

    @Override
    public void reset(G game) {
        actions.reset();

        TrucCurrentAI = new TrucNormalState();
        TrucPreviousAI = new TrucNormalState();
        stateChangeCurrentTime = game.getTotalTime();
        stateChangeShiftTime = stateChangeCurrentTime + (7 * 25);
        numberOfNormalOccured = 0;
        numberOfAngryOccured = 0;

        NormalHandler = new TrucNormalState();
        AngryHandler = new TrucAngryState();
        WeakHandler = new TrucWeakState();

        currentGlobalState = WEAK;

        X = 0;
        Y = 1;

        TrucTarget = new int[] {0, 0};
        TrucState = new int[]{NORMAL, NORMAL, NORMAL, NORMAL};
    }

    @Override
    public void nextLevel(G game) {
    }

    @Override
    public void tick(G game, long timeDue) {
        if(game.getLevelTime() <= 10) {
            numberOfNormalOccured = 0;
            numberOfAngryOccured = 0;
            stateChangeShiftTime = game.getTotalTime();
        }

        int[] directions = new int[]{3, 3, 3, 3};

        long stateChangeTimer = stateChangeShiftTime - game.getTotalTime();
        if (stateChangeTimer < 0 && numberOfNormalOccured < NUM_NORMAL_PER_LEVEL) {
            int nextStateTimeInSec = 0;
            if (currentGlobalState == NORMAL) {
                currentGlobalState = ANGRY;
                numberOfNormalOccured++;
                if (game.getCurLevel() == 1) {
                    nextStateTimeInSec = 20;
                }
                else if (game.getCurLevel() < 5) {
                    if (numberOfAngryOccured < 2) {
                        nextStateTimeInSec = 20;
                    }
                    else {
                        nextStateTimeInSec = 1033;
                    }
                }else {
                    if (numberOfAngryOccured < 2) {
                        nextStateTimeInSec = 20;
                    }
                    else{
                        nextStateTimeInSec = 1037;
                    }
                }
            }
            else {
                currentGlobalState = NORMAL;
                if (game.getCurLevel() == 1) {
                    if (numberOfNormalOccured < 2) {
                        nextStateTimeInSec = 7;
                    }
                    else {
                        nextStateTimeInSec = 5;
                    }
                } else if (game.getCurLevel() < 5) {
                    if (numberOfNormalOccured < 2) {
                        nextStateTimeInSec = 7;
                    } else if (numberOfNormalOccured == 2) {
                        nextStateTimeInSec = 5;
                    } else {
                        nextStateTimeInSec = 1;

                    }
                } else {
                    if (numberOfNormalOccured < 2) {
                        nextStateTimeInSec = 5;
                    } else {
                        nextStateTimeInSec = 1;
                    }
                }
                numberOfAngryOccured++;
            }
            stateChangeShiftTime = game.getTotalTime() + (nextStateTimeInSec * 25);

            /*
            for (int i = 0; i < directions.length; i ++) {
                directions[i] = game.getReverse(game.getCurTrucDir(i));
                System.out.println("Reverse Truc" + i + " : " + directions[i]);
            }
            */
            // REWRITE DIRECTIONS
            for (int i = 0; i < directions.length; i++) {
                actions.Truc(i).set(directions[i]);
            }
            return;
        }

        for (int i = 0; i < G.NUM_TRUCS; i++) {
            if (game.getEdibleTime(i) > 0) {
//                if (TrucState[i] != WEAK) {
//                    directions[i] = game.getReverse(game.getCurTrucDir(i));
//                    TrucState[i] = WEAK;
//                    continue;
//                }
                TrucState[i] = WEAK;
                // System.out.println("In weak for " + i + "at edible time" + game.getEdibleTime(i));
            } else if (TrucState[i] != currentGlobalState) {
                TrucState[i] = currentGlobalState;
            }

            if (TrucState[i] == NORMAL) {
                TrucTarget = NormalHandler.execute(i, game, timeDue);
            }
            if (TrucState[i] == ANGRY) {
                TrucTarget = AngryHandler.execute(i, game, timeDue);
            }

            if (TrucState[i] != WEAK) {
                Color color;
                if (i == 0) {
                    color = Color.RED;
                } else if (i == 1) {
                    color = Color.PINK;
                } else if (i == 2) {
                    color = Color.ORANGE;
                } else {
                    color = Color.CYAN;
                }
                if (debugging) {
                    GameView.addLines(game, color, game.getX(game.getCurTrucLoc(i)), game.getY(game.getCurTrucLoc(i)), TrucTarget[X], TrucTarget[Y]);
                }
            }

            int chosenDirection = -1;
            if (game.trucRequiresAction(i) && TrucState[i] != WEAK) {
                int[] possibleDirections = game.getPossibleTrucDirs(i);
                //double[] distanceFromPossibleMoves = new double[possibleDirections.length];
                double chosenDirectionDistance = 1000000;
                boolean equalPathsCheck = false;
                for (int j = 0; j < possibleDirections.length; j++) {
                    int directionNodeNum = game.getNeighbour(game.getCurTrucLoc(i), possibleDirections[j]);
                    double distanceBetweenNeighbor = Truc.getEuclideanDistance(game.getX(directionNodeNum), game.getY(directionNodeNum), TrucTarget[X], TrucTarget[Y]);

                    if (Double.compare(distanceBetweenNeighbor, chosenDirectionDistance) < 0) {
                        equalPathsCheck = false;
                        chosenDirectionDistance = distanceBetweenNeighbor;
                        chosenDirection = possibleDirections[j];
                    } else if (Double.compare(distanceBetweenNeighbor, chosenDirectionDistance) == 0) {
                        equalPathsCheck = true;
                    }
                }
                if (equalPathsCheck) {
                    boolean leftPriority = false;
                    for (int j = 0; j < possibleDirections.length; j++) {
                        if (possibleDirections[j] == G.TOP) {
                            chosenDirection = G.TOP;
                            break;
                        } else if (possibleDirections[j] == G.LEFT) {
                            chosenDirection = G.LEFT;
                        } else if (possibleDirections[j] == G.DOWN && !leftPriority) {
                            chosenDirection = G.DOWN;
                        }
                    }
                }
            } else if (game.trucRequiresAction(i) && TrucState[i] == WEAK) {
                System.out.println("Getting Truc " + i + "weak dir");
                int[] TrucPossibleDirs = game.getPossibleTrucDirs(i);
                System.out.println(TrucPossibleDirs.length);
                if (TrucPossibleDirs.length > 0) {
                    int numOfPossibleDirs = Math.abs(Game.rnd.nextInt()) % TrucPossibleDirs.length;
                    System.out.println(numOfPossibleDirs);
                    if (numOfPossibleDirs < 0) {
                        chosenDirection = TrucPossibleDirs[numOfPossibleDirs];
                    }
                }
            }
            directions[i] = chosenDirection;
        }

        // REWRITE DIRECTION
        for (int i = 0; i < directions.length; ++i) {
            actions.Truc(i).set(directions[i]);
        }

        return;

    }

    @Override
    public TrucsAction getActions() {
        return actions;
    }

    public static double getEuclideanDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }







}
