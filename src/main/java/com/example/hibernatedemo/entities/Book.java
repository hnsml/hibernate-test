package com.example.hibernatedemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BOOKS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "PUBLISHYEAR")
    private int publishYear;
    @Column(name = "GENRE")
    private String genre;
    @Column(name = "PAGECOUNT")
    private int pageCount;
    @Column(name = "DESCRIPTION")
    private String description;
}
