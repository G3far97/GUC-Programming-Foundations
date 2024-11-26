package guc.supermarket.tests;

import guc.supermarket.cart.Cart;
import guc.supermarket.products.Beverage;
import guc.supermarket.products.DairyProduct;
import guc.supermarket.products.Fat;
import guc.supermarket.products.GroceryProduct;
import guc.supermarket.products.SugarLevel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.*;

public class Lab2PublicTest {

	String gPath = "guc.supermarket.products.GroceryProduct";
	String bPath = "guc.supermarket.products.Beverage";
	String dPath = "guc.supermarket.products.DairyProduct";
	String cPath = "guc.supermarket.cart.Cart";

	@Test(timeout = 1000)
	public void testClassIsAbstractGroceryProduct()
			throws NoSuchMethodException {
		assertTrue(
				"No object should be instantiated from class groceryProducts",
				Modifier.isAbstract(GroceryProduct.class.getModifiers()));

	}

	@Test
	public void testConstructorCart() throws Exception {

		Class.forName(cPath).getConstructor();

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testConstructorCartInitialization() throws Exception {

		Cart c = new Cart();
		Field f = Cart.class.getDeclaredField("products");
		f.setAccessible(true);
		ArrayList<GroceryProduct> products = (ArrayList<GroceryProduct>) f
				.get(c);

		assertNotNull(
				"The Cart constructor should initiallize the instance variables correctly.",
				products);

	}

	@Test
	public void testInstanceVariableCartProducts() throws Exception {

		testInstanceVariablesArePresent(Cart.class, "products", true);
		testInstanceVariableIsPrivate(Cart.class, "products");

	}

	@Test
	public void testInstanceVariableCartProductsGetter() {

		testGetterMethodExistsInClass(Cart.class, "getProducts",
				ArrayList.class);

	}

	@Test
	public void testInstanceVariableCartProductsSetter() {

		testSetterMethodExistsInClass(Cart.class, "setProducts",
				ArrayList.class, false);

	}

	@Test (timeout = 1000)
	public void testMethodBeverageGetActualPrice2Logic() {

		Beverage b = new Beverage("Schweppes Pomegranate", 10, 5,
				SugarLevel.ADDED_SUGAR);
		assertEquals(
				"The overloaded method getTotal(double extra) should calculate the price after adding the voucher's extra discount to the original dicount.",
				9, b.getActualPrice(5.0), 0.01);

		b = new Beverage("Schweppes Pomegranate", 20, 10,
				SugarLevel.ADDED_SUGAR);
		assertEquals(
				"The overloaded method getTotal(double extra) should calculate the price after adding the voucher's extra discount to the original dicount.",
				18, b.getActualPrice(0.0), 0.01);

	}

	

	@Test
	public void testMethodCartGetTotal2Logic() throws Exception {

		Cart c = new Cart();

		Beverage beverage = new Beverage("Schweppes Pomegranate", 10, 10,
				SugarLevel.ADDED_SUGAR);
		DairyProduct dairyProduct = new DairyProduct("Juhayna", 20, 5,
				Fat.FULLCREAM);
		ArrayList<GroceryProduct> products = new ArrayList<>(Arrays.asList(
				dairyProduct, beverage));

		Field f = Cart.class.getDeclaredField("products");
		f.setAccessible(true);
		f.set(c, products);

		assertEquals(
				"The getTotal(double extra) method should return the total of summing up the actual price of the products in the cart taking into consideration the voucher discount extra.",
				27, c.getTotal(10.0), 0.001);

	}

	@Test(timeout = 1000)
	public void testMethodCartToStringLogic() throws Exception {

		Cart c = new Cart();

		Beverage beverage = new Beverage("Schweppes Pomegranate", 10, 10,
				SugarLevel.ADDED_SUGAR);
		DairyProduct dairyProduct = new DairyProduct("Juhayna", 20, 5,
				Fat.FULLCREAM);
		ArrayList<GroceryProduct> products = new ArrayList<>(Arrays.asList(
				dairyProduct, beverage));

		Field f = Cart.class.getDeclaredField("products");
		f.setAccessible(true);
		f.set(c, products);

		String s = c.toString();

		String t = s.toLowerCase();
		String[] sa = t.split("\n");

		assertEquals(
				"The \"Cart\" toString() method should return all infromation about the first products seperated by 1 line.\nHint: you can use \"\\n\" in order to insert a line break in a String.\n"
						+ s, 9, sa.length);

		assertTrue(
				"The \"Cart\" toString() method should return all infromation about the first product; the \"Name\" information is missing or incorrect.\n"
						+ s,
				sa[0].contains("name") && sa[0].contains("juhayna"));

		assertTrue(
				"The \"Cart\" toString() method should return all infromation about the first product; the \"Price\" information is missing or incorrect.\n"
						+ s, sa[1].contains("price") && sa[1].contains("" + 20));

		assertTrue(
				"The \"Cart\" toString() method should return all infromation about the first product; the \"Discount\" information is missing or incorrect.\n"
						+ s,
				sa[2].contains("discount") && sa[2].contains("" + 5));

		assertTrue(
				"The \"Cart\" toString() method should return all infromation about the first product; the \"Fat Level\" information is missing or incorrect.\n"
						+ s, sa[3].contains("fat") && sa[3].contains("level")
						&& sa[3].contains("fullcream"));

		assertTrue(
				"The \"Beverage\" toString() method should return all infromation about the product; the \"Name\" information is missing or incorrect.\n"
						+ s,
				sa[5].contains("name")
						&& sa[5].contains("schweppes pomegranate"));

		assertTrue(
				"The \"Beverage\" toString() method should return all infromation about the product; the \"Price\" information is missing or incorrect.\n"
						+ s, sa[6].contains("price") && sa[6].contains("" + 10));

		assertTrue(
				"The \"Beverage\" toString() method should return all infromation about the product; the \"Discount\" information is missing or incorrect.\n"
						+ s,
				sa[7].contains("discount") && sa[7].contains("" + 10));

		assertTrue(
				"The \"Beverage\" toString() method should return all infromation about the product; the \"Sugar Level\" information is missing or incorrect.\n"
						+ s, sa[8].contains("sugar") && sa[8].contains("level")
						&& sa[8].contains("added_sugar"));

	}

	@Test
	public void testMethodDairyProductRefrigerate() throws Exception {

		DairyProduct.class.getDeclaredMethod("refrigerate");

	}

	
	
	// ===============================================Helpers

		@SuppressWarnings("rawtypes")
		private void testInstanceVariablesArePresent(Class aClass, String varName,
				boolean implementedVar) throws SecurityException {

			boolean thrown = false;
			try {
				aClass.getDeclaredField(varName);
			} catch (NoSuchFieldException e) {
				thrown = true;
			}
			if (implementedVar) {
				assertFalse("There should be " + varName
						+ " instance variable in class " + aClass.getName(), thrown);
			} else {
				assertTrue("There should not be " + varName
						+ " instance variable in class " + aClass.getName()
						+ ", it should be inherited from the super class", thrown);
			}
		}

		@SuppressWarnings("rawtypes")
		private void testInstanceVariableIsPrivate(Class aClass, String varName)
				throws NoSuchFieldException, SecurityException {
			Field f = aClass.getDeclaredField(varName);
			assertEquals(
					varName + " instance variable in class " + aClass.getName()
							+ " should not be accessed outside that class", 2,
					f.getModifiers());
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void testGetterMethodExistsInClass(Class aClass, String methodName,
				Class returnedType) {
			Method m = null;
			boolean found = true;
			try {
				m = aClass.getDeclaredMethod(methodName);
			} catch (NoSuchMethodException e) {
				found = false;
			}
			String varName = methodName.substring(3).toLowerCase();
			assertTrue(
					"The " + varName + " instance variable in class "
							+ aClass.getName() + " is a READ variable.", found);
			assertTrue("incorrect return type for " + methodName + " method in "
					+ aClass.getName() + " class.", m.getReturnType()
					.isAssignableFrom(returnedType));
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		private void testSetterMethodExistsInClass(Class aClass, String methodName,
				Class inputType, boolean writeVariable) {

			Method[] methods = aClass.getDeclaredMethods();
			String varName = methodName.substring(3).toLowerCase();
			if (writeVariable) {
				assertTrue("The " + varName + " instance variable in class "
						+ aClass.getName() + " is a WRITE variable.",
						containsMethodName(methods, methodName));
			} else {
				assertFalse("The " + varName + " instance variable in class "
						+ aClass.getName() + " is a READ ONLY variable.",
						containsMethodName(methods, methodName));
				return;
			}
			Method m = null;
			boolean found = true;
			try {
				m = aClass.getDeclaredMethod(methodName, inputType);
			} catch (NoSuchMethodException e) {
				found = false;
			}

			assertTrue(aClass.getName() + " class should have " + methodName
					+ " method that takes one " + inputType.getSimpleName()
					+ " parameter", found);

			assertTrue("incorrect return type for " + methodName + " method in "
					+ aClass.getName() + ".", m.getReturnType().equals(Void.TYPE));

		}

		private static boolean containsMethodName(Method[] methods, String name) {
			for (Method method : methods) {
				if (method.getName().equals(name))
					return true;
			}
			return false;
		}
}
