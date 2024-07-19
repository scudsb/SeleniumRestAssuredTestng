package ThinkTester.ContactListApp.Tests.ApiTests;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ThinkTester.ContactListApp.Apis.UserApi;
import ThinkTester.ContactListApp.Utils.Environment;
import ThinkTester.ContactListApp.Utils.UserFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserApiTest {
	
	private Map<String, String> user;
	
	@BeforeClass
	public void setup() {
		user = UserFactory.getRandomUser();
		RestAssured.baseURI = Environment.HEROKU;
	}
	
	@Test
	public void addUserTest() {	
		Response response = UserApi.addUser(user);
		System.out.println("Add User API - " + response.getBody().print());
		System.out.println("Status Code - " + response.statusCode());
		response.then().assertThat().statusCode(201);
	}

	@Test(dependsOnMethods = "addUserTest")
	public void loginUserTest() {	
		Response response = UserApi.loginUser(user);
		response.then().assertThat().statusCode(200);
		String token = response.jsonPath().get("token");
		user.put("token", token);
		System.out.println("Login User API - " + response.getBody().print());
		System.out.println("Status Code - " + response.statusCode());
		System.out.println(token);
	}
	
	@Test(dependsOnMethods = "loginUserTest")
	public void logoutUserTest() {	
		String token = user.get("token");
		Response response = UserApi.logoutUser(token);
		response.then().assertThat().statusCode(200);
		System.out.println("Logout User API - " + response.getBody().print());
		System.out.println("Status Code - " + response.statusCode());
	}
	
	@Test(priority = 1)
	public void deleteUserTest() {	
		loginUserTest();
		String token = user.get("token");
		Response response = UserApi.deleteUser(token);
		response.then().assertThat().statusCode(200);
		System.out.println("Delete User API - " + response.getBody().print());
		System.out.println("Status Code - " + response.statusCode());
	}		
}