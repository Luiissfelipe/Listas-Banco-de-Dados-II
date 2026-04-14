package com.bancoDeDados2.prova.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @Id
    @Column(length = 10)
    private String cod;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 50)
    private String coordenador;
}
