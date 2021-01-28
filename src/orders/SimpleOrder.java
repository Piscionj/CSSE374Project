package orders;

import java.util.ArrayList;

import orders.strategies.OrderStrategy;

public class SimpleOrder extends Order {

	public SimpleOrder(int orderID, Address address, String type,
			ArrayList<Option> options) {
		super(orderID, address, type, options);
	}
}
