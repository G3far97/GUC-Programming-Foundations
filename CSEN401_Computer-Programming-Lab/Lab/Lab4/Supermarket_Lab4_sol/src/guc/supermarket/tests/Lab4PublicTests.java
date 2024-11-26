package guc.supermarket.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import guc.supermarket.cart.Cart;
import guc.supermarket.exceptions.*;
import guc.supermarket.people.Customer;
import guc.supermarket.products.*;

public class Lab4PublicTests {
	
	Customer customer;
	DairyProduct dairyProduct_fullcream;
	DairyProduct dairyProduct_halfcream;
	DairyProduct dairyProduct_skimmed;
	Beverage beverage_zero;
	Beverage beverage_addedSugar;
	Beverage beverage_noAddedSugar;
	Beverage beverage_light;
	
	@Before
	public void setup(){
		customer= new Customer("Lama", new Cart(), Fat.SKIMMED, SugarLevel.ZERO);
		dairyProduct_fullcream= new DairyProduct("Lamar", 10, 0, Fat.FULLCREAM);
		dairyProduct_halfcream= new DairyProduct("Lamar", 10, 0, Fat.HALFCREAM);
		dairyProduct_skimmed= new DairyProduct("Lamar", 10, 0, Fat.SKIMMED);
		beverage_zero= new Beverage("Pepsi", 5, 0, SugarLevel.ZERO);
		beverage_light= new Beverage("Pepsi", 5, 0, SugarLevel.LIGHT);
		beverage_addedSugar= new Beverage("Pepsi", 5, 0, SugarLevel.ADDED_SUGAR);
		beverage_noAddedSugar= new Beverage("Pepsi", 5, 0, SugarLevel.NO_ADDED_SUGAR);
	}
	
	@Test(timeout = 1000)
	public void testIncorrectFatTypeExceptionInheritance(){
		assertEquals("Class IncorrectFatTypeException should be a subclass of CannotBuyException", 
				IncorrectFatTypeException.class.getSuperclass(), CannotBuyException.class);
	}
	
	@Test(timeout = 1000, expected = IncorrectFatTypeException.class)
	public void testFullCream() throws CannotBuyException{
		customer.buy(dairyProduct_fullcream);
	}
	
	@Test(timeout = 1000)
	public void testSkimmed() throws CannotBuyException{
		customer.buy(dairyProduct_skimmed);
	}
	
	@Test(timeout = 1000, expected = IncorrectSugarLevelException.class)
	public void testLight() throws CannotBuyException{
		customer.buy(beverage_light);
	}
	
	@Test(timeout = 1000)
	public void testZero() throws CannotBuyException{
		customer.buy(beverage_zero);
	}
}