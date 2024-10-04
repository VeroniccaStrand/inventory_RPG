public class EnchantedSword extends Weapon implements Enchantable, MagicAttack, HeavyAttack, LightAttack{
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

    public void performMagicAttack() {
        if (enchantment != null) {

            System.out.println(getName() + " slashes with " + enchantment.getName() + " magic, dealing " + getWeaponDamage() * 2 + " damage!");
        } else {
            System.out.println(getName() + " has no enchantment.");
        }

}



    @Override
    public void performHeavyAttack() {
        System.out.println(getName() + " slashes with " + enchantment.getName() + " magic, dealing " + getWeaponDamage() + " damage!");

    }

    @Override
    public void performLightAttack() {
        System.out.println(getName() + " slashes with " + enchantment.getName() + " magic, dealing " + getWeaponDamage() / 2 + " damage!");

    }
}
