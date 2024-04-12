package com.immunet.immunet.model;

import java.util.Date;

import com.immunet.immunet.entity.ScheduleEntity;
import com.immunet.immunet.exception.BadRequest;

public class Schedule {
	Integer id;
	Date scheduledDate;
	 //static Date scheduledDate= new Date(124, 02, 03 );
	Date administeredDate;
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
		

	public void markComplete(Doctor d) throws BadRequest {
		Date todayDate= new Date();
		if (isComplete()) {
			throw new BadRequest("Schedule is already completed");	
		} else if (isDelayed() || status==ImmunizationStatus.PENDING) {
			setAdministeredDate(todayDate);
			this.setDoctor(d);
		}	
	}
	
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