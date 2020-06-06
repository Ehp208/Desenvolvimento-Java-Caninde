CREATE OR REPLACE FUNCTION valor_Inicial(text) 
RETURNS text AS 
$BODY$ 
select
coalesce($1, '0');
$BODY$ 
LANGUAGE 'sql' IMMUTABLE STRICT; 