import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OrderSystem {
	Order[] orders;
	
	public OrderSystem() throws Exception{
		Object obj = new JSONParser().parse(new FileReader("order-input.json"));
		JSONObject jo = (JSONObject) obj;
		
		
	}
}
