CREATE TABLE cursos (
                        cod VARCHAR(10) PRIMARY KEY,
                        nome VARCHAR(100) NOT NULL,
                        coordenador VARCHAR(50) NOT NULL
);

CREATE TABLE disciplinas (
                             cod VARCHAR(10) PRIMARY KEY,
                             nome VARCHAR(50) NOT NULL
);

CREATE TABLE alunos (
                        id BIGSERIAL PRIMARY KEY,
                        nome VARCHAR(100) NOT NULL,
                        cpf BIGINT NOT NULL UNIQUE,
                        email VARCHAR(50) NOT NULL UNIQUE,
                        sexo CHAR(1) NOT NULL CHECK (sexo IN ('F', 'M'))
);

CREATE TABLE matriculas (
                            id BIGSERIAL PRIMARY KEY,
                            semestre BIGINT NOT NULL,
                            id_aluno BIGINT NOT NULL,
                            cod_curso VARCHAR(10) NOT NULL,
                            cod_disciplina VARCHAR(10) NOT NULL,
                            CONSTRAINT fk_aluno FOREIGN KEY (id_aluno)
                                REFERENCES alunos(id) ON DELETE CASCADE,

                            CONSTRAINT fk_curso FOREIGN KEY (cod_curso)
                                REFERENCES cursos(cod) ON DELETE CASCADE,

                            CONSTRAINT fk_disciplina FOREIGN KEY (cod_disciplina)
                                REFERENCES disciplinas(cod) ON DELETE CASCADE
);