package eg.edu.guc.supermarket.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class SupermarketView extends JFrame {
	private JPanel pnlProducts;
	private JTextArea txtCart;

	public SupermarketView() {
		// set the windows title
		this.setTitle("Supermarket");
		// change the default close operation of the JFrame to exit the application instead of hiding the window
		
		// set the location and size of the JFrame

		
		// create a panel to hold the products buy buttons

		// set it to use the GridLayout with 3 columns in width

		// add it in the center of the JFrame

		// create a text area to hold the text of the cart

		// force it to have a width of 200 and the same height of the JFrame

		// prevent its contents from being edited

		// use a monospaced font to make it look cooler 

		// add it to the right of the JFrame

		
		// initialize the cart's text by setting its total to zero

	}

	// added product JButtons are simply added to the products buy buttons panel
	public void addProduct(JButton product) {

	}

	// updates the cart's text with the list of products info and the new total
	public void updateCart(ArrayList<String> products, double total) {
		
	}
}
