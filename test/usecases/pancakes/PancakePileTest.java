package usecases.pancakes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static usecases.pancakes.TestCases.*;

public class PancakePileTest {
    @Test
    public void givenGoalAndLastIndexReturnsMirrored() {
        assertEquals(goal.flip(5), oneFlipMirrored);
    }

    @Test
    public void givenMirroredAndLastIndexReturnsGoal() {
        assertEquals(oneFlipMirrored.flip(5), goal);
    }

    @Test
    public void givenGoalAndFirstIndexReturnsGoal() {
        assertEquals(goal.flip(0), goal);
    }

    @Test
    public void givenGoalAndFlippedTwiceReturnsTwoFlips() {
        assertEquals(goal.flip(5).flip(2), twoFlips2);
    }

    @Test
    public void givenTwoFlipsAndFlippedTwiceReturnsGoal() {
        assertEquals(twoFlips2.flip(2).flip(5), goal);
    }
}
