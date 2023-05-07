package com.example.test.blog.exception;

import java.sql.Date;

public class ErrorDetails {

	
	private java.util.Date timestamp;
	
	public ErrorDetails(java.util.Date timestamp, String message, String description) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.description = description;
	}

	
	public java.util.Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String message;
	
	private String description;
	
	
	
	
	
	
	
	
}
