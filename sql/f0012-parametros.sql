CREATE TABLE parametro (
 id SERIAL NOT NULL,
 utilizar_webservice BOOLEAN DEFAULT TRUE,
 tirar_foto BOOLEAN DEFAULT TRUE
);
ALTER TABLE parametro ADD CONSTRAINT PK_parametro PRIMARY KEY (id);

INSERT INTO parametro (utilizar_webservice, tirar_foto) VALUES (true, true);
INSERT INTO menu (descricao, ordem, grupo) VALUES ('Configuração', 3, 1);
INSERT INTO menu (descricao, ordem, grupo, menu_pai, classe) VALUES ('Parâmetros', 1, 1, 11, 'view.configuracao.Parametros');
INSERT INTO permissao (grupo_permissao_id, menu_id, visualizar) VALUES (1, 12, true);