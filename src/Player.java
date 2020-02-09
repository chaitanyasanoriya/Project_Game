import filehanlding.FileHandling;
import filehanlding.FileHandlingException;
import utilities.Utilities;
import weapons.Weapon;

import java.io.IOException;

public class Player
{

    private float mHealth;
    private String mName;
    private int mRank;
    private int mScore;
    private Team mTeam;
    private int mSelectedWeapon;
    private Weapon mCurrentWeapon;

    private static final int SCORE_INCREASE = 10;
    private static final float MAX_HEALTH = 100;
    private static final int MINIMUM_RANK = 1;
    private static final int MINIMUM_SCORE = 0;
    private static final int INITIAL_SELECTED_WEAPON = 1;

    public int getSelectedWeapon()
    {
        return this.mSelectedWeapon;
    }

    public void setSelectedWeapon(int mSelectedWeapon)
    {
        this.mSelectedWeapon = mSelectedWeapon;
    }

    public Player(String mName, Team mTeam)
    {
        this.mName = mName;
        this.mTeam = mTeam;
        this.mHealth = Player.MAX_HEALTH;
        this.mRank = Player.MINIMUM_RANK;
        this.mScore = Player.MINIMUM_SCORE;
        this.mSelectedWeapon = Player.INITIAL_SELECTED_WEAPON;
        this.mCurrentWeapon = Utilities.getWeaponArrayList().get(this.mSelectedWeapon);
    }

    public int getScore()
    {
        return this.mScore;
    }

    public int setScore(int newScore)
    {
        this.mScore = newScore;
        return this.mScore;
    }

    public int setRank(int newRank)
    {
        this.mRank = newRank;
        return this.mRank;
    }

    public float getHealth()
    {
        return this.mHealth;
    }

    public float setHealth(float newHealth)
    {
        this.mHealth = newHealth;
        return newHealth;
    }

    public String getName()
    {
        return this.mName;
    }

    public String setName(String newName)
    {
        this.mName = newName;
        return this.mName;
    }

    public boolean isKilled()
    {
        boolean result = false;
        if (this.mHealth <= 0)
        {
            result = true;
        }
        return result;
    }

    public float getmHealth()
    {
        return mHealth;
    }

    public void setmHealth(float mHealth)
    {
        this.mHealth = mHealth;
    }

    public String getmName()
    {
        return mName;
    }

    public void setmName(String mName)
    {
        this.mName = mName;
    }

    public int getmRank()
    {
        return mRank;
    }

    public void setmRank(int mRank)
    {
        this.mRank = mRank;
    }

    public int getmScore()
    {
        return mScore;
    }

    public void setmScore(int mScore)
    {
        this.mScore = mScore;
    }

    public Team getmTeam()
    {
        return mTeam;
    }

    public void setmTeam(Team mTeam)
    {
        this.mTeam = mTeam;
    }

    public int getmSelectedWeapon()
    {
        return mSelectedWeapon;
    }

    public void setmSelectedWeapon(int mSelectedWeapon)
    {
        this.mSelectedWeapon = mSelectedWeapon;
    }

    public Weapon getmCurrentWeapon()
    {
        return mCurrentWeapon;
    }

    public void setmCurrentWeapon(Weapon mCurrentWeapon)
    {
        this.mCurrentWeapon = mCurrentWeapon;
    }

