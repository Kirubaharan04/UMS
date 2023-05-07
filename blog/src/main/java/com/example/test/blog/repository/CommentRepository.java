package com.example.test.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.test.blog.entity.Comment;

public interface CommentRepository  extends JpaRepository<Comment, Long>{

	List<Comment> findBypostId(long id);
}
