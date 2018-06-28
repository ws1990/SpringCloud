SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_resource
-- ----------------------------
DROP TABLE IF EXISTS `auth_resource`;
CREATE TABLE `auth_resource` (
  `id` varchar(36) NOT NULL,
  `type` int(8) NOT NULL COMMENT '类型。1 菜单；2 按钮；',
  `code` varchar(64) DEFAULT NULL COMMENT '编码。前后端约定的唯一标识符',
  `name` varchar(100) NOT NULL COMMENT '资源展示名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `uri` varchar(200) DEFAULT NULL COMMENT '前端访问路径',
  `api` varchar(1024) DEFAULT NULL COMMENT '后端接口uri，多个以逗号隔开',
  `icon` varchar(200) DEFAULT NULL COMMENT '图标的url',
  `depth` int(8) NOT NULL DEFAULT '0' COMMENT '层级，从0开始',
  `sequence` int(8) NOT NULL COMMENT '排序，从0开始',
  `parent_id` varchar(36) DEFAULT NULL COMMENT '父级ID',
  `ids` varchar(512) NOT NULL COMMENT '所有的父级ID加上自己的ID，由逗号隔开',
  `is_delete` tinyint(1) NOT NULL COMMENT '是否被删除。1 已删除；0 未删除；',
  `is_lock` tinyint(1) DEFAULT NULL COMMENT '是否锁定',
  `creator_id` varchar(36) NOT NULL COMMENT '创建者ID',
  `update_id` varchar(36) DEFAULT NULL COMMENT '更新者ID',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code_unique` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` varchar(36) NOT NULL,
  `code` varchar(100) NOT NULL COMMENT '角色的编码，唯一区分',
  `name` varchar(100) NOT NULL COMMENT '角色名',
  `description` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `expire_time` datetime(6) NOT NULL COMMENT '过期时间',
  `is_lock` tinyint(1) DEFAULT NULL COMMENT '是否锁定。0 未锁定；1 锁定',
  `is_delete` tinyint(1) NOT NULL COMMENT '是否被删除。1 已删除；0 未删除；',
  `creator_id` varchar(36) NOT NULL COMMENT '创建者ID',
  `update_id` varchar(36) DEFAULT NULL COMMENT '更新者ID',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code_unique` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for auth_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_resource`;
CREATE TABLE `auth_role_resource` (
  `id` varchar(36) NOT NULL,
  `role_id` varchar(36) NOT NULL,
  `resource_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for auth_role_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_user`;
CREATE TABLE `auth_role_user` (
  `id` varchar(36) NOT NULL,
  `role_id` varchar(36) NOT NULL,
  `user_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` varchar(36) NOT NULL,
  `account` varchar(36) NOT NULL COMMENT '帐号',
  `salt` varchar(255) NOT NULL COMMENT '盐',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `expire_time` datetime(6) NOT NULL COMMENT '过期时间',
  `device_id` varchar(255) DEFAULT NULL COMMENT '绑定的设备ID',
  `is_lock` tinyint(1) DEFAULT '0' COMMENT '是否锁定。0 未锁定；1 锁定',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被删除。1 已删除；0 未删除；',
  `creator_id` varchar(36) NOT NULL COMMENT '创建者ID',
  `update_id` varchar(36) DEFAULT NULL COMMENT '更新者ID',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
