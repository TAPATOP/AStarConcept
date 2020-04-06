package usecases.pancakes;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static usecases.pancakes.TestCases.*;

public class PancakeSolverTest {

    PancakeSolver pancakeSolver = new PancakeSolver(goal);
    List<PancakeAbstract> result;

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