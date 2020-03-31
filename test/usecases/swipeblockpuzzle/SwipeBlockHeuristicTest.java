package usecases.swipeblockpuzzle;

import concept.heuristic.Heuristic;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static usecases.swipeblockpuzzle.TestCases.*;

public class SwipeBlockHeuristicTest {
    private Heuristic<SwipeBlock> heuristic = new SwipeBlockHeuristic();

    @Test
    public void givenSolvedStateReturnsZero() {
        assertEquals(0, heuristic.heuristic(eightSBSolved, eightSBSolved));
    }

    @Test
    public void givenOneMovesStateReturnsMoreThanGoal() {
        assertTrue(
                heuristic.heuristic(eightSBSolved, eightSBSolved) <
                        heuristic.heuristic(eightSBSolved, eightSBOneMove)
        );
    }

    @Test
    public void givenFiveMovesStateReturnsMoreThanOneMovesState() {
        assertTrue(
                heuristic.heuristic(eightSBOneMove, eightSBSolved) <
                        heuristic.heuristic(eightSBFiveMoves, eightSBSolved)
        );
    }

    @Test
    public void givenMaximumMovesStateReturnsMoreThanFiveMovesState() {
        assertTrue(
                heuristic.heuristic(eightSBFiveMoves, eightSBSolved) <
                        heuristic.heuristic(eightSBMaximumMoves, eightSBSolved)
        );
    }
}
