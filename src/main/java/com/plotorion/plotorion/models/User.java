package com.plotorion.plotorion.models;

import com.plotorion.plotorion.models.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String bio;

    private String avatarUrl;

    private Integer reputation = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role=Role.ROLE_USER;

    @Column(nullable = false, updatable= false)
    private Instant createdAt = Instant.now();

    @OneToMany(mappedBy = "owner")
    private List<Book> authoredBooks;

    @OneToMany(mappedBy = "user")
    private List<Proposal> proposals;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "editor")
    private List<Version> versions;
}
