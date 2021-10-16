package com.instagram.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author SudheerMS
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Comment {
	
	@Id
	@GeneratedValue(generator = "comment_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "comment_gen", sequenceName = "comment_seq", initialValue = 1,allocationSize = 1)
	private Integer commentId;
	private String comment;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private LocalDateTime dateTime;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	@JsonIgnore
	private Post post;
	
}
