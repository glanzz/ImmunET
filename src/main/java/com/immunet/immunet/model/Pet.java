package com.immunet.immunet.model;

import java.util.Date;
import com.immunet.immunet.entity.PetEntity;
import com.immunet.immunet.service.PetService;

/**
 * Represents a pet, containing details such as name, date of birth, gender, species, and ownership.
 * This class is responsible for managing the pet's lifecycle, including saving and loading its details
 * through a {@code PetService}.
 */
public class Pet {
    public static enum Gender {
        MALE,
        FEMALE;
    }

    private Integer id;
    private String name;
    private Date dob;
    private Gender gender;
    private Species species;
    private Integer creatorID;
    private Integer ownerId;
    private PetService service;

    /**
     * Constructor to create a pet with all its attributes specified.
     *
     * @param service The service responsible for pet data persistence.
     * @param name The name of the pet.
     * @param dob The date of birth of the pet.
     * @param gender The gender of the pet.
     * @param species The species of the pet.
     * @param creatorId The ID of the creator or registrant of the pet.
     */
    public Pet(PetService service, String name, Date dob, Gender gender, Species species, Integer creatorId) {
        this.service = service;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.species = species;
        this.creatorID = creatorId;
    }

    /**
     * Constructor to create a pet object when no initial attributes are specified.
     *
     * @param service The service responsible for pet data persistence.
     */
    public Pet(PetService service) {
        this.service = service;
    }

    /**
     * Determines if the pet's information is persisted in the database.
     *
     * @return true if the pet has an assigned ID and hence is persisted, false otherwise.
     */
    public boolean isPersisted() {
        return this.id != null;
    }

    /**
     * Saves the pet to the database using the provided service, setting its owner and creator IDs.
     *
     * @param ownerId The ID of the owner of the pet.
     * @param creatorId The ID of the creator or registrant of the pet.
     */
    public void save(Integer ownerId, Integer creatorId) {
        if (isPersisted()) {
            return;
        }
        this.id = service.save(this, ownerId, creatorId);
    }

    /**
     * Loads the pet's data from a {@code PetEntity} instance into this pet object.
     *
     * @param pet The entity from which to load the data.
     */
    public void load(PetEntity pet) {
        this.id = pet.getId();
        this.creatorID = pet.getCreator().getId();
        this.name = pet.getName();
        this.dob = pet.getDob();
        this.gender = Gender.valueOf(pet.getGender().name());
        this.ownerId = pet.getOwnerId();
        this.species = Species.valueOf(pet.getSpecies().name());
    }

    // Getters and setters for each field
    
    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species s) {
        this.species = s;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(Integer creatorID) {
        this.creatorID = creatorID;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
