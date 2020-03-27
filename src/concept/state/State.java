package concept.state;

import java.util.Iterator;

public interface State extends Iterable<State> {
    @Override
    Iterator<State> iterator();
}
