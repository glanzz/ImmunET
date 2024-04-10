package com.immunet.immunet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity extends BaseEntity {

	@Column(name="name", nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String contact;
	
	@Column(nullable=false, columnDefinition = "TEXT")
	private String password;
	
	@Column(columnDefinition = "TEXT")
	private String billingAddress;


	public UserEntity(String name, String contact, String password, String billingAddress) {
		super();
		this.name = name;
		this.password = password;
		this.contact = contact;
		this.billingAddress = billingAddress;
	}
	
	public UserEntity(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	public UserEntity() {
		
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


	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getBillingAddress() {
		return billingAddress;
	}


	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

}
