/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
package com.immunet.immunet.model;

import java.util.List;

public abstract class ShotRecord {
    public abstract boolean isComplete();
    public abstract List<ShotRecord> getShotDTOs(); // changed to shortRecord -> mv
    public abstract void generateSchedule();
}
