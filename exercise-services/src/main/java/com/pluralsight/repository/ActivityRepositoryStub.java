package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.List;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;

public class ActivityRepositoryStub implements ActivityRepository {

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
		Activity activity = new Activity("desc", 1);
		activity.setId(activityId);

		User user = new User();
		user.setName("Harry Brown");
		activity.setUser(user);
		
		return activity;
	}
}