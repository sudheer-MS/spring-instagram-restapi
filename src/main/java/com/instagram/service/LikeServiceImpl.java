package com.instagram.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.instagram.exceptions.PostNotFoundException;
import com.instagram.exceptions.UserNotFoundException;
import com.instagram.model.Like;
import com.instagram.model.Post;
import com.instagram.model.User;
import com.instagram.repository.ILikeRepository;
import com.instagram.repository.IPostRepository;
import com.instagram.repository.IUserRepository;

public class LikeServiceImpl implements ILikeService {
	
	ILikeRepository likeRepository;
	
	IPostRepository postRepository;

	IUserRepository userRepository;
	
	@Autowired
	public void setLikeRepository(ILikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	@Autowired
	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setPostRepository(IPostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	LocalDateTime dateTime = LocalDateTime.now();

	Like like = new Like();

	@Override
	public String likeToPost(User user, int postId) {
		User likeUser = userRepository.findByUserName(user.getUserName());
		if (likeUser == null) {
			throw new UserNotFoundException("no user found");
		}
		Post postToLike = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("no post found"));
		like.setDateTime(dateTime);
		like.setUser(likeUser);
		like.setPost(postToLike);
		likeRepository.save(like);
		return "you liked the post";
	}

	@Override
	public String removeLike(User user, int likeId, int postId) {
		Like getLike = likeRepository.findById(likeId).orElseThrow(() -> new RuntimeException("something went wrong"));
		if (getLike.getUser().getUserId() != user.getUserId()) {
			throw new UserNotFoundException("you cannot unlike others post");
		}
		if (getLike.getPost().getPostId() != postId) {
			throw new RuntimeException("something went wrong");
		}
		likeRepository.deleteById(likeId);
		return "your comment is deleted";
	}

}
