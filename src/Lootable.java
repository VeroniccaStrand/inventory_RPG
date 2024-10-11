import java.util.List;

public interface Lootable {
    Inventory getInventory();
    List<Item> dropLoot();
}
