package com.maskedsyntax.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.maskedsyntax.blog.utils.AppConstants.COLUMN_TITLE;
import static com.maskedsyntax.blog.utils.AppConstants.POST_TABLE;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = POST_TABLE, uniqueConstraints = {@UniqueConstraint(columnNames = {COLUMN_TITLE})}
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String content;

    @Column()
    private String author;

    @Column(nullable = false)
    private Date createdAt;

    @Column()
    private Date updatedAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

}
