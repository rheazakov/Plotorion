package com.plotorion.plotorion.model;


import com.plotorion.plotorion.model.enums.ChapterStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "chapters")
public class Chapter {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChapterStatus chapterStatus = ChapterStatus.DRAFT;

    private Integer position;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    private Instant lastEditedAt;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<ChapterPart> parts;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<CollaborationRequest> collaborationRequests;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<Version> versions;
}
