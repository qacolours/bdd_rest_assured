package stepDefinitions;

import io.cucumber.java.Before;

public class hooks {
	
	stepDefinition oSD = new stepDefinition();

	@Before("@DeletePlace")
	public void setUp() throws Exception {
		
		if (stepDefinition.place_id == null) {
			oSD.add_place_payload_with("koustav", "6576801009", "Kolkata");
			oSD.user_calls_with_http_request("AddPlaceAPI", "POST");
			oSD.verify_created_place_id_maps_to_using("koustav", "GetPlaceAPI");
		}
	}
}
