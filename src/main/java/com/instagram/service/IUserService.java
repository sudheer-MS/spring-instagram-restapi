package com.instagram.service;

import java.util.List;

import com.instagram.model.User;

/**
 * @author SudheerMS
 *
 */
public interface IUserService {
	
	String registerUser(User user);
	
	String loginUser(String userName, String password);
	
	User updateUser(User user);
	
	List<User> getFollowersOfUser(User user);
	
	List<User> getFollowingUsers(User user);
	
	User getUserByName(String name);
	
	User followUser(User user, String UserName);
	
}
