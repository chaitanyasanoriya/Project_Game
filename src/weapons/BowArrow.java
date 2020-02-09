package weapons;

public class BowArrow extends Weapon
{
    private static final String NAME = "Bow & Arrow";

    public BowArrow()
    {
        super();
        this.name = BowArrow.NAME;
        this.damage = super.getDamage()*6;
        this.price = super.getPrice()*6;
    }
}
