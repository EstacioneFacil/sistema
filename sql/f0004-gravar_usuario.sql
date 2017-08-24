CREATE OR REPLACE FUNCTION gravar_usuario() 
RETURNS TRIGGER AS
$BODY$
DECLARE
v_usuario VARCHAR;
v_query TEXT;
BEGIN
IF NOT EXISTS (SELECT FROM pg_catalog.pg_user WHERE usename = NEW.login) THEN
	CREATE ROLE my_user LOGIN PASSWORD 'my_password';
END IF;
v_usuario := NEW.login;

v_query := "CREATE ROLE "||v_usuario||" SUPERUSER LOGIN;";

--CREATE ROLE 

END;
$BODY$
LANGUAGE plpgsql;