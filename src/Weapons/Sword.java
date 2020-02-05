package Weapons;

public class Sword extends Weapon
{
    private static final String NAME = "Sword";
    public Sword()
    {
        super();
        this.name = Sword.NAME;
        this.damage = super.getDamage()*9;
        this.price = super.getPrice()*9;
    }
}
