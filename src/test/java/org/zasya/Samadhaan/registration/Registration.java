package org.zasya.Samadhaan.registration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.zasya.Samadhaan.files.Payload;
import org.zasya.Samadhaan.main.BasePage;

import io.restassured.path.json.JsonPath;

public class Registration extends BasePage{

	public void userRegistration() {
		baseURI();
		String response = given().log().all()
				.header("Content-Type", "application/json")
				.header("Accept", "application/json").body(Payload.registration())
				.when().post("/api/login")
				.then().assertThat().statusCode(200).body("message", equalTo("Login successfully."))
				.header("server", "cloudflare").extract().response().asString();

		System.out.println(">>Response of the Post Request : " + response);

		JsonPath js = new JsonPath(response);
		String token = js.getString("token");

		System.out.println(">>Place ID is printed here : " + token);
		setToken(token);
	}
}
