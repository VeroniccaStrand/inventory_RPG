

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Item, Integer> items;
    private int maxCapacity;

    public Inventory(int initialCapacity) {
        this.items = new HashMap<>(initialCapacity);
        this.maxCapacity = initialCapacity;
    }

    public boolean contains(Item item) {
        return items.containsKey(item);
    }

    public void addItem(Item item, int quantity) {
        if (items.size() >= maxCapacity && !item.isStackable()) {
            System.out.println("Inventory is full.");
            return;
        }

        if (item.isStackable()) {
            items.put(item, items.getOrDefault(item, 0) + quantity);

        } else {
            items.put(item, 1);

        }
    }
    public boolean removeItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            int currentQuantity = items.get(item);
            if (item.isStackable()) {
                if (currentQuantity >= quantity) {
                    if (currentQuantity == quantity) {
                        items.remove(item);
                    } else {
                        items.put(item, currentQuantity - quantity);
                    }
                    System.out.println("Removed " + quantity + " of " + item.getName());
                    return true;
                } else {
                    System.out.println("Not enough items to remove.");
                    return false;
                }
            } else {
                items.remove(item);
                System.out.println("Removed item: " + item.getName());
                return true;
            }
        } else {
            System.out.println("Item not found");
            return false;
        }
    }
    public void display() {
        System.out.println("Inventory:");
        if (items != null && !items.isEmpty()) {
            for (Map.Entry<Item, Integer> entry : items.entrySet()) {
                System.out.println(entry.getKey().getName() + " : " + entry.getValue());
            }
        }else{
                System.out.println("No items found");
            }
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public <T> T findItemByType(Class<T> type) {
        for (Item item : items.keySet()) {
            if (type.isInstance(item)) {
                return type.cast(item);
            }
        }
        return null;
    }

    public int getItemQuantity(Item item) {
        return items.getOrDefault(item, 0);
    }
}
