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

 Date: 29/05/2024 20:05:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for study_course
-- ----------------------------
DROP TABLE IF EXISTS `study_course`;
CREATE TABLE `study_course`  (
  `id` int NOT NULL,
  `ask_num` int NULL DEFAULT NULL COMMENT '课程提问次数',
  `finish_time` varbinary(255) NULL DEFAULT NULL COMMENT '章节完成率',
  `total_time` varbinary(255) NULL DEFAULT NULL COMMENT '总完成率',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of study_course
-- ----------------------------
INSERT INTO `study_course` VALUES (1, NULL, NULL, NULL);
INSERT INTO `study_course` VALUES (2, NULL, NULL, NULL);

-- ----------------------------
-- Triggers structure for table study_course
-- ----------------------------
DROP TRIGGER IF EXISTS `update_question_num`;
delimiter ;;
CREATE TRIGGER `update_question_num` AFTER UPDATE ON `study_course` FOR EACH ROW BEGIN
    -- 在 study 表中更新 question_num 列的值
    UPDATE study
    SET question_num = question_num + 1
    WHERE id = OLD.id;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
