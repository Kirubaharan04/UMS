package com.example.test.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.test.blog.BlogApplication;
import com.example.test.blog.dto.CommentDto;
import com.example.test.blog.entity.Comment;
import com.example.test.blog.entity.Post;
import com.example.test.blog.exception.BlogException;
import com.example.test.blog.exception.ResourceNotFoundException;
import com.example.test.blog.repository.CommentRepository;
import com.example.test.blog.repository.PostRepository;

@Service
public class CommentServiceimpl implements CommentService {
		private final Logger log = LogManager.getLogger(CommentServiceimpl.class);
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(long postid, CommentDto commentdto) 
			throws ResourceNotFoundException {
		log.info("The createComment  in comment service is Executed");
		
		Comment comment = mapper.map(commentdto, Comment.class);

		Post post = postRepository.findById(postid)
				.orElseThrow(() -> new ResourceNotFoundException("post", "id", postid));

		comment.setPost(post);

		Comment save = commentRepository.save(comment);

		// CommentDto mapToDto = mapToDto(save);
		CommentDto mapToDto = mapper.map(save, CommentDto.class);
		log.info("The createComment  in comment service is Execution completed");
		return mapToDto;
	}

	@Override
	public List<CommentDto> findCommentsByPostId(long id) {
		log.info("findCommentsByPostId :commentService  is excuted");
		List<Comment> comments = commentRepository.findBypostId(id);
		log.info("findCommentsByPostId :commentService  is completed");
		return comments.stream().map(i -> mapper.map(i, CommentDto.class)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(long pid, long cid) throws ResourceNotFoundException, BlogException {
		log.info("getCommentById :commentService  is excuted");
		Post post = postRepository.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("postId", "post", pid));
		Comment comment = commentRepository.findById(cid)
				.orElseThrow(() -> new ResourceNotFoundException("commentId", "Commentid", cid));
		if (!(comment.getPost().getId() == (post.getId()))) {
			throw new BlogException(HttpStatus.BAD_REQUEST, "comments doesnot belong to post");
		}
			CommentDto mapToDto = mapper.map(comment, CommentDto.class);
			log.info("getCommentById :commentService  is completed");
		return mapToDto;
	}

	@Override
	public CommentDto updateComment(long pid, long cid, CommentDto commentDto)
			throws ResourceNotFoundException, BlogException {
		log.info("updateComment :commentService  is excuted");
		Post post = postRepository.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("postId", "post", pid));
		Comment comment = commentRepository.findById(cid)
				.orElseThrow(() -> new ResourceNotFoundException("comment", "comment", cid));
		if (!(comment.getPost().getId() == post.getId())) {
			throw new BlogException(HttpStatus.BAD_REQUEST, "Not Belongs to Comments");
		}

		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		comment.setName(commentDto.getName());

		Comment save = commentRepository.save(comment);
			CommentDto mapToDto = mapper.map(save, CommentDto.class);
			log.info("updateComment :commentService  is completed");

		return mapToDto;
	}

	@Override
	public void deleteComment(long cid){
		log.info("deleteComment :commentService  is excuted");

		commentRepository.deleteById(cid);
		log.info("deleteComment :commentService  is completed");

	}}



