package concept;

import concept.astar.AStarImplAlpha;
import concept.heuristic.Heuristic;
import concept.state.State;
import usecases.pancakes.PancakeAbstract;
import usecases.pancakes.PancakeHeuristic;
import usecases.pancakes.PancakeImpl;
import usecases.swipeblockpuzzle.SwipeBlock;
import usecases.swipeblockpuzzle.SwipeBlockHeuristic;

import java.util.List;

/** TODO
* - ensure the Stages are of the same type per A*
*/
public class MainClass {
    public static void main(String[] args) {
        int[] data = {2, 3, 5, 6, 4, 1};
        int[] goalData = {1, 2, 3, 4, 5, 6};
        State pancakes = new PancakeImpl(data);
        State goalPancakes = new PancakeImpl(goalData);
        Heuristic<PancakeAbstract> heuristic = new PancakeHeuristic();

        int[][] board = {
                {6, 4, 7},
                {8, 5, 0},
                {3, 2, 1}
        };

        SwipeBlock sb = new SwipeBlock(board);
        Heuristic<SwipeBlock> sbh = new SwipeBlockHeuristic();

        AStarImplAlpha<SwipeBlock> aStarDefault = new AStarImplAlpha<>(SwipeBlock.solvedBlock(3, 3), sbh);
        List<SwipeBlock> list = aStarDefault.solve(sb);
        list.forEach(System.out::println);
    }
}
