package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import POJOLibrary.SerializeLocationPOJO;
import POJOLibrary.SerializePOJO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefination {
	RequestSpecification addPlaceReq;
	RequestSpecification addPlaceBaseReq;
	Response response;
	
	
	@Given("Add Place Payload")
	public void add_Place_Payload() {
		SerializePOJO sp = new SerializePOJO();
		sp.setAccuracy(78);
		sp.setAddress("209, side layout, cohen 09");
		sp.setLanguage("English-IN");
		sp.setName("DPalace");
		sp.setPhone_number("(+91) 983 893 3937");
		sp.setWebsite("http://google.com");
		
		List<String> inputType = new ArrayList<String>();
		inputType.add("Shoe Park");
		inputType.add("shop");
		sp.setTypes(inputType);
		
		SerializeLocationPOJO slp = new SerializeLocationPOJO();
		slp.setLat(89.9);
		slp.setLng(67.89);
		sp.setLocation(slp);
		
		addPlaceBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
												.addQueryParam("key", "qaclick123")
												.setContentType(ContentType.JSON).build();
		addPlaceReq = given().spec(addPlaceBaseReq).body(sp);
		
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON);
		
		
	}
	@When("User calls {string} with Post request")
	public void user_calls_with_Post_request(String string) {
		response = addPlaceReq.when().post("/maps/api/place/add/json")
					.then().spec(resSpec).statusCode(200).extract().response();
		
	}
	@Then("The API call is successful with status code {int}")
	public void the_API_call_is_successful_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
		
	}
}
