
    public interface RangeAttacker {

        // Metod för lätt attack med ammo
        void performLightAttack(Ammo equippedAmmo);

        // Metod för tung attack med ammo
        void performHeavyAttack(Ammo equippedAmmo);

        // Metod för magisk attack med ammo
        void performMagicAttack(Ammo equippedAmmo);
    }

