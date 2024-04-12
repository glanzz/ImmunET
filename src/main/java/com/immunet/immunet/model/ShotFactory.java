package com.immunet.immunet.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.immunet.immunet.entity.ScheduleEntity;

@Component
public class ShotFactory {

    private static ShotFactory instance = new ShotFactory();

    private ShotFactory() {}

    public static ShotFactory getInstance() {
        return instance;
    }

    public ShotRecord getShotRecord(Vaccine vaccine, Date dob) {
        // The logic here is simplified; you would need to base this on your actual criteria
        // for determining the type of ShotRecord to create (e.g., the number of doses).
        if (vaccine.requiresMultipleShots()) {
            return new MultiShotRecord(vaccine, dob);
        } else {
            return new SingleShotRecord(vaccine, dob);
        }
    }
    public ShotRecord getShotRecord(Vaccine v) {
    	if (v.requiresMultipleShots()) {
            return new MultiShotRecord(v);
        } else {
            return new SingleShotRecord(v);
        }
    }
}

