package Sounds;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Goal implements Runnable{

	@Override
	public void run() {
		try {
			FileInputStream fileInputStream = new FileInputStream("C:/Users/Krijn/Downloads/Football App/Sounds/cheer.mp3");
			Player player = new Player(fileInputStream);
			player.play();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}