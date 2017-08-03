package com.pluralsight.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Activity {
	private String description;
	private int duration;
	private String activityId;
	private User user;

	public Activity() {
	}

	public Activity(String description, int duration) {
		this.description = description;
		this.duration = duration;
	}

	@XmlElement(name="desc")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@XmlAttribute
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Activity [description=" + description + ", duration=" + duration + ", activityId=" + activityId
				+ ", user=" + user + "]";
	}
	
	
}