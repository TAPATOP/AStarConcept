package usecases.swipeblockpuzzle;

import concept.strategies.expander.Expander;
import usecases.swipeblockpuzzle.exceptions.InvalidSwipeException;

import java.util.Iterator;

public class SwipeBlockExpander
        extends Expander<SwipeBlock, SwipeBlockStage, String> {

    SwipeBlockExpander(SwipeBlockStage currentStage, Iterator<String> possibleDirectionsIterator) {
        super(currentStage, possibleDirectionsIterator);
    }

    @Override
    public Iterator<SwipeBlockStage> iterator() {
        return new Iterator<SwipeBlockStage>() {
            @Override
            public boolean hasNext() {
                return possibleChangesIterator.hasNext();
            }

            @Override
            public SwipeBlockStage next() {
                String nextDirection = possibleChangesIterator.next();
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
