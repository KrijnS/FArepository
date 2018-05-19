import java.util.Arrays;
import java.util.List;

public class Goalkeeper {
	int alg;
	int goalkeeping;
	String name;
	int number;
	int age;
	int cleanSheets;
	int value;
	String position;
	String photoPath;
	
	public Goalkeeper(int alg, int goalkeeping, String name, int number, int age, int cleanSheets, int value, String position, String photoPath) {
		this.alg = alg;
		this.goalkeeping = goalkeeping;
		this.name = name;
		this.number = number;
		this.age = age;
		this.cleanSheets = cleanSheets;
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

	public int getCleanSheets() {
		return cleanSheets;
	}

	public void setCleanSheets(int cleanSheets) {
		this.cleanSheets = cleanSheets;
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

	public int getGoalkeeping() {
		return goalkeeping;
	}

	public void setGoalkeeping(int goalkeeping) {
		this.goalkeeping = goalkeeping;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int value(Goalkeeper x) {
		List<Double> doubles = Arrays.asList(1.0, 0.95, 0.90, 0.85, 0.75, 0.65, 0.55, 0.5, 0.45, 0.35, 0.3, 0.25, 0.2, 0.15, 0.10, 0.05, 0.04, 0.03, 0.02);
		double alg = (double) x.getAlg();
		double z = Math.pow(1.1, alg);
		for(int i = 18; i < 37; i++) {
			int calc = 0;
			if(x.getAge() == i) {
				z = z/95.0;
				z = z * doubles.get(calc);
			}
			calc++;
		}
		
		if(x.getAge() > 36) {
			z = z * 0.01;
		}
		
		if(x.getAge() < 19) {
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
		
		if(x.getAlg() > 87) {
			z = z * 1.15;
		}
		
		if(x.getAge() > 34) {
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
