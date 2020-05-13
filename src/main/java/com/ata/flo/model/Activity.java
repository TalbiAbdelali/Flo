package com.ata.flo.model;

import java.util.Date;
import java.util.Timer;

public class Activity {
	private final String id;
	private final String activityname;
	private final String type;
	private final String description;
	private final int nbRepetitions;
	private final Timer duration;
	private final Date performed;
	
	public Activity(String id, String activityname, String type, String description, int nbRepetitions, Timer duration, Date performed) {
		super();
		this.id = id;
		this.activityname = activityname;
		this.description = description;
		this.nbRepetitions = nbRepetitions;
		this.type = type;
		this.duration = duration;
		this.performed = performed;
	}

	public String getId() {
		return id;
	}

	public String getActivityname() {
		return activityname;
	}

	public String getType() {
		return type;
	}

	public Timer getDuration() {
		return duration;
	}

	public Date getPerformed() {
		return performed;
	}

	public String getDescription() {
		return description;
	}

	public int getNbRepetitions() {
		return nbRepetitions;
	}
	
	
}
