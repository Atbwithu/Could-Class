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

 Date: 29/05/2024 20:05:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sub_ask_relationship
-- ----------------------------
DROP TABLE IF EXISTS `sub_ask_relationship`;
CREATE TABLE `sub_ask_relationship`  (
  `sid` int NULL DEFAULT NULL COMMENT '课程id',
  `aid` int NULL DEFAULT NULL COMMENT '提问id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sub_ask_relationship
-- ----------------------------
INSERT INTO `sub_ask_relationship` VALUES (3, 1);
INSERT INTO `sub_ask_relationship` VALUES (1, 2);

SET FOREIGN_KEY_CHECKS = 1;
