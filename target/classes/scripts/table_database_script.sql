CREATE TABLE usuario
(
    id integer NOT NULL DEFAULT nextval('usuario_id_seq'::regclass),
    cpf character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    nome character varying(255) COLLATE pg_catalog."default",
    senha character varying(255) COLLATE pg_catalog."default",
    telefone character varying(255) COLLATE pg_catalog."default",
    endereco character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

CREATE TABLE emprestimo
(
    id integer NOT NULL DEFAULT nextval('emprestimo_id_seq'::regclass),
    data_solicitacao character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    valor numeric(19,2),
    usuario_id integer,
    CONSTRAINT emprestimo_pkey PRIMARY KEY (id),
    CONSTRAINT usuarioemprestimo_fkey FOREIGN KEY (usuario_id)
        REFERENCES usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE token
(
    id integer NOT NULL DEFAULT nextval('token_id_seq'::regclass),
    data_expiracao timestamp without time zone,
    valor character varying(255) COLLATE pg_catalog."default",
    usuario_id integer,
    CONSTRAINT token_pkey PRIMARY KEY (id),
    CONSTRAINT usuariotoken_fkey FOREIGN KEY (usuario_id)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

