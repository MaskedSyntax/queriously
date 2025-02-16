package com.maskedsyntax.queriously.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequestDTO {
    private Long questionId; // The ID of the question to which the answer belongs
    private Long userId; // The ID of the user who provides the answer
    private String content; // The content of the answer
    private String imageURL; // The image URL
    private Date scheduledAt; // Optional: Date when the answer is scheduled
    private Boolean published; // Indicates if the answer is published
}
