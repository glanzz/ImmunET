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
public class BillEntity extends BaseEntity {

	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private EntityStatus status = EntityStatus.PENDING;
	
	@Column(nullable=false)
	private Double taxPercent;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_for", referencedColumnName = "id", nullable=false)
    private PetEntity billFor;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_by", referencedColumnName = "id", nullable=false)
    private UserEntity billBy;
	
	public BillEntity(EntityStatus status, Double taxPercent) {
		super();
		this.status = status;
		this.taxPercent = taxPercent;
	}
	
	public EntityStatus getStatus() {
		return status;
	}

	public void setStatus(EntityStatus status) {
		this.status = status;
	}

	public Double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(Double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public PetEntity getBillFor() {
		return billFor;
	}

	public void setBillFor(PetEntity pet) {
		this.billFor = pet;
	}

	public UserEntity getBillBy() {
		return billBy;
	}

	public void setCreator(UserEntity creator) {
		this.billBy = creator;
	}


}
