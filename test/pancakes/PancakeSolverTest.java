package pancakes;

import concept.astar.AStarDefault;
import concept.state.State;
import org.junit.Test;
import usecases.pancakes.PancakeHeuristic;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static pancakes.TestCases.*;

public class PancakeSolverTest {

    PancakeHeuristic pc = new PancakeHeuristic();
    AStarDefault pancakeSolver = new AStarDefault(goal, pc);
    List<State> result;

    @Test
    public void givenOneFlipMirroredSolvesInOneStep() {
        result = pancakeSolver.solve(oneFlipMirrored);
        assertEquals(2, result.size());
    }

    @Test
    public void givenTwoFlipsSolvesInTwoSteps_1() {
        result = pancakeSolver.solve(twoFlips2);
        assertEquals(3, result.size());
    }

    @Test
    public void givenTwoFlipsSolvesWithTwoSteps_2() {
        result = pancakeSolver.solve(twoFlips1);
        assertEquals(3, result.size());
    }

    @Test
    public void givenMaxFlipsSolvesWithSevenSteps() {
        result = pancakeSolver.solve(maximumFlips);
        assertEquals(8, result.size());
    }
}