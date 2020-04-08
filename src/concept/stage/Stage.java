package concept.stage;

import concept.state.State;

import com.sun.istack.internal.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Stage<T extends State> {
    protected final int g;
    protected final T state;
    protected final Stage<T> previous;

    public Stage(@NotNull T state) {
        this.state = state;
        previous = null;
        g = 0;
    }

    public Stage(@NotNull T state, @NotNull Stage<T> previous) {
        this.state = state;
        this.g = previous.g + 1;
        this.previous = previous;
    }

    public List<Stage<T>> getParentChain() {
        List<Stage<T>> list = new LinkedList<>();
        return this.getParentChain(list);
    }

    private List<Stage<T>> getParentChain(List<Stage<T>> list) {
        list.add(this);
        if (previous == null) {
            return list;
        }
        return previous.getParentChain(list);
    }

    // TODO check is this can be fixed or if it is even needed
    public boolean equalState(T other) {
        return state.equals(other);
    }

    public int getG() {
        return g;
    }

    public T getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stage<?> stage = (Stage<?>) o;
        return g == stage.g &&
                state.equals(stage.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(g, state);
    }

    @Override
    public String toString() {
        return state.toString() + " " + g + "\n";
    }
}
