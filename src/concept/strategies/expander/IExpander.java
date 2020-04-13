package concept.strategies.expander;

import concept.stage.Stage;
import concept.state.State;

import java.util.Iterator;

/**
 * TODO: Consider making this a Singleton
 * @param <StateType>
 * @param <StageType>
 * @param <ChangeArgType>
 */
public interface IExpander<
        StateType extends State,
        StageType extends Stage<StateType>,
        ChangeArgType>

        extends Iterable<StageType>{

    @Override
    Iterator<StageType> iterator();
}
