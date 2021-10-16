package com.instagram.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.exceptions.PostNotFoundException;
import com.instagram.exceptions.UserNotFoundException;
import com.instagram.model.Comment;
import com.instagram.model.Like;
import com.instagram.model.Post;
import com.instagram.model.User;
import com.instagram.repository.IPostRepository;
import com.instagram.repository.IUserRepository;

/**
 * @author SudheerMS
 *
 */
@Service
public class PostServiceImpl implements IPostService {

	IPostRepository postRepository;

	IUserRepository userRepository;

	@Autowired
	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setPostRepository(IPostRepository postRepository) {
		this.postRepository = postRepository;
	}

	LocalDateTime dateTime = LocalDateTime.now();
	Set<Comment> commentSet = new HashSet<Comment>();
	Set<Like> likeSet = new HashSet<Like>();

	@Override
	public String createPost(Post post, String userName) {
		User postUser = userRepository.findByUserName(userName);
		if (postUser == null) {
			throw new UserNotFoundException("you are not allowed to create a post. please register to create a post");
		}
		post.setDateTime(dateTime);
		post.setCommentSet(commentSet);
		post.setLikeSet(likeSet);
		post.setUser(postUser);
		postRepository.save(post);
		return "Post Created Successfully";
	}

	@Override
	public String deletePost(int postId) {
		postRepository.deleteById(postId);
		return "Post deleted successfully";
	}

	@Override
	public Post getSinglePost(String userName, int postId) throws PostNotFoundException {
		Post singlePost = postRepository.findById(postId)
				.orElseThrow(() -> new PostNotFoundException("No post exists with the given id"));

		userRepository.findByUserName(userName).getPostSet().stream().forEach(post -> {
			if (post.getPostId() != postId) {
				throw new PostNotFoundException("Unauthorized...! You cannot see this post");
			}
		});
		return singlePost;
	}

	@Override
	public List<Post> getAllPostsFeed(User user) {
		User postUser = userRepository.findByUserName(user.getUserName());
		List<Post> postFeedList = postRepository.getPostsFeed(postUser.getUserId());
		return postFeedList;
	}

	@Override
	public List<Post> getPostsOfUser(String userName) {
		List<Post> userPostList = postRepository.getPostsOfUser(userName);
		return userPostList;
	}

	@Override
	public List<Like> getLikesOfPost(String userName, int postId) throws PostNotFoundException {
		Post singlePost = postRepository.findById(postId)
				.orElseThrow(() -> new PostNotFoundException("No post exists with the given id"));
//		if (! singlePost.getUser().getUserName().equals(userName)) {
//			throw new PostNotFoundException("Unauthorized...! You cannot see this post Likes");
//		}
		List<Like> likes = new ArrayList<>(singlePost.getLikeSet());
		return likes;
	}

	@Override
	public List<Comment> getCommentsOfPost(String userName, int postId) throws PostNotFoundException {
		Post singlePost = postRepository.findById(postId)
				.orElseThrow(() -> new PostNotFoundException("No post exists with the given id"));
//		if (! singlePost.getUser().getUserName().equals(userName)) {
//			throw new PostNotFoundException("Unauthorized...! You cannot see this post Comments");
//		}
		List<Comment> comments = new ArrayList<>(singlePost.getCommentSet());
		return comments;
	}

}
