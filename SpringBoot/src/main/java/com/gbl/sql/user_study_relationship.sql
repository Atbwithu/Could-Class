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

 Date: 29/05/2024 20:05:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_study_relationship
-- ----------------------------
DROP TABLE IF EXISTS `user_study_relationship`;
CREATE TABLE `user_study_relationship`  (
  `uid` int NULL DEFAULT NULL COMMENT '用户id',
  `sid` int NULL DEFAULT NULL COMMENT '学习记录'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_study_relationship
-- ----------------------------
INSERT INTO `user_study_relationship` VALUES (1, 1);
INSERT INTO `user_study_relationship` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
