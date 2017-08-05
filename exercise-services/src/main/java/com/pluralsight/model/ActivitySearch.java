package com.pluralsight.model;

import java.util.List;

public class ActivitySearch {
	private List<String> descriptions;
	private Integer durationFrom;
	private Integer durationTo;
	private ActivitySearchType activitySearchType;
	
	public List<String> getDescriptions() {
		return descriptions;
	}

	public Integer getDurationFrom() {
		return durationFrom;
	}

	public Integer getDurationTo() {
		return durationTo;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	public void setDurationFrom(Integer durationFrom) {
		this.durationFrom = durationFrom;
	}

	public void setDurationTo(Integer durationTo) {
		this.durationTo = durationTo;
	}

	public ActivitySearchType getActivitySearchType() {
		return activitySearchType;
	}

	public void setActivitySearchType(ActivitySearchType activitySearchType) {
		this.activitySearchType = activitySearchType;
	}

	@Override
	public String toString() {
		return "ActivitySearch [descriptions=" + descriptions + ", durationFrom=" + durationFrom + ", durationTo="
				+ durationTo + ", activitySearchType=" + activitySearchType + "]";
	}
}
