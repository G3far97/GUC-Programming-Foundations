package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.RootPaneContainer;
import javax.swing.Timer;
import javax.swing.border.Border;

import exceptions.InvalidPowerTargetException;
import exceptions.InvalidPowerUseException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Abilities;
import model.game.Cell;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKick;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;
import view.view;



public class chess extends JFrame{
	JPanel buttons;
	JButton [][] squares;
	JTextArea currentPlayerInfo;
	view view;
	Game game;
	private JComponent themap;
	private int posi;
	private int posj;
	private int secondPosi;
	private int secondPosj;
	private int chosenPosI;
	private int chosenPosJ;
	JButton [][] squares2;
	int posi1;
    int posj1;
    int posi2;
    int posj2;
	Point newPos=new Point();
	//checks
	boolean hacked;
	int h_counter;
	boolean restored;
	int r_counter;
	boolean teleported;
	int t_counter;
	JLabel [][] Slmap;
	JComponent lmap;
    JProgressBar leftposprogressb,rightposprogressb;
    JPanel leftpos;
    Clip play;
//	private void move(){
//		
//		try {
//			AudioInputStream audio;
//			audio=AudioSystem.getAudioInputStream(new File("buttonclick.wav"));
//			play = AudioSystem.getClip();
//			play.open(audio);
//			play.start();
//		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	private void kill(){
//		
//		try {
//			AudioInputStream audio;
//			audio=AudioSystem.getAudioInputStream(new File("Punch.wav"));
//			play = AudioSystem.getClip();
//			play.open(audio);
//			play.start();
//		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	private void rangedKill(){
//		
//		try {
//			AudioInputStream audio;
//			audio=AudioSystem.getAudioInputStream(new File("GotchaB.wav"));
//			play = AudioSystem.getClip();
//			play.open(audio);
//			play.start();
//		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

	 
	public chess(Game game,view view) throws IOException{
//		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
//				new ImageIcon("h.png").getImage(),
//				new Point(0,0),"custom cursor"));
		JLabel Background = new JLabel(new ImageIcon("ch.png"));
		ImageIcon img = new ImageIcon("ch.png");
		Image scale = img.getImage();
		Background.setIcon(new ImageIcon(scale));
		setContentPane(Background);	
		setLayout(new BorderLayout());
		this.view=view;
		posi = -1;
		posj = -1;
		secondPosi=-1;
		secondPosj=-1;
		hacked = false;
		h_counter = 1;
		restored=false;
		r_counter=1;
		teleported=false;
		t_counter=2;
		view.setVisible(false);
		setTitle("Chess");
		//setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
//		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout()); 
    	themap = new JPanel();
    	themap.setOpaque(false);
//    	leftpos = new JPanel();
//    	leftpos.setOpaque(false);
//    	getContentPane().add(leftpos ,BorderLayout.WEST );
//    	leftpos.setPreferredSize(new Dimension(130, 848));
    	
    	leftposprogressb = new JProgressBar();
    	leftposprogressb.setOrientation(JProgressBar.VERTICAL);
    	leftposprogressb.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    	leftposprogressb.setMinimum(0);
    	leftposprogressb.setMaximum(6);
    	leftposprogressb.setStringPainted(true);
    	leftposprogressb.setForeground(Color.GREEN);
		leftposprogressb.setValue(game.getPlayer2().getPayloadPos());
		leftposprogressb.setSize(50, 500);
		leftposprogressb.setOpaque(false);
		leftposprogressb.setString("P2");
		getContentPane().add(leftposprogressb , BorderLayout.WEST);
		leftposprogressb.setPreferredSize(new Dimension(130, 848));
//    	lmap =  new JPanel();
//    	lmap.setOpaque(false);
//    	lmap.setLayout(new GridLayout(8,1,1,1));
//    	Slmap = new JLabel[8][1];
//    	
//     	Slmap[0][0] = new JLabel();
//   	 	lmap.add(Slmap[0][0]);
//   	 	Slmap[0][0].setBorder(BorderFactory.createLineBorder(Color.white));
//   	 	Slmap[0][0].setIcon(new ImageIcon("p2.png"));
//   	 	Slmap[0][0].setOpaque(false);
//   	 	
//   	 for(int i=1; i<7; i++)
//        {
//            	int j=0;
//
//            	Slmap[i][j] = new JLabel();
//           	 	lmap.add(Slmap[i][j]);
//           	 	Slmap[i][j].setBorder(BorderFactory.createLineBorder(Color.white));
//           	 	Slmap[i][j].setIcon(new ImageIcon("e.png"));
//           	 	Slmap[i][j].setOpaque(false);
// 	
//        }
//    	leftpos.add(lmap);
    	
    	getContentPane().add(themap ,BorderLayout.CENTER );
    	//setBounds(50, 50, 800, 600);
    	
//    	JPanel rightpos = new JPanel();
//    	rightpos.setOpaque(false);
//    	getContentPane().add(rightpos ,BorderLayout.EAST );
////    	System.out.println(getHeight());
//    	rightpos.setPreferredSize(new Dimension(130, 848));
//    	JComponent rmap =  new JPanel();
//    	rmap.setOpaque(false);
//    	rmap.setLayout(new GridLayout(8,1,1,1));
//    	JLabel [][] Srmap = new JLabel[8][1];
//    	Srmap[0][0] = new JLabel();
//   	 	rmap.add(Srmap[0][0]);
//   	 	Srmap[0][0].setBorder(BorderFactory.createLineBorder(Color.white));
//   	 	Srmap[0][0].setIcon(new ImageIcon("p1.png"));
//   	 	Srmap[0][0].setOpaque(false);
//   	 	
//    	 for(int i=1; i<7; i++)
//         {
//    	     	int j=0;
//
//            	Srmap[i][j] = new JLabel();
//           	 	rmap.add(Srmap[i][j]);
//           	 	Srmap[i][j].setBorder(BorderFactory.createLineBorder(Color.white));
//           	 	Srmap[i][j].setIcon(new ImageIcon("e.png"));
//           	 	Srmap[i][j].setOpaque(false);
// 	
//         }
////    	rightpos();
//    	rightpos.add(rmap);
    	
		rightposprogressb = new JProgressBar();
		rightposprogressb.setOrientation(JProgressBar.VERTICAL);
		rightposprogressb.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		rightposprogressb.setMinimum(0);
		rightposprogressb.setMaximum(6);
		rightposprogressb.setStringPainted(true);
		rightposprogressb.setForeground(Color.GREEN);
		rightposprogressb.setValue(game.getPlayer1().getPayloadPos());
		rightposprogressb.setSize(50, 500);
		rightposprogressb.setOpaque(false);
		rightposprogressb.setString("P1");
//		rightposprogressb.setBackground(Color.GREEN);
		getContentPane().add(rightposprogressb , BorderLayout.EAST);
		rightposprogressb.setPreferredSize(new Dimension(130, 848));
    	
        JPanel top = new JPanel();
        JPanel allcurrentPlayerInfo = new JPanel();
        top.setOpaque(false);
    	getContentPane().add(top ,BorderLayout.NORTH );
    	//getContentPane().add(allcurrentPlayerInfo ,BorderLayout.WEST );
    	//allcurrentPlayerInfo.setPreferredSize(new Dimension(200, getHeight()));
    	
        top.setPreferredSize(new Dimension(getWidth(), 150));
    	themap.setOpaque(false);
    	themap.setLayout(new GridLayout(7,6,1,1));
    	this.game = game;
    	//themap.setPreferredSize(new Dimension(150,150));
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        squares = new JButton[7][6];
       
        
        for(int i=0; i<game.getBoardHeight(); i++)
        {
            for(int j=0; j<game.getBoardWidth(); j++)
            {
//                Player player=this.game.getCellAt(i, j).getPiece().getOwner();
//        		String pieceName=this.game.getCellAt(i, j).getPiece().getName();
                squares[i][j] = new JButton();
                themap.add(squares[i][j]);
                squares[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
//                upr.setBorder(BorderFactory.createEmptyBorder());
                squares[i][j].setContentAreaFilled(false);
                final int ii=i;
                final int jj=j;
                		if(game.getCellAt(i, j).getPiece() instanceof Armored && game.getCellAt(i, j).getPiece().getName().equals("Wonder Woman") ){
//                			System.out.println("the i is : " + i + " and the j is : " + j );
                			squares[i][j].setIcon(new ImageIcon("ww.png"));
                            squares[i][j].setOpaque(false);
                            squares[i][j].addActionListener(new ActionListener() {
                            	   public void actionPerformed(ActionEvent e) {
                            		   if(!hacked){
                            	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                            	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                            		   }
                            		   else{
                            			   if(h_counter == 1){
                            				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
                            				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
                            				   h_counter--;
                            			   }
//                            			   else if(h_counter == 1){
//                            				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//                            				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//                            				   h_counter--;
//                            			   }
                            			   else{
         			 							try {
													((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
												h_counter = 1;
												hacked = false;
         			 							} catch (InvalidPowerUseException | WrongTurnException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}

                            			   }
                            		   }
                            	   //	squares[ii][jj].setToolTipText(pieceName +" of "+player);
                            	   	   }});
                         	String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
                    	   	String text="Armored of " + player+"\n";
              	   				if(((Armored)game.getCellAt(i, j).getPiece()).isArmorUp()){
              	   					text+="Armor is up";
              	   					squares[i][j].setToolTipText(text);
              	   				}
              	   				else{
              	   					text+="Armor is down";
              	   					squares[i][j].setToolTipText(text);
              	   				
              	   			}
                		}
                		else if(game.getCellAt(i, j).getPiece() instanceof Armored && game.getCellAt(i, j).getPiece().getName().equals("Captain America")){
                				squares[i][j].setIcon(new ImageIcon("ca.png"));
                				squares[i][j].setOpaque(false);
                				squares[i][j].addActionListener(new ActionListener() {
                				public void actionPerformed(ActionEvent e) {
                					   System.out.println("wrong");
                					   if(!hacked){
                						   System.out.println("nooo");
                                   	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                                   	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                                   		   }
                                   		   else{
                                   			   System.out.println("1");
                                   			   if(h_counter == 1){
                                   				 System.out.println("2");
                                   				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
                                   				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
                                   				   h_counter--;
                                   			   }
//                                   			   else if(h_counter == 1){
//                                   				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//                                   				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//                                   				   h_counter--;
//                                   			   }
                                   			   else{
                                   				 System.out.println("3");
                			 							try {
                			 								System.out.println(posi);
                			 								System.out.println(posj);
                			 								System.out.println(posi1);
                			 								System.out.println(posj1);
       													((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
       												h_counter = 1;
       												hacked = false;
                			 							} catch (InvalidPowerUseException | WrongTurnException e1) {
       													// TODO Auto-generated catch block
       													e1.printStackTrace();
       												}

                                   			   }
                                   		   }
                         	   	   }});
                				String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//                  	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
                        	   	String text="Armored of " + player+"\n";
                  	   				if(((Armored)game.getCellAt(i, j).getPiece()).isArmorUp()){
                  	   					text+="Armor is up";
                  	   					squares[i][j].setToolTipText(text);
                  	   				}
                  	   				else{
                  	   					text+="Armor is down";
                  	   					squares[i][j].setToolTipText(text);
                  	   				
                  	   			}
                            
                		}
//                		else if(game.getCellAt(i, j).getPiece() instanceof Armored && game.getCellAt(i, j).getPiece().getName().equals("Wonder Woman") && !((Armored)(game.getCellAt(i, j).getPiece())).isArmorUp() ){
////                			System.out.println("the i is : " + i + " and the j is : " + j );
//                			squares[i][j].setIcon(new ImageIcon("wwa.png"));
//                            squares[i][j].setOpaque(false);
//                            squares[i][j].addActionListener(new ActionListener() {
//                         	   public void actionPerformed(ActionEvent e) {
//                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
//                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//                         	   Player player=game.getCellAt(posi, posj).getPiece().getOwner();
//                 	   			String pieceName=game.getCellAt(posi, posj).getPiece().getName();
//                 	   		if(((Armored)game.getCellAt(posi, posj).getPiece()).isArmorUp()){
//         	   					squares[posi][posj].setToolTipText(pieceName +" of "+player +"\n"+"Armor is up");
//         	   				}
//         	   				else{
//         	   					squares[posi][posj].setToolTipText(pieceName +" of "+player +"\n"+"Armor is down");
//         	   				}
//         	   			
//                         	   	   }});
//                		}
//                		else if(game.getCellAt(i, j).getPiece() instanceof Armored && game.getCellAt(i, j).getPiece().getName().equals("Captain America") && !((Armored)(game.getCellAt(i, j).getPiece())).isArmorUp()){
//                			squares[i][j].setIcon(new ImageIcon("caa.png"));
//                            squares[i][j].setOpaque(false);
//                            squares[i][j].addActionListener(new ActionListener() {
//                         	   public void actionPerformed(ActionEvent e) {
//                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
//                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//                         	   	Player player=game.getCellAt(posi, posj).getPiece().getOwner();
//                 	   			String pieceName=game.getCellAt(posi, posj).getPiece().getName();
//                 	   			
//                 	   				if(((Armored)game.getCellAt(posi, posj).getPiece()).isArmorUp()){
//                 	   					squares[posi][posj].setToolTipText(pieceName +" of "+player +"\n"+"Armor is up");
//                 	   				}
//                 	   				else{
//                 	   					squares[posi][posj].setToolTipText(pieceName +" of "+player +"\n"+"Armor is down");
//                 	   				}
//                 	   			
//                 	   			
//                         	   	   }});
//                		}
                		else if(game.getCellAt(i, j).getPiece() instanceof Medic && game.getCellAt(i, j).getPiece().getName().equals("Green Lantern")){
                			squares[i][j].setIcon(new ImageIcon("gl.png"));
                            squares[i][j].setOpaque(false);
                            squares[i][j].addActionListener(new ActionListener() {
                         	   public void actionPerformed(ActionEvent e) {
                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                         	  
         	   			
                         	   	   }});
                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
                    	   	String text="Medic of " + player+"\n";
              	   				if(((Medic)game.getCellAt(i, j).getPiece()).isPowerUsed()){
              	   					text+="Power is used";
              	   					squares[i][j].setToolTipText(text);
              	   				}
              	   				else{
              	   					text+="Power is unused";
              	   					squares[i][j].setToolTipText(text);
              	   				
              	   			}
                		}
                		else if(game.getCellAt(i, j).getPiece() instanceof Medic && game.getCellAt(i, j).getPiece().getName().equals("Vision")){
                			squares[i][j].setIcon(new ImageIcon("v.png"));
                            squares[i][j].setOpaque(false);
                            squares[i][j].addActionListener(new ActionListener() {
                         	   public void actionPerformed(ActionEvent e) {
                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                         	
                         	   	   }});
                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
                    	   	String text="Medic of " + player+"\n";
              	   				if(((Medic)game.getCellAt(i, j).getPiece()).isPowerUsed()){
              	   					text+="Power is used";
              	   					squares[i][j].setToolTipText(text);
              	   				}
              	   				else{
              	   					text+="Power is unused";
              	   					squares[i][j].setToolTipText(text);
              	   				
              	   			}
                		}
                		else if(game.getCellAt(i, j).getPiece() instanceof Ranged && game.getCellAt(i, j).getPiece().getName().equals("Green Arrow")){
                			squares[i][j].setIcon(new ImageIcon("ga.png"));
                            squares[i][j].setOpaque(false);
                            squares[i][j].addActionListener(new ActionListener() {
                         	   public void actionPerformed(ActionEvent e) {
                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                         	   
                         	   	   }});
                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
                    	   	String text="Ranged of " + player+"\n";
              	   				if(((Ranged)game.getCellAt(i, j).getPiece()).isPowerUsed()){
              	   					text+="Power is used";
              	   					squares[i][j].setToolTipText(text);
              	   				}
              	   				else{
              	   					text+="Power is unused";
              	   					squares[i][j].setToolTipText(text);
              	   				
              	   			}
                		}
                		else if(game.getCellAt(i, j).getPiece() instanceof Ranged && game.getCellAt(i, j).getPiece().getName().equals("Hawk Eye")){
                			squares[i][j].setIcon(new ImageIcon("he.png"));
                            squares[i][j].setOpaque(false);
                            squares[i][j].addActionListener(new ActionListener() {
                         	   public void actionPerformed(ActionEvent e) {
                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                         	
                         	   	   }});
                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
                    	   	String text="Ranged of " + player+"\n";
              	   				if(((Ranged)game.getCellAt(i, j).getPiece()).isPowerUsed()){
              	   					text+="Power is used";
              	   					squares[i][j].setToolTipText(text);
              	   				}
              	   				else{
              	   					text+="Power is unused";
              	   					squares[i][j].setToolTipText(text);
              	   				
              	   			}
                		}
                		else if(game.getCellAt(i, j).getPiece() instanceof Speedster && game.getCellAt(i, j).getPiece().getName().equals("Flash")){
                			squares[i][j].setIcon(new ImageIcon("f.png"));
                            squares[i][j].setOpaque(false);
                            squares[i][j].addActionListener(new ActionListener() {
                         	   public void actionPerformed(ActionEvent e) {
                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                    
                         	   	   }});
                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
                    	   	String text="Speedster of " + player;
                    	   	squares[i][j].setToolTipText(text);
              	   			
                		}
                		else if(game.getCellAt(i, j).getPiece() instanceof Speedster && game.getCellAt(i, j).getPiece().getName().equals("Quick Silver")){
                			squares[i][j].setIcon(new ImageIcon("qs.png"));
                            squares[i][j].setOpaque(false);
                            squares[i][j].addActionListener(new ActionListener() {
                         	   public void actionPerformed(ActionEvent e) {
                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                         	   	
                         	   	   }});
                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
                    	   	String text="Speedster of " + player;
                    	   	squares[i][j].setToolTipText(text);
                		}
                		else if(game.getCellAt(i, j).getPiece() instanceof Super && game.getCellAt(i, j).getPiece().getName().equals("Superman")){
                			squares[i][j].setIcon(new ImageIcon("s.png"));
                            squares[i][j].setOpaque(false);
                            squares[i][j].addActionListener(new ActionListener() {
                         	   public void actionPerformed(ActionEvent e) {
                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                         	 
                         	   	   }});
                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//	              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
                    	   	String text="Super of " + player+"\n";
              	   				if(((Super)game.getCellAt(i, j).getPiece()).isPowerUsed()){
              	   					text+="Power is used";
              	   					squares[i][j].setToolTipText(text);
              	   				}
              	   				else{
              	   					text+="Power is unused";
              	   					squares[i][j].setToolTipText(text);
              	   				
              	   			}
                		}
                		else if(game.getCellAt(i, j).getPiece() instanceof Super && game.getCellAt(i, j).getPiece().getName().equals("Hulk")){
                			squares[i][j].setIcon(new ImageIcon("h.png"));
                            squares[i][j].setOpaque(false);
                            squares[i][j].addActionListener(new ActionListener() {
                         	   public void actionPerformed(ActionEvent e) {
                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                         	  
                         	   	   }});
                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//	              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
	                    	   	String text="Super of " + player+"\n";
	              	   				if(((Super)game.getCellAt(i, j).getPiece()).isPowerUsed()){
	              	   					text+="Power is used";
	              	   					squares[i][j].setToolTipText(text);
	              	   				}
	              	   				else{
	              	   					text+="Power is unused";
	              	   					squares[i][j].setToolTipText(text);
	              	   				
	              	   			}
                		}
                		else if(game.getCellAt(i, j).getPiece() instanceof Tech && game.getCellAt(i, j).getPiece().getName().equals("Batman")){
                			squares[i][j].setIcon(new ImageIcon("b.png"));
                            squares[i][j].setOpaque(false);
                            squares[i][j].addActionListener(new ActionListener() {
                         	   public void actionPerformed(ActionEvent e) {
                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                         	   
                         	   	   }});
                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//	              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
	                    	   	String text="Tech of " + player+"\n";
	              	   				if(((Tech)game.getCellAt(i, j).getPiece()).isPowerUsed()){
	              	   					text+="Power is used";
	              	   					squares[i][j].setToolTipText(text);
	              	   				}
	              	   				else{
	              	   					text+="Power is unused";
	              	   					squares[i][j].setToolTipText(text);
	              	   				
	              	   			}
                		}
                		else if(game.getCellAt(i, j).getPiece() instanceof Tech && game.getCellAt(i, j).getPiece().getName().equals("Ironman")){
                			squares[i][j].setIcon(new ImageIcon("im.png"));
                            squares[i][j].setOpaque(false);
                            squares[i][j].addActionListener(new ActionListener() {
                         	   public void actionPerformed(ActionEvent e) {
                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                         	   
                         	   	   }});
                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//	              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
	                    	   	String text="Tech of " + player+"\n";
	              	   				if(((Tech)game.getCellAt(i, j).getPiece()).isPowerUsed()){
	              	   					text+="Power is used";
	              	   					squares[i][j].setToolTipText(text);
	              	   				}
	              	   				else{
	              	   					text+="Power is unused";
	              	   					squares[i][j].setToolTipText(text);
	              	   			}
                		}
                		else if(game.getCellAt(i, j).getPiece() instanceof SideKickP1){
//                			System.out.println(" side kick 1");
//                			System.out.println("the i is : " + i + " and the j is : " + j );
                			squares[i][j].setIcon(new ImageIcon("sd1.png"));
                            squares[i][j].setOpaque(false);
                            squares[i][j].addActionListener(new ActionListener() {
                         	   public void actionPerformed(ActionEvent e) {
                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                         	  
                         	   	   }});
                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
              	   		String pieceName=game.getCellAt(i, j).getPiece().getName();
                    	   	String text=pieceName + player;
                    	   	squares[i][j].setToolTipText(text);
              	   				
                		}
                		else if(game.getCellAt(i, j).getPiece() instanceof SideKickP2){
//                			System.out.println(" side kick 2");
//                			System.out.println("the i is : " + i + " and the j is : " + j  );
                			squares[i][j].setIcon(new ImageIcon("sd2.png"));
                            squares[i][j].setOpaque(false);
                            squares[i][j].addActionListener(new ActionListener() {
                         	   public void actionPerformed(ActionEvent e) {
                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                         	 
                         	   	   }});
                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
              	   		String pieceName=game.getCellAt(i, j).getPiece().getName();
                    	   	String text=pieceName + player;
                    	   	squares[i][j].setToolTipText(text);
                		}
                
                	else{
//                		squares[i][j].setIcon(new ImageIcon("empty.gif"));
                        squares[i][j].setOpaque(false);
                        squares[i][j].setToolTipText("Empty Cell");
                        squares[i][j].addActionListener(new ActionListener() {
                      	   public void actionPerformed(ActionEvent e) {
                      		   posi= game.getCellAt(ii, jj).getPiece().getPosI();
              				   posj= game.getCellAt(ii, jj).getPiece().getPosJ();
                      	
                      	   	   }});
                        
                	}
                		
                		
                		
                	}
               
                }
//      Player player=this.game.getCellAt(i, j).getPiece().getOwner();
//		String pieceName=this.game.getCellAt(i, j).getPiece().getName();
//		squares[i][j].addActionListener(new ActionListener() {
//	       	   public void actionPerformed(ActionEvent e) {
//	       		squares[ii][jj].setToolTipText(pieceName +" of "+player);
//	       		
//	       	   }});
        		
       top.setLayout(new GridLayout());
       currentPlayerInfo=new JTextArea();
       currentPlayerInfo.setFont(new Font("Chiller", Font.BOLD, 20));
       currentPlayerInfo.setOpaque(false);
       currentPlayerInfo.setEditable(false);
       currentPlayerInfo.setForeground(Color.white);
       
       top.add(currentPlayerInfo , BorderLayout.WEST);
       String CurrentPlayerInfo=" Name: ";
       CurrentPlayerInfo +=game.getCurrentPlayer().getName()+"\n";
       CurrentPlayerInfo+=" Pay Load : " ;
       CurrentPlayerInfo+=game.getCurrentPlayer().getPayloadPos()+"\n";
       CurrentPlayerInfo+=" Side Killed : " ;
       CurrentPlayerInfo+=game.getCurrentPlayer().getSideKilled()+"\n";
       CurrentPlayerInfo+=" Dead Characters are : " ;
       for(int i=0; i<game.getCurrentPlayer().getDeadCharacters().size();i++){
       CurrentPlayerInfo+=game.getCurrentPlayer().getDeadCharacters().get(i).getName()+"\n";
       }
       
       

       currentPlayerInfo.setText(CurrentPlayerInfo);
//       int x=;
//       int y=;
//       squares[x][y].setBorder(BorderFactory.createLineBorder(Color.red));
//       
      
 
      
       
       buttons = new JPanel();
       buttons.setBackground(Color.white);
       buttons.setLayout(new BorderLayout());
       buttons.setOpaque(false);
       top.add(buttons , BorderLayout.CENTER);
       
       JPanel up_buttons = new JPanel();
       up_buttons.setBackground(Color.white);
       up_buttons.setLayout(new BorderLayout());
       up_buttons.setOpaque(false);
       buttons.add(up_buttons , BorderLayout.NORTH);
       
       JButton upr = new JButton();
       upr = new JButton(new ImageIcon("ur.png"));
//       up.setRolloverIcon(new ImageIcon("upglow.png"));
       upr.setFocusable(false);
       Game g=this.game;
       upr.addActionListener(new ActionListener() {
   	   public void actionPerformed(ActionEvent e) {
//   		  JButton s= squares[posi][posj];
   	   	if(posi != -1 || posj != -1){
   	   			
   	   			
   		   try {
//   			   if(game.getCellAt(posi, posj).getPiece().getOwner() == game.getCurrentPlayer()){
   				   int oldposi=posi;
   				   int oldposj=posj;
   				   
//   				   	game.getCellAt(posi, posj).getPiece().move(Direction.UPRIGHT);
				   Point p= new Point();
				   p.x=oldposi;
				   p.y=oldposj;
				   
				   p= game.getCellAt(posi, posj).getPiece().getDirectionPos(p, Direction.UPRIGHT);
   				   	if(game.getCellAt(posi, posj).getPiece() instanceof SideKick){
   				   		if(game.getCellAt(p.x, p.y).getPiece() instanceof ActivatablePowerHero){
   				   			game.getCellAt(posi, posj).getPiece().move(Direction.UPRIGHT);
//   				   			if(game.getCellAt(oldposi, oldposj).getPiece()==null){
   				   			updatemap();
   				   			if(game.checkWinner()){
   				   				
   				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
   				   				gameEnd();
   				   			JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
		   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
		   					//Image scale = img.getImage();
		   					Background.setIcon(new ImageIcon(scale));
		   					((RootPaneContainer) themap).setContentPane(Background);
		   					//here
   				   			}
   				   		   
//   				   			}
   				   		}
   				   		else{
   				   		game.getCellAt(posi, posj).getPiece().move(Direction.UPRIGHT);
   				   		updatemap();
   				   	if(game.checkWinner()){
			   				
			   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName());
			   				gameEnd();
			   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
		   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
		   					//Image scale = img.getImage();
		   					Background.setIcon(new ImageIcon(scale));
		   					((RootPaneContainer) themap).setContentPane(Background);
		   					//here
	
			   			}
   				   		}
   				   	}
   				   	else{
   				   		game.getCellAt(posi, posj).getPiece().move(Direction.UPRIGHT);
   				   		updatemap();
   				   	if(game.checkWinner()){
			   				
			   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
			   				gameEnd();
			   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
		   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
		   					//Image scale = img.getImage();
		   					Background.setIcon(new ImageIcon(scale));
		   					((RootPaneContainer) themap).setContentPane(Background);
		   					//here
			   				
	
			   			}
   				   	}
   				
//   			   }
//   			   else{
//   					JOptionPane.showMessageDialog(null, "it is not ur turn");    
//   			   }
 		} catch (UnallowedMovementException | OccupiedCellException | WrongTurnException e1) {
 			// TODO Auto-generated catch block
 			JOptionPane.showMessageDialog(null, e1.getMessage().toString()); 
 		}
//   		  updatemap();
   		updateleftprogressbar();
   		updaterightprogressbar();
   		  
   	   		currentPlayerInfoupdate();
   		   	}
   		   	else{
   		  	JOptionPane.showMessageDialog(null, "choose a piece first !!"); 
 			return;
   		   	}
   		
   	   }});
       upr.setBorder(BorderFactory.createEmptyBorder());
       upr.setContentAreaFilled(false);
       upr.setFont(new java.awt.Font(null, 1, 20));
       upr.setOpaque(false);
   	   upr.setForeground(Color.BLACK);
   	   upr.setBounds(0, 0, 50, 50); 
   	   up_buttons.add(upr, BorderLayout.EAST);
   	   
       JButton up = new JButton();
       up = new JButton(new ImageIcon("u.png"));
//       up.setRolloverIcon(new ImageIcon("upglow.png"));
       up.setFocusable(false);
       up.addActionListener(new ActionListener() {
   	   public void actionPerformed(ActionEvent e) {
   		  
   	   	if(posi != -1 || posj != -1){
   	   			JButton s= squares[posi][posj];
   	   		
	   			Player player=g.getCellAt(posi, posj).getPiece().getOwner();
	   			String pieceName=g.getCellAt(posi, posj).getPiece().getName();
	   			s.setToolTipText(pieceName +" of "+player);
   		   try {
   			int oldposi=posi;
			   int oldposj=posj;
			   
//			   	game.getCellAt(posi, posj).getPiece().move(Direction.UPRIGHT);
			   Point p= new Point();
			   p.x=oldposi;
			   p.y=oldposj;
			   p= game.getCellAt(posi, posj).getPiece().getDirectionPos(p, Direction.UP);
			   	if(game.getCellAt(posi, posj).getPiece() instanceof SideKick){
			   		if(game.getCellAt(p.x, p.y).getPiece() instanceof ActivatablePowerHero){
			   			game.getCellAt(posi, posj).getPiece().move(Direction.UP);
//			   			if(game.getCellAt(oldposi, oldposj).getPiece()==null){
			   			updatemap();
//			   			move();
			   			if(game.checkWinner()){
				   				
				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
				   				gameEnd();
				   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
			   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
			   					//Image scale = img.getImage();
			   					Background.setIcon(new ImageIcon(scale));
			   					((RootPaneContainer) themap).setContentPane(Background);
			   					//here
				   			}
			   			
			   			
			   		   
//			   			}
			   		}
			   		else{
			   		game.getCellAt(posi, posj).getPiece().move(Direction.UP);
			   		
			   		updatemap();
//			   		move();
			   		if(game.checkWinner()){
			   				
			   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
			   				gameEnd();
			   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
		   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
		   					//Image scale = img.getImage();
		   					Background.setIcon(new ImageIcon(scale));
		   					((RootPaneContainer) themap).setContentPane(Background);
		   					//here
			   			}
//			   		playsong();
			   		}
			   	}
			   	else{
			   		game.getCellAt(posi, posj).getPiece().move(Direction.UP);
			   		updatemap();
//			   		move();
			   		if(game.checkWinner()){
			   				
			   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
			   				gameEnd();
			   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
		   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
		   					//Image scale = img.getImage();
		   					Background.setIcon(new ImageIcon(scale));
		   					((RootPaneContainer) themap).setContentPane(Background);
		   					//here
			   			}
//			   		playsong();
			   	}
 		} catch (UnallowedMovementException | OccupiedCellException | WrongTurnException e1) {
 			// TODO Auto-generated catch block
 			JOptionPane.showMessageDialog(null, e1.getMessage().toString()); 
 		}
//   		  updatemap();
  		updateleftprogressbar();
  		updaterightprogressbar();
   		  
   	   		currentPlayerInfoupdate();
   		   	}
   		   	else{
   		  	JOptionPane.showMessageDialog(null, "choose a piece first !!"); 
 			return;
   		   	}

   		
   	   }});
   	   up.setBorder(BorderFactory.createEmptyBorder());
   	   up.setContentAreaFilled(false);
   	   up.setFont(new java.awt.Font(null, 1, 20));
       up.setOpaque(false);
       up.setForeground(Color.BLACK);
       up.setBounds(0, 0, 50, 50); 
       up_buttons.add(up, BorderLayout.CENTER);
       
       JButton upl = new JButton();
       upl = new JButton(new ImageIcon("ul.png"));
//       up.setRolloverIcon(new ImageIcon("upglow.png"));
       upl.setFocusable(false);
       upl.addActionListener(new ActionListener() {
   	   public void actionPerformed(ActionEvent e) {
   		  
   	   	if(posi != -1 || posj != -1){
   	   			JButton s= squares[posi][posj];
	   			Player player=g.getCellAt(posi, posj).getPiece().getOwner();
	   			String pieceName=g.getCellAt(posi, posj).getPiece().getName();
	   			s.setToolTipText(pieceName +" of "+player);
   		   try {
   			int oldposi=posi;
			   int oldposj=posj;
			   
//			   	game.getCellAt(posi, posj).getPiece().move(Direction.UPRIGHT);
			   Point p= new Point();
			   p.x=oldposi;
			   p.y=oldposj;
			   p= game.getCellAt(posi, posj).getPiece().getDirectionPos(p, Direction.UPLEFT);
			   	if(game.getCellAt(posi, posj).getPiece() instanceof SideKick){
			   		if(game.getCellAt(p.x, p.y).getPiece() instanceof ActivatablePowerHero){
			   			game.getCellAt(posi, posj).getPiece().move(Direction.UPLEFT);
//			   			if(game.getCellAt(oldposi, oldposj).getPiece()==null){
			   			updatemap();
//			   			move();
			   			if(game.checkWinner()){
				   				
				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
				   				gameEnd();
				   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
			   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
			   					//Image scale = img.getImage();
			   					Background.setIcon(new ImageIcon(scale));
			   					((RootPaneContainer) themap).setContentPane(Background);
			   					//here
				   			}
			   		   
//			   			}
			   		}
			   		else{
			   		game.getCellAt(posi, posj).getPiece().move(Direction.UPLEFT);
			   		updatemap();
//			   		move();
			   		if(game.checkWinner()){
			   				
			   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
			   				gameEnd();
			   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
		   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
		   					//Image scale = img.getImage();
		   					Background.setIcon(new ImageIcon(scale));
		   					((RootPaneContainer) themap).setContentPane(Background);
		   					//here
			   			}
			   		}
			   	}
			   	else{
			   		game.getCellAt(posi, posj).getPiece().move(Direction.UPLEFT);
			   		updatemap();
//			   		move();
			   		if(game.checkWinner()){
			   				
			   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
			   				gameEnd();
			   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
		   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
		   					//Image scale = img.getImage();
		   					Background.setIcon(new ImageIcon(scale));
		   					((RootPaneContainer) themap).setContentPane(Background);
		   					//here
			   			}
			   	}
 		} catch (UnallowedMovementException | OccupiedCellException | WrongTurnException e1) {
 			// TODO Auto-generated catch block
 			JOptionPane.showMessageDialog(null, e1.getMessage().toString()); 
 		}
//   		  updatemap();
  		updateleftprogressbar();
  		updaterightprogressbar();
   		  
   	   		currentPlayerInfoupdate();
   		   	}
   		   	else{
   		  	JOptionPane.showMessageDialog(null, "choose a piece first !!"); 
 			return;
   		   	}
   	   	

   		
   	   }});
       upl.setBorder(BorderFactory.createEmptyBorder());
       upl.setContentAreaFilled(false);
       upl.setFont(new java.awt.Font(null, 1, 20));
       upl.setOpaque(false);
       upl.setForeground(Color.BLACK);
       upl.setBounds(0, 0, 50, 50); 
   	   up_buttons.add(upl, BorderLayout.WEST);
   	   
   	 JPanel center_buttons = new JPanel();
   	center_buttons.setBackground(Color.white);
   	center_buttons.setLayout(new BorderLayout());
   	center_buttons.setOpaque(false);
     buttons.add(center_buttons , BorderLayout.CENTER);
     
      JButton right = new JButton();
      right = new JButton(new ImageIcon("r.png"));
//      right.setRolloverIcon(new ImageIcon("rightglow.png"));
      right.setFocusable(false);
      right.addActionListener(new ActionListener() {
  	   public void actionPerformed(ActionEvent e) {
  		   	if(posi != -1 || posj != -1){
  		   	JButton s= squares[posi][posj];
   	   		
   			Player player=g.getCellAt(posi, posj).getPiece().getOwner();
   			String pieceName=g.getCellAt(posi, posj).getPiece().getName();
   			s.setToolTipText(pieceName +" of "+player);
  	  		   try {
  	  			int oldposi=posi;
				   int oldposj=posj;
				   
//				   	game.getCellAt(posi, posj).getPiece().move(Direction.UPRIGHT);
				   Point p= new Point();
				   p.x=oldposi;
				   p.y=oldposj;
				   p= game.getCellAt(posi, posj).getPiece().getDirectionPos(p, Direction.RIGHT);
				   	if(game.getCellAt(posi, posj).getPiece() instanceof SideKick){
				   		if(game.getCellAt(p.x, p.y).getPiece() instanceof ActivatablePowerHero){
				   			game.getCellAt(posi, posj).getPiece().move(Direction.RIGHT);
//				   			if(game.getCellAt(oldposi, oldposj).getPiece()==null){
				   			updatemap();
//				   			move();
				   			if(game.checkWinner()){
   				   				
   				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
   				   				gameEnd();
   				   			JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
		   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
		   					//Image scale = img.getImage();
		   					Background.setIcon(new ImageIcon(scale));
		   					((RootPaneContainer) themap).setContentPane(Background);
		   					//here
   				   			}
				   		   
//				   			}
				   		}
				   		else{
				   		game.getCellAt(posi, posj).getPiece().move(Direction.RIGHT);
				   		updatemap();
				   		if(game.checkWinner()){
				   				
				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
				   				gameEnd();
				   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
			   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
			   					//Image scale = img.getImage();
			   					Background.setIcon(new ImageIcon(scale));
			   					((RootPaneContainer) themap).setContentPane(Background);
			   					//here
				   			}
				   		}
				   	}
				   	else{
				   		game.getCellAt(posi, posj).getPiece().move(Direction.RIGHT);
				   		updatemap();
				   		if(game.checkWinner()){
				   				
				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
				   				gameEnd();
				   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
			   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
			   					//Image scale = img.getImage();
			   					Background.setIcon(new ImageIcon(scale));
			   					((RootPaneContainer) themap).setContentPane(Background);
			   					//here
				   			}
				   	}
  			} catch (UnallowedMovementException | OccupiedCellException | WrongTurnException e1) {
  				// TODO Auto-generated catch block
  				JOptionPane.showMessageDialog(null, e1.getMessage().toString()); 
  			}
//  	  		  updatemap();
  			updateleftprogressbar();
  			updaterightprogressbar();
  	  	   		currentPlayerInfoupdate();
  	  		   	}
  	  		   	else{
  	  		  	JOptionPane.showMessageDialog(null, "choose a piece first !!"); 
  				return;
  	  		   	}
  	   }});
      right.setBorder(BorderFactory.createEmptyBorder());
      right.setContentAreaFilled(false);
      right.setFont(new java.awt.Font(null, 1, 20));
      right.setOpaque(false);
      right.setForeground(Color.BLACK);
      right.setBounds(0, 0, 50, 50); 
      center_buttons.add(right, BorderLayout.EAST);
      JButton left = new JButton();
      left = new JButton(new ImageIcon("l.png"));
//      left.setRolloverIcon(new ImageIcon("leftglow.png"));
      left.setFocusable(false);
      left.addActionListener(new ActionListener() {
  	   public void actionPerformed(ActionEvent e) {
  		   	if(posi != -1 || posj != -1){
  		   	JButton s= squares[posi][posj];
   	   		
   			Player player=g.getCellAt(posi, posj).getPiece().getOwner();
   			String pieceName=g.getCellAt(posi, posj).getPiece().getName();
   			s.setToolTipText(pieceName +" of "+player);
  	  		   try {
  	  			int oldposi=posi;
				   int oldposj=posj;
				   
//				   	game.getCellAt(posi, posj).getPiece().move(Direction.UPRIGHT);
				   Point p= new Point();
				   p.x=oldposi;
				   p.y=oldposj;
				   p= game.getCellAt(posi, posj).getPiece().getDirectionPos(p, Direction.LEFT);
				   	if(game.getCellAt(posi, posj).getPiece() instanceof SideKick){
				   		if(game.getCellAt(p.x, p.y).getPiece() instanceof ActivatablePowerHero){
				   			game.getCellAt(posi, posj).getPiece().move(Direction.LEFT);
//				   			if(game.getCellAt(oldposi, oldposj).getPiece()==null){
				   			updatemap();
				   			if(game.checkWinner()){
   				   				
   				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
   				   				gameEnd();
   				   			JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
		   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
		   					//Image scale = img.getImage();
		   					Background.setIcon(new ImageIcon(scale));
		   					((RootPaneContainer) themap).setContentPane(Background);
		   					//here
   				   			}
				   		   
//				   			}
				   		}
				   		else{
				   		game.getCellAt(posi, posj).getPiece().move(Direction.LEFT);
				   		updatemap();
				   		if(game.checkWinner()){
				   				
				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
				   				gameEnd();
				   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
			   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
			   					//Image scale = img.getImage();
			   					Background.setIcon(new ImageIcon(scale));
			   					((RootPaneContainer) themap).setContentPane(Background);
			   					//here
				   			}
				   		}
				   	}
				   	else{
				   		game.getCellAt(posi, posj).getPiece().move(Direction.LEFT);
				   		updatemap();
				   		if(game.checkWinner()){
				   				
				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
				   				gameEnd();
				   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
			   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
			   					//Image scale = img.getImage();
			   					Background.setIcon(new ImageIcon(scale));
			   					((RootPaneContainer) themap).setContentPane(Background);
			   					//here
				   			}
				   	}
  			} catch (UnallowedMovementException | OccupiedCellException | WrongTurnException e1) {
  				// TODO Auto-generated catch block
  				JOptionPane.showMessageDialog(null, e1.getMessage().toString()); 
  			}
//  	  		  updatemap();
  			updateleftprogressbar();
  			updaterightprogressbar();
  	  	   	currentPlayerInfoupdate();
  	  		   	}
  	  		   	else{
  	  		  	JOptionPane.showMessageDialog(null, "choose a piece first !!"); 
  				return;
  	  		   	}
  	   }});
      left.setBorder(BorderFactory.createEmptyBorder());
      left.setContentAreaFilled(false);
      left.setFont(new java.awt.Font(null, 1, 20));
      left.setOpaque(false);
      left.setForeground(Color.BLACK);
      left.setBounds(0, 0, 50, 50); 
      center_buttons.add(left , BorderLayout.WEST);
      
      JPanel down_buttons = new JPanel();
      down_buttons.setBackground(Color.white);
      down_buttons.setLayout(new BorderLayout());
      down_buttons.setOpaque(false);
       buttons.add(down_buttons , BorderLayout.SOUTH);
       
       JButton downleft = new JButton("downleft");
       downleft = new JButton(new ImageIcon("dl.png"));
//       downleft.setRolloverIcon(new ImageIcon("downglow.png"));
       downleft.setFocusable(false);
       downleft.addActionListener(new ActionListener() {
   	   public void actionPerformed(ActionEvent e) {
   	   	if(posi != -1 || posj != -1){
   	   	JButton s= squares[posi][posj];
	   		
			Player player=g.getCellAt(posi, posj).getPiece().getOwner();
			String pieceName=g.getCellAt(posi, posj).getPiece().getName();
			s.setToolTipText(pieceName +" of "+player);
   		   try {
   			int oldposi=posi;
			   int oldposj=posj;
			   
//			   	game.getCellAt(posi, posj).getPiece().move(Direction.UPRIGHT);
			   Point p= new Point();
			   p.x=oldposi;
			   p.y=oldposj;
			   p= game.getCellAt(posi, posj).getPiece().getDirectionPos(p, Direction.DOWNLEFT);
			   	if(game.getCellAt(posi, posj).getPiece() instanceof SideKick){
			   		if(game.getCellAt(p.x, p.y).getPiece() instanceof ActivatablePowerHero){
			   			game.getCellAt(posi, posj).getPiece().move(Direction.DOWNLEFT);
//			   			if(game.getCellAt(oldposi, oldposj).getPiece()==null){
			   			updatemap();
			   			if(game.checkWinner()){
				   				
				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
				   				gameEnd();
				   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
			   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
			   					//Image scale = img.getImage();
			   					Background.setIcon(new ImageIcon(scale));
			   					((RootPaneContainer) themap).setContentPane(Background);
			   					//here
				   			}
			   		   
//			   			}
			   		}
			   		else{
			   		game.getCellAt(posi, posj).getPiece().move(Direction.DOWNLEFT);
			   		updatemap();
			   		if(game.checkWinner()){
			   				
			   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
			   				gameEnd();
			   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
		   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
		   					//Image scale = img.getImage();
		   					Background.setIcon(new ImageIcon(scale));
		   					((RootPaneContainer) themap).setContentPane(Background);
		   					//here
			   			}
			   		}
			   	}
			   	else{
			   		game.getCellAt(posi, posj).getPiece().move(Direction.DOWNLEFT);
			   		updatemap();
			   		if(game.checkWinner()){
			   				
			   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
			   				gameEnd();
			   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
			   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
			   					//Image scale = img.getImage();
			   					Background.setIcon(new ImageIcon(scale));
			   					((RootPaneContainer) themap).setContentPane(Background);
			   					//here

			   			}
			   	}
 		} catch (UnallowedMovementException | OccupiedCellException | WrongTurnException e1) {
 			// TODO Auto-generated catch block
 			JOptionPane.showMessageDialog(null, e1.getMessage().toString()); 
 		}
//   		  updatemap();
  		updateleftprogressbar();
  		updaterightprogressbar();
   	   		currentPlayerInfoupdate();
   		   	}
   		   	else{
   		  	JOptionPane.showMessageDialog(null, "choose a piece first !!"); 
 			return;
   		   	}
   	   }});
       downleft.setBorder(BorderFactory.createEmptyBorder());
       downleft.setContentAreaFilled(false);
       downleft.setFont(new java.awt.Font(null, 1, 20));
       downleft.setOpaque(false);
       downleft.setForeground(Color.BLACK);
       downleft.setBounds(0, 0, 50, 50); 
       down_buttons.add(downleft, BorderLayout.WEST);
       
      JButton down = new JButton("down");
      down = new JButton(new ImageIcon("d.png"));
//      down.setRolloverIcon(new ImageIcon("downglow.png"));
      down.setFocusable(false);
      down.addActionListener(new ActionListener() {
  	   public void actionPerformed(ActionEvent e) {
  		   	if(posi != -1 || posj != -1){
  		   	JButton s= squares[posi][posj];
   	   		
   			Player player=g.getCellAt(posi, posj).getPiece().getOwner();
   			String pieceName=g.getCellAt(posi, posj).getPiece().getName();
   			s.setToolTipText(pieceName +" of "+player);
  		   try {
  			 int oldposi=posi;
				   int oldposj=posj;
				   
//				   	game.getCellAt(posi, posj).getPiece().move(Direction.UPRIGHT);
			   Point p= new Point();
			   p.x=oldposi;
			   p.y=oldposj;
			   p= game.getCellAt(posi, posj).getPiece().getDirectionPos(p, Direction.DOWN);
				   	if(game.getCellAt(posi, posj).getPiece() instanceof SideKick){
				   		if(game.getCellAt(p.x, p.y).getPiece() instanceof ActivatablePowerHero){
				   			game.getCellAt(posi, posj).getPiece().move(Direction.DOWN);
//				   			if(game.getCellAt(oldposi, oldposj).getPiece()==null){
				   			updatemap();
				   			if(game.checkWinner()){
   				   				
   				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
   				   				gameEnd();
   				   			JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
		   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
		   					//Image scale = img.getImage();
		   					Background.setIcon(new ImageIcon(scale));
		   					((RootPaneContainer) themap).setContentPane(Background);
		   					//here
   				   			}
				   		   
//				   			}
				   		}
				   		else{
				   		game.getCellAt(posi, posj).getPiece().move(Direction.DOWN);
				   		updatemap();
				   		if(game.checkWinner()){
				   				
				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
				   				gameEnd();
				   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
			   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
			   					//Image scale = img.getImage();
			   					Background.setIcon(new ImageIcon(scale));
			   					((RootPaneContainer) themap).setContentPane(Background);
			   					//here
				   			}
				   		}
				   	}
				   	else{
				   		game.getCellAt(posi, posj).getPiece().move(Direction.DOWN);
				   		updatemap();
				   		if(game.checkWinner()){
				   				
				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
				   				gameEnd();
				   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
			   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
			   					//Image scale = img.getImage();
			   					Background.setIcon(new ImageIcon(scale));
			   					((RootPaneContainer) themap).setContentPane(Background);
			   					//here
				   			}
				   	}
		} catch (UnallowedMovementException | OccupiedCellException | WrongTurnException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.getMessage().toString()); 
		}
//  		  updatemap();
  		
  		  	updateleftprogressbar();
  		    updaterightprogressbar();
  	   		currentPlayerInfoupdate();
  		   	}
  		   	else{
  		  	JOptionPane.showMessageDialog(null, "choose a piece first !!"); 
			return;
  		   	}
  	   }});
      down.setBorder(BorderFactory.createEmptyBorder());
      down.setContentAreaFilled(false);
      down.setFont(new java.awt.Font(null, 1, 20));
      down.setOpaque(false);
      down.setForeground(Color.BLACK);
      down.setBounds(0, 0, 50, 50); 
      down_buttons.add(down, BorderLayout.CENTER);
      
      JButton downright = new JButton("downright");
      downright = new JButton(new ImageIcon("dr.png"));
//      down.setRolloverIcon(new ImageIcon("downglow.png"));
      downright.setFocusable(false);
      downright.addActionListener(new ActionListener() {
  	   public void actionPerformed(ActionEvent e) {
  		     	if(posi != -1 || posj != -1){
  		     		JButton s= squares[posi][posj];
  		   	   		
  		   			Player player=g.getCellAt(posi, posj).getPiece().getOwner();
  		   			String pieceName=g.getCellAt(posi, posj).getPiece().getName();
  		   			s.setToolTipText(pieceName +" of "+player);
  		   try {
  			 int oldposi=posi;
				   int oldposj=posj;
				   
//				   	game.getCellAt(posi, posj).getPiece().move(Direction.UPRIGHT);
			   Point p= new Point();
			   p.x=oldposi;
			   p.y=oldposj;
			   p= game.getCellAt(posi, posj).getPiece().getDirectionPos(p, Direction.DOWNRIGHT);
				   	if(game.getCellAt(posi, posj).getPiece() instanceof SideKick){
				   		if(game.getCellAt(p.x, p.y).getPiece() instanceof ActivatablePowerHero){
				   			game.getCellAt(posi, posj).getPiece().move(Direction.DOWNRIGHT);
//				   			if(game.getCellAt(oldposi, oldposj).getPiece()==null){
				   			updatemap();
				   			if(game.checkWinner()){
   				   				
   				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
   				   			gameEnd();
   				   		JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
	   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
	   					//Image scale = img.getImage();
	   					Background.setIcon(new ImageIcon(scale));
	   					((RootPaneContainer) themap).setContentPane(Background);
	   					//here
   				   			}
				   		   
//				   			}
				   		}
				   		else{
				   		game.getCellAt(posi, posj).getPiece().move(Direction.DOWNRIGHT);
				   		updatemap();
				   		if(game.checkWinner()){
				   				
				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
				   				gameEnd();
				   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
			   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
			   					//Image scale = img.getImage();
			   					Background.setIcon(new ImageIcon(scale));
			   					((RootPaneContainer) themap).setContentPane(Background);
			   					//here
				   			}
				   		}
				   	}
				   	else{
				   		game.getCellAt(posi, posj).getPiece().move(Direction.DOWNRIGHT);
				   		updatemap();
				   		if(game.checkWinner()){
				   				
				   				JOptionPane.showMessageDialog(null, "The game has ended and the winner is  " + game.getCurrentPlayer().getName()); 
				   				gameEnd();
				   				JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
			   					//ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
			   					//Image scale = img.getImage();
			   					Background.setIcon(new ImageIcon(scale));
			   					((RootPaneContainer) themap).setContentPane(Background);
			   					//here
				   			}
				   	}
		} catch (UnallowedMovementException | OccupiedCellException | WrongTurnException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.getMessage().toString()); 
		}
//  		  updatemap();
  		updateleftprogressbar();
  		updaterightprogressbar();
  	   		currentPlayerInfoupdate();
  		   	}
  		   	else{
  		  	JOptionPane.showMessageDialog(null, "choose a piece first !!"); 
			return;
  		   	}
  	   }});
      downright.setBorder(BorderFactory.createEmptyBorder());
      downright.setContentAreaFilled(false);
      downright.setFont(new java.awt.Font(null, 1, 20));
      downright.setOpaque(false);
      downright.setForeground(Color.BLACK);
      downright.setBounds(0, 0, 50, 50); 
      down_buttons.add(downright, BorderLayout.EAST);
      
    
      JPanel use_power = new JPanel();
      use_power.setBackground(Color.white);
      use_power.setLayout(new BorderLayout());
      use_power.setOpaque(false);
      top.add(use_power , BorderLayout.EAST);
      JButton power = new JButton();
      power = new JButton(new ImageIcon("p.png"));
      power.setRolloverIcon(new ImageIcon("p.png"));
      power.setFocusable(false);
//      JFrame choose=new JFrame();
//      JPanel secondWind= new JPanel();
//      choose.add(secondWind);
//      JButton teleport = new JButton(new ImageIcon("teleportbutton.png"));
//      teleport.setBorder(BorderFactory.createEmptyBorder());
//      teleport.setContentAreaFilled(false);
//      teleport.setFont(new java.awt.Font(null, 1, 20));
//      teleport.setOpaque(false);
//      teleport.setForeground(Color.BLACK);
//      teleport.setBounds(0, 0, 50, 50); 
//      secondWind.add(teleport, 	BorderLayout.NORTH);
//      JButton ressurect = new JButton(new ImageIcon("ressurectbutton.png"));
//      ressurect.setBorder(BorderFactory.createEmptyBorder());
//      ressurect.setContentAreaFilled(false);
//      ressurect.setFont(new java.awt.Font(null, 1, 20));
//      ressurect.setOpaque(false);
//      ressurect.setForeground(Color.BLACK);
//      ressurect.setBounds(0, 0, 50, 50); 
//      secondWind.add(ressurect,BorderLayout.CENTER);
//      JButton hack = new JButton(new ImageIcon("hackbutton.png"));
//      hack.setBorder(BorderFactory.createEmptyBorder());
//      hack.setContentAreaFilled(false);
//      hack.setFont(new java.awt.Font(null, 1, 20));
//      hack.setOpaque(false);
//      hack.setForeground(Color.BLACK);
//      hack.setBounds(0, 0, 50, 50); 
//      secondWind.add(hack, 	BorderLayout.SOUTH);
      
//      if(power.isEnabled())
 	   		
    	  power.addActionListener(new ActionListener() {
    		  public void actionPerformed(ActionEvent e) {
//    			  choose.setVisible(true);
//  		   int count=0;
  		 if(posi != -1 || posj != -1){
//  			  count++;
//  			   Direction chosen;
  			   
//    		   try {
  		   
    			  if(game.getCellAt(posi, posj).getPiece() instanceof Armored || game.getCellAt(posi, posj).getPiece() instanceof Speedster ||
    					  game.getCellAt(posi, posj).getPiece() instanceof SideKick){
    				  JOptionPane.showMessageDialog(null, "This piece has no special ability to use it"); 
      		  		return;
    				  
    			  }
    			  else if(game.getCellAt(posi, posj).getPiece() instanceof Super){
 			 		 int x=JOptionPane.showOptionDialog(null, game.getCellAt(posi, posj).getPiece().getName()+"'s power", "Choose direction", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Direction.values(), Direction.values()[0]);
    		
        		  		 try {
        							if(x!=-1){
        								if(((Super)game.getCellAt(posi, posj).getPiece()).isPowerUsed()){
        		    						JOptionPane.showMessageDialog(null,((Super)game.getCellAt(posi, posj).getPiece()).getName() + " has already used its power");

        								}
        								else{
        									((Super)game.getCellAt(posi, posj).getPiece()).usePower(Direction.values()[x], null, null);
        								}
        								updatemap();
        								updateleftprogressbar();
        								updaterightprogressbar();
        								currentPlayerInfoupdate();
        							
    								
        							}
        		  		 	}
        		  		 				catch (InvalidPowerUseException | WrongTurnException e1) {
        	    						JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    							}
 			  }
    			  
//    			  else if(game.getCellAt(posi, posj).getPiece() instanceof Medic){
//  			 		 int x=JOptionPane.showOptionDialog(null, game.getCellAt(posi, posj).getPiece().getName()+"'s power", "Choose direction", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Direction.values(), Direction.values()[0]);
//     		          
//         		  		 try {
//         							if(x!=-1){
//         								((Medic)game.getCellAt(posi, posi).getPiece()).usePower(Direction.values()[x], null, null);
//     								updatemap();
//     							 updateleftprogressbar();
//     				    		 updaterightprogressbar();
//     								currentPlayerInfoupdate();
//         							}
//         							} catch (InvalidPowerUseException | WrongTurnException e1) {
//         	    						JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//     							}
//  			  }
    			  else if (game.getCellAt(posi, posj).getPiece() instanceof Medic){
    			  int x=JOptionPane.showOptionDialog(null, game.getCellAt(posi, posj).getPiece().getName()+"'s power", "Choose direction", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Direction.values(), Direction.values()[0]);
					String[] possibilities=new String[game.getCurrentPlayer().getDeadCharacters().size()];
					for(int se=0; se<game.getCurrentPlayer().getDeadCharacters().size();se++){
						possibilities[se] = game.getCurrentPlayer().getDeadCharacters().get(se).getName();
					}
					Piece f = null;
					String ff = (String)JOptionPane.showInputDialog(
     				                    null,
     				                    " Choose Piece :- ",
     				                    " Died Characters ",
     				                    JOptionPane.PLAIN_MESSAGE,
     				                    null,possibilities,possibilities[0]);
					for(int se=0; se<game.getCurrentPlayer().getDeadCharacters().size();se++){
						if(ff.equalsIgnoreCase(game.getCurrentPlayer().getDeadCharacters().get(se).getName())){
							 f = game.getCurrentPlayer().getDeadCharacters().get(se);
						}
					}
					Point p= new Point();
					p.x=game.getCellAt(posi,posj).getPiece().getPosI();
					p.y=game.getCellAt(posi,posj).getPiece().getPosJ();
					 try {
							if(x!=-1 && f!=null){
								if(((Medic)game.getCellAt(posi, posj).getPiece()).isPowerUsed()){
		    						JOptionPane.showMessageDialog(null,((Medic)game.getCellAt(posi, posj).getPiece()).getName() + " has already used its power");

								}
							else{
								((Medic)game.getCellAt(posi, posj).getPiece()).usePower(Direction.values()[x], f, game.getCellAt(posi,posj).getPiece().getDirectionPos(p,Direction.values()[x]));
							}
							updatemap();
							updateleftprogressbar();
							updaterightprogressbar();
							currentPlayerInfoupdate();
							
							}
							} catch (Exception e1) {
	    						JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						}
				  
			  }
    			  
    			  else if(game.getCellAt(posi, posj).getPiece() instanceof Ranged){
  			 		 int x=JOptionPane.showOptionDialog(null, game.getCellAt(posi, posj).getPiece().getName()+"'s power", "Choose direction", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Direction.values(), Direction.values()[0]);
     		
         		  		 try {
         							if(x!=-1){
         								if(((Ranged)game.getCellAt(posi, posj).getPiece()).isPowerUsed()){
         		    						JOptionPane.showMessageDialog(null,((Ranged)game.getCellAt(posi, posj).getPiece()).getName() + " has already used its power");

         								}
         								else{
         								((Ranged)game.getCellAt(posi, posj).getPiece()).usePower(Direction.values()[x], null, null);
         								}
     								updatemap();
     							 updateleftprogressbar();
     				    		 updaterightprogressbar();
     								currentPlayerInfoupdate();
         							}
         							} catch (InvalidPowerUseException | WrongTurnException e1) {
         	    						JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
     							}
  			  }
    			  
    			  else if(game.getCellAt(posi, posj).getPiece() instanceof Tech){
    				  if(game.getCellAt(posi, posj).getPiece().getOwner()==game.getCurrentPlayer()){
//    				 ArrayList<String>abilities=new ArrayList();
//    				 abilities.add("Hack");
//    				 abilities.add("Teleport");
//    				 abilities.add("Restore");
   			 		 
  			 		 int y=JOptionPane.showOptionDialog(null, game.getCellAt(posi, posj).getPiece().getName()+"'s power", "Choose one ability", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Abilities.values(), Abilities.values()[0]);
  			 		 
  			 		 
  			 		 
  			 			 if(y!=-1){
  			 				if(((Tech)game.getCellAt(posi, posj).getPiece()).isPowerUsed()){
			 						JOptionPane.showMessageDialog(null,((Tech)game.getCellAt(posi, posj).getPiece()).getName() + " has already used its power");

			 					}
  			 				else{
  			 					if(y==0){
  			 						System.out.println("hack");
  			 						hacked=true;
  			 						JOptionPane.showMessageDialog(null, "Please choose a hero piece to hack","notify",JOptionPane.PLAIN_MESSAGE);
									
  			 						//hack
//  			 						JOptionPane.showMessageDialog(null,"Choose an enemy hero to hack its power");
//  			 						for(int i=0; i<game.getBoardHeight(); i++) {
//  			 							for(int j=0; j<game.getBoardWidth(); j++) {
//  			 								squares2[i][j] = new JButton();
//  			 				                themap.add(squares2[i][j]);
//  			 				                squares2[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
////  			 				                upr.setBorder(BorderFactory.createEmptyBorder());
//  			 				                squares2[i][j].setContentAreaFilled(false);
//  			 								final int secondI=i;
//  	  			 							final int secondJ=j;
//  	  			 							squares2[i][j].addActionListener(new ActionListener() {
//  	  			 								public void actionPerformed(ActionEvent e) {
//  	  			 									try {
//  	  			 										((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(secondI, secondJ).getPiece(),null);
//  	  			 									} 
//  	  			 									catch (InvalidPowerUseException e2) {
//  	  			 										// TODO Auto-generated catch block
//  	  			 										e2.printStackTrace();
//  	  			 									} 
//  	  			 									catch (WrongTurnException e2) {
//  	  			 										// TODO Auto-generated catch block
//  	  			 										e2.printStackTrace();
//  	  			 									}
//  	  			 									
//
//  	  			 								}
//  	  			 								});
//  			 							}
//  			 						}
  			 						//ba7awel akhtar el piece ely ha hack abooha
//  			 						if(secondPosi!=-1 || secondPosj!=-1){
////  			 							count++;
////  			 							if(count==2)
//  			 							((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(secondPosi, secondPosj).getPiece(),null);
//  			 						}
//  			 							
//  			 						else{
//  				 						JOptionPane.showMessageDialog(null," you have to choose an enemy hero to hack it");
//
//  			 						}

 								
//  			 						((Tech)game.getCellAt(posi, posj).getPiece()).usePower(, null, null);
  			 					}
  			 					else if(y==1){
  			 						//teleport
  			 						teleported=true;
                              	   	JOptionPane.showMessageDialog(null, "Choose an friendly piece to teleport","notify",JOptionPane.PLAIN_MESSAGE);

  			 						
  			 					}
  			 					
  			 					else if(y==2){
  			 						restored=true;
  			 						//restore
  			 						

  			 					}
  			 				}	
  	  			 				// updatemap();
  	  			 				updateleftprogressbar();
  	  			 				updaterightprogressbar();
								currentPlayerInfoupdate();
  			 				 
  	  			 		
  			 				 
  			 				
  			 			 }
  			 		 
  			 		 

    			  }
    			  
    			  else{
    				  JOptionPane.showMessageDialog(null, "it's not your turn","Warning",JOptionPane.WARNING_MESSAGE);    			  }
    			  
//  		} 
//    		   catch(InvalidPowerUseException | WrongTurnException e1){
//    			   
//    		   }
    			  }
    		  updatemap();
    		updateleftprogressbar();
    		updaterightprogressbar();
    	   		currentPlayerInfoupdate();
    		   	}
    		   	else{
    		   		JOptionPane.showMessageDialog(null, "choose a piece first !!"); 
    		  		return;
    		   		}
    		  
  		
  	   }});
      power.setToolTipText("powers");
      power.setBorder(BorderFactory.createEmptyBorder());
      power.setContentAreaFilled(false);
      power.setFont(new java.awt.Font(null, 1, 20));
      power.setOpaque(false);
      power.setForeground(Color.BLACK);
      power.setBounds(0, 0, 50, 50); 
      use_power.add(power, BorderLayout.CENTER);
      
	}
	
	public void updatemap(){
		themap.removeAll();
		  for(int i=0; i<game.getBoardHeight(); i++)
	        {
	            for(int j=0; j<game.getBoardWidth(); j++)
	            {
	                squares[i][j] = new JButton();
	                themap.add(squares[i][j]);
	                squares[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
//	                upr.setBorder(BorderFactory.createEmptyBorder());
	                squares[i][j].setContentAreaFilled(false);
	                final int ii=i;
	                final int jj=j;
	                		if(game.getCellAt(i, j).getPiece() instanceof Armored && game.getCellAt(i, j).getPiece().getName().equals("Wonder Woman") ){
//	                			System.out.println("the i is : " + i + " and the j is : " + j );
	                			squares[i][j].setIcon(new ImageIcon("ww.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                            	   public void actionPerformed(ActionEvent e) {
	                            		   System.out.println("wrong");
	                   					   if(!hacked && !restored && !teleported){
	                   						   System.out.println("nooo");
	                   						   try{
	                                      	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
	                                      	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                      	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);
	                   						   }
	                   						   catch(Exception e1){
	    			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

	                   						   }
	                                      		   }
	                                      		   else if(hacked && !restored && !teleported){
	                                      			   System.out.println("1");
	                                      			   if(h_counter == 1){
	                                      				 System.out.println("2");
	                                      				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                      				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                      				 System.out.println("3");
	            			 							try {
	            			 								System.out.println(posi);
	            			 								System.out.println(posj);
	            			 								System.out.println(posi1);
	            			 								System.out.println(posj1);
	    													((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
	    												h_counter = 1;
	    												hacked = false;
	    												updatemap();
	            			 							} catch (Exception e1) {
	    													// TODO Auto-generated catch block
	            			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	    												}
	                                      			   }
//	                                      			   else if(h_counter == 1){
//	                                      				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//	                                      				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                      				   h_counter--;
//	                                      			   }
//	                                      			   else{
//	                                      			
	    //
//	                                      			   }
	                                      		   }
	                                      		   else if(restored && !hacked &&!teleported){
	                                      			 System.out.println("1");
	                                      			   if(r_counter == 1){
	                                      				 System.out.println("2");
	                                      				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                      				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                      				 System.out.println("3");
	            			 							try {
	            			 								System.out.println(posi);
	            			 								System.out.println(posj);
	            			 								System.out.println(posi1);
	            			 								System.out.println(posj1);
	    													((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
	    												r_counter = 1;
	    												restored = false;
	    												updatemap();
	            			 							} catch (Exception e1) {
	    													// TODO Auto-generated catch block
	            			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	    												}
	                                      			   }
	                                      		   }
	                                      		 else if(!restored && !hacked && teleported){
	                                      			 System.out.println("1");
	                                      			   if(t_counter == 2){
	                                      				 System.out.println("2");
	                                      				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                      				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                      				   t_counter--;
	                                                 	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);

	                                      			   }
	                                      			   
	                                      		   }
	                                      		 else{
	                                      			restored = false;
	                                      			hacked = false;
	                                      			teleported = false;
	                                         	   	JOptionPane.showMessageDialog(null, "You chose more than one ability; you have to choose one only! ","notify",JOptionPane.PLAIN_MESSAGE);

	                                      		 }
	                            	   	   }});
	                        	String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//	              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
	                    	   	String text="Armored of " + player+"\n";
	              	   				if(((Armored)game.getCellAt(i, j).getPiece()).isArmorUp()){
	              	   					text+="Armor is up";
	              	   					squares[i][j].setToolTipText(text);
	              	   				}
	              	   				else{
	              	   					text+="Armor is down";
	              	   					squares[i][j].setToolTipText(text);
	              	   				
	              	   			}
	                		}
	                		else if(game.getCellAt(i, j).getPiece() instanceof Armored && game.getCellAt(i, j).getPiece().getName().equals("Captain America")){
	                			squares[i][j].setIcon(new ImageIcon("ca.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  System.out.println("wrong");
	               					   if(!hacked && !restored && !teleported){
	               						   System.out.println("nooo");
	               						   try{
	                                  	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
	                                  	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);
	               						   }
	               						   catch(Exception e1){
				 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

	               						   }
	                                  		   }
	                                  		   else if(hacked && !restored && !teleported){
	                                  			   System.out.println("1");
	                                  			   if(h_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													h_counter = 1;
													hacked = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
//	                                  			   else if(h_counter == 1){
//	                                  				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//	                                  				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  				   h_counter--;
//	                                  			   }
//	                                  			   else{
//	                                  			
	//
//	                                  			   }
	                                  		   }
	                                  		   else if(restored && !hacked &&!teleported){
	                                  			 System.out.println("1");
	                                  			   if(r_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													r_counter = 1;
													restored = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
	                                  		   }
	                                  		 else if(!restored && !hacked && teleported){
	                                  			 System.out.println("1");
	                                  			   if(t_counter == 2){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				   t_counter--;
	                                             	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);

	                                  			   }
	                                  			   
	                                  		   }
	                                  		 else{
	                                  			restored = false;
	                                  			hacked = false;
	                                  			teleported = false;
	                                     	   	JOptionPane.showMessageDialog(null, "You chose more than one ability; you have to choose one only! ","notify",JOptionPane.PLAIN_MESSAGE);

	                                  		 }
	                         	   	   }});
	                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//		              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
		                    	   	String text="Armored of " + player+"\n";
		              	   				if(((Armored)game.getCellAt(i, j).getPiece()).isArmorUp()){
		              	   					text+="Armor is up";
		              	   					squares[i][j].setToolTipText(text);
		              	   				}
		              	   				else{
		              	   					text+="Armor is down";
		              	   					squares[i][j].setToolTipText(text);
		              	   				
		              	   			}
	                            
	                		}
//	                		else if(game.getCellAt(i, j).getPiece() instanceof Armored && game.getCellAt(i, j).getPiece().getName().equals("Wonder Woman") && !((Armored)(game.getCellAt(i, j).getPiece())).isArmorUp() ){
////	                			System.out.println("the i is : " + i + " and the j is : " + j );
//	                			squares[i][j].setIcon(new ImageIcon("wwa.png"));
//	                            squares[i][j].setOpaque(false);
//	                            squares[i][j].addActionListener(new ActionListener() {
//	                         	   public void actionPerformed(ActionEvent e) {
//	                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
//	                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                         	   	   }});
//	                            
//	                		}
//	                		else if(game.getCellAt(i, j).getPiece() instanceof Armored && game.getCellAt(i, j).getPiece().getName().equals("Captain America") && !((Armored)(game.getCellAt(i, j).getPiece())).isArmorUp()){
//	                			squares[i][j].setIcon(new ImageIcon("caa.png"));
//	                            squares[i][j].setOpaque(false);
//	                            squares[i][j].addActionListener(new ActionListener() {
//	                         	   public void actionPerformed(ActionEvent e) {
//	                         	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
//	                         	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                         	   	   }});
//	                		}
	                		else if(game.getCellAt(i, j).getPiece() instanceof Medic && game.getCellAt(i, j).getPiece().getName().equals("Green Lantern")){
	                			squares[i][j].setIcon(new ImageIcon("gl.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  System.out.println("wrong");
	               					   if(!hacked && !restored && !teleported){
	               						   System.out.println("nooo");
	               						   try{
	                                  	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
	                                  	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);
	               						   }
	               						   catch(Exception e1){
				 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

	               						   }
	                                  		   }
	                                  		   else if(hacked && !restored && !teleported){
	                                  			   System.out.println("1");
	                                  			   if(h_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													h_counter = 1;
													hacked = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
//	                                  			   else if(h_counter == 1){
//	                                  				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//	                                  				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  				   h_counter--;
//	                                  			   }
//	                                  			   else{
//	                                  			
	//
//	                                  			   }
	                                  		   }
	                                  		   else if(restored && !hacked &&!teleported){
	                                  			 System.out.println("1");
	                                  			   if(r_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													r_counter = 1;
													restored = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
	                                  		   }
	                                  		 else if(!restored && !hacked && teleported){
	                                  			 System.out.println("1");
	                                  			   if(t_counter == 2){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				   t_counter--;
	                                             	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);

	                                  			   }
	                                  			   
	                                  		   }
	                                  		 else{
	                                  			restored = false;
	                                  			hacked = false;
	                                  			teleported = false;
	                                     	   	JOptionPane.showMessageDialog(null, "You chose more than one ability; you have to choose one only! ","notify",JOptionPane.PLAIN_MESSAGE);

	                                  		 }
	                         	   	   }});
	                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//	              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
	                    	   	String text="Medic of " + player+"\n";
	              	   				if(((Medic)game.getCellAt(i, j).getPiece()).isPowerUsed()){
	              	   					text+="Power is used";
	              	   					squares[i][j].setToolTipText(text);
	              	   				}
	              	   				else{
	              	   					text+="Power is unused";
	              	   					squares[i][j].setToolTipText(text);
	              	   				
	              	   			}
	                		}
	                		else if(game.getCellAt(i, j).getPiece() instanceof Medic && game.getCellAt(i, j).getPiece().getName().equals("Vision")){
	                			squares[i][j].setIcon(new ImageIcon("v.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  System.out.println("wrong");
	               					   if(!hacked && !restored && !teleported){
	               						   System.out.println("nooo");
	               						   try{
	                                  	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
	                                  	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);
	               						   }
	               						   catch(Exception e1){
				 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

	               						   }
	                                  		   }
	                                  		   else if(hacked && !restored && !teleported){
	                                  			   System.out.println("1");
	                                  			   if(h_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													h_counter = 1;
													hacked = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
//	                                  			   else if(h_counter == 1){
//	                                  				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//	                                  				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  				   h_counter--;
//	                                  			   }
//	                                  			   else{
//	                                  			
	//
//	                                  			   }
	                                  		   }
	                                  		   else if(restored && !hacked &&!teleported){
	                                  			 System.out.println("1");
	                                  			   if(r_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													r_counter = 1;
													restored = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
	                                  		   }
	                                  		 else if(!restored && !hacked && teleported){
	                                  			 System.out.println("1");
	                                  			   if(t_counter == 2){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				   t_counter--;
	                                             	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);

	                                  			   }
	                                  			   
	                                  		   }
	                                  		 else{
	                                  			restored = false;
	                                  			hacked = false;
	                                  			teleported = false;
	                                     	   	JOptionPane.showMessageDialog(null, "You chose more than one ability; you have to choose one only! ","notify",JOptionPane.PLAIN_MESSAGE);

	                                  		 }
	                         	   	   }});
	                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//		              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
		                    	   	String text="Medic of " + player+"\n";
		              	   				if(((Medic)game.getCellAt(i, j).getPiece()).isPowerUsed()){
		              	   					text+="Power is used";
		              	   					squares[i][j].setToolTipText(text);
		              	   				}
		              	   				else{
		              	   					text+="Power is unused";
		              	   					squares[i][j].setToolTipText(text);
		              	   				
		              	   			}
	                		}
	                		else if(game.getCellAt(i, j).getPiece() instanceof Ranged && game.getCellAt(i, j).getPiece().getName().equals("Green Arrow")){
	                			squares[i][j].setIcon(new ImageIcon("ga.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  System.out.println("wrong");
               					   if(!hacked && !restored && !teleported){
               						   System.out.println("nooo");
               						   try{
                                  	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                                  	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//                                  	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);
               						   }
               						   catch(Exception e1){
			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

               						   }
                                  		   }
                                  		   else if(hacked && !restored && !teleported){
                                  			   System.out.println("1");
                                  			   if(h_counter == 1){
                                  				 System.out.println("2");
                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
                                  				 System.out.println("3");
        			 							try {
        			 								System.out.println(posi);
        			 								System.out.println(posj);
        			 								System.out.println(posi1);
        			 								System.out.println(posj1);
													((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
												h_counter = 1;
												hacked = false;
												updatemap();
        			 							} catch (Exception e1) {
													// TODO Auto-generated catch block
        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
												}
                                  			   }
//                                  			   else if(h_counter == 1){
//                                  				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//                                  				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//                                  				   h_counter--;
//                                  			   }
//                                  			   else{
//                                  			
//
//                                  			   }
                                  		   }
                                  		   else if(restored && !hacked &&!teleported){
                                  			 System.out.println("1");
                                  			   if(r_counter == 1){
                                  				 System.out.println("2");
                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
                                  				 System.out.println("3");
        			 							try {
        			 								System.out.println(posi);
        			 								System.out.println(posj);
        			 								System.out.println(posi1);
        			 								System.out.println(posj1);
													((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
												r_counter = 1;
												restored = false;
												updatemap();
        			 							} catch (Exception e1) {
													// TODO Auto-generated catch block
        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
												}
                                  			   }
                                  		   }
                                  		 else if(!restored && !hacked && teleported){
                                  			 System.out.println("1");
                                  			   if(t_counter == 2){
                                  				 System.out.println("2");
                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
                                  				   t_counter--;
                                             	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);

                                  			   }
                                  			   
                                  		   }
                                  		 else{
                                  			restored = false;
                                  			hacked = false;
                                  			teleported = false;
                                     	   	JOptionPane.showMessageDialog(null, "You chose more than one ability; you have to choose one only! ","notify",JOptionPane.PLAIN_MESSAGE);

                                  		 }
	                         	   	   }});
	                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//		              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
		                    	   	String text="Ranged of " + player+"\n";
		              	   				if(((Ranged)game.getCellAt(i, j).getPiece()).isPowerUsed()){
		              	   					text+="Power is used";
		              	   					squares[i][j].setToolTipText(text);
		              	   				}
		              	   				else{
		              	   					text+="Power is unused";
		              	   					squares[i][j].setToolTipText(text);
		              	   				
		              	   			}
	                		}
	                		else if(game.getCellAt(i, j).getPiece() instanceof Ranged && game.getCellAt(i, j).getPiece().getName().equals("Hawk Eye")){
	                			squares[i][j].setIcon(new ImageIcon("he.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  System.out.println("wrong");
	               					   if(!hacked && !restored && !teleported){
	               						   System.out.println("nooo");
	               						   try{
	                                  	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
	                                  	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);
	               						   }
	               						   catch(Exception e1){
				 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

	               						   }
	                                  		   }
	                                  		   else if(hacked && !restored && !teleported){
	                                  			   System.out.println("1");
	                                  			   if(h_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													h_counter = 1;
													hacked = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
//	                                  			   else if(h_counter == 1){
//	                                  				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//	                                  				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  				   h_counter--;
//	                                  			   }
//	                                  			   else{
//	                                  			
	//
//	                                  			   }
	                                  		   }
	                                  		   else if(restored && !hacked &&!teleported){
	                                  			 System.out.println("1");
	                                  			   if(r_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													r_counter = 1;
													restored = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
	                                  		   }
	                                  		 else if(!restored && !hacked && teleported){
	                                  			 System.out.println("1");
	                                  			   if(t_counter == 2){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				   t_counter--;
	                                             	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);

	                                  			   }
	                                  			   
	                                  		   }
	                                  		 else{
	                                  			restored = false;
	                                  			hacked = false;
	                                  			teleported = false;
	                                     	   	JOptionPane.showMessageDialog(null, "You chose more than one ability; you have to choose one only! ","notify",JOptionPane.PLAIN_MESSAGE);

	                                  		 }
	                         	   	   }});
	                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//		              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
		                    	   	String text="Ranged of " + player+"\n";
		              	   				if(((Ranged)game.getCellAt(i, j).getPiece()).isPowerUsed()){
		              	   					text+="Power is used";
		              	   					squares[i][j].setToolTipText(text);
		              	   				}
		              	   				else{
		              	   					text+="Power is unused";
		              	   					squares[i][j].setToolTipText(text);
		              	   				
		              	   			}
	                		}
	                		else if(game.getCellAt(i, j).getPiece() instanceof Speedster && game.getCellAt(i, j).getPiece().getName().equals("Flash")){
	                			squares[i][j].setIcon(new ImageIcon("f.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  System.out.println("wrong");
	               					   if(!hacked && !restored && !teleported){
	               						   System.out.println("nooo");
	               						   try{
	                                  	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
	                                  	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);
	               						   }
	               						   catch(Exception e1){
				 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

	               						   }
	                                  		   }
	                                  		   else if(hacked && !restored && !teleported){
	                                  			   System.out.println("1");
	                                  			   if(h_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													h_counter = 1;
													hacked = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
//	                                  			   else if(h_counter == 1){
//	                                  				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//	                                  				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  				   h_counter--;
//	                                  			   }
//	                                  			   else{
//	                                  			
	//
//	                                  			   }
	                                  		   }
	                                  		   else if(restored && !hacked &&!teleported){
	                                  			 System.out.println("1");
	                                  			   if(r_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													r_counter = 1;
													restored = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
	                                  		   }
	                                  		 else if(!restored && !hacked && teleported){
	                                  			 System.out.println("1");
	                                  			   if(t_counter == 2){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				   t_counter--;
	                                             	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);

	                                  			   }
	                                  			   
	                                  		   }
	                                  		 else{
	                                  			restored = false;
	                                  			hacked = false;
	                                  			teleported = false;
	                                     	   	JOptionPane.showMessageDialog(null, "You chose more than one ability; you have to choose one only! ","notify",JOptionPane.PLAIN_MESSAGE);

	                                  		 }
	                         	   	   }});
	                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//	              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
	                    	   	String text="Speedster of " + player;
	                    	   	squares[i][j].setToolTipText(text);
	                		}
	                		else if(game.getCellAt(i, j).getPiece() instanceof Speedster && game.getCellAt(i, j).getPiece().getName().equals("Quick Silver")){
	                			squares[i][j].setIcon(new ImageIcon("qs.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  System.out.println("wrong");
	               					   if(!hacked && !restored && !teleported){
	               						   System.out.println("nooo");
	               						   try{
	                                  	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
	                                  	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);
	               						   }
	               						   catch(Exception e1){
				 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

	               						   }
	                                  		   }
	                                  		   else if(hacked && !restored && !teleported){
	                                  			   System.out.println("1");
	                                  			   if(h_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													h_counter = 1;
													hacked = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
//	                                  			   else if(h_counter == 1){
//	                                  				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//	                                  				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  				   h_counter--;
//	                                  			   }
//	                                  			   else{
//	                                  			
	//
//	                                  			   }
	                                  		   }
	                                  		   else if(restored && !hacked &&!teleported){
	                                  			 System.out.println("1");
	                                  			   if(r_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													r_counter = 1;
													restored = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
	                                  		   }
	                                  		 else if(!restored && !hacked && teleported){
	                                  			 System.out.println("1");
	                                  			   if(t_counter == 2){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				   t_counter--;
	                                             	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);

	                                  			   }
	                                  			   
	                                  		   }
	                                  		 else{
	                                  			restored = false;
	                                  			hacked = false;
	                                  			teleported = false;
	                                     	   	JOptionPane.showMessageDialog(null, "You chose more than one ability; you have to choose one only! ","notify",JOptionPane.PLAIN_MESSAGE);

	                                  		 }
	                         	   	   }});
	                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//	              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
	                    	   	String text="Speedster of " + player;
	                    	   	squares[i][j].setToolTipText(text);
	                		}
	                		else if(game.getCellAt(i, j).getPiece() instanceof Super && game.getCellAt(i, j).getPiece().getName().equals("Superman")){
	                			squares[i][j].setIcon(new ImageIcon("s.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  System.out.println("wrong");
	               					   if(!hacked && !restored && !teleported){
	               						   System.out.println("nooo");
	               						   try{
	                                  	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
	                                  	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);
	               						   }
	               						   catch(Exception e1){
				 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

	               						   }
	                                  		   }
	                                  		   else if(hacked && !restored && !teleported){
	                                  			   System.out.println("1");
	                                  			   if(h_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													h_counter = 1;
													hacked = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
//	                                  			   else if(h_counter == 1){
//	                                  				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//	                                  				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  				   h_counter--;
//	                                  			   }
//	                                  			   else{
//	                                  			
	//
//	                                  			   }
	                                  		   }
	                                  		   else if(restored && !hacked &&!teleported){
	                                  			 System.out.println("1");
	                                  			   if(r_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													r_counter = 1;
													restored = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
	                                  		   }
	                                  		 else if(!restored && !hacked && teleported){
	                                  			 System.out.println("1");
	                                  			   if(t_counter == 2){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				   t_counter--;
	                                             	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);

	                                  			   }
	                                  			   
	                                  		   }
	                                  		 else{
	                                  			restored = false;
	                                  			hacked = false;
	                                  			teleported = false;
	                                     	   	JOptionPane.showMessageDialog(null, "You chose more than one ability; you have to choose one only! ","notify",JOptionPane.PLAIN_MESSAGE);

	                                  		 }
	                         	   	   }});
	                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//		              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
		                    	   	String text="Super of " + player+"\n";
		              	   				if(((Super)game.getCellAt(i, j).getPiece()).isPowerUsed()){
		              	   					text+="Power is used";
		              	   					squares[i][j].setToolTipText(text);
		              	   				}
		              	   				else{
		              	   					text+="Power is unused";
		              	   					squares[i][j].setToolTipText(text);
		              	   				
		              	   			}
	                		}
	                		else if(game.getCellAt(i, j).getPiece() instanceof Super && game.getCellAt(i, j).getPiece().getName().equals("Hulk")){
	                			squares[i][j].setIcon(new ImageIcon("h.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  System.out.println("wrong");
	               					   if(!hacked && !restored && !teleported){
	               						   System.out.println("nooo");
	               						   try{
	                                  	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
	                                  	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);
	               						   }
	               						   catch(Exception e1){
				 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

	               						   }
	                                  		   }
	                                  		   else if(hacked && !restored && !teleported){
	                                  			   System.out.println("1");
	                                  			   if(h_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													h_counter = 1;
													hacked = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
//	                                  			   else if(h_counter == 1){
//	                                  				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//	                                  				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  				   h_counter--;
//	                                  			   }
//	                                  			   else{
//	                                  			
	//
//	                                  			   }
	                                  		   }
	                                  		   else if(restored && !hacked &&!teleported){
	                                  			 System.out.println("1");
	                                  			   if(r_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													r_counter = 1;
													restored = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
	                                  		   }
	                                  		 else if(!restored && !hacked && teleported){
	                                  			 System.out.println("1");
	                                  			   if(t_counter == 2){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				   t_counter--;
	                                             	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);

	                                  			   }
	                                  			   
	                                  		   }
	                                  		 else{
	                                  			restored = false;
	                                  			hacked = false;
	                                  			teleported = false;
	                                     	   	JOptionPane.showMessageDialog(null, "You chose more than one ability; you have to choose one only! ","notify",JOptionPane.PLAIN_MESSAGE);

	                                  		 }
	                         	   	   }});
	                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//		              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
		                    	   	String text="Super of " + player+"\n";
		              	   				if(((Super)game.getCellAt(i, j).getPiece()).isPowerUsed()){
		              	   					text+="Power is used";
		              	   					squares[i][j].setToolTipText(text);
		              	   				}
		              	   				else{
		              	   					text+="Power is unused";
		              	   					squares[i][j].setToolTipText(text);
		              	   				
		              	   			}
	                		}
	                		else if(game.getCellAt(i, j).getPiece() instanceof Tech && game.getCellAt(i, j).getPiece().getName().equals("Batman")){
	                			squares[i][j].setIcon(new ImageIcon("b.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  System.out.println("wrong");
               					   if(!hacked && !restored && !teleported){
               						   System.out.println("nooo");
                                  	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
                                  	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
                                  		   }
                                  		   else if(hacked && !restored && !teleported){
                                  			   System.out.println("1");
                                  			   if(h_counter == 1){
                                  				 System.out.println("2");
                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
                                  				 System.out.println("3");
        			 							try {
        			 								System.out.println(posi);
        			 								System.out.println(posj);
        			 								System.out.println(posi1);
        			 								System.out.println(posj1);
													((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
												h_counter = 1;
												hacked = false;
												updatemap();
        			 							} catch (Exception e1) {
													// TODO Auto-generated catch block
        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
												}
                                  			   }
//                                  			   else if(h_counter == 1){
//                                  				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//                                  				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//                                  				   h_counter--;
//                                  			   }
//                                  			   else{
//                                  			
//
//                                  			   }
                                  		   }
                                  		   else if(restored && !hacked &&!teleported){
                                  			 System.out.println("1");
                                  			   if(r_counter == 1){
                                  				 System.out.println("2");
                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
                                  				 System.out.println("3");
        			 							try {
        			 								System.out.println(posi);
        			 								System.out.println(posj);
        			 								System.out.println(posi1);
        			 								System.out.println(posj1);
													((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
												r_counter = 1;
												restored = false;
												updatemap();
        			 							} catch (Exception e1) {
													// TODO Auto-generated catch block
        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
												}
                                  			   }
                                  		   }
	                         	   	   }});
	                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//		              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
		                    	   	String text="Tech of " + player+"\n";
		              	   				if(((Tech)game.getCellAt(i, j).getPiece()).isPowerUsed()){
		              	   					text+="Power is used";
		              	   					squares[i][j].setToolTipText(text);
		              	   				}
		              	   				else{
		              	   					text+="Power is unused";
		              	   					squares[i][j].setToolTipText(text);
		              	   			}
	                		}
	                		else if(game.getCellAt(i, j).getPiece() instanceof Tech && game.getCellAt(i, j).getPiece().getName().equals("Ironman")){
	                			squares[i][j].setIcon(new ImageIcon("im.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  System.out.println("wrong");
	               					   if(!hacked && !restored && !teleported){
	               						   System.out.println("nooo");
	               						   try{
	                                  	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
	                                  	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);
	               						   }
	               						   catch(Exception e1){
				 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

	               						   }
	                                  		   }
	                                  		   else if(hacked && !restored && !teleported){
	                                  			   System.out.println("1");
	                                  			   if(h_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													h_counter = 1;
													hacked = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
//	                                  			   else if(h_counter == 1){
//	                                  				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//	                                  				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  				   h_counter--;
//	                                  			   }
//	                                  			   else{
//	                                  			
	//
//	                                  			   }
	                                  		   }
	                                  		   else if(restored && !hacked &&!teleported){
	                                  			 System.out.println("1");
	                                  			   if(r_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													r_counter = 1;
													restored = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
	                                  		   }
	                                  		 else if(!restored && !hacked && teleported){
	                                  			 System.out.println("1");
	                                  			   if(t_counter == 2){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				   t_counter--;
	                                             	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);

	                                  			   }
	                                  			   
	                                  		   }
	                                  		 else{
	                                  			restored = false;
	                                  			hacked = false;
	                                  			teleported = false;
	                                     	   	JOptionPane.showMessageDialog(null, "You chose more than one ability; you have to choose one only! ","notify",JOptionPane.PLAIN_MESSAGE);

	                                  		 }
	                         	   	   }});
	                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
//		              	   		String pieceName=game.getCellAt(posi, posj).getPiece().getName();
		                    	   	String text="Tech of " + player+"\n";
		              	   				if(((Tech)game.getCellAt(i, j).getPiece()).isPowerUsed()){
		              	   					text+="Power is used";
		              	   					squares[i][j].setToolTipText(text);
		              	   				}
		              	   				else{
		              	   					text+="Power is unused";
		              	   					squares[i][j].setToolTipText(text);
		              	   			}
	                		}
	                		else if(game.getCellAt(i, j).getPiece() instanceof SideKickP1){
//	                			System.out.println(" side kick 1");
//	                			System.out.println("the i is : " + i + " and the j is : " + j );
	                			squares[i][j].setIcon(new ImageIcon("sd1.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  System.out.println("wrong");
	               					   if(!hacked && !restored && !teleported){
	               						   System.out.println("nooo");
	               						   try{
	                                  	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
	                                  	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);
	               						   }
	               						   catch(Exception e1){
				 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

	               						   }
	                                  		   }
	                                  		   else if(hacked && !restored && !teleported){
	                                  			   System.out.println("1");
	                                  			   if(h_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													h_counter = 1;
													hacked = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
//	                                  			   else if(h_counter == 1){
//	                                  				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//	                                  				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  				   h_counter--;
//	                                  			   }
//	                                  			   else{
//	                                  			
	//
//	                                  			   }
	                                  		   }
	                                  		   else if(restored && !hacked &&!teleported){
	                                  			 System.out.println("1");
	                                  			   if(r_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													r_counter = 1;
													restored = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
	                                  		   }
	                                  		 else if(!restored && !hacked && teleported){
	                                  			 System.out.println("1");
	                                  			   if(t_counter == 2){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				   t_counter--;
	                                             	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);

	                                  			   }
	                                  			   
	                                  		   }
	                                  		 else{
	                                  			restored = false;
	                                  			hacked = false;
	                                  			teleported = false;
	                                     	   	JOptionPane.showMessageDialog(null, "You chose more than one ability; you have to choose one only! ","notify",JOptionPane.PLAIN_MESSAGE);

	                                  		 }
	                         	   	   }});
	                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
	              	   			String pieceName=game.getCellAt(i, j).getPiece().getName();
	                    	   	String text=pieceName + player;
	                    	   	squares[i][j].setToolTipText(text);
	                		}
	                		else if(game.getCellAt(i, j).getPiece() instanceof SideKickP2){
//	                			System.out.println(" side kick 2");
//	                			System.out.println("the i is : " + i + " and the j is : " + j  );
	                			squares[i][j].setIcon(new ImageIcon("sd2.png"));
	                            squares[i][j].setOpaque(false);
	                            squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  System.out.println("wrong");
	               					   if(!hacked && !restored && !teleported){
	               						   System.out.println("nooo");
	               						   try{
	                                  	   	posi = game.getCellAt(ii, jj).getPiece().getPosI();
	                                  	   	posj = game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);
	               						   }
	               						   catch(Exception e1){
				 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

	               						   }
	                                  		   }
	                                  		   else if(hacked && !restored && !teleported){
	                                  			   System.out.println("1");
	                                  			   if(h_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													h_counter = 1;
													hacked = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
//	                                  			   else if(h_counter == 1){
//	                                  				   posi2= game.getCellAt(ii, jj).getPiece().getPosI();
//	                                  				   posj2= game.getCellAt(ii, jj).getPiece().getPosJ();
//	                                  				   h_counter--;
//	                                  			   }
//	                                  			   else{
//	                                  			
	//
//	                                  			   }
	                                  		   }
	                                  		   else if(restored && !hacked &&!teleported){
	                                  			 System.out.println("1");
	                                  			   if(r_counter == 1){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				 System.out.println("3");
	        			 							try {
	        			 								System.out.println(posi);
	        			 								System.out.println(posj);
	        			 								System.out.println(posi1);
	        			 								System.out.println(posj1);
														((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),null);
													r_counter = 1;
													restored = false;
													updatemap();
	        			 							} catch (Exception e1) {
														// TODO Auto-generated catch block
	        			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
													}
	                                  			   }
	                                  		   }
	                                  		 else if(!restored && !hacked && teleported){
	                                  			 System.out.println("1");
	                                  			   if(t_counter == 2){
	                                  				 System.out.println("2");
	                                  				   posi1= game.getCellAt(ii, jj).getPiece().getPosI();
	                                  				   posj1= game.getCellAt(ii, jj).getPiece().getPosJ();
	                                  				   t_counter--;
	                                             	   	JOptionPane.showMessageDialog(null, "Choose an empty cell","notify",JOptionPane.PLAIN_MESSAGE);

	                                  			   }
	                                  			   
	                                  		   }
	                                  		 else{
	                                  			restored = false;
	                                  			hacked = false;
	                                  			teleported = false;
	                                     	   	JOptionPane.showMessageDialog(null, "You chose more than one ability; you have to choose one only! ","notify",JOptionPane.PLAIN_MESSAGE);

	                                  		 }
	                         	   	   }});
	                            String player=game.getCellAt(i, j).getPiece().getOwner().getName();
	              	   		String pieceName=game.getCellAt(i, j).getPiece().getName();
	                    	   	String text=pieceName+ player;
	                    	   	squares[i][j].setToolTipText(text);
	                		}
	                
	                	else{
//	                		squares[i][j].setIcon(new ImageIcon("empty.gif"));
	                        squares[i][j].setOpaque(false);
	                        squares[i][j].setToolTipText("Empty cell");
	                        squares[i][j].addActionListener(new ActionListener() {
	                         	   public void actionPerformed(ActionEvent e) {
	                         		  if(t_counter == 1){
                           				 System.out.println("weselt empty cell");
                           				 posi2= ii;
                         				 posj2= jj;
                         				 Point p=new Point();
                         				 p.x=posi2;
                         				 p.y=posj2;
//  			 							try {
  			 								System.out.println(posi);
  			 								System.out.println(posj);
  			 								System.out.println(posi1);
  			 								System.out.println(posj1);
  			 								System.out.println(posi2);
  			 								System.out.println(posj2);
												try {
													((Tech)game.getCellAt(posi, posj).getPiece()).usePower(null,game.getCellAt(posi1, posj1).getPiece(),p);
												} catch (InvalidPowerUseException | WrongTurnException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												t_counter = 2;
												teleported = false;
												updatemap();
//  			 							} catch (Exception e1) {
//												// TODO Auto-generated catch block
//  			 								JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//											}
                           			   }
	                         	   	   }});
	                        
	                	}

	                	}
	               
	                }
	        		
	        
