package concept.heuristic;

import concept.state.State;

@FunctionalInterface
public interface Heuristic<T extends State> {
    int heuristic(T current, T goal);
}
