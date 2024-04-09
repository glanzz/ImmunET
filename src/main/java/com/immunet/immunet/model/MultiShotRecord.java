/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
package com.immunet.immunet.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class MultiShotRecord extends ShotRecord {
    private Vaccine vaccine;
    private List<Schedule> schedules;

    public MultiShotRecord(Vaccine vaccine) {
        this.vaccine = vaccine;
        this.schedules = new ArrayList<>();
        this.generateSchedule();
    }

    @Override
    public boolean isComplete() {
        return this.schedules != null && this.schedules.stream().allMatch(Schedule::isComplete);
    }

//    @Override
//    public List<ShotDTO> getShotDTOs() {
//        return this.schedules.stream()
//                             .map(schedule -> new ShotDTO(vaccine, schedule))
//                             .collect(Collectors.toList());
//    }

    @Override
    public void generateSchedule() {
        // Clear the existing schedules before regenerating them
        this.schedules.clear();

        // Assuming the intervals are the number of days between shots
        List<Integer> intervalDays = vaccine.getIntervals();
        
        // Set the first schedule date based on the offset
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, vaccine.getOffset());
        
        for (int i = 0; i < vaccine.getFrequency(); i++) {
            Schedule schedule = new Schedule();
            schedule.setScheduledDate(calendar.getTime());
            schedules.add(schedule);

            if (i < intervalDays.size()) {
                // Add the interval to the current date for the next shot
                calendar.add(Calendar.DAY_OF_YEAR, intervalDays.get(i));
            }
            // If there are no more intervals, use the last interval for subsequent shots
        }
    }

    public void markComplete(int scheduleId, Doctor doctor) {
        Schedule scheduleToMark = this.schedules.stream()
            .filter(s -> s.getId() == scheduleId)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Schedule not found"));
        scheduleToMark.markComplete(doctor);
    }

    // Getters and setters omitted for brevity
}
 