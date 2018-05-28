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
import java.io.FileInputStream;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import InitTeam.Competition;
import InitTeam.Goalkeeper;
import InitTeam.Player;
import InitTeam.Team;


public class Transfer {
	String currentPlayer;
	Competition competition = Competition.readCompetition();
	
	Font font = initFont();
	Color textColor = Color.white;
	Font searchFont = initSearchFont();
	Color searchColor = Color.lightGray;
	
	public void transferWindow(JLabel backGround, JLabel logoLabel, Container pane, Design des, String input) {
		pane.removeAll();
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(387, 28, 105, 34);
		Design.buttonDesign(backButton);
		

		JTextField playerName = new JTextField("Insert player name");
		playerName.setBounds(40, 310, 350, 35);
		String inName = playerName.getText();
		searchTextfield(playerName, inName, pane);
		playerName.setHorizontalAlignment(JTextField.CENTER);
		
		JTextField minAlg = new JTextField("Minimum Overall: 1");
		minAlg.setBounds(40, 380, 170, 35);
		String inMinAlg = minAlg.getText();
		searchTextfield(minAlg, inMinAlg, pane);
		
		JTextField maxAlg = new JTextField("Maximum Overall: 99");
		maxAlg.setBounds(270, 380, 170, 35);
		String inMaxAlg = maxAlg.getText();
		searchTextfield(maxAlg, inMaxAlg, pane);
		
		JTextField minAtt = new JTextField("Minimum Attack: 1");
		minAtt.setBounds(40, 440, 170, 35);
		String inMinAtt = minAtt.getText();
		searchTextfield(minAtt, inMinAtt, pane);
		
		JTextField maxAtt = new JTextField("Maximum Attack: 99");
		maxAtt.setBounds(270, 440, 170, 35);
		String inMaxAtt = maxAtt.getText();
		searchTextfield(maxAtt, inMaxAtt, pane);
		
		JTextField minDef = new JTextField("Minimum Defense: 1");
		minDef.setBounds(40, 500, 170, 35);
		String inMinDef = minDef.getText();
		searchTextfield(minDef, inMinDef, pane);
		
		JTextField maxDef = new JTextField("Maximum Defense: 99");
		maxDef.setBounds(270, 500, 170, 35);
		String inMaxDef = maxDef.getText();
		searchTextfield(maxDef, inMaxDef, pane);
		
		JTextField minAge = new JTextField("Minimum Age: 15");
		minAge.setBounds(40, 560, 170, 35);
		String inMinAge = minAge.getText();
		searchTextfield(minAge, inMinAge, pane);
		
		JTextField maxAge = new JTextField("Maximum Age: 99");
		maxAge.setBounds(270, 560, 170, 35);
		String inMaxAge = maxAge.getText();
		searchTextfield(maxAge, inMaxAge, pane);
		
		JTextField minVal = new JTextField("Minimum Value: 100000");
		minVal.setBounds(40, 620, 190, 35);
		String inMinVal = minVal.getText();
		searchTextfield(minVal, inMinVal, pane);
		
		JTextField maxVal = new JTextField("Maximum Value: 15000000");
		maxVal.setBounds(270, 620, 210, 35);
		String inMaxVal = maxVal.getText();
		searchTextfield(maxVal, inMaxVal, pane);
		
		JTextField position = new JTextField("Position: ");
		position.setBounds(40, 670, 400, 35);
		String inPos = position.getText();
		searchTextfield(position, inPos, pane);
		position.setHorizontalAlignment(JTextField.CENTER);
		
		position.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

				if (position.getText().equals("DM")) {
					minDef.setText("-");
					minDef.setEditable(false);
					maxDef.setText("-");
					maxDef.setEditable(false);
					minAtt.setEditable(false);
					minAtt.setText("-");
					maxAtt.setEditable(false);
					maxAtt.setText("-");
				}
				
				if (!(position.getText().equals("DM"))) {
					minDef.setText("Minimum Defense: 1");
					minDef.setEditable(true);
					maxDef.setText("Maximum Defense: 99");
					maxDef.setEditable(true);
					minAtt.setEditable(true);
					minAtt.setText("Minimum Attack: 1");
					maxAtt.setEditable(true);
					maxAtt.setText("Maximum Attack: 99");
				}
			}

