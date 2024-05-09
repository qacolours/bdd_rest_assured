package stepDefinitions;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import Pojo.location;
import Pojo.placeDetail;
import io.restassured.response.Response;

public class addPlaces {
	
	public static void main(String[] args) {
		
		//Serialization process, i.e., setting the request json
		//On initializing the placeDetail pojo class we can call the setter classes to set the values
		placeDetail oPD = new placeDetail();
		
		//location key is a nested json having more key-values in it. Thus we have created another pojo class
		//for location.
		location oLoc = new  location();
		oLoc.setLat(-38.383494);
		oLoc.setLng(33.427362);
		
		oPD.setLocation(oLoc);
		
		oPD.setAccuracy(50);
		oPD.setName("Frontline house");
		oPD.setPhone_number("(+91) 983 893 3937");
		oPD.setAddress("29, side layout, cohen 09");
		
		//Types is a list of values thus we have created a list.
		List<String> allTypes = new ArrayList<String>();
		allTypes.add("shoe park");
		allTypes.add("shop");
		
		oPD.setTypes(allTypes);
		
		oPD.setWebsite("http://google.com");
		oPD.setLanguage("French-IN");
		
		Response response = given().
			log().all().
			queryParam("key", "qaclick123").
			body(oPD).
			when().post("https://rahulshettyacademy.com/maps/api/place/add/json").
			then().
			assertThat().statusCode(200).extract().response();
		
		String resString = response.asString();
		System.out.println(resString);
	}

}
