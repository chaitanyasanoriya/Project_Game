package weapons;

public class Baton extends Weapon
{
    private final static String NAME = "Baton";

    public Baton()
    {
        super();
        this.name = Baton.NAME;
        this.damage = super.getDamage()*2;
        this.price = super.getPrice()*2;
    }
}
