CREATE DATABASE CONFLITOS_BELICOS;
USE CONFLITOS_BELICOS;

CREATE TABLE GRUPO_ARMADO(
    COD_GRUPO INT NOT NULL,
    NOME_GRUPO CHAR(50),
    NUM_BAIXAS_GRUPO INT,
    PRIMARY KEY(COD_GRUPO)
);

CREATE TABLE LIDER_POLITICO(
    NOME_LIDER CHAR(50) NOT NULL,
    COD_GRUPO INT NOT NULL,
    APOIOS CHAR(50),
    PRIMARY KEY(NOME_LIDER, COD_GRUPO),
    FOREIGN KEY(COD_GRUPO) REFERENCES GRUPO_ARMADO (COD_GRUPO) ON UPDATE CASCADE
);

CREATE SEQUENCE SEQ_NUM_DIV
INCREMENT BY 1
START WITH 1
OWNED BY DIVISAO.NUM_DIVISAO;

CREATE TABLE DIVISAO(
    NUM_DIVISAO SERIAL NOT NULL,
    COD_GRUPO INT NOT NULL, 
    NUM_BAIXAS_DIVISAO INT,
    HOMENS INT,
    BARCOS INT,
    AVIOES INT,
    TANQUES INT,
    PRIMARY KEY(NUM_DIVISAO, COD_GRUPO),
    FOREIGN KEY(COD_GRUPO) REFERENCES GRUPO_ARMADO (COD_GRUPO)
);

CREATE TABLE ATT_NUM_BAIXAS(
    ATT_B INT DEFAULT 0,
    NUM_DIVISAO INT REFERENCES DIVISAO(NUM_DIVISAO),
    COD_GRUPO INT REFERENCES GRUPO_ARMADO(COD_GRUPO)
);

CREATE TABLE CHEFE_MILITAR(
    COD_CHEFE INT NOT NULL,
    FAIXA CHAR(50),
    LP_NOME_LIDER CHAR(50) NOT NULL,
    LP_COD_GRUPO INT NOT NULL,
    PRIMARY KEY(COD_CHEFE),
    FOREIGN KEY(LP_NOME_LIDER, LP_COD_GRUPO) REFERENCES LIDER_POLITICO (NOME_LIDER, COD_GRUPO) ON UPDATE CASCADE,
    
    D_NRO_DIVISAO INT NOT NULL,
    D_COD_GRUPO INT NOT NULL,
    FOREIGN KEY(D_NRO_DIVISAO, D_COD_GRUPO) REFERENCES DIVISAO (NUM_DIVISAO, COD_GRUPO) ON UPDATE CASCADE

    
);


CREATE TABLE ORGANIZACAO_M(
    COD_ORGANIZACAO INT NOT NULL,
    NOME_ORG CHAR(50),
    TIPO_ORG CHAR(50),
    TIPO_AJUDA CHAR(50),
    NUM_PESSOAS INT,
    DEPENDE_ID INT UNIQUE,
    PRIMARY KEY (COD_ORGANIZACAO),
    FOREIGN KEY(DEPENDE_ID) REFERENCES ORGANIZACAO_M (COD_ORGANIZACAO) ON UPDATE CASCADE ON DELETE CASCADE 
    -- Nao deveriam todos tem esse "ON UPDATE CASCADE ON DELETE CASCADE"?
);

CREATE TABLE TIPO_ARMA(
    NOME_ARMA CHAR(50) NOT NULL,
    INDICADOR INT,
    PRIMARY KEY(NOME_ARMA)
);

