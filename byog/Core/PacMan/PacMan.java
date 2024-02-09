package byog.Core.PacMan;


import byog.Core.Operator.G;

/**
 * Controller of Ms Pac-Man.
 * <br/></br>
 * Action-selection should happen in {@link #tick(Game, long)}
 * while {@link #getAction()} should return just the result of the decision.
 */
public interface PacMan {

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
     * Perform action-selection based on information from {@link Game}.
     * <br/><br/>
     * Persist your decision within {@link PacManAction} that is periodically read via {@link #getAction()}.
     *
     * @param game current state of the game
     * @param timeDue how much time (in millis) do you have for your action-selection before {@link #getAction()} will get called.
     */
    public void tick(G game, long timeDue);

     /**
     * MS PacMan has been just eaten by a ghost.
     */

     public void killed();

    /**
     * Return {@link PacManAction} containing Ms Pac-Man decision, what to do next.
     * @return
     */
    public PacManAction getAction();






}
