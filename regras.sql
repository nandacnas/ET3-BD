USE CONFLITOS_BELICOS;

CREATE RULE TERRITORIAL_ERRADO AS
ON INSERT TO CONFLITO_TERRITORIAL WHERE NEW.TIPO = 'territorial' AND
NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                    FROM CONFLITO 
                    WHERE CONFLITO.TIPO = 'religioso') 
                    OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                                            FROM CONFLITO 
                                            WHERE CONFLITO.TIPO = 'racial')
                    OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                                            FROM CONFLITO 
                                            WHERE CONFLITO.TIPO = 'economico')
DO INSTEAD NOTHING;

CREATE RULE TERRITORIAL_ERRADO AS
ON UPDATE TO CONFLITO_TERRITORIAL WHERE NEW.TIPO = 'territorial' AND
NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                    FROM CONFLITO 
                    WHERE CONFLITO.TIPO = 'religioso') 
                    OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                                            FROM CONFLITO 
                                            WHERE CONFLITO.TIPO = 'racial')
                    OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                                            FROM CONFLITO 
                                            WHERE CONFLITO.TIPO = 'economico')
DO INSTEAD NOTHING;

CREATE RULE RELIGIOSO_ERRADO AS
ON INSERT TO CONFLITO_RELIGIOSO WHERE NEW.TIPO = 'religioso' AND
NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                    FROM CONFLITO 
                    WHERE CONFLITO.TIPO = 'territorial') 
                    OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                                            FROM CONFLITO 
                                            WHERE CONFLITO.TIPO = 'racial')
                    OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                                            FROM CONFLITO
                                            WHERE CONFLITO.TIPO = 'economico')
DO INSTEAD NOTHING;

CREATE RULE RACIAL_ERRADO AS
ON INSERT TO CONFLITO_RACIAL WHERE NEW.TIPO = 'racial' AND
NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                    FROM CONFLITO 
                    WHERE CONFLITO.TIPO = 'territorial') 
                    OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                                            FROM CONFLITO 
                                            WHERE CONFLITO.TIPO = 'religioso')
                    OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                                            FROM CONFLITO
                                            WHERE CONFLITO.TIPO = 'economico')
DO INSTEAD NOTHING;

CREATE RULE ECONOMICO_ERRADO AS
ON INSERT TO CONFLITO_ECONOMICO WHERE NEW.TIPO = 'economico' AND
NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                    FROM CONFLITO 
                    WHERE CONFLITO.TIPO = 'territorial') 
                    OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                                            FROM CONFLITO 
                                            WHERE CONFLITO.TIPO = 'racial')
                    OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO 
                                            FROM CONFLITO
                                            WHERE CONFLITO.TIPO = 'religioso')
DO INSTEAD NOTHING;



-- C
-- tem q criar a relacao do chefe militar com a divisao antes mesmo de existir a divisao!!!
-- nao poderiamos criar um chefe principal da divisao so pra forcar existir uma relacao? acho melhor
CREATE RULE MIN_CHEFES_IN_DIVISAO AS
ON INSERT TO DIVISAO  
WHERE ((SELECT COUNT(*) 
        FROM RELACIONA_CHEFE_MILITAR_E_DIVISAO
        WHERE NUM_DIVISAO = NEW.NUM_DIVISAO AND COD_GRUPO = NEW.COD_GRUPO) < 1)
DO INSTEAD NOTHING;

-- D
CREATE RULE MAX_CHEFES_IN_DIVISAO AS
ON INSERT TO RELACIONA_CHEFE_MILITAR_E_DIVISAO 
WHERE ((SELECT COUNT(*) 
        FROM RELACIONA_CHEFE_MILITAR_E_DIVISAO
        WHERE NUM_DIVISAO = NEW.NUM_DIVISAO AND COD_GRUPO = NEW.COD_GRUPO) > 3)
DO INSTEAD NOTHING;

-- E
CREATE RULE RULE_MIN_DIVISAO_IN_GRUPO_ARMADO AS
ON INSERT TO GRUPO_ARMADO 
WHERE ((SELECT COUNT(*) 
        FROM DIVISAO
        WHERE NUM_DIVISAO = NEW.NUM_DIVISAO_PRINCIPAL AND COD_GRUPO = NEW.COD_GRUPO_DIVISAO_PRINCIPAL) = 1)
DO INSTEAD NOTHING;

CREATE OR REPLACE FUNCTION MIN_DIVISAO()
  RETURNS trigger AS
$BODY$
BEGIN

UPDATE DIVISAO
SET    COD_GRUPO = NEW.COD_GRUPO
WHERE  NUM_DIVISAO = NEW.NUM_DIVISAO_PRINCIPAL AND COD_GRUPO = NEW.COD_GRUPO_DIVISAO_PRINCIPAL;

RETURN NULL;

END;
$BODY$
  LANGUAGE plpgsql;

CREATE TRIGGER TRIGGER_MIN_DIVISAO_IN_GRUPO_ARMADO
  AFTER INSERT ON GRUPO_ARMADO FOR EACH ROW
  EXECUTE PROCEDURE MIN_DIVISAO();


--F
CREATE RULE MIN_CONFLITOS_GRUPO AS
ON INSERT ON CONFLITO CHECK ((SELECT COUNT(*)
                            FROM GRUPOS_x_CONFLITOS
                            WHERE NEW.COD_CONFLITO = GRUPOS_x_CONFLITOS.COD_CONFLITO) < 2)
DO INSTEAD NOTHING;        
