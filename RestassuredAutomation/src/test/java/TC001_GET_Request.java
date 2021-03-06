import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {

	 @Test
	void getweatherDetails() {

		// Specify base URI
		RestAssured.baseURI = "https://weatherbit-v1-mashape.p.rapidapi.com/forecast/3hourly";

		// Request object
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-RapidAPI-Key", "33e6ffa558msh0b8574c762a2b2ep16f3b8jsnfdfb57f8f19d");
		requestHeaders.put("X-RapidAPI-Host", "weatherbit-v1-mashape.p.rapidapi.com");
		RequestSpecification httpRequest = RestAssured.given().headers(requestHeaders);

		// Response object
		Map<String, String> requestParams = new HashMap<>();
		requestParams.put("lat", "35.5");
		requestParams.put("lon", "-78.5");
		Response response = httpRequest.queryParams(requestParams).get();

		// print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);

		// status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);

		// status line verification
		String statusLine = response.getStatusLine();
		System.out.println("Status line is:" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	void getAllWeatherData() {

		// Specify base URI
		RestAssured.baseURI = "https://weatherbit-v1-mashape.p.rapidapi.com/forecast/3hourly";

		// Request object
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-RapidAPI-Key", "33e6ffa558msh0b8574c762a2b2ep16f3b8jsnfdfb57f8f19d");
		requestHeaders.put("X-RapidAPI-Host", "weatherbit-v1-mashape.p.rapidapi.com");
		RequestSpecification httpRequest = RestAssured.given().headers(requestHeaders);

		// Response object
		Map<String, String> requestParams = new HashMap<>();
		requestParams.put("lat", "35.5");
		requestParams.put("lon", "-78.5");
		Response response = httpRequest.queryParams(requestParams).get();
//		System.out.println(response.getBody().asPrettyString());

		// Extracting json data
		JsonPath jsonPath = response.jsonPath();
		int sizeOfArray = 1;//jsonPath.getInt("data.size()");
		for (int i = 0; i < sizeOfArray; i++) {
			String wind_cdr = jsonPath.getString("data["+i+"].wind_cdir");
			Assert.assertEquals(wind_cdr, "ENE");

		}

	}

}
//{
//    "data": [
//        {
//            "wind_cdir": "NW",
//            "rh": 66,
//            "pod": "n",
//            "timestamp_utc": "2022-06-21T06:00:00",
//            "pres": 1014,
//            "solar_rad": 0,
//            "ozone": 311.5,
//            "weather": {
//                "icon": "c02n",
//                "code": 802,
//                "description": "Scattered clouds"
//            },
