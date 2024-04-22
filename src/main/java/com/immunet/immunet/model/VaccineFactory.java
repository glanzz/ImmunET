package com.immunet.immunet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.immunet.immunet.entity.EntitySpecies;
import com.immunet.immunet.entity.VaccineEntity;
import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.repository.VaccineRepository;
import com.immunet.immunet.service.VaccineService;

/**
 * Factory class for creating Vaccine instances. This class utilizes services and repositories
 * to either fetch existing vaccine data or create new vaccine instances based on given parameters.
 */
@Component
public class VaccineFactory {

    @Autowired
    private VaccineService vaccineService;

    @Autowired
    private VaccineRepository vaccineRepository;

    public VaccineFactory() {
    }

    /**
     * Creates a Vaccine instance from a VaccineEntity.
     * 
     * @param vaccineEntity The entity containing vaccine data.
     * @return A fully populated Vaccine instance.
     * @throws BadRequest If there are inconsistencies in the entity data.
     */
    public Vaccine getVaccine(VaccineEntity vaccineEntity) throws BadRequest {
        Vaccine vaccine = new Vaccine();
        vaccine.load(vaccineEntity);
        return vaccine;
    }

    /**
     * Retrieves or creates a Vaccine instance based on provided parameters.
     * This method first attempts to find an existing vaccine matching the provided parameters.
     * If no matching vaccine is found, it creates a new one.
     * 
     * @param name The name of the vaccine.
     * @param species The species for which the vaccine is intended.
     * @param frequency The number of doses required.
     * @param intervalsCSV A comma-separated string detailing intervals between doses.
     * @param offset The start offset in days for the first dose from birth.
     * @param doctorId The identifier of the doctor associated with the vaccine.
     * @return A Vaccine instance, either loaded or newly created.
     * @throws BadRequest If any data is invalid or missing.
     */
    public Vaccine getVaccine(String name, Species species, int frequency, String intervalsCSV, int offset, int doctorId) throws BadRequest {
        VaccineEntity vaccineEntity = vaccineRepository.findDistinctByNameAndSpeciesAndDoctorId(name, EntitySpecies.valueOf(species.name()), doctorId);
        if (vaccineEntity != null) {
            // Load existing vaccine
            return this.getVaccine(vaccineEntity);
        } else {
            // Create a new vaccine instance
            return new Vaccine(vaccineService, name, species, frequency, intervalsCSV, offset);
        }
    }
}
