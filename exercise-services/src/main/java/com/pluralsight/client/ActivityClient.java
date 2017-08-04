package com.pluralsight.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pluralsight.model.Activity;

public class ActivityClient {
	private Client client;
	private WebTarget target;

	public ActivityClient() {
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080/exercise-services/webapi/");
	}

	public Activity get(String id) {
		Response response = target.path("activities/" + id).request(MediaType.APPLICATION_JSON).get(Response.class);

		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}

		return response.readEntity(Activity.class);
	}

	public String getJSON(String id) {
		String activity = target.path("activities/" + id).request(MediaType.APPLICATION_JSON).get(String.class);
		return activity;
	}

	public List<Activity> get() {
		List<Activity> activities = target.path("activities").request().get(new GenericType<List<Activity>>() {
		});
		return activities;
	}

	public Activity create(Activity activity) {
		Response response = target.path("activities/activity")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(activity, MediaType.APPLICATION_JSON));

		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}

		return response.readEntity(Activity.class);
	}

	public Activity update(Activity activity) {
		Response response = target.path("activities/" + activity.getActivityId())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(activity, MediaType.APPLICATION_JSON));

		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}

		return response.readEntity(Activity.class);
	}

	public void delete(String activityId) {
		Response response = target.path("activities/" + activityId)
				.request()
				.delete();
		
		if(response.getStatus() != Status.OK.getStatusCode()) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}
	}
}
