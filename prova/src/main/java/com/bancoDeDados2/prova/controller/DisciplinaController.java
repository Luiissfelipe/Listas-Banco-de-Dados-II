package com.bancoDeDados2.prova.controller;

import com.bancoDeDados2.prova.dto.ListarDisciplinaDto;
import com.bancoDeDados2.prova.service.DisciplinaService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DisciplinaController {

    private final DisciplinaService service;

    public DisciplinaController(DisciplinaService service) {
        this.service = service;
    }

    public List<ListarDisciplinaDto> listarTodasDisciplinas() {
        return service.listarTodasDisciplinas();
    }
}
