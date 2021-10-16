package com.instagram.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.exceptions.UserAlreadyExistsException;
import com.instagram.exceptions.UserNotFoundException;
import com.instagram.model.Comment;
import com.instagram.model.Like;
import com.instagram.model.Post;
import com.instagram.model.User;
import com.instagram.repository.IUserRepository;

/**
 * @author SudheerMS
 *
 */
@Service
public class UserServiceImpl implements IUserService {

	IUserRepository userRepository;

	@Autowired

	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	User emptyUser = new User();

	Set<User> regUserSet = new HashSet<User>();
	Set<Post> regPostSet = new HashSet<Post>();
	Set<Comment> regCommentSet = new HashSet<Comment>();
	Set<Like> regLikeSet = new HashSet<Like>();

	@Override
	public String registerUser(User user) throws UserAlreadyExistsException {
		User getUser = userRepository.findByUserName(user.getUserName());
		user.setUserSet(regUserSet);
		user.setPostSet(regPostSet);
		user.setCommentSet(regCommentSet);
		user.setLikeSet(regLikeSet);
		if (getUser == null) {
			userRepository.save(user);
		} else {
			throw new UserAlreadyExistsException("Given UserName Already Exists. Please give a new UserName");
		}
		return "User Registered Successfully";
	}

	@Override
	public String loginUser(String userName, String password) {
		User user = userRepository.findByUserName(userName);
		if (user.getUserName() == null) {
			throw new UserNotFoundException("No user exists please enter a valid user name");
		}
		if (user.getUserName().equals(user.getUserName())) {
			if (user.getPassword().equals(password)) {
				return "user logged in successfully";
			} else {
				return "invalid password";
			}
		}
		return "invalid user name";
	}
	
	@Override
	public User updateUser(User user) {
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}

	@Override
	public List<User> getFollowingUsers(User user) {
		User oneUser = userRepository.findByUserName(user.getUserName());
		return new ArrayList<User>(oneUser.getUserSet());
	}

	@Override
	public List<User> getFollowersOfUser(User user) {
		return userRepository.findFollowersList(user.getUserId());
	}

	@Override
	public User getUserByName(String name) {
		return userRepository.findByUserName(name);
	}

	@Override
	public User followUser(User user, String UserName) {
		User existingUser = userRepository.findByUserName(user.getUserName());
		User followUser = userRepository.findByUserName(UserName);
		Set<User> followingList = existingUser.getUserSet();
		followingList.add(followUser);
		existingUser.setUserSet(followingList);
		userRepository.save(existingUser);
 		return existingUser;
	}

}
