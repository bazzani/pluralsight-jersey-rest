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
		activity.setId(activityId);

		User user = new User();
		user.setName("Harry Brown");
		activity.setUser(user);

		return activity;
	}

	@Override
	public void create(Activity activity) {
		++instanceCount;
		activity.setId(instanceCount.toString());
		System.out.println("Creating Activity: " + activity);
	}
}
