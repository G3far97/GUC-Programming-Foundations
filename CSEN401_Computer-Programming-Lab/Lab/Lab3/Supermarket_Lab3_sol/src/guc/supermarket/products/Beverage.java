package guc.supermarket.products;

public class Beverage extends GroceryProduct implements Drinkable {
	private SugarLevel sugarLevel;

	public Beverage(String name, double price, double discount,
			SugarLevel sugarLevel) {
		super(name, price, discount);
		this.sugarLevel = sugarLevel;
	}

	public double getActualPrice(double extra) {
		return getPrice() - (getPrice() * (getDiscount() + extra) / 100);
	}

	public String toString() {
		return super.toString() + "\nSugar Level: " + sugarLevel;
	}

	@Override
	public boolean refrigerate() {
		return false;
	}

	public SugarLevel getSugarLevel() {
		return sugarLevel;
	}

	public void setSugarLevel(SugarLevel sugarLevel) {
		this.sugarLevel = sugarLevel;
	}

	@Override
	public boolean isHealthy() {
		if(sugarLevel == SugarLevel.ADDED_SUGAR)
			return false;
		return true;
	}

}
