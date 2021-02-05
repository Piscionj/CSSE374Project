package orders;
public class Option implements BeverageComponent {
	String name;
	long quantity;
	


	public Option(String name, long qty) {
		this.name = name;
		this.quantity = qty;
	}


	public String getName() {
		return name;
	}


	public long getQuantity() {
		return quantity;
	}


	public void addOptions() {
		
	}
	
	
}
