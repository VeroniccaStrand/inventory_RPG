public class Player {

    private final Inventory inventory;
    private final Equipment equipment;
    private int health;
    private boolean dead;

    public Player() {
        String name = "Player";

        this.inventory = new Inventory(100);
        this.equipment = new Equipment();

        this.health = 600;
        this.dead = false;
    }


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

    public void increaseHealth(int amount) {
        if(amount > 0) {
            health += amount;
            int maxHealth = 1000;
            if(this.health > maxHealth) {
                this.health = maxHealth;
            }
        }
    }



    public void useItem(Item item) {

        if (inventory.getItems().containsKey(item) && inventory.getItems().get(item) > 0) {
            if (item instanceof Consumable) {
                if (item instanceof Potion potion) {

                    if (potion.getPotionType() == PotionType.HEALTH) {
                        int value = potion.use(inventory);
                        increaseHealth(value);
                        System.out.println("You used a Health Potion.");
                    }

                    else if (potion.getPotionType() == PotionType.STAMINA) {
                        int value = potion.use(inventory);
                        System.out.println("You restored " + value + " stamina.");
                    }
                } else {

                    ((Consumable) item).use(inventory);
                }
            } else {
                System.out.println(item.getName() + " cannot be used.");
            }
        } else {

            System.out.println("You don't have any " + item.getName() + " in your inventory.");
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


    public int getTotalDefense() {
        return equipment.getTotalDefense();
    }

    public int getTotalDamage() {
        return equipment.getTotalDamage();
    }


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