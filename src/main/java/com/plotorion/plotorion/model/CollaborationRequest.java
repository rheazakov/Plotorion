package com.plotorion.plotorion.model;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "collaboration_requests")
@Data
public class CollaborationRequest {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

    @Lob
    private String description;

    @Column(nullable = false)
    private Instant deadline;

    @Column(nullable = false)
    private Integer maxCollaborators;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<Proposal> proposals;
}