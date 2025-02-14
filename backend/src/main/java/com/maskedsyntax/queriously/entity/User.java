package com.maskedsyntax.queriously.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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
@Getter
@Setter
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
    private String password_hash;
    
    /**
     * Timestamp indicating when the User was created.
     * Automatically managed by Hibernate.
     */
    @CreationTimestamp
    private Date created_at;
    
    /**
     * Timestamp indicating when the User was last updated.
     * Automatically managed by Hibernate.
     */
    @UpdateTimestamp
    private Date updated_at;

    /**
     * The roles associated with the User.
     * Represents a many-to-many relationship with the Role entity.
     * This relationship is eagerly fetched and uses a join table "user_roles" to link users and roles.
     */
    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;
}
