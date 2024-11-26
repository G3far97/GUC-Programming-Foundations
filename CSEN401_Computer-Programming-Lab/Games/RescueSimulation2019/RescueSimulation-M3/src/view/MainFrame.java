package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	private ToolBar toolBar;
	private RescuePanel rescuePanel;
	private InfoPanel infoPanel;
	private UnitsPanel unitsPanel;
	
	public MainFrame() {
		super("Rescue Simulation");
		setLayout(new BorderLayout());

		toolBar = new ToolBar();
		rescuePanel = new RescuePanel();
		infoPanel = new InfoPanel();
		unitsPanel = new UnitsPanel();
		
		add(toolBar, BorderLayout.NORTH);
		add(rescuePanel, BorderLayout.CENTER);
		add(unitsPanel, BorderLayout.WEST);
		add(infoPanel, BorderLayout.EAST);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setMinimumSize(screenSize);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
