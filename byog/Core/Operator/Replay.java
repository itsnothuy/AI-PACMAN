package byog.Core.Operator;

import byog.Core.PacMan.PacMan;
import byog.Core.PacMan.PacManAction;
import byog.Core.Truc.OriTruc;
import byog.Core.Truc.TrucController;
import byog.Core.Truc.TrucsAction;
import byog.Core.Operator.PacManSimulator.GameConfig;

import java.io.*;
import java.util.ArrayList;

@SuppressWarnings({"rawtypes","unchecked"})
public class Replay {
    public GameConfig gameConfig = new GameConfig();
    private int trucCount;
    private ReplayMsPacman pacMan;
    private ReplayTrucs trucs;

    private ArrayList<Integer> pacManActions;
    private ArrayList<Integer> pacManLocations;
    private ArrayList<int[]> trucActions;
    private ArrayList<int[]> trucLocations;

    public Replay(File file) {
        loadActions(file);
        this.pacMan = new ReplayMsPacman();
        this.trucs = new ReplayTrucs(trucCount);
    }

    public void loadActions(File file) {
        ArrayList[] data = loadData(file);
        pacManActions = data[0];
        trucActions = data[1];
        pacManLocations = data[2];
        trucLocations = data[3];
    }

    public static void saveActions(GameConfig gameConfig, int trucCount, String actions, File replayFile, boolean firstWrite) {
        try {
            FileOutputStream outS = new FileOutputStream(replayFile, !firstWrite);
            PrintWriter pw = new PrintWriter(outS);

            if (firstWrite) {
                System.out.println("Saving replay into: " + replayFile.getAbsolutePath());
                pw.println(gameConfig.asString());
                pw.println(trucCount);
            }

            pw.println(actions);

            pw.flush();
            outS.close();
        } catch (Exception e) {
            System.out.println("Count not save date!");
        }
    }

    public ReplayMsPacman getPacMan() { return pacMan; }
    public ReplayTrucs getTrucs() { return trucs; }
    public ArrayList[] loadData(File file) {
        ArrayList[] data = new ArrayList[4];
        data[0] = new ArrayList<Integer>();
        data[1] = new ArrayList<int[]>();
        data[2] = new ArrayList<Integer>();
        data[3] = new ArrayList<int[]>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            gameConfig.fromString(br.readLine());

            String trucCount = br.readLine();
            this.trucCount = Integer.parseInt(trucCount);

            String input = br.readLine();

            while (input != null && !input.equals("")) {
                input = input.trim();
                String[] numbers = input.split("\t");

                if (!numbers[0].equals("#")) { // ignore comments
                    data[0].add(Integer.parseInt(numbers[1]));  // action for Ms Pac-Man

                    int[] trucActions = new int[4]; // actions for ghosts

                    for (int i = 0; i < trucActions.length; i++)
                        trucActions[i] = Integer.parseInt(numbers[i + 2]);

                    data[1].add(trucActions);

                    data[2].add(Integer.parseInt(numbers[6]));

                    int[] trucLocations = new int[4];

                    for (int i = 0; i < trucLocations.length; i++)
                        trucLocations[i] = Integer.parseInt(numbers[i + 7]);

                    data[3].add(trucLocations);
                }

                input = br.readLine();

            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return data;
    }

    // Simple controller that simply plays the next recorded action
    public class ReplayMsPacman implements PacMan {
        private PacManAction action = new PacManAction();

        @Override
        public void reset(G game) { action.reset(); }

        @Override
        public void nextLevel(G game) {
        }

        @Override
        public void tick(G game, long timeDue) {
            int actionIndex = pacManActions.get(game.getTotalTime());
            Direction actionDir = Direction.forIndex(actionIndex);

            int locationIndex = pacManLocations.get(game.getTotalTime());
            Direction locationDir = Direction.NONE;
            for (int i = 0; i < 4; ++i) {
                if (locationIndex == game.getNeighbour(game.getCurPacManLoc(), i))
                    locationDir = Direction.forIndex(i);
            }

            if (actionDir != locationDir)
                actionDir = locationDir;

            action.set(actionDir);
        }

        @Override
        public void killed(){
        }

        public int getLocation(G game) {
            return pacManLocations.get(game.getTotalTime());
        }

        @Override
        public PacManAction getAction() { return action; }
    }

    // Simple controller that simply plays the next recorded action
    public class ReplayTrucs implements TrucController {
        private TrucsAction actions;

        public ReplayTrucs(int whichTruc) {
            actions = new TrucsAction(whichTruc);
        }

        @Override
        public int getTrucCount() { return trucCount; }

        @Override
        public void reset(G game) { actions.reset(); }

        @Override
        public void nextLevel(G game) {
        }

        @Override
        public void tick(G game, long timeDue) {
            int[] actions = trucActions.get(game.getTotalTime());
            for (int truc = 0; truc < actions.length; ++truc) {
                Direction actionDir = Direction.forIndex(actions[truc]);

                int locationIndex = trucLocations.get(game.getTotalTime())[truc];
                Direction locationDir = Direction.NONE;
                for (int j = 0; j < 4; ++j) {
                    if (locationIndex == game.getNeighbour(game.getCurTrucLoc(truc), j))
                        locationDir = Direction.forIndex(j);
                }

                if (actionDir != locationDir && game.getLairTime(truc) == 0) {
                    actionDir = locationDir;
                }

                this.actions.Truc(truc).set(actionDir);
            }
        }

        public int[] getLocations(G game) { return trucLocations.get(game.getTotalTime()); }

        @Override
        public TrucsAction getActions() { return actions; }
    }
}
