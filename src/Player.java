public class Player {


    private final Inventory inventory;
    private final Equipment equipment;

    public Player() {
        this.inventory = new Inventory(100);
        this.equipment = new Equipment();
    }


    public void performAttack(AttackType attackType) {
        Weapon equippedWeapon = equipment.getEquippedWeapon();
        Ammo equippedAmmo = equipment.getEquippedAmmo();

        // Kontrollera om spelaren har ett vapen utrustat
        if (equippedWeapon == null) {
            System.out.println("No weapon equipped.");
            return;
        }

        // Skapa en instans av Attack eller RangeAttack beroende på vapentyp
        AttackPerformer attackPerformer;  // Rätt stavning

        if (equippedWeapon instanceof RangeAttacker && equippedAmmo != null) {
            // Om vapnet använder ammo, skapa RangeAttack
            attackPerformer = new RangeAttack(equippedWeapon, equippedAmmo);
            equipment.useAmmo();
        } else {
            // Annars, skapa en vanlig Attack
            attackPerformer = new Attack(equippedWeapon);
        }

        // Utför attacken
        attackPerformer.performAttack(attackType);
    }



    public void useItem (Item item){
            if (item instanceof Consumable) {
                ((Consumable) item).use(inventory);
            } else {
                System.out.println(item.getName() + " cannot be used.");
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

