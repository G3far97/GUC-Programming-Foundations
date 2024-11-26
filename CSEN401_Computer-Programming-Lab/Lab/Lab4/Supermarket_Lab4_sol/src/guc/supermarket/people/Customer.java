package guc.supermarket.people;

import guc.supermarket.cart.Cart;
import guc.supermarket.exceptions.IncorrectFatTypeException;
import guc.supermarket.exceptions.IncorrectSugarLevelException;
import guc.supermarket.products.Beverage;
import guc.supermarket.products.DairyProduct;
import guc.supermarket.products.Fat;
import guc.supermarket.products.GroceryProduct;
import guc.supermarket.products.SugarLevel;

public class Customer {

	private String name;
	private Cart myCart;
	private SugarLevel preferableSugarLevel;
	private Fat preferableFatLevel;

	public Customer(String name, Cart cart) {

		this.name = name;
		this.myCart = cart;

	}

	public Customer(String name, Cart cart, Fat fat, SugarLevel sugar) {

		this.name = name;
		this.myCart = cart;
		this.preferableSugarLevel = sugar;
		this.preferableFatLevel = fat;

	}

	public void buy(GroceryProduct p) throws IncorrectFatTypeException,
			IncorrectSugarLevelException {

		if (p instanceof DairyProduct)
			if (preferableFatLevel != ((DairyProduct) p).getFat())
				throw new IncorrectFatTypeException(this.name + " Cannot buy "
						+ p.getName()
						+ " as it does not match the preferable fat type: "
						+ preferableFatLevel);

		if (p instanceof Beverage)
			if (preferableSugarLevel != ((Beverage) p).getSugarLevel())
				throw new IncorrectSugarLevelException(this.name
						+ " Cannot buy " + p.getName()
						+ " as it does not match the preferable sugar level: "
						+ preferableSugarLevel);

		System.out.println(this.name + " bought " + p.getName() + ".");
		myCart.addProduct(p);

	}
}
