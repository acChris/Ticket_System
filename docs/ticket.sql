/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : ticket

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 07/10/2021 12:54:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin_user`;
CREATE TABLE `tb_admin_user`  (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `login_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆名称',
  `login_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆密码',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员显示昵称',
  `locked` tinyint(4) NULL DEFAULT 0 COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  PRIMARY KEY (`admin_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_admin_user
-- ----------------------------
INSERT INTO `tb_admin_user` VALUES (1, 'root', '63a9f0ea7bb98050796b649e85481845', 'root', 0);
INSERT INTO `tb_admin_user` VALUES (2, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', 0);
INSERT INTO `tb_admin_user` VALUES (3, 'weng', '1895e0e72493a2bd17f2559364bbf518', 'weng', 0);
INSERT INTO `tb_admin_user` VALUES (5, 'test', '098f6bcd4621d373cade4e832627b4f6', 'test', 0);

-- ----------------------------
-- Table structure for tb_rent_ticket
-- ----------------------------
DROP TABLE IF EXISTS `tb_rent_ticket`;
CREATE TABLE `tb_rent_ticket`  (
  `id` int(22) NOT NULL AUTO_INCREMENT COMMENT '余票ID',
  `rent_ticket_from` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '余票起点',
  `rent_ticket_to` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '余票终点',
  `start_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '出发时间',
  `end_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '结束时间',
  `rent_ticket_count` int(22) NOT NULL COMMENT '余票数',
  `rent_ticket_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0-有余量 1-售空',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '0-删除 1-未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_rent_ticket
-- ----------------------------
INSERT INTO `tb_rent_ticket` VALUES (1, 'testFrom', 'testTo', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 100, 0, 1);
INSERT INTO `tb_rent_ticket` VALUES (2, 'testFrom', 'testTo', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 100, 0, 1);
INSERT INTO `tb_rent_ticket` VALUES (3, 'testFrom', 'testTo', '2021-10-03 13:25:01', '2021-10-03 21:25:00', 100, 0, 1);
INSERT INTO `tb_rent_ticket` VALUES (4, 'testFrom', 'testTo', '2021-10-06 17:06:00', '2021-10-06 17:06:00', 222, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (5, 'test1', 'test2', '2021-10-05 05:02:07', '2021-10-05 05:02:07', 1, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (6, 'test1', 'test2', '2021-10-05 07:41:56', '2021-10-05 07:41:56', 1, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (7, '广州', '广州', '2021-10-05 07:53:00', '2021-10-05 07:53:00', 2222, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (8, 'test', 'test', '2021-10-05 08:03:00', '2021-10-05 08:03:00', 11111, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (9, '广州', '广州', '2021-10-05 09:28:00', '2021-10-05 09:28:00', 2222, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (10, '广州', '广州', '2021-10-05 09:30:00', '2021-10-05 09:30:00', 2222, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (11, 'test1', 'test2', '2021-10-05 09:30:00', '2021-10-05 09:30:00', 1, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (12, '汕头', '汕头', '2021-10-05 09:30:00', '2021-10-05 09:30:00', 100, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (13, '汕头', '汕头', '2021-10-05 09:30:00', '2021-10-05 09:30:00', 100, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (14, '汕头', '汕头', '2021-10-05 09:30:00', '2021-10-05 09:30:00', 2222, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (15, '汕头站', '潮阳站', '2021-10-05 09:36:00', '2021-10-05 09:36:00', 2222, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (16, '1111', '1111', '2021-10-05 09:40:00', '2021-10-05 09:40:00', 100, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (17, '22222', '222222', '2021-10-05 09:42:00', '2021-10-05 09:42:00', 100, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (18, 'test', 'test', '2021-10-14 10:50:00', '2021-10-14 10:50:00', 1111, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (19, '1111', '1111', '2021-10-05 12:25:00', '2021-10-05 12:25:00', 1111, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (20, '111', '1111', '2021-10-05 12:25:00', '2021-10-05 12:25:00', 11, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (21, 'tets', 'stst', '2021-10-05 12:29:00', '2021-10-05 12:29:00', 111, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (23, '广州', '广州', '2021-10-05 12:25:00', '2021-10-05 12:25:00', 111, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (24, '广州', '广州', '2021-10-05 12:25:00', '2021-10-05 12:25:00', 111, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (25, '广州', '广州', '2021-10-05 12:25:00', '2021-10-05 12:25:00', 111, 0, 0);
INSERT INTO `tb_rent_ticket` VALUES (26, '杭州', '上海', '2021-10-05 12:25:00', '2021-10-05 12:25:00', 111, 0, 0);

-- ----------------------------
-- Table structure for tb_ticket
-- ----------------------------
DROP TABLE IF EXISTS `tb_ticket`;
CREATE TABLE `tb_ticket`  (
  `id` int(22) NOT NULL AUTO_INCREMENT COMMENT '车票ID',
  `ticket_from` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出发点',
  `ticket_to` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '终点',
  `ticket_payer` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '购票人',
  `ticket_count` tinyint(4) NOT NULL COMMENT '购票数',
  `start_time` datetime(0) NOT NULL COMMENT '出发时间',
  `end_time` datetime(0) NOT NULL COMMENT '到达时间',
  `buy_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '购票时间',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `ticket_status` tinyint(4) NULL DEFAULT 0 COMMENT '订单状态 0-未支付 1-订票成功',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_ticket
-- ----------------------------
INSERT INTO `tb_ticket` VALUES (4, '广州南站', '潮南站', 'hu', 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2021-10-03 12:13:38', '2021-10-05 12:27:38', 1, 0);
INSERT INTO `tb_ticket` VALUES (5, '广州东', '重庆南', 'weng', 2, '2021-10-06 20:36:03', '2021-10-06 22:36:03', '2021-10-06 20:37:31', NULL, 0, 0);
INSERT INTO `tb_ticket` VALUES (6, '杭州', '广州东', 'hu', 2, '2021-10-06 21:14:00', '2021-10-06 23:14:00', '2021-10-06 21:18:30', NULL, 0, 0);
INSERT INTO `tb_ticket` VALUES (7, '广州东', '重庆南', 'weng', 2, '2021-10-06 20:36:03', '2021-10-06 22:36:03', '2021-10-06 22:09:51', NULL, 0, 0);
INSERT INTO `tb_ticket` VALUES (8, '杭州', '广州', '广州', 1, '2021-10-05 14:57:00', '2021-10-05 14:57:00', '2021-10-06 22:10:26', NULL, 0, 0);
INSERT INTO `tb_ticket` VALUES (9, '杭州', '广州', '广州', 2, '2021-10-05 14:57:00', '2021-10-05 14:57:00', '2021-10-06 22:10:51', NULL, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
