public class Boots extends Armor{
    private final int baseDefense;
    private final int totalDefense;


    public Boots(String name, String description, int value, Material material, Quality quality, int baseDefense) {
        super(name,description,value,material,quality);
        this.baseDefense = baseDefense;
        this.totalDefense = calculateDefense();


    }

    @Override
    public int calculateDefense() {
    return baseDefense + getMaterial().getMaterialBonus() + getQuality().getQualityBonus();
    }

    public int getDefense() {
        return totalDefense;
    }
}
