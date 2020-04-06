package usecases.swipeblockpuzzle;

public class TestCases {
    private static final int[][] goalBoard = new int[][] {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
    };
    public static final SwipeBlock eightSBSolved = new SwipeBlock(goalBoard);

    private static final int[][] oneMoveBoard = new int[][] {
            {1, 2, 3},
            {4, 5, 6},
            {7, 0, 8}
    };
    public static final SwipeBlock eightSBOneMove = new SwipeBlock(oneMoveBoard);

    private static final int[][] fiveMovesBoard = new int[][] {
            {1, 0, 2},
            {4, 6, 3},
            {7, 5, 8}
    };
    public static final SwipeBlock eightSBFiveMoves = new SwipeBlock(fiveMovesBoard);

    private static final int[][] board1 = new int[][] {
            {8, 6, 7},
            {2, 5, 4},
            {3, 0, 1}
    };
    public static final SwipeBlock eightSBMaximumMoves1 = new SwipeBlock(board1);

    private static final int[][] board2 = new int[][] {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
    };
    public static final SwipeBlock eightSBPuzzle2 = new SwipeBlock(board2);

    private static final int[][] board3 = new int[][] {
                {6, 4, 7},
                {8, 5, 0},
                {3, 2, 1}
    };
    public static final SwipeBlock eightSBMaximumMoves2 = new SwipeBlock(board3);
}
