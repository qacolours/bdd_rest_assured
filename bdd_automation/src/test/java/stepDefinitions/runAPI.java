package stepDefinitions;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import Pojo.api;
import Pojo.getCourse;
import io.restassured.path.json.JsonPath;

public class runAPI {
	
	public static void main(String[] args) {
		
		String[] expectedCourseTitle = {"Selenium Webdriver Java","Cypress","Protractor"};
		
		String o_res = given().
			formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
			formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
			formParam("grant_type", "client_credentials").
			formParam("scope", "trust").
		when().log().all().
		post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
		System.out.println(o_res);
		
		JsonPath oJP = new JsonPath(o_res);
		String acc_token = oJP.getString("access_token");
		
		getCourse getResponse = given().
			queryParam("access_token", acc_token).
		when().log().all().
		get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(getCourse.class);
		
		
		//Deserialization process, i.e., getting the key values from response code
		System.out.println(getResponse.getInstructor());
		System.out.println(getResponse.getLinkedIn());
		
		//If we need to get the course title and price of api courses, get the list of courses by using
		//getResponse.getCourse(). As we need to check the api courses get the list of api courses by
		//getResponse.getCourse().getApi(). Now api courses are present in form of array. So to get the 
		//title and price of a course we first need to select a course by using the index of the array as 
		//getResponse.getCourses().getApi().get(0). This gets the first course element inside api array.
		//Now to get the course title we use getResponse.getCourses().getApi().get(0).getCourseTitle()
		
		System.out.println(getResponse.getCourses().getApi().get(0).getCourseTitle());
		System.out.println(getResponse.getCourses().getApi().get(1).getPrice());
		
		//As inside API we have a number of courses and prices, we can iterate using a for loop 
		//and get the details
		
		List<api> apiCourses = getResponse.getCourses().getApi();
		
		for (int i = 0; i < apiCourses.size(); i++) {
			System.out.println(apiCourses.get(i).getCourseTitle());
			System.out.println(apiCourses.get(i).getPrice());
		}
		
		
		//get web automation course details and validate with expected
		//here we are declaring an arraylist as we are unknown of the number of courses for a course section
		ArrayList<String> retrievedCourseTitle = new ArrayList<String>();
		
		List<Pojo.webAutomation> o_wa = getResponse.getCourses().getWebAutomation();
		
		for (int i = 0; i < o_wa.size(); i++) {
			retrievedCourseTitle.add(o_wa.get(i).getCourseTitle());
		}
		
		//method to convert an array to arrayList
		List<String> expList = Arrays.asList(expectedCourseTitle);
		
		Assert.assertTrue(expList.equals(retrievedCourseTitle));
		
		
		//Serialization process, i.e., setting the request json
		
	}
}