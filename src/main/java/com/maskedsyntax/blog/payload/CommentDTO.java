package com.maskedsyntax.blog.payload;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    private Long id;
    private String name;
    private String email;
    private String body;
    private Date created;
    private Date modified;
}
