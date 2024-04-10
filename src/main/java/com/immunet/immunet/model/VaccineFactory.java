package com.immunet.immunet.model;



public class VaccineFactory {
	private static VaccineFactory instance = null;
	private VaccineFactory getInstance() {
		if (null==instance) {
			synchronized (VaccineFactory.class) {
				if (null==instance) instance= new VaccineFactory ();
			}
		}
		return instance;
	}
	
	public Vaccine getVaccine(String name, int frequency, String intervalsCSV, int offset) {
		return new Vaccine(name, frequency,  intervalsCSV, offset) ;
	}
		
		

}
