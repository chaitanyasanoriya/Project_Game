import java.util.ArrayList;

public class Team
{
    private String mName;
    private String mColor;
    private int mScore;
    private ArrayList<Player> mPlayerArrayList;
    private int mX;
    private int mY;
    private int mRadius;

    private static final int MINIMUM_NUMBER_OF_PLAYERS = 1000;
    private static final int VARIABLE_NUMBER_OF_PLAYERS = 1000;
    private static final int MINIMUM_LOCATION_CRITERIA = 10000;
    private static final int VARIABLE_LOCATION_CRITERIA = 1000000;
    private static final int RADIUS_PER_PLAYER = 10;
    private static final int COST_TO_REVIVE_A_PLAYER = 1000;
    private static final float MAX_HEALTH = 100;

    public Team(String mName, String mColor)
    {
        this.mName = mName;
        this.mColor = mColor;
        this.mScore = 0;
        int n = Utilities.getRandom().nextInt(Team.VARIABLE_NUMBER_OF_PLAYERS) + Team.MINIMUM_NUMBER_OF_PLAYERS;
        mPlayerArrayList = new ArrayList<>();
        for (int i = 0; i < n; i++)
        {
            mPlayerArrayList.add(new Player("Player " + i, this));
        }
        this.mX = Utilities.getRandom().nextInt(Team.VARIABLE_LOCATION_CRITERIA) + Team.MINIMUM_LOCATION_CRITERIA;
        this.mY = Utilities.getRandom().nextInt(Team.VARIABLE_LOCATION_CRITERIA) + Team.MINIMUM_LOCATION_CRITERIA;
        this.mRadius = n * Team.RADIUS_PER_PLAYER;
        System.out.println(this.mColor + this.mName + " is ready with " + n + " soldiers at (" + this.mX + "," + this.mY + ") with a radius " + this.mRadius + Utilities.ANSI_RESET);
    }

    public String getmName()
    {
        return mName;
    }

    public String getmColor()
    {
        return mColor;
    }

    public int getmScore()
    {
        return mScore;
    }

    public ArrayList<Player> getmPlayerArrayList()
    {
        return mPlayerArrayList;
    }

    public int getmX()
    {
        return mX;
    }

    public int getmY()
    {
        return mY;
    }

    public int getmRadius()
    {
        return mRadius;
    }

    public void setmX(int mX)
    {
        this.mX = mX;
    }

    public void setmY(int mY)
    {
        this.mY = mY;
    }

    public void setmRadius(int mRadius)
    {
        this.mRadius = mRadius;
    }

    public void setmScore(int mScore)
    {
        this.mScore = mScore;
    }

    public boolean isLost()
    {
        boolean result = true;
        for (Player player : this.mPlayerArrayList)
        {
            if (player.getHealth() > 0)
            {
                result = false;
                break;
            }
        }
        return result;
    }

    public void printLocation()
    {
        System.out.println(this.mColor+this.mName+" moved to ("+this.mX+","+this.mY+")"+Utilities.ANSI_RESET);
    }

    public int getNumberOfAliveSoldiers()
    {
        int count = 0;
        for(Player player : this.mPlayerArrayList)
        {
            if(!player.isKilled())
            {
                count++;
            }
        }
        return count;
    }

    public void increaseTeamScoreBy(int score)
    {
        this.setmScore(this.mScore + score);
    }

    public int revivePlayers()
    {
        int count = 0;
        int i=0;
        while(this.mScore>Team.COST_TO_REVIVE_A_PLAYER && i<this.mPlayerArrayList.size())
        {
            if(this.mPlayerArrayList.get(i).isKilled())
            {
                this.mPlayerArrayList.get(i).setmHealth(Team.MAX_HEALTH);
                this.mScore = this.mScore - Team.COST_TO_REVIVE_A_PLAYER;
                count++;
            }
            i++;
        }
        this.mRadius = this.getNumberOfAliveSoldiers() * Team.RADIUS_PER_PLAYER;
        return count;
    }
}
