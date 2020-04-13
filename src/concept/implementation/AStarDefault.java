package concept.implementation;

import concept.heuristic.Heuristic;
import concept.solver.Solver;
import concept.stage.Stage;
import concept.state.State;
import concept.strategies.expander.Expander;

import java.util.*;

public class AStarDefault<
        StateType extends State<ChangeArgType>,
        StageType extends Stage<StateType>,
        ChangeArgType>
        implements Solver<StateType> {

    protected StateType goal;
    protected Heuristic<StateType> heuristic;
    protected PriorityQueue<StageType> queue;
    protected Expander<StateType, StageType, ChangeArgType> expander;

    /**
     * @param goal is copied by reference !!
     * @param heuristic is copied by reference !!
     */
    public AStarDefault(
            StateType goal,
            Heuristic<StateType> heuristic,
            Expander<StateType, StageType, ChangeArgType> expander
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

    @Override
    public Stack<? extends Stage<StateType>> solve(StateType currentState) {
        prepareForSolving(currentState);
        while(!shouldStop()) {
            step();
        }
        return getAnswer();
    }

    protected void prepareForSolving(StateType currentState) {
        // TODO: Look into this casting
        queue.add((StageType) new Stage<>(currentState));
    }

    /**
     * Stops when queue is empty
     * OR
     * Best element equals the goal
     * @return
     */
    protected boolean shouldStop() {
        return  queue.isEmpty() ||
                queue.peek().getState().equals(goal);
    }

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

    protected Stack<Stage<StateType>> getAnswer() {
        if (queue.isEmpty()) {
            System.out.println("Something went wrong");
            return new Stack<>();
        }
        return queue.peek().getParentChain();
    }
}
