package com.bancoDeDados2.prova.repository;

import com.bancoDeDados2.prova.model.Disciplina;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Disciplina> findByCod(String cod);
}
