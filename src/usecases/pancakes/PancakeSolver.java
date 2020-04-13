package usecases.pancakes;

import concept.heuristic.Heuristic;
import concept.implementation.AStarDefault;
import concept.implementation.AStarImplAlpha;
import concept.solver.Solver;
import concept.stage.Stage;
import concept.strategies.expander.Expander;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PancakeSolver implements Solver<PancakeAbstract> {
    private AStarDefault<PancakeAbstract, Stage<PancakeAbstract>, Integer> aStar;

    @SuppressWarnings("FieldCanBeLocal")
    private Heuristic<PancakeAbstract> heuristic;

    public PancakeSolver(PancakeAbstract goal) {
        heuristic = new PancakeHeuristic();

        List<Integer> possibleCommands = new LinkedList<>();

        for(int i = 0; i < goal.size(); i++) {
            possibleCommands.add(i);
        }

        // TODO: make this a new class
        Expander<PancakeAbstract, Stage<PancakeAbstract>, Integer> expander =
                new Expander<>(possibleCommands);

        aStar = new AStarImplAlpha<>(goal, heuristic, expander);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Stack<Stage<PancakeAbstract>> solve(PancakeAbstract currentState) {
        return (Stack<Stage<PancakeAbstract>>) aStar.solve(currentState);
    }
}
