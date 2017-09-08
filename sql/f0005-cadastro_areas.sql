INSERT INTO menu (descricao, ordem, grupo, menu_pai, classe) VALUES ('Áreas', 2, 1, 1, 'view.area.AreaLista');
INSERT INTO permissao (grupo_permissao_id, menu_id, visualizar) VALUES (1, 5, true);
INSERT INTO menu_botao (menu_id, botao_id) VALUES (5, 1);
INSERT INTO menu_botao (menu_id, botao_id) VALUES (5, 2);
INSERT INTO menu_botao (menu_id, botao_id) VALUES (5, 3);
