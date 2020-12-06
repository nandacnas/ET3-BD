


-- A
CREATE OR REPLACE FUNCTION EXCLUSIVIDADE_CONFLITOS()
RETURNS trigger AS
$BODY$
DECLARE 
	tipo_conflito char(20);
BEGIN
	
	tipo_conflito := (SELECT TIPO FROM CONFLITO WHERE COD_CONFLITO = NEW.COD_CONFLITO);
	IF(tipo_conflito <> NEW.TIPO) THEN
		RAISE EXCEPTION 'Conflito do tipo % se relacionando com o tipo %.', tipo_conflito, NEW.TIPO;	
	
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;



CREATE TRIGGER ECONOMICO_ERRADO
BEFORE UPDATE OR INSERT ON CONFLITO_ECONOMICO
FOR EACH ROW 
EXECUTE PROCEDURE EXCLUSIVIDADE_CONFLITOS();

CREATE TRIGGER RACIAL_ERRADO
BEFORE UPDATE OR INSERT ON CONFLITO_RACIAL
FOR EACH ROW 
EXECUTE PROCEDURE EXCLUSIVIDADE_CONFLITOS();

CREATE TRIGGER RELIGIOSO_ERRADO
BEFORE UPDATE OR INSERT ON CONFLITO_RELIGIOSO
FOR EACH ROW 
EXECUTE PROCEDURE EXCLUSIVIDADE_CONFLITOS();

CREATE TRIGGER TERRITORIAL_ERRADO
BEFORE UPDATE OR INSERT ON CONFLITO_TERRITORIAL
FOR EACH ROW 
EXECUTE PROCEDURE EXCLUSIVIDADE_CONFLITOS();







-- C
CREATE OR REPLACE FUNCTION MIN_CHEFES_IN_DIVISAO()
RETURNS trigger AS
$BODY$
BEGIN
	IF (TG_OP = 'DELETE') THEN
		IF ((SELECT COUNT(COD_CHEFE) FROM CHEFE_MILITAR WHERE D_COD_GRUPO = OLD.D_COD_GRUPO AND NUM_DIVISAO = OLD.NUM_DIVISAO) = 1) THEN
			IF ((SELECT COUNT(*) FROM DIVISAO WHERE COD_GRUPO = OLD.D_COD_GRUPO AND NUM_DIVISAO = OLD.NUM_DIVISAO ) <> 0) THEN
				RAISE EXCEPTION 'Divisao % não pode ter menos que 1 Chefe.', OLD.NUM_DIVISAO;	
			END IF;	
		END IF;
		RETURN OLD;
	END IF;
	IF (OLD.D_COD_GRUPO <> NEW.D_COD_GRUPO) OR (OLD.NUM_DIVISAO <> NEW.NUM_DIVISAO) THEN
		IF ((SELECT COUNT(COD_CHEFE) FROM CHEFE_MILITAR WHERE D_COD_GRUPO = OLD.D_COD_GRUPO AND NUM_DIVISAO = OLD.NUM_DIVISAO) = 1) THEN
			IF ((SELECT COUNT(*) FROM DIVISAO WHERE COD_GRUPO = OLD.D_COD_GRUPO AND NUM_DIVISAO = OLD.NUM_DIVISAO ) <> 0) THEN
				RAISE EXCEPTION 'Divisao % não pode ter menos que 1 Chefe.', OLD.NUM_DIVISAO;	
			END IF;
		END IF;
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;


CREATE TRIGGER MIN_CHEFES_IN_DIVISAO
BEFORE UPDATE OR DELETE ON CHEFE_MILITAR
FOR EACH ROW 
EXECUTE PROCEDURE MIN_CHEFES_IN_DIVISAO();










-- D
CREATE OR REPLACE FUNCTION MAX_CHEFES_IN_DIVISAO()
RETURNS trigger AS
$BODY$
BEGIN
	IF (TG_OP = 'INSERT') THEN
		IF ((SELECT COUNT(COD_CHEFE) FROM CHEFE_MILITAR WHERE D_COD_GRUPO = NEW.D_COD_GRUPO AND NUM_DIVISAO = NEW.NUM_DIVISAO) = 3) THEN
			RAISE EXCEPTION 'Divisao % não pode ter mais que 3 Chefes.', NEW.NUM_DIVISAO;	
		END IF;
		RETURN NEW;
	END IF;
	IF (OLD.D_COD_GRUPO <> NEW.D_COD_GRUPO) OR (OLD.NUM_DIVISAO <> NEW.NUM_DIVISAO) THEN
		IF ((SELECT COUNT(COD_CHEFE) FROM CHEFE_MILITAR WHERE D_COD_GRUPO = NEW.D_COD_GRUPO AND NUM_DIVISAO = NEW.NUM_DIVISAO) = 3) THEN
			RAISE EXCEPTION 'Divisao % não pode ter mais que 3 Chefes.', NEW.NUM_DIVISAO;	
		END IF;
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;


