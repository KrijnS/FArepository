package InitTeam;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import InitialModes.Main;

public class Competition {
	List<Team> teams = new ArrayList<>();
	static Scanner in;

	public Competition(List<Team> teams) {
		this.teams = teams;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public void display() {
		Scanner sc = new Scanner(System.in);

		String selection = new String("From which team would you like to view the selection?");

		System.out.println(selection);

		Competition competition = readCompetition();

		System.out.println("\n");

		for (int i = 0; i < competition.teams.size(); i++) {
			int count = i + 1;
			System.out.println(" [" + count + "] " + competition.getTeams().get(i).name);
		}

		System.out.println("\n");

		int choice = sc.nextInt() - 1;

		Team team = competition.getTeams().get(choice);

		team.display();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\n");
		Main.main();
	}

	public static Competition readCompetition() {
		List<Team> teams = new ArrayList<>();

		Competition x = new Competition(teams);

		try {
			in = new Scanner(new FileReader("C:/Users/Krijn/Downloads/Football App/Text files/noSpecial.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (in.hasNextLine()) {
			Team y = Team.readTeamName(in);
			for (int i = 0; i < 10; i++) {
				y.getPlayers().add(Team.readPlayers(in));
			}
			y.getGoalkeepers().add(Team.readGoalkeeper(in));
			teams.add(y);
		}

		return x;

	}
	
	public static ArrayList<Player> readAllPlayers() {
				ArrayList<Player> players = new ArrayList<>();

			try {
				in = new Scanner(new FileReader("C:/Users/Krijn/Downloads/Football App/Text files/noSpecial.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			while (in.hasNextLine()) {
				Team y = Team.readTeamName(in);
				for (int i = 0; i < 10; i++) {
					players.add(Team.readPlayers(in));
				}
				y.getGoalkeepers().add(Team.readGoalkeeper(in));
			}

			return players;

		
	}
	
	public static ArrayList<Goalkeeper> readAllKeepers() {
		ArrayList<Goalkeeper> players = new ArrayList<>();

	try {
		in = new Scanner(new FileReader("C:/Users/Krijn/Downloads/Football App/Text files/noSpecial.txt"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	while (in.hasNextLine()) {
		Team y = Team.readTeamName(in);
		for (int i = 0; i < 10; i++) {
			Player x = Team.readPlayers(in);
		}
		players.add(Team.readGoalkeeper(in));
	}

	return players;


}

	public static Competition readSpecialComp(String compName) {
		List<Team> teams = new ArrayList<>();

		Competition x = readCompetition();
		Competition comp = new Competition(teams);
		for (int i = 0; i < x.getTeams().size(); i++) {
			if (x.getTeams().get(i).getLeague().contains(compName)) {
				comp.getTeams().add(x.getTeams().get(i));
			}
		}
		return comp;
	}

	public static String[] competitionNames(Competition competition) {
		String[] teamNames = new String[competition.teams.size()];
		for (int i = 0; i < competition.teams.size(); i++) {
			teamNames[i] = competition.teams.get(i).getName();
		}
		return teamNames;
	}

	public static Team getTeamFromName(String currentTeam, Competition competition) {
		Team x = new Team(null, 0, null, null, 0, 0, null, null, null);
		for (int i = 0; i < competition.teams.size(); i++) {
			if (competition.getTeams().get(i).getName().equals(currentTeam)) {
				x = competition.getTeams().get(i);
			}
		}
		return x;
	}

	public static ArrayList<String> allPlayers() {
		ArrayList<Player> allPlayers = readAllPlayers();
		ArrayList<Goalkeeper> allKeepers = readAllKeepers();

		ArrayList<String> playerList = new ArrayList<>();
			for (int i = 0; i < allPlayers.size(); i++) {
				playerList.add(allPlayers.get(i).getName());
			}
			
		for (int i = 0; i < allKeepers.size(); i++) {
			playerList.add(allKeepers.get(i).getName());
		}

		return playerList;
	}

	public static String[] playersContaining(String input) {
		ArrayList<String> allPlayers = allPlayers();

		ArrayList<String> containingPlayers = new ArrayList<>();
		for (int i = 0; i < allPlayers.size(); i++) {
			if (allPlayers.get(i).toLowerCase().contains(input.toLowerCase())) {
				containingPlayers.add(allPlayers.get(i));
			}
		}
		String[] playerList = new String[containingPlayers.size()];
		for (int i = 0; i < containingPlayers.size(); i++) {
			playerList[i] = containingPlayers.get(i);
		}
		return playerList;
	}

	public static ArrayList<Player> playerFromName(String input) {
		ArrayList<Player> player = readAllPlayers();
		
		ArrayList<Player> playerName = new ArrayList<>();
		
		for (int i = 0; i < player.size(); i++) {
				if (input.equals(player.get(i).getName())) {
					playerName.add(player.get(i));
				
			}
		}
		return playerName;
	}

	public static ArrayList<Goalkeeper> keeperFromName(String input) {
		ArrayList<Goalkeeper> allKeepers = readAllKeepers();

		ArrayList<Goalkeeper> keeper = new ArrayList<>();
		for (int i = 0; i < allKeepers.size(); i++) {
			if (input.equals(allKeepers.get(i).getName())) {
				keeper.add(allKeepers.get(i));
			}
		}
		return keeper;
	}
	
	public static String getTeamNamePlayer(Player x) {
		Competition z = readCompetition();
		
		ArrayList<String> team = new ArrayList<>();
		
		for(int i = 0; i < z.getTeams().size(); i++) {
			for( int j = 0; j < z.getTeams().get(i).getPlayers().size(); j++) {
				if(z.getTeams().get(i).getPlayers().get(j).getName().equals(x.getName())) {
					team.add(z.getTeams().get(i).getName());
				}
			}
		}
		return team.get(0);
	}
	
	public static String getTeamNameKeeper(Goalkeeper x) {
		Competition z = readCompetition();
		
		ArrayList<String> team = new ArrayList<>();
		
		for(int i = 0; i < z.getTeams().size(); i++) {
			if(z.getTeams().get(i).getGoalkeepers().get(0).getName().equals(x.getName())) {
				team.add(z.getTeams().get(i).getName());
			}
		}
		return team.get(0);
	}


public static String[] playerFilter(int minAlg, int maxAlg, int minAtt, int maxAtt, int minDef, int maxDef, int minAge, int maxAge, int minVal, int maxVal, String pos, String name) {
		ArrayList<Player> allPlayers = readAllPlayers();
		ArrayList<Goalkeeper> allKeepers = readAllKeepers();
		
		ArrayList<Player> playersFiltered = new ArrayList<>();
		ArrayList<Goalkeeper> keepersFiltered = new ArrayList<>();

		for (int i = 0; i < allPlayers.size(); i++) {
			if (allPlayers.get(i).getAlg() >= minAlg && allPlayers.get(i).getAlg() <= maxAlg
					&& allPlayers.get(i).getAttack() >= minAtt && allPlayers.get(i).getAttack() <= maxAtt
					&& allPlayers.get(i).getDefense() >= minDef && allPlayers.get(i).getDefense() <= maxDef
					&& allPlayers.get(i).getAge() >= minAge && allPlayers.get(i).getAge() <= maxAge
					&& (allPlayers.get(i).getValue() >= minVal
					|| allPlayers.get(i).getValue() == 0) && (allPlayers.get(i).getValue() <= maxVal
					|| allPlayers.get(i).getValue() == 0) && allPlayers.get(i).getPosition().contains(pos)
							&& allPlayers.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
				playersFiltered.add(allPlayers.get(i));
			}
		}

		if(minAtt == 0 && maxAtt == 100 && minDef == 0 && maxDef == 100) {
			for (int i = 0; i < allKeepers.size(); i++) {
				if (allKeepers.get(i).getAlg() > minAlg && allKeepers.get(i).getAlg() < maxAlg
						&& allKeepers.get(i).getAge() > minAge && allKeepers.get(i).getAge() < maxAge
						&& allKeepers.get(i).getValue() > minVal && allKeepers.get(i).getValue() < maxVal
						&& allKeepers.get(i).getPosition().contains(pos)
						&& allKeepers.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
					keepersFiltered.add(allKeepers.get(i));
				}
			}
		}
		
		String[] playerList = new String[playersFiltered.size() + keepersFiltered.size()];
		for (int i = 0; i < playersFiltered.size(); i++) {
			playerList[i] = playersFiltered.get(i).getName();
		}
	
		for(int i = 0; i < keepersFiltered.size(); i++) {
			playerList[i + playersFiltered.size()] = keepersFiltered.get(i).getName();
		}
		return playerList;
	}

}
