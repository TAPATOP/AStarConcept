package usecases.swipeblockpuzzle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static usecases.swipeblockpuzzle.TestCases.*;

public class SolutionTest {
    SwipeBlockPuzzleSolver solver = new SwipeBlockPuzzleSolver(eightSBSolved);

    @Test
    public void givenEightSBGoalStateReturns1() {
        assertEquals(1, solver.solve(eightSBSolved).size());
    }

    @Test
    public void givenEightSB1MoveReturns2() {
        assertEquals(2, solver.solve(eightSBOneMove).size());
    }

    @Test
    public void givenEightSB5MovesReturns6() {
        assertEquals(6, solver.solve(eightSBFiveMoves).size());
    }

    @Test
    public void givenEightSB4MovesReturns5() {
        assertEquals(5, solver.solve(eightSBPuzzle2).size());
    }

    @Test
    public void givenEightSBMax1Returns32() {
        assertEquals(32, solver.solve(eightSBMaximumMoves2).size());
    }

    @Test
    public void givenEightSBMax2Returns32() {
        assertEquals(32, solver.solve(eightSBMaximumMoves1).size());
    }
}
