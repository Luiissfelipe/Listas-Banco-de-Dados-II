package com.bancoDeDados2.prova.service;

import com.bancoDeDados2.prova.dto.ListarMatriculaDto;
import com.bancoDeDados2.prova.dto.MatriculaDto;
import com.bancoDeDados2.prova.model.Aluno;
import com.bancoDeDados2.prova.model.Curso;
import com.bancoDeDados2.prova.model.Disciplina;
import com.bancoDeDados2.prova.model.Matricula;
import com.bancoDeDados2.prova.repository.AlunoRepository;
import com.bancoDeDados2.prova.repository.CursosRepository;
import com.bancoDeDados2.prova.repository.DisciplinaRepository;
import com.bancoDeDados2.prova.repository.MatriculaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
public class MatriculaService {

    private final AlunoRepository alunoRepo;

    private final CursosRepository cursoRepo;

    private final DisciplinaRepository discRepo;

    private final MatriculaRepository matriculaRepo;

    public MatriculaService(AlunoRepository alunoRepo,
                            CursosRepository cursoRepo,
                            DisciplinaRepository discRepo,
                            MatriculaRepository matriculaRepo) {
        this.alunoRepo = alunoRepo;
        this.cursoRepo = cursoRepo;
        this.discRepo = discRepo;
        this.matriculaRepo = matriculaRepo;
    }

    public List<ListarMatriculaDto> listarMatriculasPorSemestre(Long semestre) {
        return matriculaRepo.findBySemestre(semestre).stream()
                .map(ListarMatriculaDto::new)
                .toList();
    }

    public List<ListarMatriculaDto> listarMatriculasPorAluno(Long idAluno) {
        return matriculaRepo.findByAluno_Id(idAluno).stream()
                .map(ListarMatriculaDto::new)
                .toList();
    }

    @Transactional
    public void realizarMatricula(MatriculaDto dto) {
        // Verifica se o aluno existe
        Aluno aluno = alunoRepo.findById(dto.idAluno())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado. Operação abortada."));

        // Verifica se o curso existe
        Curso curso = cursoRepo.findById(dto.codCurso())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado. Operação abortada."));

        // Verifica se a disciplina existe
        Disciplina disc = discRepo.findById(dto.codDisciplina())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada. Operação abortada."));

        // Verifica integridade de domínio (Vagas >= 0)
        if (disc.getVagas() <= 0) {
            throw new RuntimeException("Não há vagas disponíveis para esta disciplina!");
        }

        // Decrementa vaga e salva
        disc.setVagas(disc.getVagas() - 1);

        // Insere a matrícula
        Matricula matricula = new Matricula(dto.semestre(), aluno, curso, disc);
        matriculaRepo.save(matricula);
        System.out.println("Matrícula realizada com sucesso! Vagas restantes: " + disc.getVagas());
    }

    @Transactional
    public void cancelarMatricula(Long id) {
        Matricula matricula = matriculaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada. Operação abortada."));

        matriculaRepo.delete(matricula);
        System.out.println("Matricula cancelada com sucesso!");
    }
}