package orders;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import orders.strategies.OrderStrategy;

public class OrderSystem extends Observable {
	
	ArrayList<Controller> controllers = new ArrayList<Controller>();
	Order order;
	Controller selectedCont;
	
	public void readOrder(String orderFilename, OrderStrategy strategy) throws Exception{
		//creates order and notifies controllers of new order
		
		order = JsonParser.parseOrder(orderFilename);

		setChanged();
	    notifyObservers(order);
	    
	    int i = -1;
	    int x;
	    
	    for (Controller cont : controllers) {
	    	x = strategy.makeOrder(cont, order);
	    	if (i < x) {
	    		i = x;
	    		selectedCont = cont;
	    	}
	    }
	    
	    if (i > -1) System.out.println("controller with ID " + selectedCont.getID() + " selected to handle order");	    
	    else System.out.println("no controllers connected");
	    
	}
	
	public void sendCommand(int machineID, String drinkName, String requestType) throws IOException {
		//creates json file representing command to make coffee and sends it to controller
		
		JsonParser.createCommand(selectedCont.getID(), machineID, order.getOrderID(), drinkName, requestType, order.getOptions());
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
