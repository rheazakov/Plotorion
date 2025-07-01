package com.plotorion.plotorion.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Data
@Entity
@Table(name = "chapter_parts")
public class ChapterPart {

    @Id
    @GeneratedValue
    private UUID id;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

    @ManyToOne
    @JoinColumn(name = "collaborator_id", nullable = false)
    private User collaborator;
}
