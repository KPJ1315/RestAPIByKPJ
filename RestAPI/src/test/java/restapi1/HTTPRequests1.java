package restapi1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator.*;


/*given()
 * Under this method/section add the details of pre-requisites for a http request like,
 * content type, set cookies, add auth, add param, set headers infor, etc..
 */

/*when()
 * Under this method/section we need to write what type of request we are sending like
 * get,post,put,delete, etc..*/

/*then()
 * Under this method/section we validate the response like,
 * response code, status, extract response, extract headers, & response body, etc..*/

public class HTTPRequests1<K, V> {

	int id;
//	@Test(priority = 2)
//	void getUser() {
//		System.out.println("Running get user method");
//		
//		System.out.println("ID value in getUser(): "+id);
//		// Specify the base URI of the API
//		RestAssured.baseURI = "https://reqres.in";//"https://api.example.com";
//		// Make a GET request and validate the response
//
//		//		given() 
//		/*if nothing is added to given method or if its not required we can remove it. 
//  		  If we remove it then the dot from when also should be removed. This is applicable to when() and then() as well.
//		 */
//		when()
//		.get("/api/users/"+id)
//		.then()
//		.statusCode(200)
//		.log().all();
//	}
	
	@Test (priority = 4)
	void getAllUsers() {
		System.out.println("Running get all users method");
		// Specify the base URI of the API
		RestAssured.baseURI = "https://reqres.in";//"https://api.example.com";
		when()
		.get("/api/users?page=2")
		.then()
		.statusCode(200)
		.body("page", equalTo(2))
		.log().all();
	}
	
	@Test (priority = 1)
	void createUser() {
		System.out.println("Running create user method");
		// Specify the base URI of the API
		RestAssured.baseURI = "https://reqres.in";//"https://api.example.com";
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "Girish");
		data.put("job", "Yoga Instructor");
		
		id=given() // stores id from response to global variable 'id'
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("/api/users")
		
//		.then()
//		.statusCode(201)
//		.log().all();
		
		//to fetch id of user from response
		.jsonPath().getInt("id");
		System.out.println("ID received:" +id);
	}
	
	@Test (priority = 3, dependsOnMethods = {"createUser"})
	void updateUser() {
		System.out.println("Running update user method");
		// Specify the base URI of the API
		RestAssured.baseURI = "https://reqres.in";//"https://api.example.com";
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "Giri");
		data.put("job", "Yoga_Instructor");		
		when()
		.put("/api/users/"+id)
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test (priority = 5)
	void deleteUser() {
		System.out.println("Running delete user method");
		// Specify the base URI of the API
		RestAssured.baseURI = "https://reqres.in";//"https://api.example.com";		
		
		when()
		.put("/api/users/"+id)
		.then()
		.statusCode(200)
		.log().all();
	}	
}
