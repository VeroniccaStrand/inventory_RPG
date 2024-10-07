public class Player {


    private final Inventory inventory;
    private final Equipment equipment;

    public Player() {
        this.inventory = new Inventory(100);
        this.equipment = new Equipment();
    }

public void performAttack(AttackType attackType, Enemy target) {
        Weapon equippedWeapon = equipment.getEquippedWeapon();

        //kollar så ett vapen är equippat
    if(equippedWeapon == null) {
        System.out.println("no weapon found");
        return;
    }
    Attack attack = new Attack(equippedWeapon, target);
    attack.performAttack(attackType);
}



//Just nu finns bara Consumables för användning, och de har en egen metod att hantera use då de är stackable.
    //bygga ut för items som inte är stackable (default) och/eller för items med special use()
    public void useItem (Item item){
            if (item instanceof Consumable) {
                ((Consumable) item).use(inventory);
            } else {
                System.out.println(item.getName() + " cannot be used.");
            }
        }
//metod för att tilldela sitt vapen en enchanment. (kan skapa en interface enchantable men nu finns bara ett enchanted sword)
    //nu enchantar man det vapnet man har equippat
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

        public void displayInventory () {
            inventory.display();
        }

        public Inventory getInventory () {
            return inventory;
        }

        public int getTotalDefense () {
            return equipment.getTotalDefense();
        }

        public int getTotalDamage () {
            return equipment.getTotalDamage();
        }
        public Equipment getEquipment () {
            return equipment;
        }
    }

