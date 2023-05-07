package com.example.test.blog.dto;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.test.blog.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data

public class PostDto {
	private long id;
	@NotEmpty
	@Size(min =2, message = "The Minimum Character is 2")
	private String title;
	@NotEmpty
	@Size(min = 10 , message = "The Minimum character is 10")
	private String description;
	@NotEmpty
	private String content;
	
	private Set<CommentDto> comment;


}
