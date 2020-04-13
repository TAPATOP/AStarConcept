package usecases.swipeblockpuzzle;

import concept.strategies.expander.Expander;
import usecases.swipeblockpuzzle.exceptions.InvalidSwipeException;

import java.util.Iterator;
import java.util.List;

public class SwipeBlockExpander
        extends Expander<SwipeBlock, SwipeBlockStage, String> {

    SwipeBlockExpander(List<String> possibleDirections) {
        super(possibleDirections);
    }

    @Override
    public Iterator<SwipeBlockStage> iterator() {
        return new Iterator<SwipeBlockStage>() {
            @Override
            public boolean hasNext() {
                return currentPossibleChangesIterator.hasNext();
            }

            @Override
            public SwipeBlockStage next() {
                String nextDirection = currentPossibleChangesIterator.next();
                SwipeBlock sb;
                try {
                    sb = currentStage.getState().move(nextDirection);
                } catch (InvalidSwipeException e) {
                    return null;
                }
                return new SwipeBlockStage(sb, currentStage, nextDirection);
            }
        };
    }
}
