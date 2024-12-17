package com.maskedsyntax.blog.controller;

import com.maskedsyntax.blog.payload.CommentDTO;
import com.maskedsyntax.blog.service.CommentService;
import jakarta.validation.Valid;
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
            @Valid @RequestBody CommentDTO commentDTO
    ) {
        return new ResponseEntity<>(
                commentService.createComment(postId, commentDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentDTO commentDTO
    ) {
        return ResponseEntity.ok(
                commentService.updateComment(postId, commentId, commentDTO));
    }

    @GetMapping("{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(
            @PathVariable("postId") Long postId
    ) {
        return ResponseEntity.ok(
                commentService.getCommentsByPostId(postId));
    }

    @DeleteMapping("{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId
    ) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>(
                "Comment Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("{postId}/comments/by-email")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostIdAndEmail(
            @PathVariable("postId") Long postId, @RequestParam String email) {
        return new ResponseEntity<>(
                commentService.getCommentsByPostIdAndEmail(postId, email),
                HttpStatus.OK
        );
    }

    @GetMapping("{postId}/comments/{commentId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByCommentIdAndPostId(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId
    ) {
        return new ResponseEntity<>(
                commentService.getCommentsByCommentIdAndPostId(
                        commentId, postId), HttpStatus.OK
        );
    }

}
