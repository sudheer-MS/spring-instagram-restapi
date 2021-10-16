package com.instagram.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.exceptions.PostNotFoundException;
import com.instagram.exceptions.UserNotFoundException;
import com.instagram.model.Comment;
import com.instagram.model.Post;
import com.instagram.model.User;
import com.instagram.repository.ICommentRepository;
import com.instagram.repository.IPostRepository;
import com.instagram.repository.IUserRepository;

/**
 * @author SudheerMS
 *
 */
@Service
public class CommentServiceImpl implements ICommentService {

	ICommentRepository commentRepository;

	IPostRepository postRepository;

	IUserRepository userRepository;

	@Autowired
	public void setCommentRepository(ICommentRepository commentRepository) {
		this.commentRepository = commentRepository;
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

	@Override
	public Comment commentToPost(User user, String comment, int postId) {
		User commentUser = userRepository.findByUserName(user.getUserName());
		if (commentUser == null) {
			throw new UserNotFoundException("no user found");
		}
		Post postToComment = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("no post found"));
		Comment newComment = new Comment();
		newComment.setComment(comment);
		newComment.setDateTime(dateTime);
		newComment.setPost(postToComment);
		newComment.setUser(commentUser);
		Comment createComment = commentRepository.save(newComment);
		return createComment;
	}

	@Override
	public String removeComment(User user, int commentId, int postId) {
		Comment getComment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("something went wrong"));
		if (getComment.getUser().getUserId() != user.getUserId()) {
			throw new UserNotFoundException("you cannot unlike others post");
		}
		if (getComment.getPost().getPostId() != postId) {
			throw new RuntimeException("something went wrong");
		}
		commentRepository.deleteById(commentId);
		return "you deleted the comment successfully";
	}

}
