package org.zasya.Samadhaan.AppCategory;

import org.zasya.Samadhaan.files.ReUsableMethods;
import org.zasya.Samadhaan.main.BasePage;
import org.zasya.Samadhaan.main.ConfigReader;

import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class AppCategory extends BasePage {

	@Test
	public void category() {
		String categoriesString = ConfigReader.getProperty("appCategories.endpoint");
		output(token);
		String getResponse = given().log().all()
				.spec(requestSpec)
				.when().get(categoriesString)
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
