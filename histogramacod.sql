\set DateStyle 'ISO';
CREATE OR REPLACE FUNCTION fun_conflito_tipo_histograma (text) RETURNS text AS $$
    SELECT $1 || '*';
$$ LANGUAGE SQL STRICT;
CREATE AGGREGATE agrega_histograma
(
    BASETYPE = "ANY",
    SFUNC = fun_conflito_tipo_histograma,
    STYPE = text,
    INITCOND = ''
);
\pset null '(nulo)'
\pset border 2
\pset title 'Histograma conflito por tipo'
SELECT TIPO, COUNT(*) AS freq, agrega_histograma(*) AS histograma
FROM CONFLITO
GROUP BY TIPO
ORDER BY 2,1;