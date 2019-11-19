/**
 * Represents a plant.
 */
public class Plant extends LifeForm implements HerbEddible, OmniEddible {

    /**
     * Constructs a plant object.
     */
    public Plant() {
        super(0);
    }

    @Override
    public boolean canEat(LifeForm lifeForm) {
        return false;
    }

    @Override
    public void move() throws Exception {
        throw new Exception();
    }

    @Override
    public String getColor() {
        return "MediumSeaGreen";
    }

    @Override
    public boolean canReproduce(int nMates, int nEmptyCells, int nFoodAvailable) {
        return nEmptyCells >= 3 && nMates >= 2;
    }

    @Override
    public LifeForm giveBirth() {
        return new Plant();
    }
}
