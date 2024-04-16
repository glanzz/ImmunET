package com.immunet.immunet.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immunet.immunet.model.Species;

@RestController
public class SpeciesController {
	
	@CrossOrigin
	@GetMapping("/species")
	public List<String> getAllSpecies() {
		List<String> species = new ArrayList<String>();
		for(Species s: Species.values()) {
			species.add(s.name());
		}
		return species;
	}

}
