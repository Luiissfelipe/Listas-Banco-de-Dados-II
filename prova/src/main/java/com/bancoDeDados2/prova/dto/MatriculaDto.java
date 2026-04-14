package com.bancoDeDados2.prova.dto;

import jakarta.validation.constraints.NotNull;

public record MatriculaDto(
        @NotNull
        Long semestre,
        @NotNull
        Long idAluno,
        @NotNull
        String codCurso,
        @NotNull
        String codDisciplina
) {
}
