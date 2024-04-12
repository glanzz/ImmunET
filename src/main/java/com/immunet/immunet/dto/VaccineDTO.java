package com.immunet.immunet.dto;

import java.util.List;

import com.immunet.immunet.model.Vaccine;

public class VaccineDTO {
	Integer id;
	String name;
	Integer frequency;
	Integer offset;
	List<Integer> intervals;
	String species;
	boolean is_default;
	public VaccineDTO(Vaccine vaccine) {
		super();
		this.id = vaccine.getId();
		this.name = vaccine.getName();
		this.frequency = vaccine.getFrequency();
		this.offset = vaccine.getOffset();
		this.intervals = vaccine.getIntervals();
		this.species = vaccine.getSpecies().name();
		this.is_default = vaccine.isDefaultVaccine();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public List<Integer> getIntervals() {
		return intervals;
	}
	public void setIntervals(List<Integer> intervals) {
		this.intervals = intervals;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public boolean isIs_default() {
		return is_default;
	}
	public void setIs_default(boolean is_default) {
		this.is_default = is_default;
	}
	
	

}
