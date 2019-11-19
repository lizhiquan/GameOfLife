/**
 * Represents a carnivore.
 */
public class Carnivore extends LifeForm implements OmniEddible {

    /**
     * Max health point.
     */
    private static final int MAX_HP = 5;

    /**
     * Constructs a Carnivore object.
     */
    public Carnivore() {
        super(MAX_HP);
    }

    @Override
    public boolean canEat(LifeForm lifeForm) {
        return lifeForm instanceof CarnEddible;
    }

    @Override
    public String getColor() {
        return "Red";
    }

    @Override
    public boolean canReproduce(int nMates, int nEmptyCells, int nFoodAvailable) {
        return nEmptyCells >= 3 && nMates >= 1 && nFoodAvailable == 2;
    }

    @Override
    public LifeForm giveBirth() {
        return new Carnivore();
    }
}
