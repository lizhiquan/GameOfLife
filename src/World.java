import java.util.ArrayList;

/**
 * A board that represents the world.
 * Life forms can move and reproduce in this world.
 */
public class World {
    private int width;
    private int height;
    private Cell[][] cells;

    /**
     * A class represents a cell in the world.
     */
    public class Cell {
        /**
         * Row position of this cell.
         */
        private int row;

        /**
         * Column position of this cell.
         */
        private int col;

        /**
         * A life form that this cell holds.
         */
        private LifeForm lifeform;

        /**
         * Indicates whether this cell is processed.
         */
        private boolean processed;

        /**
         * Constructs a Cell object.
         * @param row row position
         * @param col column position
         * @param lifeform a life form in this cell
         */
        public Cell(final int row, final int col) {
            this.row = row;
            this.col = col;
        }

        /**
         * Returns current life form.
         * @return life form
         */
        public LifeForm getLifeform() {
            return lifeform;
        }

        /**
         * Set a new life form.
         * @param lifeform new life form to set
         */
        public void setLifeform(LifeForm lifeform) {
            this.lifeform = lifeform;
            if (lifeform != null)
                lifeform.setCell(this);
        }

        /**
         * Checks whether this cell is processed for moving.
         * @return a boolean value indicating this cell is processed
         */
        public boolean isProcessed() {
            return processed;
        }

        /**
         * Updates the processed state of this cell.
         * @param processed new state
         */
        public void setProcessed(boolean processed) {
            this.processed = processed;
        }

        /**
         * Gets neighbor cells of this cell.
         * @return neighbor cells
         */
        ArrayList<Cell> getNeighbours() {
            ArrayList<Cell> neighbours = new ArrayList<>();
            int[] directions = {-1, 0, 1};

            for (int x : directions) {
                int r = row + x;

                // out of bound
                if (r < 0 || r == height)
                    continue;

                for (int y : directions) {
                    // ignore same position
                    if (x == 0 && y == 0)
                        continue;

                    int c = col + y;

                    // out of bound
                    if (c < 0 || c == width)
                        continue;

                    neighbours.add(getCell(r, c));
                }
            }

            return neighbours;
        }
    }

    /**
     * Constructs a World object.
     * @param width the width of the world
     * @param height the height of the world
     */
    public World(final int width, final int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[height][width];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j);
                cells[i][j].setLifeform(generateRandomLifeform());
            }
    }

    /**
     * Gets the height of the world.
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the width of the world.
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets a cell in the world given its row and column.
     * @param row row
     * @param col column
     * @return cell
     */
    public Cell getCell(final int row, final int col) {
        return cells[row][col];
    }

    /**
     * Moves all life forms in the world.
     */
    public void moveLifeForms() {
        for (LifeForm lifeForm: getAllLifeForms()) {
            try {
                lifeForm.move();
            } catch (Exception ignored) {}
        }
        resetCellProcessed();
    }

    /**
     * Reproduces life forms in the world.
     */
    public void breedLifeForms() {
        for (LifeForm lifeForm: getAllLifeForms()) {
            try {
                lifeForm.reproduce();
            } catch (Exception ignored) {}
        }
        resetCellProcessed();
    }

    /**
     * Gets all life forms in the world.
     * @return all life forms
     */
    private ArrayList<LifeForm> getAllLifeForms() {
        ArrayList<LifeForm> lifeForms = new ArrayList<>();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                LifeForm lifeForm = getCell(i, j).getLifeform();
                if (lifeForm != null) {
                    lifeForms.add(lifeForm);
                }
            }
        return lifeForms;
    }

    private void resetCellProcessed() {
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                cells[i][j].processed = false;
    }

    /**
     * Generates a random life form.
     * @return a random life form
     */
    private LifeForm generateRandomLifeform() {
        int value = RandomGenerator.nextNumber(100);
        if (value >= 80)
            return new Herbivore();
        else if (value >= 60)
            return new Plant();
        else if (value >= 50)
            return new Carnivore();
        else if (value >= 45)
            return new Omnivore();
        return null;
    }
}
