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
	
	public static Competition readSpecialComp(String compName) {
		List<Team> teams = new ArrayList<>();
		
		Competition x = readCompetition();
		Competition comp = new Competition(teams);
		for(int i = 0; i < x.getTeams().size(); i++) {
			if(x.getTeams().get(i).getLeague().contains(compName)) {
				comp.getTeams().add(x.getTeams().get(i));
			}
		}
		return comp;
	}
	
	public static String[] competitionNames(Competition competition) {
		String[] teamNames = new String[competition.teams.size()];
		for(int i = 0; i < competition.teams.size(); i++) {
			teamNames[i] = competition.teams.get(i).getName();
		}
		return teamNames;
	}
	
	public static Team getTeamFromName(String currentTeam, Competition competition) {
		Team x = new Team(null, 0, null, null, 0, 0, null, null, null);
		for(int i = 0; i < competition.teams.size(); i++) {
			if(competition.getTeams().get(i).getName().equals(currentTeam)) {
				x = competition.getTeams().get(i);
			}
		}
		return x;
	}

}
