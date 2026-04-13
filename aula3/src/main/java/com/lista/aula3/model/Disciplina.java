package com.lista.aula3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "disciplinas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Disciplina {
    @Id
    @Column(length = 10)
    private String cod;

    @Column(nullable = false, length = 50)
    private String nome;
}
