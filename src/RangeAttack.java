public class RangeAttack implements AttackPerformer {

    private Weapon equippedWeapon;
    private Ammo equippedAmmo;

    // Konstruktor som tar två argument: Weapon och Ammo
    public RangeAttack(Weapon weapon, Ammo ammo) {
        this.equippedWeapon = weapon;
        this.equippedAmmo = ammo;
    }

    @Override
    public void performAttack(AttackType attackType) {
        switch (attackType) {
            case LIGHT:
                performLightAttack();
                break;
            case HEAVY:
                performHeavyAttack();
                break;
            case MAGIC:
                performMagicAttack();
                break;
            default:
                System.out.println("Unknown attack type.");
        }
    }

    public void performLightAttack() {
        if (equippedWeapon instanceof RangeAttacker) {
            ((RangeAttacker) equippedWeapon).performLightAttack(equippedAmmo);
        } else {
            System.out.println("Equipped weapon cannot perform a light attack with ammo.");
        }
    }

    // Tung attack
    public void performHeavyAttack() {
        if (equippedWeapon instanceof RangeAttacker) {
            ((RangeAttacker) equippedWeapon).performHeavyAttack(equippedAmmo);
        } else {
            System.out.println("Equipped weapon cannot perform a heavy attack with ammo.");
        }
    }

    // Magisk attack
    public void performMagicAttack() {
        if (equippedWeapon instanceof RangeAttacker) {
            ((RangeAttacker) equippedWeapon).performMagicAttack(equippedAmmo);
        } else {
            System.out.println("Equipped weapon cannot perform a magic attack with ammo.");
        }
    }
}
