package byog.Core.Truc;


import byog.Core.Operator.G;
import byog.Core.PacMan.PacManAction;

/**
        * Interface that Ghosts controllers must implement. The only method that is
        * required is getActions(-), which returns the direction to be taken:
        * Up - Right - Down - Left -> 0 - 1 - 2 - 3
        * Any other number is considered to be a lack of action (Neutral).
        */
public interface TrucController {
            /**
             * How many ghosts (0-4) this controller control.
             * @return
             */
            public int getTrucCount();

            /**
             * Resets the controller before game starts.
             *
             * @param game initial state of the game
             */
            public void reset(G game);

            /**
             * Level has been changed!
             *
             * @param game
             */
            public void nextLevel(G game);

            /**
             * Perform action-selection for all ghosts based on information from {@link G}.
             * <br/><br/>
             * Persist your decision within {@link PacManAction} that is periodically read via {@link #getAction()}.
             *
             * @param game current state of the game
             * @param timeDue how much time (in millis) do you have for your action-selection before {@link #getAction()} will get called.
             */

            public void tick(G game, long timeDue);

            /**
             * Return {@link GhostsActions} containing Ghosts decision, what to do next.
             * @return
             */

            public TrucsAction getActions();

        }
