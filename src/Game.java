import javafx.scene.paint.Color;

/**
 * Wraps the logic of the game.
 */
public class Game {
    /**
     * The width of the world board.
     */
    public static final int WORLD_WIDTH = 80;

    /**
     * The height of the world board.
     */
    public static final int WORLD_HEIGHT = 80;

    /**
     * The world board.
     */
    private World world;

    /**
     * Constructs a game object.
     */
    public Game() {
        world = new World(WORLD_WIDTH, WORLD_HEIGHT);
    }

    /**
     * Moves all life forms in the world.
     */
    public void takeTurn() {
        world.moveLifeForms();
        world.breedLifeForms();
    }

    /**
     * Returns the color of a life form in a given row and col.
     * @param row row
     * @param col column
     * @return color of a life form
     */
    public Color getColor(final int row, final int col) {
        LifeForm lf = world.getCell(row, col).getLifeform();
        if (lf != null)
            return Color.valueOf(lf.getColor());
        return Color.LIGHTGRAY;
    }
}
