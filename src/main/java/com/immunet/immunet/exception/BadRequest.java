package com.immunet.immunet.exception;

import org.springframework.http.HttpStatus;

public class BadRequest extends Exception {
	private static final long serialVersionUID = 1L;
	private static final HttpStatus status = HttpStatus.BAD_REQUEST;

	public BadRequest() {
		super();
	}
	
	public BadRequest(String message) {
		super(message);
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
}
