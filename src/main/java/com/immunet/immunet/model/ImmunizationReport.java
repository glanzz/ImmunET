package com.immunet.immunet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.exception.NotFound;
import com.immunet.immunet.service.ImmunizationReportService;

/**
 * Requires vaccinationRepostiroy
 * */
public class ImmunizationReport {
    private List<ShotRecord> shotRecords;
    private Pet pet;
    private ImmunizationReportService service;

    public ImmunizationReport(ImmunizationReportService service, Pet pet) throws BadRequest {
        this.pet = pet;
        this.service = service;
        this.shotRecords = new ArrayList<ShotRecord>();
        if (pet.isPersisted()) {
        	this.load();
        } else {
        	this.loadDefaultRecords(pet.getCreatorID());
        }
    }
    
    public Pet getPet() {
    	return this.pet;
    }
    
    public List<ShotRecord> getShotRecords() {
    	return this.shotRecords;
    }

    public void load() throws BadRequest {
		// Load from pet ID the immunization reports from repository of schedules
    	service.getExistingSchedules(pet).stream().forEach(s -> this.shotRecords.add(s));
	}
    
    public void save(Integer userId) {
    	service.save(this, userId);
    }
    
	public void addShotRecord(Vaccine vaccine) throws BadRequest {
        if (shotRecordExists(vaccine)) {
            throw new BadRequest("Shot record for vaccine " + vaccine.getName() + " already exists.");
        }
        ShotRecord newRecord = ShotFactory.getInstance().getShotRecord(vaccine, this.pet.getDob());
        this.shotRecords.add(newRecord);
    }
	
	public void loadDefaultRecords(Integer doctorId) throws BadRequest {
		List<Vaccine> defaultVaccines = service.getDefaultVaccines(pet); // Fetch from vaccines repository
		for(Vaccine defaultVaccine: defaultVaccines) {
			this.addShotRecord(defaultVaccine);
		}
	}

    private boolean shotRecordExists(Vaccine vaccine) {
        return this.shotRecords.stream().anyMatch(record -> record.getVaccine().getId().equals(vaccine.getId()));
    }
    

    // Assume a method in ShotRecord to get the associated vaccine
    public void completeShot(int scheduleId, Doctor signedDoctor) throws NotFound, BadRequest {
    	for(ShotRecord shotRecord: this.shotRecords) {
    		Schedule s = shotRecord.getSchedules().stream().filter(schedule -> schedule.getId().equals(scheduleId)).findFirst().get();
    		if(s != null) {
    			shotRecord.markComplete(scheduleId, signedDoctor);
    			return;
    		}
    	}
    	throw new NotFound("No Schedule found with the given ID");
    }
    
    
    // Get a list of completed shot records till the current date
    public List<ShotRecord> getCompletedShotsTillDate() {
        return shotRecords.stream()
                                  .filter(ShotRecord::isComplete)
                                  .collect(Collectors.toList());
    }

    // Get a list of shot records scheduled for today
    public List<ShotRecord> getTodaysShots() {
        Date today = new Date(); // Today's date
        return shotRecords.stream().filter(record -> {
        	return record.getSchedules().stream().anyMatch(schedule -> schedule.getScheduledDate().equals(today));
        }).collect(Collectors.toList());
        
    }

    // Get a list of shot records that were completed today
    public List<ShotRecord> getTodaysCompletedShots() {
        Date today = new Date(); // Today's date
        return shotRecords.stream().filter(record -> {
        	return record.getSchedules().stream().anyMatch(schedule -> schedule.getScheduledDate().equals(today) && schedule.isComplete());
        }).collect(Collectors.toList());
    }

    // Get a list of shot records that are scheduled for the future
    public List<ShotRecord> getUpcomingScheduledShots() {
        Date today = new Date(); // Today's date
        return shotRecords.stream().filter(record -> {
        	return record.getSchedules().stream().anyMatch(schedule -> schedule.getScheduledDate().after(today));
        }).collect(Collectors.toList());
    }
    

    // Other methods like load, create, and getters for shot records omitted for brevity
}
