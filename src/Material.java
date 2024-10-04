public enum Material {
    LEATHER (5),
    IRON (10),
    WOOD(35),
    STEEL(15);

    private final int materialBonus;

    Material(int materialBonus) {
        this.materialBonus = materialBonus;
    }
    public int getMaterialBonus() {
        return materialBonus;
    }
}
