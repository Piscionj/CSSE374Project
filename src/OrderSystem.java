import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Observable;

import orders.Address;
import orders.Option;
import orders.Order;
import orders.strategies.OrderStrategy;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

public class OrderSystem extends Observable {
	
	public void readOrder(String orderFilename, OrderStrategy strategy) throws Exception{

		
		Order order = JsonParser.parseOrder(orderFilename, strategy);

	    
	    notifyObservers(order);
	    
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
		addObserver(cont);
	}
}
