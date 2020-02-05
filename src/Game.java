import java.util.ArrayList;

public class Game
{
    private static final int RANDOM_MOVE = 100;

    public void run()
    {
        ArrayList<Team> teamsArrayList = createTeams();
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------\n");
        while (areTwoAlive(teamsArrayList))
        {
            moveTeams(teamsArrayList);
            checkForConflicts(teamsArrayList);
        }
        Team team = getVictoriousTeam(teamsArrayList);
        Player player = getHighestScorePlayer(teamsArrayList);
        printWinners(team,player);
    }

    private void printWinners(Team team, Player player)
    {
        System.out.println(team.getmColor() + "-------------------------------------------------------------------------------------------------------------------\n" +
                "\t\t\t\t\t\t" + team.getmName() + " Emerged Victorious Among All with Team Score of " + team.getmScore() + " (Remaining soldiers: " + team.getNumberOfAliveSoldiers() + ")\n" +
                Utilities.ANSI_RESET + player.getmTeam().getmColor() + "\t\t\t\t\t\t\t" + player.getmName() + " of " + player.getmTeam().getmName() + " has the highest score among all with " +
                player.getmScore() + " points" + team.getmColor() +
                "\n-------------------------------------------------------------------------------------------------------------------" + Utilities.ANSI_RESET);
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
        for (Team team : teamsArrayList)
        {
            if (!team.isLost())
            {
                x = Utilities.getRandom().nextInt(Game.RANDOM_MOVE);
                y = Utilities.getRandom().nextInt(Game.RANDOM_MOVE);
                if (team.getmX() - x >= 0)
                {
                    team.setmX(team.getmX() - x);
                }
                if (team.getmY() - y >= 0)
                {
                    team.setmY(team.getmY() - y);
                }
                team.printLocation();
            }
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
                    printConflictDetected(team,team1);
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
        System.out.println("\n\n-------------------------------------------------------------------------------------------------------------------\n" +
                "\t\t\t\t\t\t" + team.getmColor() + team.getmName() + Utilities.ANSI_RESET + " and " + team1.getmColor() + team1.getmName() + Utilities.ANSI_RESET +
                " are Conflicted and Starting to Battle\n" +
                "-------------------------------------------------------------------------------------------------------------------");
    }

    private void battle(Team team, Team team1)
    {
        Player[] players = new Player[2];
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
        }
        findVictoriousTeamAmongBattle(team, team1);
    }

    private void findVictoriousTeamAmongBattle(Team team, Team team1)
    {
        System.out.print("");
        int revived_players;
        if (team.isLost())
        {
            System.out.print(team1.getmColor() + "------------------------------------------------------------------------------------------------------------------- \n" +
                    "\t\t\t\t\t\tMatch over - Winner: " + team1.getmName() + " (remaining soldiers: " + team1.getNumberOfAliveSoldiers() + ")");
            revived_players = team1.revivePlayers();
            System.out.print("\n\t\t\t\t\t\t\t\t" + team1.getmName() + " has revived " + revived_players + " players " +
                    "\n-------------------------------------------------------------------------------------------------------------------\n\n" + Utilities.ANSI_RESET);
        }
        if (team1.isLost())
        {
            System.out.print(team.getmColor() + "------------------------------------------------------------------------------------------------------------------- \n" +
                    "\t\t\t\t\t\tMatch over - Winner: " + team.getmName() + " (remaining soldiers: " + team.getNumberOfAliveSoldiers() + ")");
            revived_players = team.revivePlayers();
            System.out.print("\n\t\t\t\t\t\t\t\t" + team.getmName() + " has revived " + revived_players + " players " +
                    "\n-------------------------------------------------------------------------------------------------------------------\n\n" + Utilities.ANSI_RESET);
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
