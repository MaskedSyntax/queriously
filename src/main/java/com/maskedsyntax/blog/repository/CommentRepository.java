package com.maskedsyntax.blog.repository;

import com.maskedsyntax.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
