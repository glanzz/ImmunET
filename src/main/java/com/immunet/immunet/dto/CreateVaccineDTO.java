package com.immunet.immunet.dto;
import jakarta.validation.constraints.NotNull;

public class CreateVaccineDTO {
	
	@NotNull
	String name;
	
	@NotNull
	Integer frequency = 1;
	
	@NotNull
	Double cost;
	
	@NotNull
	Integer offset = 1;
	
	@NotNull
	String intervals;
	
	@NotNull
	String species;
	
	@NotNull
	boolean mandatory = false;


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

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getIntervals() {
		return intervals;
	}

	public void setIntervals(String intervals) {
		this.intervals = intervals;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	
	
}
