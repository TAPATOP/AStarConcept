package usecases.pancakes;

import java.util.Arrays;

public class PancakeImpl extends PancakeAbstract {
    public PancakeImpl(PancakeAbstract original) {
        super(original.pancakes);
    }

    public PancakeImpl(int[] pancakes) {
        super(pancakes);
    }

    private int bestFlippingId() {
        return 0;
    }

    @Override
    public PancakeAbstract flip(int index) {
        int[] newPancakes = Arrays.copyOf(pancakes, pancakes.length);
        if (index > size() - 1) {
            throw new RuntimeException("well fug lol");
        }
        for(int i = 0; i < index / 2 + 1; i++) {
            swap(newPancakes, i, index - i);
        }
//        System.out.println(this.toString());
        return new PancakeImpl(newPancakes);
    }

    private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    @Override
    public int size() {
        return pancakes.length;
    }

    @Override
    public int pancakeAt(int index) {
        return pancakes[index];
    }

    @Override
    public int findProperPlace(int pancake) {
        return pancake - 1;
    }

    @Override
    public String toString() {
        return Arrays.toString(pancakes);
    }

    @Override
    public void print() {
        System.out.println(toString());
    }
}
