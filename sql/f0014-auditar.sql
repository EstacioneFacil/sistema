CREATE OR REPLACE FUNCTION auditar() RETURNS TRIGGER AS $body$
/*************************************************************************************************************
  NAME: auditar
  PURPOSE: Função que permite auditoria de tabelas.
**************************************************************************************************************/
DECLARE
    v_original_value TEXT;
    v_new_value TEXT;
    v_col TEXT;
    v_audit_id BIGINT;
    v_do_insert BOOLEAN;
    v_verificapk BOOLEAN = FALSE;
    v_ispk BOOLEAN;
    v_path_dblink TEXT;
    v_dblink TEXT;
    v_is_dblink BOOLEAN = FALSE;
    v_current_query TEXT;
    v_next_val BIGINT;
    v_dblink_extension BOOLEAN = FALSE;
    v_database TEXT;
    v_pkeys VARCHAR[];
    v_pkey TEXT;
    v_versao TEXT;
    usuario_id INTEGER;
    auditoriaHabilitada BOOLEAN = TRUE;
BEGIN

    SELECT INTO auditoriaHabilitada utilizar_auditoria FROM parametro;
    
    IF auditoriaHabilitada IS TRUE THEN

        v_original_value := NULL;
        v_new_value := '-';
        v_next_val := nextval('auditoria_id_seq');

        --Obtem o usuario
        SELECT INTO usuario_id id FROM usuario WHERE login = session_user::VARCHAR;

          
        IF TG_OP = 'DELETE' OR TG_OP = 'UPDATE' THEN
            INSERT INTO auditoria 
                        (id, tabela, usuario_id, acao, query, datahora)
                VALUES (v_next_val, TG_TABLE_NAME::TEXT, usuario_id, TG_OP, current_query(), now());

        END IF;
               
       
        -- Percorre todas as colunas da tabela.
        FOR v_col IN SELECT attname as column_name
                       FROM pg_attribute
                      WHERE attrelid = (TG_TABLE_SCHEMA||'.'||TG_TABLE_NAME)::regclass
                        AND attnum > 0
                        AND NOT attisdropped
                   ORDER BY attnum
        LOOP
            v_ispk := FALSE;
            v_do_insert := TRUE;

            -- Identifica se o valor da coluna foi alterado, caso sim, deverá registrar nos detalhes da auditoria.
            IF TG_OP = 'DELETE' OR TG_OP = 'UPDATE' THEN
                EXECUTE 'SELECT $1.' || v_col INTO v_original_value USING OLD;
            END IF;

            IF TG_OP = 'UPDATE' THEN
                EXECUTE 'SELECT $1.' || v_col INTO v_new_value USING NEW;

                IF TG_OP = 'UPDATE' AND (v_original_value = v_new_value OR (v_original_value IS NULL AND v_new_value IS NULL)) 
                THEN
                    v_do_insert := FALSE;
                END IF;

            END IF;

            --Verifica se a coluna é chave primária
            IF v_col = 'id' THEN
                v_ispk := true;
                v_do_insert := true;
            END IF;

            IF v_do_insert THEN
                INSERT INTO auditoria_detalhe
                            (auditoria_id, coluna, valor_antigo, valor_novo, chaveprimaria)
                    VALUES (v_next_val, v_col, v_original_value, v_new_value, v_ispk);
            END IF;
            
        END LOOP;

    END IF;

    IF TG_OP = 'INSERT' OR TG_OP = 'UPDATE'
    THEN
        RETURN NEW;
    ELSE
        RETURN OLD;
    END IF;


END;
$body$ language plpgsql security definer;

