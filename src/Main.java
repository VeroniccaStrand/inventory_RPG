public class Main {
    public static void main(String[] args) {
        // Skapa en spelare
        Player player = new Player();

        // Skapa några objekt
        EnchantedSword enchantedSword = new EnchantedSword("Enchanted Sword", "A sword imbued with magic", 250, Material.STEEL, Quality.EXELLENT, 50);
        Bow bow = new Bow("Longbow", "A powerful longbow", 150, Material.WOOD, Quality.EXELLENT, 30);
        Potion healthPotion = new Potion("Health Potion", "Restores health", 20, PotionType.HEALTH);
        Boots leatherBoots = new Boots("Leather Boots", "Provides light protection", 50, Material.LEATHER, Quality.AVARAGE, 10);
        Arrow   steelArrow = new Arrow("steel arrow","piercing arrows",35,Material.WOOD,Quality.EXELLENT );
        // Lägg till objekt i spelarens inventory
        player.getInventory().addItem(enchantedSword, 1);
        player.getInventory().addItem(bow, 1);
        player.getInventory().addItem(healthPotion, 5);
        player.getInventory().addItem(leatherBoots, 1);
        player.getInventory().addItem(steelArrow, 100);

        // Visa spelarens inventory
        System.out.println("Player's Inventory:");
        player.displayInventory();

        // Utrusta Enchanted Sword och skorna
        player.equip(enchantedSword);
        player.equip(leatherBoots);
        System.out.println("Player's Inventory:");
        player.displayInventory();
        // Förtrolla Enchanted Sword
        player.enchantWeapon(Enchantment.FIRE);  // Lägg till denna rad för att förtrolla svärdet
        player.useItem(healthPotion);
        // Utför en lätt, tung och magisk attack med Enchanted Sword
        System.out.println("\nPerforming attacks with Enchanted Sword:");
        player.performAttack("light");  // Lätt attack
        player.performAttack("heavy");  // Tung attack
        player.performAttack("magic");  // Magisk attack

        player.equip(bow);
        player.equip(steelArrow);

        // Utför en tung attack med Bow
        System.out.println("\nPerforming heavy attack with Bow:");
        player.performAttack("heavy");
        player.performAttack("magic");

        player.displayInventory();
    }
}
