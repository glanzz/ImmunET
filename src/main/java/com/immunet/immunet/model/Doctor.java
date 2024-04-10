package com.immunet.immunet.model;

import java.util.Date;

public class Doctor extends User {
	String clinicAddress;
	float serviceCost;

	public Doctor(String name, String address, Date dob, String username, String password, String clinicAddress,float serviceCost) {
		super(name, address, dob, username, password);
		this.clinicAddress = clinicAddress;
		this.serviceCost = serviceCost;
	}
	public Doctor(String name, String address, Date dob, String username, String password) {
		super(name, address, dob, username, password);
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}
	
	public float getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(float serviceCost) {
		this.serviceCost = serviceCost;
	}
	

}
