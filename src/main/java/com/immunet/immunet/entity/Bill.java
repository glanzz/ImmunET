package com.immunet.immunet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name="bills")
public class Bill extends BaseEntity {

	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;
	
	@Column(nullable=false)
	private Double taxPercent;
	
	public Bill(Status status, Double taxPercent) {
		super();
		this.status = status;
		this.taxPercent = taxPercent;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(Double taxPercent) {
		this.taxPercent = taxPercent;
	}


}
