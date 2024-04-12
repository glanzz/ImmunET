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
import java.util.Date;
import java.util.List;

import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.exception.NotFound;

public class MultiShotRecord extends ShotRecord {
    private Vaccine vaccine;
    private List<Schedule> schedules;

    public MultiShotRecord(Vaccine vaccine, Date dob) {
        this.vaccine = vaccine;
        this.schedules = new ArrayList<>();
        this.generateSchedule(dob);
    }
    
    public MultiShotRecord(Vaccine vaccine) {
        this.vaccine = vaccine;
        this.schedules = new ArrayList<>();
    }
    
    public void addSchedule(Schedule s) {
    	this.schedules.add(s);
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
    public void generateSchedule(Date dob) {
        // Clear any existing schedules
        this.schedules.clear();

        // Create a calendar instance starting at the pet's date of birth
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dob);

        // Add the offset to the date of birth
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

    public void markComplete(int scheduleId, Doctor doctor) throws NotFound, BadRequest {
        Schedule scheduleToMark = this.schedules.stream()
            .filter(s -> s.getId() == scheduleId)
            .findFirst()
            .orElseThrow(() -> new NotFound("Schedule not found"));
        scheduleToMark.markComplete(doctor);
    }

	@Override
	public List<ShotRecord> getShotDTOs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vaccine getVaccine() {
		// TODO Auto-generated method stub
		return this.vaccine;
	}
	
	public List<Schedule> getSchedules() {
		return this.schedules;
	}

	public Schedule getSchedule() {
		// TODO Auto-generated method stub
		return null;
	}

    // Getters and setters omitted for brevity
}
 