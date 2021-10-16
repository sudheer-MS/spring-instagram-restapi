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

import com.instagram.model.User;
import com.instagram.service.ILikeService;

/**
 * @author SudheerMS
 *
 */
@RestController
@RequestMapping("/instagram-api")
public class LikeController {

	ILikeService likeService;

	@Autowired
	public void setLikeService(ILikeService likeService) {
		this.likeService = likeService;
	}

	@PostMapping("/like/post/{postId}")
	ResponseEntity<String> LikePost(@RequestBody User user,@PathVariable("postId") int postId){
		String response = likeService.likeToPost(user, postId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Like a post api");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(response);
	}

	@DeleteMapping("/like/post/{postId}/{likeId}")
	ResponseEntity<String> removeLike(@RequestBody User user, @PathVariable("postId") int postId, @PathVariable("likeId") int likeId){
		String response = likeService.removeLike(user, likeId, postId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "remove a like api");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(response);
	}

}
