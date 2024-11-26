package view;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import controller.NewGame;
import controller.chess;
import controller.main;

public class view extends JFrame{

	private main mainMenu;
	private NewGame newGame;
	private chess map;
	Clip play;


	
	public view() {
		setTitle("Marvel heroes chess");
//		playsong();
		setSize(1366, 780);
		setLocationRelativeTo(null);
		setResizable(false);
		
		mainMenu = new main(this);
		add(mainMenu);
		mainMenu.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true); 
	}
//	private void playsong(){
//		
//		try {
//			AudioInputStream audio;
//			audio=AudioSystem.getAudioInputStream(new File("boom.wav"));
//			play = AudioSystem.getClip();
//			play.open(audio);
//			play.start();
//		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	public void stopsong(){
		play.stop();
	}
	
	public main getMainMenu() {
		return mainMenu;
	}

	public void HideFrame() {
		dispose();
		mainMenu.setVisible(false);
	}

}
