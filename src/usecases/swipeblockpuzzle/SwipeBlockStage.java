package usecases.swipeblockpuzzle;

import concept.stage.Stage;

public class SwipeBlockStage extends Stage<SwipeBlock> {
    private String lastDirection;

    public SwipeBlockStage(SwipeBlock state, String lastDirection) {
        super(state);
        this.lastDirection = lastDirection;
    }

    public SwipeBlockStage(SwipeBlock state, Stage<SwipeBlock> previous, String lastDirection) {
        super(state, previous);
        this.lastDirection = lastDirection;
    }

    public String getLastDirection() {
        return lastDirection;
    }
}
