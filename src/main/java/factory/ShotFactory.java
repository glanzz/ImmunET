package factory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */


import model.MultiShotRecord;
import model.ShotRecord;
import model.SingleShotRecord;

public class ShotFactory {

    private static ShotFactory instance = new ShotFactory();

    private ShotFactory() {}

    public static ShotFactory getInstance() {
        return instance;
    }

    public ShotRecord getShotRecord(Vaccine vaccine) {
        // The logic here is simplified; you would need to base this on your actual criteria
        // for determining the type of ShotRecord to create (e.g., the number of doses).
        if (vaccine.requiresMultipleShots()) {
            return new MultiShotRecord(vaccine);
        } else {
            return new SingleShotRecord(vaccine);
        }
    }
}

