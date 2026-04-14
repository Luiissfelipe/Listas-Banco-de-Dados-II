package com.bancoDeDados2.prova.dto;

import com.bancoDeDados2.prova.model.Curso;

public record ListarCursoDto(
        String codCurso,
        String nomeCurso,
        String coordenador
) {
    public ListarCursoDto(Curso curso) {
        this(
                curso.getCod(),
                curso.getNome(),
                curso.getCoordenador()
        );
    }
}
