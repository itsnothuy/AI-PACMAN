package byog.Core.Operator;

import byog.Core.PacMan.PacMan;
import byog.Core.PacMan.PacManAction;
import byog.Core.Truc.TrucController;
import byog.Core.Truc.TrucsAction;
import byog.Core.Operator.Replay.ReplayMsPacman;
import byog.Core.Operator.Replay.ReplayTrucs;
import byog.Core.Operator._RGame_;
import byog.Core.Operator.GameView;
import byog.Core.Operator.Game;
import byog.Core.Operator.PacManSimulator.GameConfig;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;

/**
 * One replayer can run one instance of PacMan-vs-Ghosts replay.
 *
 * Can be used for both head/less games.
 *
 * TODO: for some strange reasons, this is not working correctly ... problems usually starts when PacMan eats power-pill...
 *       It seems that replays are not correctly replayed ...
 *
 * @author Jimmy
 */
public class PacManReplayer {

    public static class ReplayerConfig {

        public boolean visualize = true;

        public boolean visualizationScale2x = true;

        /**
         * How fast the replay be running.
         *
         * DEFAULT: 40 FPS
         */
        public int visualizationMillis = 25;

        /**
         * Must be filled out!
         */
        public File replayFile = null;
    }

    private ReplayerConfig config;
    private GameView gv;
    private _RGame_ game;

    // HIJACK FIELDS
    private Hijack hijack;
    private boolean paused = false;
    private boolean nextFrame = false;

    public synchronized void run(final ReplayerConfig config) {
        // SANITY
        if (config.replayFile == null || !config.replayFile.exists() || !config.replayFile.isFile()) {
            throw new RuntimeException("Cannot run replayer as replay file is not specified/invalid.");
        }

        // RESET INSTANCE & SAVE CONFIG
        reset(config);

        // LOAD REPLAY
        Replay replay = new Replay(config.replayFile);

        Game.rnd = new Random(replay.gameConfig.seed);

        // INITIALIZE THE SIMULATION
        game = new _RGame_();
        game.newGame(replay.gameConfig);

        // INIT CONTROLLERS
        final PacMan pacManController = replay.getPacMan();
        final TrucController trucController = replay.getTrucs();

        // INITIALIZE THE VIEW
        if (config.visualize) {
            gv = new GameView(game);
            if (config.visualizationScale2x)
                gv.setScale2x(true);
            gv.showGame();
            hijack = new Hijack();
            gv.getFrame().addKeyListener(hijack);
        }

        try {
            // RUN THE REPLAY!
            while(!game.gameOver()) {
                //TICK CONTROLLERS
                pacManController.tick(game, System.currentTimeMillis() + config.visualizationMillis);
                trucController.tick(game, System.currentTimeMillis() + config.visualizationMillis);

                // GET ACTIONS
                PacManAction pacManAction = pacManController.getAction();
                TrucsAction trucsAction = trucController.getActions();

                // ADVANCE SIMULATION
                if (!paused || nextFrame) {
                    nextFrame = false;
                    game.advanceGameReplay(pacManAction, trucsAction, ((ReplayMsPacman)pacManController).getLocation(game),
                            ((ReplayTrucs)trucController).getLocations(game));
                }

                // VISUALIZE
                if (config.visualize) {
                    gv.repaint();
                    try {
                        Thread.sleep(config.visualizationMillis);
                    } catch(Exception e){
                    }
                }
            }
        } finally {
            // CLEAN UP
            if (config.visualize) {
                gv.getFrame().setTitle("[FINISHED]");
                gv.repaint();
                gv.getFrame().removeKeyListener(hijack);
            }
        }
    }

    private void reset(ReplayerConfig config) {
        this.config = config;

        gv = null;
        game = null;
    }

    private class Hijack implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_P) {
                paused = !paused;
                nextFrame = false;
            } else if (key == KeyEvent.VK_N) {
                if (paused) nextFrame = true;
            } else if (key == KeyEvent.VK_Z) {
                config.visualizationMillis -= 5;
                if (config.visualizationMillis < 10)
                    config.visualizationMillis = 10;
            } else if (key == KeyEvent.VK_X) {
                config.visualizationMillis += 5;
                if (config.visualizationMillis > 100)
                    config.visualizationMillis = 100;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }

    public static void main(String[] args) {
        PacManReplayer replayer = new PacManReplayer();

        ReplayerConfig config = new ReplayerConfig();

        config.replayFile = new File("../PacMan-vs-Ghosts-Agents/replay.log");

        replayer.run(config);
    }
}
