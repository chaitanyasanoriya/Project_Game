package Weapons;

public class Spear extends Weapon
{
    private static final String NAME = "Spear";
    public Spear()
    {
        super();
        this.name = Spear.NAME;
        this.damage = super.getDamage()*7;
        this.price = super.getPrice()*7;
    }
}
