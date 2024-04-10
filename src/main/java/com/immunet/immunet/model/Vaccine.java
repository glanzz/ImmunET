package com.immunet.immunet.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Vaccine extends VaccineFactory {

	String name;
	int frequency;
	List<Integer> intervals;
	int offset;
	boolean defaultVaccine;
	Species species;
	
	
	public ShotRecord getShotRecord(Date dob) {
		ShotRecord s = new ShotRecord();
		return s;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public List<Integer> getIntervals() {
		return intervals;
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


	public Vaccine(String name, int frequency, List<Integer> intervals, int offset, boolean defaultVaccine,
			Species species) {
		super();
		this.name = name;
		this.frequency = frequency;
		this.intervals = intervals;
		this.offset = offset;
		this.defaultVaccine = defaultVaccine;
		this.species = species;
	}
	
	boolean isDefault() {
		if (defaultVaccine==true) {
			return true;
		}
		return false;	
	}
	
	public  Vaccine(String name, int frequency, String intervalsCSV, int offset) {
		try {
			if(name==null || frequency==0||intervalsCSV==null || offset==0 ) {
				System.out.println("Invalid input given ");
				
				
			}else {
				this.name=name;
				this.frequency=frequency;
				parseIntervals(intervalsCSV);
				this.offset=offset;
			}
			
		}catch(NumberFormatException e) {
			System.out.println("Invalid input given ");
		}
	}
	
	public static List<Integer> parseIntervals(String intervalsCSV) {
		
		List<String> intervals = Arrays.asList(intervalsCSV.split(","));
		return (List<Integer>) intervals.stream().map((a) -> Integer.parseInt(a));
	}
	
	
	
	

}
