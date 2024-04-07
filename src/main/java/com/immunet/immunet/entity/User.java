package com.immunet.immunet.entity;

import java.util.Date;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User extends BaseEntity {
	@Id
	@NonNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name", nullable=false)
	private String name;
	
	@Column(nullable=false, columnDefinition = "TEXT")
	private String password;
	
	@Column(name="billing_address", columnDefinition = "TEXT")
	private String billingAddress;


	public User(String name, String password, String billingAddress) {
		this.name = name;
		this.password = password;
		this.billingAddress = billingAddress;
	}


	public Integer getId() {
		return id;
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


	public String getBillingAddress() {
		return billingAddress;
	}


	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

}
