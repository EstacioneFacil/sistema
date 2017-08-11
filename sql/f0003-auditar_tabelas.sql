CREATE OR REPLACE FUNCTION auditar_todas_tabelas()
  RETURNS boolean AS
$BODY$
/***************************************************************
  NAME: auditar_todas_tabelas
  PURPOSE: Verifica se configuração está ativa e insere a 
           trigger de auditoria em todas as tabelas.
****************************************************************/
DECLARE
    
    --Recebe as tabelas que possíveis de auditoria que não estão sendo auditadas
    v_tabelas RECORD;

    --Recebe o nome da trigger que será criada
    v_trigger TEXT;

    --Recebe o nome da tabela concatenada com o esquema
    v_trigger_on TEXT;
    
BEGIN
    --Obtém todas as tabelas possíveis de auditorias que não estão sendo auditadas
    FOR v_tabelas IN SELECT nspname,
			    relname
		       FROM pg_class 
		 INNER JOIN pg_namespace
			 ON relnamespace = pg_namespace.oid
		      WHERE relkind = 'r'
			AND nspname NOT LIKE 'pg_%'
			AND nspname != 'information_schema'
			AND relname NOT IN ('auditoria', 'auditoria_detalhe')		
			AND pg_class.oid NOT IN ( SELECT tgrelid
						    FROM pg_trigger 
					      INNER JOIN pg_proc
						      ON tgfoid = pg_proc.oid
						   WHERE proname = 'auditar' )
		   ORDER BY nspname, relname
    LOOP  
	v_trigger := 'audit_' || v_tabelas.nspname || '_' || v_tabelas.relname;
	v_trigger_on := v_tabelas.nspname || '.' || v_tabelas.relname;

	v_trigger_insert := 'audit_insert' || v_tabelas.nspname || '_' || v_tabelas.relname;
	v_trigger_insert_on := v_tabelas.nspname || '.' || v_tabelas.relname;
	
	BEGIN


	
	    EXECUTE 'CREATE TRIGGER ' || v_trigger || '
			     BEFORE UPDATE OR DELETE 
				 ON ' || v_trigger_on || '
			   FOR EACH ROW EXECUTE PROCEDURE auditar()';

	   EXECUTE 'CREATE TRIGGER ' || v_trigger_insert || '
			     AFTER INSERT 
				 ON ' || v_trigger_insert_on || '
			   FOR EACH ROW EXECUTE PROCEDURE auditar_insert()';
			   
	    EXCEPTION
		WHEN OTHERS THEN
		    RAISE EXCEPTION 'Não foi possível inserir a trigger de auditoria na tabela %.%.', v_tabelas.nspname,v_tabelas.relname;
		    RETURN FALSE;
	END;        
    END LOOP;
    
    RETURN TRUE;
	 
END;
$BODY$
  LANGUAGE plpgsql;

begin;
  select * from auditar_todas_tabelas();
 commit;


