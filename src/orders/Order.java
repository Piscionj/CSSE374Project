package orders;

import java.util.ArrayList;


public class Order {


	private long orderID;
	private Address address;
	private String type;
	private ArrayList<Option> options;
	
	public Order(long orderID, Address address, String type, ArrayList<Option> options) {
		this.orderID = orderID;
		this.address = address;
		this.type = type;
		this.options = options;
	}

	public long getOrderID() {
		return orderID;
	}

	public Address getAddress() {
		return address;
	}

	public String getType() {
		return type;
	}

	public ArrayList<Option> getOptions() {
		return options;
	}

}
