package com.bancoDeDados2.prova.service;

import com.bancoDeDados2.prova.dto.ListarAlunoDto;
import com.bancoDeDados2.prova.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepo;

    public AlunoService(AlunoRepository alunoRepo) {
        this.alunoRepo = alunoRepo;
    }

    public List<ListarAlunoDto> listarTodosAlunos() {
        return alunoRepo.findAll().stream()
                .map(ListarAlunoDto::new)
                .toList();
    }
}
