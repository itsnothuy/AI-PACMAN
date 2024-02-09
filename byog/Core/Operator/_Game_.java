package byog.Core.Operator;

import byog.Core.Operator.PacManSimulator.GameConfig;
import java.util.BitSet;

public class _Game_ extends Game {
    public static final int EDIBLE_ALERT = 30;  // for display only (ghosts turning blue)

    // to save replays
    private int pacManDir = Game.INITIAL_PAC_DIR;
    private StringBuilder sb;
    public _Game_(){}

    // Instantiates everything to start a new game
    public void newGame(GameConfig config) {
        this.config = config;
        this.remainingLevels = config.levelsToPlay;

        init();  // load mazes if not yet loaded

        curMaze = 0;

        curTrucLocs = new int[Game.NUM_TRUCS];
        lastTrucDirs = new int[Game.NUM_TRUCS];
        edibleTimes = new int[Game.NUM_TRUCS];
        lairTimes = new int[Game.NUM_TRUCS];

        pills = new BitSet(getNumberPills());
        pills.set(0, getNumberPills());
        powerPills = new BitSet(getNumberPowerPills());
        powerPills.set(0, getNumberPowerPills());
        score = 0;
        levelTime = 0;
        totalTime = 0;
        totLevel = 0;
        livesRemaining = config.lives;
        extraLife = false;
        gameOver = false;

        if (!config.powerPillsEnabled) {
            powerPills.clear();
        }
        if (config.totalPills < 1) {
            int number = (int) Math.ceil(pills.length() * (1 - (config.totalPills > 0 ? config.totalPills : 0)));
            decimatePills(number);
        }

        reset(false);

        // for replays
        this.sb = new StringBuilder();
    }

    // Size of the Maze (for display only)
    public int getWidth() { return mazes[curMaze].width; }

    // Size of the maze (for display only)
    public int getHeight() { return mazes[curMaze].height; }

    // for the web-site javascript replays
    public void monitorGame() {
        sb.append("{");

        //maze
        sb.append("ma:" + curMaze + ",");
        sb.append("tt:" + totalTime + ",");
        sb.append("li:" + livesRemaining + ",");
        sb.append("sc:" + score + ",");
        sb.append("lt:" + levelTime + ",");
        sb.append("le:" + totLevel + ",");

        // pacman
        sb.append("pn:" + curPacManLoc + ",");

        int pacDir = lastPacManDir;

        if (pacDir >= 0 && pacDir < 4)
            pacManDir = pacDir;

        sb.append("pd:" + pacManDir + ",");

        // ghosts
        for (int i = 0; i < 4; i++) {
            sb.append("tr:[");
            sb.append("{tr:" + curTrucLocs[i] + ",");
            sb.append("di:" + lastTrucDirs[i] + ",et:" + edibleTimes[i]);
            sb.append(",lt:" + lairTimes[i]);
            sb.append("},");
        }

        // pills
        sb.append("pi:\"");

        for (int i = 0; i < getPillIndices().length; i++)
            if (checkPill(i))
                sb.append("1");
            else
                sb.append("0");

        sb.append("\",");
        sb.append("po:\"");

        for (int i = 0; i < getPowerPillIndices().length; i++)
            if (checkPowerPill(i))
                sb.append("1");
            else
                sb.append("0");

        sb.append("\"");
        sb.append("},\n");
    }

    public StringBuilder getRecordedMatch() { return sb; }
}

