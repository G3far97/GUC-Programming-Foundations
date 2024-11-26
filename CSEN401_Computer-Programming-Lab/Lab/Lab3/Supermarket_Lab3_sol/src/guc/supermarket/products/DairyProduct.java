package guc.supermarket.products;

public class DairyProduct extends GroceryProduct{
	private Fat fat;
	
	public DairyProduct(String name, double price, double discount, Fat fat){
		super(name, price, discount);
		this.fat=fat;
	}

	public String toString(){
		return super.toString()+"\nFat Level: "+ fat;
	}
	
	@Override
	public boolean refrigerate() {
		return true;
	}
	
	public Fat getFat() {
		return fat;
	}

	public void setFat(Fat fat) {
		this.fat = fat;
	}
}
