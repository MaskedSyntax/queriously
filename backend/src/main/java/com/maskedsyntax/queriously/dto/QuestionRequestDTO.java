package com.maskedsyntax.queriously.dto;

import java.util.Date;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDTO {
    private Long userId;
    private String content;
    private String imageUrl;
    private Date scheduledAt;
    private Boolean published;
}
