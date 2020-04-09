package concept.stage;

import concept.state.State;

import com.sun.istack.internal.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

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

    public Stack<Stage<T>> getParentChain() {
        Stack<Stage<T>> parentChain = new Stack<>();
        return this.getParentChain(parentChain);
    }

    private Stack<Stage<T>> getParentChain(Stack<Stage<T>> parentChain) {
        parentChain.push(this);
        if (previous == null) {
            return parentChain;
        }
        return previous.getParentChain(parentChain);
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
