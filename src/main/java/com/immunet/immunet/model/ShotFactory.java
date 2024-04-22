/**
 * A factory class for creating {@code ShotRecord} objects based on vaccine properties and patient information.
 * This class employs the Singleton pattern to ensure that only one instance of the factory is used throughout
 * the application, facilitating the centralized creation of shot records.
 */
package com.immunet.immunet.model;

import java.util.Date;
import org.springframework.stereotype.Component;
import com.immunet.immunet.entity.ScheduleEntity;

@Component
public class ShotFactory {

    private static ShotFactory instance = new ShotFactory();

    /**
     * Private constructor to prevent instantiation from outside the class and support singleton pattern.
     */
    private ShotFactory() {}

    /**
     * Provides access to the single instance of the {@code ShotFactory} class, ensuring a consistent point of access.
     *
     * @return the single, static instance of the ShotFactory
     */
    public static ShotFactory getInstance() {
        return instance;
    }

    /**
     * Creates a {@code ShotRecord} for a given vaccine and date of birth. The method determines the appropriate 
     * type of {@code ShotRecord} to create (single or multiple shot) based on whether the vaccine requires multiple shots.
     *
     * @param vaccine the vaccine for which a shot record is needed
     * @param dob the date of birth of the patient, used for scheduling purposes
     * @return a {@code ShotRecord} that is either a {@code SingleShotRecord} or a {@code MultiShotRecord}, depending on the vaccine
     */
    public ShotRecord getShotRecord(Vaccine vaccine, Date dob) {
        if (vaccine.requiresMultipleShots()) {
            return new MultiShotRecord(vaccine, dob);
        } else {
            return new SingleShotRecord(vaccine, dob);
        }
    }

    /**
     * Creates a {@code ShotRecord} for a given vaccine without requiring a date of birth. 
     * This method also determines the type of {@code ShotRecord} based on whether the vaccine requires multiple shots.
     * 
     * @param v the vaccine for which a shot record is needed
     * @return a {@code ShotRecord} that is either a {@code SingleShotRecord} or a {@code MultiShotRecord}, depending on the vaccine
     */
    public ShotRecord getShotRecord(Vaccine v) {
        // Factory method to determine shot
        if (v.requiresMultipleShots()) {
            return new MultiShotRecord(v);
        } else {
            return new SingleShotRecord(v);
        }
    }
}
