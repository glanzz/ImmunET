package com.immunet.immunet.dto;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class ImmunizationReportDTO {
	
	public class ScheduleDTO {
		@NotNull
		Integer id;
		
		@NotNull
		Date scheduledDate;
		
		Date administeredDate;
		
		@NotNull
		String status;
		
		DoctorDTO doctor;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Date getScheduledDate() {
			return scheduledDate;
		}

		public void setScheduledDate(Date scheduledDate) {
			this.scheduledDate = scheduledDate;
		}

		public Date getAdministeredDate() {
			return administeredDate;
		}

		public void setAdministeredDate(Date administeredDate) {
			this.administeredDate = administeredDate;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public DoctorDTO getDoctor() {
			return doctor;
		}

		public void setDoctor(DoctorDTO doctor) {
			this.doctor = doctor;
		}
		
	}
	
	public class ImmunizationRecordDTO {
		@NotNull
		Integer vaccineId;

		@NotNull
		String name;
		
		@NotNull
		String type;
		
		@NotNull
		List<ScheduleDTO> schedules;
		
		public ImmunizationRecordDTO() {
			schedules = new ArrayList<ScheduleDTO>();
		}

		public Integer getVaccineId() {
			return vaccineId;
		}

		public void setVaccineId(Integer vaccineId) {
			this.vaccineId = vaccineId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public List<ScheduleDTO> getSchedules() {
			return schedules;
		}

		public void setSchedules(List<ScheduleDTO> schedules) {
			this.schedules = schedules;
		}
		
	}

	@NotNull
	String name;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date dob;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<ImmunizationRecordDTO> getRecords() {
		return records;
	}

	public void setRecords(List<ImmunizationRecordDTO> records) {
		this.records = records;
	}

	List<ImmunizationRecordDTO> records;
	
	public ImmunizationReportDTO() {
		records = new ArrayList<ImmunizationRecordDTO>();
	}
	
}
