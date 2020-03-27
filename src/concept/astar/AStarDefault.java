package concept.astar;

import concept.heuristic.Heuristic;
import concept.stage.Stage;
import concept.state.State;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarDefault {
    protected State goal;
    protected Heuristic heuristic;
    protected PriorityQueue<Stage> queue;

    /**
     * @param goal is copied by reference !!
     * @param heuristic is copied by reference !!
     */
    public AStarDefault(State goal, Heuristic heuristic) {
        this.goal = goal;
        this.heuristic = heuristic;

        // should find a way to specify the queue and make sure it's valid
        this.queue = new PriorityQueue<>(Comparator.comparingInt(
                        stage -> stage.getG() + this.heuristic.heuristic(stage.getState(), goal)
                ));
    }

    public List<State> solve(State currentState) {
        prepareForSolving(currentState);
        while(!shouldStop()) {
            step();
        }
        return getAnswer();
    }

    protected void prepareForSolving(State currentState) {
        queue.add(new Stage(currentState));
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
        final Stage currentStage = queue.poll();
        final State currentState = currentStage.getState();
        for (State changedState : currentState) {
            queue.add(new Stage(changedState, currentStage));
            if (changedState.equals(goal)) {
                break;
            }
        }
    }

    protected List<State> getAnswer() {
        if (queue.isEmpty()) {
            System.out.println("Something went wrong");
            return new LinkedList<>();
        }
        return queue.peek().getParentChain();
    }
}
