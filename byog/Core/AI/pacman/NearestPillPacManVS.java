package byog.Core.AI.pacman;

import byog.Core.Operator.G;
import byog.Core.Operator.Game;
import byog.Core.Operator.GameView;
import byog.Core.Operator.PacManSimulator;
import byog.Core.PacMan.PacManHijackController;
import byog.Core.Truc.Truc;

import java.awt.*;


/*
 * Same as NearestPillPacMan but does some visuals to illustrate what can be done.
 * Please note: the visuals are just to highlight different functionalities and may
 * not make sense from a controller's point of view (i.e., they might not be useful)
 * Comment/un-comment code below as desired (drawing all visuals would probably be too much).
 */
public final class NearestPillPacManVS extends PacManHijackController {
    @Override
    public void tick(G game, long timeDue){
        int current = game.getCurPacManLoc();
        int[] activePills = game.getPillIndicesActive();
        int[] activePowerPills = game.getPowerPillIndicesActive();
        int[] targetsArray = new int[activePills.length + activePowerPills.length];

        for(int i = 0; i < activePills.length; i++) {
            targetsArray[i] = activePills[i];
        }

        for(int i = 0; i < activePowerPills.length; i++) {
            targetsArray[activePills.length + i] = activePowerPills[i];
        }

        int nearest = game.getTarget(current, targetsArray, true, Game.DM.PATH);

        // add the path that Ms Pac-Man is following
        GameView.addPoints(game, Color.PINK, game.getPath(current, nearest));

        pacman.set(game.getNextPacManDir(nearest, true, G.DM.PATH));
    }

    public static void main(String[] args) {
        while(true) {
            PacManSimulator.play(new NearestPillPacManVS(), new Truc(0, true));
            GameView.lastInstance.setVisible(true);
        }
    }

}
