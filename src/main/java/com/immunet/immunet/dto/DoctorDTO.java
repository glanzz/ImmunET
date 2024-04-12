package com.immunet.immunet.dto;

import jakarta.validation.constraints.NotNull;

public class DoctorDTO {
	@NotNull
	String name;
	
	@NotNull
	Integer id;
	
	String clinicAddress;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}
	
}
