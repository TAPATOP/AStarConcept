package concept;

import concept.astar.AStarDefault;
import concept.heuristic.Heuristic;
import concept.state.State;
import usecases.blockswipepuzzle.SwipeBlock;
import usecases.blockswipepuzzle.SwipeBlockHeuristic;
import usecases.pancakes.PancakeAbstract;
import usecases.pancakes.PancakeHeuristic;
import usecases.pancakes.PancakeImpl;

import java.util.List;

/** TODO
* - ensure the Stages are of the same type per A*
*/
public class MainClass {
    public static void main(String[] args) {
//        int[] data = {2, 3, 5, 6, 4, 1};
//        int[] goalData = {1, 2, 3, 4, 5, 6};
//        State pancakes = new PancakeImpl(data);
//        State goalPancakes = new PancakeImpl(goalData);
//        Heuristic<PancakeAbstract> heuristic = new PancakeHeuristic();
//
//        AStarDefault aStarDefault = new AStarDefault(goalPancakes, heuristic);
//        List<State> solved = aStarDefault.solve(pancakes);
//
//        for(State state : solved) {
//            System.out.println(state);
//        }
        int numberOfBlocks3 = 8;
//        int[][] board = {
//                {6, 4, 7},
//                {8, 5, 0},
//                {3, 2, 1}
//        };
        int[][] board = {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        SwipeBlock sb = new SwipeBlock(3, 3, board);
        Heuristic<SwipeBlock> sbh = new SwipeBlockHeuristic();

        AStarDefault<SwipeBlock> aStarDefault = new AStarDefault<>(SwipeBlock.solvedBlock(3, 3), sbh);
        List<SwipeBlock> list = aStarDefault.solve(sb);
        list.forEach(System.out::println);
    }
}
