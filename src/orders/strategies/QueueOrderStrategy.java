package orders.strategies;

import orders.Controller;
import orders.Order;

public class QueueOrderStrategy implements OrderStrategy {

    @Override
    public int makeOrder(Controller cont, Order order) {
    	return cont.getOrdersQueued();
    }
}
