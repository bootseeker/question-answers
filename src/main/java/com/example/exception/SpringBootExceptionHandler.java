package com.example.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpringBootExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> HandleAllExceptions(Exception exception) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = SpringBootException.class)
	public ResponseEntity<Object> HandleAllExceptions(SpringBootException exception) {
		List<String> details = exception.getErrorMessageList();

		return new ResponseEntity<>(details.toString().substring(1, details.toString().length() - 1),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<String, String>();

		List<ObjectError> ErrorsList = ex.getBindingResult().getAllErrors();

		for (ObjectError objectError : ErrorsList) {
			String fieldName = ((FieldError) objectError).getField();
			String errorMessage = objectError.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		}
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ResourceExistsException.class)
	public ResponseEntity<Object> handleResourceAlreadyExistException(ResourceExistsException exception) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
