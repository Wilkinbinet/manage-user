package com.manage.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Entity representing a user in the database.
 */
@Data
@Entity
@Table(name = "users")
public class User {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    /**
     * Name of the user.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Email of the user, must be unique.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Password of the user.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Token of the user.
     */
    @Column(nullable = false)
    private String token;

    /**
     * Date and time when the user was created.
     */
    @Column(nullable = false)
    private LocalDateTime created;

    /**
     * Date and time when the user was last modified.
     */
    private LocalDateTime modified;

    /**
     * Date and time of the user's last login.
     */
    @Column(nullable = false)
    private LocalDateTime lastLogin;

    /**
     * Indicates whether the user is active.
     */
    @Column(nullable = false)
    private boolean isActive;

    /**
     * Set of phone numbers associated with the user.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Phone> phones;
}
