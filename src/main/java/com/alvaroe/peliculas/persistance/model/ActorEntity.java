package com.alvaroe.peliculas.persistance.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "actors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int birthYear;
    private Integer deathYear;
}
