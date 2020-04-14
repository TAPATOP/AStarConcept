package concept.strategies.expander;

import concept.exceptions.InvalidChangeException;
import concept.stage.Stage;
import concept.state.State;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A Strategy whose goal is to, given a set of commands, to feed them
 * into a specific State and find out who are its next immediate States.
 *
 * IMPORTANT: Currently, proper use of this class needs to have its "setStage" method
 * invoked prior iterating over it. Not sure how to enforce this atm.
 *
 * @param <StateType>
 * @param <StageType>
 * @param <ChangeArgType>
 */
public class ExpanderStrategy<
        StateType extends State<ChangeArgType>,
        StageType extends Stage<StateType>,
        ChangeArgType>
        implements Iterable<StageType> {

    // The stage whose immediate children we need to find
    protected StageType currentStage;

    // Filtered commands that the currentStage can handle properly, e.g.
    // guaranteed to return a child once given to it
    protected Iterator<ChangeArgType> currentPossibleCommandsIterator;

    // All possible commands you can issue to a State of this type
    final protected List<ChangeArgType> allPossibleCommands;

    public ExpanderStrategy(List<ChangeArgType> allPossibleCommands) {
        this.allPossibleCommands = allPossibleCommands;
    }

    /**
     * Important to be called before iterating through the expander,
     * so we know what State we're exploring
     *
     * @param stage Stage of the State we're exploring
     */
    public void setStage(StageType stage) {
        currentStage = stage;
        List<ChangeArgType> currentPossibleChanges = new LinkedList<>();

        for(ChangeArgType command : allPossibleCommands) {
            if (currentStage.getState().canChange(command)) {
                currentPossibleChanges.add(command);
            }
        }

        this.currentPossibleCommandsIterator = currentPossibleChanges.iterator();
    }

    @Override
    public Iterator<StageType> iterator() {
        return new Iterator<StageType>() {
            @Override
            public boolean hasNext() {
                return currentPossibleCommandsIterator.hasNext();
            }

            @Override
            public StageType next() {
                StateType nextState;
                try {
                    nextState = (StateType) currentStage.getState().change(currentPossibleCommandsIterator.next());
                } catch (InvalidChangeException e) {
                    System.out.println("For some reason I explored an invalid state");
                    return null;
                }
                // TODO: fix this casting
                return (StageType) new Stage<>(nextState, currentStage);
            }
        };
    }
}
