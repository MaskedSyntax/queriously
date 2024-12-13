package com.maskedsyntax.blog.payload;

import lombok.Data;

import java.util.Date;

@Data
public class PostDTO {

    private Long id;
    private String title;
    private String description;
    private String content;
    private String author;
    private Date createdAt;
    private Date updatedAt;
}
