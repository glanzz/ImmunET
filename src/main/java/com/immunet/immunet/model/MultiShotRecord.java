/**
 * Represents a vaccination record for vaccines that require multiple doses. This class extends {@code ShotRecord}
 * and manages multiple schedules for the administrations of the various doses of a vaccine.
 */
package com.immunet.immunet.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.exception.NotFound;

public class MultiShotRecord extends ShotRecord {
    private Vaccine vaccine;
    private List<Schedule> schedules;
    // Contructor overloading

    /**
     * Constructs a new MultiShotRecord with a specified vaccine and date of birth.
     * Automatically generates the multiple schedules based on the vaccine's dosage frequency and intervals.
     *
     * @param vaccine the vaccine to be administered across multiple doses
     * @param dob the date of birth of the patient, used to calculate the initial and subsequent vaccination dates
     */
    public MultiShotRecord(Vaccine vaccine, Date dob) {
        this.vaccine = vaccine;
        this.schedules = new ArrayList<>();
        this.generateSchedule(dob);
    }

    /**
     * Constructs a new MultiShotRecord with a specified vaccine but without generating the schedules automatically.
     *
     * @param vaccine the vaccine to be administered across multiple doses
     */
    public MultiShotRecord(Vaccine vaccine) {
        this.vaccine = vaccine;
        this.schedules = new ArrayList<>();
    }

    /**
     * Adds a new schedule to this vaccination record.
     *
     * @param s the new schedule to be added
     */
    public void addSchedule(Schedule s) {
        this.schedules.add(s);
    }

    /**
     * Checks if all schedules associated with this vaccination record are complete.
     *
     * @return true if all schedules are complete, false otherwise
     */
    @Override
    public boolean isComplete() {
        return this.schedules != null && this.schedules.stream().allMatch(Schedule::isComplete);
    }

    /**
     * Generates the schedules for the vaccinations based on the patient's date of birth.
     * The method calculates each vaccination date by applying the offset and intervals specified by the vaccine.
     *
     * @param dob the date of birth of the patient
     */
    @Override
    public void generateSchedule(Date dob) {
        this.schedules.clear();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dob);
        calendar.add(Calendar.DAY_OF_YEAR, vaccine.getOffset());

        List<Integer> intervalDays = vaccine.getIntervals();
        for (int i = 0; i < vaccine.getFrequency(); i++) {
            Schedule schedule = new Schedule();
            schedule.setScheduledDate(calendar.getTime());
            this.schedules.add(schedule);

            if (i < intervalDays.size()) {
                calendar.add(Calendar.DAY_OF_YEAR, intervalDays.get(i));
            } else if (!intervalDays.isEmpty()) {
                calendar.add(Calendar.DAY_OF_YEAR, intervalDays.get(intervalDays.size() - 1));
            }
        }
    }

    /**
     * Marks a specific vaccination schedule as complete, if the specified schedule ID matches the existing one.
     * Throws exceptions if the schedule ID does not match any existing schedules.
     *
     * @param scheduleId the ID of the schedule to be marked as complete
     * @param doctor the doctor responsible for marking the schedule as complete
     * @throws NotFound if the schedule with the specified ID does not exist
     * @throws BadRequest if there are issues processing the completion
     */
    public void markComplete(int scheduleId, Doctor doctor) throws NotFound, BadRequest {
        Schedule scheduleToMark = this.schedules.stream()
            .filter(s -> s.getId() == scheduleId)
            .findFirst()
            .orElseThrow(() -> new NotFound("Schedule not found"));
        scheduleToMark.markComplete(doctor);
    }

    /**
     * Retrieves the vaccine associated with this vaccination record.
     *
     * @return the vaccine used for this record
     */
    @Override
    public Vaccine getVaccine() {
        return this.vaccine;
    }

    /**
     * Retrieves the list of schedules associated with this vaccination record.
     *
     * @return a list of schedules
     */
    public List<Schedule> getSchedules() {
        return this.schedules;
    }

    /**
     * (This method is not implemented yet and returns null.)
     * Retrieves a list of ShotRecords, potentially formatted for specific data transfer needs.
     *
     * @return null as the method is not implemented
     */
    @Override
    public List<ShotRecord> getShotDTOs() {
        // Implementation required if this method is to be used.
        return null;
    }


}
