package concept;

import concept.astar.AStarDefault;
import concept.heuristic.Heuristic;
import concept.state.State;
import usecases.pancakes.PancakeAbstract;
import usecases.pancakes.PancakeHeuristic;
import usecases.pancakes.PancakeImpl;

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

        AStarDefault aStarDefault = new AStarDefault(goalPancakes, heuristic);
        List<State> solved = aStarDefault.solve(pancakes);

        for(State state : solved) {
            System.out.println(state);
        }
    }
}
