package GUI;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import InitMisc.Method;
import InitTeam.Competition;
import InitTeam.Goalkeeper;
import InitTeam.Player;
import InitTeam.Team;



public class ShowTeam {
	
Formations form = new Formations();
	
	Method init = new Method();
	
	String currentTeam;
	String currentLeague;
	Competition competition = Competition.readCompetition();
	List<Team> teams = null;
	Competition special = new Competition(teams);
	
	Font font = new Font("Trebuchet MS", Font.PLAIN, 18);
	Color textColor = Color.white;

	public void showAll(Container pane, JLabel backGround, JLabel logoLabel, Design des) {
		showLeagues(pane, des, logoLabel, backGround);
	}
	

	public void showKeeper(Team x, Goalkeeper z, JLabel clubLogo, JLabel backGround, JLabel logoLabel, Container pane, Design des) {		
		JButton backButton = new JButton("Back");
		backButton.setBounds(387, 28, 105, 34);
		Design.buttonDesign(backButton);

		JButton keeper = new JButton(
				"#" + x.getGoalkeepers().get(0).getNumber() + " " + x.getGoalkeepers().get(0).getName());

		keeper.setOpaque(false);
		keeper.setBorder(null);
		keeper.setContentAreaFilled(false);
		keeper.setForeground(textColor);
		keeper.setFont(font);
		keeper.setHorizontalAlignment(SwingConstants.CENTER);
		keeper.setBounds(150, 659, 214, 44);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFormation(x, backGround, logoLabel, clubLogo, pane, des, special);
			}
		});

		pane.add(keeper);

		keeper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();

				pane.add(clubLogo);

				Image img = null;
				try {
					URL url = new URL(z.getPhotoPath());
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

				JLabel nameNumber = new JLabel("#" + z.getNumber() + " " + z.getName());
				nameNumber.setBounds(135, 285, 322, 51);

				JLabel positions = new JLabel("Positions: " + z.getPosition());
				positions.setBounds(135, 365, 322, 51);
				positions.setOpaque(false);
				positions.setForeground(textColor);
				positions.setFont(font);
				positions.setHorizontalAlignment(SwingConstants.CENTER);

				JLabel team = new JLabel(x.getName());
				team.setBounds(135, 445, 322, 51);

				JLabel value = new JLabel();
				value.setBounds(135, 525, 322, 51);
				
				if(x.getName().equals("Icons")) {
					value.setText("Value:  -");
				}
				else{
					value.setText("Value: " + z.getValue()  + ".000.000");
				}

				JLabel ageAttackDefense = new JLabel("Age: " + z.getAge() + " Goalkeeping: " + z.getGoalkeeping());
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
		});
	}
	
	public void showPlayer(Player x, Team z, Container pane, Design des, JLabel logoLabel, JLabel backGround) {
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
				showFormation(z, backGround, logoLabel, clubLogo, pane, des, special);
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

		pane.add(nameNumber);
		pane.add(backButton);
		pane.add(playerPhoto);
		pane.add(positions);
		pane.add(team);
		pane.add(value);
		pane.add(ageAttackDefense);

	}


	public Team showTeams(JLabel backGround, JLabel logoLabel, Competition competition, Container pane, Design des) {
		pane.removeAll();
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(387, 28, 105, 34);
		Design.buttonDesign(backButton);

		JPanel container = new JPanel();
		container.setOpaque(false);
		GridLayout gl = new GridLayout(0, 1, 50, 60);
		container.setLayout(gl);

		// get the users from the user file
		String[] teams = Competition.competitionNames(competition);

		// get the amount of users
		int i = teams.length;
		Arrays.sort(teams, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

		Team z = null;
		// check if the amount of users is greater than zero
		if (i <= 0) {
			JOptionPane.showMessageDialog(pane,
					"There were no users found.\nPlease contact your supervisor.", "Error",
					JOptionPane.WARNING_MESSAGE);
		} else {
			// add buttons for each user with their own name
			do {
				// get the username
				String team = teams[i - 1];
				JButton btn_user = new JButton(team);
				z = Competition.getTeamFromName(team, competition);
				Image img = null;
				try {
					String getPng = "/Users/Krijn/Downloads/Football App/Club Logo/" + z.getLogoPath();
					File clubFile = new File(getPng);
					img = ImageIO.read(clubFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Image newImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				ImageIcon logo = new ImageIcon(newImg);
				JLabel logoTeam = new JLabel(logo);
				btn_user.add(logoTeam);
				Design.buttonDesign(btn_user);
				// btn_user.setPreferredSize(new Dimension(0, 80));
				container.add(btn_user);
				i = i - 1;

				// add the actionlistener for the buttons
				btn_user.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						currentTeam = team;
						Team z = Competition.getTeamFromName(currentTeam, competition);
						
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
						
						showFormation(z, backGround, logoLabel, clubLogo, pane, des, special);
					}
				});

			}

			while (i > 0);

		}

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showLeagues(pane, des, logoLabel, backGround);
			}
		});

		container.setBounds(43, 214, 450, 516);
		container.setOpaque(false);

		// create a scrollpane with container in it
		JScrollPane scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setOpaque(false);
		scrollPane.setBounds(43, 214, 450, 516);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.getViewport().setBorder(null);

		// add the scrollpane to the contentpane
		pane.add(backButton);
		pane.add(scrollPane);
		pane.add(logoLabel);
		pane.add(backGround);

		pane.revalidate();
		pane.repaint();
		pane.setVisible(true);

		return z;
	}
	
	
	public void showLeagues(Container pane, Design des, JLabel logoLabel, JLabel backGround) {
		pane.removeAll();

		JButton backButton = new JButton("Back");
		backButton.setBounds(387, 28, 105, 34);
		Design.buttonDesign(backButton);

		JPanel container = new JPanel();
		container.setOpaque(false);
		GridLayout gl = new GridLayout(0, 1, 60, 50);
		container.setLayout(gl);

		// get the users from the user file
		String[] teams = init.readLeagues();
		Arrays.sort(teams, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

		// get the amount of users
		int i = teams.length;

		// check if the amount of users is greater than zero
		if (i <= 0) {
			JOptionPane.showMessageDialog(pane,
					"There were no users found.\nPlease contact your supervisor.", "Error",
					JOptionPane.WARNING_MESSAGE);
		} else {
			// add buttons for each user with their own name
			do {
				// get the username
				String league = teams[i - 1];
				JButton btn_user = new JButton(league);
				Design.buttonDesign(btn_user);
				Image img = null;
				try {
					String getPng = "/Users/Krijn/Downloads/Football App/League Logo/" + league + ".png";
					File leagueFile = new File(getPng);
					img = ImageIO.read(leagueFile);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
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
				// btn_user.setPreferredSize(new Dimension(0, 80));
				btn_user.setPreferredSize(null);
				container.add(btn_user);
				i = i - 1;

				// add the actionlistener for the buttons
				btn_user.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						currentLeague = league;

						special = Competition.readSpecialComp(currentLeague);

						showTeams(backGround, logoLabel, special, pane, des);

					}
				});

			}

			while (i > 0);

		}

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				des.mainMenu();
			}
		});

		container.setBounds(43, 214, 450, 516);
		container.setOpaque(false);

		// create a scrollpane with container in it
		JScrollPane scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setOpaque(false);
		scrollPane.setBounds(43, 214, 450, 516);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.getViewport().setBorder(null);

		// add the scrollpane to the contentpane
		pane.add(backButton);
		pane.add(scrollPane);
		pane.add(logoLabel);
		pane.add(backGround);

		pane.revalidate();
		pane.repaint();
		pane.setVisible(true);
	}
	
	public void showFormation(Team x, JLabel backGround, JLabel logoLabel, JLabel clubLogo, Container pane, Design des, Competition special) {
		pane.removeAll();
		
		Competition specialTwo = special;
		
		showKeeper(x, x.getGoalkeepers().get(0), clubLogo, backGround, logoLabel, pane, des);
	
		JButton backButton = new JButton("Back");
		backButton.setBounds(387, 28, 105, 34);
		Design.buttonDesign(backButton);

		JLabel formation = new JLabel(x.getFormation());
		formation.setBounds(39, 206, 96, 48);
		formation.setOpaque(false);
		formation.setForeground(textColor);
		formation.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		pane.add(formation);

		Image img = null;
		try {
			String getPng = "/Users/Krijn/Downloads/Football App/Club Logo/" + x.getLogoPath();
			File clubFile = new File(getPng);
			img = ImageIO.read(clubFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Image newImg = img.getScaledInstance(65, 65, Image.SCALE_SMOOTH);
		ImageIcon logo = new ImageIcon(newImg);

		JLabel teamLogo = new JLabel(logo);
		teamLogo.setBounds(390, 210, 65, 65);

		pane.add(teamLogo);

		JButton ten = new JButton(
				"<html>" + "#" + x.getPlayers().get(0).getNumber() + " " + x.getPlayers().get(0).getName() + "</html>");
		JButton nine = new JButton(
				"<html>" + "#" + x.getPlayers().get(1).getNumber() + " " + x.getPlayers().get(1).getName() + "</html>");
		JButton eight = new JButton(
				"<html>" + "#" + x.getPlayers().get(2).getNumber() + " " + x.getPlayers().get(2).getName() + "</html>");
		JButton seven = new JButton(
				"<html>" + "#" + x.getPlayers().get(3).getNumber() + " " + x.getPlayers().get(3).getName() + "</html>");
		JButton six = new JButton(
				"<html>" + "#" + x.getPlayers().get(4).getNumber() + " " + x.getPlayers().get(4).getName() + "</html>");
		JButton five = new JButton(
				"<html>" + "#" + x.getPlayers().get(5).getNumber() + " " + x.getPlayers().get(5).getName() + "</html>");
		JButton four = new JButton(
				"<html>" + "#" + x.getPlayers().get(6).getNumber() + " " + x.getPlayers().get(6).getName() + "</html>");
		JButton three = new JButton(
				"<html>" + "#" + x.getPlayers().get(7).getNumber() + " " + x.getPlayers().get(7).getName() + "</html>");
		JButton two = new JButton(
				"<html>" + "#" + x.getPlayers().get(8).getNumber() + " " + x.getPlayers().get(8).getName() + "</html>");
		JButton one = new JButton(
				"<html>" + "#" + x.getPlayers().get(9).getNumber() + " " + x.getPlayers().get(9).getName() + "</html>");

		if (x.getFormation().contains("4-3-3(A)")) {
			form.show433A(ten, nine, eight, seven, six, five, four, three, two, one);
		}

		else if (x.getFormation().contains("4-3-3(C)")) {
			form.show433C(ten, nine, eight, seven, six, five, four, three, two, one);
		}

		else if (x.getFormation().contains("4-3-3(D)")) {
			form.show433D(ten, nine, eight, seven, six, five, four, three, two, one);
		}

		else if (x.getFormation().contains("4-3-2-1")) {
			form.show4321(ten, nine, eight, seven, six, five, four, three, two, one);
		}

		else if (x.getFormation().contains("3-5-1-1")) {
			form.show3511(ten, nine, eight, seven, six, five, four, three, two, one);
		}

		else if (x.getFormation().contains("4-3-1-2")) {
			form.show4312(ten, nine, eight, seven, six, five, four, three, two, one);
		}

		else if (x.getFormation().contains("4-4-1-1")) {
			form.show4411(ten, nine, eight, seven, six, five, four, three, two, one);
		}

		else if (x.getFormation().contains("4-4-2")) {
			form.show442(ten, nine, eight, seven, six, five, four, three, two, one);
		}

		else if (x.getFormation().contains("3-5-2")) {
			form.show352(ten, nine, eight, seven, six, five, four, three, two, one);
		}
		
		else if (x.getFormation().contains("5-2-3")) {
			form.show523(ten, nine, eight, seven, six, five, four, three, two, one);
		}

		ten.setBorder(null);
		nine.setBorder(null);
		eight.setBorder(null);
		seven.setBorder(null);
		six.setBorder(null);
		five.setBorder(null);
		four.setBorder(null);
		three.setBorder(null);
		two.setBorder(null);
		one.setBorder(null);

		ten.setOpaque(false);
		nine.setOpaque(false);
		eight.setOpaque(false);
		seven.setOpaque(false);
		six.setOpaque(false);
		five.setOpaque(false);
		four.setOpaque(false);
		three.setOpaque(false);
		two.setOpaque(false);
		one.setOpaque(false);

		ten.setContentAreaFilled(false);
		nine.setContentAreaFilled(false);
		eight.setContentAreaFilled(false);
		seven.setContentAreaFilled(false);
		six.setContentAreaFilled(false);
		five.setContentAreaFilled(false);
		four.setContentAreaFilled(false);
		three.setContentAreaFilled(false);
		two.setContentAreaFilled(false);
		one.setContentAreaFilled(false);

		ten.setForeground(textColor);
		nine.setForeground(textColor);
		eight.setForeground(textColor);
		seven.setForeground(textColor);
		six.setForeground(textColor);
		five.setForeground(textColor);
		four.setForeground(textColor);
		three.setForeground(textColor);
		two.setForeground(textColor);
		one.setForeground(textColor);

		ten.setFont(font);
		nine.setFont(font);
		eight.setFont(font);
		seven.setFont(font);
		six.setFont(font);
		five.setFont(font);
		four.setFont(font);
		three.setFont(font);
		two.setFont(font);
		one.setFont(font);

		ten.setHorizontalAlignment(SwingConstants.CENTER);

		nine.setHorizontalAlignment(SwingConstants.CENTER);

		eight.setHorizontalAlignment(SwingConstants.CENTER);

		seven.setHorizontalAlignment(SwingConstants.CENTER);

		six.setHorizontalAlignment(SwingConstants.CENTER);

		five.setHorizontalAlignment(SwingConstants.CENTER);

		four.setHorizontalAlignment(SwingConstants.CENTER);

		three.setHorizontalAlignment(SwingConstants.CENTER);

		two.setHorizontalAlignment(SwingConstants.CENTER);

		one.setHorizontalAlignment(SwingConstants.CENTER);

		pane.add(one);
		pane.add(two);
		pane.add(three);
		pane.add(four);
		pane.add(five);
		pane.add(six);
		pane.add(seven);
		pane.add(eight);
		pane.add(nine);
		pane.add(ten);
		
		pane.add(backButton);
		pane.add(logoLabel);
		pane.add(backGround);
		
		pane.revalidate();
		pane.repaint();

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTeams(backGround, logoLabel, specialTwo, pane, des);
			}
		});
		
		ten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				showPlayer(x.getPlayers().get(0), x, pane, des, logoLabel, backGround);
				pane.add(clubLogo);
				pane.add(logoLabel);
				pane.add(backGround);
				pane.revalidate();
				pane.repaint();
			}
		});

		nine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				showPlayer(x.getPlayers().get(1), x, pane, des, logoLabel, backGround);
				pane.add(clubLogo);
				pane.add(logoLabel);
				pane.add(backGround);
				pane.revalidate();
				pane.repaint();
			}
		});

		eight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				showPlayer(x.getPlayers().get(2), x, pane, des, logoLabel, backGround);
				pane.add(clubLogo);
				pane.add(logoLabel);
				pane.add(backGround);
				pane.revalidate();
				pane.repaint();
			}
		});

		seven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				showPlayer(x.getPlayers().get(3), x, pane, des, logoLabel, backGround);
				pane.add(clubLogo);
				pane.add(logoLabel);
				pane.add(backGround);
				pane.revalidate();
				pane.repaint();
			}
		});

		six.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				showPlayer(x.getPlayers().get(4), x, pane, des, logoLabel, backGround);
				pane.add(clubLogo);
				pane.add(logoLabel);
				pane.add(backGround);
				pane.revalidate();
				pane.repaint();
			}
		});

		five.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				showPlayer(x.getPlayers().get(5), x, pane, des, logoLabel, backGround);
				pane.add(clubLogo);
				pane.add(logoLabel);
				pane.add(backGround);
				pane.revalidate();
				pane.repaint();
			}
		});

		four.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				showPlayer(x.getPlayers().get(6), x, pane, des, logoLabel, backGround);
				pane.add(clubLogo);
				pane.add(logoLabel);
				pane.add(backGround);
				pane.revalidate();
				pane.repaint();
			}
		});

		three.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				showPlayer(x.getPlayers().get(7), x, pane, des, logoLabel, backGround);
				pane.add(clubLogo);
				pane.add(logoLabel);
				pane.add(backGround);
				pane.revalidate();
				pane.repaint();
			}
		});

		two.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				showPlayer(x.getPlayers().get(8), x, pane, des, logoLabel, backGround);
				pane.add(clubLogo);
				pane.add(logoLabel);
				pane.add(backGround);
				pane.revalidate();
				pane.repaint();
			}
		});

		one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pane.removeAll();
				showPlayer(x.getPlayers().get(9), x, pane, des, logoLabel, backGround);
				pane.add(clubLogo);
				pane.add(logoLabel);
				pane.add(backGround);
				pane.revalidate();
				pane.repaint();
			}
		});

	}
}
