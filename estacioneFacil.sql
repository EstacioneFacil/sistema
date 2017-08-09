CREATE TABLE areas (
 id INT NOT NULL,
 descricao VARCHAR(255)
);

ALTER TABLE areas ADD CONSTRAINT PK_areas PRIMARY KEY (id);


CREATE TABLE telas (
 id SERIAL NOT NULL,
 descricao VARCHAR(255) NOT NULL
);

ALTER TABLE telas ADD CONSTRAINT PK_telas PRIMARY KEY (id);


CREATE TABLE tipodeveiculos (
 id SERIAL NOT NULL,
 descricao VARCHAR(255)
);

ALTER TABLE tipodeveiculos ADD CONSTRAINT PK_tipodeveiculos PRIMARY KEY (id);


CREATE TABLE usuarios (
 id SERIAL NOT NULL,
 nome VARCHAR(255) NOT NULL,
 senha VARCHAR(255) NOT NULL,
 login VARCHAR(10) NOT NULL
);

ALTER TABLE usuarios ADD CONSTRAINT PK_usuarios PRIMARY KEY (id);


CREATE TABLE vagas (
 id SERIAL NOT NULL,
 codigo VARCHAR(10),
 area_id INT NOT NULL,
 tipodeveiculo_id SERIAL
);

ALTER TABLE vagas ADD CONSTRAINT PK_vagas PRIMARY KEY (id);


CREATE TABLE auditorias (
 id SERIAL NOT NULL,
 tabela VARCHAR(255) NOT NULL,
 usuario_id SERIAL NOT NULL,
 datahora TIMESTAMP NOT NULL,
 acao VARCHAR(255) NOT NULL,
 query TEXT NOT NULL
);

ALTER TABLE auditorias ADD CONSTRAINT PK_auditorias PRIMARY KEY (id);


CREATE TABLE permissoes (
 id SERIAL NOT NULL,
 usuario_id SERIAL NOT NULL,
 tela_id SERIAL NOT NULL,
 visualizar BOOLEAN DEFAULT true NOT NULL,
 inserir BOOLEAN DEFAULT true NOT NULL,
 editar BOOLEAN DEFAULT true NOT NULL,
 excluir BOOLEAN DEFAULT true NOT NULL
);

ALTER TABLE permissoes ADD CONSTRAINT PK_permissoes PRIMARY KEY (id);


CREATE TABLE precos (
 id SERIAL NOT NULL,
 datainicio DATE NOT NULL,
 datafim DATE,
 valor REAL NOT NULL,
 area_id INT NOT NULL,
 tipo CHAR(10) NOT NULL,
 tipodeveiculo_id SERIAL,
 vaga_id SERIAL
);

ALTER TABLE precos ADD CONSTRAINT PK_precos PRIMARY KEY (id);


CREATE TABLE auditoria_detalhes (
 id SERIAL NOT NULL,
 auditoria_id SERIAL NOT NULL,
 coluna VARCHAR(255) NOT NULL,
 valor_antigo TEXT,
 valor_novo TEXT,
 chaveprimaria BOOLEAN DEFAULT FALSE NOT NULL
);

ALTER TABLE auditoria_detalhes ADD CONSTRAINT PK_auditoria_detalhes PRIMARY KEY (id);


CREATE TABLE movimentacoes (
 id SERIAL NOT NULL,
 datahoraentrada TIMESTAMP NOT NULL,
 datahorasaida TIMESTAMP,
 usuario_id SERIAL NOT NULL,
 placa VARCHAR(10) NOT NULL,
 valor REAL,
 preco_id SERIAL,
 vaga_id SERIAL NOT NULL
);

ALTER TABLE movimentacoes ADD CONSTRAINT PK_movimentacoes PRIMARY KEY (id);


ALTER TABLE vagas ADD CONSTRAINT FK_vagas_0 FOREIGN KEY (area_id) REFERENCES areas (id);
ALTER TABLE vagas ADD CONSTRAINT FK_vagas_1 FOREIGN KEY (tipodeveiculo_id) REFERENCES tipodeveiculos (id);


ALTER TABLE auditorias ADD CONSTRAINT FK_auditorias_0 FOREIGN KEY (usuario_id) REFERENCES usuarios (id);


ALTER TABLE permissoes ADD CONSTRAINT FK_permissoes_0 FOREIGN KEY (usuario_id) REFERENCES usuarios (id);
ALTER TABLE permissoes ADD CONSTRAINT FK_permissoes_1 FOREIGN KEY (tela_id) REFERENCES telas (id);


ALTER TABLE precos ADD CONSTRAINT FK_precos_0 FOREIGN KEY (area_id) REFERENCES areas (id);
ALTER TABLE precos ADD CONSTRAINT FK_precos_1 FOREIGN KEY (tipodeveiculo_id) REFERENCES tipodeveiculos (id);
ALTER TABLE precos ADD CONSTRAINT FK_precos_2 FOREIGN KEY (vaga_id) REFERENCES vagas (id);


ALTER TABLE auditoria_detalhes ADD CONSTRAINT FK_auditoria_detalhes_0 FOREIGN KEY (auditoria_id) REFERENCES auditorias (id);


ALTER TABLE movimentacoes ADD CONSTRAINT FK_movimentacoes_0 FOREIGN KEY (usuario_id) REFERENCES usuarios (id);
ALTER TABLE movimentacoes ADD CONSTRAINT FK_movimentacoes_1 FOREIGN KEY (preco_id) REFERENCES precos (id);
ALTER TABLE movimentacoes ADD CONSTRAINT FK_movimentacoes_2 FOREIGN KEY (vaga_id) REFERENCES vagas (id);


