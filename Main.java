import java.util.Scanner;

public class Main {

	public static void main(String... args) {
		
		String output = new String("What would you like to do?\n\n [1] Play a random match\n [2] Play a match\n [3] Guide your team to glory\n [4] Show selection\n\n");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(output);
		
		int choice = sc.nextInt();
		
		switch (choice){
			case 1:
			
			System.out.println("\n");
			new Thread(new RandomMatch()).run();
			main();
			
			case 2:
				
			System.out.println("\n");
			new Thread(new Match()).run();
			main();
			
			case 3:
			
			System.out.println("\n");
			new Thread(new ToGlory()).run();
			
			case 4:
			
			System.out.println("\n");
			Competition x = new Competition(null);
			x.display();
			
			
			default:
				System.out.println("Please input an integer 1 to 4..\n\n");
				main();
				
		
		}
		
		
	}
	
	
	
}
