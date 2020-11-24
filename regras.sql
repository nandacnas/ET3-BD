USE CONFLITOS_BELICOS;

CREATE RULE TERRITORIAL_ERRADO AS
ON INSERT TO CONFLITO_TERRITORIAL WHERE NEW.TIPO = 'territorial' AND
NEW.COD_CONFLITO IN (SELECT COD_CONFLITO FROM 
                            CONFLITO WHERE CONFLITO.TIPO = 'religioso') 
                            OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO FROM 
                            CONFLITO WHERE CONFLITO.TIPO = 'racial')
                            OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO FROM 
                            CONFLITO WHERE CONFLITO.TIPO = 'economico')
DO INSTEAD NOTHING;

CREATE RULE RELIGIOSO_ERRADO AS
ON INSERT TO CONFLITO_RELIGIOSO WHERE NEW.TIPO = 'religioso' AND
NEW.COD_CONFLITO IN (SELECT COD_CONFLITO FROM 
                            CONFLITO WHERE CONFLITO.TIPO = 'territorial') 
                            OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO FROM 
                            CONFLITO WHERE CONFLITO.TIPO = 'racial')
                            OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO FROM 
                            CONFLITO WHERE CONFLITO.TIPO = 'economico')
DO INSTEAD NOTHING;

CREATE RULE RACIAL_ERRADO AS
ON INSERT TO CONFLITO_RACIAL WHERE NEW.TIPO = 'racial' AND
NEW.COD_CONFLITO IN (SELECT COD_CONFLITO FROM 
                            CONFLITO WHERE CONFLITO.TIPO = 'religioso') 
                            OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO FROM 
                            CONFLITO WHERE CONFLITO.TIPO = 'territorial')
                            OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO FROM 
                            CONFLITO WHERE CONFLITO.TIPO = 'economico')
DO INSTEAD NOTHING;

CREATE RULE ECONOMICO_ERRADO AS
ON INSERT TO CONFLITO_ECONOMICO WHERE NEW.TIPO = 'economico' AND
NEW.COD_CONFLITO IN (SELECT COD_CONFLITO FROM 
                            CONFLITO WHERE CONFLITO.TIPO = 'religioso') 
                            OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO FROM 
                            CONFLITO WHERE CONFLITO.TIPO = 'racial')
                            OR NEW.COD_CONFLITO IN (SELECT COD_CONFLITO FROM 
                            CONFLITO WHERE CONFLITO.TIPO = 'territorial')
DO INSTEAD NOTHING;


--          ARRUMANDO


CREATE OR REPLACE FUNCTION limite_chefes_upaft()
  RETURNS trigger AS
$BODY$
BEGIN

IF OLD.NUM_DIVISAO <> NEW.NUM_DIVISAO THEN
    UPDATE DIVISAO
    SET    NUM_CHEFES = NUM_CHEFES - 1
    WHERE  NUM_DIVISAO = OLD.NUM_DIVISAO;

    UPDATE DIVISAO
    SET    NUM_CHEFES = NUM_CHEFES + 1
    WHERE  NUM_DIVISAO = NEW.NUM_DIVISAO;
END IF;

RETURN NULL;

END;
$BODY$
  LANGUAGE plpgsql;

CREATE TRIGGER upaft
  AFTER UPDATE ON CHEFE_MILITAR FOR EACH ROW
  EXECUTE PROCEDURE limite_chefes_upaft();


CREATE OR REPLACE FUNCTION limite_chefes_insaft()
  RETURNS trigger AS
$BODY$
BEGIN

UPDATE DIVISAO
SET    NUM_CHEFES = NUM_CHEFES + 1
WHERE  NUM_DIVISAO = NEW.CHEFE_MILITAR.D_NRO_DIVISAO;

RETURN NULL;

END;
$BODY$
  LANGUAGE plpgsql;

CREATE TRIGGER insaft
  AFTER INSERT ON CHEFE_MILITAR FOR EACH ROW
  EXECUTE PROCEDURE limite_chefes_insaft();


CREATE OR REPLACE FUNCTION limite_chefes_delaft()
  RETURNS trigger AS
$BODY$
BEGIN

UPDATE DIVISAO
SET    NUM_CHEFES = NUM_CHEFES - 1
WHERE  NUM_DIVISAO = OLD.CHEFE_MILITAR.D_NRO_DIVISAO;

RETURN NULL;

END;
$BODY$
  LANGUAGE plpgsql;

CREATE TRIGGER delaft
  AFTER DELETE ON CHEFE_MILITAR FOR EACH ROW
  EXECUTE PROCEDURE limite_chefes_delaft();