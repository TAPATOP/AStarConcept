package concept.stage;

import concept.state.State;

import com.sun.istack.internal.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Stage {
    private final int g;
    private final State state;
    private final Stage previous;

    public Stage(@NotNull State state) {
        this.state = state;
        previous = null;
        g = 0;
    }

    public Stage(@NotNull State state, @NotNull Stage previous) {
        this.state = state;
        this.g = previous.g + 1;
        this.previous = previous;
    }

    public List<State> getParentChain() {
        List<State> list = new LinkedList<>();
        return this.getParentChain(list);
    }

    private List<State> getParentChain(List<State> list) {
        list.add(state);
        if (previous == null) {
            return list;
        }
        return previous.getParentChain(list);
    }

    // TODO check is this can be fixed or if it is even needed
    public boolean equalState(State other) {
        return state.equals(other);
    }

    public int getG() {
        return g;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stage stage = (Stage) o;
        return g == stage.g &&
                state.equals(stage.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(g, state);
    }
}
