package com.bancoDeDados2.prova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alunos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private Long cpf;

    @Column(nullable = false, unique = true, length = 50)
    @Email
    private String email;

    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private Sexo sexo;


}
