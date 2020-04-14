package concept.solver;

import concept.stage.Stage;
import concept.state.State;

import java.util.Stack;

/**
 * TODO: Solver<StateType, ChangeArgType>
 * Should probably be replaced by or complimented with a Factory
 *
 * Interface for anything that can reach a goalState from a currentState.
 * As it stands, this is not a very proper thing to exist, so I should fix
 * it.
 */
public interface Solver<T extends State> {
    /**
     * This method returns the different steps that would solve the goal. Therefore, the final
     * element would be the goal itself, implying the size of the solution is bigger than the
     * required steps by 1
     *
     * @param currentState The current state of the model
     * @return a list of steps required to solve the problem
     */
    Stack<? extends Stage<T>> solve(T currentState);
}
