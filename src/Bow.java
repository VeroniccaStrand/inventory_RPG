public class Bow extends Weapon implements HeavyAttack{
    private final int baseDamage;
    private final int weaponDamage;


    public Bow(String name, String description, int value, Material material, Quality quality, int baseDamage) {
        super(name, description, value, material, quality);
        this.baseDamage = baseDamage;
        this.weaponDamage = calculateWeaponDamage();
    }



    @Override
    public int calculateWeaponDamage() {
        return baseDamage + getMaterial().getMaterialBonus() + getQuality().getQualityBonus();
    }
    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void performHeavyAttack() {

        Ammo equippedAmmo = Equipment.getEquippedAmmo();  // Hämta utrustad ammo
        if (!(equippedAmmo instanceof Arrow equippedArrow)) {
            System.out.println("No Arrows equipped");
        } else {

            int totalDamage = getWeaponDamage() + equippedArrow.getAmmoDamage();  // Lägg till ammo damage
            System.out.println("Total Damage: " + totalDamage);
        }
    }
}
