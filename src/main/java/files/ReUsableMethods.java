package files;

import io.restassured.path.json.JsonPath;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReUsableMethods {

	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js1 =new JsonPath(response);
		return js1;
	}

	public static JSONArray rawToJsonArray(String response) throws ParseException {
		JSONParser jsonParser = new JSONParser();
			JSONArray array = (JSONArray) jsonParser.parse(response);

		return array;
	}
}
