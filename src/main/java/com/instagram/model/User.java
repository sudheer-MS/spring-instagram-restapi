package com.instagram.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User {
	@Id
	@GeneratedValue(generator = "user_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "user_gen", sequenceName = "user_seq", initialValue = 1,allocationSize = 1)
	private Integer userId;
	private String userName;
	private String password;
	private String gender;
	
	@ManyToMany
	@JoinTable(name = "follower",
	   joinColumns = { @JoinColumn(name = "follower_id") },
	   inverseJoinColumns = { @JoinColumn(name = "following_id") }
	 )
	@JsonIgnore
	private Set<User> userSet;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Set<Post> postSet;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private Set<Comment> commentSet;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private Set<Like> likeSet;
	
}
