package concept.solver;

import concept.state.State;

import java.util.List;

public interface AStarSolver<T extends State> {
    List<T> solve(T currentState);
}
