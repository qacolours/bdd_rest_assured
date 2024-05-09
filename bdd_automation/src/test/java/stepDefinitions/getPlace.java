package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.*;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.*;

import io.cucumber.java.en.*;

public class getPlace {

	public static void main(String[] args) throws Exception {
		
		PrintStream req_log = new PrintStream(new FileOutputStream("req_log_test.txt"));
		PrintStream res_log = new PrintStream(new FileOutputStream("res_log_test.txt"));
		
		RequestSpecification oReqSpec = new RequestSpecBuilder().
											setBaseUri("https://rahulshettyacademy.com").
											addQueryParam("key", "qaclick123").
											addFilter(RequestLoggingFilter.logRequestTo(req_log)).
											addFilter(ResponseLoggingFilter.logResponseTo(res_log)).
											setContentType(ContentType.JSON).
											build();
		
		ResponseSpecification oResSpec = new ResponseSpecBuilder().
												expectStatusCode(200).
												expectContentType(ContentType.JSON).
												build();
		
		
		given().
			spec(oReqSpec).
			queryParam("place_id", "6a0a57a29b2454e6896d362316597324").
		when().
			get("/maps/api/place/get/json")/*.
		then().
			spec(oResSpec).
			extract().
			response()*/;
										
		
	}
}
