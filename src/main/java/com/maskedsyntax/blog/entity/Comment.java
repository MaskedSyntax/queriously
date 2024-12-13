package com.maskedsyntax.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.maskedsyntax.blog.utils.AppConstants.COLUMN_POST_ID;
import static com.maskedsyntax.blog.utils.AppConstants.COMMENTS_TABLE;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = COMMENTS_TABLE)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String body;

    private Date created;
    private Date modified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_POST_ID, nullable = false)
    private Post post;
}
