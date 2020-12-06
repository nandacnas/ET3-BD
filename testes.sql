














--A

--------------------------------------------------		<TESTES>	 	------------------------------------------------------------------
INSERT INTO CONFLITO (NOME, TIPO, NUM_FERIDOS, NUM_MORTOS) VALUES ('conflito das CARRETAS', 'economico', 3000, 0);
INSERT INTO CONFLITO (NOME, TIPO, NUM_FERIDOS, NUM_MORTOS) VALUES ('conflito baioio', 'religioso', 3000, 0);


INSERT INTO CONFLITO_ECONOMICO (COD_CONFLITO, MAT_PRIMA, TIPO) VALUES (1, 'carreta', economico);

INSERT INTO CONFLITO_RELIGIOSO (COD_CONFLITO, RELIGIAO, TIPO) VALUES (2, 'baiala', 'religioso');
INSERT INTO CONFLITO_RELIGIOSO (COD_CONFLITO, RELIGIAO, TIPO) VALUES (2, 'beeoio', 'religioso');
--------------------------------------------------		<TESTES/>	 	------------------------------------------------------------------







--C e D

--------------------------------------------------		<TESTES>	 	------------------------------------------------------------------

INSERT INTO GRUPO_ARMADO (NOME_GRUPO, NUM_BAIXAS_GRUPO) VALUES('pilantras',0);
INSERT INTO GRUPO_ARMADO (NOME_GRUPO, NUM_BAIXAS_GRUPO) VALUES('BRAsilians',0);
INSERT INTO LIDER_POLITICO (NOME_LIDER, COD_GRUPO, APOIOS) VALUES('Joao Silva', 1, 'MBL');
INSERT INTO DIVISAO (COD_GRUPO, NUM_BAIXAS_DIVISAO, HOMENS) VALUES(1, 40, 1000);
INSERT INTO DIVISAO (COD_GRUPO, NUM_BAIXAS_DIVISAO, HOMENS) VALUES(1, 367, 456789);
INSERT INTO CHEFE_MILITAR (NOME_LIDER, D_COD_GRUPO, LP_COD_GRUPO, NUM_DIVISAO) VALUES('Joao Silva', 1, 1, 1);
INSERT INTO CHEFE_MILITAR (NOME_LIDER, D_COD_GRUPO, LP_COD_GRUPO, NUM_DIVISAO) VALUES('Joao Silva', 1, 1, 1);

UPDATE CHEFE_MILITAR
SET NUM_DIVISAO = 2
WHERE COD_CHEFE = 1;

INSERT INTO CHEFE_MILITAR (NOME_LIDER, D_COD_GRUPO, LP_COD_GRUPO, NUM_DIVISAO) VALUES('Joao Silva', 1, 1, 1);
INSERT INTO CHEFE_MILITAR (NOME_LIDER, D_COD_GRUPO, LP_COD_GRUPO, NUM_DIVISAO) VALUES('Joao Silva', 1, 1, 1);
--------------------------------------------------		<TESTES/>	 	------------------------------------------------------------------








--E
--------------------------------------------------		<TESTES>	 	------------------------------------------------------------------
UPDATE DIVISAO
SET COD_GRUPO = 2
WHERE COD_GRUPO = 1 AND NUM_DIVISAO = 1;
--------------------------------------------------		<TESTES/>	 	------------------------------------------------------------------





--F


--------------------------------------------------		<TESTES>	 	------------------------------------------------------------------
INSERT INTO GRUPO_ARMADO (NOME_GRUPO, NUM_BAIXAS_GRUPO) VALUES('bonossauro',0);
INSERT INTO GRUPO_ARMADO (NOME_GRUPO, NUM_BAIXAS_GRUPO) VALUES('cafe-com-leite',0);
INSERT INTO CONFLITO (NOME, TIPO, NUM_FERIDOS, NUM_MORTOS) VALUES ('Conflito dos gados', 'territorial', 3000, 0);
INSERT INTO CONFLITO (NOME, TIPO, NUM_FERIDOS, NUM_MORTOS) VALUES ('Conflito BEBUM', 'territorial', 3000, 0);
INSERT INTO GRUPOS_x_CONFLITOS (COD_CONFLITO, COD_GRUPO) VALUES(1,1);
INSERT INTO GRUPOS_x_CONFLITOS (COD_CONFLITO, COD_GRUPO) VALUES(1,2);
INSERT INTO GRUPOS_x_CONFLITOS (COD_CONFLITO, COD_GRUPO) VALUES(1,3);

DELETE FROM GRUPOS_x_CONFLITOS
WHERE COD_CONFLITO = 1 AND COD_GRUPO = 2;

UPDATE GRUPOS_x_CONFLITOS
SET COD_CONFLITO = 2
WHERE COD_CONFLITO = 1 AND COD_GRUPO = 2;
--------------------------------------------------		<TESTES/>	 	------------------------------------------------------------------









--G


--------------------------------------------------		<TESTES>	 	------------------------------------------------------------------
INSERT INTO CONFLITO (NOME, TIPO, NUM_FERIDOS, NUM_MORTOS) VALUES ('conflito dos paises', 'territorial', 3000, 0);
INSERT INTO CONFLITO (NOME, TIPO, NUM_FERIDOS, NUM_MORTOS) VALUES ('conflito das nacoes', 'territorial', 3000, 0);
INSERT INTO PAIS (COD_CONFLITO, PAIS) VALUES (3, 'brasil');
INSERT INTO PAIS (COD_CONFLITO, PAIS) VALUES (3, 'alemanha');
INSERT INTO PAIS (COD_CONFLITO, PAIS) VALUES (3, 'argentina');


UPDATE PAIS
SET COD_CONFLITO = 4
WHERE COD_CONFLITO = 3 AND PAIS = 'brasil';
--------------------------------------------------		<TESTES/>	 	------------------------------------------------------------------




--H


--------------------------------------------------		<TESTES>	 	------------------------------------------------------------------
INSERT INTO DIVISAO (COD_GRUPO, NUM_BAIXAS_DIVISAO, HOMENS) VALUES(2, 1000, 20000);
--------------------------------------------------		<TESTES/>	 	------------------------------------------------------------------






--------------------------------------------------		<TESTES>	 	------------------------------------------------------------------
INSERT INTO GRUPO_ARMADO (NOME_GRUPO, NUM_BAIXAS_GRUPO) VALUES('DOIDOS', 300);
INSERT INTO GRUPO_ARMADO (NOME_GRUPO, NUM_BAIXAS_GRUPO) VALUES('maeBraba', 2030);

INSERT INTO GRUPOS_x_CONFLITOS (COD_CONFLITO, COD_GRUPO) VALUES(3,1);
INSERT INTO GRUPOS_x_CONFLITOS (COD_CONFLITO, COD_GRUPO) VALUES(5,5);
INSERT INTO GRUPOS_x_CONFLITOS (COD_CONFLITO, COD_GRUPO) VALUES(5,6);
INSERT INTO GRUPOS_x_CONFLITOS (COD_CONFLITO, COD_GRUPO) VALUES(4,6);
INSERT INTO GRUPOS_x_CONFLITOS (COD_CONFLITO, COD_GRUPO) VALUES(6,2);
--------------------------------------------------		<TESTES/>	 	------------------------------------------------------------------






