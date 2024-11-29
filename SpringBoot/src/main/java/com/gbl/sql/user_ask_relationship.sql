/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : db_goblins

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 29/05/2024 20:05:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_ask_relationship
-- ----------------------------
DROP TABLE IF EXISTS `user_ask_relationship`;
CREATE TABLE `user_ask_relationship`  (
  `qid` int NULL DEFAULT NULL COMMENT '问题id',
  `uid` int NULL DEFAULT NULL COMMENT '用户id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_ask_relationship
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
