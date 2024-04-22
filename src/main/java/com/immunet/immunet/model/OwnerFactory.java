package com.immunet.immunet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.immunet.immunet.entity.OwnerEntity;
import com.immunet.immunet.service.OwnerService;

/**
 * Factory class responsible for creating and retrieving {@code Owner} instances.
 * This class leverages the {@code OwnerService} to check for existing owner records or to create new ones.
 */
@Component
public class OwnerFactory {
    
    @Autowired
    private OwnerService service;

    /**
     * Retrieves an {@code Owner} instance using the provided name and address. If an existing owner is found in the database,
     * it loads the owner data into an {@code Owner} object; otherwise, it creates a new {@code Owner} with the provided details.
     *
     * @param name the name of the owner
     * @param address the address of the owner
     * @return an {@code Owner} object populated with the retrieved or new data
     */
    public Owner getOwner(String name, String address) {
        OwnerEntity existingOwner = service.getExistingOwner(name, address);
        if (existingOwner != null) {
            Owner owner = new Owner(service);
            owner.load(existingOwner);
            return owner;
        }
        return new Owner(service, name, address);
    }

    /**
     * Creates an {@code Owner} instance based on a provided {@code OwnerEntity}. This method directly loads the data from
     * an {@code OwnerEntity} into a new {@code Owner} object.
     *
     * @param entity the {@code OwnerEntity} containing the owner's data
     * @return an {@code Owner} object populated with data from the provided entity
     */
    public Owner getOwner(OwnerEntity entity) {
        Owner owner = new Owner(service);
        owner.load(entity);
        return owner;
    }
}
