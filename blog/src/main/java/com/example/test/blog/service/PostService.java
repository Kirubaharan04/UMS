package com.example.test.blog.service;

import java.util.List;

import com.example.test.blog.dto.PostDto;
import com.example.test.blog.exception.ResourceNotFoundException;
import com.example.test.utils.PostResponse;

public interface PostService {
public PostDto createPost(PostDto postDto);
public PostResponse getAllPosts(int pageNo,int pageSize, String sortBy,String sortDir);
public PostDto getById(long id) throws ResourceNotFoundException;
public PostDto updatePost(PostDto postDto ,long id)throws ResourceNotFoundException;
public void deletePost(long id)throws ResourceNotFoundException;

}
