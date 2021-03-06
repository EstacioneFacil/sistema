﻿CREATE OR REPLACE FUNCTION auditar_insert()
RETURNS TRIGGER AS
$BODY$
DECLARE
    v_next_val BIGINT;
    v_versao TEXT;
    usuario_id INTEGER;
BEGIN
    v_next_val := nextval('auditoria_id_seq');

    SELECT INTO usuario_id id FROM usuario WHERE login = session_user::VARCHAR;

    INSERT INTO auditoria 
	    (id, tabela, usuario_id, acao, query, datahora)
    VALUES (v_next_val, TG_TABLE_NAME::TEXT, usuario_id, TG_OP, current_query(), now());
        
    RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;
