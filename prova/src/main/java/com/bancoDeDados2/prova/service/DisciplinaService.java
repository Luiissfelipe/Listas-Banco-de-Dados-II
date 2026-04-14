package com.bancoDeDados2.prova.service;

import com.bancoDeDados2.prova.dto.ListarDisciplinaDto;
import com.bancoDeDados2.prova.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository discRepo;

    public DisciplinaService(DisciplinaRepository discRepo) {
        this.discRepo = discRepo;
    }

    public List<ListarDisciplinaDto> listarTodasDisciplinas() {
        return discRepo.findAll().stream()
                .map(ListarDisciplinaDto::new)
                .toList();
    }
}
