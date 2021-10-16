package com.instagram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.instagram.model.User;

/**
 * @author SudheerMS
 *
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);
	
	@Query(value = "SELECT * FROM user WHERE user_id IN (SELECT follower_id FROM follower WHERE following_id = ?1)", nativeQuery = true)
	List<User> findFollowersList(int userId);
	
}
