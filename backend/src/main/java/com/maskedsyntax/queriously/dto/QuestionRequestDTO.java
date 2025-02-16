package com.maskedsyntax.queriously.dto;

import java.util.Date;

import com.maskedsyntax.queriously.entity.User;

import lombok.*;

/**
 * A Data Transfer Object used to capture question details from the client 
 * when creating or updating a question.
 * <p>
 * This DTO contains information such as the user ID, question content, 
 * and optional publishing details.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDTO {

    /**
     * The unique identifier of the user who created or owns the question.
     */
    private User user;

    /**
     * The textual content of the question.
     */
    private String content;

    /**
     * The URL of an image associated with the question, if any.
     */
    private String imageUrl;

    /**
     * The scheduled date and time when the question should be published.
     */
    private Date scheduledAt;

    /**
     * Indicates whether the question should be published.
     */
    private Boolean published;
}
