package com.maskedsyntax.blog.service;

import com.maskedsyntax.blog.payload.CommentDTO;

import java.util.List;

public interface CommentService {

    // Post a comment
    CommentDTO createComment(Long postId, CommentDTO commentDTO);

    // Update a comment
    CommentDTO updateComment(
            Long postId, Long commendId, CommentDTO commentDTO);

    // Delete a comment
    void deleteComment(Long postId, Long commentId);

    // get Comments from a specific post
    List<CommentDTO> getCommentsByPostId(Long postId);

    // get comments from a specific post from a specific post
    List<CommentDTO> getCommentsByPostIdAndEmail(Long postId, String email);

    // get a specific comment from a specific post
    List<CommentDTO> getCommentsByCommentIdAndPostId(
            Long commentId, Long postId);
}
