import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Match implements Runnable{
	static String goal = new String("\n" + "|||||||||   |||||||||      ||||      ||" + "\n"
										 + "||          ||     ||     ||  ||     ||" + "\n"
										 + "||          ||     ||     ||  ||     ||" + "\n"
										 + "||          ||     ||    ||    ||    ||" + "\n"
										 + "||  |||||   ||     ||    ||||||||    ||" + "\n"
										 + "||     ||   ||     ||   ||      ||   ||" + "\n"
										 + "|||||||||   |||||||||   ||      ||   |||||||||\n\n");

	Random rand = new Random();
	
	Scanner sc = new Scanner(System.in);
	
	String option = new String();
	
	Method init = new Method();	
	
	Competition competition = Competition.readCompetition();
	
		public void run() {
			String chooseTeam = new String("Please type in two integers of which teams you would like to face eachother:\n");
			
			System.out.println(chooseTeam);
			
			for(int i = 0; i < competition.teams.size(); i++) {
				int count = i + 1;
				System.out.println(" [" + count + "] " + competition.getTeams().get(i).name);
			}
			int time = 0;
			
			System.out.println("\n");
			
			List<StringBuilder> scenario = new ArrayList<>();
			List<StringBuilder> scenarioTwo = new ArrayList<>();
			List<StringBuilder> scenarioAgainst = new ArrayList<>();
			List<StringBuilder> scenarioKeeper = new ArrayList<>();
			
			List<Team> competitionLoop = new ArrayList<>();
			
			int club = sc.nextInt()-1;
			int club2 = sc.nextInt()-1;

			competitionLoop.add(competition.getTeams().get(club));
			competitionLoop.add(competition.getTeams().get(club2));
			
			init.rightPositions(competitionLoop);
			
			Thread crowd = new Thread(new Crowd());
			crowd.start();
			
			try {
				TimeUnit.MILLISECONDS.sleep(1500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			while (time <= 90) {
				int loop = rand.nextInt(2) + 0;
				int loop2 = 0;
				
				if(loop == loop2) {
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
				
				System.out.println(competition.getTeams().get(club).getName() + " : " + competition.getTeams().get(club).getScore() + "     " + competition.getTeams().get(club2).getName() + " : "
						+ competition.getTeams().get(club2).getScore() + "\n");

				System.out.println(scenario.get(choice));
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (competitionLoop.get(loop).players.get(player1).getAttack() + rand.nextInt(20)
						+ 5 > competitionLoop.get(loop2).players.get(player2).getDefense() + rand.nextInt(30) + 10) {
					System.out.println(scenarioTwo.get(choice));
										
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (competitionLoop.get(loop).players.get(player1).getAttack() + rand.nextInt(20)
							+ 5 > competitionLoop.get(loop2).goalkeepers.get(0).getGoalkeeping() + rand.nextInt(25) + 10) {
						new Thread(new Goal()).start();
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(goal);
						competitionLoop.get(loop).setScore(competitionLoop.get(loop).getScore() + 1);
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
			}

			new Thread(new FinalWhistle()).start();
			System.out.println("The game has ended\n");
			
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			System.out.println("\n\n");
		

		}

		public String getOption() {
			return option;
		}

		public void setOption(String option) {
			this.option = option;
		}
	
	
}
