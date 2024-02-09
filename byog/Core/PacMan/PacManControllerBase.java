package byog.Core.PacMan;

import byog.Core.Operator.G;

public abstract class PacManControllerBase implements PacMan {
    protected PacManAction pacman = new PacManAction();
    protected G game;
    protected Maze maze = new Maze();
    @Override
    public void reset(G game) {
        pacman.reset();
        this.game = game;
        maze.reset(game);
    }

    @Override
    public void nextLevel(G game) { maze.reset(game); }
    @Override
    public abstract void tick(G game, long timeDue);

    @Override
    public void killed() {
    }
    @Override
    public PacManAction getAction() {
        return pacman;
    }

}
