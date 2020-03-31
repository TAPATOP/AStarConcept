package usecases.swipeblockpuzzle;

/**
 * Container class
 */
public class Coordinates {
    // Member variables //
    private int x;
    private int y;

    // Constructor
    public Coordinates() {
        x = 0;
        y = 0;
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Methods //
    public static Coordinates sum(Coordinates leftCoords, Coordinates rightCoords) {
        return new Coordinates(
                leftCoords.getX() + rightCoords. getX(),
                leftCoords.getY() + rightCoords.getY()
        );
    }

    public static int manhattanDistance(Coordinates leftCoords, Coordinates rightCoords) {
        return
                Math.abs(leftCoords.getY() - rightCoords.getY()) +
                        Math.abs(leftCoords.getX() - rightCoords.getX()
                        );
    }

    public void print() {
        System.out.println(x + " " + y);
    }

    // Boring methods //
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}