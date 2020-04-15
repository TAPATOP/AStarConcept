package usecases.swipeblockpuzzle;

import concept.state.State;
import jdk.nashorn.internal.ir.annotations.Immutable;
import usecases.swipeblockpuzzle.exceptions.InvalidSwipeException;
import usecases.swipeblockpuzzle.exceptions.NumberNotFoundRuntimeException;

import java.util.*;

public class SwipeBlock implements State<String> {
    static final private List<String> possibleDirections;

    static {
        possibleDirections = new ArrayList<>();
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

    /**
     * Returns a new swipeBlock that is created by swiping the current one
     * using the Coordinates input
     */
    private SwipeBlock swipeBlock(Coordinates swipedCoords) {
        SwipeBlock newState = new SwipeBlock(this.board);
        newState.board[emptySquareCoordinates.getY()][emptySquareCoordinates.getX()] =
                newState.board[swipedCoords.getY()][swipedCoords.getX()];
        newState.board[swipedCoords.getY()][swipedCoords.getX()] = 0;
        newState.emptySquareCoordinates = new Coordinates(swipedCoords.getX(), swipedCoords.getY());
        return newState;
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
        if (!canChange(direction)) {
            throw new InvalidSwipeException();
        }
        Coordinates swipedCoords = newCoordinates(calculateRelativeCoords(direction));

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
        return (y >= 0 && y < height) && (x >= 0 && x < width);
    }

    @Override
    public State<String> change(String direction) throws InvalidSwipeException {
        return move(direction);
    }

    @Override
    public boolean canChange(String direction) {
        Coordinates relativeCoords = calculateRelativeCoords(direction);

        if(relativeCoords == null) {
            return false;
        }

        Coordinates swipedCoords = newCoordinates(relativeCoords);

        return coordsAreValid(swipedCoords);
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int[] row : board) {
            sb.append(Arrays.toString(row));
            sb.append("\n");
        }
        return sb.toString();
    }

    public static List<String> getPossibleDirections() {
        return new ArrayList<>(possibleDirections);
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
}
