package InitMisc;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import GUI.Design;
import InitTeam.Competition;
import InitTeam.Goalkeeper;
import InitTeam.Player;
import InitTeam.Team;
import Sounds.Goal;

public class Method {
	Random rand = new Random();

	public void competitions(List<Team> competitionFaced, Competition competition, int club,
			List<Team> competitionToFace, List<Team> competitionCompetitors) {
		competitionFaced.add(competition.getTeams().get(club));

		for (int i = 0; i < competition.getTeams().size(); i++) {
			competitionToFace.add(competition.getTeams().get(i));
			competitionCompetitors.add(competition.getTeams().get(i));
		}

		competitionCompetitors.remove(club);

		while (competitionToFace.isEmpty() == false) {

			for (int i = 0; i < competitionFaced.size(); i++) {
				if (competitionToFace.contains(competitionFaced.get(i))) {
					competitionToFace.remove(competitionFaced.get(i));
				}
			}

		}
	}

	public void stats(List<Team> competitionFaced) {
		System.out.println(
				competitionFaced.get(0).getName() + " : " + competitionFaced.get(0).getPoints() + " points.\n");
		for (int i = 0; i < competitionFaced.get(0).getPlayers().size(); i++) {
			if (competitionFaced.get(0).getPlayers().get(i).getGoals() == 1) {
				System.out.println(" #" + competitionFaced.get(0).getPlayers().get(i).getNumber() + "  "
						+ competitionFaced.get(0).getPlayers().get(i).getName() + " "
						+ competitionFaced.get(0).getPlayers().get(i).getGoals() + " goal\n");
			} else {
				System.out.println(" #" + competitionFaced.get(0).getPlayers().get(i).getNumber() + "  "
						+ competitionFaced.get(0).getPlayers().get(i).getName() + " "
						+ competitionFaced.get(0).getPlayers().get(i).getGoals() + " goals\n");
			}

		}
		if (competitionFaced.get(0).getGoalkeepers().get(0).getCleanSheets() == 1) {
			System.out.println(" #" + competitionFaced.get(0).getGoalkeepers().get(0).getNumber() + "  "
					+ competitionFaced.get(0).getGoalkeepers().get(0).getName() + " "
					+ competitionFaced.get(0).getGoalkeepers().get(0).getCleanSheets() + " clean sheet\n");
		} else {
			System.out.println(" #" + competitionFaced.get(0).getGoalkeepers().get(0).getNumber() + "  "
					+ competitionFaced.get(0).getGoalkeepers().get(0).getName() + " "
					+ competitionFaced.get(0).getGoalkeepers().get(0).getCleanSheets() + " clean sheets\n");

		}

	}

	public void transfer(List<Team> competitionCompetitors, Scanner sc, List<Team> competitionFaced) {
		System.out.println("From which team would you like to buy a player?\n");

		for (int i = 0; i < competitionCompetitors.size(); i++) {
			int count = i + 1;
			System.out.println(" [" + count + "] " + competitionCompetitors.get(i).getName());
		}
		int choiceTeam = sc.nextInt() - 1;
		competitionCompetitors.get(choiceTeam).display();
		System.out.println("Please input the number of the player you would like to buy.        Your budget: "
				+ competitionFaced.get(0).getMoney() + "\n");
		int choicePlayer = sc.nextInt();
		for (int i = 0; i < competitionCompetitors.get(choiceTeam).getPlayers().size(); i++) {
			if (choicePlayer == competitionCompetitors.get(choiceTeam).getPlayers().get(i).getNumber()
					|| choicePlayer == competitionCompetitors.get(choiceTeam).getGoalkeepers().get(0).getNumber()) {
				System.out.println("What would you like to offer for "
						+ competitionCompetitors.get(choiceTeam).getPlayers().get(i).getName()
						+ "?        Your budget: " + competitionFaced.get(0).getMoney() + "\n");
				int offer = sc.nextInt();

				moneyCheck(sc, offer, competitionFaced, competitionCompetitors, choiceTeam, i);

				List<Integer> worth = new ArrayList<>();
				worth.add(competitionCompetitors.get(choiceTeam).getPlayers().get(i).worth());

				if (offer >= worth.get(0)) {
					wonPlayer(offer, worth, competitionCompetitors, competitionFaced, choiceTeam, i, sc);

				}

				else {
					System.out.println("That offer is not enough.. " + competitionCompetitors.get(choiceTeam).getName()
							+ " wants at least " + worth.get(0) + " for "
							+ competitionCompetitors.get(choiceTeam).getPlayers().get(i).getName()
							+ ".\n\nImprove your offer.        Your budget: " + competitionFaced.get(0).getMoney()
							+ "\n");
					int offer2 = sc.nextInt();
					moneyCheck(sc, offer2, competitionFaced, competitionCompetitors, choiceTeam, i);
					if (offer2 >= worth.get(0)) {
						wonPlayer(offer, worth, competitionCompetitors, competitionFaced, choiceTeam, i, sc);
						System.out.println("The next match will now play!\n");

					} else {
						System.out.println(competitionCompetitors.get(choiceTeam).getName()
								+ " have already let you know what they want for their player and want to negotiate no further.\n");
						System.out.println("\nThe next match will now play!\n");
					}
				}
			}
		}

	}

