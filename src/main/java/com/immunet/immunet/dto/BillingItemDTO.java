package com.immunet.immunet.dto;

public class BillingItemDTO {
	String name;
	float price;
	String type;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BillingItemDTO(String name, float price, String type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	
}
