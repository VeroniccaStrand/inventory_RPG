public class AttackProcessor {

    private Weapon equippedWeapon;
    private Enemy target;

    public AttackProcessor(Weapon equippedWeapon, Enemy target) {
        this.equippedWeapon = equippedWeapon;
        this.target = target;
    }

    public void performAttack(AttackType attackType) {
        int baseDamage = equippedWeapon.calculateWeaponDamage();
        int totalDamage = (int) (baseDamage * attackType.getMultiplier());

        target.takeDamage(totalDamage);

        if (target.isAlive()) {
            System.out.println("The attack dealt " + totalDamage + " " + attackType.name() + " damage!");
            System.out.println("The " + target.getName() + " has " + target.getHealth() + " health remaining.");
        } else {
            System.out.println("The attack dealt " + totalDamage + " " + attackType.name() + " damage!");
            System.out.println("The " + target.getName() + " died.");
        }
    }
}