package com.ata.flo.model;

import java.util.Date;
import java.util.Timer;
import java.util.UUID;

public class Activity {
	private final UUID id;
	private final String activityname;
	private final String type;
	private final String description;
	private final int nbRepetitions;
	private final Timer duration;
	private final Date date;
	
	public Activity(UUID id, String activityname, String type, String description, int nbRepetitions, Timer duration, Date date) {
		super();
		this.id = id;
		this.activityname = activityname;
		this.description = description;
		this.nbRepetitions = nbRepetitions;
		this.type = type;
		this.duration = duration;
		this.date = date;
	}

	public UUID getId() {
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

	public Date getDate() {
		return date;
	}
	
	
}
