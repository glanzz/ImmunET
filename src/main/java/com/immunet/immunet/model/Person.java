package com.immunet.immunet.model;

import java.util.Date;

public class Person {
	String name;
	String address;
	Date dob;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Person(String name, String address, Date dob) {
		super();
		this.name = name;
		this.address = address;
		this.dob = dob;
	}


}
