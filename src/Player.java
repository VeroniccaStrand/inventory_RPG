public class Player {

    private final Inventory inventory;
    private final Equipment equipment;
    private String name;
    private int health;
    private boolean dead;

    public Player() {
        this.inventory = new Inventory(100);
        this.equipment = new Equipment();
        this.name = "Player";
        this.health = 500;
        this.dead = false;
    }

    // Hantera attacker med AttackProcessor
    public void performAttack(AttackType attackType, Enemy target) {
        Weapon equippedWeapon = equipment.getEquippedWeapon();

        if (equippedWeapon == null) {
            System.out.println("No weapon found.");
            return;
        }

        AttackProcessor attack = new AttackProcessor(equippedWeapon, target);
        attack.performAttack(attackType);
    }

    public void pickUpItem(Item item, int quantity) {
        if (item.isStackable()) {

            inventory.addItem(item, quantity);
            System.out.println("Picked up " + quantity + " of " + item.getName());
        } else {

            inventory.addItem(item, 1);
            System.out.println("Picked up 1 of " + item.getName());
        }
    }

    public void throwItem(Item item) {
        boolean success = inventory.removeItem(item, 1);
        if (success) {
            System.out.println("Throwed " + item.getName());
        }else {
            System.out.println("Throwing "  + item.getName());
        }

    }



    public void useItem(Item item) {
        if (item instanceof Consumable) {
            ((Consumable) item).use(inventory);
        } else {
            System.out.println(item.getName() + " cannot be used.");
        }
    }

    // Enchanta det nuvarande vapnet
    public void enchantWeapon(Enchantment enchantment) {
        Weapon equippedWeapon = equipment.getEquippedWeapon();
        if (equippedWeapon instanceof Enchantable) {
            ((Enchantable) equippedWeapon).setEnchantment(enchantment);
        } else {
            System.out.println("The equipped weapon cannot be enchanted.");
        }
    }

    // Utrusta ett föremål
    public void equip(Item item) {
        equipment.equip(item, inventory);
    }

    // Hämta försvar och skada från utrustningen
    public int getTotalDefense() {
        return equipment.getTotalDefense();
    }

    public int getTotalDamage() {
        return equipment.getTotalDamage();
    }

    // Getter för inventory och equipment
    public Inventory getInventory() {
        return inventory;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int attackPower) {
        health -= attackPower;
        if(health <= 0) {
            dead = true;
        }
    }
    public boolean isDead() {
        return dead;
    }


}