public class Enemy {
    private int health;
    public Enemy(int health) {
        this.health = health;
    }
    public void takeDamage(int damage) {
        health -= damage;
        System.out.println("Enemy took " + damage + " damage. Remaining health: " + health);
    }

    public int getHealth() {
            return health;
    }
}
