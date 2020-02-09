package weapons;

public class CrossBow extends Weapon
{
    private final static String NAME = "Crossbow";

    public CrossBow()
    {
        super();
        this.name = CrossBow.NAME;
        this.damage = super.getDamage()*11;
        this.price = super.getPrice()*11;
    }
}
