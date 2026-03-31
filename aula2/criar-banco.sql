-- Criando as tabelas
CREATE TABLE CURSOS (
                        Cod VARCHAR(10) PRIMARY KEY,
                        Nome VARCHAR(100),
                        Depto VARCHAR(50),
                        Coord VARCHAR(50)
);

CREATE TABLE ALUNOS (
                        Matr INT PRIMARY KEY,
                        Nome VARCHAR(100),
                        Sexo CHAR(1),
                        Cr VARCHAR(10),
                        FOREIGN KEY (Cr) REFERENCES CURSOS(Cod)
);

CREATE TABLE MATRICULAS (
                            Matr INT,
                            Disc VARCHAR(50),
                            T CHAR(1),
                            Sem VARCHAR(10),
                            PRIMARY KEY (Matr, Disc, Sem),
                            FOREIGN KEY (Matr) REFERENCES ALUNOS(Matr)
);

-- Inserindo dados
INSERT INTO CURSOS (Cod, Nome, Depto, Coord) VALUES
                                                 ('CC', 'Ciência da Computação', 'DCC', 'Prof. Natan'),
                                                 ('SI', 'Sistemas para Internet', 'DCC', 'Prof. Rodrigues'),
                                                 ('ECA', 'Engenharia de Controle e Automação', 'ENG', 'Prof. Silva');

INSERT INTO ALUNOS (Matr, Nome, Sexo, Cr) VALUES
                                              (1, 'Luís Silva', 'M', 'SI'),
                                              (2, 'Ana Oliveira', 'F', 'CC'),
                                              (3, 'Bruno Costa', 'M', 'CC'),
                                              (4, 'Carla Souza', 'F', 'SI'),
                                              (5, 'Diego Santos', 'M', 'ECA'),
                                              (6, 'Elena Rocha', 'F', 'ECA'),
                                              (7, 'Fabio Lima', 'M', 'SI'),
                                              (8, 'Gisele Neves', 'F', 'CC'),
                                              (9, 'Heloisa Meireles', 'F', 'SI'),
                                              (10, 'Igor Ferreira', 'M', 'ECA');

INSERT INTO MATRICULAS (Matr, Disc, T, Sem) VALUES
                                                (1, 'Banco de Dados II', 'A', '2026.1'),
                                                (1, 'Programação Web', 'B', '2026.1'),
                                                (2, 'Algoritmos', 'A', '2026.1'),
                                                (2, 'Cálculo I', 'C', '2026.1'),
                                                (3, 'Banco de Dados II', 'A', '2026.1'),
                                                (3, 'Sistemas Operacionais', 'B', '2026.1'),
                                                (4, 'Banco de Dados II', 'A', '2026.1'),
                                                (4, 'Engenharia de Software', 'A', '2026.1'),
                                                (5, 'Circuitos Lógicos', 'B', '2026.1'),
                                                (5, 'Física II', 'A', '2026.1'),
                                                (6, 'Cálculo II', 'C', '2026.1'),
                                                (6, 'Banco de Dados II', 'A', '2026.1'),
                                                (7, 'Programação Web', 'B', '2026.1'),
                                                (7, 'Redes de Computadores', 'A', '2026.1'),
                                                (8, 'Inteligência Artificial', 'B', '2026.1'),
                                                (8, 'Banco de Dados II', 'A', '2026.1'),
                                                (9, 'Interface Humano-Computador', 'A', '2026.1'),
                                                (9, 'Segurança da Informação', 'B', '2026.1'),
                                                (10, 'Controle Digital', 'A', '2026.1'),
                                                (10, 'Robótica', 'C', '2026.1'),
                                                (1, 'Estrutura de Dados', 'B', '2026.1');