	public void moneyCheck(Scanner sc, int offer, List<Team> competitionFaced, List<Team> competitionCompetitors,
			int choiceTeam, int i) {
		while (offer > competitionFaced.get(0).getMoney()) {
			System.out.println("You currently have to little funds for this.        Your budget: "
					+ competitionFaced.get(0).getMoney()
					+ "\nDo you want your complete budget to be bid?\n [1] Yes\n [2] No");
			int yesNo = sc.nextInt();
			if (yesNo == 1) {
				offer = competitionFaced.get(0).getMoney();
			} else {
				System.out.println("\nWhat would you like to bid for "
						+ competitionCompetitors.get(choiceTeam).getPlayers().get(i).getName() + " then?\n");
				offer = sc.nextInt();
			}
		}
	}

	public void wonPlayer(int offer, List<Integer> worth, List<Team> competitionCompetitors,
			List<Team> competitionFaced, int choiceTeam, int i, Scanner sc) {
		System.out.println("\nCongratulations! You have bought "
				+ competitionCompetitors.get(choiceTeam).getPlayers().get(i).getName()
				+ "!\n\nWhich number would you like to give him?\n");
		System.out.println("Please pick any number other then:\n");

		competitionFaced.get(0).setMoney(competitionFaced.get(0).getMoney() - offer);

		for (int x = 0; x < competitionFaced.get(0).getPlayers().size(); x++) {
			System.out.println(" #" + competitionFaced.get(0).getPlayers().get(x).getNumber() + "\n");
		}
		System.out.println(" #" + competitionFaced.get(0).getGoalkeepers().get(0).getNumber());

		int number = sc.nextInt();

		for (int x = 0; x < competitionFaced.get(0).getPlayers().size(); x++) {
			while (competitionFaced.get(0).getPlayers().get(x).getNumber() == number
					|| competitionFaced.get(0).getGoalkeepers().get(0).getNumber() == number) {
				System.out.println("Please input a number not currently in use.\n");
				number = sc.nextInt();
			}
			competitionCompetitors.get(choiceTeam).getPlayers().get(i).setNumber(number);
		}

		System.out.println("For which player would you like to swap your purchase?\n");
		competitionFaced.get(0).display();
		int playerSwap = sc.nextInt();
		for (int x = 0; x < competitionFaced.get(0).getPlayers().size(); x++) {
			if (competitionFaced.get(0).getPlayers().get(x).getNumber() == playerSwap) {
				competitionFaced.get(0).getPlayers().remove(x);
				competitionFaced.get(0).getPlayers().add(x, competitionCompetitors.get(choiceTeam).getPlayers().get(i));
			}

		}
		competitionCompetitors.get(choiceTeam).getPlayers().remove(i);
		System.out.println("\nYour Team now looks like this:\n\n");
		competitionFaced.get(0).display();

	}

	public static StringBuilder readFirst(List<Team> competitionLoop, int loop, int player1, int loop2, int player2,
			int time, String next, String playerTeam1, String playerTeam2, String keeper) {
		File scenarios = new File("C:/Users/Krijn/Downloads/Football App/Text files/scenarios.txt");
		Scanner in;
		StringBuilder x = new StringBuilder();
		try {
			in = new Scanner(scenarios);
			String[] parts = next.split(" + ");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].contains("player1")) {
					sb.append(playerTeam1);

				} else if (parts[i].contains("player2")) {
					sb.append(playerTeam2);

				} else if (parts[i].contains("keeper2")) {
					sb.append(keeper);

				} else {
					sb.append(parts[i]);
				}
				