CREATE TRIGGER MAX_CHEFES_IN_DIVISAO
BEFORE UPDATE OR INSERT ON CHEFE_MILITAR
FOR EACH ROW 
EXECUTE PROCEDURE MAX_CHEFES_IN_DIVISAO();









-- E
CREATE OR REPLACE FUNCTION MIN_DIVISAO_IN_GRUPO()
RETURNS trigger AS
$BODY$
BEGIN
	IF (TG_OP = 'DELETE') THEN
		IF ((SELECT COUNT(NUM_DIVISAO) FROM DIVISAO WHERE COD_GRUPO = OLD.COD_GRUPO) = 1) THEN
			IF ((SELECT COUNT(*) FROM GRUPO_ARMADO WHERE COD_GRUPO = OLD.COD_GRUPO) <> 0) THEN
				RAISE EXCEPTION 'O grupo % não pode ter menos que uma divisao.', OLD.COD_GRUPO;	
			END IF;
		END IF;
		RETURN OLD;
	END IF;
	IF (OLD.COD_GRUPO <> NEW.COD_GRUPO) THEN
		IF ((SELECT COUNT(NUM_DIVISAO) FROM DIVISAO WHERE COD_GRUPO = OLD.COD_GRUPO) = 1) THEN
			IF ((SELECT COUNT(*) FROM GRUPO_ARMADO WHERE COD_GRUPO = OLD.COD_GRUPO) <> 0) THEN
				RAISE EXCEPTION 'O grupo % não pode ter menos que uma divisao.', OLD.COD_GRUPO;	
			END IF;	
		END IF;
			
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;


CREATE TRIGGER MIN_DIVISAO_IN_GRUPO
BEFORE UPDATE OR DELETE ON DIVISAO
FOR EACH ROW 
EXECUTE PROCEDURE MIN_DIVISAO_IN_GRUPO();








-- F
CREATE OR REPLACE FUNCTION MIN_CONFLITOS_GRUPO()
RETURNS trigger AS
$BODY$
BEGIN
	IF (TG_OP = 'DELETE') THEN
		IF ((SELECT COUNT(COD_GRUPO) FROM GRUPOS_x_CONFLITOS WHERE COD_CONFLITO = OLD.COD_CONFLITO) <= 2) THEN
			IF (((SELECT COUNT(*) FROM CONFLITO WHERE COD_CONFLITO = OLD.COD_CONFLITO) <> 0) AND  
			    ((SELECT COUNT(*) FROM GRUPO_ARMADO WHERE COD_GRUPO = OLD.COD_GRUPO) <> 0)) THEN
				RAISE EXCEPTION 'O conflito % não pode ter menos que 2 grupos.  D', OLD.COD_CONFLITO;	
			END IF;
		END IF;
		RETURN OLD;
	END IF;
	IF (OLD.COD_CONFLITO <> NEW.COD_CONFLITO) THEN
		IF ((SELECT COUNT(COD_GRUPO) FROM GRUPOS_x_CONFLITOS WHERE COD_CONFLITO = OLD.COD_CONFLITO) <= 2) THEN
			IF (((SELECT COUNT(*) FROM CONFLITO WHERE COD_CONFLITO = OLD.COD_CONFLITO) <> 0) AND  
			    ((SELECT COUNT(*) FROM GRUPO_ARMADO WHERE COD_GRUPO = OLD.COD_GRUPO) <> 0)) THEN
				RAISE EXCEPTION 'O conflito % não pode ter menos que 2 grupos.  U', OLD.COD_CONFLITO;	
			END IF;
		END IF;
			
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;


CREATE TRIGGER MIN_CONFLITOS_GRUPO
BEFORE UPDATE OR DELETE ON GRUPOS_x_CONFLITOS
FOR EACH ROW 
EXECUTE PROCEDURE MIN_CONFLITOS_GRUPO();











