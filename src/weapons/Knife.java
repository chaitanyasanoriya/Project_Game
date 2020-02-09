package weapons;

public class Knife extends Weapon
{
    private static final String NAME = "Knife";
    public Knife()
    {
        super();
        this.name = Knife.NAME;
        this.damage = super.getDamage()*3;
        this.price = super.getPrice()*3;
    }
}
