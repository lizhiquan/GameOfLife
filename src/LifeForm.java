import java.util.ArrayList;

/**
 * Represents a life form.
 */
public abstract class LifeForm {

    /**
     * Current health point.
     */
    protected int currentHP;

    /**
     * Current health point.
     */
    private int maxHP;

    /**
     * A cell that holds this life form.
     */
    private World.Cell cell;

    /**
     * Constructs a new life form.
     * @param maxHP maximum HP
     */
    public LifeForm(int maxHP) {
        this.maxHP = maxHP;
        this.currentHP = maxHP;
    }

    /**
     * Sets a new cell that holds this life form.
     * @param cell a new cell to set
     */
    public void setCell(World.Cell cell) {
        this.cell = cell;
    }

    /**
     * Determines if it can eat a given life form
     * @param lifeForm life form that represents food
     * @return true if it can eat
     */
    public abstract boolean canEat(LifeForm lifeForm);

    /**
     * Resets current HP to the maximum value.
     */
    private void resetHP() {
        currentHP = maxHP;
    }

    /**
     * Moves to a new position.
     * @throws Exception throws if it can't move
     */
    public void move() throws Exception {
        if (cell.isProcessed())
            return;

        ArrayList<World.Cell> neighbours = cell.getNeighbours();
        World.Cell cellToMove = getRandomCellToMove(neighbours);

        if (cellToMove == null)
            throw new Exception();

        if (cellToMove.getLifeform() != null) {
            resetHP();
        } else {
            currentHP--;
        }

        cell.setLifeform(null);
        if (currentHP > 0) {
            cellToMove.setLifeform(this);
            cellToMove.setProcessed(true);
        }
    }

    /**
     * Reproduces a new life form.
     * @throws Exception throws if it can't reproduce
     */
    public void reproduce() throws Exception {
        ArrayList<World.Cell> neighbours = cell.getNeighbours();
        int nMates = 0;
        int nEmptyCells = 0;
        int nFoodAvailable = 0;

        for (World.Cell cell : neighbours) {
            LifeForm lf = cell.getLifeform();
            if (lf == null)
                nEmptyCells++;
            else if (canEat(lf))
                nFoodAvailable++;
            else if (getClass().equals(lf.getClass()))
                nMates++;
        }

        if (!canReproduce(nMates, nEmptyCells, nFoodAvailable))
            throw new Exception();

        World.Cell cellToReproduce = getRandomCellToReproduce(neighbours);
        if (cellToReproduce == null)
            throw new Exception();
        cellToReproduce.setLifeform(giveBirth());
    }

    /**
     * Returns the color of a life form.
     * @return css color string
     */
    public abstract String getColor();

    /**
     * Checks if a life form can reproduce.
     * @param nMates number of mates
     * @param nEmptyCells number of empty cells
     * @param nFoodAvailable number of food available
     * @return true if this life form can reproduce in this neighbour
     */
    public abstract boolean canReproduce(int nMates, int nEmptyCells, int nFoodAvailable);

    /**
     * Gives birth and returns a new life form.
     * @return a new life form
     */
    public abstract LifeForm giveBirth();

    /**
     * Gets a random cell to move.
     * @param cells list of cells
     * @return a random cell to move in
     */
    private World.Cell getRandomCellToMove(ArrayList<World.Cell> cells) {
        ArrayList<World.Cell> possibleCells = new ArrayList<>();

        for (World.Cell cell : cells) {
            LifeForm lf = cell.getLifeform();
            if (lf == null || canEat(lf))
                possibleCells.add(cell);
        }

        if (possibleCells.isEmpty())
            return null;

        return possibleCells.get(RandomGenerator.nextNumber(possibleCells.size()));
    }

    /**
     * Gets a random cell to reproduce.
     * @param cells list of cells
     * @return a random cell to reproduce
     */
    private World.Cell getRandomCellToReproduce(ArrayList<World.Cell> cells) {
        ArrayList<World.Cell> possibleCells = new ArrayList<>();

        for (World.Cell cell : cells)
            if (cell.getLifeform() == null)
                possibleCells.add(cell);

        if (possibleCells.isEmpty())
            return null;

        return possibleCells.get(RandomGenerator.nextNumber(possibleCells.size()));
    }
}
