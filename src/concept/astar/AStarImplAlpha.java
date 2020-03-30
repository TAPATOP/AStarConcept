package concept.astar;

import concept.heuristic.Heuristic;
import concept.state.State;

import java.util.List;

public class AStarImplAlpha<T extends State> extends AStarDefault<T> {
   public AStarImplAlpha(T state, Heuristic<T> heuristic) {
       super(state, heuristic);
   }

    @Override
    public List<T> solve(T currentState) {
        return null;
    }
}
