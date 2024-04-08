package com.immunet.immunet.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="schedules")
public class Schedule extends BaseEntity {
	
	

	@Column( nullable=false)
	private Date scheduleDate;
	
	private Date takenDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", referencedColumnName = "id", nullable=false)
    private Pet pet;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccine_id", referencedColumnName = "id", nullable=false)
    private Vaccine vaccine;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "given_by", referencedColumnName = "id", nullable=false)
    private Doctor doctor;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable=false)
    private User user;
	
	public Schedule(Date scheduleDate, Vaccine v) {
		this.scheduleDate = scheduleDate;
		this.vaccine = v;
	}
	
	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public Date getTakenDate() {
		return takenDate;
	}

	public void setTakenDate(Date takenDate) {
		this.takenDate = takenDate;
	}
	
	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Vaccine getVaccine() {
		return vaccine;
	}

	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
