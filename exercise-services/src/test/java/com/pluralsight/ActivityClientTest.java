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
		Activity activity = client.get("1234");
		System.out.println(String.format("activity [%s]", activity));

		assertNotNull(activity);
	}

	@Test
	public void testGetActivityJSON() {
		String activity = client.getJSON("5678");

		System.out.println(String.format("activity JSON [%s]", activity));
		assertNotNull(activity);
	}

	@Test
	public void testGetActivities() {
		List<Activity> activities = client.get();

		assertNotNull(activities);
		assertThat("Activity count problem", activities.size(), is(2));
	}

	@Test(expected = RuntimeException.class)
	public void testGetThrowsExceptionWithNullId() {
		client.get(null);
	}

	@Test(expected = RuntimeException.class)
	public void testGetThrowsExceptionWithShortId() {
		client.get("123");
	}

	@Test(expected = RuntimeException.class)
	public void testGetThrowsExceptionWithUnknownId() {
		client.get("8888");
	}

	@Test
	public void testPost() {
		Activity activity = new Activity("Bikram Yoga", 45);
		activity = client.create(activity);

		assertNotNull(activity);
		assertNotNull(activity.getActivityId());
		assertThat("Activity duration should be increased", activity.getDuration(), is(55));
	}
	
	@Test
	public void testPutUpdate() {
		Activity activity = new Activity("Running", 55);
		activity.setActivityId("1357");
		
		activity = client.update(activity);
		
		assertNotNull(activity);
		assertThat(activity.getDuration(), is(60));
	}
	
	@Test
	public void testPutCreate() {
		Activity activity = new Activity("Walking", 20);
		
		activity = client.update(activity);
		
		assertNotNull(activity);
		assertNotNull(activity.getActivityId());
		assertThat(activity.getDuration(), is(20));
	}
}
