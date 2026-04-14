package com.bancoDeDados2.prova.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class BackupService {

    public void povoarBancoDeDados() {
        String nomeArquivo = "povoando-bd.sql";
        File arquivoLocal = new File(nomeArquivo);

        if (!arquivoLocal.exists()) {
            System.err.println("ERRO: O arquivo '" + nomeArquivo + "' não foi encontrado na raiz do projeto.");
            return;
        }

        try {
            // Passo 1: Copia o arquivo para dentro do container
            ProcessBuilder cp = new ProcessBuilder(
                    "docker", "cp", nomeArquivo, "faculdade-db:/tmp/" + nomeArquivo
            );
            cp.inheritIO().start().waitFor();

            // Passo 2: Executa o psql apontando para o arquivo interno
            ProcessBuilder exec = new ProcessBuilder(
                    "docker", "exec", "faculdade-db",
                    "psql", "-U", "postgres", "-d", "faculdade", "-f", "/tmp/" + nomeArquivo
            );

            exec.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process process = exec.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("SUCESSO: Banco de dados povoado com os dados iniciais!");
            } else {
                System.err.println("Erro ao povoar banco. Código: " + exitCode);
            }
        } catch (Exception e) {
            System.err.println("Falha técnica ao povoar: " + e.getMessage());
        }
    }

    public void gerarBackup() {
        try {
            String nomeArquivo = "backup_faculdade.sql";
            String nomeContainer = "faculdade-db";

            // Adicionamos --clean (para dropar) e --if-exists (para não dar erro se não houver a tabela)
            ProcessBuilder pb = new ProcessBuilder(
                    "docker", "exec", nomeContainer,
                    "pg_dump", "-U", "postgres", "--clean", "--if-exists", "faculdade"
            );

            File arquivoBackup = new File(nomeArquivo);
            pb.redirectOutput(arquivoBackup);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);

            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Backup gerado com sucesso: " + nomeArquivo);
            } else {
                System.err.println("Erro ao gerar backup. Código: " + exitCode);
                if(arquivoBackup.exists()) arquivoBackup.delete();
            }
        } catch (Exception e) {
            System.err.println("Falha ao executar o comando do Docker: " + e.getMessage());
        }
    }

    @Transactional
    public void removerDados() {
        try {
            // TRUNCATE: Apaga os dados mas mantém a estrutura
            // RESTART IDENTITY: Reseta os IDs automáticos para 1 novamente
            // CASCADE: Garante que as relações de chave estrangeira sejam respeitadas
            String sql = "TRUNCATE TABLE matriculas, alunos, cursos, disciplinas RESTART IDENTITY CASCADE;";

            ProcessBuilder pb = new ProcessBuilder(
                    "docker", "exec", "faculdade-db",
                    "psql", "-U", "postgres", "-d", "faculdade", "-c", sql
            );

            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("DADOS REMOVIDOS");
            } else {
                System.err.println("Erro ao limpar dados. Código: " + exitCode);
            }
        } catch (Exception e) {
            System.err.println("Falha ao executar limpeza: " + e.getMessage());
        }
    }

    public void restaurarBackup() {
        File arquivo = new File("backup_faculdade.sql");

        // Gerar exception caso o arquivo não exista
        if (!arquivo.exists()) {
            throw new RuntimeException("ERRO CRÍTICO: Arquivo 'backup_faculdade.sql' não encontrado! ");
        }

        try {
            // O parâmetro "-i" (interativo) é necessário para o Docker aceitar o redirecionamento de entrada
            ProcessBuilder pb = new ProcessBuilder(
                    "docker", "exec", "-i", "faculdade-db",
                    "psql", "-U", "postgres", "-d", "faculdade"
            );

            // Redireciona o conteúdo do arquivo .sql para a entrada do psql
            pb.redirectInput(arquivo);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);

            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("RESTAURAÇÃO CONCLUÍDA: O banco de dados retornou ao estado anterior.");
            } else {
                System.err.println("Erro na restauração. Código: " + exitCode);
            }
        } catch (Exception e) {
            System.err.println("Falha técnica na restauração: " + e.getMessage());
        }
    }
}
