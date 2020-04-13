package concept.strategies.expander;

import concept.stage.Stage;
import concept.state.State;

import java.util.Iterator;

public abstract class Expander<T extends State<ChangeArgType>, W extends Stage<T>, ChangeArgType>
        implements Iterable<W> {
    protected W currentStage;
    protected Iterator<ChangeArgType> possibleChangesIterator;

    public Expander(W currentStage, Iterator<ChangeArgType> iterator) {
        this.currentStage = currentStage;
        this.possibleChangesIterator = iterator;
    }
}
