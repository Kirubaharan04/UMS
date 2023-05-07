package com.example.test.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
@Data


public class CommentDto {
	private long id;
	@NotEmpty
	@Size(min = 5 ,message = "Character should abouve 5")
	private String name;
	@Email(message = "Enter the Valid Email")
	private String email;
	@Size(min =2 , message = "The character Should be ")
	private String body;
	

}
