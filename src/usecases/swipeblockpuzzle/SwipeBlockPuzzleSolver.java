package usecases.swipeblockpuzzle;

import concept.heuristic.Heuristic;
import concept.implementation.AStarDefault;
import concept.implementation.AStarImplAlpha;
import concept.solver.Solver;
import concept.strategies.expander.Expander;

import java.util.List;
import java.util.Stack;

public class SwipeBlockPuzzleSolver implements Solver<SwipeBlock> {
    private AStarDefault<SwipeBlock, SwipeBlockStage, String> aStar;

    @SuppressWarnings("FieldCanBeLocal")
    private Heuristic<SwipeBlock> heuristic;

    public SwipeBlockPuzzleSolver(SwipeBlock goal) {
        heuristic = new SwipeBlockHeuristic();
        Expander<SwipeBlock, SwipeBlockStage, String> expander =
                new Expander<>(SwipeBlock.getPossibleDirections()
        );
        aStar = new SwipeBlockAStar(goal, heuristic, expander);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Stack<SwipeBlockStage> solve(SwipeBlock currentState) {
        return (Stack<SwipeBlockStage>) aStar.solve(currentState);
    }

    static class SwipeBlockAStar extends AStarImplAlpha<SwipeBlock, SwipeBlockStage, String> {

        public SwipeBlockAStar(
                SwipeBlock goal,
                Heuristic<SwipeBlock> heuristic,
                Expander<SwipeBlock, SwipeBlockStage, String> expander

        ) {
            super(goal, heuristic, expander);
        }

        @Override
        protected void prepareForSolving(SwipeBlock currentState) {
            queue.add(new SwipeBlockStage(currentState));
            visitedStates.add(currentState);
        }

        @Override
        protected void step() {
            final SwipeBlockStage currentStage = queue.poll();

            if (currentStage == null) return;

            SwipeBlockExpander swipeBlockExpander = new SwipeBlockExpander(SwipeBlock.getPossibleDirections());
            swipeBlockExpander.setStage(currentStage);

            for (SwipeBlockStage changedStage : swipeBlockExpander) {
                if (changedStage == null || visitedStates.contains(changedStage.getState())) {
                    continue;
                }
                visitedStates.add(changedStage.getState());
                queue.add(changedStage);
                if (changedStage.getState().equals(goal)) {
                    break;
                }
            }
        }
    }
}
