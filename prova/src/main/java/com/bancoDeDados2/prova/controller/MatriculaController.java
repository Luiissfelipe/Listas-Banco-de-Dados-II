package com.bancoDeDados2.prova.controller;

import com.bancoDeDados2.prova.dto.ListarMatriculaDto;
import com.bancoDeDados2.prova.dto.MatriculaDto;
import com.bancoDeDados2.prova.service.MatriculaService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MatriculaController {
    private final MatriculaService service;

    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    public List<ListarMatriculaDto> listarMatriculasPorSemestre(Long semestre) {
        return service.listarMatriculasPorSemestre(semestre);
    }

    public List<ListarMatriculaDto> listarMatriculasPorAluno(Long idAluno) {
        return service.listarMatriculasPorAluno(idAluno);
    }

    public void realizarMatricula(MatriculaDto dto) {
        service.realizarMatricula(dto);
    }

    public void cancelarMatricula(Long id) {
        service.cancelarMatricula(id);
    }
}
