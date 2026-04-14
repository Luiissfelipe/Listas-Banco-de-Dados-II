-- Integridade: Adiciona a coluna de vagas e a restrição CHECK
ALTER TABLE disciplinas ADD COLUMN vagas INT DEFAULT 0;
ALTER TABLE disciplinas ADD CONSTRAINT check_vagas CHECK (vagas >= 0);

-- Segurança: Criação de utilizadores e permissões
-- Utilizador Aluno (Apenas consulta)
CREATE USER aluno WITH PASSWORD 'aluno123';
GRANT SELECT ON ALL TABLES IN SCHEMA public TO aluno;

-- Utilizador Professor (Consulta e Atualização)
CREATE USER professor WITH PASSWORD 'prof123';
GRANT SELECT, UPDATE ON ALL TABLES IN SCHEMA public TO professor;