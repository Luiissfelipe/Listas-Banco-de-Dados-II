package com.bancoDeDados2.prova.repository;

import com.bancoDeDados2.prova.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findBySemestre(Long semestre);

    List<Matricula> findByAluno_Id(Long alunoId);

    Optional<Matricula> findById(Long id);
}
