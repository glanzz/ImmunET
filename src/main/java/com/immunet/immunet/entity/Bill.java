package com.immunet.immunet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="bills")
public class Bill extends BaseEntity {

	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;
	
	@Column(nullable=false)
	private Double taxPercent;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_for", referencedColumnName = "id", nullable=false)
    private Pet billFor;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_by", referencedColumnName = "id", nullable=false)
    private User billBy;
	
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

	public Pet getBillFor() {
		return billFor;
	}

	public void setBillFor(Pet pet) {
		this.billFor = pet;
	}

	public User getBillBy() {
		return billBy;
	}

	public void setCreator(User creator) {
		this.billBy = creator;
	}


}
