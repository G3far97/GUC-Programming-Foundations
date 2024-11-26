package guc.supermarket.cart;

import java.util.ArrayList;

import guc.supermarket.products.Beverage;
import guc.supermarket.products.GroceryProduct;

public class Cart {

	private ArrayList<GroceryProduct> products;

	public Cart() {
		products = new ArrayList<>();
	}

	public void addProduct(GroceryProduct p) {

		products.add(p);

	}

	public double getTotal() {

		double total = 0;
		for (GroceryProduct product : products) {

			total += product.getActualPrice();

		}
		return total;

	}

	public double getTotal(double extra) {

		double total = 0;
		for (GroceryProduct product : products) {

			if (product instanceof Beverage)
				total += ((Beverage) product).getActualPrice(extra);
			else
				total += product.getActualPrice();

		}
		return total;

	}

	public String toString() {

		String s = "";
		if (products.size() == 0)
			return s;
		int i = 0;
		s = s + products.get(i).toString();
		for (i = 1; i < products.size(); i++)
			s = s + "\n--------------------------------\n"
					+ products.get(i).toString();
		return s;

	}

	public ArrayList<GroceryProduct> getProducts() {
		return products;
	}
}
