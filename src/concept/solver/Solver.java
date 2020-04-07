package concept.solver;

import concept.stage.Stage;
import concept.state.State;

import java.util.List;

/**
 * Should probably be replaced by or complimented with a Factory
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
    List<? extends Stage<T>> solve(T currentState);
}
