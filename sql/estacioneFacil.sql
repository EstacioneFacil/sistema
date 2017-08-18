CREATE TABLE anexo (
 id SERIAL NOT NULL,
 data TIMESTAMP NOT NULL,
 nome VARCHAR(255) NOT NULL,
 observacao VARCHAR(255)
);

ALTER TABLE anexo ADD CONSTRAINT PK_anexo PRIMARY KEY (id);


CREATE TABLE area (
 id SERIAL NOT NULL,
 descricao VARCHAR(255) NOT NULL
);

ALTER TABLE area ADD CONSTRAINT PK_area PRIMARY KEY (id);


CREATE TABLE grupo_permissao (
 id SERIAL NOT NULL,
 descricao VARCHAR(255) NOT NULL
);

ALTER TABLE grupo_permissao ADD CONSTRAINT PK_grupo_permissao PRIMARY KEY (id);


CREATE TABLE menu (
 id SERIAL NOT NULL,
 descricao VARCHAR(255) NOT NULL,
 ordem INT,
 grupo INT,
 menu_id INT
);

ALTER TABLE menu ADD CONSTRAINT PK_menu PRIMARY KEY (id);


CREATE TABLE permissao (
 id SERIAL NOT NULL,
 grupo_permissao_id SERIAL NOT NULL,
 menu_id SERIAL NOT NULL,
 visualizar BOOLEAN DEFAULT true NOT NULL,
 inserir BOOLEAN DEFAULT true NOT NULL,
 editar BOOLEAN DEFAULT true NOT NULL,
 excluir BOOLEAN DEFAULT true NOT NULL
);

ALTER TABLE permissao ADD CONSTRAINT PK_permissao PRIMARY KEY (id);


CREATE TABLE tipo_veiculo (
 id SERIAL NOT NULL,
 descricao VARCHAR(255) NOT NULL
);

ALTER TABLE tipo_veiculo ADD CONSTRAINT PK_tipo_veiculo PRIMARY KEY (id);


CREATE TABLE usuario (
 id SERIAL NOT NULL,
 nome VARCHAR(255) NOT NULL,
 senha VARCHAR(255) NOT NULL,
 login VARCHAR(10) NOT NULL,
 grupo_permissao_id SERIAL NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT PK_usuario PRIMARY KEY (id);


CREATE TABLE vaga (
 id SERIAL NOT NULL,
 codigo VARCHAR(10),
 area_id SERIAL NOT NULL,
 tipo_veiculo_id SERIAL
);

ALTER TABLE vaga ADD CONSTRAINT PK_vaga PRIMARY KEY (id);


CREATE TABLE auditoria (
 id SERIAL NOT NULL,
 tabela VARCHAR(255) NOT NULL,
 datahora TIMESTAMP NOT NULL,
 acao VARCHAR(255) NOT NULL,
 query TEXT NOT NULL,
 usuario_id SERIAL
);

ALTER TABLE auditoria ADD CONSTRAINT PK_auditoria PRIMARY KEY (id);


CREATE TABLE auditoria_detalhe (
 id SERIAL NOT NULL,
 auditoria_id SERIAL NOT NULL,
 coluna VARCHAR(255) NOT NULL,
 valor_antigo TEXT,
 valor_novo TEXT,
 chaveprimaria BOOLEAN DEFAULT FALSE NOT NULL
);

ALTER TABLE auditoria_detalhe ADD CONSTRAINT PK_auditoria_detalhe PRIMARY KEY (id);


CREATE TABLE tabela_preco (
 id SERIAL NOT NULL,
 data_inicio DATE NOT NULL,
 data_fim DATE,
 valor REAL NOT NULL,
 area_id SERIAL NOT NULL,
 tipo CHAR(10) NOT NULL,
 tipo_veiculo_id SERIAL,
 vaga_id SERIAL
);

ALTER TABLE tabela_preco ADD CONSTRAINT PK_tabela_preco PRIMARY KEY (id);


CREATE TABLE movimentacao (
 id SERIAL NOT NULL,
 data_hora_entrada TIMESTAMP NOT NULL,
 data_hora_saida TIMESTAMP,
 placa VARCHAR(10) NOT NULL,
 valor REAL,
 vaga_id SERIAL NOT NULL,
 anexo_id SERIAL NOT NULL,
 usuario_id SERIAL NOT NULL,
 tabela_preco_id SERIAL NOT NULL
);

ALTER TABLE movimentacao ADD CONSTRAINT PK_movimentacao PRIMARY KEY (id);


ALTER TABLE permissao ADD CONSTRAINT FK_permissao_0 FOREIGN KEY (grupo_permissao_id) REFERENCES grupo_permissao (id);
ALTER TABLE permissao ADD CONSTRAINT FK_permissao_1 FOREIGN KEY (menu_id) REFERENCES menu (id);


ALTER TABLE usuario ADD CONSTRAINT FK_usuario_0 FOREIGN KEY (grupo_permissao_id) REFERENCES grupo_permissao (id);


ALTER TABLE vaga ADD CONSTRAINT FK_vaga_0 FOREIGN KEY (area_id) REFERENCES area (id);
ALTER TABLE vaga ADD CONSTRAINT FK_vaga_1 FOREIGN KEY (tipo_veiculo_id) REFERENCES tipo_veiculo (id);


ALTER TABLE auditoria ADD CONSTRAINT FK_auditoria_0 FOREIGN KEY (usuario_id) REFERENCES usuario (id);


ALTER TABLE auditoria_detalhe ADD CONSTRAINT FK_auditoria_detalhe_0 FOREIGN KEY (auditoria_id) REFERENCES auditoria (id);


ALTER TABLE tabela_preco ADD CONSTRAINT FK_tabela_preco_0 FOREIGN KEY (area_id) REFERENCES area (id);
ALTER TABLE tabela_preco ADD CONSTRAINT FK_tabela_preco_1 FOREIGN KEY (tipo_veiculo_id) REFERENCES tipo_veiculo (id);
ALTER TABLE tabela_preco ADD CONSTRAINT FK_tabela_preco_2 FOREIGN KEY (vaga_id) REFERENCES vaga (id);


ALTER TABLE movimentacao ADD CONSTRAINT FK_movimentacao_0 FOREIGN KEY (vaga_id) REFERENCES vaga (id);
ALTER TABLE movimentacao ADD CONSTRAINT FK_movimentacao_1 FOREIGN KEY (anexo_id) REFERENCES anexo (id);
ALTER TABLE movimentacao ADD CONSTRAINT FK_movimentacao_2 FOREIGN KEY (usuario_id) REFERENCES usuario (id);
ALTER TABLE movimentacao ADD CONSTRAINT FK_movimentacao_3 FOREIGN KEY (tabela_preco_id) REFERENCES tabela_preco (id);


