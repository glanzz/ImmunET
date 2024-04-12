package com.immunet.immunet.exception;

import org.springframework.http.HttpStatus;

public class Conflict extends Exception {
	private static final long serialVersionUID = 1L;
	private static final HttpStatus status = HttpStatus.CONFLICT;

	public Conflict() {
		super();
	}
	
	public Conflict(String message) {
		super(message);
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
}
