package usecases.pancakes;

import concept.heuristic.Heuristic;
import concept.implementation.AStarDefault;
import concept.implementation.AStarImplAlpha;
import concept.solver.Solver;
import concept.stage.Stage;

import java.util.List;
import java.util.Stack;

public class PancakeSolver implements Solver<PancakeAbstract> {
    private AStarDefault<PancakeAbstract, Stage<PancakeAbstract>> aStar;

    @SuppressWarnings("FieldCanBeLocal")
    private Heuristic<PancakeAbstract> heuristic;

    public PancakeSolver(PancakeAbstract goal) {
        heuristic = new PancakeHeuristic();
        aStar = new AStarImplAlpha<>(goal, heuristic);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Stack<Stage<PancakeAbstract>> solve(PancakeAbstract currentState) {
        return (Stack<Stage<PancakeAbstract>>) aStar.solve(currentState);
    }
}
