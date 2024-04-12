package com.immunet.immunet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="doctors")
public class DoctorEntity extends BaseEntity {

	private Double serviceCost;
	
	@OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable=false)
    private UserEntity userDetails;


	@Column(columnDefinition = "TEXT")
	private String clinicAddress;
	
	
	public DoctorEntity() {
		
	}

	public DoctorEntity(Double serviceCost, String clinicAddress) {
		super();
		this.serviceCost = serviceCost;
		this.clinicAddress = clinicAddress;
	}
	
	public DoctorEntity(String clinicAddress) {
		super();
		this.clinicAddress = clinicAddress;
	}

	public Double getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(Double serviceCost) {
		this.serviceCost = serviceCost;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}
	
	public UserEntity getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserEntity user) {
		this.userDetails = user;
	}
	

}
