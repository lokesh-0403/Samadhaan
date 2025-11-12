package org.zasya.Samadhaan.login;

import org.testng.annotations.Test;
import org.zasya.Samadhaan.files.Payload;
import org.zasya.Samadhaan.main.BasePage;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Login extends BasePage{
	@Test
	public void userLogin() {
		baseURI();
		String response = given().log().all()
				.header("Content-Type", "application/json")
				.header("Accept", "application/json").body(Payload.loginCredentials())
				.when().post("/api/login")
				.then().assertThat().statusCode(200).body("message", equalTo("Login successfully."))
				.header("server", "cloudflare").extract().response().asString();

		output(">>Response of the Post Request : " + response);

		JsonPath js = new JsonPath(response);
		String token = js.getString("token");

		output(">>Place ID is printed here : " + token);
		setToken(token);
		output("Valid credentials test Passed");
		
	}

	@Test
	public void userInvalidLogin() {
		baseURI();
		String response = given().log().all()
				.header("Content-Type", "application/json")
				.header("Accept", "application/json").body(Payload.loginInvalidCredentials())
				.when().post("/api/login")
				.then().assertThat().statusCode(200).body("message", equalTo("Invalid email or password."))
				.header("server", "cloudflare").extract().response().asString();

		output(">>Response of the Post Request : " + response);

		JsonPath js = new JsonPath(response);
		String token = js.getString("token");

		output(">>Place ID is printed here : " + token);
		setToken(token);
		output("Invalid credentials test Passed");
		
	}
	
}
