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

 Date: 29/05/2024 20:05:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名字',
  `parent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父类别',
  `sort` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Java', '0', '1', '2024-05-09 15:55:40');
INSERT INTO `category` VALUES (2, 'Python', '0', '2', '2024-05-09 15:56:13');
INSERT INTO `category` VALUES (3, 'C#', '0', '3', '2024-05-09 15:56:36');
INSERT INTO `category` VALUES (4, '前端开发', '0', '4', '2024-05-09 16:35:49');
INSERT INTO `category` VALUES (5, '移动开发', '0', '5', '2024-05-09 16:36:03');
INSERT INTO `category` VALUES (6, '游戏', '0', '6', '2024-05-09 16:36:15');
INSERT INTO `category` VALUES (7, 'C语言', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
