package com.bancoDeDados2.prova.repository;

import com.bancoDeDados2.prova.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
