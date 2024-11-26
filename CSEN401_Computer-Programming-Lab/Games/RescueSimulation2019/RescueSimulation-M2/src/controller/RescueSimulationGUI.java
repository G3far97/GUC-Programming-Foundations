package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

import model.events.SOSListener;
import simulation.Rescuable;
import simulation.Simulator;
import view.RescueSimulationView;


public class RescueSimulationGUI implements SOSListener, ActionListener{
	private Simulator sim;
	private RescueSimulationView rescueSimulationView;
	
	public RescueSimulationGUI() {
		Simulator sim = this.sim;
		sim.setEmergencyService(this);
		RescueSimulationView rescueSimulationView = this.rescueSimulationView;
		
		
		
		
		
		rescueSimulationView.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Red"))
			getContentPane().setBackground(Color.RED);
			else if (e.getActionCommand().equals("Black"))
			getContentPane().setBackground(Color.BLACK);
	}

	@Override
	public void receiveSOSCall(Rescuable r) {
		// TODO Auto-generated method stub
		
	}
	public static void main (String[] args) {
		RescueSimulationGUI test = new RescueSimulationGUI();
	}
}
