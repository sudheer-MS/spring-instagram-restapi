package com.instagram.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instagram.model.Like;

/**
 * @author SudheerMS
 *
 */
@Repository
public interface ILikeRepository extends JpaRepository<Like, Integer> {
	
}
