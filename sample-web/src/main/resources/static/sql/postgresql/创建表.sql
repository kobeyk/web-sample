/*
Navicat PGSQL Data Transfer

Source Server         : localhost【本机】_5432
Source Server Version : 100200
Source Host           : localhost:5432
Source Database       : my_db_test
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 100200
File Encoding         : 65001

Date: 2019-04-29 16:34:48
*/


-- ----------------------------
-- Table structure for yk_catalog
-- ----------------------------
DROP TABLE IF EXISTS "public"."yk_catalog";
CREATE TABLE "public"."yk_catalog" (
"id" int8 NOT NULL,
"name" varchar(255) COLLATE "default",
"parentid" int4,
"rootid" int4,
"ctime" int8,
"mtime" int8
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."yk_catalog"."id" IS '主键';
COMMENT ON COLUMN "public"."yk_catalog"."name" IS '目录名称';
COMMENT ON COLUMN "public"."yk_catalog"."parentid" IS '目录父id';
COMMENT ON COLUMN "public"."yk_catalog"."rootid" IS '目录树根id';
COMMENT ON COLUMN "public"."yk_catalog"."ctime" IS '创建时间';
COMMENT ON COLUMN "public"."yk_catalog"."mtime" IS '修改时间';

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table yk_catalog
-- ----------------------------
ALTER TABLE "public"."yk_catalog" ADD PRIMARY KEY ("id");


/*
Navicat PGSQL Data Transfer

Source Server         : localhost【本机】_5432
Source Server Version : 100200
Source Host           : localhost:5432
Source Database       : my_db_test
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 100200
File Encoding         : 65001

Date: 2019-04-29 16:34:55
*/


-- ----------------------------
-- Table structure for yk_catalog_node
-- ----------------------------
DROP TABLE IF EXISTS "public"."yk_catalog_node";
CREATE TABLE "public"."yk_catalog_node" (
"id" int8 NOT NULL,
"name" varchar(255) COLLATE "default",
"oid" int8,
"catalogid" int4,
"ctime" int8,
"mtime" int8
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."yk_catalog_node"."id" IS '主键';
COMMENT ON COLUMN "public"."yk_catalog_node"."name" IS '节点对象的名称';
COMMENT ON COLUMN "public"."yk_catalog_node"."oid" IS '节点对象的id';
COMMENT ON COLUMN "public"."yk_catalog_node"."catalogid" IS '对应的目录id';
COMMENT ON COLUMN "public"."yk_catalog_node"."ctime" IS '创建时间';
COMMENT ON COLUMN "public"."yk_catalog_node"."mtime" IS '修改时间';

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table yk_catalog_node
-- ----------------------------
ALTER TABLE "public"."yk_catalog_node" ADD PRIMARY KEY ("id");


/*
Navicat PGSQL Data Transfer

Source Server         : localhost【本机】_5432
Source Server Version : 100200
Source Host           : localhost:5432
Source Database       : my_db_test
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 100200
File Encoding         : 65001

Date: 2019-04-29 16:35:01
*/


-- ----------------------------
-- Table structure for yk_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."yk_user";
CREATE TABLE "public"."yk_user" (
"id" int8 NOT NULL,
"name" varchar(255) COLLATE "default",
"avatar" varchar(255) COLLATE "default",
"telphone" varchar(255) COLLATE "default",
"ctime" int8,
"role" int4
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."yk_user"."name" IS '用户名称';
COMMENT ON COLUMN "public"."yk_user"."avatar" IS '用户头像地址';
COMMENT ON COLUMN "public"."yk_user"."telphone" IS '用户联系方式';
COMMENT ON COLUMN "public"."yk_user"."ctime" IS '创建时间';
COMMENT ON COLUMN "public"."yk_user"."role" IS '用户角色';

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table yk_user
-- ----------------------------
ALTER TABLE "public"."yk_user" ADD PRIMARY KEY ("id");
