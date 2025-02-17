package com.maskedsyntax.queriously.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for creating or updating a Comment.
 * 
 * This DTO captures the necessary fields when a user submits a new comment or
 * updates an existing one.
 */
@Getter
@Setter
public class CommentRequestDTO {
    /**
     * The ID of the answer to which this comment belongs.
     * Required for top-level comments.
     */
    private Long answerId;

    /**
     * The ID of the parent comment, if this is a reply to another comment.
     * If this is a top-level comment, this field will be null.
     */
    private Long parentCommentId;

    /**
     * The textual content of the comment.
     */
    private String content;
}
