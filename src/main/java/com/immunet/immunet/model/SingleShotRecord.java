/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
package com.immunet.immunet.model;

import java.util.Calendar;
import java.util.Date;

public class SingleShotRecord extends ShotRecord {
    private Vaccine vaccine;
    private Schedule schedule;

    public SingleShotRecord(Vaccine vaccine, Date dob) {
        this.vaccine = vaccine;
        this.generateSchedule(dob);
    }

    @Override
    public boolean isComplete() {
        return this.schedule != null && this.schedule.isComplete();
    }

//    @Override
//    public List<ShotRecord> getShotDTOs() {
//        return this.schedule != null ? 
//               Collections.singletonList(new ShotDTO(vaccine, schedule)) : 
//               Collections.emptyList();
//    }

    @Override
    public void generateSchedule(Date dob) {
        // Create a calendar instance starting at the pet's date of birth
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dob);

        // Apply the offset from the vaccine to the date of birth
        calendar.add(Calendar.DAY_OF_YEAR, vaccine.getOffset());

        // Create the schedule and set the scheduled date
        this.schedule = new Schedule();
        this.schedule.setScheduledDate(calendar.getTime());

    }

    public void markComplete(Doctor doctor) {
        if (this.schedule != null) {
            this.schedule.markComplete(doctor);
        }
    }

    // Getters and setters omitted for brevity
}
