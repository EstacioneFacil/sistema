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

