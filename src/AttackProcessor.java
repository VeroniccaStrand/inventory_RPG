public class AttackProcessor  {

    protected Weapon equippedWeapon;
    protected Enemy target;

    public AttackProcessor(Weapon weapon, Enemy target) {
        this.equippedWeapon = weapon;
        this.target = target;
    }


    public void performAttack(AttackType attackType) {
        // Använd vapnets beräknade skada och multiplicera baserat på attacktyp
        int baseDamage = equippedWeapon.calculateWeaponDamage();
        int totalDamage = (int) (baseDamage * attackType.getMultiplier());

        System.out.println("The attack dealt " + totalDamage + " " + attackType.name() + " damage.");
        target.takeDamage(totalDamage);
    }


}
