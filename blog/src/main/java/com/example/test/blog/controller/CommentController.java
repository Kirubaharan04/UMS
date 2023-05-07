package com.example.test.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.blog.dto.CommentDto;
import com.example.test.blog.exception.BlogException;
import com.example.test.blog.exception.ResourceNotFoundException;
import com.example.test.blog.service.CommentService;
import com.example.test.blog.service.CommentServiceimpl;

@RestController																		
@RequestMapping("/api/")
public class CommentController {
	@Autowired
	CommentServiceimpl commentService;
	
	private final Logger log = LogManager.getLogger(CommentController.class);
	@PostMapping("/post/{postid}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable (value = "postid")long postid,@Valid @RequestBody CommentDto commentDto) throws ResourceNotFoundException {
		
		log.info("To Create the New Comment");
		CommentDto createComment = commentService.createComment(postid, commentDto);
		log.info("The comment is created");
	return new ResponseEntity<>( createComment,HttpStatus.CREATED);
	
	}
	
	@GetMapping("post/{postid}/comments")
	
	public List<CommentDto> findCommentsByPostId(@PathVariable("postid")long id){
		
		log.info("To find the comments by id");
		
		List<CommentDto> findCommentsByPostid = commentService.findCommentsByPostId(id);
		log.info("The Comment id found");
		return findCommentsByPostid;
	}
	
	@GetMapping("post/{postid}/comments/{commentsid}")
	
	public ResponseEntity<Object> getCommentById(@PathVariable
			("postid") long pid , @PathVariable("commentsid") long cid  ) throws ResourceNotFoundException, BlogException{
		
		log.info("To find the comments by post Id");
		
		CommentDto commentById = commentService.getCommentById(pid, cid);
		
		log.info("The Comment is found by post Id");
		
		return new ResponseEntity<Object>(commentById ,HttpStatus.ACCEPTED);
		
	}
	@GetMapping("post/{postid}/comments/{commentsid}/update")
	
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postid")
	long pid ,@PathVariable(value ="commentsid" ) long cid,@Valid@RequestBody CommentDto commentDto) throws ResourceNotFoundException, BlogException{
		log.info("To update the comment by postId");
		CommentDto updateComment = commentService.updateComment(pid, cid, commentDto);
		log.info("The comment is updated by postId ");
		return new ResponseEntity<CommentDto>(updateComment, HttpStatus.ACCEPTED);
		
		
		
	}
	
	@DeleteMapping("comments/delete/{id}")
	public ResponseEntity deleteComment(@PathVariable(value = "id") long cid) throws ResourceNotFoundException {
		log.info("To delete the commrent");
		commentService.deleteComment(cid);
		log.info("The comment is deleted");
		return new ResponseEntity<Object>("Comments Delete Sucessfully", HttpStatus.ACCEPTED);
		
	}
	
	
	
	

}
