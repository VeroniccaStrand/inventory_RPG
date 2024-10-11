public abstract class Consumable extends Item {
    public Consumable( String name, String description, int value) {

    super(name, description, value,true);

    }
    public abstract int use(Inventory inventory);
}
