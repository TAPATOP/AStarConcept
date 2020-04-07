package usecases.swipeblockpuzzle;

import concept.state.State;
import usecases.swipeblockpuzzle.exceptions.InvalidSwipeException;
import usecases.swipeblockpuzzle.exceptions.NumberNotFoundRuntimeException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SwipeBlock implements State {
    static private Set<String> possibleDirections;

    static {
        possibleDirections = new HashSet<>();
        possibleDirections.add("up");
        possibleDirections.add("down");
        possibleDirections.add("left");
        possibleDirections.add("right");
    }

    private int[][] board;
    private int height;
    private int width;

    private Coordinates emptySquareCoordinates;

    public SwipeBlock(int[][] board) {
        this.height = board.length;
        this.width = board[0].length;
        this.board = new int[height][width];

        copyBoard(board);
    }

    public SwipeBlock(SwipeBlock otherState) {
        this.height = otherState.height;
        this.width = otherState.width;
        this.board = new int[height][width];
        copyBoard(otherState.board);

        this.emptySquareCoordinates =
                new Coordinates(
                        otherState.emptySquareCoordinates.getY(),
                        otherState.emptySquareCoordinates.getX()
                );
    }

    /**
     * Returns a new swipeBlock
     */
    private SwipeBlock swipeBlock(Coordinates swipedCoords) {
        SwipeBlock newState = new SwipeBlock(this.board);
        newState.board[emptySquareCoordinates.getY()][emptySquareCoordinates.getX()] =
                newState.board[swipedCoords.getY()][swipedCoords.getX()];
        newState.board[swipedCoords.getY()][swipedCoords.getX()] = 0;
        newState.emptySquareCoordinates = new Coordinates(swipedCoords.getX(), swipedCoords.getY());
        return newState;
    }

    // generates a solved SwipeBlock
    public static SwipeBlock solvedBlock(int height, int width) {
        int[][] board = new int[height][width];

        int counter = 1;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++){
                board[i][j] = counter++;
            }
        }
        board[height - 1][width - 1] = 0;
        return new SwipeBlock(board);
    }

    private void copyBoard(int[][] board) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                this.board[i][j] = board[i][j];
                if(board[i][j] == 0) {
                    emptySquareCoordinates = new Coordinates(j, i);
                }
            }
        }
    }

    public SwipeBlock move(String direction) throws InvalidSwipeException {
        Coordinates relativeCoords = calculateRelativeCoords(direction);
        // TODO: Probably throw an exception here
        if(relativeCoords == null) {
            return null;
        }

        Coordinates swipedCoords = newCoordinates(relativeCoords);

        if(!coordsAreValid(swipedCoords)) {
            throw new InvalidSwipeException();
        }
        return swipeBlock(swipedCoords);
    }

    private Coordinates newCoordinates(Coordinates change) {
        return Coordinates.sum(emptySquareCoordinates, change);
    }

    private Coordinates calculateRelativeCoords(String direction) {
        switch(direction) {
            case "left":    return new Coordinates(1, 0);
            case "right":   return new Coordinates(-1, 0);
            case "up":      return new Coordinates(0, 1);
            case "down":    return new Coordinates(0, -1);
            default:        return null;
        }
    }

    private boolean coordsAreValid(Coordinates coords) {
        int x = coords.getX();
        int y = coords.getY();
        return !(y < 0 || y >= height || x < 0 || x >= width);
    }

    public void print() {
        for(int[] line : board) {
            for(int number: line) {
                System.out.format("%3d", number);
            }
            System.out.println();
        }
    }

    // System functions
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwipeBlock gameState = (SwipeBlock) o;

        return Arrays.deepEquals(board, gameState.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    @Override
    public Iterator<State> iterator() {

        return new Iterator<State>() {
            Set<Coordinates> validCoordinates = new HashSet<>();
            {
                for(String direction : possibleDirections) {
                    Coordinates newCoord = newCoordinates(calculateRelativeCoords(direction));
                    if (coordsAreValid(newCoord)) {
                        validCoordinates.add(newCoord);
                    }
                }
            }
            Iterator<Coordinates> validCoordIterator = validCoordinates.iterator();

            @Override
            public boolean hasNext() {
                return validCoordIterator.hasNext();
            }

            @Override
            public State next() {
                return swipeBlock(validCoordIterator.next());
            }
        };
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int numberAt(int i, int j) {
        return board[i][j];
    }

    public Coordinates locationOf(int number) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if (board[i][j] == number) {
                    return new Coordinates(j, i);
                }
            }
        }
        throw new NumberNotFoundRuntimeException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int[] row : board) {
            sb.append(Arrays.toString(row));
            sb.append("\n");
        }
        return sb.toString();
    }
}
