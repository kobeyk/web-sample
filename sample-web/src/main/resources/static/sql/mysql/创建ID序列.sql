/*创建sequence表*/

DROP TABLE IF EXISTS sequence; 
CREATE TABLE sequence ( 
     name VARCHAR(30) NOT NULL,                   
     current_value INT NOT NULL,  
     increment INT NOT NULL DEFAULT 1, 
     PRIMARY KEY (name) 
) ENGINE=InnoDB; 


/*创建--取当前值的函数*/
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

/*创建--取下一个值的函数*/
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

/*创建--更新当前值的函数*/
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

/*初始化序列*/

INSERT INTO sequence VALUES ('yk_object_id', 0, 1);   #插入一条数据，添加一个sequence名称和初始值，以及自增幅度
SELECT SETVAL('yk_object_id', 10000);                    #设置指定sequence的初始值
SELECT CURRVAL('yk_object_id');                       #查询指定sequence的当前值
SELECT NEXTVAL('yk_object_id');                       #查询指定sequence的下一个值