package concept.stage;

import concept.state.State;

import com.sun.istack.internal.NotNull;

import java.util.Objects;
import java.util.Stack;

/**
 * Basically, State with metadata. That metadata can hold information
 * about how the inner State has been reached so, when the goalState
 * does get reached, Stage can provide information about the process.
 *
 * Can be used interchangeably with State in some contexts.
 *
 * Even though it is tempting to implement it as a child of State,
 * it is not one in a logical sense. State describes the possible forms of
 * the model that we are interested in, while Stage is that, but with
 * additional metadata. State can be used to simply "play" the game that
 * is modelled through it, while Stage holds unneeded metadata for that
 * use case.
 *
 *
 * @param <StateType>
 */
public class Stage<StateType extends State> {
    // Named according to the convention usually used when dealing with A*
    // It hold the number of steps that were performed in order
    // to reach the inner State
    final protected int g;

    // The inner State of the Stage
    final protected StateType state;

    // The immediate Stage the current one was reached from
    final protected Stage<StateType> previous;

    public Stage(@NotNull StateType state) {
        this.state = state;
        previous = null;
        g = 0;
    }

    public Stage(@NotNull StateType state, @NotNull Stage<StateType> previous) {
        this.state = state;
        this.g = previous.g + 1;
        this.previous = previous;
    }

    /**
     * A public method intended to find all the Stages that were
     * visited from a State to the inner State of this Stage. It simply
     * creates a Stack and passes it to the private method, which actually
     * does the job.
     * @return Stack of the visited Stages. The first popped is the starting State,
     * the last popped is the inner State of this Stage.
     */
    public Stack<Stage<StateType>> getParentChain() {
        Stack<Stage<StateType>> parentChain = new Stack<>();
        return this.getParentChain(parentChain);
    }

    /**
     * Given a Stack, populates it with the different Stages that were explored
     * while searching for the this Stage. Uses this order of pushing because
     * this is a tail recursion.
     *
     * @param parentChain stack
     * @return Stack of the visited Stages. The first popped is the starting State,
     *      * the last popped is the inner State of this Stage.
     */
    private Stack<Stage<StateType>> getParentChain(Stack<Stage<StateType>> parentChain) {
        parentChain.push(this);
        if (previous == null) {
            return parentChain;
        }
        return previous.getParentChain(parentChain);
    }

    /**
     * @return the number of steps that have been travelled to get to the
     * inner State
     */
    public int getG() {
        return g;
    }

    /**
     * @return the inner State
     */
    public StateType getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stage<?> stage = (Stage<?>) o;
        return g == stage.g &&
                state.equals(stage.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(g, state);
    }

    @Override
    public String toString() {
        return state.toString() + " " + g + "\n";
    }
}
