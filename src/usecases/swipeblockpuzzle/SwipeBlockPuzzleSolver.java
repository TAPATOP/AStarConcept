package usecases.swipeblockpuzzle;

import concept.heuristic.Heuristic;
import concept.implementation.AStarDefault;
import concept.implementation.AStarImplAlpha;
import concept.solver.Solver;

import java.util.List;

public class SwipeBlockPuzzleSolver implements Solver<SwipeBlock> {
    private AStarDefault<SwipeBlock> aStar;

    @SuppressWarnings("FieldCanBeLocal")
    private Heuristic<SwipeBlock> heuristic;

    public SwipeBlockPuzzleSolver(SwipeBlock goal) {
        heuristic = new SwipeBlockHeuristic();
        aStar = new AStarImplAlpha<>(goal, heuristic);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SwipeBlockStage> solve(SwipeBlock currentState) {
        return (List<SwipeBlockStage>) aStar.solve(currentState);
    }
}
