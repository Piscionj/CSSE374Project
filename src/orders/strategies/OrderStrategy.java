package orders.strategies;

import orders.Address;
import orders.Controller;
import orders.Order;

/**
 * Strategy for selecting what machine an order is sent to
 */
public interface OrderStrategy {

	public int makeOrder(Controller cont, Order order);
}
