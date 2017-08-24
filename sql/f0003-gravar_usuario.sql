CREATE OR REPLACE FUNCTION gravar_usuario() 
RETURNS TRIGGER AS
$BODY$
DECLARE
v_usuario VARCHAR;
BEGIN
v_usuario := NEW.login;

--Verificar para utilizar a senha do usuario (ver como criptografar)

IF NOT EXISTS (SELECT 1 FROM pg_catalog.pg_user WHERE usename = v_usuario) THEN
	EXECUTE 'CREATE USER '||v_usuario||' WITH password ''postgres'' SUPERUSER LOGIN;';
END IF;

RETURN NEW;

END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER trg_criar_usuario AFTER INSERT ON usuario FOR EACH ROW EXECUTE PROCEDURE gravar_usuario();


--Função para criar primeiro usuário caso ainda não exista
CREATE OR REPLACE FUNCTION gera_usuario_inicial()
RETURNS BOOLEAN AS
$BODY$
DECLARE

BEGIN
IF NOT EXISTS (SELECT 1 FROM usuario WHERE login = 'postgres')
THEN
	IF NOT EXISTS (SELECT 1 FROM grupo_permissao WHERE descricao = 'Admin') THEN
		INSERT INTO grupo_permissao(descricao) VALUES ('Admin');
	END IF;
	INSERT INTO usuario (nome,senha,login, grupo_permissao_id) VALUES ('Admin', 'senha', 'admin', (select id from grupo_permissao where descricao = 'Admin'));
END IF;

RETURN TRUE;
END;
$BODY$
LANGUAGE plpgsql;

SELECT * FROM gera_usuario_inicial();
