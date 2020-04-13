package concept.state;

import java.util.Iterator;

public interface State<ChangeArgType> extends Iterable<State<ChangeArgType>> {
    @Override
    Iterator<State<ChangeArgType>> iterator();

//    State<ChangeArgType> change(ChangeArgType command);

//    boolean canChange(ChangeArgType command);

    @Override
    String toString();
}
