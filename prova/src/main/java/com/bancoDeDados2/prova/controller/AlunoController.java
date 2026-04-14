package com.bancoDeDados2.prova.controller;

import com.bancoDeDados2.prova.dto.ListarAlunoDto;
import com.bancoDeDados2.prova.service.AlunoService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    public List<ListarAlunoDto> listarTodosAlunos() {
        return service.listarTodosAlunos();
    }
}
