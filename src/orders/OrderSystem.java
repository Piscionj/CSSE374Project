package orders;
import java.util.ArrayList;
import java.util.Observable;

import orders.strategies.OrderStrategy;

public class OrderSystem extends Observable {
	
	ArrayList<Controller> controllers = new ArrayList<Controller>();
	
	public void readOrder(String orderFilename, OrderStrategy strategy) throws Exception{
		
		Order order = JsonParser.parseOrder(orderFilename);

		setChanged();
	    notifyObservers(order);
	    
	    int i = -1;
	    int x;
	    Controller bestController = null;
	    
	    for (Controller cont : controllers) {
	    	x = strategy.makeOrder(cont, order);
	    	if (i < x) {
	    		i = x;
	    		bestController = cont;
	    	}
	    }
	    
	    if (i > -1) System.out.println("sending order to controller with ID " + bestController.getID());	    
	    else System.out.println("no controllers connected");
		
	    //creates order and notifies controllers
	}
	
	public void sendCommand() {
		//creates json file representing command to make coffee and sends it to controller
	}
	
	public void readControllerResponse() {
		//reads json file response from controller
	}
	
	public void sendResult() {
		//creates json file representing status of coffee being made and sends to app
	}

	public void addController(Controller cont) {
		controllers.add(cont);
		addObserver(cont);
	}
}
