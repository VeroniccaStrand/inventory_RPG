public class Attack implements AttackPerformer {

    protected Weapon equippedWeapon;

    public Attack(Weapon weapon) {
        this.equippedWeapon = weapon;
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
        if (equippedWeapon instanceof LightAttack) {
            ((LightAttack) equippedWeapon).performLightAttack();
        } else {
            System.out.println("Equipped weapon cannot perform a light attack.");
        }
    }

    public void performHeavyAttack() {
        if (equippedWeapon instanceof HeavyAttack) {
            ((HeavyAttack) equippedWeapon).performHeavyAttack();
        } else {
            System.out.println("Equipped weapon cannot perform a heavy attack.");
        }
    }

    public void performMagicAttack() {
        if (equippedWeapon instanceof MagicAttack) {
            ((MagicAttack) equippedWeapon).performMagicAttack();
        } else {
            System.out.println("Equipped weapon cannot perform a magic attack.");
        }
    }
}
