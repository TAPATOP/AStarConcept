package concept.implementation;

import com.sun.istack.internal.NotNull;
import concept.heuristic.Heuristic;
import concept.solver.Solver;
import concept.stage.Stage;
import concept.state.State;
import concept.strategies.expander.ExpanderStrategy;

import java.util.*;

/**
 * The default implementation of A* without any fancy optimisations.
 *
 * The important functionality is separated in different methods, so it can be
 * partially overriden by child classes:
 * - solve()
 * - prepareForSolving()
 * - shouldStop()
 * - step()
 * - getAnswer()
 *
 * Maybe it would be appropriate to change each of these with a strategy.
 *
 * @param <StateType> The State type that is going to be solved
 * @param <StageType> The type of the Stage that is going to hold the
 *                   different States that were explored during the
 *                   creation of the solution
 * @param <ChangeArgType> The type of the argument that you would give to
 *                       a State in order to change it. Important for the
 *                       ExpanderStrategy and State interface
 */
public class AStarDefault<
        StateType extends State<ChangeArgType>,
        StageType extends Stage<StateType>,
        ChangeArgType>
        implements Solver<StateType> {

    // The State that we must reach from the current State
    final protected StateType goal;

    // The function used to calculate how close we are to finding the goal
    final protected Heuristic<StateType> heuristic;

    // Queue used to order Stages that should be explored based on
    // the sum of the steps taken so far and how promising the State is(calculated
    // by the Heuristic)
    final protected PriorityQueue<StageType> queue;

    // A strategy whose goal is to reach every immediately reachable State
    // from any given State
    final protected ExpanderStrategy<StateType, StageType, ChangeArgType> expander;

    /**
     * @param goal Whatever we consider "solved"
     * @param heuristic Heuristic function
     * @param expander Expander Strategy
     */
    public AStarDefault(
            @NotNull StateType goal,
            @NotNull Heuristic<StateType> heuristic,
            @NotNull ExpanderStrategy<StateType, StageType, ChangeArgType> expander
    ) {
        this.goal = goal;
        this.heuristic = heuristic;
        this.expander = expander;

        // TODO: should find a way to specify the queue and make sure it's valid.
        // Probably through a Strategy
        this.queue = new PriorityQueue<>(Comparator.comparingInt(
                        stage -> stage.getG() + this.heuristic.heuristic(stage.getState(), goal)
                ));
    }

    /**
     * A function that structures the overall solution logic.
     * Using this architecture, you can replace a part of it by overriding
     * and still have its remaining parts working as expected
     *
     * @param currentState The current State which we need to reach the goal
     *                     from
     * @return a Stack of the different Stages our solution went through while
     * looking for the answer
     */
    @Override
    public Stack<? extends Stage<StateType>> solve(StateType currentState) {
        prepareForSolving(currentState);
        while(!shouldStop()) {
            step();
        }
        return getAnswer();
    }

    /**
     * Prepares the assisting Data Structures. The default implementation
     * simply puts the currentState into the PriorityQueue
     *
     * @param currentState the starting state from which we'll try to find a way to the goal
     */
    protected void prepareForSolving(StateType currentState) {
        // TODO: Look into this casting
        queue.add((StageType) new Stage<>(currentState));
    }

    /**
     * Stops when queue is empty
     * OR
     * Best element equals the goal
     * @return if the search for solution should stop
     */
    protected boolean shouldStop() {
        return  queue.isEmpty() ||
                queue.peek().getState().equals(goal);
    }

    /**
     * Iterates over all immediate States following the currently
     * best State( as decided by the PriorityQueue), recording metadata
     * in a new Stage of type StageType and pushes it into the queue.
     *
     * Should be noted that Stage is just State with some metadata,
     * so these terms are partially interchangeable in this context
     */
    protected void step() {
        final StageType currentStage = queue.poll();

        if (currentStage == null) return;

        expander.setStage(currentStage);
        for (StageType changedStage : expander) {
            queue.add(changedStage);
            if (changedStage.getState().equals(goal)) {
                break;
            }
        }
    }

    /**
     * Asks the "best" Stage to return its parent chain, e.g.
     * the Stages it has went through before finding the solution
     *
     * IMPORTANT: The parent chain currently contains both the goal
     * and the starting State, so it is going to contain n + 1 elements,
     * where n is the needed steps to solve the problem
     *
     * @return A Stack, because currently Stages push themselves and
     * push the previous Stages afterwards
     */
    protected Stack<Stage<StateType>> getAnswer() {
        if (queue.isEmpty()) {
            System.out.println("Something went wrong");
            return new Stack<>();
        }
        return queue.peek().getParentChain();
    }
}
