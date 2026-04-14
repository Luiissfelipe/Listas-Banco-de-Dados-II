--
-- PostgreSQL database dump
--

\restrict Ves8TPMUbjNBZ96sBfTIsiSILBTfb4DuB9de7nDOcCJv96KQPYftOPj9hQI4yfr

-- Dumped from database version 17.9 (Debian 17.9-1.pgdg13+1)
-- Dumped by pg_dump version 17.9 (Debian 17.9-1.pgdg13+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE IF EXISTS ONLY public.matriculas DROP CONSTRAINT IF EXISTS fk_disciplina;
ALTER TABLE IF EXISTS ONLY public.matriculas DROP CONSTRAINT IF EXISTS fk_curso;
ALTER TABLE IF EXISTS ONLY public.matriculas DROP CONSTRAINT IF EXISTS fk_aluno;
DROP INDEX IF EXISTS public.flyway_schema_history_s_idx;
ALTER TABLE IF EXISTS ONLY public.matriculas DROP CONSTRAINT IF EXISTS matriculas_pkey;
ALTER TABLE IF EXISTS ONLY public.flyway_schema_history DROP CONSTRAINT IF EXISTS flyway_schema_history_pk;
ALTER TABLE IF EXISTS ONLY public.disciplinas DROP CONSTRAINT IF EXISTS disciplinas_pkey;
ALTER TABLE IF EXISTS ONLY public.cursos DROP CONSTRAINT IF EXISTS cursos_pkey;
ALTER TABLE IF EXISTS ONLY public.alunos DROP CONSTRAINT IF EXISTS alunos_pkey;
ALTER TABLE IF EXISTS ONLY public.alunos DROP CONSTRAINT IF EXISTS alunos_email_key;
ALTER TABLE IF EXISTS ONLY public.alunos DROP CONSTRAINT IF EXISTS alunos_cpf_key;
ALTER TABLE IF EXISTS public.matriculas ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.alunos_goiania ALTER COLUMN campus DROP DEFAULT;
ALTER TABLE IF EXISTS public.alunos_goiania ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.alunos_goianesia ALTER COLUMN campus DROP DEFAULT;
ALTER TABLE IF EXISTS public.alunos_goianesia ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.alunos_anapolis ALTER COLUMN campus DROP DEFAULT;
ALTER TABLE IF EXISTS public.alunos_anapolis ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.alunos ALTER COLUMN id DROP DEFAULT;
DROP VIEW IF EXISTS public.v_alunos_global;
DROP SEQUENCE IF EXISTS public.matriculas_id_seq;
DROP TABLE IF EXISTS public.matriculas;
DROP TABLE IF EXISTS public.flyway_schema_history;
DROP TABLE IF EXISTS public.disciplinas;
DROP TABLE IF EXISTS public.cursos;
DROP SEQUENCE IF EXISTS public.alunos_id_seq;
DROP TABLE IF EXISTS public.alunos_goiania;
DROP TABLE IF EXISTS public.alunos_goianesia;
DROP TABLE IF EXISTS public.alunos_anapolis;
DROP TABLE IF EXISTS public.alunos;
SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: alunos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alunos (
    id bigint NOT NULL,
    nome character varying(100) NOT NULL,
    cpf bigint NOT NULL,
    email character varying(50) NOT NULL,
    sexo character(1) NOT NULL,
    campus character varying(20) DEFAULT 'GOIANESIA'::character varying,
    CONSTRAINT alunos_sexo_check CHECK ((sexo = ANY (ARRAY['F'::bpchar, 'M'::bpchar])))
);


ALTER TABLE public.alunos OWNER TO postgres;

--
-- Name: alunos_anapolis; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alunos_anapolis (
    CONSTRAINT alunos_anapolis_campus_check CHECK (((campus)::text = 'ANAPOLIS'::text))
)
INHERITS (public.alunos);


ALTER TABLE public.alunos_anapolis OWNER TO postgres;

--
-- Name: alunos_goianesia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alunos_goianesia (
    CONSTRAINT alunos_goianesia_campus_check CHECK (((campus)::text = 'GOIANESIA'::text))
)
INHERITS (public.alunos);


ALTER TABLE public.alunos_goianesia OWNER TO postgres;

--
-- Name: alunos_goiania; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alunos_goiania (
    CONSTRAINT alunos_goiania_campus_check CHECK (((campus)::text = 'GOIANIA'::text))
)
INHERITS (public.alunos);


ALTER TABLE public.alunos_goiania OWNER TO postgres;

--
-- Name: alunos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.alunos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.alunos_id_seq OWNER TO postgres;

--
-- Name: alunos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.alunos_id_seq OWNED BY public.alunos.id;


--
-- Name: cursos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cursos (
    cod character varying(10) NOT NULL,
    nome character varying(100) NOT NULL,
    coordenador character varying(50) NOT NULL
);


ALTER TABLE public.cursos OWNER TO postgres;

--
-- Name: disciplinas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.disciplinas (
    cod character varying(10) NOT NULL,
    nome character varying(50) NOT NULL,
    vagas integer DEFAULT 0,
    CONSTRAINT check_vagas CHECK ((vagas >= 0))
);


ALTER TABLE public.disciplinas OWNER TO postgres;

--
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- Name: matriculas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.matriculas (
    id bigint NOT NULL,
    semestre bigint NOT NULL,
    id_aluno bigint NOT NULL,
    cod_curso character varying(10) NOT NULL,
    cod_disciplina character varying(10) NOT NULL
);


ALTER TABLE public.matriculas OWNER TO postgres;

--
-- Name: matriculas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.matriculas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.matriculas_id_seq OWNER TO postgres;

--
-- Name: matriculas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.matriculas_id_seq OWNED BY public.matriculas.id;


--
-- Name: v_alunos_global; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_alunos_global AS
 SELECT alunos_goianesia.id,
    alunos_goianesia.nome,
    alunos_goianesia.cpf,
    alunos_goianesia.email,
    alunos_goianesia.sexo,
    alunos_goianesia.campus
   FROM public.alunos_goianesia
UNION ALL
 SELECT alunos_anapolis.id,
    alunos_anapolis.nome,
    alunos_anapolis.cpf,
    alunos_anapolis.email,
    alunos_anapolis.sexo,
    alunos_anapolis.campus
   FROM public.alunos_anapolis
UNION ALL
 SELECT alunos_goiania.id,
    alunos_goiania.nome,
    alunos_goiania.cpf,
    alunos_goiania.email,
    alunos_goiania.sexo,
    alunos_goiania.campus
   FROM public.alunos_goiania;


ALTER VIEW public.v_alunos_global OWNER TO postgres;

--
-- Name: alunos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alunos ALTER COLUMN id SET DEFAULT nextval('public.alunos_id_seq'::regclass);


--
-- Name: alunos_anapolis id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alunos_anapolis ALTER COLUMN id SET DEFAULT nextval('public.alunos_id_seq'::regclass);


--
-- Name: alunos_anapolis campus; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alunos_anapolis ALTER COLUMN campus SET DEFAULT 'GOIANESIA'::character varying;


--
-- Name: alunos_goianesia id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alunos_goianesia ALTER COLUMN id SET DEFAULT nextval('public.alunos_id_seq'::regclass);


--
-- Name: alunos_goianesia campus; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alunos_goianesia ALTER COLUMN campus SET DEFAULT 'GOIANESIA'::character varying;


--
-- Name: alunos_goiania id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alunos_goiania ALTER COLUMN id SET DEFAULT nextval('public.alunos_id_seq'::regclass);


--
-- Name: alunos_goiania campus; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alunos_goiania ALTER COLUMN campus SET DEFAULT 'GOIANESIA'::character varying;


--
-- Name: matriculas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matriculas ALTER COLUMN id SET DEFAULT nextval('public.matriculas_id_seq'::regclass);


--
-- Data for Name: alunos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alunos (id, nome, cpf, email, sexo, campus) FROM stdin;
1	Felipe Andrade	11122233344	felipe@email.com	M	GOIANESIA
2	Ana Julia	22233344455	ana@email.com	F	GOIANESIA
3	Carlos Eduardo	33344455566	carlos@email.com	M	GOIANESIA
4	Beatriz Lopes	44455566677	beatriz@email.com	F	GOIANESIA
5	Diego Souza	55566677788	diego@email.com	M	GOIANESIA
6	Fernanda Lima	66677788899	fernanda@email.com	F	ANAPOLIS
7	Gabriel Mota	77788899900	gabriel@email.com	M	ANAPOLIS
8	Heloisa Paiva	88899900011	heloisa@email.com	F	ANAPOLIS
9	Igor Gomes	99900011122	igor@email.com	M	ANAPOLIS
10	Julia Costa	10101010101	julia@email.com	F	ANAPOLIS
11	Kevin Silva	12121212121	kevin@email.com	M	GOIANIA
12	Larissa Reis	13131313131	larissa@email.com	F	GOIANIA
13	Mateus Solto	14141414141	mateus@email.com	M	GOIANIA
14	Nicole Dias	15151515151	nicole@email.com	F	GOIANIA
15	Otavio Cruz	16161616161	otavio@email.com	M	GOIANIA
\.


--
-- Data for Name: alunos_anapolis; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alunos_anapolis (id, nome, cpf, email, sexo, campus) FROM stdin;
\.


--
-- Data for Name: alunos_goianesia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alunos_goianesia (id, nome, cpf, email, sexo, campus) FROM stdin;
\.


--
-- Data for Name: alunos_goiania; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alunos_goiania (id, nome, cpf, email, sexo, campus) FROM stdin;
\.


--
-- Data for Name: cursos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cursos (cod, nome, coordenador) FROM stdin;
CC	Ciência da Computação	Natan Rodrigues
SI	Sistemas para Internet	Lucas Oliveira
ECA	Engenharia de Controle e Automação	Frederico
\.


--
-- Data for Name: disciplinas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.disciplinas (cod, nome, vagas) FROM stdin;
BD2	Banco de Dados II	18
PROG1	Programação I	11
PROG2	Programação II	20
CALC1	Cálculo I	1
CALC2	Cálculo II	14
ES	Engenharia de Software	9
BD1	Banco de Dados I	6
\.


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	create initial tables	SQL	V1__create_initial_tables.sql	-651555005	postgres	2026-04-14 19:41:45.548048	17	t
2	2	Integridade e Seguranca	SQL	V2__Integridade_e_Seguranca.sql	1325735912	postgres	2026-04-14 19:41:45.59184	15	t
3	3	Distribuicao e Fragmentacao	SQL	V3__Distribuicao_e_Fragmentacao.sql	-429426142	postgres	2026-04-14 19:41:45.624391	10	t
\.


--
-- Data for Name: matriculas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.matriculas (id, semestre, id_aluno, cod_curso, cod_disciplina) FROM stdin;
1	20261	1	CC	BD1
2	20262	1	CC	PROG1
3	20261	2	CC	BD1
4	20262	2	CC	PROG2
5	20261	3	CC	BD1
6	20262	3	CC	CALC2
7	20261	4	CC	PROG1
8	20262	4	CC	ES
9	20261	5	CC	PROG1
10	20262	5	CC	ES
11	20261	6	SI	BD2
12	20262	6	SI	PROG2
13	20261	7	SI	BD2
14	20262	7	SI	ES
15	20261	8	SI	BD2
16	20262	8	SI	CALC2
17	20261	9	SI	PROG1
18	20262	9	SI	ES
19	20261	10	SI	PROG1
20	20262	10	SI	ES
21	20261	11	ECA	CALC2
22	20262	11	ECA	PROG1
23	20261	12	ECA	CALC2
24	20262	12	ECA	PROG2
25	20261	13	ECA	CALC2
26	20262	13	ECA	BD1
27	20261	14	ECA	ES
28	20262	14	ECA	PROG1
29	20261	15	ECA	ES
30	20262	15	ECA	PROG2
\.


--
-- Name: alunos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.alunos_id_seq', 15, true);


--
-- Name: matriculas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.matriculas_id_seq', 31, true);


--
-- Name: alunos alunos_cpf_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alunos
    ADD CONSTRAINT alunos_cpf_key UNIQUE (cpf);


--
-- Name: alunos alunos_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alunos
    ADD CONSTRAINT alunos_email_key UNIQUE (email);


--
-- Name: alunos alunos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alunos
    ADD CONSTRAINT alunos_pkey PRIMARY KEY (id);


--
-- Name: cursos cursos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cursos
    ADD CONSTRAINT cursos_pkey PRIMARY KEY (cod);


--
-- Name: disciplinas disciplinas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disciplinas
    ADD CONSTRAINT disciplinas_pkey PRIMARY KEY (cod);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: matriculas matriculas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matriculas
    ADD CONSTRAINT matriculas_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- Name: matriculas fk_aluno; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matriculas
    ADD CONSTRAINT fk_aluno FOREIGN KEY (id_aluno) REFERENCES public.alunos(id) ON DELETE CASCADE;


--
-- Name: matriculas fk_curso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matriculas
    ADD CONSTRAINT fk_curso FOREIGN KEY (cod_curso) REFERENCES public.cursos(cod) ON DELETE CASCADE;


--
-- Name: matriculas fk_disciplina; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matriculas
    ADD CONSTRAINT fk_disciplina FOREIGN KEY (cod_disciplina) REFERENCES public.disciplinas(cod) ON DELETE CASCADE;


--
-- Name: TABLE alunos; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT ON TABLE public.alunos TO aluno;
GRANT SELECT,UPDATE ON TABLE public.alunos TO professor;


--
-- Name: TABLE cursos; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT ON TABLE public.cursos TO aluno;
GRANT SELECT,UPDATE ON TABLE public.cursos TO professor;


--
-- Name: TABLE disciplinas; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT ON TABLE public.disciplinas TO aluno;
GRANT SELECT,UPDATE ON TABLE public.disciplinas TO professor;


--
-- Name: TABLE flyway_schema_history; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT ON TABLE public.flyway_schema_history TO aluno;
GRANT SELECT,UPDATE ON TABLE public.flyway_schema_history TO professor;


--
-- Name: TABLE matriculas; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT ON TABLE public.matriculas TO aluno;
GRANT SELECT,UPDATE ON TABLE public.matriculas TO professor;


--
-- PostgreSQL database dump complete
--

\unrestrict Ves8TPMUbjNBZ96sBfTIsiSILBTfb4DuB9de7nDOcCJv96KQPYftOPj9hQI4yfr

