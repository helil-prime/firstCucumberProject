package api_tests;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class PetStoreAPIs {
	
	
	String baseUrl = "https://petstore.swagger.io/v2";
	Response response;
	
	
	@Test
	public void findPetsByStatus() {
		// The test is directly calling to the end point and validating just two items 
		// status code and content type
		
		
		String endpoint = "/pet/findByStatus";
		
		given().contentType("application/json")
		.accept(ContentType.JSON)
		.when().get(baseUrl + endpoint + "?status=sold")
		.then().statusCode(200)
		.and().contentType("application/json");
	}
	
	@Test
	public void findPetsByStatus_providingQueryParam() {
		// The test is directly calling to the end point with query parameter as a header 
		// and getting the response into a Response object, 
		// Then validating the status code and content type
		String endpoint = "/pet/findByStatus";
		
		response = given().contentType("application/json")
		.accept(ContentType.JSON)
		.queryParam("status", "sold")
		.when().get(baseUrl + endpoint)
		.thenReturn();
		
		response.prettyPrint();
		
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.getContentType(), "application/json");
	}

}
