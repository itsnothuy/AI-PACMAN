package byog.Core.AI.pacman;

import byog.Core.Operator.G;
import byog.Core.Operator.Game;
import byog.Core.Operator.PacManSimulator;
import byog.Core.PacMan.PacManHijackController;
import byog.Core.Truc.Truc;

public class RandomPacMan extends PacManHijackController {

    @Override
    public void tick(G game, long timeDue) {
        int[] directions = game.getPossiblePacManDirs(true);
        pacman.set(directions[Game.rnd.nextInt(directions.length)]);
    }

    public static void main(String[] agrs) {
        PacManSimulator.play(new RandomPacMan(), new Truc(true));
    }
}
