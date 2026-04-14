package com.bancoDeDados2.prova.controller;

import com.bancoDeDados2.prova.dto.ListarCursoDto;
import com.bancoDeDados2.prova.service.CursoService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    public List<ListarCursoDto> listarTodosCursos() {
        return service.listarTodosCursos();
    }
}
