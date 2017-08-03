package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.List;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;

public class ActivityRepositoryStub implements ActivityRepository {

	private static Integer instanceCount = 0;

	@Override
	public List<Activity> findAllActivities() {
		List<Activity> activities = new ArrayList<Activity>();

		Activity activity1 = new Activity("desc 1", 10);
		Activity activity2 = new Activity("desc 2", 20);

		activities.add(activity1);
		activities.add(activity2);

		return activities;
	}

	@Override
	public Activity findActivity(String activityId) {
		if (activityId.equals("8888")) {
			System.out.println(String.format("Unknown activity Id [%s]", activityId));
			return null;
		}

		Activity activity = new Activity("desc", 1);
		activity.setActivityId(activityId);

		User user = new User();
		user.setName("Harry Brown");
		activity.setUser(user);

		return activity;
	}

	@Override
	public void create(Activity activity) {
		++instanceCount;
		activity.setActivityId(instanceCount.toString());
		System.out.println("Creating Activity: " + activity);
	}

	@Override
	public Activity update(Activity activity) {
		// search for activity in db
		// - update if found
		// - create new if not found
		
		String activityId = activity.getActivityId();
		if (activityId != null && activityId.equals("1357")) {
			activity.setDuration(activity.getDuration() + 5);
		}
		
		create(activity);
		
		return activity;
	}
}
