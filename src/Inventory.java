import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Item, Integer> items;
    private int maxCapacity;

    public Inventory(int initialCapacity) {
        this.items = new HashMap<Item, Integer>(initialCapacity);
        this.maxCapacity = initialCapacity;
    }
    public void addItem(Item item, int quantity) {
        if (item.isStackable()) {
            items.put(item, items.getOrDefault(item, 0) + quantity);
            System.out.println("Added " + quantity + " of " + item.getName());
        } else {
            if (items.size() < maxCapacity) {
                items.put(item, 1);
                System.out.println("added item: " + item.getName());


            } else {
                System.out.println("inventory is full");

            }

        }
    }
    public boolean removeItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            int currentQuantity = items.get(item);
            if (item.isStackable()) {
                if (currentQuantity >= quantity) {
                    if (currentQuantity == quantity) {
                        items.remove(item);  // Ta bort item om kvantiteten når 0
                    } else {
                        items.put(item, currentQuantity - quantity);  // Minska kvantiteten
                    }
                    System.out.println("Removed " + quantity + " of " + item.getName());
                    return true;
                } else {
                    System.out.println("Not enough items to remove.");
                    return false;
                }
            } else {
                items.remove(item);  // För icke-stapelbara föremål, ta bara bort det
                System.out.println("Removed item: " + item.getName());
                return true;
            }
        } else {
            System.out.println("Item not found");
            return false;
        }
    }
    public void display() {
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            System.out.println(entry.getKey().getName() + " : " + entry.getValue());

        }
    }
    public Map<Item, Integer> getItems() {
        return items;
    }


    public int getItemQuantity(Item item) {
        return items.getOrDefault(item, 0);
    }
}
