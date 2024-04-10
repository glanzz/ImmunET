package com.immunet.immunet.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immunet.immunet.model.Species;

@RestController
public class DoctorsController {
	
	@PostMapping("/doctors")
	public List<String> createDoctor() {
		List<String> species = new ArrayList<String>();
		for(Species s: Species.values()) {
			species.add(s.name());
		}
		return species;
	}

}
