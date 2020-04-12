/*
Source Server         : MYSQL
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : spring_cloud_nacos

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-03-18 17:52:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `system_role`
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id`          varchar(32) NOT NULL,
  `create_by`   varchar(255) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `revision`    int(11) DEFAULT NULL,
  `update_by`   varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `role_code`   varchar(255) DEFAULT NULL,
  `role_name`   varchar(255) DEFAULT NULL,
  `summary`     varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role
-- ----------------------------

-- ----------------------------
-- Table structure for `system_user`
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id`          varchar(32) NOT NULL,
  `create_by`   varchar(255) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `revision`    int(11) DEFAULT NULL,
  `update_by`   varchar(255) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `email`       varchar(255) DEFAULT NULL,
  `nick_name`   varchar(255) DEFAULT NULL,
  `pass_word`   varchar(16) DEFAULT NULL,
  `phone`       varchar(255) DEFAULT NULL,
  `user_name`   varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------

-- ----------------------------
-- Table structure for `system_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKnp61n3syn415rmbwvhnw87u3a` (`role_id`),
  CONSTRAINT `FKkc6ik04bm9v9kldgbt3kkgfac` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`id`),
  CONSTRAINT `FKnp61n3syn415rmbwvhnw87u3a` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
