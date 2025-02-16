package com.maskedsyntax.queriously.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maskedsyntax.queriously.entity.User;

import lombok.*;

import java.util.Date;

/**
 * A Data Transfer Object used to return question details to the client 
 * after creation, retrieval, or update.
 * <p>
 * This DTO includes the question's unique identifier, ownership, content, 
 * publication status, and timestamp information.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO {

    /**
     * The unique identifier of the question.
     */
    private Long id;

    /**
     * The unique identifier of the user who created or owns the question.
     */
    private Long userId;

    /**
     * The textual content of the question.
     */
    private String content;

    /**
     * The URL of an image associated with the question, if any.
     */
    private String imageURL;

    /**
     * The scheduled date and time when the question should be published.
     */
    private Date scheduledAt;

    /**
     * Indicates whether the question has been published.
     */
    private Boolean published;

    /**
     * Timestamp indicating when the question was created.
     * Formatted in ISO-8601 with time zone information.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date createdAt;

    /**
     * Timestamp indicating when the question was last updated.
     * Formatted in ISO-8601 with time zone information.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date updatedAt;
}
