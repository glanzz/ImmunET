/**
 *
 * @author HP
 */
package com.immunet.immunet.model;

import java.util.Date;
import java.util.List;

import com.immunet.immunet.entity.ScheduleEntity;
import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.exception.NotFound;


public abstract class ShotRecord {
	
	public abstract Vaccine getVaccine();
	public abstract List<Schedule> getSchedules();
    public abstract boolean isComplete();
    public abstract List<ShotRecord> getShotDTOs(); // changed to shortRecord -> mv
    public abstract void generateSchedule(Date dob);
    public abstract void addSchedule(Schedule s);
	protected abstract void markComplete(int scheduleId, Doctor signedDoctor) throws NotFound, BadRequest;

}
