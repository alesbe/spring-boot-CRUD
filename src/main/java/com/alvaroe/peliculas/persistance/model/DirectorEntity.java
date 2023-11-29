package com.alvaroe.peliculas.persistance.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "directors")
@Data
@NoArgsConstructor
public class DirectorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int birthYear;
    private Integer deathYear;
}
