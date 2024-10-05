public enum AttackType {
    LIGHT(1.0),  // Bas-skada
    HEAVY(1.5),  // 50% mer skada
    MAGIC(2.0);  // Dubbla skadan

    private final double multiplier;

    AttackType(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}