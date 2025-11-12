package org.zasya.Samadhaan.files;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Payload {

	public static Object loginCredentials() {
		 Map<String, Object> credentials = new HashMap<>();
//	        ArrayList<String> email = new ArrayList<>();
	        String email="testuser7@gmail.com";
//	        ArrayList<String> password = new ArrayList<>();
	        String password = "Tester@zasya.2025";
//	        List<Map<String, Object>> creds = new ArrayList<>();
	        credentials.put("email", email);
	        credentials.put("password",password);
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String jsonOutput = gson.toJson(credentials);
//	        System.out.println(jsonOutput);
			return jsonOutput;
	}
	public static Object loginInvalidCredentials() {
		 Map<String, Object> credentials = new HashMap<>();
//	        ArrayList<String> email = new ArrayList<>();
	        String email="testuser7@gmail.com";
//	        ArrayList<String> password = new ArrayList<>();
	        String password = "tester@zasya.2025";
//	        List<Map<String, Object>> creds = new ArrayList<>();
	        credentials.put("email", email);
	        credentials.put("password",password);
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String jsonOutput = gson.toJson(credentials);
//	        System.out.println(jsonOutput);
			return jsonOutput;
	}
}
