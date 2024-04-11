package com.immunet.immunet.entity;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="vaccines")
public class VaccineEntity extends BaseEntity {
	public VaccineEntity(String name, Integer frequency, Integer offset, boolean isDefault, String intervals,
			double cost) {
		super();
		this.name = name;
		this.frequency = frequency;
		this.offset = offset;
		this.isDefault = isDefault;
		this.intervals = intervals;
		this.cost = cost;
	}
	
	public VaccineEntity() {}
	
	public VaccineEntity(String name, String intervals, EntitySpecies species,
			double cost) {
		super();
		this.name = name;
		this.intervals = intervals;
		this.species = species;
		this.cost = cost;
	}
	
	public VaccineEntity(String name, String intervals, EntitySpecies species, boolean isDefault,
			double cost) {
		super();
		this.name = name;
		this.intervals = intervals;
		this.isDefault = isDefault;
		this.species = species;
		this.cost = cost;
	}


	@NonNull
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(nullable=false)
	private Integer frequency = 1;
	
	@Column(nullable=false)
	private Integer offset = 0;
	
	@Column(nullable=false)
	private boolean isDefault = false;
	
	@Column(nullable=false)
	private String intervals;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private EntitySpecies species;
	
	@Column(nullable=false)
	private double cost;
	
	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable=false)
    private DoctorEntity doctor;

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getIntervals() {
		return intervals;
	}

	public void setIntervals(String intervals) {
		this.intervals = intervals;
	}

	public EntitySpecies getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = EntitySpecies.valueOf(species);
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	

}
