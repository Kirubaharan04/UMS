
package com.example.test.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "NAME_SEQUENCE")
	@GenericGenerator(name = "NAME_SEQUENCE", strategy = "increment")
	@Column(name = "CommentId")
	private Long id;

	private String name;

	private String email;

	private String body;
@ManyToOne(fetch = FetchType.EAGER) 
@JoinColumn(name = "postFK",nullable = false)
	private Post post;

}
