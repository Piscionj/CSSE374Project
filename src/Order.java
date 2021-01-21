import java.util.ArrayList;

public class Order {
	
	String id;
	String type;
	Address address;
	ArrayList<Option> options;
	
	public Order(String id, String type, Address address, ArrayList<Option> options) {
		this.id = id;
		this.type = type;
		this.address = address;
		this.options = options;
	}

}
