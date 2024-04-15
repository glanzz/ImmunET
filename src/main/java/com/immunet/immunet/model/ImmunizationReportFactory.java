/**
 * Factory class for creating {@code ImmunizationReport} instances. This class encapsulates the logic
 * required to create immunization reports for pets, leveraging the {@code ImmunizationReportService} 
 * to fetch and compile necessary data. It is annotated as a Spring Component, indicating that it is 
 * to be automatically detected and registered by Spring's component-scanning mechanism.
 */
package com.immunet.immunet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.service.ImmunizationReportService;

@Component
public class ImmunizationReportFactory {
	
	/**
	 * Service for managing immunization data and operations. This dependency is automatically
	 * injected by Spring's dependency injection facilities due to the {@code @Autowired} annotation.
	 */
	@Autowired
	private ImmunizationReportService reportService;
	
	/**
	 * Creates an {@code ImmunizationReport} for a given pet. This method uses the injected
	 * {@code ImmunizationReportService} to generate a new {@code ImmunizationReport} instance,
	 * which involves potentially complex business logic and data fetching handled by the service layer.
	 *
	 * @param p the pet for which the immunization report is to be generated
	 * @return an instance of {@code ImmunizationReport} containing the immunization details of the specified pet
	 * @throws BadRequest if there are issues generating the report, possibly due to missing or invalid data
	 */
	public ImmunizationReport getReport(Pet p) throws BadRequest {
		return new ImmunizationReport(reportService, p);
	}
}
