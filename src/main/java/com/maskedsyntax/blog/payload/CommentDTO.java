package com.maskedsyntax.blog.payload;

import com.maskedsyntax.blog.entity.Post;
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
    private Post post;
}
