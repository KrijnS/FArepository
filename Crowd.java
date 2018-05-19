import java.io.FileInputStream;
import java.io.FileNotFoundException;


import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Crowd implements Runnable{
	
	AdvancedPlayer player; 
	
	@Override
	public void run() {
			try {
				FileInputStream fileInputStream = new FileInputStream("C:/Users/Krijn/Downloads/Football App/Sounds/crowd.mp3");
				AdvancedPlayer player = new AdvancedPlayer(fileInputStream);
				this.player = player;
				player.play();
				player.stop();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JavaLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	public void stop() {
		player.close();
	}

	public AdvancedPlayer getPlayer() {
		return player;
	}

	public void setPlayer(AdvancedPlayer player) {
		this.player = player;
	}

}
