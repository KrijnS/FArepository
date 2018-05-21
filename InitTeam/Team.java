package InitTeam;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Team {
	String name;
	int score;
	List<Player> players = new ArrayList<>();
	List<Goalkeeper> goalkeepers = new ArrayList<>();
	int points;
	int money;
	String formation;
	String logoPath;
	String league;

	public Team(String name, int score, List<Player> players, List<Goalkeeper> goalkeepers, int points, int money, String formation, String logoPath, String league) {
		this.name = name;
		this.score = score;
		this.players = players;
		this.goalkeepers = goalkeepers;
		this.points = points;
		this.money = money;
		this.formation = formation;
		this.logoPath = logoPath;
		this.league = league;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public List<Goalkeeper> getGoalkeepers() {
		return goalkeepers;
	}

	public void setGoalkeepers(List<Goalkeeper> goalkeepers) {
		this.goalkeepers = goalkeepers;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void display() {
		System.out.println("\n");
		System.out.println(getName() + "   Budget: " + getMoney() + "\n");
		for (int i = 0; i < players.size(); i++) {
			System.out.println("#" + players.get(i).getNumber() + " " + players.get(i).getName() + " Age: "
					+ players.get(i).getAge() + "  Value: " + players.get(i).getValue() + ".000.000\n       Attacking: "
					+ players.get(i).getAttack() + "   Defending: " + players.get(i).getDefense() + "\n");
		}
		System.out.println("#" + goalkeepers.get(0).getNumber() + " " + goalkeepers.get(0).getName() + " Age: "
				+ goalkeepers.get(0).getAge() + "  Value: " + goalkeepers.get(0).getValue()
				+ ".000.000\n       Goalkeeping: " + goalkeepers.get(0).getGoalkeeping() + "\n");
	}

	public static Team readTeam(Scanner in) {

		try {
			in = new Scanner(new FileReader("teams.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Team x = readTeamName(in);

		for (int i = 0; i < 10; i++) {
			Player z = readPlayers(in);
			x.getPlayers().add(z);
		}
		Goalkeeper y = readGoalkeeper(in);
		x.getGoalkeepers().add(y);

		return x;
	}

	public static Team readTeamName(Scanner in) {
		String[] parts = in.nextLine().split(", ");

		List<Player> players = new ArrayList<>();
		List<Goalkeeper> goalkeepers = new ArrayList<>();

		Team x = new Team(parts[0], 0, players, goalkeepers, 0, Integer.parseInt(parts[1]), parts[2], parts[3], parts[4]);
		return x;
	}

	public static Player readPlayers(Scanner in) {
		String[] parts = in.nextLine().split(", ");

		Player x = new Player(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
				parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), 0, 0, 
			    parts[6], parts[7]);
		
		x.setValue(x.value(x));

		return x;
	}

	public static Goalkeeper readGoalkeeper(Scanner in) {
		String[] parts = in.nextLine().split(", ");

		Goalkeeper x = new Goalkeeper(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2],
				Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), 0, 0, parts[5], parts[6]);
		
		x.setValue(x.value(x));

		return x;
	}

}
