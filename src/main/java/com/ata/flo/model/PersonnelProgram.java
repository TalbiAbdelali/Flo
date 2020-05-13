package com.ata.flo.model;

import java.util.Date;
import java.util.List;

public class PersonnelProgram {
	private final String id;
	private final Date startDate;
	private final Date endDate;
	private final List<Activity> activities;
	
	public PersonnelProgram(String id, Date startDate, Date endDate, List<Activity> activités) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.activities = activités;
	}

	public String getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public List<Activity> getActivities() {
		return activities;
	}
	
	
}
