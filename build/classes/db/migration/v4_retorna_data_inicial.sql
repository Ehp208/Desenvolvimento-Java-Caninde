CREATE OR REPLACE FUNCTION data_Inicial(text) 
RETURNS text AS 
$BODY$ 
select
coalesce($1, '01-01-2000');
$BODY$ 
LANGUAGE 'sql' IMMUTABLE STRICT; 