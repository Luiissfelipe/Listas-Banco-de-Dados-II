package com.bancoDeDados2.prova.service;

import com.bancoDeDados2.prova.dto.ListarCursoDto;
import com.bancoDeDados2.prova.repository.CursosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursosRepository cursoRepo;

    public CursoService(CursosRepository cursoRepo) {
        this.cursoRepo = cursoRepo;
    }

    public List<ListarCursoDto> listarTodosCursos() {
        return cursoRepo.findAll().stream()
                .map(ListarCursoDto::new)
                .toList();
    }
}
