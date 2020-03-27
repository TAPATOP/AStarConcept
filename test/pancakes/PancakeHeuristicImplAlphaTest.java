package pancakes;

import org.junit.Test;
import usecases.pancakes.PancakeHeuristic;

import static org.junit.Assert.*;
import static pancakes.TestCases.*;

public class PancakeHeuristicImplAlphaTest {
    private PancakeHeuristic hc = new PancakeHeuristic();

    @Test
    public void comparingGoalWithGoalReturnsEqual() {
        assertEquals(0, hc.heuristic(goal, goal));
    }

    @Test
    public void comparingOneFlipMirroredWithGoalReturnsWorse() {
        assertTrue(hc.heuristic(goal, goal) < hc.heuristic(oneFlipMirrored, goal));
    }

    @Test
    public void comparingOneFlip1WithGoalReturnsWorse() {
        assertTrue(hc.heuristic(goal, goal) < hc.heuristic(oneFlip1, goal));
    }

    @Test
    public void comparingOneFlip2WithGoalReturnsWorse() {
        assertTrue(hc.heuristic(goal, goal) < hc.heuristic(oneFlip2, goal));
    }

    @Test
    public void comparingMaxFlipsWithGoalReturnsWorse() {
        assertTrue(hc.heuristic(goal, goal) < hc.heuristic(maximumFlips, goal));
    }

    @Test
    public void comparingMaxFlipsWithOneFlipReturnsWorse() {
        assertTrue(hc.heuristic(oneFlip1, goal) < hc.heuristic(maximumFlips, goal));
    }

    @Test
    public void comparingOnePancakePileWithItselfReturnsEqual() {
        assertEquals(hc.heuristic(oneFlipMirrored, goal), hc.heuristic(oneFlipMirrored, goal));
    }

    @Test
    public void comparingTwoFlips1WithGoalReturnsWorse() {
        assertTrue(hc.heuristic(goal, goal)  < hc.heuristic(twoFlips1, goal));
    }

    @Test
    public void comparingTwoFlips1WithMaxFlipsReturnsBetter() {
        assertTrue(hc.heuristic(twoFlips1, goal) < hc.heuristic(maximumFlips, goal));
    }
}
