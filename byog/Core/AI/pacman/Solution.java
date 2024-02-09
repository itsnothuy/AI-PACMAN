package byog.Core.AI.pacman;

import java.util.List;

public class Solution <S, A>{
    public List<A> actions;
    public S goalState;
    public double pathCost;

    public Solution(List<A> actions, S goalState, double pathCost) {
        this.actions = actions;
        this.goalState = goalState;
        this.pathCost = pathCost;
    }

    public boolean isValid(Problem<S, A> prob) {
        S state = prob.initialState();
        double cost = 0.0;

        // Check that the actions actually lead from the problem's initial state to the goal
        for(A action : actions) {
            state = prob.result(state, action);
            cost += prob.cost(state, action);
        }

        return state.equals(goalState) && prob.isGoal(goalState) && pathCost == cost;
    }

    // Describe a solution
    public static <S, A> void report(Solution<S, A> solution, Problem<S, A> prob) {
        if (solution == null)
            System.out.println("no solution found");
        else if (!solution.isValid(prob))
            System.out.println("solution is invalid");
        else {
            System.out.println("solution is valid");
            System.out.format("total cost is %.1f\n", solution.pathCost);
        }
    }
}
