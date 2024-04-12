package com.immunet.immunet.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponseDTO {
	private HttpStatus status;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyMMdd hh:mm:ss")
	private LocalDateTime timeStamp;
	private String message;
	public ErrorResponseDTO(HttpStatus status) {
		this.status = status;
	}
	
	public ErrorResponseDTO() {
		timeStamp = LocalDateTime.now();
	}
	

	public ErrorResponseDTO(HttpStatus status, LocalDateTime timeStamp, String message) {
		super();
		this.status = status;
		this.timeStamp = timeStamp;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
