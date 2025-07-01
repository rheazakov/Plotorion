package com.plotorion.plotorion.models;


import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<Book> books;
}