package br.ufrn.imd.rankerinformation.user.search;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParserUtil {

	public JsonParserUtil() {
		
	}
	
	public JSONObject extractJSONObject(String stringJson) throws ParseException{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(stringJson);
		return (JSONObject) obj;
	}
	
	public String extractStringJSONObject(String stringJson, String stringObj) throws ParseException{
		JSONObject jsonObject = extractJSONObject(stringJson);
		return jsonObject.get(stringObj).toString();
	}

	public JSONArray extractJSONArray(String stringJson,String stringObj) throws ParseException{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(stringJson);
		JSONObject jsonObject = (JSONObject) obj;
		return (JSONArray) jsonObject.get(stringObj);
	}
	
	public JSONArray extractJSONArray(String stringJson) throws ParseException{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(stringJson);
		return (JSONArray) obj;
	}
}