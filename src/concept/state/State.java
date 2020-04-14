package concept.state;

import concept.exceptions.InvalidChangeException;

/**
 * A concept that represents the model and all of its possible variations,
 * including the goal model and the model we need to reach that goal from.
 *
 * Can be used interchangeably with Stage in some contexts.
 *
 * @param <ChangeArgType>
 */
public interface State<ChangeArgType> {
    /**
     * This actually doesn't change the State itself, it just creates a state that
     * you can reach from the current through the given command, so maybe I could rename
     * the method
     * @param command Some way of changing the State
     * @return a new State that is logically reachable from the current
     */
    State<ChangeArgType> change(ChangeArgType command) throws InvalidChangeException;

    /**
     * Currently used when I need to create an iterator that
     * will iterate through all possible immediately following States
     * of this one.
     *
     * @param command Some way of changing the State
     * @return whether the State can be changed with that command
     */
    boolean canChange(ChangeArgType command);

    /**
     * TODO
     * Stating that toString should be overridden. This can be forced in a few ways that
     * I might consider eventually.
     * @return stringified version of the State
     */
    @Override
    String toString();
}
