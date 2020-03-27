package usecases.pancakes;

import com.sun.istack.internal.NotNull;
import concept.state.State;

import java.util.Arrays;
import java.util.Iterator;

abstract public class PancakeAbstract implements State {
    protected int[] pancakes;

    public PancakeAbstract(@NotNull int[] pancakes) {
        this.pancakes = Arrays.copyOf(pancakes, pancakes.length);
    }

    // TODO: Use inner class instead of local
    @Override
    public Iterator<State> iterator() {
        return new Iterator<State>() {
            private int currentIndex = 1;

            @Override
            public boolean hasNext() {
                return currentIndex < size();
            }

            @Override
            public PancakeAbstract next() {
                return flip(currentIndex++);
            }
        };
    }

    public abstract PancakeAbstract flip(int index);
    public abstract int size();
    public abstract int pancakeAt(int index);
    public abstract int findProperPlace(int pancake); // move elsewhere

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PancakeAbstract that = (PancakeAbstract) o;
        return Arrays.equals(pancakes, that.pancakes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(pancakes);
    }
}
