
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Game {
    private Player player;
    private List<Enemy> enemies;
    private Scanner scanner;


    public Game(Player player, Scanner scanner) {
        this.player = player;
        this.enemies = new ArrayList<>();
        this.scanner = scanner;
        initializeEnemies();
    }


    public void start() {

        Potion staminaPotion = new Potion("Stamina Potion","Increas Stamina",14,PotionType.STAMINA,1);
        player.pickUpItem(staminaPotion, 1);
        player.throwItem(staminaPotion);

        System.out.println("You wake up in a mysterious forest... and there is a chest, let's open it!");
        openChest();

        while (!player.isDead()) {
            System.out.println("player stats: " + "health: " + player.getHealth());

            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Fight");
            System.out.println("2. Show Inventory");
            System.out.println("3. Quit");
            System.out.println("4. Enchant Weapons");
            System.out.println("5. Show Equipment Inventory");
            System.out.println("6. Drink Health Potion");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    startBattle();
                    break;

                case 2:
                    showInventoryAndEquip();
                    break;

                case 3:
                    System.out.println("You have chosen to quit the game.");
                    System.exit(0);
                    break;

                case 4:
                    enchantWeapon();
                    break;

                case 5:
                    player.getEquipment().displayEquipment();
                    break;

                case 6:
                    Potion healthPotion = player.getInventory().findItemByType(Potion.class);
                   if (healthPotion != null && healthPotion.getPotionType() == PotionType.HEALTH) {
                       player.useItem(healthPotion);
                   }else{
                       System.out.println("You don't have any Health Potions");
                }
                   break;

                default:
                    System.out.println("Invalid choice. Try again.");

            }
        }

        gameOver();
    }

    private void enchantWeapon() {
        Weapon equippedWeapon = player.getEquipment().getEquippedWeapon();

        if (equippedWeapon instanceof EnchantedSword enchantedSword) {


            System.out.println("Choose an enchantment for your weapon:");
            Enchantment[] enchantments = Enchantment.values();
            for (int i = 0; i < enchantments.length; i++) {
                System.out.println((i + 1) + ". " + enchantments[i].getName() + " (+" + enchantments[i].getDamageBonus() + " damage)");
            }


            int choice = scanner.nextInt();
            if (choice > 0 && choice <= enchantments.length) {
                Enchantment chosenEnchantment = enchantments[choice - 1];
                enchantedSword.setEnchantment(chosenEnchantment);
                System.out.println("Your weapon has been enchanted with: " + chosenEnchantment.getName());
            } else {
                System.out.println("Invalid choice. No enchantment applied.");
            }
        } else {
            System.out.println("You don't have an enchanted weapon equipped.");
        }
    }

    private void showInventoryAndEquip() {
        System.out.println("Your Inventory:");
        player.getInventory().display();

        System.out.println("Do you want to equip an item? (yes/no)");
        String equipChoice = scanner.next();

        if (equipChoice.equalsIgnoreCase("yes")) {
            System.out.println("Enter the number of the item to equip:");
            List<Item> items = new ArrayList<>(player.getInventory().getItems().keySet());

            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).getName());
            }

            int itemChoice = scanner.nextInt();
            if (itemChoice > 0 && itemChoice <= items.size()) {
                Item itemToEquip = items.get(itemChoice - 1);
                player.equip(itemToEquip);
                System.out.println("You have equipped: " + itemToEquip.getName());
            } else {
                System.out.println("Invalid choice. No item equipped.");
            }
        }
    }
    private void openChest() {
        System.out.println("You found a chest! Let's see what's inside...");


        List<Item> chestItems = getItems();


        System.out.println("The chest contains:");
        for (int i = 0; i < chestItems.size(); i++) {
            System.out.println((i + 1) + ". " + chestItems.get(i).getName() );
        }


        System.out.println("Do you want to take everything? (yes/no)");
        String takeAllChoice = scanner.next();

        if (takeAllChoice.equalsIgnoreCase("yes")) {

            for (Item item : chestItems) {
                if(item instanceof Potion potion) {
                    player.pickUpItem(potion, potion.getQuantity());
                }else {
                    player.pickUpItem(item, 1);
                }

            }
            System.out.println("You took everything from the chest!");

        } else {

            System.out.println("Choose which items to take (enter the numbers separated by commas, e.g., 1,2):");
            String choices = scanner.next();
            String[] itemChoices = choices.split(",");

            for (String choice : itemChoices) {
                try {
                    int itemIndex = Integer.parseInt(choice.trim()) - 1;
                    if (itemIndex >= 0 && itemIndex < chestItems.size()) {
                        Item selectedItem = chestItems.get(itemIndex);
                        player.pickUpItem(selectedItem, 1);

                        System.out.println("You picked up the " + selectedItem.getName() + ".");
                    } else {
                        System.out.println("Invalid item choice.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter valid numbers.");
                }
            }
        }
    }

    private static List<Item> getItems() {
        Weapon sword = new EnchantedSword("Sword", "A sharp steel sword.", 150, Material.STEEL, Quality.EXCELLENT, 150);
        Armor boots = new Boots("Leather Boots", "Rugged", 10, Material.LEATHER, Quality.POOR, 25);
        Potion healthPotion = new Potion("Health Potion", "Restores 50 health.", 50, PotionType.HEALTH, 1);
        Potion staminaPotion = new Potion("Stamina Potion", "Restores 20 stamina.", 30, PotionType.STAMINA,34);


        List<Item> chestItems = new ArrayList<>();
        chestItems.add(sword);
        chestItems.add(boots);
        chestItems.add(healthPotion);
        chestItems.add(staminaPotion);
        return chestItems;
    }


    public void startBattle() {
        if (enemies.isEmpty()) {
            System.out.println("There are no more enemies left to fight!");
            return;
        }

        Enemy enemy = enemies.getFirst();
        System.out.println("You encounter a " + enemy.getName());

        while (enemy.isAlive() && !player.isDead()) {
            playerTurn(enemy);
            if (!enemy.isAlive()) {
                System.out.println("You have defeated the " + enemy.getName() + "!");
                enemyDefeated(enemy);
                enemies.remove(enemy);
                break;
            }
            enemyTurn(enemy);
        }
    }
    public void enemyDefeated(Enemy enemy) {
        if (enemy != null) {
            List<Item> loot = ((Lootable) enemy).dropLoot();
            if (!loot.isEmpty()) {
                System.out.println("You have collected the following loot from " + enemy.getName() + ":");
                for (Item item : loot) {
                    player.pickUpItem(item, 1);
                    System.out.println(" - " + item.getName());
                }
            } else {
                System.out.println("No loot dropped by " + enemy.getName());
            }
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
