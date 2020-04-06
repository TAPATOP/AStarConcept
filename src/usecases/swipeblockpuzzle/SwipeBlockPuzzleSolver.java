package usecases.swipeblockpuzzle;

import concept.heuristic.Heuristic;
import concept.implementation.AStarDefault;
import concept.implementation.AStarImplAlpha;
import concept.solver.AStarSolver;

import java.util.List;

public class SwipeBlockPuzzleSolver implements AStarSolver<SwipeBlock> {
    private AStarDefault<SwipeBlock> aStar;

    @SuppressWarnings("FieldCanBeLocal")
    private Heuristic<SwipeBlock> heuristic;

    SwipeBlockPuzzleSolver(SwipeBlock goal) {
        heuristic = new SwipeBlockHeuristic();
        aStar = new AStarImplAlpha<>(goal, heuristic);
    }

    @Override
    public List<SwipeBlock> solve(SwipeBlock currentState) {
        return aStar.solve(currentState);
    }
}
