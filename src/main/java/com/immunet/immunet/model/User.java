package com.immunet.immunet.model;

import java.util.Date;

public class User extends Person{
	String username;
	String password;
	String billingAddress;
	public User(String name, String address, Date dob, String username, String password) {
		super(name, address, dob);
		this.username = username;
		this.password = password;
	}
	
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	

}
