package com.immunet.immunet.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.immunet.immunet.entity.DoctorEntity;
import com.immunet.immunet.entity.VaccineEntity;
import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.service.VaccineService;

/**
 * Represents a vaccine including details such as name, species it's used for,
 * frequency of doses, intervals between doses, and cost. This class also handles
 * the validation and persistence of vaccine data.
 */
public class Vaccine {
	Integer id;
	String name;
	Integer frequency;
	List<Integer> intervals;
	Integer offset;
	boolean defaultVaccine;
	Double cost;
	Integer doctorId;
	VaccineService vaccineService;
	

	public boolean isDefaultVaccine() {
		return defaultVaccine;
	}


	public void setDefaultVaccine(boolean defaultVaccine) {
		this.defaultVaccine = defaultVaccine;
	}

	Species species;
	
	public Integer getId() {
		return this.id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) throws BadRequest {
		this.frequency = frequency;
		if (validateFrequency()== false) {
			throw new BadRequest("Frequency not compatible with intervals given");
		}
	}
	public List<Integer> getIntervals() {
		return intervals;
	}
	
	public String getIntervalsString() {
		String intervalString = "";
		for(Integer interval: intervals) {
			intervalString += interval + ",";
		}
		return intervalString;
		
	}
	public void setIntervals(List<Integer> intervals) {
		this.intervals = intervals;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public Species getSpecies() {
		return species;
	}
	public void setSpecies(Species species) {
		this.species = species;
	}


	public Double getCost() {
		return cost;
	}


	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	
	public Integer getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}


	boolean isDefault() {
		if (defaultVaccine==true) {
			return true;
		}
		return false;	
	}

	
        /**
         * Constructs a Vaccine with specified details.
         * 
         * @param vaccineService Service used for vaccine-related operations.
         * @param name Name of the vaccine.
         * @param species Species the vaccine is intended for.
         * @param frequency Number of doses required.
         * @param intervalsCSV Comma-separated values of days between doses.
         * @param offset Days until the first vaccination from the date of birth.
         * @throws BadRequest If any parameter is invalid.
         */
	public  Vaccine(VaccineService vaccineService, String name, Species s,  int frequency, String intervalsCSV, int offset) throws BadRequest {
		this.vaccineService = vaccineService;
			if(name==null || frequency==0||intervalsCSV==null ) {
				throw new BadRequest("Invalid Vaccine data given !");
				
				
			} else {
				this.name=name;
				setSpecies(s);
				setIntervals(parseIntervals(intervalsCSV));
				setFrequency(frequency);
				this.offset=offset;
			}
	}
	
	public Vaccine() {}
	

        /**
        * Parses the string of intervals into a list of integers.
        * 
        * @param intervalsCSV Comma-separated string of interval days.
        * @return List of intervals as integers.
        * @throws BadRequest If the string format is incorrect.
        */
	public static List<Integer> parseIntervals(String intervalsCSV) throws BadRequest {
		try {
			List<Integer> parsedIntervals = new ArrayList<Integer>();
			if (intervalsCSV.compareTo("") != 0) { // Handle empty csv
				List<String> intervals = Arrays.asList(intervalsCSV.split(","));
				if (intervals.size() > 0) {
					intervals.stream().forEach((a) -> {
						parsedIntervals.add(Integer.parseInt(a));
					});
				}
			}
			return parsedIntervals;
			
		}
		catch(NumberFormatException e) {
			throw new BadRequest("Invalid intervals data given !");
		}
		
	}
	
         /**
        * Validates if the frequency matches the number of intervals.
        * 
        * @return true if the frequency is compatible with the intervals, otherwise false.
        */
	private boolean validateFrequency() {
		return frequency.equals(this.intervals.size() + 1); 
	}
	
	public boolean isPersisted() {
		return this.id != null; 
	}
	
        /**
        * Saves the vaccine information to the database using the associated service.
        * 
        * @param doctor The doctor entity associated with this vaccine.
        */
	public void save(DoctorEntity doctor) {
		if (isPersisted()) {
			return;
		} else {
			this.id = vaccineService.save(this, doctor);
		}
	}
	
	
        /**
        * Loads vaccine data from a VaccineEntity.
        * 
        * @param vaccine The entity from which to load the data.
        * @throws BadRequest If there are inconsistencies in the data.
        */
	public void load(VaccineEntity vaccine) throws BadRequest {
		System.out.println(vaccine.getId());
		this.id = vaccine.getId();
		this.setCost(vaccine.getCost());
		this.setDefaultVaccine(vaccine.isDefault());
		this.setIntervals(Vaccine.parseIntervals(vaccine.getIntervals()));
		this.setName(vaccine.getName());
		this.setOffset(vaccine.getOffset());
		this.setFrequency(vaccine.getFrequency());
		this.setSpecies(Species.valueOf(vaccine.getSpecies().name()));
		this.setDoctorId(vaccine.getDoctor().getId());
	}
	
	
	public String toString() {
		return "Vaccine:" + this.getName() +","+ this.getCost() + "," + this.getFrequency()+ ";";
	}


	public ShotRecord getShotRecord(Date dob) {
		// Load from existing schedules the Shotrecord
		return ShotFactory.getInstance().getShotRecord(this, dob);
	}
	
	
	
	public boolean requiresMultipleShots() {
		return frequency > 1;
	}
	
	
	
	

}
