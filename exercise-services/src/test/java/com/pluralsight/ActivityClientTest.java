package com.pluralsight;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.pluralsight.client.ActivityClient;
import com.pluralsight.model.Activity;

public class ActivityClientTest {

	private ActivityClient client;

	@Before
	public void setup() {
		client = new ActivityClient();
	}

	@Test
	public void testGetActivity() {
		Activity activity = client.activityGet("1234");
		System.out.println(String.format("activity [%s]", activity));

		assertNotNull(activity);
	}

	@Test
	public void testGetActivityJSON() {
		String activity = client.activityGetJSON("1234");

		System.out.println(String.format("activity JSON [%s]", activity));
		assertNotNull(activity);
	}

	@Test
	public void testGetActivities() {
		List<Activity> activities = client.activitiesGet();

		assertNotNull(activities);
		assertThat("Activity count problem", activities.size(), is(2));
	}
}
