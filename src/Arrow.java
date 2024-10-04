public class Arrow extends Ammo{
    private final Material material;
    private final Quality quality;

    public Arrow(String name, String description, int value, Material material, Quality quality) {
        super(name, description, value, calculateDamage(material, quality));
        this.material = material;
        this.quality = quality;

    }
    private static int calculateDamage(Material material, Quality quality) {
        return material.getMaterialBonus() + quality.getQualityBonus();
    }

    @Override
    public void use(Inventory inventory) {
        inventory.removeItem(this, 1);
    }
}
