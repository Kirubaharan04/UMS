package com.example.test.blog.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity handleResourceNotFound(ResourceNotFoundException ex, WebRequest wr) {
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), wr.getDescription(false));
		
		
		return new ResponseEntity<Object>(errorDetails,HttpStatus.BAD_REQUEST);
	
	}
	@ExceptionHandler(BlogException.class)
	
	public ResponseEntity handleBlogException(BlogException BE , WebRequest web) {
		
		ErrorDetails er = new ErrorDetails(new Date(), BE.getMessage(), web.getDescription(false));
		return new ResponseEntity<Object>(er,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	
	public ResponseEntity handleGlobalException(Exception exception) {
		ErrorDetails e = new ErrorDetails(new Date(), exception.getMessage(),
				exception.getLocalizedMessage());
		return new ResponseEntity(e,HttpStatus.BAD_GATEWAY);
	}
	
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> error = new HashMap<String, String>();
			ex.getBindingResult().getFieldErrors().forEach(er->{
				error.put(er.getField(), er.getDefaultMessage());
				
			});
			return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST );
			
		
	}
}
