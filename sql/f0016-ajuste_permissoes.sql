DROP VIEW buscar_menu;
CREATE VIEW buscar_menu AS
SELECT m.id, m.descricao, m.ordem, m.grupo, m.menu_pai, m.classe, m.visivel FROM menu m WHERE m.menu_pai IS NULL
UNION
SELECT m.id, m.descricao, m.ordem, m.grupo, m.menu_pai, m.classe, m.visivel FROM menu m
INNER JOIN permissao p ON p.menu_id = m.id
INNER JOIN grupo_permissao gp ON p.grupo_permissao_id = gp.id
INNER JOIN usuario u ON u.grupo_permissao_id = gp.id
WHERE p.visualizar = 't'
AND u.id = (SELECT id FROM usuario WHERE login = session_user::VARCHAR);
ALTER TABLE menu ADD visivel boolean DEFAULT TRUE;
INSERT INTO menu (descricao, visivel) VALUES ('Dashboard', false);
INSERT INTO menu (descricao, visivel) VALUES ('Dashboard - Vagas', false);
INSERT INTO permissao (grupo_permissao_id, menu_id, visualizar) VALUES (1, 13, true);
INSERT INTO permissao (grupo_permissao_id, menu_id, visualizar) VALUES (1, 14, true);