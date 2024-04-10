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
	
	@Column( nullable=false)
	private Date dob;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(nullable=false, columnDefinition = "TEXT")
	private String address;
	
	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable=false)
	private UserEntity user;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public OwnerEntity(String name, Date dob, Gender gender, String address) {
		this.name = name;
		this.dob = dob;
		this.gender = gender;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}
