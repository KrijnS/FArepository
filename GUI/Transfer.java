package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import InitTeam.Competition;
import InitTeam.Goalkeeper;
import InitTeam.Player;
import InitTeam.Team;


public class Transfer {
	
	public void transferWindow(JLabel backGround, JLabel logoLabel, Container pane, Design des) {
		pane.removeAll();
		
		Font font = new Font("Trebuchet MS", Font.PLAIN, 18);
		Color textColor = Color.white;
		Font searchFont = new Font("Trebuchet MS", Font.ITALIC, 16);
		Color searchColor = Color.GRAY;
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(387, 28, 105, 34);
		Design.buttonDesign(backButton);
		
		JTextField playerName = new JTextField("Insert player name");
		playerName.setBounds(40, 310, 350, 35);
		playerName.setHorizontalAlignment(JTextField.CENTER);
		playerName.setOpaque(false);
		playerName.setBorder(null);
		playerName.setFont(searchFont);
		playerName.setForeground(searchColor);
		
		JButton lockPlayerName = new JButton("Search");
		lockPlayerName.setBounds(390, 310, 100, 32);
		Design.buttonDesign(lockPlayerName);
		
		playerName.addFocusListener(new FocusListener() {
		public void focusGained(FocusEvent e) {
	        if (playerName.getText().equals("Insert player name")) {
	        	playerName.setText("");
	        	playerName.setForeground(Color.BLACK);
	        }
	    }
	    public void focusLost(FocusEvent e) {
	        if (playerName.getText().isEmpty()) {
	        	playerName.setForeground(Color.GRAY);
	        	playerName.setText("Insert player name");
	        }
	    }
	    });		
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				des.mainMenu();
			}
		});

		lockPlayerName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				String input = playerName.getText();
				
				JPanel container = new JPanel();
				container.setOpaque(false);
				GridLayout gl = new GridLayout(0, 1, 10, 30);
				container.setLayout(gl);
				
				// get the users from the user file
				String[] players = Competition.playersContaining(input);
				Arrays.sort(players, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

				// get the amount of users
				int i = players.length;

				// check if the amount of users is greater than zero
				if (i <= 0) {
					JOptionPane.showMessageDialog(pane,
							"There were no players found.\nPlease search a different name.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					// add buttons for each user with their own name
					do {
						// get the username
						String player = players[i - 1];
						//JButton btn_user = new JButton(player);
						//btn_user.setOpaque(false);
						//btn_user.setFont(font);
						//btn_user.setContentAreaFilled(false);
						//btn_user.setForeground(textColor);
						//btn_user.setBorder(null);
						
						ArrayList<Player> playerList = Competition.playerFromName(player);
						if(!(playerList.isEmpty())) {
							Player newPlayer = playerList.get(0);
							String display = "Ovr: " + newPlayer.getAlg() + "    " + player;
							JButton btn_user = new JButton(display);
							btn_user.setOpaque(false);
							btn_user.setFont(font);
							btn_user.setContentAreaFilled(false);
							btn_user.setForeground(textColor);
							btn_user.setBorder(null);
							Image img = null;
							try {
								URL url = new URL(newPlayer.getPhotoPath());
								img = ImageIO.read(url);
							} catch (MalformedURLException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							Image imgNew = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
							ImageIcon leagueLogo = new ImageIcon(imgNew);
							JLabel logoMini = new JLabel(leagueLogo);
							logoMini.setSize(50, 50);
							btn_user.setIcon(leagueLogo);
							btn_user.setIconTextGap(50);
							btn_user.setHorizontalAlignment(SwingConstants.LEFT);
							
							btn_user.setPreferredSize(new Dimension(350, 50));
							container.add(btn_user);
						}

						ArrayList<Goalkeeper> keeperList = Competition.keeperFromName(player);
						if(!(keeperList.isEmpty())) {
							Goalkeeper newKeeper = keeperList.get(0);
							
							String display = "Ovr: " + newKeeper.getAlg() + "    " + player;
							JButton btn_user = new JButton(display);
							btn_user.setOpaque(false);
							btn_user.setFont(font);
							btn_user.setContentAreaFilled(false);
							btn_user.setForeground(textColor);
							btn_user.setBorder(null);
							
							Image img = null;
							try {
								URL url = new URL(newKeeper.getPhotoPath());
								img = ImageIO.read(url);
							} catch (MalformedURLException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							Image imgNew = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
							ImageIcon leagueLogo = new ImageIcon(imgNew);
							JLabel logoMini = new JLabel(leagueLogo);
							logoMini.setSize(50, 50);
							btn_user.add(logoMini);
							
							btn_user.setPreferredSize(new Dimension(350, 50));
							container.add(btn_user);
						}
						//Image img = null;
						//try {
						//	String getPng = "/Users/Krijn/Downloads/Football App/League Logo/" + league + ".png";
						//	File leagueFile = new File(getPng);
						//	img = ImageIO.read(leagueFile);
						//} catch (MalformedURLException e1) {
						//	// TODO Auto-generated catch block
						//	e1.printStackTrace();
						//} catch (IOException e1) {
						//	// TODO Auto-generated catch block
						//	e1.printStackTrace();
						//}
						//Image imgNew = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
						//ImageIcon leagueLogo = new ImageIcon(imgNew);
						//JLabel logoMini = new JLabel(leagueLogo);
						//logoMini.setSize(50, 50);
						//btn_user.add(logoMini);
						
						//btn_user.setPreferredSize(new Dimension(350, 50));
						//container.add(btn_user);
						i = i - 1;

						// add the actionlistener for the buttons
						//btn_user.addActionListener(new ActionListener() {
						//	public void actionPerformed(ActionEvent e) {
						//		currentPlayer = league;

						//		special = Competition.readSpecialComp(currentLeague);

						//		showTeams(backGround, logoLabel, special, pane, des);

						//	}
						//});

					}

					while (i > 0);

				}

				container.setBounds(43, 350, 450, 356);
				container.setOpaque(false);

				// create a scrollpane with container in it
				JScrollPane scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrollPane.getVerticalScrollBar().setOpaque(false);
				scrollPane.setBounds(43, 350, 450, 356);
				scrollPane.setOpaque(false);
				scrollPane.getViewport().setOpaque(false);
				scrollPane.setBorder(null);
				scrollPane.getViewport().setBorder(null);
				
				pane.add(scrollPane);
				pane.add(lockPlayerName);
				pane.add(playerName);
				pane.add(logoLabel);
				pane.add(backButton);
				pane.add(backGround);
				
				pane.revalidate();
				pane.repaint();
			}
			
		});
		
		pane.add(lockPlayerName);
		pane.add(playerName);
		pane.add(logoLabel);
		pane.add(backButton);
		pane.add(backGround);
		
		pane.revalidate();
		pane.repaint();
	}
}