    public void attack(Player otherPlayer)
    {

        //My mHealth and enemy mHealth
        //System.out.println(this.getmTeam().getmColor()+this.getName() + " of  "+this.mTeam.getmName()+" is attacking " + otherPlayer.getName()+" of  "+otherPlayer.getmTeam().getmName()+Utilities.ANSI_RESET);
        int damage = this.mCurrentWeapon.getDamage();
        otherPlayer.setHealth(otherPlayer.getHealth() - ((damage) + Utilities.getRandom().nextInt(damage / 2)));
        if (otherPlayer.isKilled())
        {
            this.setScore(this.getScore() + otherPlayer.getmRank() * Player.SCORE_INCREASE);
            this.getmTeam().increaseTeamScoreBy(otherPlayer.getmRank() * Player.SCORE_INCREASE);
            String msg = this.getName() + " of " + this.mTeam.getmName() + " killed " + otherPlayer.getName() + " of " + otherPlayer.getmTeam().getmName();
            try
            {
                FileHandling.writeToFile(msg);
            } catch (FileHandlingException e)
            {
                System.out.println("There was a problem with writing in Game File");
            } catch (IOException e)
            {
                System.out.println("There might be a problem with the Game File");
            }
            System.out.println(this.getmTeam().getmColor() + this.getName() + " of " + this.mTeam.getmName() + " killed " + otherPlayer.getName() + " of " +
                    otherPlayer.getmTeam().getmName() + Utilities.ANSI_RESET);
            this.increaseRank(otherPlayer);
            this.upgradeHealthAndWeapon();
        }
    }

    public void increaseRank(Player otherPlayer)
    {
        if (otherPlayer.getmRank() == 1)
        {
            this.setRank(this.getmRank() + 1);
        } else
        {
            this.setRank(this.getmRank() + (otherPlayer.getmRank() / 2));
        }
        String msg = this.mName+" of "+this.mTeam.getmName()+" has upgraded their rank to "+this.mRank;
        try
        {
            FileHandling.writeToFile(msg);
        } catch (FileHandlingException e)
        {
            System.out.println("There was a problem with writing in Game File");
        } catch (IOException e)
        {
            System.out.println("There might be a problem with the Game File");
        }
        System.out.println(this.mTeam.getmColor()+msg+Utilities.ANSI_RESET);
    }

    public void upgradeHealthAndWeapon()
    {
        if (this.getHealth() < Player.MAX_HEALTH)
        {
            float diff = Player.MAX_HEALTH - (int) this.getHealth();
            if (diff <= this.getScore())
            {
                this.setHealth(Player.MAX_HEALTH);
                this.setScore(this.getScore() - (int) diff);
            } else
            {
                this.setHealth(this.getHealth() + this.getScore());
                this.setScore(Player.MINIMUM_SCORE);
            }
            String msg ="Health of " + this.getName() + " of " + this.getmTeam().getmName() + " had been increased to " + this.getHealth();
            try
            {
                FileHandling.writeToFile(msg);
            } catch (FileHandlingException e)
            {
                System.out.println("There was a problem with writing in Game File");
            } catch (IOException e)
            {
                System.out.println("There might be a problem with the Game File");
            }
            System.out.println(this.getmTeam().getmColor() + "Health of " + this.getName() + " of " + this.getmTeam().getmName() + " had been increased to " +
                    this.getHealth() + Utilities.ANSI_RESET);
        }
        if (this.mSelectedWeapon < Utilities.getWeaponArrayList().size() - 1 && Utilities.getWeaponArrayList().get(this.mSelectedWeapon + 1).getPrice() <= this.mScore)
        {
            for (int i = Utilities.getWeaponArrayList().size() - 1; i >= 1; i--)
            {
                if (this.mScore >= Utilities.getWeaponArrayList().get(i).getPrice())
                {
                    this.setSelectedWeapon(i);
                    this.setmCurrentWeapon(Utilities.getWeaponArrayList().get(this.mSelectedWeapon));
                    this.setScore(this.getScore() - Utilities.getWeaponArrayList().get(this.mSelectedWeapon).getPrice());
                    String msg = this.getName() + " of " + this.getmTeam().getmName() + " upgraded their weapon to " + this.getmCurrentWeapon().getName();
                    try
                    {
                        FileHandling.writeToFile(msg);
                    } catch (FileHandlingException e)
                    {
                        System.out.println("There was a problem with writing in Game File");
                    } catch (IOException e)
                    {
                        System.out.println("There might be a problem with the Game File");
                    }
                    System.out.println(this.getmTeam().getmColor() + this.getName() + " of " + this.getmTeam().getmName() + " upgraded their weapon to " +
                            this.getmCurrentWeapon().getName() + Utilities.ANSI_RESET);
                    break;
                }
            }

        }

    }
}
