

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        Game game = new Game(player);

        EnchantedSword enchantedSword = new EnchantedSword("Enchanted Sword", "Magic sword", 45, Material.STEEL, Quality.AVARAGE, 190);
        player.pickUpItem(enchantedSword, 1);
        player.getInventory().display();
        player.equip(enchantedSword);
        Enemy enemy = new Enemy(100);
        game.addEnemy(enemy);
        player.performAttack(AttackType.LIGHT,enemy);
        player.performAttack(AttackType.HEAVY,enemy);
        player.getEquipment().displayEquipment();
        player.throwItem(enchantedSword);
        player.getInventory().display();

    }
}
