package com.immunet.immunet.model;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.immunet.immunet.entity.PetEntity;
import com.immunet.immunet.model.Pet.Gender;
import com.immunet.immunet.service.PetService;

/**
 * Factory class for creating {@code Pet} instances. This class uses {@code PetService} for pet data operations
 * and encapsulates the logic for pet object creation, ensuring that pets are properly initialized before use.
 */
@Component
public class PetFactory {
    
    @Autowired
    private PetService service;

    /**
     * Creates a new {@code Pet} instance with no initial attributes. This method is typically used when the pet's
     * details are set post-creation.
     *
     * @return a new instance of {@code Pet}
     */
    public Pet getPet() {
        return new Pet(service);
    }

    /**
     * Creates a new {@code Pet} instance with specified attributes. This is the fully specified constructor that
     * initializes all attributes of the pet at the time of creation.
     *
     * @param name the name of the pet
     * @param dob the date of birth of the pet
     * @param gender the gender of the pet
     * @param species the species of the pet
     * @param creatorId the identifier of the creator or owner of the pet at creation
     * @return a new instance of {@code Pet} with the specified attributes
     */
    public Pet getPet(String name, Date dob, Gender gender, Species species, Integer creatorId) {
        return new Pet(service, name, dob, gender, species, creatorId);
    }

    /**
     * Creates a {@code Pet} instance based on a provided {@code PetEntity}. This method loads the pet's details from
     * an existing {@code PetEntity}, which is typically retrieved from a database or external data source.
     *
     * @param p the {@code PetEntity} containing the pet's data
     * @return a new instance of {@code Pet} populated with data from the provided {@code PetEntity}
     */
    public Pet getPet(PetEntity p) {
        Pet pet = new Pet(service);
        pet.load(p);
        return pet;
    }

}
