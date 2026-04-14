-- 1. Inserindo Cursos
INSERT INTO cursos (cod, nome, coordenador) VALUES
                                                ('CC', 'Ciência da Computação', 'Natan Rodrigues'),
                                                ('SI', 'Sistemas para Internet', 'Daniel Silva'),
                                                ('ECA', 'Engenharia de Controle e Automação', 'Marcos Oliveira'),
                                                ('ADS', 'Análise e Desenvolvimento de Sistemas', 'Lucas Ferreira');

-- 2. Inserindo Disciplinas
INSERT INTO disciplinas (cod, nome, vagas) VALUES
                                        ('BD1', 'Banco de Dados I', 7),
                                        ('BD2', 'Banco de Dados II', 18),
                                        ('PROG1', 'Programação I', 11),
                                        ('PROG2', 'Programação II', 20),
                                        ('CALC1', 'Cálculo I', 5),
                                        ('CALC2', 'Cálculo II', 14),
                                        ('ES', 'Engenharia de Software', 9);

-- 3. Inserindo Alunos
INSERT INTO alunos (nome, cpf, email, sexo) VALUES
                                                ('Felipe Andrade', 11122233344, 'felipe@email.com', 'M'),
                                                ('Ana Julia', 22233344455, 'ana@email.com', 'F'),
                                                ('Carlos Eduardo', 33344455566, 'carlos@email.com', 'M'),
                                                ('Beatriz Lopes', 44455566677, 'beatriz@email.com', 'F'),
                                                ('Diego Souza', 55566677788, 'diego@email.com', 'M'),
                                                ('Fernanda Lima', 66677788899, 'fernanda@email.com', 'F'),
                                                ('Gabriel Mota', 77788899900, 'gabriel@email.com', 'M'),
                                                ('Heloisa Paiva', 88899900011, 'heloisa@email.com', 'F'),
                                                ('Igor Gomes', 99900011122, 'igor@email.com', 'M'),
                                                ('Julia Costa', 10101010101, 'julia@email.com', 'F'),
                                                ('Kevin Silva', 12121212121, 'kevin@email.com', 'M'),
                                                ('Larissa Reis', 13131313131, 'larissa@email.com', 'F'),
                                                ('Mateus Solto', 14141414141, 'mateus@email.com', 'M'),
                                                ('Nicole Dias', 15151515151, 'nicole@email.com', 'F'),
                                                ('Otavio Cruz', 16161616161, 'otavio@email.com', 'M'),
                                                ('Patricia Mel', 17171717171, 'patricia@email.com', 'F'),
                                                ('Quirino Jose', 18181818181, 'quirino@email.com', 'M'),
                                                ('Rafaela Luz', 19191919191, 'rafaela@email.com', 'F'),
                                                ('Samuel Viana', 20202020202, 'samuel@email.com', 'M'),
                                                ('Tatiana Paz', 21212121212, 'tatiana@email.com', 'F');

-- 4. Inserindo Matrículas

-- Alunos de SI
INSERT INTO matriculas (semestre, id_aluno, cod_curso, cod_disciplina) VALUES
                                                                           (20261, 1, 'SI', 'BD2'), (20262, 1, 'SI', 'PROG1'), (20261, 1, 'SI', 'ES');

-- Alunos de CC
INSERT INTO matriculas (semestre, id_aluno, cod_curso, cod_disciplina) VALUES
                                                                           (20261, 2, 'CC', 'BD1'), (20262, 2, 'CC', 'CALC1'), (20261, 2, 'CC', 'PROG1');

-- Alunos de ECA
INSERT INTO matriculas (semestre, id_aluno, cod_curso, cod_disciplina) VALUES
                                                                           (20261, 5, 'ECA', 'CALC1'), (20262, 5, 'ECA', 'CALC2'), (20262, 5, 'ECA', 'PROG1');

-- Alunos de ADS
INSERT INTO matriculas (semestre, id_aluno, cod_curso, cod_disciplina) VALUES
                                                                           (20261, 11, 'ADS', 'ES'), (20262, 11, 'ADS', 'PROG1'), (20261, 11, 'ADS', 'BD1');

