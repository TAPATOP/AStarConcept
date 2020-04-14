package concept.heuristic;

import concept.state.State;

/**
 * Given any State and a goal State, calculates what its heuristic value is.
 * It's one of the signature features of A*.
 * @param <T>
 */
@FunctionalInterface
public interface Heuristic<T extends State> {
    int heuristic(T current, T goal);
}
