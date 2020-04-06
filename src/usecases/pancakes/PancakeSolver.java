package usecases.pancakes;

import concept.heuristic.Heuristic;
import concept.implementation.AStarDefault;
import concept.implementation.AStarImplAlpha;
import concept.solver.AStarSolver;

import java.util.List;

public class PancakeSolver implements AStarSolver<PancakeAbstract> {
    private AStarDefault<PancakeAbstract> aStar;

    @SuppressWarnings("FieldCanBeLocal")
    private Heuristic<PancakeAbstract> heuristic;

    PancakeSolver(PancakeAbstract goal) {
        heuristic = new PancakeHeuristic();
        aStar = new AStarImplAlpha<>(goal, heuristic);
    }

    @Override
    public List<PancakeAbstract> solve(PancakeAbstract currentState) {
        return aStar.solve(currentState);
    }
}
