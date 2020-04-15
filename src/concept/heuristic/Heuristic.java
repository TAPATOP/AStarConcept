package concept.heuristic;

import concept.state.State;

/**
 * Given any State and a goal State, calculates what its heuristic value is.
 * It's one of the signature features of A*.
 *
 * @param <StateType> Ensures both states are of the same type
 */
@FunctionalInterface
public interface Heuristic<StateType extends State> {
    /**
     * Rules a heuristic should follow and therefore must be guaranteed
     * with unit tests:
     * 1) If @current and @goal are the same, the heuristic value must be 0
     *
     * 2) If @current is different from @goal, the heuristic value must be at
     * least 1
     *
     * 3) Heuristic value when @current can be transformed into @goal
     * just by one or subjectively low number of State::change invocations must be
     * smaller than the heuristic value when @current needs the theoretical maximum
     * or subjectively close number of change() invocations to become @goal
     *
     * 4) Any number of invocations with the same @current and @goal( independent
     * of whether @current.equals(@goal)) in any order compared to other
     * heuristic invocations must always return the same heuristic value
     *
     * Of course there are other requirements for heuristic functions, but these
     * are the ones that can be easily checked through Unit tests
     *
     * @param current
     * @param goal
     * @return heuristic value
     */
    int heuristic(StateType current, StateType goal);
}
