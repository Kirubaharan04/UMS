package com.example.test.utils;

import java.util.List;
import java.util.Set;

import com.example.test.blog.dto.CommentDto;
import com.example.test.blog.dto.PostDto;
import com.example.test.blog.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
	private List<PostDto> content;

	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
	
}
