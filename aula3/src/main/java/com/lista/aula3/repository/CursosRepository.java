package com.lista.aula3.repository;

import com.lista.aula3.model.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosRepository extends JpaRepository<Cursos, String> {
}
