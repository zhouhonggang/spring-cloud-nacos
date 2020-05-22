/*
Source Server         : 127.0.0.1
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : spring_cloud_nacos

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-05-22 17:35:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `system_role`
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` varchar(32) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `role_code` varchar(255) DEFAULT NULL,
  `role_summary` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  `revision` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('402883ae71bb001d0171bb02094b0000', '管理员', 'manager', '系统管理员', '1', '2020-04-27 17:40:05.089000', null, null, '0');
INSERT INTO `system_role` VALUES ('402883ae71bb001d0171bb02094b0001', '普通用户', 'normal', '普通权限', '1', '2020-04-28 12:09:12.000000', null, null, null);

-- ----------------------------
-- Table structure for `system_user`
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` varchar(32) NOT NULL,
  `user_name` varchar(16) DEFAULT NULL,
  `pass_word` varchar(128) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `user_photo` varchar(50) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  `revision` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', 'admin', '$2a$10$y.HgAxf7AM8IHRAcrAAPSeOPX7WhO/WxPHOch8a4Et9EeqAyaRxXa', 'Mr.zhou', '15001390311@163.com', '15001390311', '402883ae7-157bd-94017157e-a276c-0001.jpg', null, '2020-04-27 15:14:16.000000', null, '2020-04-27 15:14:16.000000', '0');
INSERT INTO `system_user` VALUES ('10', 'qitao', '$2a$10$y.HgAxf7AM8IHRAcrAAPSeOPX7WhO/WxPHOch8a4Et9EeqAyaRxXa', '其陶', 'qitao@qq.com', '13687109741', '982a9c49-1f2d-4e2a-8f44-ec3863a4d603.jpg', null, '2020-04-27 15:14:16.000000', null, '2020-04-27 15:14:16.000000', '0');
INSERT INTO `system_user` VALUES ('11', 'junrong', '$10$y.HgAxf7AM8IHRAcrAAPSeOPX7WhO/WxPHOch8a4Et9EeqAyaRxXa', '隽溶', 'sangqibo@qq.com', '13687109742', '982a9c49-1f2d-4e2a-8f44-ec3863a4d603.jpg', null, '2020-04-27 15:14:16.000000', null, '2020-04-27 15:14:16.000000', '0');
INSERT INTO `system_user` VALUES ('12', 'sangqibo', '$10$y.HgAxf7AM8IHRAcrAAPSeOPX7WhO/WxPHOch8a4Et9EeqAyaRxXa', '桑绮波', 'sangqibo@qq.com', '13687109743', '982a9c49-1f2d-4e2a-8f44-ec3863a4d603.jpg', null, '2020-04-27 15:14:16.000000', null, '2020-04-27 15:14:16.000000', '0');
INSERT INTO `system_user` VALUES ('2', 'xiaojing', '$10$y.HgAxf7AM8IHRAcrAAPSeOPX7WhO/WxPHOch8a4Et9EeqAyaRxXa', '晓晶', 'xiaojing@qq.com', '13687109744', '982a9c49-1f2d-4e2a-8f44-ec3863a4d603.jpg', null, '2020-04-27 15:14:16.000000', null, '2020-04-27 15:14:16.000000', '0');
INSERT INTO `system_user` VALUES ('3', 'danxuanjing', '$10$y.HgAxf7AM8IHRAcrAAPSeOPX7WhO/WxPHOch8a4Et9EeqAyaRxXa', '丹玄静', 'danxuanjing@qq.com', '13687109745', '982a9c49-1f2d-4e2a-8f44-ec3863a4d603.jpg', null, '2020-04-27 15:14:16.000000', null, '2020-04-27 15:14:16.000000', '0');
INSERT INTO `system_user` VALUES ('4', 'xingyu', '$10$y.HgAxf7AM8IHRAcrAAPSeOPX7WhO/WxPHOch8a4Et9EeqAyaRxXa', '幸瑜', 'xingyu@qq.com', '13687109746', '982a9c49-1f2d-4e2a-8f44-ec3863a4d603.jpg', null, '2020-04-27 15:14:16.000000', null, '2020-04-27 15:14:16.000000', '0');
INSERT INTO `system_user` VALUES ('402883ae7157bd94017157ea276c0001', 'admin1', '$2a$10$NVJAZwq6LsoAdMbwT9UxBeAA4RXMsWskkSzS05w2jkd7aIlywswtq', 'admin1', '13782798171@126.com', '13782798171', '982a9c49-1f2d-4e2a-8f44-ec3863a4d603.jpg', null, '2020-04-27 15:14:16.000000', null, '2020-04-27 15:14:16.000000', '0');
INSERT INTO `system_user` VALUES ('5', 'liangqiushishi', '$10$y.HgAxf7AM8IHRAcrAAPSeOPX7WhO/WxPHOch8a4Et9EeqAyaRxXa', '梁丘施诗', 'liangqiushishi@qq.com', '13687109747', '982a9c49-1f2d-4e2a-8f44-ec3863a4d603.jpg', null, '2020-04-27 15:14:16.000000', null, '2020-04-27 15:14:16.000000', '0');
INSERT INTO `system_user` VALUES ('6', 'jiayuerui', '$10$y.HgAxf7AM8IHRAcrAAPSeOPX7WhO/WxPHOch8a4Et9EeqAyaRxXa', '贾乐蕊', 'jiayuerui@qq.com', '13687109748', '982a9c49-1f2d-4e2a-8f44-ec3863a4d603.jpg', null, '2020-04-27 15:14:16.000000', null, '2020-04-27 15:14:16.000000', '0');
INSERT INTO `system_user` VALUES ('7', 'xiaojingyi', '$10$y.HgAxf7AM8IHRAcrAAPSeOPX7WhO/WxPHOch8a4Et9EeqAyaRxXa', '校静逸', 'xiaojingyi@qq.com', '13687109749', '982a9c49-1f2d-4e2a-8f44-ec3863a4d603.jpg', null, '2020-04-27 15:14:16.000000', null, '2020-04-27 15:14:16.000000', '0');
INSERT INTO `system_user` VALUES ('8', 'ranxiran', '$10$y.HgAxf7AM8IHRAcrAAPSeOPX7WhO/WxPHOch8a4Et9EeqAyaRxXa', '冉忻然', 'ranxiran@qq.com', '13687109740', '982a9c49-1f2d-4e2a-8f44-ec3863a4d603.jpg', null, '2020-04-27 15:14:16.000000', null, '2020-04-27 15:14:16.000000', '0');
INSERT INTO `system_user` VALUES ('9', 'zhonglimengfei', '$10$y.HgAxf7AM8IHRAcrAAPSeOPX7WhO/WxPHOch8a4Et9EeqAyaRxXa', '钟离梦菲', 'zhonglimengfei@qq.com', '13687109232', '982a9c49-1f2d-4e2a-8f44-ec3863a4d603.jpg', null, '2020-04-27 15:14:16.000000', null, '2020-04-27 15:14:16.000000', '0');

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
