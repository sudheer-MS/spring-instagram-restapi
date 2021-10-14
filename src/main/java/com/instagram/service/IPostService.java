package com.instagram.service;

import java.util.List;

import com.instagram.model.Comment;
import com.instagram.model.Like;
import com.instagram.model.Post;
import com.instagram.model.User;

public interface IPostService {
	
	String createPost(Post post, String userName);
	
	String deletePost(int postId);
	
	Post getSinglePost(String userName, int postId);
	
	List<Post> getAllPostsFeed(User user);
	
	List<Post> getPostsOfUser(String userName);
	
	List<Like> getLikesOfPost(String userName, int postId);
	
	List<Comment> getCommentsOfPost(String userName, int postId);
	
}
