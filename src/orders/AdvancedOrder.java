package orders;

import java.util.ArrayList;

import orders.strategies.OrderStrategy;

public class AdvancedOrder extends Order {

	public AdvancedOrder(int orderID, Address address, String type, ArrayList<Option> options) {
		super(orderID, address, type, options);
	}
}
