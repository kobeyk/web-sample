/* Create Sequences */

CREATE SEQUENCE public.yk_object_id
  INCREMENT 1
  MINVALUE 1  --最小是1
  MAXVALUE 9999999999
  START 1    --从1开始
  CACHE 1;
;

COMMENT ON SEQUENCE yk_object_id
	IS '用户ID自增序列'
;


 --SELECT nextval('yk_object_id') alter sequence yk_object_id restart with 10000