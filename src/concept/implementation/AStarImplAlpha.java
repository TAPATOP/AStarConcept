package concept.implementation;

import concept.heuristic.Heuristic;
import concept.stage.Stage;
import concept.state.State;

import java.util.HashSet;
import java.util.Set;

public class AStarImplAlpha<T extends State, W extends Stage<T>> extends AStarDefault<T, W> {
    protected Set<T> visitedStates;

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
        final W currentStage = queue.poll();

        if (currentStage == null) return;

        final State currentState = currentStage.getState();
        for (State changedState : currentState) {
            //noinspection unchecked
            T castChangedState = (T)changedState;
            if (visitedStates.contains(castChangedState)) {
                continue;
            }
            visitedStates.add(castChangedState);
            queue.add((W) new Stage<T>(castChangedState, currentStage));
            if (changedState.equals(goal)) {
                break;
            }
        }
    }
}
