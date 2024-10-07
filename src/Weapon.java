public abstract class Weapon extends Item{
    private final Material material;
    private final Quality quality;

    public Weapon(String name, String description, int value, Material material, Quality quality) {
        super(name, description, value);
        this.material = material;
        this.quality = quality;
    }
    public Material getMaterial() {
        return material;
    }
    public Quality getQuality() {
        return quality;
    }


    public abstract int calculateWeaponDamage();
    public abstract int lightAttack();
    public abstract int heavyAttack();

}
