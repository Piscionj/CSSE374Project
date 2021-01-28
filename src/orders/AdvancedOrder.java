package orders;

import java.util.ArrayList;

import orders.strategies.OrderStrategy;

public class AdvancedOrder extends Order {

	public AdvancedOrder(OrderStrategy orderStrategy, int orderID, Address address, String type,
			ArrayList<Option> options) {
		super(orderStrategy, orderID, address, type, options);
	}
}
