/*����sequence��*/

DROP TABLE IF EXISTS sequence; 
CREATE TABLE sequence ( 
     name VARCHAR(30) NOT NULL,                   
     current_value INT NOT NULL,  
     increment INT NOT NULL DEFAULT 1, 
     PRIMARY KEY (name) 
) ENGINE=InnoDB; 


/*����--ȡ��ǰֵ�ĺ���*/
DROP FUNCTION IF EXISTS currval; 
DELIMITER $ 
CREATE FUNCTION currval (seq_name VARCHAR(30))            
     RETURNS INTEGER
     LANGUAGE SQL 
     DETERMINISTIC 
     CONTAINS SQL 
     SQL SECURITY DEFINER 
     COMMENT ''
BEGIN
     DECLARE value INTEGER; 
     SET value = 0; 
     SELECT current_value INTO value 
          FROM sequence
          WHERE name = seq_name; 
     RETURN value; 
END
$ 
DELIMITER ;

/*����--ȡ��һ��ֵ�ĺ���*/
DROP FUNCTION IF EXISTS nextval; 
DELIMITER $ 
CREATE FUNCTION nextval (seq_name VARCHAR(50)) 
     RETURNS INTEGER
     LANGUAGE SQL 
     DETERMINISTIC 
     CONTAINS SQL 
     SQL SECURITY DEFINER 
     COMMENT ''
BEGIN
     UPDATE sequence
          SET current_value = current_value + increment 
          WHERE name = seq_name; 
     RETURN currval(seq_name); 
END
$ 
DELIMITER ;

/*����--���µ�ǰֵ�ĺ���*/
DROP FUNCTION IF EXISTS setval; 
DELIMITER $ 
CREATE FUNCTION setval (seq_name VARCHAR(50), value INTEGER) 
     RETURNS INTEGER
     LANGUAGE SQL 
     DETERMINISTIC 
     CONTAINS SQL 
     SQL SECURITY DEFINER 
     COMMENT ''
BEGIN
     UPDATE sequence
          SET current_value = value 
          WHERE name = seq_name; 
     RETURN currval(seq_name); 
END
$ 
DELIMITER ; 

/*��ʼ������*/

INSERT INTO sequence VALUES ('yk_object_id', 0, 1);   #����һ�����ݣ����һ��sequence���ƺͳ�ʼֵ���Լ���������
SELECT SETVAL('yk_object_id', 10000);                    #����ָ��sequence�ĳ�ʼֵ
SELECT CURRVAL('yk_object_id');                       #��ѯָ��sequence�ĵ�ǰֵ
SELECT NEXTVAL('yk_object_id');                       #��ѯָ��sequence����һ��ֵ