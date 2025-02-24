package com.maskedsyntax.queriously.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maskedsyntax.queriously.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
