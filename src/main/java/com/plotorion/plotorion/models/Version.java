package com.plotorion.plotorion.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "versions")
public class Version {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "editor_id", nullable = false)
    private User editor;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}