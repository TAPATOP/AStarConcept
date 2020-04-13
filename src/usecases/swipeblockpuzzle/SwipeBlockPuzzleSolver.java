package usecases.swipeblockpuzzle;

import concept.heuristic.Heuristic;
import concept.implementation.AStarDefault;
import concept.implementation.AStarImplAlpha;
import concept.solver.Solver;
import concept.strategies.expander.Expander;

import java.util.Stack;

public class SwipeBlockPuzzleSolver implements Solver<SwipeBlock> {
    private AStarDefault<SwipeBlock, SwipeBlockStage, String> aStar;

    @SuppressWarnings("FieldCanBeLocal")
    private Heuristic<SwipeBlock> heuristic;

    public SwipeBlockPuzzleSolver(SwipeBlock goal) {
        heuristic = new SwipeBlockHeuristic();
        Expander<SwipeBlock, SwipeBlockStage, String> expander =
                new SwipeBlockExpander(SwipeBlock.getPossibleDirections()
        );
        aStar = new AStarImplAlpha<SwipeBlock, SwipeBlockStage, String>(goal, heuristic, expander) {
            @Override
            protected void prepareForSolving(SwipeBlock currentState) {
                // TODO super.prepareForSolving(currentState);
                queue.add(new SwipeBlockStage(currentState));
                visitedStates.add(currentState);
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Stack<SwipeBlockStage> solve(SwipeBlock currentState) {
        return (Stack<SwipeBlockStage>) aStar.solve(currentState);
    }
}
