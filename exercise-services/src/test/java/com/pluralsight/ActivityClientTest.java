package com.pluralsight;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.pluralsight.client.ActivityClient;
import com.pluralsight.client.ActivitySearchClient;
import com.pluralsight.model.Activity;
import com.pluralsight.model.ActivitySearch;
import com.pluralsight.model.ActivitySearchType;

public class ActivityClientTest {

	private ActivityClient client;
	private ActivitySearchClient searchClient;

	@Before
	public void setup() {
		client = new ActivityClient();
		searchClient = new ActivitySearchClient(); 
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

	@Test
	public void testDelete() {
		String activityId = "1234";
		
		client.delete(activityId);
	}
	
	@Test
	public void testSearchWithQueryParams() {
		List<String> searchValues = new ArrayList<String>();
		searchValues.add("swimming");
		searchValues.add("running");
		
		List<Activity> searchResult = searchClient.search("description", searchValues);
		
		assertNotNull(searchResult);
		assertThat(searchResult.size(), is(2));
	}
	
	@Test
	public void testSearchWithQueryParamRanges() {
		List<String> searchValues = new ArrayList<String>();
		searchValues.add("swimming");
		searchValues.add("running");
		
		String secondParam = "durationFrom";
		int durationFrom = 10;
		
		String thirdParam = "durationTo";
		int durationTo = 50;
		
		List<Activity> activities = searchClient.search("description", searchValues, secondParam, durationFrom, thirdParam, durationTo);
		
		assertNotNull(activities);
		assertThat(activities.size(), is(1));
	}
	
	@Test(expected=RuntimeException.class)
	public void testSearchWithPostNotFound() {
		ActivitySearch search = new ActivitySearch();
		search.setDescriptions(Arrays.asList("skipping", "hopping"));
		search.setDurationFrom(30);
		search.setDurationTo(45);
		
		searchClient.search(search);
	}
	
	@Test
	public void testSearchWithPost() {
		ActivitySearch search = new ActivitySearch();
		search.setDescriptions(Arrays.asList("jumping", "hopping"));
		search.setDurationFrom(65);
		
		List<Activity> activities = searchClient.search(search);
		
		assertNotNull(activities);
		assertThat(activities.size(), is(1));
	}
	
	@Test
	public void testSearchAndTypeWithPost() {
		ActivitySearch search = new ActivitySearch();
		search.setDescriptions(Arrays.asList("jumping", "hopping"));
		search.setDurationFrom(65);
		search.setActivitySearchType(ActivitySearchType.SEARCH_BY_DESCRIPTION);
		
		List<Activity> activities = searchClient.search(search);
		
		assertNotNull(activities);
		assertThat(activities.size(), is(1));
	}
}
