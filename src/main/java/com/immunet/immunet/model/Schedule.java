package com.immunet.immunet.model;

import java.util.Date;

import com.immunet.immunet.entity.ScheduleEntity;
import com.immunet.immunet.exception.BadRequest;

public class Schedule {
	Integer id;
	Date scheduledDate;
	 //static Date scheduledDate= new Date(124, 02, 03 );
	Date administeredDate;
        
        /**
        * Enum for tracking the status of immunization: whether it's pending, delayed, or complete.
        */
	public static enum ImmunizationStatus {
		PENDING,
		DELAYED,
		COMPLETE;
	}
	Integer doctorId;
	Doctor doctor; 
	ImmunizationStatus status= ImmunizationStatus.PENDING; //Initial status
	
	public Schedule() {
		
	}
	
        /**
        * Constructor to create a schedule with defined scheduled and administered dates.
        * Automatically updates the status based on these dates.
        * @param scheduledDate The planned date for the immunization.
        * @param administeredDate The actual date the immunization was administered.
        */
	public Schedule(Date scheduledDate, Date administeredDate) {
		this.scheduledDate = scheduledDate;
		this.administeredDate = administeredDate;
		updateStatus();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
	
	public Integer getDoctorId() {
		return this.doctorId;
	}
	
        /**
        * Updates the status of the immunization based on the current date and the scheduled and administered dates.
        */
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
			setStatus(ImmunizationStatus.PENDING);
		} else {
			setStatus(ImmunizationStatus.DELAYED);
		}
		
	}
	
	
	
	
	public boolean isDelayed() {
		return status == ImmunizationStatus.DELAYED;
		
	}
	
	public boolean isComplete() {
		return status == ImmunizationStatus.COMPLETE;
	}
		

        /**
        * Marks the immunization as complete and assigns the doctor who administered it. Throws an exception if already complete.
        * @param doctor The doctor administering the immunization.
        * @throws BadRequest if the schedule is already marked as complete.
        */
	public void markComplete(Doctor d) throws BadRequest {
		Date todayDate= new Date();
		if (isComplete()) {
			throw new BadRequest("Schedule is already completed");	
		} else if (isDelayed() || status==ImmunizationStatus.PENDING) {
			setAdministeredDate(todayDate);
			this.setDoctor(d);
		}	
	}
	
        /**
        * Loads schedule data from a database entity.
        * @param s The ScheduleEntity instance containing the data.
        * @return The Schedule instance populated with data from the entity.
        */
	public static Schedule load(ScheduleEntity s) {
		Schedule schedule = new Schedule();
		schedule.id = s.getId();
		schedule.setScheduledDate(s.getScheduleDate());
		schedule.setAdministeredDate(s.getTakenDate());
		if (s.getDoctor() != null) {
			schedule.setDoctorId(s.getDoctor().getId());
			Doctor adminDoctor = new Doctor();
			adminDoctor.load(s.getDoctor());
			schedule.setDoctor(adminDoctor);
		}
		return schedule;
	}

	private void setDoctorId(Integer id2) {
		this.doctorId = id2;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		if(this.doctor == null) {
			this.setDoctorId(null);
		} else {
			this.setDoctorId(doctor.getId());
		}
		this.doctor = doctor;
	}

}