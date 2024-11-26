package guc.supermarket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import guc.supermarket.cart.Cart;
import guc.supermarket.exceptions.CannotBuyException;
import guc.supermarket.people.Customer;
import guc.supermarket.products.Beverage;
import guc.supermarket.products.DairyProduct;
import guc.supermarket.products.Fat;
import guc.supermarket.products.GroceryProduct;
import guc.supermarket.products.SugarLevel;

public class Supermarket {

	private ArrayList<GroceryProduct> products;
	private ArrayList<Customer> customers;

	// Main Method
	public static void main(String[] args) throws IOException {

		Supermarket s = new Supermarket();
		s.simulate();

	}

	// Supermarket Class
	public Supermarket() {

		products = new ArrayList<GroceryProduct>();
		customers = new ArrayList<Customer>();

	}

	@SuppressWarnings("resource")
	private String readInputLine() {

		System.out.println("Input:");
		Scanner sc = new Scanner(System.in);
		String s = "";
		s = sc.nextLine();
		return s;

	}

	/**
	 * Reads the lines in the CSV and calls the executeTransaction method to
	 * simulate the buy transaction.
	 */
	public void simulate() throws IOException {

		BufferedReader br = null;
		String currentLine = "";

		FileReader fileReader = null;
		String fileName = "Transactions.csv";

		while (fileReader == null) {

			try {

				fileReader = new FileReader(fileName);

			} catch (FileNotFoundException e) {

				System.out.println("the File " + fileName
						+ " is missing. Please enter the updated file name.");
				fileName = readInputLine();

			}

		}

		br = new BufferedReader(fileReader);

		while ((currentLine = br.readLine()) != null) {

			try {

				executeTransaction(currentLine);

			} catch (ArrayIndexOutOfBoundsException e) {

				System.out.println("Invalid data in line: \"" + currentLine
						+ "\"");

			} catch (CannotBuyException e) {

				System.out
						.println("Customer trying to buy a mismatched product in line: \n"
								+ currentLine + "\n" + e.getMessage());

			}

		}
		br.close();

	}

	/**
	 * Extracts the 6 values that represent the transaction and executes the
	 * transaction that is provided in the given CSV line.
	 *
	 * @param transactionRow
	 *            a CSV line containing the transaction values.
	 * @throws CannotBuyException
	 */
	private void executeTransaction(String transactionRow)
			throws CannotBuyException {

		String[] result = transactionRow.split(",");

		// Customer data
		String customerName = result[0];
		Customer customer = new Customer(customerName, new Cart());
		addCustomer(customer);

		// Product data
		String type = result[1];
		String productName = result[2];
		double price = Double.parseDouble(result[3]);
		double discount = Double.parseDouble(result[4]);
		GroceryProduct g = null;

		switch (type) {
		case "DairyProduct":
			g = new DairyProduct(productName, price, discount,
					getFatFromString(result[5]));
			break;
		case "Beverage":
			g = new Beverage(productName, price, discount,
					getSugarLevelFromString(result[5]));
			break;
		}

		addProduct(g);

		customer.buy(g);

	}

	public void addCustomer(Customer c) {

		customers.add(c);

	}

	public void addProduct(GroceryProduct c) {

		products.add(c);

	}

	public ArrayList<GroceryProduct> getProducts() {

		return products;

	}

	public ArrayList<Customer> getCustomers() {

		return customers;

	}

	// Helper Methods
	private Fat getFatFromString(String s) {
		switch (s) {
		case "Fat.FULLCREAM":
			return Fat.FULLCREAM;
		case "Fat.HALFCREAM":
			return Fat.HALFCREAM;
		case "Fat.SKIMMED":
			return Fat.SKIMMED;
		default:
			return null;
		}
	}

	private SugarLevel getSugarLevelFromString(String s) {
		switch (s) {
		case "SugarLevel.ADDED_SUGAR":
			return SugarLevel.ADDED_SUGAR;
		case "SugarLevel.LIGHT":
			return SugarLevel.LIGHT;
		case "SugarLevel.NO_ADDED_SUGAR":
			return SugarLevel.NO_ADDED_SUGAR;
		case "SugarLevel.ZERO":
			return SugarLevel.ZERO;
		default:
			return null;
		}
	}
}