//	     themap.repaint();	
	        themap.revalidate();
//        themap.repaint();	
		
	}
	
	public void gameEnd(){
		themap.removeAll();
//		JLabel Background = new JLabel(new ImageIcon("giphy-downsized-2.gif"));
//		ImageIcon img = new ImageIcon("giphy-downsized-2.gif");
//		Image scale = img.getImage();
//		Background.setIcon(new ImageIcon(scale));
//		setContentPane(Background);	
//		setLayout(new BorderLayout());
//        themap.setOpaque(false);
		themap.revalidate();
		
	}
	
	public void currentPlayerInfoupdate(){
	      String CurrentPlayerInfo=" Name: ";
	       CurrentPlayerInfo +=game.getCurrentPlayer().getName()+"\n";
	       CurrentPlayerInfo+=" Pay Load : " ;
	       CurrentPlayerInfo+=game.getCurrentPlayer().getPayloadPos()+"\n";
	       CurrentPlayerInfo+=" Side Killed : " ;
	       CurrentPlayerInfo+=game.getCurrentPlayer().getSideKilled()+"\n";
	       CurrentPlayerInfo+=" Dead Characters are : \n " ;
	       for(int i=0; i<game.getCurrentPlayer().getDeadCharacters().size();i++){
	       CurrentPlayerInfo+=game.getCurrentPlayer().getDeadCharacters().get(i).getName() +"-";
	       }
	       currentPlayerInfo.setText(CurrentPlayerInfo);
	       currentPlayerInfo.revalidate();
	       	}
	public void updateleftpos(){
//		if(game.getPlayer2().getPayloadPos() != 0){
//			if(game.getPlayer2().getPayloadPos() !=7){
//				System.out.println("1");
//		lmap.removeAll();
//		Slmap = new JLabel[8][1];
//	 	Slmap[1][0] = new JLabel();
//   	 	lmap.add(Slmap[1][0]);
//   	 	Slmap[1][0].setBorder(BorderFactory.createLineBorder(Color.white));
//   	 	Slmap[1][0].setIcon(new ImageIcon("p2.png"));
//   	 	Slmap[1][0].setOpaque(false);
//
////   	 for(int x=6 ; x>6-game.getPlayer2().getPayloadPos();x--){
////   		System.out.println(x);
////   		System.out.println(game.getPlayer2().getPayloadPos());
////   		int j=0;
////   		Slmap[x][j] = new JLabel();
////   	 	lmap.add(Slmap[x][j]);
////   	 	Slmap[x][j].setBorder(BorderFactory.createLineBorder(Color.white));
////   	 	Slmap[x][j].setIcon(new ImageIcon("Green.png"));
////   	 	Slmap[x][j].setOpaque(false);
////   	 }
//	 for(int x=6-game.getPlayer2().getPayloadPos() ; x>1;x--){
//		 System.out.println("1 " + x);
////	   		System.out.println(x);
////	   		System.out.println(game.getPlayer2().getPayloadPos());
//	   		int j=0;
//	   		Slmap[x][j] = new JLabel();
//	   	 	lmap.add(Slmap[x][j]);
//	   	 	Slmap[x][j].setBorder(BorderFactory.createLineBorder(Color.white));
//	   	 	Slmap[x][j].setIcon(new ImageIcon("e.png"));
//	   	 	Slmap[x][j].setOpaque(false);
//	   	 }
//			}
//			else{
//				 for(int x=0 ; x<game.getPlayer2().getPayloadPos();x++){
//				   		
//				   		int j=0;
//				   		Slmap[x][j] = new JLabel();
//				   	 	lmap.add(Slmap[x][j]);
//				   	 	Slmap[x][j].setBorder(BorderFactory.createLineBorder(Color.white));
//				   	 	Slmap[x][j].setIcon(new ImageIcon("Green.png"));
//				   	 	Slmap[x][j].setOpaque(false);
//				   	 }
//			}
//			lmap.repaint();
//			lmap.revalidate();
//   	 }
	
	}
	public void updateleftprogressbar(){
		remove(leftposprogressb);
		leftposprogressb = new JProgressBar();
    	leftposprogressb.setOrientation(JProgressBar.VERTICAL);
    	leftposprogressb.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    	leftposprogressb.setMinimum(0);
    	leftposprogressb.setMaximum(7);
    	leftposprogressb.setStringPainted(true);
    	leftposprogressb.setForeground(Color.GREEN);
		leftposprogressb.setValue(game.getPlayer2().getPayloadPos());
		leftposprogressb.setSize(50, 500);
		leftposprogressb.setOpaque(false);
		leftposprogressb.setString("P2");
		getContentPane().add(leftposprogressb , BorderLayout.WEST);
		leftposprogressb.setPreferredSize(new Dimension(130, 848));

	}
	public void updaterightprogressbar(){
		remove(rightposprogressb);
		rightposprogressb = new JProgressBar();
		rightposprogressb.setOrientation(JProgressBar.VERTICAL);
		rightposprogressb.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		rightposprogressb.setMinimum(0);
		rightposprogressb.setMaximum(7);
		rightposprogressb.setStringPainted(true);
		rightposprogressb.setForeground(Color.GREEN);
		rightposprogressb.setValue(game.getPlayer1().getPayloadPos());
		rightposprogressb.setSize(50, 500);
		rightposprogressb.setOpaque(false);
		rightposprogressb.setString("P1");
		getContentPane().add(rightposprogressb , BorderLayout.EAST);
		rightposprogressb.setPreferredSize(new Dimension(130, 848));

	}
 
}