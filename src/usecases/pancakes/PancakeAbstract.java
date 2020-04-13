package usecases.pancakes;

import com.sun.istack.internal.NotNull;
import concept.state.State;

import java.util.Arrays;
import java.util.Iterator;

abstract public class PancakeAbstract implements State<Integer> {
    protected int[] pancakes;

    public PancakeAbstract(@NotNull int[] pancakes) {
        this.pancakes = Arrays.copyOf(pancakes, pancakes.length);
    }

    public abstract PancakeAbstract flip(int index);
    public abstract int pancakeAt(int index);
    public abstract int findProperPlace(int pancake); // move elsewhere

    @Override
    public State<Integer> change(Integer index) {
        return flip(index);
    }

    @Override
    public boolean canChange(Integer index) {
        return index >= 0 && index < size();
    }

    @Override
    public Iterator<State<Integer>> iterator() {
        return new Iterator<State<Integer>>() {
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

    public int size() {
        return pancakes.length;
    }

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
