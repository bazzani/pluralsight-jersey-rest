package com.pluralsight.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.pluralsight.model.Activity;

public class ActivityClient {
	private Client client;
	private WebTarget target;

	public ActivityClient() {
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080/exercise-services/webapi/");
	}

	public Activity activityGet(String id) {
		Activity activity = target.path("activities/" + id).request().get(Activity.class);
		return activity;
	}

	public String activityGetJSON(String id) {
		String activity = target.path("activities/" + id).request(MediaType.APPLICATION_JSON).get(String.class);
		return activity;
	}

	public List<Activity> activitiesGet() {
		List<Activity> activities = target.path("activities").request().get(new GenericType<List<Activity>>() {});
		return activities;
	}
}
