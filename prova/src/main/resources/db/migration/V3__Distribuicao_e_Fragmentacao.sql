-- 1. Adiciona a coluna campus à tabela principal
ALTER TABLE alunos ADD COLUMN campus VARCHAR(20) DEFAULT 'GOIANESIA';

-- 2. Criando os fragmentos que simulam os nós distribuídos fisicamente
CREATE TABLE alunos_goianesia (
                                  CHECK (campus = 'GOIANESIA')
) INHERITS (alunos);

CREATE TABLE alunos_anapolis (
                                 CHECK (campus = 'ANAPOLIS')
) INHERITS (alunos);

CREATE TABLE alunos_goiania (
                                CHECK (campus = 'GOIANIA')
) INHERITS (alunos);

-- 3. Uma View que une os nós isolados para consultas transparentes
CREATE OR REPLACE VIEW v_alunos_global AS
SELECT * FROM alunos_goianesia
UNION ALL
SELECT * FROM alunos_anapolis
UNION ALL
SELECT * FROM alunos_goiania;