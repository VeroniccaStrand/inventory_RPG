import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy implements Lootable{
    private final String name;
    private int health;
    private final int attackPower;
    private boolean isAlive;
    Inventory inventory;

    public Enemy(String name,int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.inventory = new Inventory(4);
        initializeLoot();

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
    private void initializeLoot() {
        Potion healthPotion = new Potion("Health Potion", "Restores 50 health.", 50, PotionType.HEALTH, 1);
        inventory.addItem(healthPotion, 1);

    }


    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public List<Item> dropLoot() {
        return new ArrayList<>(inventory.getItems().keySet());
    }
}
