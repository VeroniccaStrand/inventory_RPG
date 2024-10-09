import java.util.Random;

public class Enemy {
    private final String name;
    private int health;
    private final int attackPower;
    private boolean isAlive;

    public Enemy(String name,int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;

        this.isAlive = true;
    }
    public void takeDamage(int damage) {
        health -= damage;
        if(health <= 0) {
            isAlive = false;
        }

    }
    public boolean isAlive() {
        return isAlive;
    }
    public String getName() {
        return name;
    }

    public int getHealth() {
            return health;
    }
    public int getAttackPower() {
        return attackPower;
    }
    public AttackType chooseAttack() {
        AttackType[] allowedAttacks = {AttackType.LIGHT, AttackType.HEAVY};
        Random random = new Random();
        return allowedAttacks[random.nextInt(allowedAttacks.length)];
    }

    public void attack(Player player) {
       AttackType chosenAttack = chooseAttack();
       EnemyAttackProcessor enemyAttackProcessor = new EnemyAttackProcessor(this, player);
       enemyAttackProcessor.performAttack(chosenAttack);
    }


}
