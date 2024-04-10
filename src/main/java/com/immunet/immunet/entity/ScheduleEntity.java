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
public class ScheduleEntity extends BaseEntity {
	
	

	@Column( nullable=false)
	private Date scheduleDate;
	
	private Date takenDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", referencedColumnName = "id", nullable=false)
    private PetEntity pet;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccine_id", referencedColumnName = "id", nullable=false)
    private VaccineEntity vaccine;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "given_by", referencedColumnName = "id", nullable=false)
    private DoctorEntity doctor;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable=false)
    private UserEntity user;
	
	public ScheduleEntity(Date scheduleDate, VaccineEntity v) {
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
	
	public PetEntity getPet() {
		return pet;
	}

	public void setPet(PetEntity pet) {
		this.pet = pet;
	}

	public VaccineEntity getVaccine() {
		return vaccine;
	}

	public void setVaccine(VaccineEntity vaccine) {
		this.vaccine = vaccine;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	
	
}
