import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Design {
	int time = 0;
	String currentTeam;
	String currentLeague;
	Competition competition = Competition.readCompetition();
	List<Team> teams = null;
	Competition special = new Competition(teams);
	JLabel logoLabel;
	JLabel backGround;

	Method init = new Method();

	Random rand = new Random();
	
	PickTeam pick = new PickTeam();
	
	ShowTeam show = new ShowTeam();

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Design window = new Design();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Design() {
		initialize();
		Thread music = new Thread(new Music());
		music.start();
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ImageIcon icon = new ImageIcon(getClass().getResource("championsIcon.png"));
		
		frame = new JFrame("Football App");
		frame.setBounds(100, 100, 510, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setIconImage(icon.getImage());

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// JButton ten = new JButton();
		// JButton nine = new JButton();
		// JButton eight = new JButton();
		// JButton seven = new JButton();
		// JButton six = new JButton();
		// JButton five= new JButton();
		// JButton four = new JButton();
		// JButton three= new JButton();
		// JButton two = new JButton();
		// JButton one = new JButton();

		// show523(ten, nine, eight, seven, six, five,four, three, two, one);

		// frame.getContentPane().add(ten);
		// frame.getContentPane().add(nine);
		// frame.getContentPane().add(eight);
		// frame.getContentPane().add(seven);
		// frame.getContentPane().add(six);
		// frame.getContentPane().add(five);
		// frame.getContentPane().add(four);
		// frame.getContentPane().add(three);
		// frame.getContentPane().add(two);
		// frame.getContentPane().add(one);

		mainMenu();
	}
	
	public void mainMenu() {
		JButton match = new JButton("Play Match");
		match.setBounds(128, 395, 254, 67);
		buttonDesign(match);

		JButton toGlory = new JButton("To Glory Mode");
		toGlory.setBounds(128, 519, 254, 67);
		buttonDesign(toGlory);

		JButton showSelection = new JButton("Show Selection");
		showSelection.setBounds(128, 640, 254, 67);
		buttonDesign(showSelection);

		JButton randomMatch = new JButton("Play Random Match");
		randomMatch.setBounds(128, 274, 254, 67);
		buttonDesign(randomMatch);

		frame.getContentPane().add(randomMatch);

		frame.getContentPane().add(match);
		frame.getContentPane().add(toGlory);
		frame.getContentPane().add(showSelection);

		ImageIcon logo = new ImageIcon(getClass().getResource("footballLogo.png"));

		ImageIcon background = new ImageIcon(getClass().getResource("background.jpg"));

		logoLabel = new JLabel(logo);
		logoLabel.setBounds(107, 45, 312, 159);
		frame.getContentPane().add(logoLabel);
		backGround = new JLabel(background);
		backGround.setBounds(0, 0, 504, 733);
		frame.getContentPane().add(backGround);

		randomMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.getContentPane().removeAll();

				Font btnFont = new Font("Trebuchet MS", Font.BOLD, 18);
				Font scoreFont = new Font("Trebuchet MS", Font.BOLD, 24);
				Color textColor = Color.white;

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

				Team one = init.getRandomTeam(competition);
				Team two = init.getRandomTeam(competition);

				while (one.equals(two)) {
					two = init.getRandomTeam(competition);
				}

				JLabel team1Label = new JLabel("<html>" + one.getName() + "</html>");
				team1Label.setHorizontalAlignment(SwingConstants.CENTER);
				team1Label.setBounds(87, 284, 154, 56);
				team1Label.setFont(btnFont);
				team1Label.setForeground(textColor);

				JLabel team2Label = new JLabel("<html>" + two.getName() + "</html>");
				team2Label.setHorizontalAlignment(SwingConstants.CENTER);
				team2Label.setBounds(263, 284, 154, 56);
				team2Label.setFont(btnFont);
				team2Label.setForeground(textColor);

				JLabel scoreLabel = new JLabel(one.getScore() + " - " + two.getScore());
				scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
				scoreLabel.setBounds(196, 217, 107, 44);
				scoreLabel.setFont(scoreFont);
				scoreLabel.setForeground(textColor);

				ImageIcon wrongSize = new ImageIcon(getClass().getResource(one.getLogoPath()));
				Image img = wrongSize.getImage();
				Image newImg = img.getScaledInstance(63, 67, Image.SCALE_SMOOTH);
				ImageIcon logo = new ImageIcon(newImg);
				JLabel clubLogo = new JLabel(logo);
				clubLogo.setBounds(12, 276, 63, 67);

				ImageIcon wrongSize2 = new ImageIcon(getClass().getResource(two.getLogoPath()));
				Image img2 = wrongSize2.getImage();
				Image newImg2 = img2.getScaledInstance(63, 67, Image.SCALE_SMOOTH);
				ImageIcon logo2 = new ImageIcon(newImg2);
				JLabel clubLogo2 = new JLabel(logo2);
				clubLogo2.setBounds(429, 276, 63, 67);
			
			
				frame.getContentPane().add(team1Label);
				frame.getContentPane().add(team2Label);
				frame.getContentPane().add(scoreLabel);
				frame.getContentPane().add(clubLogo);
				frame.getContentPane().add(clubLogo2);
				frame.getContentPane().add(firstScenario);
				frame.getContentPane().add(secondScenario);
				frame.getContentPane().add(logoLabel);
				frame.getContentPane().add(backGround);
				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
			}
		});

		showSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTeams();
			}
		});

		toGlory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseTeam();
			}
		});

		match.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseTeam();
			}
		});
		
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
	}

	public static void buttonDesign(JButton btn) {

		Color textColor = Color.white;
		Color backgroundColor = new Color(55, 60, 65);
		Color backgroundColorHover = new Color(70, 75, 80);

		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.setHorizontalAlignment(JButton.CENTER);

		btn.setBackground(backgroundColor);
		btn.setForeground(textColor);
		btn.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent event) {
				if (btn.getModel().isRollover()) {
					btn.setBackground(backgroundColorHover);
				} else {
					btn.setBackground(backgroundColor);
				}
			}

		});
		Font btnFont = new Font("Trebuchet MS", Font.PLAIN, 18);
		btn.setFont(btnFont);
	}




	public void chooseTeam() {
		Container pane = frame.getContentPane();
		Design des = this;
		pick.pickAll(pane, backGround, logoLabel, des);
	}
	
	public void showTeams() {
		Container pane = frame.getContentPane();
		Design des = this;
		show.showAll(pane, backGround, logoLabel, des);
	}
}
