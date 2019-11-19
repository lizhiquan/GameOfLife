/**
 * Represents a herbivore.
 */
public class Herbivore extends LifeForm implements CarnEddible, OmniEddible {

    /**
     * Max health point.
     */
    private static final int MAX_HP = 5;

    /**
     * Constructs a herbivore object.
     */
    public Herbivore() {
        super(MAX_HP);
    }

    @Override
    public boolean canEat(LifeForm lifeForm) {
        return lifeForm instanceof HerbEddible;
    }

    @Override
    public String getColor() {
        return "Yellow";
    }

    @Override
    public boolean canReproduce(int nMates, int nEmptyCells, int nFoodAvailable) {
        return nMates >= 1 && nEmptyCells >= 2 && nFoodAvailable >= 2;
    }

    @Override
    public LifeForm giveBirth() {
        return new Herbivore();
    }
}
