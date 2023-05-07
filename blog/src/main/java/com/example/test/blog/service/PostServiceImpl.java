package com.example.test.blog.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.test.blog.dto.CommentDto;
import com.example.test.blog.dto.PostDto;
import com.example.test.blog.entity.Comment;
import com.example.test.blog.entity.Post;
import com.example.test.blog.exception.ResourceNotFoundException;
import com.example.test.blog.repository.CommentRepository;
import com.example.test.blog.repository.PostRepository;
import com.example.test.utils.PostResponse;
@Service
public class PostServiceImpl implements PostService {
	private final Logger log = LogManager.getLogger(PostServiceImpl.class);
	
@Autowired
PostRepository postRepository;
@Autowired
CommentRepository commentRepository;
@Autowired
private ModelMapper mapper;
	@Override
	public PostDto createPost(PostDto postDto) {
		
		log.info("The CreatePost in service is Executed");
	
			Post post = new Post();
			post.setId(postDto.getId());
			post.setTitle(postDto.getTitle());
			post.setContent(postDto.getContent());
			post.setDescription(postDto.getDescription());
			Post newPost = postRepository.save(post);	
			PostDto postDtoResponse = new PostDto();
			
			postDtoResponse.setId(newPost.getId());
			postDtoResponse.setContent(newPost.getContent());
			postDtoResponse.setTitle(newPost.getTitle());
			postDtoResponse.setDescription(newPost.getDescription());
			log.info("The CreatePost in service  is Execution completed");
		return postDtoResponse;
		
	}
	
	
	public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir){
		log.info("The getAllPosts in service is Executed");
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? 
				Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
	PageRequest page = PageRequest.of(pageNo, pageSize, sort);
	
		
					Page<Post> findAll = postRepository.findAll(page);
					List<Post> content = findAll.getContent();
					System.err.println(content.get(0).getComment().size());;
					
		List<PostDto> collect = content.stream().map(i->maptoDto((i))).collect(Collectors.toList());
		
				
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(collect);
		postResponse.setPageNo(findAll.getNumber());
		postResponse.setPageSize(findAll.getSize());
		postResponse.setTotalPages(findAll.getTotalPages());
		postResponse.setTotalElements(findAll.getTotalElements());
		postResponse.setLast(findAll.isLast());
		
		log.info("The getAllPosts in service  is Execution completed");
		return postResponse;
		
	}

	@Override
	public PostDto getById(long id) throws ResourceNotFoundException {
			log.info("The getById in service is Executed");
			Post post = postRepository.findById(id).
			  orElseThrow(()->new ResourceNotFoundException("Post", "Id", id));
			log.info("The getById in service  is Execution completed");

			return maptoDto(post);
	}
	
	
	// convert entity  to dto  
	private PostDto maptoDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setContent(post.getContent());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
	Set<CommentDto> comment = new HashSet<CommentDto>();
	
		Set<CommentDto> comments=new HashSet<CommentDto>();
		
		for(Comment com:post.getComment()) {
			CommentDto comment1 = mapper.map(com, CommentDto.class);
			comments.add(comment1);
		}	
		postDto.setComment(comments);
		return postDto;
		
	}
	
	//covert dto entity
	private	 Post maptoEntity(PostDto postDto) {
		Post post = new Post();
		post.setId(postDto.getId());
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		return post;
		
	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) throws ResourceNotFoundException {
		
		log.info("The updatePost in service is Executed");
		Post post = postRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("post", "id", id));		
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		post.setTitle(postDto.getTitle());
		Post save = postRepository.save(post);
		log.info("The updatePost in service  is Execution completed");
		return maptoDto(save);
	}

	@Override
	public void deletePost(long id) throws ResourceNotFoundException {
		//Post p =postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
		log.info("The deletePost in service is Executed");
	postRepository.deleteById(id);
	log.info("The deletePost in service  is Execution completed");

	//postRepository.delete(p);
		
	}

	
	
	
	
	
	
	

}
