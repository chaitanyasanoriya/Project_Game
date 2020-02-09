package weapons;

public class Axe extends Weapon
{
    private static final String NAME = "Axe";

    public Axe()
    {
        super();
        this.name = Axe.NAME;
        this.damage = super.getDamage()*8;
        this.price = super.getPrice()*8;
    }
}
