package controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import view.view;



public class main extends JLabel{
	private JButton newGame;
	private JButton exitGame;
	public JButton getNewGame() {
		return newGame;
	}
public main(final view view){
	super(new ImageIcon("main.jpg"));
	setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
			new ImageIcon("s.png").getImage(),
			new Point(0,0),"custom cursor"));
	setSize(1366,780);
	ImageIcon img = new ImageIcon("main.jpg");
	Image scale = img.getImage().getScaledInstance(1366, 780, Image.SCALE_SMOOTH);
	setIcon(new ImageIcon(scale));
	newGame = new JButton(new ImageIcon("new.png"));
	newGame.setForeground(Color.WHITE);
	newGame.setRolloverIcon(new ImageIcon("newglow.png"));
	newGame.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			NewGame newGame = null;
			try {
				newGame = new NewGame(view);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			removeAll();
			repaint();
			add(newGame);
			newGame.setVisible(true);
		}
	});
	add(newGame);
	newGame.setBounds(420, 400, 460, 120);
	newGame.setBorder(BorderFactory.createEmptyBorder());
	newGame.setContentAreaFilled(false);	
	exitGame = new JButton(new ImageIcon("exit.png"));
	exitGame.setRolloverIcon(new ImageIcon("exitglow.png"));
	exitGame.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	add(exitGame);
	exitGame.setBounds(420, 500, 460, 120);
	exitGame.setBorder(BorderFactory.createEmptyBorder());
	exitGame.setContentAreaFilled(false);
	}

}


