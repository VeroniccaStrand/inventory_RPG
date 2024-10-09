import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Game {
    private Player player;
    private List<Enemy> enemies;
    private Scanner scanner;

    // Konstruktorn tar in spelaren och scannern
    public Game(Player player, Scanner scanner) {
        this.player = player;
        this.enemies = new ArrayList<>();
        this.scanner = scanner;
        initializeEnemies();  // Initiera fiender
    }

    // Startar spelet och hanterar spelets gång
    public void start() {
        System.out.println("You wake up in a mysterious forest...");
        exploreArea();  // Börjar med att spelaren går runt och hittar saker

        while (!player.isDead()) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Fight");
            System.out.println("2. Explore");
            System.out.println("3. Show Inventory");
            System.out.println("4. Quit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    startBattle();
                    break;
                case 2:
                    exploreArea();
                    break;
                case 3:
                    player.getInventory().display();  // Visa spelarens inventory
                    break;
                case 4:
                    System.out.println("You have chosen to quit the game.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        gameOver();
    }

    // Metod för att utforska området och hitta föremål
    private void exploreArea() {
        System.out.println("You explore the area and find some items.");

        // Skapa föremål
        Weapon sword = new EnchantedSword("Sword", "A sharp steel sword.", 150, Material.STEEL,Quality.EXELLENT,150);
        Armor boots = new Boots("leather boots","Rugged",10,Material.LEATHER,Quality.POOR,25);
        Potion healthPotion = new Potion("Health Potion", "Restores 50 health.", 50, PotionType.HEALTH);
        Potion staminaPotion = new Potion("Stamina Potion", "Restores 20 stamina.", 30, PotionType.STAMINA);

        // Låt spelaren välja om de vill plocka upp föremålen
        System.out.println("You found a " + sword.getName() + ". Do you want to pick it up? (yes/no)");
        if (scanner.next().equalsIgnoreCase("yes")) {
            player.pickUpItem(sword, 1);
            player.equip(sword);
        }
        System.out.println("You found  " + boots.getName() + ". Do you want to pick them up? (yes/no)");
        if (scanner.next().equalsIgnoreCase("yes")) {
            player.pickUpItem(boots, 1);
            player.equip(boots);
        }

        System.out.println("You found a " + healthPotion.getName() + ". Do you want to pick it up? (yes/no)");
        if (scanner.next().equalsIgnoreCase("yes")) {
            player.pickUpItem(healthPotion, 1);
        }

        System.out.println("You found a " + staminaPotion.getName() + ". Do you want to pick it up? (yes/no)");
        if (scanner.next().equalsIgnoreCase("yes")) {
            player.pickUpItem(staminaPotion, 1);
        }
    }

    // Starta en strid med fiender
    public void startBattle() {
        if (enemies.isEmpty()) {
            System.out.println("There are no more enemies left to fight!");
            return;
        }

        Enemy enemy = enemies.get(0);  // Ta den första fienden i listan
        System.out.println("You encounter a " + enemy.getName());

        while (enemy.isAlive() && !player.isDead()) {
            playerTurn(enemy);
            if (!enemy.isAlive()) {
                System.out.println("You have defeated the " + enemy.getName() + "!");
                enemies.remove(enemy);  // Ta bort fienden om den är död
                break;
            }
            enemyTurn(enemy);
        }
    }

    private void playerTurn(Enemy enemy) {
        System.out.println("Choose your attack:");
        AttackType[] attackTypes = AttackType.values();
        for (int i = 0; i < attackTypes.length; i++) {
            System.out.println((i + 1) + ". " + attackTypes[i].name());
        }

        int choice = scanner.nextInt();
        if (choice > 0 && choice <= attackTypes.length) {
            player.performAttack(attackTypes[choice - 1], enemy);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void enemyTurn(Enemy enemy) {
        System.out.println(enemy.getName() + " attacks you!");
        enemy.attack(player);
    }

    private void gameOver() {
        System.out.println("Game Over! The player has died.");
        System.exit(0);
    }

    private void initializeEnemies() {
        enemies.add(spawnEnemy("Goblin", 1000, 100));
        enemies.add(spawnEnemy("Orc", 1200, 25));
        enemies.add(spawnEnemy("Dragon", 1000, 200));
    }

    public Enemy spawnEnemy(String name, int health, int attackPower) {
        return new Enemy(name, health, attackPower);
    }
}
