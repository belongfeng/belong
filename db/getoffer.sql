/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : getoffer

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 13/12/2019 18:47:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for t_logger
-- ----------------------------
DROP TABLE IF EXISTS `t_logger`;
CREATE TABLE `t_logger`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `unit_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `tag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_tx_exception
-- ----------------------------
DROP TABLE IF EXISTS `t_tx_exception`;
CREATE TABLE `t_tx_exception`  (
  `id` bigint(20) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `ex_state` smallint(6) NOT NULL,
  `group_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mod_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `registrar` smallint(6) NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `transaction_state` int(11) NULL DEFAULT NULL,
  `unit_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for wx_user_casus
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_casus`;
CREATE TABLE `wx_user_casus`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主键 主键',
  `wx_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信用户主键',
  `apply_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请主键',
  `introduced` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '案列介绍',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人 创建人',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人 更新人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间 创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间 更新时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '逻辑删除 逻辑删除 1:正常;0:删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁 乐观锁',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注 备注'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '案列' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_user_casus
-- ----------------------------
INSERT INTO `wx_user_casus` VALUES ('1', '1', '1', 's杀杀杀', NULL, NULL, '2019-12-13 10:00:43', '2019-12-13 10:00:45', '1', 0, '水水水水');

-- ----------------------------
-- Table structure for wx_user_info
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_info`;
CREATE TABLE `wx_user_info`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键 主键',
  `app_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'appid 微信平台id',
  `open_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'openid 对应平台唯一用户id',
  `union_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'union_id 跨平台统一id',
  `nick_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信名称 微信名称',
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实名称 真实名称',
  `avatar_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信头像地址 微信头像地址',
  `addre` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '位置 位置信息，json存储',
  `integral` int(10) NULL DEFAULT NULL COMMENT '积分 积分信息',
  `sex` int(10) NULL DEFAULT NULL COMMENT '性别 0女，1男',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号 手机号',
  `country` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国家 国家',
  `province` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省份 省份',
  `city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市 城市',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次登录时间 最近一次登录时间',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否可用 是否可用 是否可用1：可用0：冻结',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人 创建人',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人 更新人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间 创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间 更新时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '逻辑删除 逻辑删除 0:正常;1:删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁 乐观锁',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注 备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信用户信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of wx_user_info
-- ----------------------------
INSERT INTO `wx_user_info` VALUES ('1', '1', '456', '1', '1', '1', '1', '1', 1, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2019-12-03 14:19:16', NULL, '1', 0, NULL);
INSERT INTO `wx_user_info` VALUES ('2', '1', '2', '1', '1', '1', '1', NULL, 1, 1, '1', '1', '1', '1', '2019-12-03 11:38:55', 1, '1', '1', '2019-12-03 11:39:14', '2019-12-03 11:39:16', '1', 0, NULL);
INSERT INTO `wx_user_info` VALUES ('76f6941cf1c12c58aeb42c1cbc622a79', NULL, '3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2019-12-03 14:19:19', NULL, '0', 0, NULL);
INSERT INTO `wx_user_info` VALUES ('da74194ebb5ee92c7328a9edf446fd65', NULL, '4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2019-12-03 14:19:21', NULL, '0', 0, NULL);
INSERT INTO `wx_user_info` VALUES ('da74194ebb5ee92c7328a9edf446fd66', NULL, '5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2019-12-03 14:19:24', NULL, '0', 0, NULL);
INSERT INTO `wx_user_info` VALUES ('da74194ebb5ee92c7328a9edf446fd67', NULL, '6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2019-12-03 14:19:26', NULL, '0', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
