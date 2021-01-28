package orders;

import java.util.ArrayList;

import orders.strategies.OrderStrategy;

public class Order {


	OrderStrategy orderStrategy;
	long orderID;
	Address address;
	String type;
	ArrayList<Option> options;
	
	public Order(OrderStrategy orderStrategy, long orderID, Address address, String type, ArrayList<Option> options) {
		this.orderStrategy = orderStrategy;
		this.orderID = orderID;
		this.address = address;
		this.type = type;
		this.options = options;
	}

}
