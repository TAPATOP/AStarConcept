package concept.implementation;

import concept.heuristic.Heuristic;
import concept.stage.Stage;
import concept.state.State;
import concept.strategies.expander.Expander;

import java.util.HashSet;
import java.util.Set;

public class AStarImplAlpha<
        StateType extends State<ChangeArgType>,
        StageType extends Stage<StateType>,
        ChangeArgType>
        extends AStarDefault<StateType, StageType, ChangeArgType> {
    protected Set<StateType> visitedStates;

   public AStarImplAlpha(
           StateType goal,
           Heuristic<StateType> heuristic,
           Expander<StateType, StageType, ChangeArgType> expander
           ) {
       super(goal, heuristic, expander);
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

        expander.setStage(currentStage);
        for (StageType changedStage : expander) {
            StateType changedState = changedStage.getState();
            if (visitedStates.contains(changedState)) {
                continue;
            }
            visitedStates.add(changedState);
            queue.add(changedStage);
            if (changedState.equals(goal)) {
                break;
            }
        }
    }
}
