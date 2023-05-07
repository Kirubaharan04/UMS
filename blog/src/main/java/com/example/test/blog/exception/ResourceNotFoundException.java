package com.example.test.blog.exception;

public class ResourceNotFoundException extends Exception {

	public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
		super(String.format("not found with",fieldName, fieldValue,resourceName));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	private String resourceName;
	private String fieldName;
	private Long fieldValue;

	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Long getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(Long fieldValue) {
		this.fieldValue = fieldValue;
	}
	
}
