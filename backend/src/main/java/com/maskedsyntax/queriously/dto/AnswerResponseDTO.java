package com.maskedsyntax.queriously.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponseDTO {
    
    private Long id;             // Answer ID
    private Long questionId;     // The ID of the associated question
    private Long userId;         // The ID of the user who provided the answer
    private String content;      // The content of the answer
    private String imageURL;
    private Date scheduledAt;    // Scheduled date (if applicable)
    private Boolean published; // Indicates if the answer is published
    private Date createdAt;      // The timestamp when the answer was created
    private Date updatedAt;      // The timestamp when the answer was last updated
}
