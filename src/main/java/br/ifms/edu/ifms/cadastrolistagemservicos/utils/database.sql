CREATE TABLE public.servicos
(
    "ID" integer NOT NULL,
    "NOME" character varying(100) NOT NULL,
    "DESCRICAO" character varying(50) NOT NULL,
    "TEMPO_ESTIMADO" character varying(50) NOT NULL,
    "VALOR" character varying(10) NOT NULL,
    "EMPRESA" character varying(30) NOT NULL,
    PRIMARY KEY ("ID")
);

ALTER TABLE IF EXISTS public.servicos
    OWNER to postgres;