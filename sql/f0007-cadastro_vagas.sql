INSERT INTO menu (descricao, ordem, grupo, menu_pai, classe) VALUES ('Vagas', 3, 1, 1, 'view.vaga.VagaLista');
INSERT INTO permissao (grupo_permissao_id, menu_id, visualizar) VALUES (1, 8, true);
ALTER TABLE vaga ADD descricao VARCHAR(255);
