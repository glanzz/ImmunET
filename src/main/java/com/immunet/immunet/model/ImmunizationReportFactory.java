package com.immunet.immunet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.service.ImmunizationReportService;

@Component
public class ImmunizationReportFactory {
	
	@Autowired
	ImmunizationReportService reportService;
	
	public ImmunizationReport getReport(Pet p) throws BadRequest {
		return new ImmunizationReport(reportService, p);
	}

}
