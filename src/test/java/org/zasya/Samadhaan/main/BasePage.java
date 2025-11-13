package org.zasya.Samadhaan.main;

import org.testng.annotations.BeforeClass;
import org.zasya.Samadhaan.login.AuthManager;
import org.zasya.Samadhaan.login.Login;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;



public class BasePage {
	 protected static String token;
	 protected static String authToken;
	 public RequestSpecification requestSpec;
	 
	 @BeforeClass
	    public void setup() {
	        // Load configuration
	        String baseUrl = ConfigReader.getProperty("base.url");
	        RestAssured.baseURI = baseUrl;
	        
	        output("Base URL configured: " + baseUrl);   
	            authToken = AuthManager.login();
	         
	            output("Token obtained: " + authToken.substring(0, Math.min(20, authToken.length())) + "...");

	            requestSpec = RestAssured.given()
	                .header("Content-Type", "application/json")
	                .header("Authorization", "Bearer " + authToken);
	        
	        output("Request specification configured");
	    }
	 
	 
	 public static String baseURI() {
		 return RestAssured.baseURI = "base.url";
	 }
	 public static void output(Object value) {
		 System.out.println(value);
	 }
//	    static {
//	        // Common Base URI setup
//	        RestAssured.baseURI = "base.url";
//	    }

	    public static void setToken(String authToken) {
	        token = authToken;
	    }

	 
	    public static String getToken() {
	    	return token;
	    }
}
