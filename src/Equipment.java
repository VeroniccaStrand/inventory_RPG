import java.util.ArrayList;
import java.util.List;

public class Equipment {
    private Weapon equippedWeapon;
    private Ammo equippedAmmo;
    private int equippedAmmoCount = 0;
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
    public void equipAmmo(Ammo ammo, Inventory inventory) {
        if (equippedAmmo != null && equippedAmmo.equals(ammo)) {
            int ammoAvailable = inventory.getItemQuantity(ammo);
            int ammoToAdd = Math.min(30 - equippedAmmoCount, ammoAvailable);

            if (ammoToAdd > 0) {
                inventory.removeItem(ammo, ammoToAdd);
                equippedAmmoCount += ammoToAdd;  // Uppdatera antalet utrustade ammo
                System.out.println("Added " + ammoToAdd + " " + ammo.getName() + ". Total: " + equippedAmmoCount + " equipped.");
            } else {
                System.out.println(ammo.getName() + " is already at max capacity (30).");
            }
        } else {
            if (equippedAmmo != null) {
                inventory.addItem(equippedAmmo, equippedAmmoCount);  // Returnera det faktiska antalet utrustade ammo
                System.out.println(equippedAmmo.getName() + " was unequipped and returned to inventory.");
            }

            int ammoAvailable = inventory.getItemQuantity(ammo);
            equippedAmmoCount = Math.min(30, ammoAvailable);  // Utrusta max 30 enheter eller så många som finns tillgängliga

            equippedAmmo = ammo;
            inventory.removeItem(ammo, equippedAmmoCount);
            System.out.println(equippedAmmoCount + " " + ammo.getName() + " equipped.");
        }
    }

    // Metod för att använda en ammo i en attack
    public void useAmmo() {
        if (equippedAmmo == null || equippedAmmoCount == 0) {
            System.out.println("No ammo equipped or no ammo left.");
        } else {
            equippedAmmoCount--;  // Minska antalet utrustade ammo med 1
            System.out.println("Used 1 " + equippedAmmo.getName() + ". Remaining ammo: " + equippedAmmoCount);

            if (equippedAmmoCount == 0) {
                equippedAmmo = null;  // Sätt equippedAmmo till null om allt är använt
                System.out.println("Out of ammo! You need to equip more.");
            }
        }
    }
    public boolean hasAmmo() {
        return equippedAmmoCount > 0;
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

    public Ammo getEquippedAmmo() {
        return equippedAmmo;
    }

    public void displayEquipment() {
        System.out.println("Current Equipment:");

        if (equippedWeapon != null) {
            System.out.println("Weapon: " + equippedWeapon.getName());
        } else {
            System.out.println("No weapon equipped.");
        }

        if (equippedAmmo != null) {
            System.out.println("Ammo: " + equippedAmmo.getName() + equippedAmmoCount);
        } else {
            System.out.println("No ammo equipped.");
        }

        if (!equippedArmor.isEmpty()) {
            System.out.println("Armor equipped:");
            for (Armor armor : equippedArmor) {
                System.out.println("- " + armor.getName());
            }
        } else {
            System.out.println("No armor equipped.");
        }
    }
}
