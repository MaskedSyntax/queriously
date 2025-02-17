package com.maskedsyntax.queriously.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for returning Comment details.
 * 
 * This DTO is used to send structured responses containing comment details, 
 * including nested replies if applicable.
 */
@Getter
@Setter
public class CommentResponseDTO {
        /**
     * Unique identifier of the comment.
     */
    private Long id;

    /**
     * The ID of the answer to which this comment belongs.
     */
    private Long answerId;

    /**
     * The ID of the parent comment, if this is a reply.
     * If this is a top-level comment, this field will be null.
     */
    private Long parentCommentId;

    /**
     * The ID of the user who created this comment.
     */
    private Long userId;

    /**
     * The textual content of the comment.
     */
    private String content;

    /**
     * Timestamp when the comment was created.
     */
    private Date createdAt;

    /**
     * Timestamp when the comment was last updated.
     */
    private Date updatedAt;

    /**
     * List of replies to this comment (nested comments).
     */
    private List<CommentResponseDTO> replies;
}
