public class EnchantedSword extends Weapon implements Enchantable{
    private Enchantment enchantment;
    private final int baseDamage;
    private final int weaponDamage;

    public EnchantedSword(String name, String description, int value, Material material, Quality quality, int baseDamage) {
        super(name, description, value, material, quality);
        this.baseDamage = baseDamage;
        this.weaponDamage = calculateWeaponDamage();
    }
    public int getWeaponDamage() {
        return weaponDamage;
    }

    @Override
    public void setEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
        System.out.println(getName() + " is now enchanted with " + enchantment.getName());
    }

    @Override
    public Enchantment getEnchantment() {
        return enchantment;
    }

    @Override
    public int calculateWeaponDamage() {
        // Kolla om enchantment är null, annars sätt bonus till 0
        int enchantmentBonus = (enchantment != null) ? enchantment.getDamageBonus() : 0;
        return baseDamage + enchantmentBonus + getQuality().getQualityBonus() + getMaterial().getMaterialBonus();
    }

    public int performAttack(AttackType attackType) {
        // Beräkna den totala skadan baserat på attacktypens multiplikator
        double totalDamage = calculateWeaponDamage() * attackType.getMultiplier();
        return (int) totalDamage;
    }
    @Override
    public int lightAttack() {
        return performAttack(AttackType.LIGHT) ;
    }

    @Override
    public int heavyAttack() {
        return performAttack(AttackType.HEAVY);
    }






}
