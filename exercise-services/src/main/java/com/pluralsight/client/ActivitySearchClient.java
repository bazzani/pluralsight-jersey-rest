package com.pluralsight.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.pluralsight.model.Activity;

public class ActivitySearchClient {

	private Client client;

	public ActivitySearchClient() {
		client = ClientBuilder.newClient();
	}

	public List<Activity> search(String param, List<String> searchValues) {
		// http://localhost:8080/exercise-services/webapi/search/activities?description=[swimming,running]

		URI uri = UriBuilder.fromUri("http://localhost:8080/exercise-services/webapi/")
				.path("search/activities")
				.queryParam(param, searchValues)
				.build();

		WebTarget target = client.target(uri);

		List<Activity> activities = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>>() {
		});

		System.out.println("Search param activities" + activities);

		return activities;
	}

	public List<Activity> search(String string, List<String> searchValues, String secondParam, int durationFrom,
			String thirdParam, int durationTo) {
		// http://localhost:8080/exercise-services/webapi/search/activities?description=[swimming, running]&durationFrom=10&durationTo=50
			
		URI uri = UriBuilder.fromUri("http://localhost:8080/exercise-services/webapi/")
				.path("search/activities")
				.queryParam(string, searchValues)
				.queryParam(secondParam, durationFrom)
				.queryParam(thirdParam, durationTo)
				.build();
		
		WebTarget target = client.target(uri);
		
		List<Activity> activities = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>>() {});
		
		System.out.println("Search paramS activities" + activities);
		
		return activities;
	}

	// todo async().get()
}
