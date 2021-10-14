package com.instagram.service;

import com.instagram.model.Comment;
import com.instagram.model.User;

public interface ICommentService {
	
	Comment commentToPost(User user, String comment, int postId);
	
	String removeComment(User user, int commentId, int postId);
	
}
