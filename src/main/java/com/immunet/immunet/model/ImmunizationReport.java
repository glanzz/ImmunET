package com.immunet.immunet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ImmunizationReport {
    private List<ShotRecord> shotRecords;
    private Pet pet;

    public ImmunizationReport(Pet pet) {
        this.pet = pet;
        this.shotRecords = pet.getShotRecords();
        if (shotRecords == null) {
           this.load(pet.id);
         }
    }

    public void addShotRecord(Vaccine vaccine) {
//        if (shotRecordExists(vaccine)) {
//            throw new DuplicateShotRecordException("Shot record for vaccine " + vaccine.getName() + " already exists.");
//        }
        ShotRecord newRecord = vaccine.isDefault() ? 
                               new SingleShotRecord(vaccine) : 
                               new MultiShotRecord(vaccine);
        this.shotRecords.add(newRecord);
    }

    private boolean shotRecordExists(Vaccine vaccine) {
        return this.shotRecords.stream().anyMatch(record -> record.getVaccine().equals(vaccine));
    }

    // Assume a method in ShotRecord to get the associated vaccine
    public void completeShot(int scheduleId, Doctor signedDoctor) {
        this.shotRecords.stream()
                        .map(record -> (record instanceof MultiShotRecord) ? 
                                       (MultiShotRecord) record : null)
                        .filter(Objects::nonNull)
                        .forEach(record -> record.markComplete(scheduleId, signedDoctor));
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
        return shotRecords.stream()
                                  .filter(record -> record.getSchedule() != null &&
                                                    record.getSchedule().getScheduledDate().equals(today))
                                  .collect(Collectors.toList());
    }

    // Get a list of shot records that were completed today
    public List<ShotRecord> getTodaysCompletedShots() {
        Date today = new Date(); // Today's date
        return shotRecords.stream()
                                  .filter(record -> record.getSchedule() != null &&
                                                    record.getSchedule().getAdministeredDate() != null &&
                                                    record.getSchedule().getAdministeredDate().equals(today))
                                  .collect(Collectors.toList());
    }

    // Get a list of shot records that are scheduled for the future
    public List<ShotRecord> getUpcomingScheduledShots() {
        Date today = new Date(); // Today's date
        return shotRecords.stream()
                                  .filter(record -> record.getSchedule() != null &&
                                                    record.getSchedule().getScheduledDate().after(today))
                                  .collect(Collectors.toList());
    }

    // Other methods like load, create, and getters for shot records omitted for brevity
}
