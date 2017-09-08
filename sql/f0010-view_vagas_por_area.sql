CREATE OR REPLACE VIEW  vagas_por_area AS 
SELECT vaga.codigo, vaga.descricao, area.descricao as area
  FROM vaga
INNER JOIN area ON vaga.area_id = area.id
