package com.immunet.immunet.model;

import com.immunet.immunet.entity.UserEntity;

public class User extends Person {
	Integer id;
	String username;
	String password;
	String billingAddress;
	public User(String name, String address, String username, String password) {
		super(name, address);
		this.username = username;
		this.password = password;
		this.billingAddress = address;
	}
	
	public User() {
		super();
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		setAddress(billingAddress);
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
	
	public static boolean comparePassword(String password1, String password2) {
		return password1.compareTo(password2) == 0;
	}
	
	public void load(UserEntity user) {
		this.id = user.getId();
		this.setName(user.getName());
		this.setPassword(user.getPassword());
		this.setUsername(user.getContact());
		this.setBillingAddress(user.getBillingAddress());
	}

}
