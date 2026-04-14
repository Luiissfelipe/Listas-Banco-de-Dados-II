package com.bancoDeDados2.prova.dto;

import com.bancoDeDados2.prova.model.Disciplina;

public record ListarDisciplinaDto(
        String codDisciplina,
        String nomeDisciplina,
        Integer vagas
) {
    public ListarDisciplinaDto(Disciplina disciplina) {
        this(
                disciplina.getCod(),
                disciplina.getNome(),
                disciplina.getVagas()
        );
    }
}
