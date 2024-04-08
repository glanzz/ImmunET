package com.immunet.immunet.model;

import java.util.Date;


public class Schedule {
	int id;
	Date scheduledDate;
	 //static Date scheduledDate= new Date(124, 02, 03 );
	Date administeredDate;
	static enum ImmunizationStatus {
		PENDING,
		DELAYED,
		COMPLETE;
	}
	ImmunizationStatus status= ImmunizationStatus.PENDING; //Initial status 
	
	public Schedule(Date scheduledDate, Date administeredDate) {
		super();
		this.scheduledDate = scheduledDate;
		this.administeredDate = administeredDate;
		updateStatus();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
		updateStatus();
	}

	public Date getAdministeredDate() {
		return administeredDate;
	}

	public void setAdministeredDate(Date administeredDate) {
		this.administeredDate = administeredDate;
		updateStatus();
	}

	public ImmunizationStatus getStatus() {
		return status;
	}

	public void setStatus(ImmunizationStatus status) {
		this.status = status;
	}
	
	private void updateStatus() {
		/*
		 * Is adminDate present => completed
		 * scheduled is after today => delayed
		 * by default it is pending 
		 * */
	}
	
	
	
	
	public boolean isDelayed() {
		return status == ImmunizationStatus.DELAYED;
		if (status == ImmunizationStatus.PENDING) {
			Date todayDate= new Date();
			if (scheduledDate.after(todayDate)) {
				setStatus(ImmunizationStatus.DELAYED);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public boolean isComplete() {
		return status == ImmunizationStatus.COMPLETE;
	}
		

	public void markComplete(Doctor d) throws Exception {
		Date todayDate= new Date();
		if (isComplete()) {
			throw new Exception("Already completed");	
		} else if (isDelayed()|| status==ImmunizationStatus.PENDING) {
			setAdministeredDate(todayDate);
			updateStatus();
			
		}	
	}
	
	public void save() {
		
	}

}
