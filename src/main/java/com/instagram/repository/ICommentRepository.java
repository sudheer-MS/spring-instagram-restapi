package com.instagram.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instagram.model.Comment;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Integer> {
	
}
