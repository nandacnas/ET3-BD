'''
RESTA FAZER:

+ Pergunta de UPDATE que fiz pro professor 
+ testar oq ja fizemos
+ Comecar a parte 2

'''

USE CONFLITOS_BELICOS;

CREATE RULE I_TERRITORIAL_ERRADO AS
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

CREATE RULE U_TERRITORIAL_ERRADO AS
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



CREATE RULE I_RELIGIOSO_ERRADO AS
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

CREATE RULE U_RELIGIOSO_ERRADO AS
ON UPDATE TO CONFLITO_RELIGIOSO WHERE NEW.TIPO = 'religioso' AND
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




CREATE RULE I_RACIAL_ERRADO AS
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

CREATE RULE U_RACIAL_ERRADO AS
ON UPDATE TO CONFLITO_RACIAL WHERE NEW.TIPO = 'racial' AND
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




CREATE RULE I_ECONOMICO_ERRADO AS
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

CREATE RULE U_ECONOMICO_ERRADO AS
ON UPDATE TO CONFLITO_ECONOMICO WHERE NEW.TIPO = 'economico' AND
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
CREATE RULE U_MIN_CHEFES_IN_DIVISAO AS
ON UPDATE TO CHEFE_MILITAR
WHERE ((SELECT COUNT(*) 
        FROM CHEFE_MILITAR
        WHERE D_NRO_DIVISAO = OLD.D_NRO_DIVISAO) < 1)
DO INSTEAD NOTHING;

CREATE RULE D_MIN_CHEFES_IN_DIVISAO AS
ON DELETE TO CHEFE_MILITAR
WHERE ((SELECT COUNT(*) 
        FROM CHEFE_MILITAR
        WHERE D_NRO_DIVISAO = OLD.D_NRO_DIVISAO) < 1)
DO INSTEAD NOTHING;



-- D
CREATE RULE I_MAX_CHEFES_IN_DIVISAO AS
ON INSERT TO CHEFE_MILITAR 
WHERE ((SELECT COUNT(*) 
        FROM CHEFE_MILITAR
        WHERE D_NRO_DIVISAO = NEW.D_NRO_DIVISAO) > 3)
DO INSTEAD NOTHING;

CREATE RULE U_MAX_CHEFES_IN_DIVISAO AS
ON UPDATE TO CHEFE_MILITAR 
WHERE ((SELECT COUNT(*) 
        FROM CHEFE_MILITAR
        WHERE D_NRO_DIVISAO = NEW.D_NRO_DIVISAO) > 3)
DO INSTEAD NOTHING;


-- E
CREATE RULE U_MIN_DIVISAO_IN_GRUPO_ARMADO AS
ON UPDATE TO DIVISAO  
WHERE ((SELECT COUNT(*) 
        FROM DIVISAO
        WHERE COD_GRUPO = OLD.COD_GRUPO) = 0)
DO INSTEAD NOTHING;

CREATE RULE D_MIN_DIVISAO_IN_GRUPO_ARMADO AS
ON DELETE TO DIVISAO  
WHERE ((SELECT COUNT(*) 
        FROM DIVISAO
        WHERE COD_GRUPO = OLD.COD_GRUPO) = 0)
DO INSTEAD NOTHING;


-- F
CREATE RULE U_MIN_CONFLITOS_GRUPO AS
ON UPDATE TO GRUPOS_x_CONFLITOS 
WHERE ((SELECT COUNT(*)              
        FROM GRUPOS_x_CONFLITOS
        WHERE OLD.COD_CONFLITO = GRUPOS_x_CONFLITOS.COD_CONFLITO) < 2)
DO INSTEAD NOTHING;     

CREATE RULE D_MIN_CONFLITOS_GRUPO AS
ON DELETE TO GRUPOS_x_CONFLITOS 
WHERE ((SELECT COUNT(*)              
        FROM GRUPOS_x_CONFLITOS
        WHERE OLD.COD_CONFLITO = GRUPOS_x_CONFLITOS.COD_CONFLITO) < 2)
DO INSTEAD NOTHING;  


-- G
CREATE RULE U_MIN_PAISES_AFETADOS AS
ON UPDATE TO PAIS  
WHERE ((SELECT COUNT(*)
        FROM PAIS
        WHERE COD_CONFLITO = OLD.COD_CONFLITO) = 0)
DO INSTEAD NOTHING;  

CREATE RULE D_MIN_PAISES_AFETADOS AS
ON DELETE TO PAIS  
WHERE ((SELECT COUNT(*)
        FROM PAIS
        WHERE COD_CONFLITO = OLD.COD_CONFLITO) = 0)
