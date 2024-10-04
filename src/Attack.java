public class Attack {
    private Equipment equipment;

    public Attack(Equipment equipment) {
        this.equipment = equipment;
    }

    // Lätt attack
    public void performLightAttack() {
        Weapon equippedWeapon = equipment.getEquippedWeapon();
        if (equippedWeapon instanceof LightAttack) {
            ((LightAttack) equippedWeapon).performLightAttack();
        } else {
            System.out.println("Equipped weapon cannot perform a light attack.");
        }
    }

    // Tung attack
    public void performHeavyAttack() {
        Weapon equippedWeapon = equipment.getEquippedWeapon();
        if (equippedWeapon instanceof HeavyAttack) {
            ((HeavyAttack) equippedWeapon).performHeavyAttack();
        } else {
            System.out.println("Equipped weapon cannot perform a heavy attack.");
        }
    }

    // Magisk attack
    public void performMagicAttack() {
        Weapon equippedWeapon = equipment.getEquippedWeapon();
        if (equippedWeapon instanceof MagicAttack) {
            ((MagicAttack) equippedWeapon).performMagicAttack();
        } else {
            System.out.println("Equipped weapon cannot perform a magic attack.");
        }
    }
}
