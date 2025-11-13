package org.zasya.SamadhaanEvent;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import org.zasya.Samadhaan.main.BasePage;
import org.zasya.Samadhaan.main.ConfigReader;

import io.restassured.path.json.JsonPath;


public class Event extends BasePage {

	@Test
	public void eventData() {
		String eventData = ConfigReader.getProperty("event.endpoint");
		output(token);
		String getResponse = given().log().all()
				.spec(requestSpec)
				.when().get(eventData)
				.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js1 = new JsonPath(getResponse);
		int count = js1.getInt("data.size()");;
		for(int i=0;i<count;i++) {
			
		String title = js1.getString("data["+i+"].title");
		output(">> Getting a get Response for title : "+title);
		String status = js1.getString("status");
		output(status);
	}
	}
}
