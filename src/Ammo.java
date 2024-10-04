public abstract class Ammo extends Item{
    private final int ammoDamage;
    public Ammo(String name, String description, int value, int ammoDamage) {
        super(name, description, value, true);
        this.ammoDamage = ammoDamage;
    }
    public int getAmmoDamage() {
        return ammoDamage;
    }

    public abstract void use(Inventory inventory);
}
