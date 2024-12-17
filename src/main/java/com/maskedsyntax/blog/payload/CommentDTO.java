package com.maskedsyntax.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    private Long id;

    @NotEmpty(message = "Name must not be null or empty")
    private String name;

    @NotEmpty(message = "Email must not be null or empty")
    private String email;

    @NotEmpty(message = "Name must not be null or empty")
    @Size(min = 2, message = "Comment must have at least 2 characters")
    private String body;

    private Date created;
    private Date modified;
}
