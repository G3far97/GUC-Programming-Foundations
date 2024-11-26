package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.events.WorldListener;

public class RescueSimulationView extends JFrame{
	private JPanel infoPanel;
	private JPanel rescuePanel;
	private JPanel unitsPanel;
	
	public RescueSimulationView() {
		this.setTitle("Rescue Simulation");
//		WindowDestroyer wd = new WindowDestroyer();
//		this.addWindowListener(wd);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);
		
		this.getContentPane().setLayout(new GridLayout(2,3));
		JTextArea currentCycle = new JTextArea();
		currentCycle.setEditable(false);
		this.getContentPane().add(currentCycle);
		JTextArea casulties = new JTextArea();
		casulties.setEditable(false);
		this.getContentPane().add(casulties);
		JButton endCycle = new JButton("End Cycle");
		this.getContentPane().add(endCycle);
		infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(2, 1));
		this.getContentPane().add(infoPanel);
		rescuePanel = new JPanel();
		//rescuePanel.setLayout(new GridLayout(rows, cols));
		this.getContentPane().add(rescuePanel);
		unitsPanel = new JPanel();
		this.getContentPane().add(unitsPanel);
		
		WindowDestroyer wd = new WindowDestroyer();
		this.addWindowListener(wd);
		
	}
}
