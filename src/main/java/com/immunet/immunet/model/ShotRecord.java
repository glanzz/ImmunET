/**
 * Abstract class representing a generic shot (vaccination) record. This class provides the framework for managing 
 * vaccination records, which includes scheduling, tracking, and marking vaccinations as complete.
 * Implementations of this class should define specific behavior appropriate for different types of vaccinations,
 * such as single-shot or multi-shot vaccines.
 */
package com.immunet.immunet.model;

import java.util.Date;
import java.util.List;

import com.immunet.immunet.entity.ScheduleEntity;
import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.exception.NotFound;
    /** Abstract Class */
public abstract class ShotRecord {

    /**
     * Retrieves the vaccine associated with this shot record.
     * 
     * @return the vaccine associated with this record
     */
    public abstract Vaccine getVaccine();

    /**
     * Retrieves a list of schedules associated with this shot record. 
     * Depending on the implementation, this could include multiple schedules for multi-shot vaccines.
     * 
     * @return a list of schedules for the vaccine shots
     */
    public abstract List<Schedule> getSchedules();

    /**
     * Determines if the vaccination schedule (or all schedules) associated with this record are complete.
     * 
     * @return true if all required vaccinations in the schedule are complete, false otherwise
     */
    public abstract boolean isComplete();

    /**
     * Retrieves a list of shot records, potentially transformed into a specific data transfer format.
     * 
     * @return a list of shot records, possibly containing more detailed or specific information
     */
    public abstract List<ShotRecord> getShotDTOs();

    /**
     * Generates a vaccination schedule based on the date of birth of the patient.
     * Implementations should calculate the appropriate dates for vaccination based on specific vaccine requirements.
     * 
     * @param dob the date of birth of the patient for whom the schedule is to be generated
     */
    public abstract void generateSchedule(Date dob);

    /**
     * Adds a new schedule to this vaccination record.
     * Implementations should handle adding and potentially merging schedules in the context of the specific vaccine type.
     * 
     * @param s the schedule to be added to this record
     */
    public abstract void addSchedule(Schedule s);

    /**
     * Marks a specific vaccination schedule as complete, if the specified schedule ID matches an existing schedule.
     * Implementations must handle the verification of schedule ID and the completion process.
     *
     * @param scheduleId the ID of the schedule to be marked as complete
     * @param signedDoctor the doctor who is signing off on the completion of the vaccination
     * @throws NotFound if the schedule with the specified ID does not exist
     * @throws BadRequest if there are issues processing the request or marking the schedule as complete
     */
    protected abstract void markComplete(int scheduleId, Doctor signedDoctor) throws NotFound, BadRequest;

}
