package concept.state;

import concept.exceptions.InvalidChangeException;

import java.util.Iterator;

public interface State<ChangeArgType> extends Iterable<State<ChangeArgType>> {
    @Override
    Iterator<State<ChangeArgType>> iterator();

    /**
     * This actually doesn't change the State itself, it just creates a state that
     * you can reach from the current through the given command, so maybe I could rename
     * the method
     * @param command Some way of changing the State
     * @return a new State that is logically reachable from the current
     */
    State<ChangeArgType> change(ChangeArgType command) throws InvalidChangeException;

    boolean canChange(ChangeArgType command);

    /**
     * TODO
     * Stating that toString should be overridden. This can be forced in a few ways that
     * I might consider eventually.
     * @return
     */
    @Override
    String toString();
}
