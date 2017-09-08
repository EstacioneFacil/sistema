INSERT INTO menu (descricao, ordem, grupo, menu_pai, classe) VALUES ('Vagas', 3, 1, 1, 'view.vaga.VagaLista');
INSERT INTO permissao (grupo_permissao_id, menu_id, visualizar) VALUES (1, 7, true);
ALTER TABLE vaga ADD descricao VARCHAR(255);
INSERT INTO menu_botao (menu_id, botao_id) VALUES (7, 1);
INSERT INTO menu_botao (menu_id, botao_id) VALUES (7, 2);
INSERT INTO menu_botao (menu_id, botao_id) VALUES (7, 3);
