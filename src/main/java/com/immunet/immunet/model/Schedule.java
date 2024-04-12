package com.immunet.immunet.model;

import java.util.Date;

import com.immunet.immunet.entity.ScheduleEntity;

public class Schedule {
	Integer id;
	Date scheduledDate;
	 //static Date scheduledDate= new Date(124, 02, 03 );
	Date administeredDate;
	static enum ImmunizationStatus {
		PENDING,
		DELAYED,
		COMPLETE;
	}
	Integer doctorId;
	ImmunizationStatus status= ImmunizationStatus.PENDING; //Initial status
	
	public Schedule() {
		
	}
	
	public Schedule(Date scheduledDate, Date administeredDate) {
		this.scheduledDate = scheduledDate;
		this.administeredDate = administeredDate;
		updateStatus();
	}

	public Integer getId() {
		return id;
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
	
	public Integer getDoctorId() {
		return this.doctorId;
	}
	
	private void updateStatus() {
		/*
		 * Is adminDate present => completed
		 * scheduled is after today => delayed
		 * by default it is pending 
		 * */
		Date todayDate= new Date();
		if (administeredDate != null) {
			setStatus(ImmunizationStatus.COMPLETE);
		} else if (scheduledDate.after(todayDate)) {
			setStatus(ImmunizationStatus.DELAYED);
		} else {
			setStatus(ImmunizationStatus.PENDING);
		}
		
	}
	
	
	
	
	public boolean isDelayed() {
		return status == ImmunizationStatus.DELAYED;
		
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
	
	public static Schedule load(ScheduleEntity s) {
		Schedule schedule = new Schedule();
		schedule.id = s.getId();
		schedule.setScheduledDate(s.getScheduleDate());
		schedule.setAdministeredDate(s.getTakenDate());
		schedule.setDoctorId(s.getDoctor().getId());
		return schedule;
	}

	private void setDoctorId(Integer id2) {
		this.doctorId = id2;
		
	}

}