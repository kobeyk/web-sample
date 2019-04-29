CREATE TABLE `yk_user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名称',
  `avatar` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户头像',
  `telphone` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '联系方式',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `role` int(11) DEFAULT NULL COMMENT '用户角色：\n0：超级管理员\n1：管理员\n2：普通用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户表';

CREATE TABLE `yk_catalog` (
  `id` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '目录名称',
  `parentid` bigint(20) DEFAULT NULL COMMENT '目录父ID',
  `rootid` bigint(20) DEFAULT NULL COMMENT '根目录ID',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `mtime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='目录';

CREATE TABLE `yk_catalog_node` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '节点/文件名称',
  `oid` bigint(20) DEFAULT NULL COMMENT '节点/文件对象的ID',
  `catalogid` bigint(20) DEFAULT NULL COMMENT '所对应的目录ID',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `mtime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='目录节点';
