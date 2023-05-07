package com.example.test.blog.controller;


import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.blog.dto.PostDto;
import com.example.test.blog.exception.ResourceNotFoundException;
import com.example.test.blog.service.PostServiceImpl;
import com.example.test.utils.ApplicationConstant;
import com.example.test.utils.PostResponse;

@RestController
@RequestMapping("/api/post")
public class PostController {

	private final Logger log = LogManager.getLogger(PostController.class);
	
	
	@Autowired
	PostServiceImpl postServiceImpl;
	
	@PostMapping("/new")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
		log.info("The New Post Updation...The Method From Controller ");
		ResponseEntity<PostDto> responseEntity = new ResponseEntity<PostDto>(postServiceImpl.createPost(postDto),
				HttpStatus.CREATED);
		
		log.info("The New Post Updated...This is to send the resposnse from controller ");
		return responseEntity;

	}

	@GetMapping("/List")

	public  ResponseEntity<PostResponse> getAllPosts(
	@RequestParam(value = "pageNo" ,defaultValue = ApplicationConstant.DEFAULT_PAGE_NO,required = false)int pageNo,
	@RequestParam(value = "pageSize", defaultValue = ApplicationConstant.DEFAULT_PAGE_NO ,required = false)int pageSize,
	@RequestParam(value="sortBy",defaultValue = ApplicationConstant.DEFAULT_SORT_BY,required = false)String sortBy,
	@RequestParam(value="sortDir", defaultValue = ApplicationConstant.DEFAULT_DIR,required = false)String SortDir
	) {
		log.info("This is from getAllPost method..This for Provide all List");

		return new ResponseEntity<PostResponse>(postServiceImpl.getAllPosts(pageNo,pageSize,sortBy,SortDir), HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getById(@PathVariable("id") long id) throws ResourceNotFoundException {
		log.info("The getById for getting one post by Id");
		PostDto byId = postServiceImpl.getById(id);
		log.info("The post is submitted");
		
		return ResponseEntity.ok(byId);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("id") long id)
			throws ResourceNotFoundException {
		log.info("The updatePost for updating   post by Id");
		ResponseEntity<PostDto> responseEntity = new ResponseEntity<PostDto>(postServiceImpl.updatePost(postDto, id),
				HttpStatus.ACCEPTED);
		log.info("The post is updated Sucessfully");
		return responseEntity;

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePost(@PathVariable("id") long id) throws ResourceNotFoundException {
		log.info("The deletePost for delete post by Id");

		postServiceImpl.deletePost(id);
		log.info("The post deleted");
		return ResponseEntity.ok("Post successfully deleted");

	}

}
