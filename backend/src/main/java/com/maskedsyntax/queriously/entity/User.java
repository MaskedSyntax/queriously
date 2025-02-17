package com.maskedsyntax.queriously.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Represents a User entity in the system.
 * 
 * This entity maps to the "users" table in the database and stores user-related information,
 * including a unique username, email, password hash, and associated roles. It also tracks when
 * the user was created and last updated.
 * 
 * 
 * The class uses Lombok annotations to automatically generate boilerplate code like getters,
 * setters, and constructors.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    /**
     * Unique identifier for the User.
     * Auto-generated using the identity strategy.
     */    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Unique username for the User.
     * This field is mandatory and must be unique.
     */
    @Column(nullable = false, unique = true)
    private String username;
    
    /**
     * The full name or display name of the User.
     */
    private String name;
    
    /**
     * Unique email address for the User.
     * This field is mandatory and must be unique.
     */
    @Column(nullable = false, unique = true)
    private String email;
    
    /**
     * The hashed password of the User.
     * This field is mandatory.
     */
    @Column(nullable = false)
    private String password;
    
    /**
     * Timestamp indicating when the User was created.
     * Automatically managed by Hibernate.
     */
    @CreationTimestamp
    private Date createdAt;
    
    /**
     * Timestamp indicating when the User was last updated.
     * Automatically managed by Hibernate.
     */
    @UpdateTimestamp
    private Date updatedAt;

    /**
     * The roles associated with the User.
     * Represents a many-to-many relationship with the Role entity.
     * This relationship is eagerly fetched and uses a join table "user_roles" to link users and roles.
     */
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = { CascadeType.MERGE, CascadeType.REFRESH}
    )
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    /**
     * One user can create multiple questions.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    /**
     * One user can create multiple answers.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    /**
     * One user can create multiple comments.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
