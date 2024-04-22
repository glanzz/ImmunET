package com.immunet.immunet.model;

import java.util.Date;
import java.util.List;

import com.immunet.immunet.entity.OwnerEntity;
import com.immunet.immunet.entity.UserEntity;
import com.immunet.immunet.service.OwnerService;

/**
 * Represents an owner of a pet, extending the {@code Person} class. This class includes methods for
 * managing the owner's data persistence through the {@code OwnerService}.
 */
public class Owner extends Person {
    private Integer id;
    private OwnerService service;

    /**
     * Constructor for creating an Owner instance with specified service, name, and address.
     *
     * @param service the service to interact with the data layer for Owner-specific operations
     * @param name the name of the owner
     * @param address the address of the owner
     */
    public Owner(OwnerService service, String name, String address) {
        super(name, address);
        this.service = service;
    }

    /**
     * Constructor for creating an Owner instance with a specified service. This constructor is typically
     * used when the name and address are set later in the lifecycle.
     *
     * @param service the service to interact with the data layer for Owner-specific operations
     */
    public Owner(OwnerService service) {
        this.service = service;
    }

    /**
     * Retrieves the unique identifier for the owner.
     *
     * @return the unique identifier of the owner
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Saves the owner's data to the database. If the owner is already persisted, this method does nothing.
     *
     * @param userId the user ID associated with the action, used for tracking who saved the owner
     */
    public void save(Integer userId) {
        if (isPersisted()) {
            return;
        }
        this.id = service.save(this, userId);
    }

    /**
     * Checks if the owner's data is already persisted in the database.
     *
     * @return true if the owner is persisted, otherwise false
     */
    public boolean isPersisted() {
        return this.id != null;
    }

    /**
     * Loads the owner's data from an {@code OwnerEntity} object. This method sets the owner's internal
     * state to match that of the specified entity.
     *
     * @param e the owner entity whose data is to be loaded into this owner
     */
    public void load(OwnerEntity e) {
        this.id = e.getId();
        this.setName(e.getName());
        this.setAddress(e.getAddress());
    }

    // Method for displaying pets commented out for brevity and because it's not implemented
    // public List<Pet> displayPets() {
    //     return Pet.getPets();
    // }

}