DO INSTEAD NOTHING;  


-- H pt1
CREATE OR REPLACE FUNCTION FUNC_NUM_BAIXAS()
RETURNS trigger AS
$BODY$
BEGIN
        IF INSERTING THEN
                UPDATE GRUPO_ARMADO
                SET NUM_BAIXAS_GRUPO = (SELECT SUM(NUM_BAIXAS_DIVISAO) 
                                        FROM DIVISAO
                                        WHERE COD_GRUPO = NEW.COD_GRUPO)
                WHERE COD_GRUPO = OLD.COD_GRUPO;
        ELSIF UPDATING THEN
                UPDATE GRUPO_ARMADO
                SET NUM_BAIXAS_GRUPO = (SELECT SUM(NUM_BAIXAS_DIVISAO) 
                                        FROM DIVISAO
                                        WHERE COD_GRUPO = OLD.COD_GRUPO)
                WHERE COD_GRUPO = OLD.COD_GRUPO;

                UPDATE GRUPO_ARMADO
                SET NUM_BAIXAS_GRUPO = (SELECT SUM(NUM_BAIXAS_DIVISAO) 
                                        FROM DIVISAO
                                        WHERE COD_GRUPO = NEW.COD_GRUPO)
                WHERE COD_GRUPO = NEW.COD_GRUPO;
        ELSE 
                UPDATE GRUPO_ARMADO
                SET NUM_BAIXAS_GRUPO = (SELECT SUM(NUM_BAIXAS_DIVISAO) 
                                        FROM DIVISAO
                                        WHERE COD_GRUPO = OLD.COD_GRUPO)
                WHERE COD_GRUPO = OLD.COD_GRUPO;
        END IF;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER UPDATE_NUM_BAIXAS 
AFTER INSERT OR UPDATE OR DELETE ON DIVISAO
FOR EACH ROW 
EXECUTE PROCEDURE FUNC_NUM_BAIXAS();





-- H pt2
CREATE OR REPLACE FUNCTION FUNC_SEQUENCE_DIVISAO2()
RETURNS trigger AS
$BODY$
DECLARE
        i int;
        total int;
BEGIN           
        IF UPDATING ('NUM_DIVISAO') THEN  
                RAISE EXCEPTION 'Você nao pode atualizar o NUM_DIVISAO da tabela 
                DIVISAO. Os outros dados foram atualizados';
                UPDATE DIVISAO
                SET NUM_DIVISAO = OLD.NUM_DIVISAO
                WHERE COD_GRUPO = OLD.COD_GRUPO AND NUM_DIVISAO = i+1;
        ELSIF  UPDATING('COD_GRUPO') OR DELETING THEN
                i :=  OLD.NUM_DIVISAO;
                total = (SELECT CONT(NUM_DIVISAO) 
                         FROM DIVISAO 
                         WHERE COD_GRUPO = OLD.COD_GRUPO);

                WHILE i < total LOOP
                        UPDATE DIVISAO
                        SET NUM_DIVISAO = i
                        WHERE COD_GRUPO = OLD.COD_GRUPO AND NUM_DIVISAO = i+1;
                        i := i + 1;
                END LOOP;   
        END IF;
        IF UPDATING('COD_GRUPO') THEN 
                total = (SELECT CONT(NUM_DIVISAO) 
                FROM DIVISAO 
                WHERE COD_GRUPO = NEW.COD_GRUPO);
                UPDATE DIVISAO
                SET NUM_DIVISAO = total
                WHERE COD_GRUPO = NEW.COD_GRUPO AND NUM_DIVISAO = NEW.NUM_DIVISAO;
        END IF;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER UPDATE_SEQUENCE_DIVISAO2
AFTER UPDATE OR DELETE ON DIVISAO
FOR EACH ROW 
EXECUTE PROCEDURE FUNC_SEQUENCE_DIVISAO2();










-- H pt2 FEITO PELA NANDA
--CREATE FUNCTION SEQ_NUM_DIVISAO()
--ALTER SEQUENCE SEQ_NUM_DIV RESTART WITH 1;
--UPDATE DIVISAO SET NUM_DIVISAO = nextval('SEQ_NUM_DIV');

--CREATE TRIGGER ATT_NUM_DIVISAO
--AFTER DELETE ON DIVISAO
--FOR EACH ROW
--EXECUTE PROCEDURE SEQ_NUM_DIVISAO();
