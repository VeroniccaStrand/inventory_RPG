public class Player {


    private Inventory inventory;
    private Equipment equipment;

    public Player() {
        this.inventory = new Inventory(100);  // Skapa ett inventory för spelaren med 100 platser
        this.equipment = new Equipment();  // Skapa ett equipment för spelaren
    }

    public void performAttack(String attackType) {
        Attack attack = new Attack(equipment);  // Skickar spelarens utrustning till Attack-klassen

        switch (attackType) {
            case "light":
                attack.performLightAttack();
                break;
            case "heavy":
                attack.performHeavyAttack();
                break;
            case "magic":
                attack.performMagicAttack();
                break;
            default:
                System.out.println("Unknown attack type.");
        }
    }
    public void enchantWeapon(Enchantment enchantment) {
        Weapon equippedWeapon = equipment.getEquippedWeapon();
        if (equippedWeapon instanceof Enchantable) {
            ((Enchantable) equippedWeapon).setEnchantment(enchantment);
        } else {
            System.out.println("The equipped weapon cannot be enchanted.");
        }
    }

    public void equip(Item item) {
        equipment.equip(item, inventory);
    }

    public void useItem(Item item) {
        if (item instanceof Consumable) {
            ((Consumable) item).use(inventory);  // Använd konsumtionslogiken
        } else {
            System.out.println(item.getName() + " cannot be used.");
        }
    }

    public void displayInventory() {
        inventory.display();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getTotalDefense() {
        return equipment.getTotalDefense();
    }

    public int getTotalDamage() {
        return equipment.getTotalDamage();
    }
    public Equipment getEquipment() {
        return equipment;
    }
}
