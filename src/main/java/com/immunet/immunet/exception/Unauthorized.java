package com.immunet.immunet.exception;

import org.springframework.http.HttpStatus;

public class Unauthorized extends Exception {
	private static final long serialVersionUID = 1L;
	private static final HttpStatus status = HttpStatus.UNAUTHORIZED;

	public Unauthorized() {
		super();
	}
	
	public Unauthorized(String message) {
		super(message);
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
}
