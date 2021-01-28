package orders.strategies;

/**
 * Strategy for selecting what machine an order is sent to
 */
public interface OrderStrategy {

	public void makeOrder();
}
