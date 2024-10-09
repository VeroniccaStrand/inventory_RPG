public class EnemyAttackProcessor {
    private Enemy attacker;
    private Player target;

    public EnemyAttackProcessor(Enemy attacker, Player target) {
        this.attacker = attacker;
        this.target = target;
    }

    public void performAttack(AttackType attackType) {
        int baseDamage = attacker.getAttackPower();
        int totalDamage = (int) (baseDamage * attackType.getMultiplier());

        target.takeDamage(totalDamage);

        if (!target.isDead()) {
            System.out.println("The " + attacker.getName() + " dealt " + totalDamage + " " + attackType.name() + " damage!");
            System.out.println("The player has " + target.getHealth() + " health remaining.");
        } else {
            System.out.println("The " + attacker.getName() + " dealt " + totalDamage + " " + attackType.name() + " damage!");
            System.out.println("The player has died.");
        }
    }

}
