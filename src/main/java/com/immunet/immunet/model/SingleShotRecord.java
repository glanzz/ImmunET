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

public class SingleShotRecord extends ShotRecord {
    private Vaccine vaccine;
    private Schedule schedule;

    public SingleShotRecord(Vaccine vaccine, Date dob) {
        this.vaccine = vaccine;
        this.generateSchedule(dob);
    }
    
    public SingleShotRecord(Vaccine vaccine) {
        this.vaccine = vaccine;
    }
    
    public void setSchedule(Schedule s) {
    	this.schedule = s;
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

    public void markComplete(int scheduleId, Doctor doctor) throws NotFound, BadRequest {
        if (this.schedule != null && schedule.getId().equals(scheduleId)) {
            this.schedule.markComplete(doctor);
        } else {
        	throw new NotFound("Schedule not found !");
        }
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

	
	public Schedule getSchedule() {
		// TODO Auto-generated method stub
		return this.schedule;
	}
	
	public List<Schedule> getSchedules() {
		List<Schedule> schedules = new ArrayList<Schedule>();
		schedules.add(getSchedule());
		return schedules;
	}

	@Override
	public void addSchedule(Schedule s) {
		this.setSchedule(s);
		
	}

    // Getters and setters omitted for brevity
}
