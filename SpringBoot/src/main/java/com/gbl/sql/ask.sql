/* Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : db_goblins

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 29/05/2024 20:04:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ask
-- ----------------------------
DROP TABLE IF EXISTS `ask`;
CREATE TABLE `ask`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生名字',
  `course` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程',
  `chapter` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '章节',
  `questions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '问题',
  `status` bit(1) NULL DEFAULT b'0' COMMENT '状态',
  `create_time` datetime NULL DEFAULT NULL COMMENT '提问的时间',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回复',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_name`(`user_name` ASC) USING BTREE,
  INDEX `idx_subject`(`course` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ask
-- ----------------------------
INSERT INTO `ask` VALUES (1, '张三', 'python', '1', 'python包三餐吗？', b'1', '2024-05-25 20:22:58', '10086', '包的');
INSERT INTO `ask` VALUES (2, '李四', 'Java', '2', '学Java包分配吗？？', b'1', '2024-05-25 22:00:35', '10010', '包的');
INSERT INTO `ask` VALUES (3, '张三', '', NULL, 'Java三大特性是什么呀？', b'1', '2024-05-26 04:48:27', 'root', '封装，多态，继承');
INSERT INTO `ask` VALUES (4, '张三', '', NULL, '张老师老奶奶钻被窝怎么操作？', b'0', '2024-05-26 04:53:55', 'root', NULL);
INSERT INTO `ask` VALUES (5, '张三', '', NULL, '张老师老奶奶怎么钻被窝', b'0', '2024-05-26 04:55:28', 'root', NULL);
INSERT INTO `ask` VALUES (6, '张三', '', NULL, '张老师老奶奶钻被窝怎么操作？', b'0', '2024-05-26 04:56:58', 'root', NULL);
INSERT INTO `ask` VALUES (7, '张三', '', NULL, '张老师老奶奶钻被窝怎么操作？', b'0', '2024-05-26 04:57:40', 'root', NULL);
INSERT INTO `ask` VALUES (8, '张三', '', NULL, '张老师老奶奶钻被窝怎么操作？', b'0', '2024-05-26 05:03:45', 'root', NULL);
INSERT INTO `ask` VALUES (9, '张三', '', NULL, '张老师老奶奶张老师老奶奶钻被窝怎么操作？钻被窝怎么操作？', b'0', '2024-05-26 05:04:00', 'root', NULL);

SET FOREIGN_KEY_CHECKS = 1;
