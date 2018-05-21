package Sounds;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Music implements Runnable{

	@Override
	public void run() {
		try {
			File music = new File("C:/Users/Krijn/Downloads/Football App/Text files/music.txt");
			Scanner in = new Scanner(music);
			List<String> songs = new ArrayList<>();
			while(in.hasNextLine()) {
				songs.add(in.nextLine());
			}
			Random rand = new Random();
			for(int i = 0; i < songs.size(); i++) {
				int choice = rand.nextInt(songs.size()) + 0;
				FileInputStream fileInputStream = new FileInputStream(songs.get(choice));
				Player player = new Player(fileInputStream);
				player.play();
				songs.remove(choice);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}