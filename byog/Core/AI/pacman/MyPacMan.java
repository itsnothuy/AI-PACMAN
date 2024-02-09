package byog.Core.AI.pacman;

import byog.Core.Operator.G;
import byog.Core.Operator.GameView;
import byog.Core.Operator.PacManSimulator;
import byog.Core.PacMan.PacManHijackController;
import byog.Core.AI.pacman.Usc;
import byog.Core.Truc.Truc;

import java.awt.*;

public class MyPacMan extends PacManHijackController {
    @Override
    public void tick(G game, long timeDue) {
        // Code your agent here
        PacManProblem pacManProblem = new PacManProblem(game);
        int state = pacManProblem.initialState();
        Solution<Integer, Integer> n = Usc.search(pacManProblem);

        if (n == null) {
            System.out.println("Couldn't find goal node, so going on current direction");
            pacman.set(game.getCurPacManDir());
            return;
        }

        int[] trucDistances = new int[] {
                game.getPathDistance(state, game.getCurTrucLoc(0)),
                game.getPathDistance(state, game.getCurTrucLoc(1)),
                game.getPathDistance(state, game.getCurTrucLoc(2)),
                game.getPathDistance(state, game.getCurTrucLoc(3)),
        };
        GameView.addText(0, 10, Color.YELLOW, "Truc distances: " +
                trucDistances[0] + ", " + trucDistances[1] + ", " + trucDistances[2] + ", " + trucDistances[3]);

        GameView.addPoints(game, Color.GREEN, game.getPath(state, n.goalState));
        System.out.println("Decided to go on goal mode: " + n.goalState + " with path " + n.pathCost);

        int action = n.actions.get(0);
        pacman.set(action);
    }

    public static void main(String[] args) {
        // If seed = 0, a random seed is chosen on every run. Set seed to a positive value
        // for repeatable play
        int seed = 0;

        // The number of lives to start with
        int lives = 3;

        // The number of milliseconds between frame; 40 ms = 25 frame per second.
        int thinkTime = 40;

        PacManSimulator.play(new MyPacMan(), new Truc(1, false), seed, lives, thinkTime);
    }
}
