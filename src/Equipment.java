import java.util.ArrayList;
import java.util.List;

public class Equipment {
    private Weapon equippedWeapon;
    private static Ammo equippedAmmo;
    private List<Armor> equippedArmor;
    private List<Item> equippedItems;
    private int totalDefense;
    private int totalDamage;

    public Equipment() {
        this.equippedArmor = new ArrayList<Armor>();
        this.equippedItems = new ArrayList<>();
        this.totalDefense = 0;
        this.totalDamage = 0;
    }

    public static Ammo getEquippedAmmo() {
        return equippedAmmo;
    }

    public void equip(Item item, Inventory inventory) {
        switch (item) {
            case Weapon weapon -> equipWeapon(weapon);
            case Ammo ammo -> equipAmmo(ammo, inventory);
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
    private void equipAmmo(Ammo ammo, Inventory inventory) {
        if (equippedAmmo != null) {
            System.out.println(equippedAmmo.getName() + " was unequipped.");
        }

        int ammoAvailable = inventory.getItemQuantity(ammo);
        int ammoToEquip = Math.min(30, ammoAvailable);  // Plocka max 30 enheter
        equippedAmmo = ammo;
        inventory.removeItem(ammo, ammoToEquip);  // Ta bort ammo från inventory
        System.out.println(ammoToEquip + " " + ammo.getName() + " equipped.");
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
}
