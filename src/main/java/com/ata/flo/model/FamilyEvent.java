package com.ata.flo.model;

import java.util.Date;
import java.util.List;

public class FamilyEvent {
	private final String id;
	private final String type;
	private final String place;
	private final Date date;
	private final List<User> participants;
	
	public FamilyEvent(String id, String type, String place, Date date, List<User> participants) {
		super();
		this.id = id;
		this.type = type;
		this.place = place;
		this.date = date;
		this.participants = participants;
	}
	
	public String getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public String getPlace() {
		return place;
	}
	public Date getDate() {
		return date;
	}
	public List<User> getParticipants() {
		return participants;
	}
	
	
	
}
