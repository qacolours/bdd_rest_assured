package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import Pojo.location;
import Pojo.placeDetail;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.testDataBuild;
import resources.utils;


public class stepDefinition extends utils {
	
	RequestSpecification oReqSpec;
	ResponseSpecification oResSpec;
	
	//Object definition when we are not using data driven framework
	placeDetail oPD;
	
	Response response;
	
	//Object initialization when we are using data driven framework
	testDataBuild oTD = new testDataBuild();
	
	JsonPath oJP;
	static String place_id;
	

	@Given("Add Place Payload with {string},{string},{string}")
	public void add_place_payload_with(String name, String phone, String add)throws Exception {
		
		//If we are not making the test case data driven, we can create an object of the pojo class placeDetail
		//directly here, create the payload and send it to the body.
		
		//If we are using the data driven concept we are creating a testDataBuilder class where we are
		//creating the object of the pojo class placeDetail, buidling the payload, initializing the 
		//testDataBuilder class here and calling the method that creates the payload and send it to the body
		
		
		/*oReqSpec = new RequestSpecBuilder().
													setBaseUri("https://rahulshettyacademy.com").
													setContentType(ContentType.JSON).
													build();
		
		oResSpec = new ResponseSpecBuilder().
													expectStatusCode(200).
													expectContentType(ContentType.JSON).
													build();*/
		
		oReqSpec = given().
				spec(requestSpecification()).
				body(oTD.addPlacePayload(name,phone,add));
		
	}
	
	
	@When("User calls {string} with {string} HTTP request")
	public void user_calls_with_http_request(String api_resource, String http_method) throws Exception {
		
		APIResources resourceAPI = APIResources.valueOf(api_resource);
		
		System.out.println(resourceAPI.getResource());
		
		if (http_method.equalsIgnoreCase("post")) {
			response = oReqSpec.
					when().	
						post(resourceAPI.getResource());
			
			System.out.println(response.asString());
		} else if (http_method.equalsIgnoreCase("get")) {
			response = oReqSpec.
					when().	
						get(resourceAPI.getResource());
			
			System.out.println(response.asString());
		} else if (http_method.equalsIgnoreCase("delete")) {
			response = oReqSpec.
					when().	
						delete(resourceAPI.getResource());
		}
		
		response.
				then().
					spec(responseSpecification()).
					extract().
					response();
	}
	
	
	@Then("The API call returns success with Status code {string}")
	public void the_api_call_returns_success_with_status_code(String statusCode) {

		assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
		
	}
	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		
		assertEquals(getJsonPath(response, key),value);
	}
	
	
	@Then("verify created place_id maps to {string} using {string}")
	public void verify_created_place_id_maps_to_using(String name, String api_resource) throws Exception {
	    
		place_id = getJsonPath(response, "place_id");
		
		oReqSpec = given().
						spec(requestSpecification()).
						queryParam("place_id", place_id);
		
		user_calls_with_http_request(api_resource, "GET");
		
		String name_key = getJsonPath(response, "name");
		
		Assert.assertEquals(name_key, name);
		
	}
	
	
	@Given("DeletePlace payload")
	public void delete_place_payload() throws Exception {
	    
		oReqSpec = given().
						spec(requestSpecification()).
						body(oTD.deletePlacePayload(place_id));
		
	}
}
