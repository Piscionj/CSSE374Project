import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Observable;

import orders.Order;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

public class OrderSystem extends Observable {
	ArrayList<Order> orders;
	
	public void readOrder(String orderFilename) throws Exception{
	    ArrayList<JSONObject> json = new ArrayList<JSONObject>();
	    JSONObject obj;
	    
	    String fileName = "order-input.json";

	    String line = null;

	    FileReader fileReader = new FileReader(fileName);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    while((line = bufferedReader.readLine()) != null) {
	    	obj = (JSONObject) new JSONParser().parse(line);
	    	json.add(obj);
	    }
	    
	    bufferedReader.close();         
	    

		//adds to order ArrayList and notifies controllers
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
