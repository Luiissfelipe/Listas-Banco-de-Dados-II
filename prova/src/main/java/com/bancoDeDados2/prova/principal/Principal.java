package com.bancoDeDados2.prova.principal;

import com.bancoDeDados2.prova.controller.*;
import com.bancoDeDados2.prova.dto.MatriculaDto;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    private final MatriculaController matriculaController;
    private final AlunoController alunoController;
    private final CursoController cursoController;
    private final DisciplinaController disciplinaController;
    private final BackupController backupController;

    Scanner input = new Scanner(System.in);

    public Principal(MatriculaController matriculaController,
                            AlunoController alunoController,
                            CursoController cursoController,
                            DisciplinaController disciplinaController,
                            BackupController backupController) {
        this.matriculaController = matriculaController;
        this.alunoController = alunoController;
        this.cursoController = cursoController;
        this.disciplinaController = disciplinaController;
        this.backupController = backupController;
    }

    public void run() {
        System.out.println("""
                
                ==============================================================
                                  GERENCIADOR DE MATRICULAS
                ==============================================================
                """);
        int opcao;
        do {
            System.out.println("""
					Digite uma opção:
					
					1 - Listar todos alunos
					2 - Listar todos cursos
					3 - Listar todas disciplinas
					4 - Listar matriculas por semestre
					5 - Listar matriculas por aluno
					6 - Realizar matricula
					7 - Cancelar matricula
					8 - Povoar Banco de Dados (Script SQL)
					9 - Gerar Backup (SQL)
					10 - Simular Falha (Remover tudo)
					11 - Restaurar do Backup
					
					""");

            opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    listarTodosAlunos();
                    break;
                case 2:
                    listarTodosCursos();
                    break;
                case 3:
                    listarTodasDisciplinas();
                    break;
                case 4:
                    listarMatriculaPorSemestre();
                    break;
                case 5:
                    listarMatriculaPorAluno();
                    break;
                case 6:
                    realizarMatricula();
                    break;
                case 7:
                    cancelarMatricula();
                    break;
                case 8:
                    backupController.povoarBancoDeDados();
                    break;
                case 9:
                    backupController.gerarBackup();
                    break;
                case 10:
                    backupController.removerDados();
                    break;
                case 11:
                    try {
                        backupController.restaurarBackup();
                    } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Encerrando!");
                    break;
                default:
                    System.out.println("Digite um valor válido!");
            }


        } while (opcao != 0);
    }

    private void listarTodosAlunos() {
        System.out.println("Alunos cadastrados:");
        alunoController.listarTodosAlunos()
                .forEach(a -> System.out.printf("[%d] %s - Sexo: %s\n",
                        a.idAluno(), a.nomeAluno(), a.sexo()));
        System.out.println();
    }

    private void listarTodosCursos() {
        System.out.println("Cursos cadastrados:");
        cursoController.listarTodosCursos()
                .forEach(c -> System.out.printf("[%s] %s - Coordenador: %s\n",
                        c.codCurso(), c.nomeCurso(), c.coordenador()));
        System.out.println();
    }

    private void listarTodasDisciplinas() {
        System.out.println("Disciplinas cadastradas:");
        disciplinaController.listarTodasDisciplinas()
                .forEach(d -> System.out.printf("[%s] %s - %d vagas\n",
                        d.codDisciplina(), d.nomeDisciplina(), d.vagas()));
        System.out.println();
    }

    private void listarMatriculaPorSemestre() {
        System.out.println("Digite o semestre: (Ex: 20261)");
        Long semestre = input.nextLong();
        matriculaController.listarMatriculasPorSemestre(semestre)
                .forEach(m -> System.out.printf("[%d] Aluno: %s - Curso: %s - Disciplina: %s\n",
                        m.idMatricula(), m.nomeAluno(), m.nomeCurso(), m.nomeDisciplina()));
    }

    private void listarMatriculaPorAluno() {
        System.out.println("Digite o ID do aluno: ");
        Long id = input.nextLong();
        System.out.println("Matriculas: ");
        matriculaController.listarMatriculasPorAluno(id)
                .forEach(m -> System.out.printf(
                        "[%d] Aluno: %s - Semestre: %d - Curso: %s - Disciplina: %s\n",
                        m.idMatricula(), m.nomeAluno(), m.semestre(), m.nomeCurso(), m.nomeDisciplina()));
    }

    private void realizarMatricula() {
        System.out.println("Digite o ID do aluno:");
        Long idAluno = input.nextLong();
        System.out.println("Digite o Cod do curso:");
        String codCurso = input.next();
        System.out.println("Digite o Cod da disciplina:");
        String codDisc = input.next();
        System.out.println("Digite o semestre: (Ex: 20261)");
        Long semestreMatricula = input.nextLong();
        matriculaController.realizarMatricula(new MatriculaDto(semestreMatricula, idAluno, codCurso, codDisc));
    }

    private void cancelarMatricula() {
        listarMatriculaPorAluno();
        System.out.println("Digite o ID da matricula:");
        Long idMatricula = input.nextLong();
        matriculaController.cancelarMatricula(idMatricula);
    }
}
