CREATE OR REPLACE FUNCTION data_Final(text) 
RETURNS text AS 
$BODY$ 
select
coalesce($1, '01-01-2099');
$BODY$ 
LANGUAGE 'sql' IMMUTABLE STRICT; 