package com.maskedsyntax.queriously.service;

import java.util.List;

import com.maskedsyntax.queriously.dto.CommentRequestDTO;
import com.maskedsyntax.queriously.dto.CommentResponseDTO;

public interface CommentService {
    // save comment
    CommentResponseDTO saveComment(CommentRequestDTO commentRequestDTO);
    
    // update comment 
    CommentResponseDTO updateComment(Long id, CommentRequestDTO commentRequestDTO);
    
    // delete comment
    void deleteComment(Long id);

    // list all comments
    List<CommentResponseDTO> getComments();

    // get particular comment -- not exactly needed I guess
    CommentResponseDTO getCommentById(Long id);
}
