public enum Quality {
    POOR(5),
    AVARAGE(15),
    EXELLENT(25);

    private final int qualityBonus;

    Quality(int qualityBonus) {
        this.qualityBonus = qualityBonus;
    }
    public int getQualityBonus() {
        return qualityBonus;
    }
}

