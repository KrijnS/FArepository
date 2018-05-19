import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {
	int alg;
	int attack;
	int defense;
	String name;
	int number;
	int age;
	int goals;
	int value;
	String position;
	String photoPath;
	
	public Player(int alg, int attack, int defense, String name, int number, int age, int goals, int value, String position, String photoPath) {
		this.alg = alg;
		this.attack = attack;
		this.defense = defense;
		this.name = name;
		this.number = number;
		this.age = age;
		this.goals = goals;
		this.value = value;
		this.position = position;
		this.photoPath = photoPath;
	}
	
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getAlg() {
		return alg;
	}

	public void setAlg(int alg) {
		this.alg = alg;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int worth() {
		int worth;
		
		Random rand = new Random();
		
		List<Integer> multiplier = new ArrayList<>();
		
		for(int i = 850000; i < 1500000; i = i + 50000) {
			multiplier.add(i);
		}
		
		int multiply = rand.nextInt(13) + 0;
		
		worth = getValue() * multiplier.get(multiply);
		
		return worth;
	}
	
	public int value(Player x) {
		List<Double> doubles = Arrays.asList(1.0, 0.95, 0.90, 0.85, 0.75, 0.65, 0.55, 0.5, 0.45, 0.35, 0.3, 0.25, 0.2, 0.15, 0.10, 0.05, 0.04, 0.03, 0.02);
		double alg = (double) x.getAlg();
		double z = Math.pow(1.1, alg);
		z = z/95.0;
		for(int i = 18; i < 37; i++) {
			int calc = 0;
			if(x.getAge() == i) {
				z = z * doubles.get(calc);
			}
			calc++;
		}
		
		if(x.getAge() > 37) {
			z = z * 0.01;
		}
		
		if(x.getAge() < 18) {
			z = z * 1.1;
		}
		
		if(x.getAlg() < 80) {
			z = z * 0.33;
		}
		
		if(x.getAlg() < 83 && x.getAlg() > 79) {
			z = z * 0.5;
		}
		
		if(x.getAlg() < 85 && x.getAlg() > 82) {
			z = z * 0.8;
		}
		
		if(x.getAge() < 21) {
			z = z * 1.3;
		}
		
		if(x.getAge() > 20 && x.getAge() < 24) {
			z = z * 1.15;
		}
		
		if(x.getAttack() > 88 || x.getDefense() > 88) {
			z = z * 1.25;
		}
		
		if(x.getAttack() > 83 && x.getDefense() > 83) {
			z = z * 1.5;
		}
		
		if(x.getAge() > 34 && x.getAge() < 37) {
			z = z * 0.33;
		}
		
		if(x.getAge() < 35 && x.getAge() > 33) {
			z = z * 0.75;
		}
		
		if(x.getAge() < 34 && x.getAge() > 30) {
			z = z * 0.85;
		}
		int c = (int) z;
		return c;
	}
}
