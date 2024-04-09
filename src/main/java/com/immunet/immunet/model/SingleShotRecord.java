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

public class SingleShotRecord extends ShotRecord {
    private Vaccine vaccine;
    private Schedule schedule;

    public SingleShotRecord(Vaccine vaccine) {
        this.vaccine = vaccine;
        this.generateSchedule();
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
    public void generateSchedule() {
        // Create a calendar instance to manage date
        Calendar calendar = Calendar.getInstance();

        // Apply the offset from the vaccine if it exists
        calendar.add(Calendar.DAY_OF_YEAR, vaccine.getOffset());

        // Create the schedule and set the scheduled date
        this.schedule = new Schedule();
        this.schedule.setScheduledDate(calendar.getTime());
        
        // Additional properties for schedule can be set here if necessary
    }

    public void markComplete(Doctor doctor) {
        if (this.schedule != null) {
            this.schedule.markComplete(doctor);
        }
    }

    // Getters and setters omitted for brevity
}
