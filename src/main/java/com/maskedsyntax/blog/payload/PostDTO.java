package com.maskedsyntax.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class PostDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 255, message = "Title length must be between 2 and 255 (both inclusive)")
    private String title;

    @NotEmpty
    @Size(min = 10, max = 255, message = "Description length must be between 2 and 255 (both inclusive)")
    private String description;

    @NotEmpty
    @Size(min = 10, message = "Content must be at least 10 characters.")
    private String content;

    @NotEmpty
    private String author;
    private Date createdAt;
    private Date updatedAt;
}
