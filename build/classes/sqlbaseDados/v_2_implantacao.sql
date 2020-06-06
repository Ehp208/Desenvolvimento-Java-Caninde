INSERT INTO public.pais (pai_id, pai_nome, pai_sigla, versionnum) VALUES (1, 'BRASIL', 'BR11', 1);
COMMIT;
ALTER SEQUENCE public.pais_seq RESTART WITH 2;

INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (1, 'Acre', 'AC', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (2, 'Alagoas', 'AL', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (3, 'Amapá', 'AP', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (4, 'Amazonas', 'AM', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (5, 'Bahia', 'BA', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (6, 'Ceará', 'CE', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (7, 'Distrito Federal', 'DF', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (8, 'Espírito Santo', 'ES', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (9, 'Goiás', 'GO', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (10, 'Maranhão', 'MA', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (11, 'Mato Grosso', 'MT', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (12, 'Mato Grosso do Sul', 'MS', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (13, 'Minas Gerais', 'MG', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (14, 'Pará', 'PA', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (15, 'Paraíba', 'PB', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (16, 'Paraná', 'PR', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (17, 'Pernanbuco', 'PE', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (18, 'Piauí', 'PI', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (19, 'Rio de Janeiro', 'RJ', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (20, 'Rio Grande do Norte', 'RN', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (21, 'Rio Grande do Sul', 'RS', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (22, 'Rondônia', 'RO', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (23, 'Roraima', 'RR', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (24, 'Santa Catarina', 'SC', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (25, 'São Paulo', 'SP', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (26, 'Sergipe', 'SE', 1, 1);
INSERT INTO public.estado (est_id, est_nome, est_uf, registroPais, versionnum) VALUES (27, 'Tocantins', 'TO', 1, 1);
COMMIT;
ALTER SEQUENCE public.estado_seq RESTART WITH 29;

INSERT INTO public.cidade(cid_id, cid_nome, versionnum) VALUES (1, 'Cidade Padrão', 1);
COMMIT;
ALTER SEQUENCE public.cidade_seq RESTART WITH 2;

INSERT INTO public.logradouro(log_id, log_nome, versionnum) VALUES (1, 'Logradouro Padrão', 1);
COMMIT;
ALTER SEQUENCE public.logradouro_seq RESTART WITH 2;

INSERT INTO public.bairro(bai_id, bai_nome, versionnum) VALUES (1, 'Bairro Padrão', 1);
COMMIT;
ALTER SEQUENCE public.bairro_seq RESTART WITH 2;

INSERT INTO public.cep(cep_id, cep_codigo, cep_unico, versionnum, registroLogradouro, registroBairro, registroEstado, registroCidade) VALUES (1, '00001-000', 'true', 1, 1, 1, 13, 1);
COMMIT;
ALTER SEQUENCE public.cep_seq RESTART WITH 2;

INSERT INTO public.entidade(ent_id, ent_tipopessoa, ent_tipocadastro, ent_nome, ent_login, ent_senha, ent_inativo, ent_datacadastro, registrocep, ent_numero, versionnum)
VALUES (1, 'TIPO_PESSOA_FISICA', 'TIPO_CADASTRO_USUARIO', 'Eduardo Henrique de Paula', 'Admin', 'admin', false, '2020-02-15 00:00:00', 1, 1, 0);
INSERT INTO public.entidade(ent_id, ent_tipopessoa, ent_tipocadastro, ent_nome, ent_login, ent_senha, ent_inativo, ent_datacadastro, registrocep, ent_numero, versionnum)
VALUES (2, 'TIPO_PESSOA_FISICA', 'TIPO_CADASTRO_USUARIO', 'Irene Assis Rocha', 'User', 'user', false, '2020-02-15 00:00:00', 1, 1, 0);
COMMIT;
ALTER SEQUENCE public.entidade_seq RESTART WITH 3;
			 			 
INSERT INTO public.entidadeacesso (ent_id, esa_codigo) VALUES (1, 'CEO');
INSERT INTO public.entidadeacesso (ent_id, esa_codigo) VALUES (1, 'ADMIN');
INSERT INTO public.entidadeacesso (ent_id, esa_codigo) VALUES (1, 'USER');
INSERT INTO public.entidadeacesso (ent_id, esa_codigo) VALUES (1, 'APPLET_FILE_LOCAL');
INSERT INTO public.entidadeacesso (ent_id, esa_codigo) VALUES (2, 'USER');
COMMIT;

