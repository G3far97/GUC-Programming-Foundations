package guc.supermarket.people;

import guc.supermarket.cart.Cart;
import guc.supermarket.products.GroceryProduct;

public class Customer {

	private String name;
	private Cart myCart;

	public Customer(String name, Cart cart) {

		this.name = name;
		this.myCart = cart;

	}

	public void buy(GroceryProduct p) {

		System.out.println(this.name + " bought " + p.getName() + ".");
		myCart.addProduct(p);

	}
}
