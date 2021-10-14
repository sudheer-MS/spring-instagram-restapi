package com.instagram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.model.Comment;
import com.instagram.model.User;
import com.instagram.service.ICommentService;

@RestController
@RequestMapping("/instagram-api")
public class CommentController {
	
	ICommentService commentService;
	
	@Autowired
	public void setCommentService(ICommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/comment/{comment}/post/{postId}")
	ResponseEntity<Comment> commentToPost(@RequestBody User user,@PathVariable("comment") String comment, @PathVariable("postId") int postId){
		Comment newComment = commentService.commentToPost(user, comment, postId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Comment to a post api");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(newComment);
	}
	
	@DeleteMapping("/like/post/{postId}/{likeId}")
	ResponseEntity<String> removeComment(@RequestBody User user, @PathVariable("postId") int postId, @PathVariable("likeId") int likeId){
		String response = commentService.removeComment(user, likeId, postId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "remove a comment api");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(response);
	}

}
