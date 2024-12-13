package com.maskedsyntax.blog.controller;

import com.maskedsyntax.blog.payload.CommentDTO;
import com.maskedsyntax.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(
            CommentService commentService
    ) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentDTO commentDTO
    ) {
        return new ResponseEntity<>(
                commentService.createComment(postId, commentDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping("{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(
            @PathVariable("postId") Long postId
    ) {
        return ResponseEntity.ok(
                commentService.getCommentsByPostId(postId));
    }

}
