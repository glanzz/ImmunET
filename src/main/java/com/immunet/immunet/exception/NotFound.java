package com.immunet.immunet.exception;

import org.springframework.http.HttpStatus;

public class NotFound extends Exception {
	private static final long serialVersionUID = 1L;
	private static final HttpStatus status = HttpStatus.NOT_FOUND;

	public NotFound() {
		super();
	}
	
	public NotFound(String message) {
		super(message);
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
}