			public void removeUpdate(DocumentEvent e) {

				if (position.getText().equals("DM")) {
					minDef.setText("-");
					minDef.setEditable(false);
					maxDef.setText("-");
					maxDef.setEditable(false);
					minAtt.setEditable(false);
					minAtt.setText("-");
					maxAtt.setEditable(false);
					maxAtt.setText("-");
				}
				
				if (!(position.getText().equals("DM"))) {
					minDef.setText("Minimum Defense: 1");
					minDef.setEditable(true);
					maxDef.setText("Maximum Defense: 99");
					maxDef.setEditable(true);
					minAtt.setEditable(true);
					minAtt.setText("Minimum Attack: 1");
					maxAtt.setEditable(true);
					maxAtt.setText("Maximum Attack: 99");
				}
			}

			public void insertUpdate(DocumentEvent e) {

				if (position.getText().equals("DM")) {
					minDef.setText("-");
					minDef.setEditable(false);
					maxDef.setText("-");
					maxDef.setEditable(false);
					minAtt.setEditable(false);
					minAtt.setText("-");
					maxAtt.setEditable(false);
					maxAtt.setText("-");
				}
				
				if (!(position.getText().equals("DM"))) {
					minDef.setText("Minimum Defense: 1");
					minDef.setEditable(true);
					maxDef.setText("Maximum Defense: 99");
					maxDef.setEditable(true);
					minAtt.setEditable(true);
					minAtt.setText("Minimum Attack: 1");
					maxAtt.setEditable(true);
					maxAtt.setText("Maximum Attack: 99");
				}
			}
		});
		
		JButton lockPlayerName = new JButton("Search");
		lockPlayerName.setBounds(390, 310, 100, 32);
		Design.buttonDesign(lockPlayerName);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				des.mainMenu();
			}
		});
		
		lockPlayerName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
							
				int minAlgInt = 0;
				
				if(!(inMinAlg.equals(minAlg.getText()))) {
					minAlgInt = Integer.parseInt(minAlg.getText());
				}
				
				int maxAlgInt = 100;
				
				if(!(inMaxAlg.equals(maxAlg.getText()))) {
					maxAlgInt = Integer.parseInt(maxAlg.getText());
				}
				
				int minAttInt = 0;
				
				if(!(inMinAtt.equals(minAtt.getText()) || minAtt.getText().equals("-"))) {
					minAttInt = Integer.parseInt(minAtt.getText());
				}
				
				int maxAttInt = 100;
				
				if(!(inMaxAtt.equals(maxAtt.getText()) || maxAtt.getText().equals("-"))) {
					maxAttInt = Integer.parseInt(maxAtt.getText());
				}	
				
				int minDefInt = 0;
				
				if(!(inMinDef.equals(minDef.getText()) || minDef.getText().equals("-"))) {
					minDefInt = Integer.parseInt(minDef.getText());
				}
				
				int maxDefInt = 100;
				
				if(!(inMaxDef.equals(maxDef.getText()) || maxDef.getText().equals("-"))) {
					maxDefInt = Integer.parseInt(maxDef.getText());
				}
			
				int minAgeInt = 0;
				
				if(!(inMinAge.equals(minAge.getText()))) {
					minAgeInt = Integer.parseInt(minAge.getText());
				}
				
				int maxAgeInt = 100;
				
				if(!(inMaxAge.equals(maxAge.getText()))) {
					maxAgeInt = Integer.parseInt(maxAge.getText());
				}

				int minValInt = 0;
				
				if(!(inMinVal.equals(minVal.getText()))) {
					minValInt = Integer.parseInt(minVal.getText());
				}
				
				int maxValInt = 1000000000;
				
				if(!(inMaxVal.equals(maxVal.getText()))) {
					maxValInt = Integer.parseInt(maxVal.getText());
				}

				String pos = "";
				
				if(!(inPos.equals(position.getText()))) {
					pos = position.getText();
				}
				
				String name = "";
				
				if(!(inName.equals(playerName.getText()))) {
					name = playerName.getText();
				}
				
				JPanel container = new JPanel();
				container.setOpaque(false);
				GridLayout gl = new GridLayout(0, 1, 10, 30);
				container.setLayout(gl);
				
				// get the users from the user file
				String[] players = Competition.playerFilter(minAlgInt, maxAlgInt, minAttInt, maxAttInt, minDefInt, maxDefInt, minAgeInt, maxAgeInt, minValInt, maxValInt, pos, name);
				Arrays.sort(players, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

				// get the amount of users
				int i = players.length;

				// check if the amount of users is greater than zero
				if (i <= 0) {
					JOptionPane.showMessageDialog(pane,
							"There were no players found.\nPlease search a different name.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} 
				else if (i > 50) {
					JOptionPane.showMessageDialog(pane,
							"There were too many players found.\nPlease refine your search.", "Error",
							JOptionPane.WARNING_MESSAGE);
				}else {
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
							
							// add the actionlistener for the buttons
							btn_user.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									currentPlayer = player;

									Player chosenPlayer = Competition.playerFromName(currentPlayer).get(0);

									showPlayer(chosenPlayer, pane, des, logoLabel, backGround, input);

								}
							});
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
							btn_user.setIcon(leagueLogo);
							btn_user.add(logoMini);
							btn_user.setIconTextGap(50);
							btn_user.setHorizontalAlignment(SwingConstants.LEFT);
							
							
							btn_user.setPreferredSize(new Dimension(350, 50));
							container.add(btn_user);
							
							// add the actionlistener for the buttons
							btn_user.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									currentPlayer = player;

									Goalkeeper keeper = Competition.keeperFromName(currentPlayer).get(0);

									showKeeper(pane, keeper, des, logoLabel, backGround, input);

								}
							});
						}
						i = i - 1;

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
		
		if(input != null) {
			playerName.setText(input);
			lockPlayerName.doClick();
		}
		
		pane.add(lockPlayerName);
		pane.add(playerName);
		pane.add(logoLabel);
		pane.add(backButton);
		pane.add(backGround);
		
		pane.revalidate();
		pane.repaint();
	}
	
	public void showKeeper(Container pane, Goalkeeper x, Design des, JLabel logoLabel, JLabel backGround, String input) {		
		String teamName = Competition.getTeamNameKeeper(x);
		Team z = Competition.getTeamFromName(teamName, competition);

		pane.removeAll();
		
		Image clubImg = null;
		try {
			String getPng = "/Users/Krijn/Downloads/Football App/Club Logo/" + z.getLogoPath();
			File clubFile = new File(getPng);
			clubImg = ImageIO.read(clubFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Image newClubImg = clubImg.getScaledInstance(134, 134, Image.SCALE_SMOOTH);
		ImageIcon logo = new ImageIcon(newClubImg);
		JLabel clubLogo = new JLabel(logo);
		clubLogo.setBounds(30, 470, 134, 134);

		Image img = null;
		try {
			URL url = new URL(x.getPhotoPath());
			img = ImageIO.read(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Image newImg = img.getScaledInstance(134, 134, Image.SCALE_SMOOTH);
		ImageIcon photo = new ImageIcon(newImg);
		JLabel playerPhoto = new JLabel(photo);
		playerPhoto.setBounds(30, 300, 134, 134);

		pane.add(playerPhoto);
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(387, 28, 105, 34);
		Design.buttonDesign(backButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferWindow(backGround, logoLabel, pane, des, input);
			}
		});

		JLabel nameNumber = new JLabel("#" + x.getNumber() + " " + x.getName());
		nameNumber.setBounds(135, 285, 322, 51);

		JLabel positions = new JLabel("Positions: " + x.getPosition());
		positions.setBounds(135, 365, 322, 51);
		positions.setOpaque(false);
		positions.setForeground(textColor);
		positions.setFont(font);
		positions.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel team = new JLabel(z.getName());
		team.setBounds(135, 445, 322, 51);

		JLabel value = new JLabel();
		value.setBounds(135, 525, 322, 51);
		
		if(x.getName().equals("Icons")) {
			value.setText("Value:  -");
		}
		else{
			value.setText("Value: " + x.getValue()  + ".000.000");
		}

		JLabel ageAttackDefense = new JLabel("Age: " + x.getAge() + " Goalkeeping: " + x.getGoalkeeping());
		ageAttackDefense.setBounds(135, 605, 322, 51);

		nameNumber.setOpaque(false);
		team.setOpaque(false);
		value.setOpaque(false);
		ageAttackDefense.setOpaque(false);

		nameNumber.setForeground(textColor);
		team.setForeground(textColor);
		value.setForeground(textColor);
		ageAttackDefense.setForeground(textColor);

		nameNumber.setFont(font);
		team.setFont(font);
		value.setFont(font);
		ageAttackDefense.setFont(font);

		nameNumber.setHorizontalAlignment(SwingConstants.CENTER);
		team.setHorizontalAlignment(SwingConstants.CENTER);
		value.setHorizontalAlignment(SwingConstants.CENTER);
		ageAttackDefense.setHorizontalAlignment(SwingConstants.CENTER);

		pane.add(clubLogo);
		pane.add(nameNumber);
		pane.add(team);
		pane.add(backButton);
		pane.add(value);
		pane.add(ageAttackDefense);
		pane.add(positions);
		pane.add(logoLabel);
		pane.add(backGround);

		pane.revalidate();
		pane.repaint();
	}
	
	public void showPlayer(Player x, Container pane, Design des, JLabel logoLabel, JLabel backGround, String input) {
		pane.removeAll();
		
		String teamName = Competition.getTeamNamePlayer(x);
		Team z = Competition.getTeamFromName(teamName, competition);
		
		Image img = null;
		try {
			String getPng = "/Users/Krijn/Downloads/Football App/Club Logo/" + z.getLogoPath();
			File clubFile = new File(getPng);
			img = ImageIO.read(clubFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Image newImg = img.getScaledInstance(134, 134, Image.SCALE_SMOOTH);
		ImageIcon logo = new ImageIcon(newImg);
		JLabel clubLogo = new JLabel(logo);
		clubLogo.setBounds(30, 470, 134, 134);
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(387, 28, 105, 34);
		Design.buttonDesign(backButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferWindow(backGround, logoLabel, pane, des, input);
			}
		});

		Image image = null;
		try {
			URL url = new URL(x.getPhotoPath());
			image = ImageIO.read(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Image newImage = image.getScaledInstance(134, 134, Image.SCALE_SMOOTH);
		ImageIcon playerPicture = new ImageIcon(newImage);
		JLabel playerPhoto = new JLabel(playerPicture);
		playerPhoto.setBounds(30, 300, 134, 134);

		JLabel nameNumber = new JLabel("#" + x.getNumber() + " " + x.getName());
		nameNumber.setBounds(135, 305, 322, 51);

		JLabel positions = new JLabel("Positions: " + x.getPosition());
		positions.setBounds(135, 385, 322, 51);
		positions.setOpaque(false);
		positions.setForeground(textColor);
		positions.setFont(font);
		positions.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel team = new JLabel(z.getName());
		team.setBounds(135, 465, 322, 51);
		
		JLabel value = new JLabel();
		value.setBounds(135, 545, 322, 51);

		if(z.getName().equals("Icons")) {
			value.setText("Value:  -");
		}
		else{
			value.setText("Value: " + x.getValue()  + ".000.000");
		}

		JLabel ageAttackDefense = new JLabel(
				"Age: " + x.getAge() + " Attack: " + x.getAttack() + " Defense: " + x.getDefense());
		ageAttackDefense.setBounds(135, 625, 322, 51);
		
		nameNumber.setOpaque(false);
		team.setOpaque(false);
		value.setOpaque(false);
		ageAttackDefense.setOpaque(false);

		nameNumber.setForeground(textColor);
		team.setForeground(textColor);
		value.setForeground(textColor);
		ageAttackDefense.setForeground(textColor);

		nameNumber.setFont(font);
		team.setFont(font);
		value.setFont(font);
		ageAttackDefense.setFont(font);

		nameNumber.setHorizontalAlignment(SwingConstants.CENTER);
		team.setHorizontalAlignment(SwingConstants.CENTER);
		value.setHorizontalAlignment(SwingConstants.CENTER);
		ageAttackDefense.setHorizontalAlignment(SwingConstants.CENTER);

		pane.add(clubLogo);
		pane.add(nameNumber);
		pane.add(backButton);
		pane.add(playerPhoto);
		pane.add(positions);
		pane.add(team);
		pane.add(value);
		pane.add(ageAttackDefense);
		pane.add(logoLabel);
		pane.add(backGround);
		
		pane.revalidate();
		pane.repaint();

	}
	
	public Font initFont() {
		ArrayList<Font> fonts = new ArrayList<>();
		
		
		try {
			File fontFile = new File("/Users/Krijn/Downloads/Football App/Text files/Champions-Bold.ttf");
			Font btnFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(fontFile)).deriveFont(Font.BOLD, 20);
			fonts.add(btnFont);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return fonts.get(0);
	}
	
	public Font initSearchFont() {
		ArrayList<Font> fonts = new ArrayList<>();
		
		
		try {
			File fontFile = new File("/Users/Krijn/Downloads/Football App/Text files/Champions-Bold.ttf");
			Font btnFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(fontFile)).deriveFont(Font.ITALIC, 18);
			fonts.add(btnFont);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return fonts.get(0);
	}
	
	public void searchTextfield(JTextField x, String input, Container pane) {
		x.setHorizontalAlignment(JTextField.LEFT);
		x.setOpaque(false);
		x.setBorder(null);
		x.setFont(searchFont);
		x.setForeground(searchColor);
		
		x.addFocusListener(new FocusListener() {
		public void focusGained(FocusEvent e) {
	        if (x.getText().equals(input)) {
	        	x.setText("");
	        	x.setForeground(Color.BLACK);
	        }
	    }
	    public void focusLost(FocusEvent e) {
	        if (x.getText().isEmpty()) {
	        	x.setForeground(Color.LIGHT_GRAY);
	        	x.setText(input);
	        }
	    }
	    });		
		
		pane.add(x);
	}
}
