package com.maskedsyntax.blog.repository;

import com.maskedsyntax.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

    // get comments by postId and userId
    List<Comment> findByPostIdAndEmail(Long postId, String email);

    List<Comment> findByPostIdAndId(Long postId, Long commentId);
}
