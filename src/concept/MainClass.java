package concept;

import concept.stage.Stage;
import usecases.pancakes.PancakeAbstract;
import usecases.pancakes.PancakeImpl;
import usecases.pancakes.PancakeSolver;
import usecases.swipeblockpuzzle.SwipeBlock;
import usecases.swipeblockpuzzle.SwipeBlockPuzzleSolver;
import usecases.swipeblockpuzzle.SwipeBlockStage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

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
        Stack<Stage<PancakeAbstract>> result = pancakeSolver.solve(pancakes);
        result.stream().sorted(Comparator.comparingInt(Stage::getG)).forEach(System.out::println);

        int[][] board = {
                {6, 4, 7},
                {8, 5, 0},
                {3, 2, 1}
        };

        SwipeBlock sb = new SwipeBlock(board);
        SwipeBlockPuzzleSolver SWSolver = new SwipeBlockPuzzleSolver(SwipeBlock.solvedBlock(3, 3));
        Stack<SwipeBlockStage> parentChain = SWSolver.solve(sb);

//        length = parentChain.size();
//        for(int i = 0; i < length; i++) {
//            System.out.println(parentChain.pop());
//        }
    }
}
