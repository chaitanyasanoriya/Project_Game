package weapons;

public class Dagger extends Weapon
{
    private final static String NAME = "Dagger";
    public Dagger()
    {
        this.name = Dagger.NAME;
        this.damage = super.getDamage()*5;
        this.price = super.getPrice()*5;
    }
}
