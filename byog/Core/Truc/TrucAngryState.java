package byog.Core.Truc;

import byog.Core.Operator.G;

public class TrucAngryState implements OriTruc {
    int X = 0;
    int Y = 1;
    int pinkDist = 16;
    int tileDist = 4;
    int ClydeCornerNode = 1195;
    int ClydeMaintainDistance = 8;
    int InkyPacDistance = 2;

    @Override
    public int[] execute(int trucType, G game, long timeDue) {
        int[] target = {0, 0};
        if (trucType == BLINKY) {
            target[X] = game.getX(game.getCurPacManLoc());
            target[Y] = game .getY(game.getCurPacManLoc());
        }
        if (trucType == PINKY) {
            target[X] = game.getX(game.getCurPacManLoc());
            target[Y] = game.getY(game.getCurPacManLoc());
            int pacLastDirection = game.getCurPacManDir();
            if (pacLastDirection == G.TOP) {
                target[X] -= pinkDist;
                target[Y] -= pinkDist;
            } else if (pacLastDirection == G.LEFT) {
                target[X] -= pinkDist;
            } else if (pacLastDirection == G.RIGHT) {
                target[X] += pinkDist;
            } else if (pacLastDirection == G.DOWN) {
                target[Y] += pinkDist;
            }
        }

        if(trucType == CLYDE) {
            double distanceFromPac = Truc.getEuclideanDistance(game.getX(game.getCurTrucLoc(trucType)), game.getY(game.getCurTrucLoc(trucType)),
                    game.getX(game.getCurPacManLoc()), game.getY(game.getCurPacManLoc()));
            if (distanceFromPac >= ClydeMaintainDistance * tileDist) {
                target[X] = game.getX(game.getCurPacManLoc());
                target[Y] = game.getY(game.getCurPacManLoc());
            } else {
                target[X] = game.getX(ClydeCornerNode);
                target[Y] = game.getY(ClydeCornerNode);
            }

        }

        if (trucType == INKY) {
            int pacFrontX = game.getX(game.getCurPacManLoc());
            int pacFrontY = game.getY(game.getCurPacManLoc());
            int pacLastDirection = game.getCurPacManDir();
            if (pacLastDirection == G.TOP) {
                pacFrontX -= tileDist * InkyPacDistance;
                pacFrontY -= tileDist * InkyPacDistance;
            } else if (pacLastDirection == G.LEFT) {
                pacFrontX -= tileDist * InkyPacDistance;
            } else if (pacLastDirection == G.RIGHT) {
                pacFrontX += tileDist * InkyPacDistance;
            } else if (pacLastDirection == G.DOWN) {
                pacFrontY += tileDist * InkyPacDistance;
            }

            int blinkyLocX = game.getX(game.getCurTrucLoc(BLINKY));
            int blinkyLocY = game.getY(game.getCurTrucLoc(BLINKY));
            int vectorX = pacFrontX - blinkyLocX;
            int vectorY = pacFrontY - blinkyLocY;
            target[X] = blinkyLocX + vectorX * 2;
            target[Y] = blinkyLocY + vectorY * 2;
        }

        return target;
    }

}
