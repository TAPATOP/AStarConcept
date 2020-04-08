package concept;

import usecases.pancakes.PancakeAbstract;
import usecases.pancakes.PancakeImpl;
import usecases.pancakes.PancakeSolver;
import usecases.swipeblockpuzzle.SwipeBlock;
import usecases.swipeblockpuzzle.SwipeBlockPuzzleSolver;
import usecases.swipeblockpuzzle.SwipeBlockStage;

import java.util.List;

/** TODO
 * - final where needed
 * - comments
*/
public class MainClass {
    public static void main(String[] args) {
        int[] data = {2, 3, 5, 6, 4, 1};
        int[] goalData = {1, 2, 3, 4, 5, 6};
        PancakeAbstract pancakes = new PancakeImpl(data);
        PancakeAbstract goalPancakes = new PancakeImpl(goalData);
        PancakeSolver pancakeSolver = new PancakeSolver(goalPancakes);
        pancakeSolver.solve(pancakes).forEach(System.out::println);

        int[][] board = {
                {6, 4, 7},
                {8, 5, 0},
                {3, 2, 1}
        };

        SwipeBlock sb = new SwipeBlock(board);
        SwipeBlockPuzzleSolver SWSolver = new SwipeBlockPuzzleSolver(SwipeBlock.solvedBlock(3, 3));
        List<SwipeBlockStage> list = SWSolver.solve(sb);
        list.forEach(System.out::println);
    }
}
