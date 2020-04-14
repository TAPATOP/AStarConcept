package concept.implementation;

import concept.heuristic.Heuristic;
import concept.stage.Stage;
import concept.state.State;
import concept.strategies.expander.ExpanderStrategy;

import java.util.HashSet;
import java.util.Set;

/**
 * An implementation of the AStarDefault that uses a Set to check
 * whether a given State has already been expanded, so we can save
 * potentially large amount of calculations
 * @param <StateType>
 * @param <StageType>
 * @param <ChangeArgType>
 */
public class AStarImplAlpha<
        StateType extends State<ChangeArgType>,
        StageType extends Stage<StateType>,
        ChangeArgType>
        extends AStarDefault<StateType, StageType, ChangeArgType> {

    // Set of visited States
    final protected Set<StateType> visitedStates;

    public AStarImplAlpha(
           StateType goal,
           Heuristic<StateType> heuristic,
           ExpanderStrategy<StateType, StageType, ChangeArgType> expander
           ) {
       super(goal, heuristic, expander);
       visitedStates = new HashSet<>();
    }

    /**
     * Other than calling itself from super, also initializes the Set
     *
     * @param currentState the starting state from which we'll try to find a way to the goal
     */
    @Override
    protected void prepareForSolving(StateType currentState) {
        super.prepareForSolving(currentState);
        visitedStates.add(currentState);
    }

    /**
     * TODO
     *
     * Adds an additional line of code, which is pushing the State
     * of the current Stage into the Set. Can probably be abstracted further
     */
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
