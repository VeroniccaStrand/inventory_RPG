public class Potion extends Consumable{
    private PotionType potionType;

    public Potion(String name, String description,int value, PotionType potionType) {
        super(name, description, value);
        this.potionType = potionType;
    }

    @Override
    public void use( Inventory inventory ) {
        switch (potionType) {
            case HEALTH -> {
                System.out.println("Drinking health potion " + potionType.getIncreaseAmount());
                inventory.removeItem(this, 1);
            }
            case STAMINA -> {
                System.out.println("Drinking stamina potion " + potionType.getIncreaseAmount());
                inventory.removeItem(this, 1);
            }
            default -> System.out.println("No potion found");
        }
    }
}
