
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ToGlory implements Runnable{
	static String goal = new String("\n" + "|||||||||   |||||||||      ||||      ||" + "\n"
										 + "||          ||     ||     ||  ||     ||" + "\n"
										 + "||          ||     ||     ||  ||     ||" + "\n"
										 + "||          ||     ||    ||    ||    ||" + "\n"
										 + "||  |||||   ||     ||    ||||||||    ||" + "\n"
										 + "||     ||   ||     ||   ||      ||   ||" + "\n"
										 + "|||||||||   |||||||||   ||      ||   |||||||||\n\n");

	Random rand = new Random();

	Scanner sc = new Scanner(System.in);

	Competition competition = Competition.readCompetition();
	Method init = new Method();
	
	public void run() {
		String chooseTeam = new String("Please type an integer of which teams you would like to guide to glory:\n");

		System.out.println(chooseTeam);

		for (int i = 0; i < competition.teams.size(); i++) {
			int count = i + 1;
			System.out.println(" [" + count + "] " + competition.getTeams().get(i).name);
		}
		int time = 0;

		System.out.println("\n");

		List<StringBuilder> scenario = new ArrayList<>();
		List<StringBuilder> scenarioTwo = new ArrayList<>();
		List<StringBuilder> scenarioAgainst = new ArrayList<>();
		List<StringBuilder> scenarioKeeper = new ArrayList<>();

		List<Team> competitionFaced = new ArrayList<>();
		List<Team> competitionToFace = new ArrayList<>();
		List<Team> competitionCompetitors = new ArrayList<>();

		int club = sc.nextInt() - 1;
		
		
			competitionFaced.add(competition.getTeams().get(club));

			for (int i = 0; i < competition.teams.size(); i++) {
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
			
		
		int club2 = rand.nextInt(competitionToFace.size() - 1) + 0;

		if (competitionToFace.size() == 1) {
			club2 = 0;
		}
			
			String betweenMenu = new String("Do you want to:\n [1] Play the next match versus "
					+ competitionToFace.get(club2).getName() + "\n [2] Make a transfer\n [3] Look at the statistics");

			System.out.println(betweenMenu);

			Scanner sc = new Scanner(System.in);

			int choiceBetween = sc.nextInt();
			
			if(choiceBetween == 3) {
				init.stats(competitionFaced);			
			}

			if (choiceBetween == 2) {
				init.transfer(competitionCompetitors, sc, competitionFaced);
				}
			
			List<Team> competitionLoop = new ArrayList<>();

			competitionLoop.add(competitionFaced.get(0));
			competitionLoop.add(competitionToFace.get(club2));
			
			init.rightPositions(competitionLoop);


			int team1 = rand.nextInt(1) + 0;
			int team2 = 0;

			if (team1 == team2) {
				team2 = 1;
			}

				
				competitionFaced.add(competitionToFace.get(club2));
				
				Thread crowd = new Thread(new Crowd());
				crowd.start();

				try {
					TimeUnit.MILLISECONDS.sleep(1500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				time = 0;

				competitionFaced.get(0).setScore(0);

				while (time <= 90) {
					int loop = rand.nextInt(2) + 0;
					int loop2 = 0;

					if (loop == loop2) {
						loop2 = 1;
					}
					
					int player1 = rand.nextInt(10) + 0;
					int player2 = rand.nextInt(10) + 0;
					

					String playerTeam1 = new String(" " + competitionLoop.get(loop).players.get(player1).getName() + " ");
					String playerTeam2 = new String(" " + competitionLoop.get(loop2).players.get(player2).getName() + " ");
					String keeper = new String(" " + competitionLoop.get(loop2).goalkeepers.get(0).getName() + " ");
					
					File scenarios = new File("scenarios.txt");
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
					
					int choice = rand.nextInt(scenario.size()) + 0;

					System.out.println(competitionLoop.get(team1).getName() + " : "
							+ competitionLoop.get(team1).getScore() + "     " + competitionLoop.get(team2).getName()
							+ " : " + competitionLoop.get(team2).getScore() + "\n");

					System.out.println(scenario.get(choice));
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (competitionLoop.get(loop).players.get(player1).getAttack() + rand.nextInt(20)
							+ 5 > competitionLoop.get(loop2).players.get(player2).getDefense() + rand.nextInt(30)
									+ 10) {
						System.out.println(scenarioTwo.get(choice));

						try {
							TimeUnit.SECONDS.sleep(3);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (competitionLoop.get(loop).players.get(player1).getAttack() + rand.nextInt(20)
								+ 5 > competitionLoop.get(loop2).goalkeepers.get(0).getGoalkeeping() + rand.nextInt(25)
										+ 10) {
							new Thread(new Goal()).start();
							try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println(goal);
							
							try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							System.out.println("#" +  competitionLoop.get(loop).getPlayers().get(player1).getNumber() + "  " + competitionLoop.get(loop).getPlayers().get(player1).getName() + " with the goal!\n\n");
							
							competitionLoop.get(loop).setScore(competitionLoop.get(loop).getScore() + 1);
							competitionLoop.get(loop).getPlayers().get(player1).setGoals(competitionLoop.get(loop).getPlayers().get(player1).getGoals() + 1);
							try {
								TimeUnit.SECONDS.sleep(4);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						else {

							System.out.println(scenarioKeeper.get(choice));
							try {
								TimeUnit.SECONDS.sleep(2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					} else {
						System.out.println(scenarioAgainst.get(choice));
						try {
							TimeUnit.SECONDS.sleep(2);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					scenario.clear();
					scenarioTwo.clear();
					scenarioAgainst.clear();
					scenarioKeeper.clear();

					time = time + rand.nextInt(7) + 1;
					
					
					if (time > 90) {
						new Thread(new FinalWhistle()).start();
						
						System.out.println("The game has ended\n\nResult: " + competitionLoop.get(team1).getName() + " : "
								+ competitionLoop.get(team1).getScore() + "     " + competitionLoop.get(team2).getName()
								+ " : " + competitionLoop.get(team2).getScore() + "\n");
						
						
						
						if(competitionLoop.get(1).getScore() == 0) {
							competitionLoop.get(0).getGoalkeepers().get(0).setCleanSheets(competitionLoop.get(0).getGoalkeepers().get(0).getCleanSheets() + 1);
						}
						
						if(competitionLoop.get(0).getScore() > competitionLoop.get(1).getScore()) {
							competitionLoop.get(0).setPoints(competitionLoop.get(0).getPoints() + 3);
						}
						
						else if(competitionLoop.get(0).getScore() == competitionLoop.get(1).getScore()) {
							competitionLoop.get(0).setPoints(competitionLoop.get(0).getPoints() + 1);
						}

					
						try {
							TimeUnit.SECONDS.sleep(2);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

		

		System.out.println("\n\n");
		Main.main();


	
}
}

	
