Feature: Validate Place APIs

Scenario : Verify Place is getting added successfully using Add Place API
	Given Add Place Payload
	When User calls "AddPlaceAPI" with Post request
	Then The API call is successful with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"