-- G
CREATE OR REPLACE FUNCTION MIN_PAISES_AFETADOS()
RETURNS trigger AS
$BODY$
BEGIN
	IF (TG_OP = 'DELETE') THEN
		IF ((SELECT COUNT(PAIS) FROM PAIS WHERE COD_CONFLITO = OLD.COD_CONFLITO) = 1) THEN
			IF ((SELECT COUNT(*) FROM CONFLITO WHERE COD_CONFLITO = OLD.COD_CONFLITO) <> 0) THEN
				RAISE EXCEPTION 'Conflito % precisa de pelo menos 1 pais ligado a ele.', OLD.COD_CONFLITO;	
			END IF;	
		END IF;
		RETURN OLD;
	END IF;
	IF (OLD.COD_CONFLITO <> NEW.COD_CONFLITO) THEN
		IF ((SELECT COUNT(PAIS) FROM PAIS WHERE COD_CONFLITO = OLD.COD_CONFLITO) = 1) THEN
			IF ((SELECT COUNT(*) FROM CONFLITO WHERE COD_CONFLITO = OLD.COD_CONFLITO) <> 0) THEN
				RAISE EXCEPTION 'Conflito % precisa de pelo menos 1 pais ligado a ele.', OLD.COD_CONFLITO;	
			END IF;	
		END IF;
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;


CREATE TRIGGER MIN_PAISES_AFETADOS
BEFORE UPDATE OR DELETE ON PAIS
FOR EACH ROW 
EXECUTE PROCEDURE MIN_PAISES_AFETADOS();











-- H pt1
CREATE OR REPLACE FUNCTION FUNC_NUM_BAIXAS()
RETURNS trigger AS
$BODY$
BEGIN
        IF (TG_OP = 'INSERT') THEN
                UPDATE GRUPO_ARMADO
                SET NUM_BAIXAS_GRUPO = (SELECT SUM(NUM_BAIXAS_DIVISAO) 
                                        FROM DIVISAO
                                        WHERE COD_GRUPO = NEW.COD_GRUPO)
                WHERE COD_GRUPO = NEW.COD_GRUPO;
                
        ELSIF (TG_OP = 'DELETE') THEN 
                UPDATE GRUPO_ARMADO
                SET NUM_BAIXAS_GRUPO = (SELECT SUM(NUM_BAIXAS_DIVISAO) 
                                        FROM DIVISAO
                                        WHERE COD_GRUPO = OLD.COD_GRUPO)
                WHERE COD_GRUPO = OLD.COD_GRUPO;
                RETURN OLD; 
        ELSE
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
       
        END IF;
        RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER UPDATE_NUM_BAIXAS 
AFTER INSERT OR UPDATE OR DELETE ON DIVISAO
FOR EACH ROW 
EXECUTE PROCEDURE FUNC_NUM_BAIXAS();









-- H pt1 EXTRA: Manter a consistência das baixas totais em cada CONFLITO, a partir das baixas produzidas nos GRUPOS ARMADOS
CREATE OR REPLACE FUNCTION FUNC_NUM_BAIXAS_CONFLITO()
RETURNS trigger AS
$BODY$
BEGIN
        IF (TG_OP = 'INSERT') THEN
        	IF ((SELECT COUNT(*) FROM GRUPOS_x_CONFLITOS WHERE COD_GRUPO = NEW.COD_GRUPO) <> 0) THEN
		        UPDATE CONFLITO AS T
			SET NUM_MORTOS = (SELECT SUM(NUM_BAIXAS_GRUPO) 
				                FROM (GRUPO_ARMADO INNER JOIN GRUPOS_x_CONFLITOS 
				                ON (GRUPOS_x_CONFLITOS.COD_GRUPO = GRUPO_ARMADO.COD_GRUPO)) AS S 
				                WHERE S.COD_CONFLITO = T.COD_CONFLITO) 
			WHERE T.COD_CONFLITO IN (SELECT COD_CONFLITO
				              FROM GRUPOS_x_CONFLITOS
				              WHERE COD_GRUPO = NEW.COD_GRUPO );
        
        	END IF;
        ELSIF (TG_OP = 'DELETE') THEN
        	IF ((SELECT COUNT(*) FROM GRUPOS_x_CONFLITOS WHERE COD_GRUPO = NEW.COD_GRUPO) <> 0) THEN
			UPDATE CONFLITO AS T
			SET NUM_MORTOS = (SELECT SUM(NUM_BAIXAS_GRUPO) 
				                FROM (GRUPO_ARMADO INNER JOIN GRUPOS_x_CONFLITOS 
				                ON (GRUPOS_x_CONFLITOS.COD_GRUPO = GRUPO_ARMADO.COD_GRUPO)) AS S 
				                WHERE S.COD_CONFLITO = T.COD_CONFLITO) 
			WHERE T.COD_CONFLITO IN (SELECT COD_CONFLITO
				              FROM GRUPOS_x_CONFLITOS
				              WHERE COD_GRUPO = OLD.COD_GRUPO );
	       	RETURN OLD;
               END IF;
        
        ELSIF ((OLD.NUM_BAIXAS_GRUPO <> NEW.NUM_BAIXAS_GRUPO) OR (OLD.COD_GRUPO <> NEW.COD_GRUPO)) THEN
        	IF ((SELECT COUNT(*) FROM GRUPOS_x_CONFLITOS WHERE COD_GRUPO = NEW.COD_GRUPO) <> 0) THEN
			UPDATE CONFLITO AS T
			SET NUM_MORTOS = NUM_MORTOS - NEW.NUM_BAIXAS_GRUPO
			WHERE T.COD_CONFLITO IN (SELECT COD_CONFLITO
				              FROM GRUPOS_x_CONFLITOS
				              WHERE COD_GRUPO = OLD.COD_GRUPO );


			UPDATE CONFLITO AS T
			SET NUM_MORTOS = (SELECT SUM(NUM_BAIXAS_GRUPO) 
				                FROM (GRUPO_ARMADO INNER JOIN GRUPOS_x_CONFLITOS 
				                ON (GRUPOS_x_CONFLITOS.COD_GRUPO = GRUPO_ARMADO.COD_GRUPO)) AS S 
				                WHERE S.COD_CONFLITO = T.COD_CONFLITO) 
			WHERE T.COD_CONFLITO IN (SELECT COD_CONFLITO
				              FROM GRUPOS_x_CONFLITOS
				              WHERE COD_GRUPO = NEW.COD_GRUPO);
		END IF;
        END IF;
        RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER UPDATE_NUM_BAIXAS_CONFLITO
