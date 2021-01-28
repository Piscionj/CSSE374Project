package orders.strategies;

import orders.Address;
import orders.Controller;
import orders.Order;

public class AddressOrderStrategy implements OrderStrategy {


	
	public int makeOrder(Controller cont, Order order) {
		Address contAddress = cont.getAddress();
		Address orderAddress = order.getAddress();
		if(contAddress.getAddress().equals(orderAddress.getAddress()) &&
			contAddress.getZip() == orderAddress.getZip()) {
			return 1;
		}
		return 0;
	}

}
