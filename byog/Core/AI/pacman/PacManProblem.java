package byog.Core.AI.pacman;

import byog.Core.Operator.G;
import byog.Core.Operator.G.DM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PacManProblem implements Problem<Integer, Integer> {

    private G game;

    public PacManProblem (G game)  { this.game = game; }

    public Integer initialState() { return game.getCurPacManLoc(); }

    public List<Integer> actions(Integer state) {
        List<Integer> myList = new ArrayList<>();
        int[] dirs = game.getPossibleDirs(state, -1, true);
        for(int dir : dirs){
            myList.add(dir);
        }

        return myList;
    }

    public Integer result(Integer state, Integer action) { return game.getNeighbour(state, action); }

    public boolean isGoal(Integer state) {
        // SOME THINGS
         int superPill = game.getPowerPillIndex(state);
         int simplePill = game.getPillIndex(state);
         // int my = getNearestTruc(state);
        // int mydistances = game.getTarget(state, game.getCurTrucLoc(1), true, DM.PATH);


        if (gameIsEdible()) {
            for (int i = 0; i < 4; i++) {
                if (game.isEdible(i) && state == game.getCurTrucLoc(i)) {
                    // System.out.println("FOUND TRUC EDIBLE AS GOAL: " + i);
                    return true;
                }
            }
        } else {
            if (game.getNumActivePills() > 10) {
                if (superPill >= 0 && game.checkPowerPill(superPill)) {
                    // System.out.println("FOUND SUPER PILL: " + state);
                    return true;
                }

                if (simplePill >= 0 && game.checkPill(simplePill)) {
                    // System.out.println("FOUND PILL: " + state);
                }
            } else if (superPill >= 0 && game.checkPowerPill(superPill)) {
                // System.out.println("FOUND SUPER PILL: " + state);
            }

            if (simplePill >= 0 && game.checkPill(simplePill)) {
                // System.out.println("FOUND PILL: " + state);
                return true;
            }
        }
        return false;
    }


    public int getNearestTruc(Integer state) {
        int[] trucLoc = new int[4];
        HashMap<Integer, Integer> distMapToTrucIdx = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            trucLoc[i] = game.getCurTrucLoc(i);
            distMapToTrucIdx.put(game.getCurTrucLoc(i), i);
        }
        int nearestDist = game.getTarget(state, trucLoc, true, DM.PATH);
        int nearestTruc = distMapToTrucIdx.get(nearestDist);
        return nearestTruc;
    }


    public double cost(Integer state, Integer action) {
        int nextState = result(state, action);
        int superPill = game.getPowerPillIndex(nextState);
        int simplePill = game.getPillIndex(nextState);


        for(int i = 0; i < 4; i++) {
            if ((nextState == game.getCurTrucLoc(i)) && ! game.isEdible(i))
                return 20000000;
        }

        int nearestTruc = getNearestTruc(nextState);
        int nearestTrucLoc = game.getCurTrucLoc(nearestTruc);
        int distToNearest = game.getPathDistance(nextState, nearestTrucLoc);

        //
        //

        if (superPill >= 0 && game.checkPowerPill(superPill) && gameIsEdible())
            return 10000000;

        if (!game.isEdible(nearestTruc) && distToNearest <= 15)
            return 100000 - distToNearest * 2500 + 10;

        if (game.isEdible(nearestTruc)) {
            // && distToNearest <= 50 && g.getEdibleTime(nearestTruc) >= 25
            return 1;
        }

        if (superPill >= 0 && game.checkPowerPill(superPill) && !game.isEdible(nearestTruc) && distToNearest >= 25)
            return 100000;

        //if(superPill >= 0 && g.checkPowerPill(superPill)&&gameIsEdible())
        // return 100000;

        if (simplePill >= 0 && game.checkPill(simplePill) &&
                game.getNumActivePills() == 1 && game.getNumActivePowerPills() >= 1)
            return 100000;

        //if (simplePill >= 0 && g.checkPill(simplePill) && gameIsEdible())
        // return 100000;

        if (simplePill >= 0 && game.checkPill(simplePill) &&
                game.getNumActivePills() == 1 && game.getNumActivePowerPills() == 0)
            return 2000;

        if (simplePill >= 0 && game.checkPill(simplePill)) return 2000;
        // we need to give low costs to empty slots so, when we add up to path, it should not increase cost of path that much
        return 1;
    }

    private boolean gameIsEdible() {
        return (game.isEdible(1)||game.isEdible(0)||game.isEdible(2)||game.isEdible(3));
    }
}