AFTER INSERT OR UPDATE OR DELETE ON GRUPO_ARMADO
FOR EACH ROW 
EXECUTE PROCEDURE FUNC_NUM_BAIXAS_CONFLITO();








-- H pt1 EXTRA: (Continuacao...)
CREATE OR REPLACE FUNCTION FUNC_NUM_BAIXAS_CONFLITO2()
RETURNS trigger AS
$BODY$
BEGIN
        IF (TG_OP = 'INSERT') THEN
        	UPDATE CONFLITO 
		SET NUM_MORTOS = (NUM_MORTOS + (SELECT NUM_BAIXAS_GRUPO FROM GRUPO_ARMADO WHERE COD_GRUPO = NEW.COD_GRUPO))
       	WHERE COD_CONFLITO = NEW.COD_CONFLITO;
        ELSIF (TG_OP = 'DELETE') THEN
		UPDATE CONFLITO 
		SET NUM_MORTOS = (NUM_MORTOS - (SELECT NUM_BAIXAS_GRUPO FROM GRUPO_ARMADO WHERE COD_GRUPO = OLD.COD_GRUPO))
       	WHERE COD_CONFLITO = OLD.COD_CONFLITO;
		RETURN OLD;
        ELSE 
		UPDATE CONFLITO 
		SET NUM_MORTOS = (NUM_MORTOS - (SELECT NUM_BAIXAS_GRUPO FROM GRUPO_ARMADO WHERE COD_GRUPO = OLD.COD_GRUPO))
       	WHERE COD_CONFLITO = OLD.COD_CONFLITO;
       	
       	UPDATE CONFLITO 
		SET NUM_MORTOS = (NUM_MORTOS + (SELECT NUM_BAIXAS_GRUPO FROM GRUPO_ARMADO WHERE COD_GRUPO = NEW.COD_GRUPO))
       	WHERE COD_CONFLITO = NEW.COD_CONFLITO;
        END IF;
        RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;



CREATE TRIGGER UPDATE_NUM_BAIXAS_CONFLITO2
AFTER INSERT OR UPDATE OR DELETE ON GRUPOS_x_CONFLITOS
FOR EACH ROW 
EXECUTE PROCEDURE FUNC_NUM_BAIXAS_CONFLITO2();










-- H pt2 
CREATE OR REPLACE FUNCTION SEQ_NUM_DIVISAO()
RETURNS trigger AS
$BODY$
BEGIN
	IF (TG_OP = 'INSERT') THEN
		ALTER SEQUENCE SEQ_NUM_DIV RESTART WITH 1;
		UPDATE DIVISAO 
		SET NUM_DIVISAO = nextval('SEQ_NUM_DIV')
		WHERE COD_GRUPO = NEW.COD_GRUPO;
		RETURN NEW;
	ELSE
		ALTER SEQUENCE SEQ_NUM_DIV RESTART WITH 1;
		UPDATE DIVISAO SET NUM_DIVISAO = nextval('SEQ_NUM_DIV')
		WHERE COD_GRUPO = OLD.COD_GRUPO;
		RETURN OLD;
	END IF;
END;
$BODY$
LANGUAGE plpgsql;



CREATE TRIGGER SEQ_NUM_DIVISAO
AFTER INSERT OR DELETE ON DIVISAO
FOR EACH ROW 
EXECUTE PROCEDURE SEQ_NUM_DIVISAO();





















