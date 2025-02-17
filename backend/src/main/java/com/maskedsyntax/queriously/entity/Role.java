package com.maskedsyntax.queriously.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a role within the system.
 * 
 * This entity is mapped to the "roles" table in the database and is used to
 * define
 * various roles for access control and permissions.
 * 
 * 
 * Lombok annotations
 * (@Getter, @Setter, @AllArgsConstructor, @NoArgsConstructor) are used
 * to automatically generate the necessary boilerplate code such as getters,
 * setters, and constructors.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    /**
     * Unique identifier for the Role.
     * This ID is auto-generated using the identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the Role.
     * This field is required and must not be null.
     */
    @Column(nullable = false)
    private String roleName;

    /**
     * A role can be assigned to multiple users.
     */
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
