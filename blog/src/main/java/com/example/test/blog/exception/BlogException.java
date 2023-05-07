package com.example.test.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogException extends Exception {
	
	private HttpStatus status;

	private String message;

	
	
	
	
	

	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public BlogException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}


	public BlogException(String message, HttpStatus status, String message2) {
		super(message);
		this.status = status;
		message = message2;
	}
	
}
