Feature: Validating place API's

@AddPlace
Scenario Outline: Verify place is being successfully added using AddPlaceAPI
		Given Add Place Payload with "<name>","<phone_number>","<address>"
		When User calls "AddPlaceAPI" with "POST" HTTP request
		Then The API call returns success with Status code "200"
		And "status" in response body is "OK"
		And "scope" in response body is "APP"
		And verify created place_id maps to "<name>" using "GetPlaceAPI" 
		
Examples:
					|name		|phone_number			|address				|
					|John		|(+91) 9872198734	|Bengaluru			|
#					|David	|(+91) 8672198734	|Pune						|


@DeletePlace
Scenario: Verify  if Delete Place functionality is working
			Given DeletePlace payload
			When User calls "DeletePlaceAPI" with "POST" HTTP request
			Then The API call returns success with Status code "200"
			And "status" in response body is "OK"