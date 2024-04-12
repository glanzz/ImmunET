/**
 *
 * @author HP
 */
package com.immunet.immunet.model;

import java.util.Date;
import java.util.List;

import com.immunet.immunet.entity.ScheduleEntity;


public abstract class ShotRecord {
	
	public abstract Vaccine getVaccine();
	public abstract Schedule getSchedule();
    public abstract boolean isComplete();
    public abstract List<ShotRecord> getShotDTOs(); // changed to shortRecord -> mv
    public abstract void generateSchedule(Date dob);
    public abstract void addSchedule(Schedule s);

}
