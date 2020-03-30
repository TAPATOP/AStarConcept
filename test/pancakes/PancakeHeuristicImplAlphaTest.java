package pancakes;

import concept.heuristic.Heuristic;
import org.junit.Test;
import usecases.pancakes.PancakeAbstract;
import usecases.pancakes.PancakeHeuristic;

import static org.junit.Assert.*;
import static pancakes.TestCases.*;

public class PancakeHeuristicImplAlphaTest {
    private Heuristic<PancakeAbstract> pancakeHeuristic = new PancakeHeuristic();

    @Test
    public void comparingGoalWithGoalReturnsEqual() {
        assertEquals(0, pancakeHeuristic.heuristic(goal, goal));
    }

    @Test
    public void comparingOneFlipMirroredWithGoalReturnsWorse() {
        assertTrue(pancakeHeuristic.heuristic(goal, goal) < pancakeHeuristic.heuristic(oneFlipMirrored, goal));
    }

    @Test
    public void comparingOneFlip1WithGoalReturnsWorse() {
        assertTrue(pancakeHeuristic.heuristic(goal, goal) < pancakeHeuristic.heuristic(oneFlip1, goal));
    }

    @Test
    public void comparingOneFlip2WithGoalReturnsWorse() {
        assertTrue(pancakeHeuristic.heuristic(goal, goal) < pancakeHeuristic.heuristic(oneFlip2, goal));
    }

    @Test
    public void comparingMaxFlipsWithGoalReturnsWorse() {
        assertTrue(pancakeHeuristic.heuristic(goal, goal) < pancakeHeuristic.heuristic(maximumFlips, goal));
    }

    @Test
    public void comparingMaxFlipsWithOneFlipReturnsWorse() {
        assertTrue(pancakeHeuristic.heuristic(oneFlip1, goal) < pancakeHeuristic.heuristic(maximumFlips, goal));
    }

    @Test
    public void comparingOnePancakePileWithItselfReturnsEqual() {
        assertEquals(pancakeHeuristic.heuristic(oneFlipMirrored, goal), pancakeHeuristic.heuristic(oneFlipMirrored, goal));
    }

    @Test
    public void comparingTwoFlips1WithGoalReturnsWorse() {
        assertTrue(pancakeHeuristic.heuristic(goal, goal)  < pancakeHeuristic.heuristic(twoFlips1, goal));
    }

    @Test
    public void comparingTwoFlips1WithMaxFlipsReturnsBetter() {
        assertTrue(pancakeHeuristic.heuristic(twoFlips1, goal) < pancakeHeuristic.heuristic(maximumFlips, goal));
    }
}
