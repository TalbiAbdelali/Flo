package com.ata.flo.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PersonnelProgram {
	private final UUID id;
	private final Date startDate;
	private final Date endDate;
	private final List<Activity> activités;
	
	public PersonnelProgram(UUID id, Date startDate, Date endDate, List<Activity> activités) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.activités = activités;
	}

	public UUID getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public List<Activity> getActivités() {
		return activités;
	}
	
	
}
