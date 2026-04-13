package com.lista.aula3.repository;

import com.lista.aula3.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findBySemestre(Long semestre);

    Optional<Matricula> findByAlunoIdAndCursoCodAndDisciplinaCodAndSemestre(
            Long alunoId, String cursoCod, String disciplinaCod, Long semestre
    );
}
