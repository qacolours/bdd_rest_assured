package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.*;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.*;


public class utils {
	
	public static RequestSpecification oReqSpec;
	public static ResponseSpecification oResSpec;

	public RequestSpecification requestSpecification() throws Exception {
		
		if (oReqSpec == null) {
			PrintStream req_log = new PrintStream(new FileOutputStream(getGlobalValue("request_log_location")));
			PrintStream res_log = new PrintStream(new FileOutputStream(getGlobalValue("response_log_location")));
			
			oReqSpec = new RequestSpecBuilder().
					setBaseUri(getGlobalValue("baseURI")).
					addQueryParam("key", "qaclick123").
					setContentType(ContentType.JSON).
					addFilter(RequestLoggingFilter.logRequestTo(req_log)).		//Used for logging request payload
					addFilter(ResponseLoggingFilter.logResponseTo(res_log)).	//Used for logging response json
					build();
		}
		
		return oReqSpec;
	}
	
	
	public ResponseSpecification responseSpecification(){
		
		if (oResSpec == null) {
			oResSpec = new ResponseSpecBuilder().
					expectStatusCode(200).
					expectContentType(ContentType.JSON).
					build();
		}
		
		return oResSpec;
	}
	
	
	public String getGlobalValue(String key) throws Exception {
		
		Properties oProp = new Properties();
		FileInputStream oFis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\global.properties");
		oProp.load(oFis);
		
		String val = oProp.getProperty(key);
		
		return val;
	}
	
	
	public String getJsonPath(Response res, String key) {
		
		String response = res.asString();
		JsonPath oJP = new JsonPath(response);
		String keyVal = oJP.get(key).toString();
		
		return keyVal;
	}
}
