package Weapons;

public class Hammer extends Weapon
{
    private static final String NAME = "Hammer";

    public Hammer()
    {
        super();
        this.name = Hammer.NAME;
        this.damage = super.getDamage()*4;
        this.price = super.getPrice()*4;
    }
}
