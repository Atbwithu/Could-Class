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

 Date: 29/05/2024 20:05:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_subject_relationship
-- ----------------------------
DROP TABLE IF EXISTS `user_subject_relationship`;
CREATE TABLE `user_subject_relationship`  (
  `uid` int NULL DEFAULT NULL COMMENT '用户id',
  `cid` int NULL DEFAULT NULL COMMENT '课程id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_subject_relationship
-- ----------------------------
INSERT INTO `user_subject_relationship` VALUES (1, 2);
INSERT INTO `user_subject_relationship` VALUES (1, 1);
INSERT INTO `user_subject_relationship` VALUES (1, 3);
INSERT INTO `user_subject_relationship` VALUES (2, 1);
INSERT INTO `user_subject_relationship` VALUES (2, 3);
INSERT INTO `user_subject_relationship` VALUES (2, 5);

SET FOREIGN_KEY_CHECKS = 1;
