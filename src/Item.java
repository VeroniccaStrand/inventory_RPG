import java.util.Objects;

public abstract class Item {
   private String name;
   private String description;
   private int value;

   public Item(String name, String description, int value) {
       this.name = name;
       this.description = description;
       this.value = value;

   }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       Item item = (Item) o;
       return value == item.value &&
               name.equals(item.name) &&
               description.equals(item.description);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, description, value);
    }
}
