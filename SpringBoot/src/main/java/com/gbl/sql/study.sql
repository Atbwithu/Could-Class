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

 Date: 29/05/2024 20:05:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for study
-- ----------------------------
DROP TABLE IF EXISTS `study`;
CREATE TABLE `study`  (
  `id` int NOT NULL DEFAULT 0,
  `learn_num` int NULL DEFAULT 0 COMMENT '学习次数',
  `collect_num` int NULL DEFAULT 0 COMMENT '收藏课程数',
  `learn_time` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '学习时长',
  `question_num` int NULL DEFAULT 0 COMMENT '提问次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of study
-- ----------------------------
INSERT INTO `study` VALUES (1, 16, 0, 0.01, 3);
INSERT INTO `study` VALUES (2, 4, 0, 0.01, 0);

SET FOREIGN_KEY_CHECKS = 1;
