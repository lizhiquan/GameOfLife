/**
 * Represents a omnivore.
 */
public class Omnivore extends LifeForm implements CarnEddible {

    /**
     * Max health point.
     */
    private static final int MAX_HP = 5;

    /**
     * Constructs a Omnivore object.
     */
    public Omnivore() {
        super(MAX_HP);
    }

    @Override
    public boolean canEat(LifeForm lifeForm) {
        return lifeForm instanceof OmniEddible;
    }

    @Override
    public String getColor() {
        return "Blue";
    }

    @Override
    public boolean canReproduce(int nMates, int nEmptyCells, int nFoodAvailable) {
        return nEmptyCells >= 3 && nMates >= 1 && nFoodAvailable == 1;
    }

    @Override
    public LifeForm giveBirth() {
        return new Omnivore();
    }
}
