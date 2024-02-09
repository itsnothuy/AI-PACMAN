package byog.Core.PacMan;


import byog.Core.Operator.G;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class HumanPacMan implements PacMan, KeyListener {
    private PacManAction input = new PacManAction();
    @Override
    public void reset(G game) {
        input.reset();
    }
    @Override
    public void nextLevel(G game) {
    }

    @Override
    public void tick(G game, long timeDue) {
    }

    @Override
    public void killed() {
    }

    @Override
    public PacManAction getAction() {
        return input;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) input.up();
        else if (key == KeyEvent.VK_RIGHT) input.right();
        else if (key == KeyEvent.VK_DOWN) input.down();
        else if (key == KeyEvent.VK_LEFT) input.left();
        else if (key == KeyEvent.VK_P) input.togglePause();
        else if (key == KeyEvent.VK_N && input.pauseSimulation) input.nextFrame = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
