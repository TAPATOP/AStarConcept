package concept.implementation;

import concept.heuristic.Heuristic;
import concept.solver.Solver;
import concept.stage.Stage;
import concept.state.State;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarDefault<T extends State, W extends Stage<T>> implements Solver<T> {
    protected T goal;
    protected Heuristic<T> heuristic;
    protected PriorityQueue<W> queue;

    /**
     * @param goal is copied by reference !!
     * @param heuristic is copied by reference !!
     */
    public AStarDefault(T goal, Heuristic<T> heuristic) {
        this.goal = goal;
        this.heuristic = heuristic;

        // should find a way to specify the queue and make sure it's valid
        this.queue = new PriorityQueue<>(Comparator.comparingInt(
                        stage -> stage.getG() + this.heuristic.heuristic(stage.getState(), goal)
                ));
    }

    @Override
    public List<? extends Stage<T>> solve(T currentState) {
        prepareForSolving(currentState);
        while(!shouldStop()) {
            step();
        }
        return getAnswer();
    }

    protected void prepareForSolving(T currentState) {
        queue.add((W) new Stage<T>(currentState));
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
        final Stage<T> currentStage = queue.poll();

        if (currentStage == null) return;

        final State currentState = currentStage.getState();
        for (State changedState : currentState) {
            //noinspection unchecked
            queue.add((W) new Stage<T>((T)changedState, currentStage));
            if (changedState.equals(goal)) {
                break;
            }
        }
    }

    protected List<Stage<T>> getAnswer() {
        if (queue.isEmpty()) {
            System.out.println("Something went wrong");
            return new LinkedList<>();
        }
        return queue.peek().getParentChain();
    }
}
