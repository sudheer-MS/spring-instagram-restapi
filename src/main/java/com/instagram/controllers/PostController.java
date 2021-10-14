package com.instagram.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.model.Comment;
import com.instagram.model.Like;
import com.instagram.model.Post;
import com.instagram.model.User;
import com.instagram.service.IPostService;

@RestController
@RequestMapping("/instagram-api")
public class PostController {

	IPostService postService;

	@Autowired
	public void setPostService(IPostService postService) {
		this.postService = postService;
	}
	
	@PostMapping("/post/{userName}")
	ResponseEntity<String> createPost(@RequestBody Post post, @PathVariable("userName") String userName){
		String response = postService.createPost(post, userName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "create a post api");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(response);
	}
	
	@DeleteMapping("/post/{postId}")
	ResponseEntity<String> deletePost(@PathVariable("postId") int postId){
		postService.deletePost(postId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "delete a post api");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body("Post Deleted Successfully");
	}
	
	@GetMapping("/post/{postId}")
	ResponseEntity<Post> getSinglePost(@RequestBody User user, @PathVariable("postId") int postId){
		Post singlePost = postService.getSinglePost(user.getUserName(), postId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get a single post api");
		return ResponseEntity.ok().headers(headers).body(singlePost);
	}
	
	@GetMapping("/post/feed")
	ResponseEntity<List<Post>> getPostsFeed(@RequestBody User user){
		List<Post> allPosts = postService.getAllPostsFeed(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get all posts who the user will follow api");
		return ResponseEntity.ok().headers(headers).body(allPosts);
	}
	
	@GetMapping("/post/user")
	ResponseEntity<List<Post>> getAllPostsByUser(@RequestBody User user){
		List<Post> allPostsOfUser = postService.getPostsOfUser(user.getUserName());
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get all posts posted by the user api");
		return ResponseEntity.ok().headers(headers).body(allPostsOfUser);
	}
	
	@GetMapping("/post/likes/{postId}")
	ResponseEntity<List<Like>> getLikesOfPost(@RequestBody User user, @PathVariable("postId") int postId){
		List<Like> likesOfPost = postService.getLikesOfPost(user.getUserName(), postId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get likes of post who the user follows api");
		return ResponseEntity.ok().headers(headers).body(likesOfPost);
	}
	
	@GetMapping("/post/comments/{postId}")
	ResponseEntity<List<Comment>> getCommentsOfPost(@RequestBody User user, @PathVariable("postId") int postId){
		List<Comment> commentsOfPost = postService.getCommentsOfPost(user.getUserName(), postId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get comments of post who the user follows api");
		return ResponseEntity.ok().headers(headers).body(commentsOfPost);
	}

}
