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

 Date: 29/05/2024 20:05:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `subject_time` datetime NULL DEFAULT NULL COMMENT '课程时长',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程类别',
  `teacher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '导师',
  `order` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'Java面向对象', '2024-05-09 17:24:41', NULL, NULL, NULL, NULL);
INSERT INTO `course` VALUES (2, '前端面试(吊打面试官)', '2024-05-25 12:45:22', NULL, NULL, NULL, NULL);
INSERT INTO `course` VALUES (3, 'python爬虫(包三餐)', '2024-05-25 12:45:24', NULL, NULL, NULL, NULL);
INSERT INTO `course` VALUES (4, 'go(低学历别碰)', '2024-05-04 12:45:27', NULL, NULL, NULL, NULL);
INSERT INTO `course` VALUES (5, '撒旦发射点', '2024-05-10 12:45:29', NULL, NULL, NULL, NULL);
INSERT INTO `course` VALUES (6, '士大夫但是', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
