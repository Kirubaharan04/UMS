package com.example.test.blog.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Post", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
	
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO, generator="NAME_SEQUENCE")
	@GenericGenerator(name= "NAME_SEQUENCE", strategy = "increment")
	@Column(name="postId")
	private long id;
	@Column(name = "title",nullable = true)
	private String title;
	@Column(name = "description",nullable = true)
	private String description;
	@Column(name = "content",nullable = true)
	
	private String content;
	

@OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	private Set<Comment> comment;
	
}














