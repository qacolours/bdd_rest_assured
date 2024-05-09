package resources;

import java.util.ArrayList;
import java.util.List;

import Pojo.location;
import Pojo.placeDetail;

public class testDataBuild {

	
	public placeDetail addPlacePayload(String name, String phone, String add) {
		
		placeDetail oPD = new placeDetail();
		
		//location key is a nested json having more key-values in it. Thus we have created another pojo class
		//for location.
		location oLoc = new  location();
		oLoc.setLat(-38.383494);
		oLoc.setLng(33.427362);
		
		oPD.setLocation(oLoc);
		
		oPD.setAccuracy(50);
		oPD.setName(name);
		oPD.setPhone_number(phone);
		oPD.setAddress(add);
		
		//Types is a list of values thus we have created a list.
		List<String> allTypes = new ArrayList<String>();
		allTypes.add("shoe park");
		allTypes.add("shop");
		
		oPD.setTypes(allTypes);
		
		oPD.setWebsite("http://google.com");
		oPD.setLanguage("French-IN");
		
		return oPD;
	}
	
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n\t\"place_id\":\"" + placeId + "\"\r\n}";
	}
}
