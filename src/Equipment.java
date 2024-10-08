import java.util.ArrayList;
import java.util.List;

public class Equipment {
    private Weapon equippedWeapon;
    private List<Armor> equippedArmor;
    private List<Item> equippedItems;
    private int totalDefense;
    private int totalDamage;


    public Equipment() {
        this.equippedArmor = new ArrayList<>();
        this.equippedItems = new ArrayList<>();
        this.totalDefense = 0;
        this.totalDamage = 0;
    }



    public void equip(Item item, Inventory inventory) {
        if(!inventory.contains(item)) {
            System.out.println(item.getName() + "is not in your inventory");
            return;
        }
        switch (item) {
            case Weapon weapon -> equipWeapon(weapon);
            case Armor armor -> equipArmor(armor);
            case null, default -> equipItem(item);
        }

    }


    public void equipWeapon(Weapon weapon) {
        if (equippedWeapon != null) {
            totalDamage -= equippedWeapon.calculateWeaponDamage();
            System.out.println(equippedWeapon.getName() + " was unequipped.");
        }
        equippedWeapon = weapon;
        totalDamage += weapon.calculateWeaponDamage();
        System.out.println(weapon.getName() + " equipped. Damage increased.");
    }



    private void equipArmor(Armor armor) {
        Armor existingArmor = findArmorOfType(armor.getClass());
        if (existingArmor != null) {
            totalDefense -= existingArmor.calculateDefense();
            equippedArmor.remove(existingArmor);
            System.out.println(existingArmor.getName() + " was unequipped.");
        }

        equippedArmor.add(armor);
        totalDefense += armor.calculateDefense();
        System.out.println(armor.getName() + " equipped. Defense increased.");
    }


    private void equipItem(Item item) {
        if (equippedItems.size() < 10) {
            equippedItems.add(item);
            System.out.println(item.getName() + " equipped.");
        } else {
            System.out.println("Cannot equip more than 10 items.");
        }
    }


    private Armor findArmorOfType(Class<? extends Armor> armorClass) {
        for (Armor armor : equippedArmor) {
            if (armorClass.isInstance(armor)) {
                return armor;
            }
        }
        return null;
    }


    public int getTotalDefense() {
        return totalDefense;
    }



    public int getTotalDamage() {
        return totalDamage;
    }


    public Weapon getEquippedWeapon(){
        return equippedWeapon;
    }



    public void displayEquipment() {
        System.out.println("Current Equipment:");

        if (equippedWeapon != null) {
            System.out.println("Weapon: " + equippedWeapon.getName());
        } else {
            System.out.println("No weapon equipped.");
        }

        if (!equippedArmor.isEmpty()) {
            System.out.println("Armor equipped:");
            for (Armor armor : equippedArmor) {
                System.out.println("- " + armor.getName());
            }
        } else {
            System.out.println("No armor equipped.");
        }

        if (!equippedItems.isEmpty()) {
            System.out.println("Other items equipped:");
            for (Item item : equippedItems) {
                System.out.println("- " + item.getName());
            }
        } else {
            System.out.println("No other items equipped.");
        }
    }
}
