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
        if (item instanceof Stackable) {
            items.put(item, items.getOrDefault(item, 0) + quantity);
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
        if(items.containsKey(item)) {
            int currentQuantity = items.get(item);
            if(item instanceof Stackable) {
                if(currentQuantity > quantity) {
                    items.put(item, currentQuantity - quantity);
                    if(items.get(item) > 0) {
                        items.remove(item);
                    }
                    return true;
                }else{
                    return false;
                }
            }else{
                items.remove(item);
                return true;
            }
        }else {
            System.out.println("item not found");
            return false;
        }
    }
    public void display() {
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            System.out.println(entry.getKey().getName() + " : " + entry.getValue());

        }
    }

}
