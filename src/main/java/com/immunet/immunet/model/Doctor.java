package com.immunet.immunet.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.immunet.immunet.entity.DoctorEntity;
import com.immunet.immunet.service.DoctorService;

@Component
public class Doctor extends User {

	@Autowired
	DoctorService doctorService;

	Integer id = null;

	String clinicAddress;
	Double serviceCost = 0.0;
	
	public Doctor() {
		super();
	}
	public Doctor(String name, String address, String username, String password, Double serviceCost) {
		super(name, address, username, password);
		setClinicAddress(address);
		setServiceCost(serviceCost);
	}

	public Doctor(String name, String address, String username, String password) {
		super(name, address, username, password);
	}
	
	public Integer getId() {
		return id;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		// Using the user address as billing and clinic address
		this.setAddress(clinicAddress);
		this.setBillingAddress(clinicAddress);
		this.clinicAddress = clinicAddress;
	}
	
	public Double getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(Double serviceCost) {
		// If service cost < 1 raise error
		this.serviceCost = serviceCost;
	}
	
	private boolean isPersisted() {
		return this.id != null;
	}
	
	public void save() {
		if (isPersisted()) {
			return;
		}
		this.id = doctorService.save(this);
	}
	
	public void load(DoctorEntity doctor) {
		super.load(doctor.getUserDetails());
		this.id = doctor.getId();
		setClinicAddress(doctor.getClinicAddress());
		setServiceCost(doctor.getServiceCost());
	}

}
