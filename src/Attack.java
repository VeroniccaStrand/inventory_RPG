public class Attack  {

    protected Weapon equippedWeapon;
    protected Enemy target;

    public Attack(Weapon weapon, Enemy target) {
        this.equippedWeapon = weapon;
        this.target = target;
    }


    public void performAttack(AttackType attackType) {
        int damage = 0;
        switch (attackType) {
            case LIGHT:
                damage = equippedWeapon.lightAttack();
                break;
            case HEAVY:
                damage = equippedWeapon.heavyAttack();
                System.out.println();
                break;

            default:
                System.out.println("Unknown attack type.");
        }
        System.out.println("The attack dealt " + damage + " damage.");
        target.takeDamage(damage);
    }


}
