package concept.implementation;

import concept.heuristic.Heuristic;
import concept.stage.Stage;
import concept.state.State;

import java.util.HashSet;
import java.util.Set;

public class AStarImplAlpha<
        StateType extends State<ChangeArgType>,
        StageType extends Stage<StateType>,
        ChangeArgType>
        extends AStarDefault<StateType, StageType, ChangeArgType> {
    protected Set<StateType> visitedStates;

   public AStarImplAlpha(StateType goal, Heuristic<StateType> heuristic) {
       super(goal, heuristic);
       visitedStates = new HashSet<>();
   }

    @Override
    protected void prepareForSolving(StateType currentState) {
        super.prepareForSolving(currentState);
        visitedStates.add(currentState);
    }

    @Override
    protected void step() {
        final StageType currentStage = queue.poll();

        if (currentStage == null) return;

        final State<ChangeArgType> currentState = currentStage.getState();
        for (State<ChangeArgType> changedState : currentState) {
            //noinspection unchecked
            StateType castChangedState = (StateType)changedState;
            if (visitedStates.contains(castChangedState)) {
                continue;
            }
            visitedStates.add(castChangedState);
            queue.add((StageType) new Stage<>(castChangedState, currentStage));
            if (changedState.equals(goal)) {
                break;
            }
        }
    }
}
