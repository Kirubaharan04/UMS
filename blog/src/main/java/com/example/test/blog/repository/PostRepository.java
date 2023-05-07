package com.example.test.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.test.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
