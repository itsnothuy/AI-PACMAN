package byog.Core.Operator;


import byog.Core.Truc.TrucsAction;

import java.util.Arrays;

/**
 * This class is to replay games that were recorded using Replay. The only differences are:
 * 1. Ghost reversals are removed
 * 2. Directions are not checked (since they are necessarily valid)
 * This class should only be used in conjunction with stored directions, not to play the game itself.
 */

public final class _RGame_ extends _Game_ {
    // Updates the locations of the ghosts without reversals
    protected void updateTrucs(TrucsAction trucs, boolean reverse) {
        super.updateTrucs(trucs, false);
    }
    public int[] getTrucNeighbours(int whichTruc) {
        int[] neighbours = Arrays.copyOf(mazes[curMaze].graph[curTrucLocs[whichTruc]].neighbours,
                mazes[curMaze].graph[curTrucLocs[whichTruc]].neighbours.length);
        return neighbours;
    }

    public int checkTrucDir(int whichTruc, int direction) {
        int origCheck = super.checkTrucDir(whichTruc, direction);
        if (origCheck != direction) {
            super.checkTrucDir(whichTruc, direction);
        }
        return direction;
    }

    public int checkPacManDir(int direction){ return direction; }
}
