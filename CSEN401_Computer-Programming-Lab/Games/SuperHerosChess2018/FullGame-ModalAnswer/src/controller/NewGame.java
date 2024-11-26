package controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.game.Game;
import model.game.Player;
import view.view;


public class NewGame extends JLabel {
private JButton StartTheGame;

//private JButton FClicked;
private ArrayList<String> pname;
private JTextField playerName1;
private JLabel playerN1;
private JTextField playerName2;
private JLabel playerN2;

private chess map;
private ArrayList<String> player;

private Game game;
private Player p1;
private Player p2;
public NewGame(final view view) throws IOException{
	
	super(new ImageIcon("news.jpg"));
	java.net.URL url = ClassLoader.getSystemResource("aa.jpg");
	setSize(1366,780);
	ImageIcon img = new ImageIcon("news.jpg");
	Image scale = img.getImage().getScaledInstance(1366, 780, Image.SCALE_SMOOTH);
	setIcon(new ImageIcon(scale));
	
	
	
	player=new ArrayList<String>();
	
	playerN1 = new JLabel (new ImageIcon("PlayerName.png"));
	add(playerN1);
	playerN1.setBorder(BorderFactory.createEmptyBorder());
	playerN1.setBounds(185,95,460,120);
	playerN1.setFont(new java.awt.Font(null, 1, 20));
	playerN1.setForeground(Color.WHITE);
	playerName1 = new JTextField(1);
	add(playerName1);
	playerName1.setBounds(520, 135, 150, 35);

	playerN2 = new JLabel (new ImageIcon("PlayerName.png"));
	add(playerN2);
	playerN2.setBorder(BorderFactory.createEmptyBorder());
	playerN2.setBounds(585,95,460,120);
	playerN2.setFont(new java.awt.Font(null, 1, 20));
	playerN2.setForeground(Color.WHITE);
	playerName2 = new JTextField(1);
	add(playerName2);
	playerName2.setBounds(920, 135, 150, 35);

	
	pname= new ArrayList<String>();
	

	
	StartTheGame = new JButton(new ImageIcon("startthegame.png"));
	StartTheGame.setRolloverIcon(new ImageIcon("startthegameglow.png"));
	StartTheGame.setFocusable(false);
	StartTheGame.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(playerName1.getText().equals("")){
				JOptionPane.showMessageDialog(null, "You need to choose a Player Name for Player 1 !!"); 
				return;
			}
			if(playerName2.getText().equals("")){
				JOptionPane.showMessageDialog(null, "You need to choose a Player Name for Player 2 !!"); 
				return;
			}
			
			p1 = new Player(playerName1.getText());
			p2 = new Player(playerName2.getText());
		
						
					   
						game=new Game(p1,p2);
						try {
							map = new chess(game,view);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						removeAll();
						repaint();
						revalidate();
						map.setVisible(true);
		}					
	});
	add(StartTheGame);
	StartTheGame.setBounds(580,570,225,48); 
	StartTheGame.setBorder(BorderFactory.createEmptyBorder());
	StartTheGame.setContentAreaFilled(false);
	StartTheGame.setFont(new java.awt.Font(null, 1, 20));

	
	
}

public ArrayList<String> getPname() {
	return pname;
}
public String getp1(){
  return  playerName1.getText();
}
public String getp2(){
	  return  playerName2.getText();
	}

public ArrayList<String> getPlayer() {
	return player;
}
public static void main(String[]args){
	
}
}
