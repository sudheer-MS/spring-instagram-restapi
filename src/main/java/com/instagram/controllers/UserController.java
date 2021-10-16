package com.instagram.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.model.User;
import com.instagram.service.IUserService;

/**
 * @author SudheerMS
 *
 */
@RestController
@RequestMapping("/instagram-api")
public class UserController {

	IUserService userService;

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping("/user/register")
	ResponseEntity<String> registerUser(@RequestBody User user) {
		userService.registerUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "user registration api");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("User Registered Successfully");
	}

	@PostMapping("/user/login")
	ResponseEntity<String> loginUser(@RequestBody User user) {
		String userName = user.getUserName();
		String password = user.getPassword();
		String result = userService.loginUser(userName, password);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "user Login api");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
	}

	@GetMapping("/user/details")
	User getUserDetails(@RequestBody User user) {
		return userService.getUserByName(user.getUserName());
	}

	@PostMapping("/user/follow-user/{followUser}")
	ResponseEntity<String> followUser(@RequestBody User user, @PathVariable("followUser") String followUserName) {
		User updatedUser = userService.followUser(user, followUserName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "follow one user api");
		return ResponseEntity.ok().headers(headers)
				.body(updatedUser.getUserName() + " followed " + followUserName + " successfully");
	}

	@GetMapping("/user/following-users")
	ResponseEntity<List<User>> getFollowingUsers(@RequestBody User user) {
		List<User> followingUsers = userService.getFollowingUsers(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get the list of users who follows the user api");
		return ResponseEntity.ok().headers(headers).body(followingUsers);
	}

	@GetMapping("/user/followers")
	ResponseEntity<List<User>> getFollowersOfUser(@RequestBody User user) {
		List<User> followers = userService.getFollowersOfUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get followers of user api");
		return ResponseEntity.ok().headers(headers).body(followers);
	}

}
