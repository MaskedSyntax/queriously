package com.maskedsyntax.queriously.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Represents a Question entity in the system
 * 
 * This entity is used to store questions created by users, including content,
 * an optional image URL, and scheduling information.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions")
public class Question {

    /**
     * Unique identifier for the Question.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who created this question.
     * This is a Many-to-One relationship as multiple questions can be created by
     * one user.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The textual content of the question.
     */
    private String content;

    /**
     * URL of the image associated with the question, if any.
     */
    private String imageURL;

    /**
     * The scheduled date and time when the question should be published.
     */
    private Date scheduledAt;

    /**
     * Indicates whether the question has been published.
     */
    private Boolean published;

    /**
     * Timestamp indicating when the question was created.
     * Managed automatically by Hibernate.
     */
    @CreationTimestamp
    private Date createdAt;

    /**
     * Timestamp indicating when the question was last updated.
     * Managed automatically by Hibernate.
     */
    @UpdateTimestamp
    private Date updatedAt;

    /**
     * One question can have multiple answers.
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

}
