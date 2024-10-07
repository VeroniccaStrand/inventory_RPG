import java.util.ArrayList;
import java.util.List;

public class Game {
    private Player player;
    private List<Enemy> enemies;

    public Game(Player player) {
        this.player = player;
        this.enemies = new ArrayList<>();
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        System.out.println("A new enemy has appeared!");
    }
}