				x = sb;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//x.append("     '");
		//x.append(time);
		x.append("\n");
		return x;
	}

	public static StringBuilder readSecond(List<Team> competitionLoop, int loop, int player1, int loop2, int player2,
			int time, String next, String playerTeam1, String playerTeam2, String keeper) {
		StringBuilder x = new StringBuilder();
		File scenarios = new File("C:/Users/Krijn/Downloads/Football App/Text files/scenarios.txt");
		Scanner in;
		try {
			in = new Scanner(scenarios);
			String[] parts = next.split(" + ");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].contains("player1")) {
					sb.append(playerTeam1);
				} else if (parts[i].contains("player2")) {
					sb.append(playerTeam2);
				} else if (parts[i].contains("keeper2")) {
					sb.append(keeper);
				} else {
					sb.append(parts[i]);
				}
				x = sb;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//x.append("     '");
		//x.append(time);
		x.append("\n");
		return x;
	}

	public static StringBuilder readAgainst(List<Team> competitionLoop, int loop, int player1, int loop2, int player2,
			int time, String next, String playerTeam1, String playerTeam2, String keeper) {
		StringBuilder x = new StringBuilder();
		File scenarios = new File("C:/Users/Krijn/Downloads/Football App/Text files/scenarios.txt");
		Scanner in;
		try {
			in = new Scanner(scenarios);
			String[] parts = next.split(" + ");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].contains("player1")) {
					sb.append(playerTeam1);
				} else if (parts[i].contains("player2")) {
					sb.append(playerTeam2);
				} else if (parts[i].contains("keeper2")) {
					sb.append(keeper);
				} else {
					sb.append(parts[i]);
				}
				x = sb;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//x.append("     '");
		//x.append(time);
		x.append("\n");
		return x;
	}

	public static StringBuilder readKeeper(List<Team> competitionLoop, int loop, int player1, int loop2, int player2,
			int time, String next, String playerTeam1, String playerTeam2, String keeper) {
		StringBuilder x = new StringBuilder();
		File scenarios = new File("C:/Users/Krijn/Downloads/Football App/Text files/scenarios.txt");
		Scanner in;
		try {
			in = new Scanner(scenarios);
			String[] parts = next.split(" + ");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].contains("player1")) {
					sb.append(playerTeam1);
				} else if (parts[i].contains("player2")) {
					sb.append(playerTeam2);
				} else if (parts[i].contains("keeper2")) {
					sb.append(keeper);
				} else {
					sb.append(parts[i]);
				}
				x = sb;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//x.append("     '");
		//x.append(time);
		x.append("\n");
		return x;
	}
	
	public static List<String> fill433A(){
		List<String> formation = new ArrayList<>();
		formation.add("LW");
		formation.add("SP");
		formation.add("RW");
		formation.add("CAM");
		formation.add("CM");
		formation.add("CM");
		formation.add("RB");
		formation.add("CB");
		formation.add("CB");
		formation.add("LB");
		
		return formation;
	}
	
	public static List<String> fill433(){
		List<String> formation = new ArrayList<>();
		formation.add("LW");
		formation.add("SP");
		formation.add("RW");
		formation.add("CM");
		formation.add("CM");
		formation.add("CM");
		formation.add("RB");
		formation.add("CB");
		formation.add("CB");
		formation.add("LB");
	
		
		return formation;
	}
	
	public static List<String> fill3511(){
		List<String> formation = new ArrayList<>();
		formation.add("SP");
		formation.add("CF");
		formation.add("LM");
		formation.add("RM");
		formation.add("CM");
		formation.add("CM");
		formation.add("CVM");
		formation.add("CB");
		formation.add("CB");
		formation.add("CB");
		
		return formation;
	}
	
	public static List<String> fill352(){
		List<String> formation = new ArrayList<>();
		formation.add("SP");
		formation.add("SP");
		formation.add("CAM");
		formation.add("LM");
		formation.add("RM");
		formation.add("CVM");
		formation.add("CVM");
		formation.add("CB");
		formation.add("CB");
		formation.add("CB");
		
		return formation;
	}
	public static List<String> fill4321(){
		List<String> formation = new ArrayList<>();
		formation.add("LF");
		formation.add("SP");
		formation.add("RF");
		formation.add("CM");
		formation.add("CM");
		formation.add("CM");
		formation.add("RB");
		formation.add("CB");
		formation.add("CB");
		formation.add("LB");
		
		return formation;
	}
	
	public static List<String> fill433C(){
		List<String> formation = new ArrayList<>();
		formation.add("LW");
		formation.add("SP");
		formation.add("RW");
		formation.add("CM");
		formation.add("CM");
		formation.add("CVM");
		formation.add("RB");
		formation.add("CB");
		formation.add("CB");
		formation.add("LB");
		
		return formation;
	}
	
	public static List<String> fill433D(){
		List<String> formation = new ArrayList<>();
		formation.add("LW");
		formation.add("SP");
		formation.add("RW");
		formation.add("CM");
		formation.add("CVM");
		formation.add("CVM");
		formation.add("RB");
		formation.add("CB");
		formation.add("CB");
		formation.add("LB");
		
		return formation;
	}
	
	public static List<String> getFormationFill(Team x){
		List<String> formation = new ArrayList<>();
		if(x.getFormation().equals("4-3-3(A)")) {
			formation = fill433A();
		}
		else if(x.getFormation().equals("3-5-1-1")) {
			formation = fill3511();
		}
		else if(x.getFormation().equals("4-3-3(C)")) {
			formation = fill433C();
		}
		else if(x.getFormation().equals("4-3-3(D)")) {
			formation = fill433D();
		}
		else if(x.getFormation().equals("4-3-3(C)")) {
			formation = fill433C();
		}
		else if(x.getFormation().equals("4-3-3")) {
			formation = fill433();
		}
		else if(x.getFormation().equals("4-3-2-1")) {
			formation = fill4321();
		}
		else {
			System.out.println("This formation doesn't exist");
		}
		return formation;
	}
	
	public void rightPositions(List<Team> competitionLoop) {
		List<String> formation1 = new ArrayList<>();
		List<String> formation2 = new ArrayList<>();
		
		formation1 = Method.getFormationFill(competitionLoop.get(0));
		formation2 = Method.getFormationFill(competitionLoop.get(1));
		
		for(int i = 0; i < competitionLoop.get(0).getPlayers().size(); i++) {
			if(!(competitionLoop.get(0).getPlayers().get(i).getPosition().contains(formation1.get(i)))) {
				competitionLoop.get(0).getPlayers().get(i).setAlg(competitionLoop.get(0).getPlayers().get(i).getAlg() - 5);
				competitionLoop.get(0).getPlayers().get(i).setAttack(competitionLoop.get(0).getPlayers().get(i).getAttack() - 5);
				competitionLoop.get(0).getPlayers().get(i).setDefense(competitionLoop.get(0).getPlayers().get(i).getDefense() - 5);
			}
		}
		
		for(int i = 0; i < competitionLoop.get(1).getPlayers().size(); i++) {
			if(!(competitionLoop.get(1).getPlayers().get(i).getPosition().contains(formation2.get(i)))) {
				competitionLoop.get(1).getPlayers().get(i).setAlg(competitionLoop.get(1).getPlayers().get(i).getAlg() - 5);
				competitionLoop.get(1).getPlayers().get(i).setAttack(competitionLoop.get(1).getPlayers().get(i).getAttack() - 5);
				competitionLoop.get(1).getPlayers().get(i).setDefense(competitionLoop.get(1).getPlayers().get(i).getDefense() - 5);
			}
		}
		
		
	}
	
	public String[] readLeagues() {
		File file = new File("C:/Users/Krijn/Downloads/Football App/Text files/leagues.txt");
		Scanner in;
		List<String> leagueName = new ArrayList<>();
		try {
			in = new Scanner(file);
			while(in.hasNextLine()) {
				leagueName.add(in.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] leagueNames = new String[leagueName.size()];
		for(int i = 0; i < leagueName.size(); i++) {
			leagueNames[i] = leagueName.get(i);
		}
		return leagueNames;
	}
	
	public void playMatch(JFrame frame, JLabel backGround, JLabel logoLabel) {
		Font btnFont = new Font("Trebuchet MS", Font.BOLD, 18);
		Font scoreFont = new Font("Trebuchet MS", Font.BOLD, 24);
		Color textColor = Color.white;

		int time = 0;

		Competition competition = Competition.readCompetition();

		List<StringBuilder> scenario = new ArrayList<>();
		List<StringBuilder> scenarioTwo = new ArrayList<>();
		List<StringBuilder> scenarioAgainst = new ArrayList<>();
		List<StringBuilder> scenarioKeeper = new ArrayList<>();

		List<Team> competitionLoop = new ArrayList<>();

		int club = rand.nextInt(competition.getTeams().size()) + 0;
		int club2 = rand.nextInt(competition.getTeams().size()) + 0;
		if (club == club2) {
			club2 = rand.nextInt(competition.getTeams().size()) + 0;
		}

		competitionLoop.add(competition.getTeams().get(club));
		competitionLoop.add(competition.getTeams().get(club2));

		frame.getContentPane().removeAll();

		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();

		JButton mainMenu = new JButton("Exit");
		mainMenu.setBounds(378, 670, 105, 45);
		Design.buttonDesign(mainMenu);

		JLabel firstScenario = new JLabel();
		firstScenario.setHorizontalAlignment(SwingConstants.CENTER);
		firstScenario.setAlignmentX(Component.CENTER_ALIGNMENT);
		firstScenario.setAlignmentY(Component.CENTER_ALIGNMENT);
		firstScenario.setBounds(12, 390, 480, 60);
		firstScenario.setForeground(textColor);
		firstScenario.setFont(btnFont);

		JLabel secondScenario = new JLabel();
		secondScenario.setHorizontalAlignment(SwingConstants.CENTER);
		secondScenario.setAlignmentX(Component.CENTER_ALIGNMENT);
		secondScenario.setAlignmentY(Component.CENTER_ALIGNMENT);
		secondScenario.setBounds(12, 490, 480, 60);
		secondScenario.setForeground(textColor);
		secondScenario.setFont(btnFont);

		JLabel thirdScenario = new JLabel();
		thirdScenario.setHorizontalAlignment(SwingConstants.CENTER);
		thirdScenario.setAlignmentX(Component.CENTER_ALIGNMENT);
		thirdScenario.setAlignmentY(Component.CENTER_ALIGNMENT);
		thirdScenario.setBounds(12, 590, 480, 60);
		thirdScenario.setForeground(textColor);
		thirdScenario.setFont(btnFont);

		Random rand = new Random();


		int team1 = rand.nextInt(competition.getTeams().size());
		int team2 = rand.nextInt(competition.getTeams().size());

		int loop = rand.nextInt(2) + 0;
		int loop2 = 0;

		if (loop == loop2) {
			loop2 = 1;
		}

		int player1 = rand.nextInt(10) + 0;
		int player2 = rand.nextInt(10) + 0;

		String playerTeam1 = new String(" " + competitionLoop.get(loop).getPlayers().get(player1).getName() + " ");
		String playerTeam2 = new String(" " + competitionLoop.get(loop2).getPlayers().get(player2).getName() + " ");
		String keeper = new String(" " + competitionLoop.get(loop2).getGoalkeepers().get(0).getName() + " ");

		if (team1 == team2) {
			team2 = rand.nextInt(competition.getTeams().size());
		}

		int i = 0;
		
		while(i <= 90) {
			
				// get the username
				Scenario scen = Scenario.fill(competitionLoop, loop, player1, loop2, player2, time, playerTeam1,
						playerTeam2, keeper);

				frame.getContentPane().removeAll();
				
				JLabel lblNewLabel = new JLabel("" + i);
				lblNewLabel.setFont(scoreFont);
				lblNewLabel.setBounds(319, 217, 56, 16);
				frame.getContentPane().add(lblNewLabel);
									    

				scenario = scen.getScenario();
				scenarioTwo = scen.getScenarioTwo();
				scenarioAgainst = scen.getScenarioAgainst();
				scenarioKeeper = scen.getScenarioKeeper();

				int choice = rand.nextInt(scenario.size()) + 0;

				firstScenario.setText("<html>" + scenario.get(choice).toString() + "</html>");
				secondScenario.setText("<html>" + scenarioTwo.get(choice).toString() + "</html>");
				thirdScenario.setText("<html>" + scenarioKeeper.get(choice).toString() + "</html>");
				frame.getContentPane().add(firstScenario);
				frame.getContentPane().add(secondScenario);
				frame.getContentPane().add(thirdScenario);
				frame.getContentPane().add(logoLabel);
				frame.getContentPane().add(backGround);
				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
			
			    
			    i = i + rand.nextInt(7) + 1;
		}

				// add the actionlistener for the buttons


		
		JLabel team1Label = new JLabel("<html>" + competitionLoop.get(0).getName() + "</html>");
		team1Label.setHorizontalAlignment(SwingConstants.CENTER);
		team1Label.setBounds(87, 284, 154, 56);
		team1Label.setFont(btnFont);
		team1Label.setForeground(textColor);

		JLabel team2Label = new JLabel("<html>" + competitionLoop.get(1).getName() + "</html>");
		team2Label.setHorizontalAlignment(SwingConstants.CENTER);
		team2Label.setBounds(263, 284, 154, 56);
		team2Label.setFont(btnFont);
		team2Label.setForeground(textColor);

		JLabel scoreLabel = new JLabel(
				competitionLoop.get(0).getScore() + " - " + competitionLoop.get(1).getScore());
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setBounds(196, 217, 107, 44);
		scoreLabel.setFont(scoreFont);
		scoreLabel.setForeground(textColor);

		ImageIcon wrongSize = new ImageIcon(getClass().getResource(competitionLoop.get(0).getLogoPath()));
		Image img = wrongSize.getImage();
		Image newImg = img.getScaledInstance(63, 67, Image.SCALE_SMOOTH);
		ImageIcon logo = new ImageIcon(newImg);
		JLabel clubLogo = new JLabel(logo);
		clubLogo.setBounds(12, 276, 63, 67);

		ImageIcon wrongSize2 = new ImageIcon(getClass().getResource(competitionLoop.get(1).getLogoPath()));
		Image img2 = wrongSize2.getImage();
		Image newImg2 = img2.getScaledInstance(63, 67, Image.SCALE_SMOOTH);
		ImageIcon logo2 = new ImageIcon(newImg2);
		JLabel clubLogo2 = new JLabel(logo2);
		clubLogo2.setBounds(429, 276, 63, 67);

		frame.getContentPane().add(scoreLabel);
		frame.getContentPane().add(team1Label);
		frame.getContentPane().add(team2Label);
		frame.getContentPane().add(logoLabel);
		frame.getContentPane().add(clubLogo);
		frame.getContentPane().add(clubLogo2);
		frame.getContentPane().add(mainMenu);

		frame.getContentPane().add(backGround);

	}

	public void setFirstText(JLabel firstScenario, Player x, Player y, int choice, List<String> scenario) {
		firstScenario.setText("<html>" + scenario.get(choice).toString() + "</html>");
		}
	
	public void setSecondText(JLabel secondScenario, Player x, Player y, int choice, List<String> scenarioTwo, List<String> scenarioAgainst) {
		if (x.getAttack() + rand.nextInt(20)+ 5 > y.getDefense() + rand.nextInt(30) + 10) {
			secondScenario.setText("<html>" + scenarioTwo.get(choice).toString() + "</html>");
	}
	
		else {
			secondScenario.setText("<html>" + scenarioAgainst.get(choice).toString() + "</html>");
		}
}
	
	public void setThirdText(JLabel thirdScenario, Player x, Goalkeeper y, Team z, int choice, List<String> scenarioKeeper) {
		

	if (x.getAttack() + rand.nextInt(20)+ 5 > y.getGoalkeeping() + rand.nextInt(25) + 10) {
		new Thread(new Goal()).start();
	
		z.setScore(z.getScore() + 1);
	}
	
	else {
		
		thirdScenario.setText("<html>" + scenarioKeeper.get(choice).toString() + "</html>");
	
	}
	}
	
	public Team getRandomTeam(Competition x) {
		int y = rand.nextInt(x.getTeams().size()) + 0;
		Team z = x.getTeams().get(y);
		return z;
	}
	
}
