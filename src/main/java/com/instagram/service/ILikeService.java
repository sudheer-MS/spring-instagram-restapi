package com.instagram.service;

import com.instagram.model.User;

public interface ILikeService {
	
	String likeToPost(User user, int postId);
	
	String removeLike(User user, int likeId, int postId);
	
}
