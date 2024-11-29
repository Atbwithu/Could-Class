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

 Date: 29/05/2024 20:05:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for study_chapter_relationship
-- ----------------------------
DROP TABLE IF EXISTS `study_chapter_relationship`;
CREATE TABLE `study_chapter_relationship`  (
  `cid` int NOT NULL COMMENT '章节id',
  `sid` int NOT NULL COMMENT '学习记录id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of study_chapter_relationship
-- ----------------------------
INSERT INTO `study_chapter_relationship` VALUES (1, 2);
INSERT INTO `study_chapter_relationship` VALUES (3, 1);

SET FOREIGN_KEY_CHECKS = 1;
