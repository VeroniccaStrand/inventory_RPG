public class Bow extends Weapon implements RangeAttacker {
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

    // Gemensam metod för att hantera alla attacker med ammo och AttackType
    private void performAttack(Ammo equippedAmmo, AttackType attackType) {
        if (equippedAmmo != null) {
            int baseWeaponDamage = calculateWeaponDamage();
            double multiplier = attackType.getMultiplier();  // Hämta multiplikator från AttackType
            int totalDamage = (int) ((baseWeaponDamage + equippedAmmo.getAmmoDamage()) * multiplier);
            System.out.println("Performing " + attackType + " attack with " + equippedAmmo.getName() + ". Total damage: " + totalDamage);
        } else {
            System.out.println("No ammo equipped for " + attackType + " attack.");
        }
    }

    @Override
    public void performLightAttack(Ammo equippedAmmo) {
        performAttack(equippedAmmo, AttackType.LIGHT);  // Använd LIGHT attack
    }

    @Override
    public void performHeavyAttack(Ammo equippedAmmo) {
        performAttack(equippedAmmo, AttackType.HEAVY);  // Använd HEAVY attack
    }

    @Override
    public void performMagicAttack(Ammo equippedAmmo) {
        performAttack(equippedAmmo, AttackType.MAGIC);  // Använd MAGIC attack
    }
}