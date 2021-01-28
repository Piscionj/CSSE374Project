package orders.strategies;

import orders.Address;

/**
 * Strategy for selecting what machine an order is sent to
 */
public interface OrderStrategy {

	public void makeOrder(String machineType, Address address);
}
