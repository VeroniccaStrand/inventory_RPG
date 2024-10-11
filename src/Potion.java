public class Potion extends Consumable{
    private PotionType potionType;
    private int quantity;

    public Potion(String name, String description,int value, PotionType potionType, int quantity) {
        super(name, description, value);
        this.potionType = potionType;
        this.quantity = quantity;
    }

    @Override
    public int use(Inventory inventory ) {
        switch (potionType) {
            case HEALTH -> {
                System.out.println("Drinking health potion " + potionType.getIncreaseAmount());
                inventory.removeItem(this, 1);
                return PotionType.HEALTH.getIncreaseAmount();
            }
            case STAMINA -> {
                System.out.println("Drinking stamina potion " + potionType.getIncreaseAmount());
                inventory.removeItem(this, 1);
                return PotionType.STAMINA.getIncreaseAmount();
            }
            default -> System.out.println("No potion found");
        }

        return 0;
    }

    public int getQuantity() {
        return quantity;
    }
    public PotionType getPotionType(){
        return this.potionType;
    }
}
