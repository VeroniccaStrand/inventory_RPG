import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Simulera att vakna upp i en grotta
        System.out.println("You wake up in a dark cave, naked and confused...");
        System.out.println("You start walking around the cave.");

        // Skapa ett Player-objekt som hanterar inventory och equipment
        Player player = new Player();

        Scanner scanner = new Scanner(System.in);

        // Hitta olika föremål
        System.out.println("You find a pair of Boots.");
        Armor boots = new Boots("Boots", "Runners", 50, Material.LEATHER, Quality.AVARAGE, 32);
        player.getInventory().addItem(boots, 1); // Korrekt anrop för att lägga till i spelarens inventory

        System.out.println("You find a Bow.");
        Weapon bow = new Bow("Wooden Bow", "A sturdy wooden bow", 50,  Material.WOOD, Quality.EXELLENT, 15);
        player.getInventory().addItem(bow, 1); // Korrekt anrop för inventory

        System.out.println("You find an Enchanted Sword.");
        EnchantedSword enchantedSword = new EnchantedSword("Enchanted Sword", "Magic sword", 45, Material.STEEL, Quality.AVARAGE, 190);
        player.getInventory().addItem(enchantedSword, 1); // Lägg till enchantedSword till inventory

        // Fråga om förtrollning för det förtrollade svärdet
        System.out.println("How would you like to enchant the sword? (fire, ice, lightning)");
        String enchantment = scanner.nextLine();
        enchantedSword.setEnchantment(Enchantment.valueOf(enchantment.toUpperCase())); // Sätt rätt enchantment

        // Hitta några health potions
        System.out.println("You find 3 Health Potions.");
        Potion healthPotion = new Potion("Health Potion", "Good for you", 55, PotionType.HEALTH);
        player.getInventory().addItem(healthPotion, 3); // Lägg till potioner till inventory

        // Visa inventory och be spelaren att utrusta
        System.out.println("\nYour Inventory:");
        player.displayInventory(); // Anropa spelarens inventory

        // Utrusta Boots
        System.out.println("\nWould you like to equip the Boots? (yes/no)");
        String equipBoots = scanner.nextLine();
        if (equipBoots.equalsIgnoreCase("yes")) {
            player.equip(boots); // Använd player.equip för att utrusta boots
            System.out.println("You have equipped the Boots.");
        }

        // Utrusta Bow
        System.out.println("\nWould you like to equip the Bow? (yes/no)");
        String equipBow = scanner.nextLine();
        if (equipBow.equalsIgnoreCase("yes")) {
            player.equip(bow); // Använd player.equip för att utrusta bow
            System.out.println("You have equipped the Bow.");
        }

        // Utrusta Enchanted Sword
        System.out.println("\nWould you like to equip the Enchanted Sword? (yes/no)");
        String equipSword = scanner.nextLine();
        if (equipSword.equalsIgnoreCase("yes")) {
            player.equip(enchantedSword); // Utrusta enchanted sword
            System.out.println("You have equipped the Enchanted Sword with " + enchantedSword.getName() + " enchantment.");
        }

        // Använd Health Potion
        System.out.println("\nWould you like to use a Health Potion? (yes/no)");
        String usePotion = scanner.nextLine();
        if (usePotion.equalsIgnoreCase("yes")) {
            player.useItem(healthPotion); // Använd health potion
        }

        // Visa slutlig utrustning
        System.out.println("\nYour final equipment:");
        player.getEquipment().displayEquipment(); // Visa spelarens utrustning
    }
}
