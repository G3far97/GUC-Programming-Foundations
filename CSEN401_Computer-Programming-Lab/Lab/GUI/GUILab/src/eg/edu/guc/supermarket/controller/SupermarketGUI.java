package eg.edu.guc.supermarket.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import eg.edu.guc.supermarket.model.cart.Cart;
import eg.edu.guc.supermarket.model.product.GroceryProduct;
import eg.edu.guc.supermarket.model.supermarket.Supermarket;
import eg.edu.guc.supermarket.model.supermarket.SupermarketListener;
import eg.edu.guc.supermarket.view.SupermarketView;

public class SupermarketGUI implements SupermarketListener, ActionListener {
	private Supermarket supermarket;
	private SupermarketView supermarketView;
	private ArrayList<JButton> btnsProduct;

	public SupermarketGUI() {
		// create a new instance of Supermarket and set the controller as its listener
		Supermarket supermarket = this.supermarket;
		supermarket.setListener(this);
		
		// create a new instance of the SupermarketView
		SupermarketView supermarketView = this.supermarketView;

		// initialize the ArrayList of product buttons

		for (final GroceryProduct product : supermarket.getProducts()) {
			// create a JButton for each product in the supermarket
			JButton b = new JButton(product.getName());
			// set its text to the product's info

			// add the controller as its ActionListener
			b.addActionListener(this);
			// add it to the products buy buttons panel
			
			// and also add it to the ArrayList for later use

		}

		// present the frame
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// get the JButton that was clicked

		// get its index within the ArrayList of buttons

		// get the corresponding product from the supermarket

		// invoke the buy method on it

	}

	@Override
	public void onCartUpdated(Cart cart) {
		// create a list of products info
	
		// set the list along with the total to the supermarketView's updateCart method

	}

	public static void main(String[] args) {
		// initialize the supermarket GUI
		SupermarketGUI x = new SupermarketGUI();
	}
}
