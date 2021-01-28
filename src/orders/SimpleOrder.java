package orders;

import java.util.ArrayList;

import orders.strategies.OrderStrategy;

public class SimpleOrder extends Order {

	public SimpleOrder(OrderStrategy orderStrategy, int orderID, Address address, String type,
			ArrayList<Option> options) {
		super(orderStrategy, orderID, address, type, options);
	}
}
