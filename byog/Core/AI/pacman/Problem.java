package byog.Core.AI.pacman;

import java.util.List;

public interface Problem<S, A> {
    S initialState();
    List<A> actions(S state);
    S result(S state, A action);
    boolean isGoal(S state);
    double cost(S state, A action);

}
