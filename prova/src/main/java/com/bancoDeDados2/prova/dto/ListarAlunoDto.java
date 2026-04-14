package com.bancoDeDados2.prova.dto;

import com.bancoDeDados2.prova.model.Aluno;
import com.bancoDeDados2.prova.model.Sexo;

public record ListarAlunoDto(
        Long idAluno,
        String nomeAluno,
        Sexo sexo,
        String campus
) {
    public ListarAlunoDto(Aluno aluno) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getSexo(),
                aluno.getCampus().name()
        );
    }
}
