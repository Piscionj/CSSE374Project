import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

public class OrderSystem implements SystemSubject{
	ArrayList<Order> orders;
	
	public OrderSystem() throws Exception{
	    ArrayList<JSONObject> json = new ArrayList<JSONObject>();
	    JSONObject obj;
	    
	    String fileName = "order-input.json";

	    String line = null;
	    int x = 0;

	    FileReader fileReader = new FileReader(fileName);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    while((line = bufferedReader.readLine()) != null) {
	    	System.out.println(x++);
//	    	json.add(obj);
	    }
	    
	    bufferedReader.close();         
	    

		
	}

	public void registerObserver() {
		
	}

	public void removeObserver() {
		
	}

	public void notifyObservers() {
		
	}
}
