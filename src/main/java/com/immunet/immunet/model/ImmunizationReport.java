/**
 * Represents an immunization report for a pet, encapsulating all the vaccination records associated with that pet.
 * This class interacts with the {@code ImmunizationReportService} to load and manage these records, ensuring that
 * the immunization status of the pet is accurately maintained and accessible.
 */
package com.immunet.immunet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.exception.NotFound;
import com.immunet.immunet.service.ImmunizationReportService;
import java.util.Objects;

public class ImmunizationReport {
    // API design - No single and multishot record
    private List<ShotRecord> shotRecords;
    private Pet pet;
    private ImmunizationReportService service;

    /**
     * Constructs an ImmunizationReport for a specified pet. This constructor initializes the report,
     * potentially loading existing records if the pet is persisted or loading default records otherwise.
     *
     * @param service the service used to interact with the data layer for loading and saving immunization data
     * @param pet the pet for which the immunization report is to be created
     * @throws BadRequest if there are issues loading the pet's records
     */
    public ImmunizationReport(ImmunizationReportService service, Pet pet) throws BadRequest {
        this.pet = pet;
        this.service = service;
        this.shotRecords = new ArrayList<>();
        if (pet.isPersisted()) {
            this.load();
        } else {
            this.loadDefaultRecords(pet.getCreatorID());
        }
    }

    /**
     * Retrieves the pet associated with this immunization report.
     *
     * @return the pet
     */
    public Pet getPet() {
        return this.pet;
    }

    /**
     * Retrieves the list of shot records for the pet.
     *
     * @return the list of shot records
     */
    public List<ShotRecord> getShotRecords() {
        return this.shotRecords;
    }

    /**
     * Loads the immunization records for the pet from the repository.
     *
     * @throws BadRequest if there are issues retrieving the records
     */
    public void load() throws BadRequest {
        service.getExistingSchedules(pet).forEach(this.shotRecords::add);
    }

    /**
     * Saves the current state of the immunization report to the repository.
     *
     * @param userId the identifier of the user performing the save operation
     */
    public void save(Integer userId) {
        service.save(this, userId);
    }

    /**
     * Adds a shot record for the specified vaccine to the pet's immunization report.
     *
     * @param vaccine the vaccine to add a shot record for
     * @throws BadRequest if a record for the vaccine already exists
     */
    public void addShotRecord(Vaccine vaccine) throws BadRequest {
        // Exception handling
        if (shotRecordExists(vaccine)) {
            throw new BadRequest("Shot record for vaccine " + vaccine.getName() + " already exists.");
        }
        ShotRecord newRecord = ShotFactory.getInstance().getShotRecord(vaccine, this.pet.getDob());
        this.shotRecords.add(newRecord);
    }

    /**
     * Loads default immunization records based on the vaccines typically administered to pets of this type.
     *
     * @param doctorId the identifier of the doctor or creator initializing these records
     * @throws BadRequest if there is an issue initializing the records
     */
    public void loadDefaultRecords(Integer doctorId) throws BadRequest {
        List<Vaccine> defaultVaccines = service.getDefaultVaccines(pet);
        for (Vaccine vaccine : defaultVaccines) {
            this.addShotRecord(vaccine);
        }
    }

    /**
     * Checks if a shot record already exists for a given vaccine.
     *
     * @param vaccine the vaccine to check
     * @return true if a shot record exists, false otherwise
     */
    private boolean shotRecordExists(Vaccine vaccine) {
        return this.shotRecords.stream().anyMatch(record -> Objects.equals(record.getVaccine().getId(), vaccine.getId()));
    }

    /**
     * Completes a shot given a specific schedule ID, marking it as done by the signed doctor.
     *
     * @param scheduleId the identifier of the schedule to mark as complete
     * @param signedDoctor the doctor who is signing off on the shot
     * @throws NotFound if no matching schedule is found
     * @throws BadRequest if there is an issue marking the shot as complete
     */
    public void completeShot(int scheduleId, Doctor signedDoctor) throws NotFound, BadRequest {
        for (ShotRecord shotRecord : this.shotRecords) {
            Schedule s = shotRecord.getSchedules().stream()
                .filter(schedule -> schedule.getId().equals(scheduleId))
                .findFirst()
                .orElseThrow(() -> new NotFound("No Schedule found with the given ID"));
            shotRecord.markComplete(scheduleId, signedDoctor);
        }
    }

    /**
     * Retrieves a list of shot records that have been completed up until the current date.
     *
     * @return a list of completed shot records
     */
    public List<ShotRecord> getCompletedShotsTillDate() {
        // Method reference
        return shotRecords.stream()
            .filter(ShotRecord::isComplete)
            .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of shot records that are scheduled for today.
     *
     * @return a list of shot records scheduled for today
     */
    public List<ShotRecord> getTodaysShots() {
        // Streams usage
        Date today = new Date(); // Today's date
        return shotRecords.stream().filter(record -> {
            return record.getSchedules().stream().anyMatch(schedule -> schedule.getScheduledDate().equals(today));
        }).collect(Collectors.toList());
    }

    /**
     * Retrieves a list of shot records that were completed today.
     *
     * @return a list of shot records completed today
     */
    public List<ShotRecord> getTodaysCompletedShots() {
        Date today = new Date(); // Today's date
        return shotRecords.stream().filter(record -> {
            return record.getSchedules().stream().anyMatch(schedule -> schedule.getScheduledDate().equals(today) && schedule.isComplete());
        }).collect(Collectors.toList());
    }

    /**
     * Retrieves a list of shot records that are scheduled for future dates.
     *
     * @return a list of upcoming shot records
     */
    public List<ShotRecord> getUpcomingScheduledShots() {
        Date today = new Date(); // Today's date
        return shotRecords.stream().filter(record -> {
            return record.getSchedules().stream().anyMatch(schedule -> schedule.getScheduledDate().after(today));
        }).collect(Collectors.toList());
    }
}
