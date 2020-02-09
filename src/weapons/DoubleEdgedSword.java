package weapons;

public class DoubleEdgedSword extends Weapon
{
    private final static String NAME = "Double Edged Sword";
    public DoubleEdgedSword()
    {
        super();
        this.name = DoubleEdgedSword.NAME;
        this.damage = super.getDamage()*10;
        this.price = super.getPrice()*10;
    }
}
