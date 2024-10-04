public enum PotionType {
    HEALTH(100),
    STAMINA(100);

    private final int increaseAmount;

    PotionType(int increaseAmount) {
        this.increaseAmount = increaseAmount;
    }
    public int getIncreaseAmount() {
        return increaseAmount;
    }
}
