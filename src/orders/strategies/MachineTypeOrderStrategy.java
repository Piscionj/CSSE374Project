package orders.strategies;


import orders.Controller;
import orders.Order;

public class MachineTypeOrderStrategy implements OrderStrategy {



	
	public int makeOrder(Controller cont, Order order) {
		if ((order.getOptions().isEmpty() && !cont.getIsAdvancedType()) ||
			(!order.getOptions().isEmpty() && cont.getIsAdvancedType())) {
			return 1;
		}
		return 0;
	}

}
