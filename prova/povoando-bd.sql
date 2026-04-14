-- 1. Inserindo Cursos
INSERT INTO cursos (cod, nome, coordenador) VALUES
                                                ('CC', 'Ciência da Computação', 'Natan Rodrigues'),
                                                ('SI', 'Sistemas para Internet', 'Lucas Oliveira'),
                                                ('ECA', 'Engenharia de Controle e Automação', 'Frederico');

-- 2. Inserindo Disciplinas (7 Disciplinas)
INSERT INTO disciplinas (cod, nome, vagas) VALUES
                                               ('BD1', 'Banco de Dados I', 7),
                                               ('BD2', 'Banco de Dados II', 18),
                                               ('PROG1', 'Programação I', 11),
                                               ('PROG2', 'Programação II', 20),
                                               ('CALC1', 'Cálculo I', 1),
                                               ('CALC2', 'Cálculo II', 14),
                                               ('ES', 'Engenharia de Software', 9);

-- 3. Inserindo Alunos
-- Nó 1: GOIANÉSIA
INSERT INTO alunos (nome, cpf, email, sexo, campus) VALUES
                                                        ('Felipe Andrade', 11122233344, 'felipe@email.com', 'M', 'GOIANESIA'),
                                                        ('Ana Julia', 22233344455, 'ana@email.com', 'F', 'GOIANESIA'),
                                                        ('Carlos Eduardo', 33344455566, 'carlos@email.com', 'M', 'GOIANESIA'),
                                                        ('Beatriz Lopes', 44455566677, 'beatriz@email.com', 'F', 'GOIANESIA'),
                                                        ('Diego Souza', 55566677788, 'diego@email.com', 'M', 'GOIANESIA');

-- Nó 2: ANÁPOLIS
INSERT INTO alunos (nome, cpf, email, sexo, campus) VALUES
                                                        ('Fernanda Lima', 66677788899, 'fernanda@email.com', 'F', 'ANAPOLIS'),
                                                        ('Gabriel Mota', 77788899900, 'gabriel@email.com', 'M', 'ANAPOLIS'),
                                                        ('Heloisa Paiva', 88899900011, 'heloisa@email.com', 'F', 'ANAPOLIS'),
                                                        ('Igor Gomes', 99900011122, 'igor@email.com', 'M', 'ANAPOLIS'),
                                                        ('Julia Costa', 10101010101, 'julia@email.com', 'F', 'ANAPOLIS');

-- Nó 3: GOIANIA
INSERT INTO alunos (nome, cpf, email, sexo, campus) VALUES
                                                        ('Kevin Silva', 12121212121, 'kevin@email.com', 'M', 'GOIANIA'),
                                                        ('Larissa Reis', 13131313131, 'larissa@email.com', 'F', 'GOIANIA'),
                                                        ('Mateus Solto', 14141414141, 'mateus@email.com', 'M', 'GOIANIA'),
                                                        ('Nicole Dias', 15151515151, 'nicole@email.com', 'F', 'GOIANIA'),
                                                        ('Otavio Cruz', 16161616161, 'otavio@email.com', 'M', 'GOIANIA');

-- 4. Inserindo Matrículas

-- Matrículas: Alunos de Goianésia (CC)
INSERT INTO matriculas (semestre, id_aluno, cod_curso, cod_disciplina) VALUES
                                                                           (20261, 1, 'CC', 'BD1'), (20262, 1, 'CC', 'PROG1'),
                                                                           (20261, 2, 'CC', 'BD1'), (20262, 2, 'CC', 'PROG2'),
                                                                           (20261, 3, 'CC', 'BD1'), (20262, 3, 'CC', 'CALC2'),
                                                                           (20261, 4, 'CC', 'PROG1'), (20262, 4, 'CC', 'ES'),
                                                                           (20261, 5, 'CC', 'PROG1'), (20262, 5, 'CC', 'ES');

-- Matrículas: Alunos de Anápolis (SI)
INSERT INTO matriculas (semestre, id_aluno, cod_curso, cod_disciplina) VALUES
                                                                           (20261, 6, 'SI', 'BD2'), (20262, 6, 'SI', 'PROG2'),
                                                                           (20261, 7, 'SI', 'BD2'), (20262, 7, 'SI', 'ES'),
                                                                           (20261, 8, 'SI', 'BD2'), (20262, 8, 'SI', 'CALC2'),
                                                                           (20261, 9, 'SI', 'PROG1'), (20262, 9, 'SI', 'ES'),
                                                                           (20261, 10, 'SI', 'PROG1'), (20262, 10, 'SI', 'ES');

-- Matrículas: Alunos de GOIANIA (ECA)
INSERT INTO matriculas (semestre, id_aluno, cod_curso, cod_disciplina) VALUES
                                                                           (20261, 11, 'ECA', 'CALC2'), (20262, 11, 'ECA', 'PROG1'),
                                                                           (20261, 12, 'ECA', 'CALC2'), (20262, 12, 'ECA', 'PROG2'),
                                                                           (20261, 13, 'ECA', 'CALC2'), (20262, 13, 'ECA', 'BD1'),
                                                                           (20261, 14, 'ECA', 'ES'), (20262, 14, 'ECA', 'PROG1'),
                                                                           (20261, 15, 'ECA', 'ES'), (20262, 15, 'ECA', 'PROG2');