package com.example.test.blog.service;

import java.util.List;

import com.example.test.blog.dto.CommentDto;
import com.example.test.blog.entity.Comment;
import com.example.test.blog.exception.BlogException;
import com.example.test.blog.exception.ResourceNotFoundException;

public interface CommentService {
	CommentDto createComment(long postid, CommentDto commentDto) throws ResourceNotFoundException;

	List<CommentDto> findCommentsByPostId(long id);

	CommentDto getCommentById(long pid, long cid) throws ResourceNotFoundException, BlogException;

	CommentDto updateComment(long pid, long cid, CommentDto commentDto) throws ResourceNotFoundException, BlogException;

	void deleteComment(long cid) throws ResourceNotFoundException;

}
