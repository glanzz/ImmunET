package com.immunet.immunet.entity;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="pets")
public class PetEntity extends BaseEntity {
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private Date dob;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private EntityGender gender;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private EntitySpecies species;
	
	
	@Column(name="owner_id", nullable=false)
	Integer ownerId;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id",insertable=false, updatable=false,  nullable=false)
    private OwnerEntity owner;
	
	@Column(name="created_by", nullable=false)
	Integer createdBy;

	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "created_by", insertable=false, updatable=false, nullable=false)
    private UserEntity creator;

	public PetEntity(String name, Date dob, EntityGender gender, EntitySpecies species) {
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.species = species;
	}
	
	public PetEntity() {
		
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public EntityGender getGender() {
		return gender;
	}
	public void setGender(EntityGender gender) {
		this.gender = gender;
	}
	public EntitySpecies getSpecies() {
		return species;
	}
	public void setSpecies(EntitySpecies species) {
		this.species = species;
	}
	public OwnerEntity getOwner() {
		return owner;
	}


	public void setOwner(OwnerEntity owner) {
		this.owner = owner;
	}


	public UserEntity getCreator() {
		return creator;
	}


	public void setCreator(UserEntity user) {
		this.creator = user;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	
	

}
