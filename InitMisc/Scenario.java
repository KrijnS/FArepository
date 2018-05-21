package InitMisc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import InitTeam.Team;

public class Scenario {
		List<StringBuilder> scenario = new ArrayList<>();
		List<StringBuilder> scenarioTwo = new ArrayList<>();
		List<StringBuilder> scenarioAgainst = new ArrayList<>();
		List<StringBuilder> scenarioKeeper = new ArrayList<>();
	
	    public Scenario(List<StringBuilder> scenario, List<StringBuilder> scenarioTwo, List<StringBuilder> scenarioAgainst, List<StringBuilder> scenarioKeeper) {
	        this.scenario = scenario;
	        this.scenarioTwo = scenarioTwo;
	        this.scenarioAgainst = scenarioAgainst;
	        this.scenarioKeeper = scenarioKeeper;
	        
	    }

	  	public static Scenario fill(List<Team> competitionLoop, int loop, int player1, int loop2, int player2, int time, String playerTeam1, String playerTeam2, String keeper) {
	  		List<StringBuilder> scenario = new ArrayList<>();
	  		List<StringBuilder> scenarioTwo = new ArrayList<>();
	  		List<StringBuilder> scenarioAgainst = new ArrayList<>();
	  		List<StringBuilder> scenarioKeeper = new ArrayList<>();
	  		File scenarios = new File("C:/Users/Krijn/Downloads/Football App/Text files/scenarios.txt");
	  		Scanner in;
	  		try {
	  			in = new Scanner(new FileInputStream(scenarios));
	  			while (in.hasNextLine()) {
	  				StringBuilder x = new StringBuilder();
	  				
	  				String next = in.nextLine();
	  				x = Method.readFirst(competitionLoop, loop, player1, loop2, player2, time, next, playerTeam1, playerTeam2, keeper);
	  				scenario.add(x);
	  				
	  				next = in.nextLine();
	  				x = Method.readSecond(competitionLoop, loop, player1, loop2, player2, time, next, playerTeam1, playerTeam2, keeper);
	  				scenarioTwo.add(x);
	  				
	  				next = in.nextLine();
	  				x = Method.readAgainst(competitionLoop, loop, player1, loop2, player2, time, next, playerTeam1, playerTeam2, keeper);
	  				scenarioAgainst.add(x);
	  				
	  				next = in.nextLine();
	  				x = Method.readKeeper(competitionLoop, loop, player1, loop2, player2, time, next, playerTeam1, playerTeam2, keeper);
	  				scenarioKeeper.add(x);
	  			}
	  		} catch (FileNotFoundException e1) {
	  			// TODO Auto-generated catch block
	  			e1.printStackTrace();
	  		}

	    return new Scenario(scenario, scenarioTwo, scenarioAgainst, scenarioKeeper);
	}

		public List<StringBuilder> getScenario() {
			return scenario;
		}

		public void setScenario(List<StringBuilder> scenario) {
			this.scenario = scenario;
		}

		public List<StringBuilder> getScenarioTwo() {
			return scenarioTwo;
		}

		public void setScenarioTwo(List<StringBuilder> scenarioTwo) {
			this.scenarioTwo = scenarioTwo;
		}

		public List<StringBuilder> getScenarioAgainst() {
			return scenarioAgainst;
		}

		public void setScenarioAgainst(List<StringBuilder> scenarioAgainst) {
			this.scenarioAgainst = scenarioAgainst;
		}

		public List<StringBuilder> getScenarioKeeper() {
			return scenarioKeeper;
		}

		public void setScenarioKeeper(List<StringBuilder> scenarioKeeper) {
			this.scenarioKeeper = scenarioKeeper;
		}

}
