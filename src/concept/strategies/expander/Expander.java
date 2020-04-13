package concept.strategies.expander;

import concept.exceptions.InvalidChangeException;
import concept.stage.Stage;
import concept.state.State;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Expander<
        StateType extends State<ChangeArgType>,
        StageType extends Stage<StateType>,
        ChangeArgType>
        implements Iterable<StageType> {
    protected StageType currentStage;
    protected Iterator<ChangeArgType> currentPossibleChangesIterator;

    protected List<ChangeArgType> allPossibleChanges;

    public Expander(List<ChangeArgType> allPossibleChanges) {
        this.allPossibleChanges = allPossibleChanges;
    }

    public void setStage(StageType stage) {
        currentStage = stage;
        List<ChangeArgType> currentPossibleChanges = new LinkedList<>();

        for(ChangeArgType command : allPossibleChanges) {
            if (currentStage.getState().canChange(command)) {
                currentPossibleChanges.add(command);
            }
        }

        this.currentPossibleChangesIterator = currentPossibleChanges.iterator();
    }

    @Override
    public Iterator<StageType> iterator() {
        return new Iterator<StageType>() {
            @Override
            public boolean hasNext() {
                return currentPossibleChangesIterator.hasNext();
            }

            @Override
            public StageType next() {
                StateType nextState;
                try {
                    nextState = (StateType) currentStage.getState().change(currentPossibleChangesIterator.next());
                } catch (InvalidChangeException e) {
                    System.out.println("For some reason I explored an invalid state");
                    return null;
                }
                return (StageType) new Stage<>(nextState, currentStage);
            }
        };
    }
}
