import audio.GameAudio;
import filehanlding.FileHandling;
import filehanlding.FileHandlingException;
import graphics.GameGraphics;
import models.Circle;
import utilities.Utilities;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Game
{
    private static final int RANDOM_MOVE = 2;
    private GameGraphics mGameGraphics;
    private ArrayList<Circle> mCircleArrayList;
    public void run()
    {
        GameAudio.startGameTheme();
        invokeFileHandling();
        GameAudio.initializeTTS();
        startJFrame();
        ArrayList<Team> teamsArrayList = createTeams();
        pause();
        System.out.println("-------------------------------------------------------------------------------------------------------------------\n");
        while (areTwoAlive(teamsArrayList))
        {
            moveTeams(teamsArrayList);
            checkForConflicts(teamsArrayList);
        }
        printWinners(teamsArrayList);
        endFileHandling();
    }

    private void startJFrame()
    {
        JFrame frame = new JFrame();
        mGameGraphics = new GameGraphics();
        frame.add(mGameGraphics);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        mCircleArrayList = new ArrayList<>();
    }

    private void endFileHandling()
    {
        try
        {
            FileHandling.closeFileWriterObject();
        } catch (IOException e)
        {
            System.out.println("There might be a problem with the Game File");
        }
    }

    private void invokeFileHandling()
    {
        try
        {
            FileHandling.createNewFile();
            FileHandling.createFileWriterObject();
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("There might be a problem with the Game File");
        }
    }

    private void pause()
    {
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private void pauseSmall()
    {
        try
        {
            Thread.sleep(1);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private void printWinners(ArrayList<Team> teamsArrayList)
    {
        Team team = getVictoriousTeam(teamsArrayList);
        Player player = getHighestScorePlayer(teamsArrayList);
        mCircleArrayList.clear();
        mCircleArrayList.add(new Circle(team.getmX(), team.getmY(), team.getmRadius()*2, Utilities.getColor(team.getmColor()),team.getmName()));
        //mGameGraphics.setCircles(mCircleArrayList);
        String msg = "-------------------------------------------------------------------------------------------------------------------\n" +
                "\t\t" + team.getmName() + " Emerged Victorious Among All with Team Score of " + team.getmScore() + " (Remaining soldiers: " +
                team.getNumberOfAliveSoldiers() + ")\n\t\t\t" + player.getmName() + " of " +
                player.getmTeam().getmName() + " has the highest score among all with " + player.getmScore() + " points" +
                "\n-------------------------------------------------------------------------------------------------------------------";
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
        System.out.println(team.getmColor() + "-------------------------------------------------------------------------------------------------------------------\n" +
                "\t\t\t" + team.getmName() + " Emerged Victorious Among All with Team Score of " + team.getmScore() + " (Remaining soldiers: " +
                team.getNumberOfAliveSoldiers() + ")\n" + Utilities.ANSI_RESET + player.getmTeam().getmColor() + "\t\t\t\t\t\t\t" + player.getmName() + " of " +
                player.getmTeam().getmName() + " has the highest score among all with " + player.getmScore() + " points" + team.getmColor() +
                "\n-------------------------------------------------------------------------------------------------------------------" + Utilities.ANSI_RESET);
        msg = msg.replace("-","");
        msg = msg.replace("\n","<br/>");
        msg = "<HTML>" + msg + "</HTML>";
        mGameGraphics.setWinner(mCircleArrayList,msg);
    }

    private Player getHighestScorePlayer(ArrayList<Team> teamsArrayList)
    {
        Player player = null;
        ArrayList<Player> playerArrayList;
        for (int i = 0; i < teamsArrayList.size(); i++)
        {
            playerArrayList = teamsArrayList.get(i).getmPlayerArrayList();
            for (int j = 0; j < playerArrayList.size(); j++)
            {
                if (i == 0 && j == 0)
                {
                    player = playerArrayList.get(j);
                }
                if (player.getmScore() < playerArrayList.get(j).getmScore())
                {
                    player = playerArrayList.get(j);
                }
            }
        }
        return player;
    }

    private Team getVictoriousTeam(ArrayList<Team> teamsArrayList)
    {
        Team team = null;
        for (Team team1 : teamsArrayList)
        {
            if (!team1.isLost())
            {
                team = team1;
                break;
            }
        }
        return team;
    }

    private boolean circle(double x1, double y1, double x2, double y2, double r1, double r2)
    {
        double distSq = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        double radSumSq = (r1 + r2) * (r1 + r2);
        if (distSq > radSumSq)
        {
            return false;
        } else
        {
            return true;
        }
    }

    private void moveTeams(ArrayList<Team> teamsArrayList)
    {
        int x;
        int y;
        mCircleArrayList.clear();
        for (Team team : teamsArrayList)
        {
            if (!team.isLost())
            {
                x = Utilities.getRandom().nextInt(Game.RANDOM_MOVE);
                y = Utilities.getRandom().nextInt(Game.RANDOM_MOVE);
                if (team.getmX() - x >= 100)
                {
                    team.setmX(team.getmX() - x);
                }
                if (team.getmY() - y >= 100)
                {
                    team.setmY(team.getmY() - y);
                }
                mCircleArrayList.add(new Circle(team.getmX(), team.getmY(), team.getmRadius()*2, Utilities.getColor(team.getmColor()),team.getmName()));
                team.printLocation();
            }
        }
        mGameGraphics.setCircles(mCircleArrayList);
        try
        {
            Thread.sleep(10);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private boolean areTwoAlive(ArrayList<Team> teamsArrayList)
    {
        int num_alive = 0;
        boolean result = false;
        for (Team team : teamsArrayList)
        {
            if (!team.isLost())
            {
                num_alive++;
            }
        }
        if (num_alive > 1)
        {
            result = true;
        }
        return result;
    }

    private ArrayList<Team> createTeams()
    {
        ArrayList<Team> teamArrayList = new ArrayList<>();
        teamArrayList.add(new Team("Team 1", Utilities.ANSI_YELLOW));
        teamArrayList.add(new Team("Team 2", Utilities.ANSI_RED));
        teamArrayList.add(new Team("Team 3", Utilities.ANSI_BLUE));
        teamArrayList.add(new Team("Team 4", Utilities.ANSI_GREEN));
        teamArrayList.add(new Team("Team 5", Utilities.ANSI_PURPLE));
        for (Team team : teamArrayList)
        {
            mCircleArrayList.add(new Circle(team.getmX(), team.getmY(), team.getmRadius()*2, Utilities.getColor(team.getmColor()),team.getmName()));
        }
        mGameGraphics.setCircles(mCircleArrayList);
        return teamArrayList;
    }

    private void checkForConflicts(ArrayList<Team> teamsArrayList)
    {
        Team team, team1;
        for (int i = 0; i < teamsArrayList.size(); i++)
        {
            team = teamsArrayList.get(i);
            for (int j = 0; j < teamsArrayList.size(); j++)
            {
                team1 = teamsArrayList.get(j);
                if (i != j && !team.isLost() && !team1.isLost() && checkForRadiusConflicts(teamsArrayList.get(i), teamsArrayList.get(j)))
                {
                    GameAudio.initialize();
                    GameAudio.playConflictSound();
                    pause();
                    printConflictDetected(team, team1);
                    battle(teamsArrayList.get(i), teamsArrayList.get(j));
                    try
                    {
                        Thread.sleep(2000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void printConflictDetected(Team team, Team team1)
    {
        String msg = "\n\n-------------------------------------------------------------------------------------------------------------------\n" +
                "\t\t\t" + team.getmName() + " and " + team1.getmName() + " are Conflicted and Starting to Battle\n" +
                "-------------------------------------------------------------------------------------------------------------------";
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
        GameAudio.sayTTS(msg);
        System.out.println("\n\n-------------------------------------------------------------------------------------------------------------------\n" +
                "\t\t\t\t\t\t" + team.getmColor() + team.getmName() + Utilities.ANSI_RESET + " and " + team1.getmColor() + team1.getmName() + Utilities.ANSI_RESET +
                " are Conflicted and Starting to Battle\n" +
                "-------------------------------------------------------------------------------------------------------------------");
    }

    private void battle(Team team, Team team1)
    {
        Player[] players = new Player[2];
        GameAudio.playCombatSound();
        while (!team.isLost() && !team1.isLost())
        {
            players[0] = team.getmPlayerArrayList().get(Utilities.getRandom().nextInt(team.getmPlayerArrayList().size()));
            while (players[0].isKilled())
            {
                players[0] = team.getmPlayerArrayList().get(Utilities.getRandom().nextInt(team.getmPlayerArrayList().size()));
            }
            players[1] = team1.getmPlayerArrayList().get(Utilities.getRandom().nextInt(team1.getmPlayerArrayList().size()));
            while (players[1].isKilled())
            {
                players[1] = team1.getmPlayerArrayList().get(Utilities.getRandom().nextInt(team1.getmPlayerArrayList().size()));
            }
            fight(players);
            pauseSmall();
        }
        GameAudio.stopCombatSound();
        findVictoriousTeamAmongBattle(team, team1);
    }

    private void findVictoriousTeamAmongBattle(Team team, Team team1)
    {
        int revived_players;
        String msg;
        if (team.isLost())
        {
            msg = "------------------------------------------------------------------------------------------------------------------- \n" +
                    "\t\t\tMatch over - Winner: " + team1.getmName() + " (remaining soldiers: " + team1.getNumberOfAliveSoldiers() + ")";
            System.out.print(team1.getmColor() + "------------------------------------------------------------------------------------------------------------------- \n" +
                    "\t\t\t\t\t\tMatch over - Winner: " + team1.getmName() + " (remaining soldiers: " + team1.getNumberOfAliveSoldiers() + ")");
            revived_players = team1.revivePlayers();
            msg = msg + "\n\t\t\t\t" + team1.getmName() + " has revived " + revived_players + " players " +
                    "\n-------------------------------------------------------------------------------------------------------------------\n\n";
            System.out.print("\n\t\t\t\t\t\t\t\t" + team1.getmName() + " has revived " + revived_players + " players " +
                    "\n-------------------------------------------------------------------------------------------------------------------\n\n" + Utilities.ANSI_RESET);
        } else
        {
            msg = "------------------------------------------------------------------------------------------------------------------- \n" +
                    "\t\t\tMatch over - Winner: " + team.getmName() + " (remaining soldiers: " + team.getNumberOfAliveSoldiers() + ")";
            System.out.print(team.getmColor() + "------------------------------------------------------------------------------------------------------------------- \n" +
                    "\t\t\t\t\t\tMatch over - Winner: " + team.getmName() + " (remaining soldiers: " + team.getNumberOfAliveSoldiers() + ")");
            revived_players = team.revivePlayers();
            msg = msg + "\n\t\t\t\t" + team.getmName() + " has revived " + revived_players + " players " +
                    "\n-------------------------------------------------------------------------------------------------------------------\n\n";
            System.out.print("\n\t\t\t\t\t\t\t\t" + team.getmName() + " has revived " + revived_players + " players " +
                    "\n-------------------------------------------------------------------------------------------------------------------\n\n" + Utilities.ANSI_RESET);
        }
        GameAudio.sayTTS(msg);
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
    }

    private void fight(Player[] players)
    {
        boolean attack;
        while (!players[0].isKilled() && !players[1].isKilled())
        {
            attack = Utilities.getRandom().nextBoolean();
            if (attack)
            {
                players[0].attack(players[1]);
            } else
            {
                players[1].attack(players[0]);
            }
        }
    }

    private boolean checkForRadiusConflicts(Team team, Team team1)
    {
        boolean result = false;
        if (circle(team.getmX(), team.getmY(), team1.getmX(), team1.getmY(), team.getmRadius(), team1.getmRadius()))
        {
            result = true;
        }
        return result;
    }
}
