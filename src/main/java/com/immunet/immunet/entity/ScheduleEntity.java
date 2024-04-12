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
	
	
	@Column(name="pet_id", nullable=false)
	Integer petId;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", insertable=false, updatable=false,  nullable=false)
    private PetEntity pet;
	
	@Column(name="vaccine_id", nullable=false)
	Integer vaccineId;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccine_id", insertable=false, updatable=false, nullable=false)
    private VaccineEntity vaccine;
	
	@Column(name="given_by")
	Integer doctorId;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "given_by",insertable=false, updatable=false)
    private DoctorEntity doctor;
	
	@Column(name="created_by", nullable=false)
	Integer createdBy;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", insertable=false, updatable=false, nullable=false)
    private UserEntity user;
	
	public ScheduleEntity(Date scheduleDate, VaccineEntity v) {
		this.scheduleDate = scheduleDate;
		this.vaccine = v;
	}
	
	public ScheduleEntity() {
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

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public Integer getVaccineId() {
		return vaccineId;
	}

	public void setVaccineId(Integer vaccineId) {
		this.vaccineId = vaccineId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	
	
}
