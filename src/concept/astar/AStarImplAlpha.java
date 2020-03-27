package concept.astar;

import concept.heuristic.Heuristic;
import concept.state.State;

import java.util.List;

public class AStarImplAlpha extends AStarDefault {
   public AStarImplAlpha(State state, Heuristic heuristic) {
       super(state, heuristic);
   }

    @Override
    public List<State> solve(State currentState) {
        return null;
    }
}
