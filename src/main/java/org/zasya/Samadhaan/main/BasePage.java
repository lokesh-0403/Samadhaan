package org.zasya.Samadhaan.main;

import io.restassured.RestAssured;

public class BasePage {
	 private static String token;
	 public static String baseURI() {
		 return RestAssured.baseURI = "https://staging-samadhan.zasyasolutions.com";
	 }
	 public static void output(Object value) {
		 System.out.println(value);
	 }
	    static {
	        // Common Base URI setup
	        RestAssured.baseURI = "https://staging-samadhan.zasyasolutions.com";
	    }

	    public static void setToken(String authToken) {
	        token = authToken;
	    }

	    public static String getToken() {
	        return token;
	    }
}
