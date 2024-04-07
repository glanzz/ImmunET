package com.immunet.immunet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name="doctors")
public class Doctor extends BaseEntity {

	private double serviceCost;
	
	@Column(nullable=false, columnDefinition = "TEXT")
	private String clinicAddress;

	public Doctor(double serviceCost, String clinicAddress) {
		super();
		this.serviceCost = serviceCost;
		this.clinicAddress = clinicAddress;
	}
	
	public Doctor(String clinicAddress) {
		super();
		this.clinicAddress = clinicAddress;
	}

	public double getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(double serviceCost) {
		this.serviceCost = serviceCost;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}
	

}
