package org.zasya.Samadhaan;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;



import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
    	RestAssured.baseURI = "https://staging-samadhan.zasyasolutions.com";
    	String response = given().log().all()
				.header("Content-Type", "application/json")
				.header("Accept", "application/json").body("{\r\n"
						+ "  \"password\": \"Tester@zasya.2025\",\r\n"
						+ "  \"email\": \"testuser7@gmail.com\"\r\n"
						+ "}")
				.when().post("/api/login")
				.then().assertThat().statusCode(200).body("message", equalTo("Login successfully."))
				.header("server", "cloudflare").extract().response().asString();

		System.out.println(">>Response of the Post Request : " + response);

		JsonPath js =  new JsonPath(response);
		String token = js.getString("token");

		System.out.println(">>token is printed here : " + token);
    }
}
