package com.instagram.exceptions;

import java.time.LocalDateTime;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.instagram.model.ApiErrors;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		LocalDateTime timestamp = LocalDateTime.now();
		String error = "Method not allowed";
		ApiErrors apiError = new ApiErrors(timestamp, message, status.value(), error);
		return ResponseEntity.status(status.value()).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		LocalDateTime timestamp = LocalDateTime.now();
		String error = "Invalid Media Type";
		ApiErrors apiError = new ApiErrors(timestamp, message, status.value(), error);
		return ResponseEntity.status(status).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		LocalDateTime timestamp = LocalDateTime.now();
		String error = "Missing Path Variables";
		ApiErrors apiError = new ApiErrors(timestamp, message, status.value(), error);
		return ResponseEntity.status(status).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		LocalDateTime timestamp = LocalDateTime.now();
		String error = "Missing Request Parameters";
		ApiErrors apiError = new ApiErrors(timestamp, message, status.value(), error);
		return ResponseEntity.status(status).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		LocalDateTime timestamp = LocalDateTime.now();
		String error = "Type Mismatch";
		ApiErrors apiError = new ApiErrors(timestamp, message, status.value(), error);
		return ResponseEntity.status(status).body(apiError);
	}

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleException(RuntimeException ex) {
		String message = ex.getMessage();
		LocalDateTime timestamp = LocalDateTime.now();
		String error = "Other Exception";
		ApiErrors apiError = new ApiErrors(timestamp, message, HttpStatus.BAD_GATEWAY.value(), error);
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(apiError);
	}

}
