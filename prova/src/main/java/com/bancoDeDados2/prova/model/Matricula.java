package com.bancoDeDados2.prova.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "matriculas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 6)
    private Long semestre;

    @ManyToOne
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "cod_curso", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "cod_disciplina", nullable = false)
    private Disciplina disciplina;

    public Matricula(Long semestre, Aluno aluno, Curso curso, Disciplina disciplina) {
        this.semestre = semestre;
        this.aluno = aluno;
        this.curso = curso;
        this.disciplina = disciplina;
    }
}
