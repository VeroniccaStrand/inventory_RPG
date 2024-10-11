public enum Quality {
    POOR(5),
    AVERAGE(15),
    EXCELLENT(25);

    private final int qualityBonus;

    Quality(int qualityBonus) {
        this.qualityBonus = qualityBonus;
    }
    public int getQualityBonus() {


        return qualityBonus;
    }
}

