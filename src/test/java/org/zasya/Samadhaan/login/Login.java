package org.zasya.Samadhaan.login;

import org.testng.annotations.Test;
import org.zasya.Samadhaan.files.Payload;
import org.zasya.Samadhaan.files.ReUsableMethods;
import org.zasya.Samadhaan.main.BasePage;
import org.zasya.Samadhaan.main.ConfigReader;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Login extends BasePage{
	
	@Test
	public void userLogin() {
		 String baseUrl = ConfigReader.getProperty("base.url");
         String loginEndpoint = ConfigReader.getProperty("login.endpoint");
		String response = given().baseUri(baseUrl).log().all()
				.header("Content-Type", "application/json")
				.header("Accept", "application/json").body(Payload.loginCredentials())
				.when().post(loginEndpoint)
				.then().assertThat().statusCode(200).body("message", equalTo("Login successfully."))
				.header("server", "cloudflare").extract().response().asString();

		output(">>Response of the Post Request : " + response);

		JsonPath js =  new JsonPath(response);
		String token = js.getString("token");

		output(">>Place ID is printed here : " + token);
		
		output("Valid credentials test Passed");
		 setToken(token);
		
	}

//	@Test
	public void userInvalidLogin() {
		
		String response = given().log().all()
				.header("Content-Type", "application/json")
				.header("Accept", "application/json").body(Payload.loginInvalidCredentials())
				.when().post("/api/login")
				.then().assertThat().statusCode(200).body("message", equalTo("Invalid email or password."))
				.header("server", "cloudflare").extract().response().asString();

		output(">>Response of the Post Request : " + response);

		JsonPath js = new JsonPath(response);
		String token = js.getString("token");

		output(">>Token is printed here : " + token);
	
		output("Invalid credentials test Passed");
		
	}
	
}
