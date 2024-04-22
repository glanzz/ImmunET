/**
 * Represents a single-shot vaccination record for a patient within the Immunet system. 
 * This class extends {@code ShotRecord}, specifically designed for vaccines that require 
 * only a single administration. It manages scheduling and tracking the completion of the vaccination.
 */
package com.immunet.immunet.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.exception.NotFound;

public class SingleShotRecord extends ShotRecord {
    private Vaccine vaccine;
    private Schedule schedule;

    /**
     * Constructs a SingleShotRecord with the specified vaccine and the date of birth of the patient.
     * It initializes the record and automatically generates a vaccination schedule based on the patient's date of birth.
     *
     * @param vaccine the vaccine to be administered
     * @param dob the date of birth of the patient, used to calculate the schedule
     */
    public SingleShotRecord(Vaccine vaccine, Date dob) {
        this.vaccine = vaccine;
        this.generateSchedule(dob);
    }

    /**
     * Constructs a SingleShotRecord with the specified vaccine. This constructor does not automatically
     * generate a schedule.
     *
     * @param vaccine the vaccine to be administered
     */
    public SingleShotRecord(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    /**
     * Sets the schedule for this vaccination record.
     *
     * @param s the schedule to set
     */
    public void setSchedule(Schedule s) {
        this.schedule = s;
    }

    /**
     * Determines if the vaccination schedule is complete.
     *
     * @return boolean indicating whether the schedule is complete
     */
    @Override
    public boolean isComplete() {
        return this.schedule != null && this.schedule.isComplete();
    }

    /**
     * Generates a vaccination schedule based on the date of birth of the patient.
     * This method calculates the scheduled vaccination date by adding the offset provided by the vaccine to the patient's date of birth.
     *
     * @param dob the date of birth of the patient
     */
    @Override
    public void generateSchedule(Date dob) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dob);
        calendar.add(Calendar.DAY_OF_YEAR, vaccine.getOffset());
        
        this.schedule = new Schedule();
        this.schedule.setScheduledDate(calendar.getTime());
    }

    /**
     * Marks the vaccination schedule as complete if the specified schedule ID matches the current schedule.
     * Throws an exception if the schedule ID does not match or the schedule is null.
     *
     * @param scheduleId the ID of the schedule to be marked complete
     * @param doctor the doctor responsible for marking the schedule complete
     * @throws NotFound if the schedule with the given ID is not found
     * @throws BadRequest if there are issues marking the schedule as complete
     */
    public void markComplete(int scheduleId, Doctor doctor) throws NotFound, BadRequest {
        if (this.schedule != null && this.schedule.getId().equals(scheduleId)) {
            this.schedule.markComplete(doctor);
        } else {
            throw new NotFound("Schedule not found !");
        }
    }

    /**
     * Retrieves the vaccine associated with this vaccination record.
     *
     * @return the associated vaccine
     */
    @Override
    public Vaccine getVaccine() {
        return this.vaccine;
    }

    /**
     * Retrieves the current schedule associated with this vaccination record.
     *
     * @return the current schedule, or null if no schedule is set
     */
    public Schedule getSchedule() {
        return this.schedule;
    }

    /**
     * Retrieves a list of schedules associated with this vaccination record. 
     * For a single-shot record, this method will typically return a list containing only one schedule.
     *
     * @return a list of schedules, which may contain only one schedule
     */
    public List<Schedule> getSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(getSchedule());
        return schedules;
    }

    /**
     * Adds a new schedule to this vaccination record. This will overwrite any existing schedule.
     *
     * @param s the schedule to add
     */
    @Override
    public void addSchedule(Schedule s) {
        this.setSchedule(s);
    }

    /**
     * Unused method from superclass, meant to return a list of ShotDTOs. Currently not implemented and returns null.
     *
     * @return null as the method is not implemented
     */
    @Override
    public List<ShotRecord> getShotDTOs() {
        // Implementation is required if method is to be used.
        return null;
    }

    
}
