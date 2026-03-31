-- 1. Nomes dos alunos do curso CC:
SELECT Nome FROM ALUNOS WHERE Cr='CC';

-- 2. Nomes das alunas do curso CC:
SELECT Nome FROM ALUNOS WHERE Sexo='F' AND Cr='CC';

-- 3. Nome do aluno e Nome do curso:
SELECT A.Nome, C.Nome FROM ALUNOS A JOIN CURSOS C ON A.Cr = C.Cod;

-- 4. Alunos matriculados no semestre 2026.1:
SELECT DISTINCT A.Nome FROM ALUNOS A JOIN MATRICULAS M ON A.Matr = M.Matr WHERE M.Sem='2026.1';

-- 5. Nome do aluno e Disciplina das matrículas em 2026.1:
SELECT A.Nome, M.Disc FROM ALUNOS A JOIN MATRICULAS M ON A.Matr = M.Matr WHERE M.Sem='2026.1';