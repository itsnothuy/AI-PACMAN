package byog.Core.AI.pacman;

import byog.Core.Operator.G;
import byog.Core.Operator.Game;
import byog.Core.Operator.PacManSimulator;
import byog.Core.PacMan.PacManHijackController;

public class PacmanOnly extends PacManHijackController {
    @Override
    public void tick(G game, long timeDue) {
        int current = game.getCurPacManLoc();

        // get all active pills
        int[] activePills = game.getPillIndicesActive();

        // get all active power pills
        int[] activePowerPills = game.getPowerPillIndicesActive();

        // create a target array that includes all ACTIVE pills and power pills
        int[] targetsArray = new int[activePills.length + activePowerPills.length];

        for(int i = 0; i < activePills.length; i++)
            targetsArray[i] = activePills[i];

        for(int i = 0; i < activePowerPills.length; i++)
            targetsArray[activePills.length + i] = activePowerPills[i];

        // return the next direction once the close target has been identified
        pacman.set(game.getNextPacManDir(game.getTarget(current, targetsArray, true, Game.DM.PATH), true, G.DM.PATH));
    }

    public static void main(String[] args) {
        PacManSimulator.play(new PacmanOnly());
    }
}
