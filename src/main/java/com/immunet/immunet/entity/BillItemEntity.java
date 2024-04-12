package com.immunet.immunet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="bill_items")
public class BillItemEntity extends BaseEntity {
	
	@Column(nullable=false)
	private Double cost;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String type;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", referencedColumnName = "id", nullable=false)
    private BillEntity bill;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable=false)
    private DoctorEntity doctor;

	public BillItemEntity(Double cost, String name, String type) {
		super();
		this.cost = cost;
		this.name = name;
		this.type = type;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BillEntity getBill() {
		return bill;
	}

	public void setBill(BillEntity bill) {
		this.bill = bill;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}
	
}
