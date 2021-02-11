package orders;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import org.json.simple.parser.ParseException;

import orders.strategies.OrderStrategy;

public class OrderSystem extends Observable {
	
	private ArrayList<Controller> controllers = new ArrayList<Controller>();
	private Order order;
	private Controller selectedCont;
	private ControllerResponse cr;
	private DrinkFactory df;
	private AbstractRecipe ar;
	
	public int readOrder(String orderFilename, OrderStrategy strategy) throws Exception {
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
	    
	    if (i > -1) {
	    	System.out.println("controller with ID " + selectedCont.getID() + " selected to handle order");
			return selectedCont.getID();
		}
	    else System.out.println("no controllers connected");
		return -1;
	}
	
	public void sendCommand(String commandFilename, int machineID, String drinkName, String requestType, ArrayList<String> commands, ArrayList<String> ingredients) throws IOException {
		//creates json file representing command to make coffee and sends it to controller
		df = new MainRecipeFactory();
		BeverageDecorator drink = df.makeDrink(drinkName, requestType, commands, ingredients);
		JsonParser.createCommand(commandFilename, selectedCont.getID(), machineID, order.getOrderID(), drink, drinkName, requestType, order.getOptions());
	    System.out.println("sending " + commandFilename + " to controller with ID " + selectedCont.getID());
	}
	
	public void readControllerResponse(String responseFilename) throws FileNotFoundException, IOException, ParseException {
		//reads json file response from controller
		cr = JsonParser.parseControllerResponse(responseFilename);
		if(cr.getStatus() == 0) System.out.println("order " + cr.getOrderID() + " processed successfully");
		else System.out.println("machine error for order " + cr.getOrderID() + "\nerror code: " + cr.getErrCode() + "\nerror message: " + cr.getErrDesc());
	}
	
	public void sendResult(String responseFilename, int machineID) throws IOException {
		//creates json file representing status of coffee being made and sends to app
		JsonParser.createAppResponse(responseFilename, machineID, cr);
	    System.out.println("sending " + responseFilename + " to user with order ID " + cr.getOrderID());
	}

	public void addController(Controller cont) {
		controllers.add(cont);
		addObserver(cont);
	}
	
}
