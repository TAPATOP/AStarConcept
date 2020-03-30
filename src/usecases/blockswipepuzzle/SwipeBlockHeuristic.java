package usecases.blockswipepuzzle;

import concept.heuristic.Heuristic;

public class SwipeBlockHeuristic implements Heuristic<SwipeBlock> {
    @Override
    public int heuristic(SwipeBlock current, SwipeBlock goal) {
        int sum = 0;
        int height = current.getHeight();
        int width = current.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int number = current.numberAt(i, j);
                if (number == 0) {
                    number = height * width;
                }

                Coordinates numberCoords = new Coordinates(j, i);

                // TODO: make the below work with goal
                Coordinates supposedPlace = new Coordinates((number - 1) % width, (number - 1) / width);

                sum += Coordinates.manhattanDistance(supposedPlace, numberCoords);
            }
        }
        return sum;
    }
}
