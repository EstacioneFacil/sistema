INSERT INTO menu (descricao, ordem, grupo, menu_pai, classe) VALUES ('Tabela de Preços', 4, 1, 1, 'view.tabelaPreco.TabelaPrecoLista');
INSERT INTO permissao (grupo_permissao_id, menu_id, visualizar) VALUES (1, 8, true);
INSERT INTO menu_botao (menu_id, botao_id) VALUES (8, 1);
INSERT INTO menu_botao (menu_id, botao_id) VALUES (8, 2);
INSERT INTO menu_botao (menu_id, botao_id) VALUES (8, 3);
