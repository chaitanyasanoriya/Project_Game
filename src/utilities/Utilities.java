package utilities;

import weapons.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Utilities
{
    private static ArrayList<Weapon> weaponArrayList;
    private static Random random;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    static
    {
        weaponArrayList = new ArrayList<>();
        weaponArrayList.add(new Hands());
        weaponArrayList.add(new Baton());
        weaponArrayList.add(new Knife());
        weaponArrayList.add(new Hammer());
        weaponArrayList.add(new Dagger());
        weaponArrayList.add(new BowArrow());
        weaponArrayList.add(new Spear());
        weaponArrayList.add(new Axe());
        weaponArrayList.add(new Sword());
        weaponArrayList.add(new DoubleEdgedSword());
        weaponArrayList.add(new CrossBow());
        Utilities.random = new Random();
    }

    public static ArrayList<Weapon> getWeaponArrayList()
    {
        return weaponArrayList;
    }

    public static Random getRandom()
    {
        return random;
    }

    public static Color getColor(String str)
    {
        Color return_color;
        if (str.equals(Utilities.ANSI_BLUE))
        {
            return_color = Color.BLUE;
        } else if (str.equals(Utilities.ANSI_RED))
        {
            return_color = Color.RED;
        } else if (str.equals(Utilities.ANSI_GREEN))
        {
            return_color = Color.GREEN;
        } else if (str.equals(Utilities.ANSI_YELLOW))
        {
            return_color = Color.YELLOW;
        } else
        {
            return_color = Color.PINK;
        }
        return return_color;
    }

}
