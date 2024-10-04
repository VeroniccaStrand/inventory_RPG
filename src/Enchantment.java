public enum Enchantment {
    FIRE("Fire", 10),
    ICE("Ice", 8),
    LIGHTNING("Lightning", 12);

    private final String name;
    private final int damageBonus;

    Enchantment(String name, int damageBonus) {
        this.name = name;
        this.damageBonus = damageBonus;
    }

    public String getName() {
        return name;
    }

    public int getDamageBonus() {
        return damageBonus;
    }
}
