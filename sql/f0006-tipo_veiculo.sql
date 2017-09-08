INSERT INTO menu (descricao, ordem, grupo, menu_pai, classe) VALUES ('Tipos de Veículo', 1, 1, 1, 'view.tipoVeiculo.TipoVeiculoLista');
INSERT INTO permissao (grupo_permissao_id, menu_id, visualizar) VALUES (1, 6, true);
INSERT INTO menu_botao (menu_id, botao_id) VALUES (6, 1);
INSERT INTO menu_botao (menu_id, botao_id) VALUES (6, 2);
INSERT INTO menu_botao (menu_id, botao_id) VALUES (6, 3);

