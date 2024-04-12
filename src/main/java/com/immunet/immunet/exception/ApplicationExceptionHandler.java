package com.immunet.immunet.exception;

import org.springframework.core.Ordered;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.immunet.immunet.dto.ErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<Object> handleBadRequest(HttpServletRequest req, BadRequest exception) {
		ErrorResponseDTO responseDTO = new ErrorResponseDTO();
		responseDTO.setMessage(exception.getMessage());
		responseDTO.setStatus(exception.getStatus());
		return buildResponse(responseDTO);
	}
	
	@ExceptionHandler(Unauthorized.class)
	public ResponseEntity<Object> handleBadRequest(HttpServletRequest req, Unauthorized exception) {
		ErrorResponseDTO responseDTO = new ErrorResponseDTO();
		responseDTO.setMessage(exception.getMessage());
		responseDTO.setStatus(exception.getStatus());
		return buildResponse(responseDTO);
	}
	
	@ExceptionHandler(Conflict.class)
	public ResponseEntity<Object> handleConflictRequest(HttpServletRequest req, Conflict exception) {
		ErrorResponseDTO responseDTO = new ErrorResponseDTO();
		responseDTO.setMessage(exception.getMessage());
		responseDTO.setStatus(exception.getStatus());
		return buildResponse(responseDTO);
	}
	
	@ExceptionHandler(NotFound.class)
	public ResponseEntity<Object> handleConflictRequest(HttpServletRequest req, NotFound exception) {
		ErrorResponseDTO responseDTO = new ErrorResponseDTO();
		responseDTO.setMessage(exception.getMessage());
		responseDTO.setStatus(exception.getStatus());
		return buildResponse(responseDTO);
	}
	
	private ResponseEntity<Object> buildResponse(ErrorResponseDTO responseDTO) {
		return new ResponseEntity<Object>(responseDTO, responseDTO.getStatus());
	}

}
