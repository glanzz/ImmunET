package com.immunet.immunet.entity;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="owners")
public class OwnerEntity extends BaseEntity {

	@Column(name="name", nullable=false)
	private String name;
	
	private Date dob;
	
	@Enumerated(EnumType.STRING)
	private EntityGender gender;
	
	@Column(nullable=false, columnDefinition = "TEXT")
	private String address;
	
	
	@Column(name="created_by", nullable=false)
	private Integer createdBy;

	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "created_by", insertable=false, updatable=false, nullable=false)
	private UserEntity user;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	OwnerEntity(){
		
	}
	public OwnerEntity(String name, Date dob, EntityGender gender, String address) {
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
	}
	
	public OwnerEntity(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public EntityGender getGender() {
		return gender;
	}

	public void setGender(EntityGender gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}


}
