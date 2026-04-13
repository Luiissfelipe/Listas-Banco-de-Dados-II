package com.lista.aula3.service;

import com.lista.aula3.model.*;
import com.lista.aula3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private AlunoRepository alunoRepo;
    @Autowired
    private CursosRepository cursoRepo;
    @Autowired
    private DisciplinaRepository discRepo;
    @Autowired
    private MatriculaRepository matriculaRepo;

    @Transactional
    public void realizarMatricula(Long alunoId, String cursoCod, String discCod, Long semestre) {
        // Verifica se o aluno existe
        Aluno aluno = alunoRepo.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));

        // Verifica se o curso existe
        Cursos curso = cursoRepo.findById(cursoCod)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado."));

        // Verifica se a disciplina existe
        Disciplina disc = discRepo.findById(discCod)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada."));

        // Insere a matrícula
        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setCurso(curso);
        matricula.setDisciplina(disc);
        matricula.setSemestre(semestre);

        matriculaRepo.save(matricula);
    }

    @Transactional
    public void cancelarMatricula(Long alunoId, String cursoCod, String discCod, Long semestre) {
        Matricula m = matriculaRepo.findByAlunoIdAndCursoCodAndDisciplinaCodAndSemestre(
                        alunoId, cursoCod, discCod, semestre)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada. Operação abortada."));

        matriculaRepo.delete(m);
    }

    // Listagens para o painel
    public List<Aluno> listarTodosAlunos() { return alunoRepo.findAll(); }
    public List<Disciplina> listarTodasDisciplinas() { return discRepo.findAll(); }
    public List<Matricula> listarMatriculasPorSemestre(Long semestre) {
        return matriculaRepo.findBySemestre(semestre);
    }
}