package com.immunet.immunet.dto;

import jakarta.validation.constraints.NotNull;

public class CreateDoctorDTO {
	@NotNull
	String name;
	@NotNull
	String password;
	@NotNull
	String rePassword;
	@NotNull
	String contact;
	String address = null;
	Double serviceCost = null;
	public CreateDoctorDTO(String name, String password, String rePassword, String contact) {
		super();
		this.name = name;
		this.password = password;
		this.rePassword = rePassword;
		this.contact = contact;
	}
	public CreateDoctorDTO() {
		
	}
	public CreateDoctorDTO(String name, String password, String rePassword, String contact, String address,
			Double serviceCost) {
		super();
		this.name = name;
		this.password = password;
		this.rePassword = rePassword;
		this.contact = contact;
		this.address = address;
		this.serviceCost = serviceCost;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(Double serviceCost) {
		this.serviceCost = serviceCost;
	}

}
