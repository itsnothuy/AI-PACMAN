package byog.Core.Operator;

import byog.Core.PacMan.PacManAction;
import byog.Core.Truc.TrucsAction;

public interface G {
    public static final int TOP = 0;
    public static final int RIGHT = 1;

    public static final int DOWN = 2;
    public static final int LEFT = 3;

    public static final int EMPTY = -1;
    public static final int PILL = 10;
    public static final int POWER_PILL = 50;
    public static final int TRUC_EAT_SCORE = 200;
    public static final int EDIBLE_TIME = 200;
    public static final float EDIBLE_TIME_REDUCTION = 0.9f;
    public static final int[] LAIR_TIMES = {40, 60, 80, 100};
    public static final int COMMON_LAIR_TIMES = 40;
    public static final float LAIR_REDUCTION = 0.9f;
    public static final int LEVEL_LIMIT = 3000;
    public static final float TRUC_REVERSAL = 0.0015f;
    public static final int MAX_LEVELS = 16;
    public static final int EXTRA_LIFE_SCORE = 10000;
    public static final int EAT_DISTANCE = 2;
    public static final int NUM_TRUCS = 4;

    public static final int NUM_MAZES = 4;
    public static final int NUM_LIVES = 3;
    public static final int INITIAL_PAC_DIR = 3;
    public static final int[] INITIAL_TRUC_DIRS = {3, 3, 3, 3};
    public static final int TRUC_SPEED_REDUCTION = 2;

    public G copy();
    public int[] advanceGame(PacManAction pacMan, TrucsAction trucs);
    public int getReverse(int direction);

    public boolean gameOver();

    public boolean checkPill(int pillIndex);

    public boolean checkPowerPill(int powerPillIndex);

    public int[] getPacManNeighbours();
    public int[] getTrucNeighbours(int whichTruc);
    public int getCurLevel();

    public int getCurMaze();
    public int getCurPacManLoc();
    public int getCurPacManDir();
    public int getCurTrucLoc(int whichTruc);
    public int getCurTrucDir(int whichTruc);
    public int getEdibleTime(int whichTruc);
    public boolean isEdible(int whichTruc);
    public int getScore();
    public int getLevelTime();
    public int getTotalTime();
    public int getNumberPills();
    public int getNumberPowerPills();
    public int getLairTime(int whichTruc);
    public boolean trucRequiresAction(int whichTruc);
    public String getName();
    public int getInitialPacPosition();
    public int getInitialTrucsPosition();
    public int getNumberOfNodes();
    public int getX(int nodeIndex);
    public int getY(int nodeIndex);

    public int getPillIndex(int nodeIndex);
    public int getPowerPillIndex(int nodeIndex);
    public int getNeighbour(int nodeIndex, int direction);
    public int[] getPillIndices();
    public int[] getPowerPillIndices();
    public int[] getJunctionIndices();
    public int getNextEdibleTrucScore();
    public int getNumActivePills();
    public int getNumActivePowerPills();
    public int[] getPillIndicesActive();
    public int[] getPowerPillIndicesActive();
    public boolean isJunction(int nodeIndex);
    public int getNumNeighbours(int nodeIndex);
    public enum DM{PATH, EUCLID, MANHATTAN};
    public int getNextPacManDir(int to, boolean closer, DM measure);
    public int getNextTrucDir(int whichTruc, int to, boolean closer, DM measure);
    public int getPathDistance(int from, int to);
    public double getEuclideanDistance(int from, int to);
    public int getManhattanDistance(int from, int to);
    public int[] getPossiblePacManDirs(boolean includeReverse);
    public int[] getPossibleTrucDirs(int whichTruc);
    public int[] getPossibleDirs(int curLoc, int curDir, boolean includeReverse);
    public int[] getPath(int from, int to);
    public int[] getTrucPath(int whichTruc, int to);
    public int getTarget(int from, int[] targets, boolean nearest, DM measure);
    public int getTrucTarget(int from, int[] targets, boolean nearest);
    public int getTrucPathDistance(int whichTruc, int to);
}

