/* Create Sequences */

CREATE SEQUENCE public.yk_object_id
  INCREMENT 1
  MINVALUE 1  --��С��1
  MAXVALUE 9999999999
  START 1    --��1��ʼ
  CACHE 1;
;

COMMENT ON SEQUENCE yk_object_id
	IS '�û�ID��������'
;


 --SELECT nextval('yk_object_id') alter sequence yk_object_id restart with 10000