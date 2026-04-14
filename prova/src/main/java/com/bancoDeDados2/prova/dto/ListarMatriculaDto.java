package com.bancoDeDados2.prova.dto;

import com.bancoDeDados2.prova.model.Matricula;
import jakarta.validation.constraints.NotNull;

public record ListarMatriculaDto(
        Long idMatricula,
        Long semestre,
        String nomeAluno,
        String nomeCurso,
        String nomeDisciplina
) {
    public ListarMatriculaDto(Matricula matricula) {
        this(
                matricula.getId(),
                matricula.getSemestre(),
                matricula.getAluno().getNome(),
                matricula.getCurso().getNome(),
                matricula.getDisciplina().getNome()
        );
    }
}
