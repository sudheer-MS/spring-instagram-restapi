package com.instagram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.instagram.model.Post;

@Repository
public interface IPostRepository extends JpaRepository<Post, Integer> {
	
	@Query(value = "SELECT * FROM post WHERE user_id IN (SELECT following_id FROM follower WHERE follower_id  = ?1)", nativeQuery = true)
	List<Post> getPostsFeed(int userId);
	
	@Query("from Post p inner join p.user u where u.userName = ?1")
	List<Post> getPostsOfUser(String userName);

}
