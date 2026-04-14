package com.bancoDeDados2.prova.repository;

import com.bancoDeDados2.prova.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosRepository extends JpaRepository<Curso, String> {
}