CREATE TABLE FORNECE(
    COD_GRUPO INT NOT NULL,
    NOME_ARMA CHAR(50) NOT NULL,
    NOME_TRAFICANTE CHAR(50) NOT NULL,
    NUM_ARMAS INT,
    PRIMARY KEY (NOME_TRAFICANTE, NOME_ARMA, COD_GRUPO),
    FOREIGN KEY (NOME_ARMA) REFERENCES TIPO_ARMA (NOME_ARMA) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (COD_GRUPO) REFERENCES GRUPO_ARMADO (COD_GRUPO) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE PODE_FORNECER(
    QUANTIDADE INT,
    NOME_ARMA CHAR(50) NOT NULL,
    NOME_TRAFICANTE CHAR(50) NOT NULL,
    PRIMARY KEY (NOME_TRAFICANTE, NOME_ARMA),
    FOREIGN KEY (NOME_ARMA) REFERENCES TIPO_ARMA (NOME_ARMA) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE CONFLITO(
    NOME CHAR(50),
    COD_CONFLITO INT NOT NULL CONSTRAINT COD_CONF_UNIQ UNIQUE,
    TIPO CHAR(50) CHECK (TIPO IN ('territorial', 'religioso', 'racial', 'economico')),
    NUM_FERIDOS INT,
    NUM_MORTOS INT,
    PRIMARY KEY(COD_CONFLITO)
	
);

CREATE TABLE PAIS(
    COD_CONFLITO INT NOT NULL,
    PAIS CHAR(50) NOT NULL,
    PRIMARY KEY(COD_CONFLITO, PAIS),
    FOREIGN KEY (COD_CONFLITO) REFERENCES CONFLITO(COD_CONFLITO)
);

CREATE TABLE DIALOGA(
    COD_GRUPO INT NOT NULL,
    NOME_LIDER CHAR(50) NOT NULL,
    COD_ORGANIZACAO INT NOT NULL,
    PRIMARY KEY (COD_ORGANIZACAO, COD_GRUPO, NOME_LIDER),
    FOREIGN KEY(COD_ORGANIZACAO) REFERENCES ORGANIZACAO_M (COD_ORGANIZACAO) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(NOME_LIDER, COD_GRUPO) REFERENCES LIDER_POLITICO (NOME_LIDER, COD_GRUPO) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE GRUPOS_x_CONFLITOS(
    COD_CONFLITO INT NOT NULL,
    COD_GRUPO INT NOT NULL,
    PRIMARY KEY (COD_CONFLITO, COD_GRUPO),
    FOREIGN KEY (COD_CONFLITO) REFERENCES CONFLITO(COD_CONFLITO) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (COD_GRUPO) REFERENCES GRUPO_ARMADO(COD_GRUPO) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE DE_GRUPO(
    DE_GRUPO DATE NOT NULL,
    COD_GRUPO INT NOT NULL,
    COD_CONFLITO INT NOT NULL,
    PRIMARY KEY (DE_GRUPO, COD_GRUPO, COD_CONFLITO),
    FOREIGN KEY(COD_GRUPO) REFERENCES GRUPO_ARMADO (COD_GRUPO),
    FOREIGN KEY(COD_CONFLITO) REFERENCES CONFLITO (COD_CONFLITO)
);

CREATE TABLE DS_GRUPO(
    DS_GRUPO DATE NOT NULL,
    COD_GRUPO INT NOT NULL,
    COD_CONFLITO INT NOT NULL,
    PRIMARY KEY (DS_GRUPO, COD_GRUPO, COD_CONFLITO),
    FOREIGN KEY(COD_GRUPO) REFERENCES GRUPO_ARMADO (COD_GRUPO),
    FOREIGN KEY(COD_CONFLITO) REFERENCES CONFLITO (COD_CONFLITO)
);

CREATE TABLE DE_MEDIA(
    COD_ORGANIZACAO INT NOT NULL,
    COD_CONFLITO INT NOT NULL,
    DE_MEDIA DATE NOT NULL,
    PRIMARY KEY (COD_ORGANIZACAO, COD_CONFLITO, DE_MEDIA),
    FOREIGN KEY (COD_ORGANIZACAO) REFERENCES ORGANIZACAO_M(COD_ORGANIZACAO),
    FOREIGN KEY (COD_CONFLITO) REFERENCES CONFLITO(COD_CONFLITO)
);

CREATE TABLE DS_MEDIA(
    COD_ORGANIZACAO INT NOT NULL,
    COD_CONFLITO INT NOT NULL,
    DS_MEDIA DATE NOT NULL,
    PRIMARY KEY (COD_ORGANIZACAO, COD_CONFLITO, DS_MEDIA),
    FOREIGN KEY (COD_ORGANIZACAO) REFERENCES ORGANIZACAO_M(COD_ORGANIZACAO),
    FOREIGN KEY (COD_CONFLITO) REFERENCES CONFLITO(COD_CONFLITO)
);


CREATE TABLE CONFLITO_TERRITORIAL(
    COD_CONFLITO INT NOT NULL,
    REGIAO CHAR(50) NOT NULL,
    TIPO CHAR(20) CHECK (TIPO = 'territorial'),
    PRIMARY KEY(COD_CONFLITO, REGIAO),
    FOREIGN KEY (COD_CONFLITO) REFERENCES CONFLITO(COD_CONFLITO) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE CONFLITO_RELIGIOSO(
    COD_CONFLITO INT NOT NULL,
    RELIGIAO CHAR(50) NOT NULL,
    TIPO CHAR(20) CHECK (TIPO = 'religioso'),
    PRIMARY KEY(COD_CONFLITO, RELIGIAO),
    FOREIGN KEY(COD_CONFLITO) REFERENCES CONFLITO (COD_CONFLITO) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE CONFLITO_ECONOMICO(
    COD_CONFLITO INT NOT NULL,
    MAT_PRIMA CHAR(50) NOT NULL,
    TIPO CHAR(20) CHECK (TIPO = 'economico'),
    PRIMARY KEY(COD_CONFLITO, MAT_PRIMA),
    FOREIGN KEY(COD_CONFLITO) REFERENCES CONFLITO (COD_CONFLITO) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE CONFLITO_RACIAL(
    COD_CONFLITO INT NOT NULL,
    ETNIA CHAR(50) NOT NULL,
    TIPO CHAR(20) CHECK (TIPO = 'racial'),
    PRIMARY KEY(COD_CONFLITO, ETNIA),
    FOREIGN KEY(COD_CONFLITO) REFERENCES CONFLITO (COD_CONFLITO) ON UPDATE CASCADE ON DELETE CASCADE
);
