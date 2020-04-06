package concept.implementation;

import concept.heuristic.Heuristic;
import concept.stage.Stage;
import concept.state.State;

import java.util.HashSet;
import java.util.Set;

public class AStarImplAlpha<T extends State> extends AStarDefault<T> {
    Set<T> visitedStates;

   public AStarImplAlpha(T goal, Heuristic<T> heuristic) {
       super(goal, heuristic);
       visitedStates = new HashSet<>();
   }

    @Override
    protected void prepareForSolving(T currentState) {
        super.prepareForSolving(currentState);
        visitedStates.add(currentState);
    }

    @Override
    protected void step() {
        final Stage<T> currentStage = queue.poll();
        if (currentStage == null) return;
        final State currentState;
        currentState = currentStage.getState();
        for (State changedState : currentState) {
            //noinspection unchecked
            T castChangedState = (T)changedState;
            if (visitedStates.contains(castChangedState)) {
                continue;
            }
            visitedStates.add(castChangedState);
            queue.add(new Stage<>(castChangedState, currentStage));
            if (changedState.equals(goal)) {
                break;
            }
        }
    }
}
