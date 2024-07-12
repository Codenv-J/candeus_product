/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : candeus-product

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 12/07/2024 22:38:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `admin_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员Id',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像地址',
  `emergency_contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '紧急联系人',
  `admin_level` int NULL DEFAULT NULL COMMENT '管理员等级',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES ('admin', '123456', '_', '_', 1, '2024-07-11 15:45:45', '2024-07-12 15:45:51', 0);

-- ----------------------------
-- Table structure for custom_text_config
-- ----------------------------
DROP TABLE IF EXISTS `custom_text_config`;
CREATE TABLE `custom_text_config`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `field_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `custom_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of custom_text_config
-- ----------------------------
INSERT INTO `custom_text_config` VALUES (1, 'header', '您本次查询产品为官方正品，感谢您的支持!', '2024-07-12 18:29:01', NULL);
INSERT INTO `custom_text_config` VALUES (2, 'end', NULL, '2024-07-12 18:29:01', NULL);

-- ----------------------------
-- Table structure for field_display_config
-- ----------------------------
DROP TABLE IF EXISTS `field_display_config`;
CREATE TABLE `field_display_config`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `field_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_display` tinyint(1) NOT NULL DEFAULT 1,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of field_display_config
-- ----------------------------
INSERT INTO `field_display_config` VALUES (1, 'brand', 0, '2024-07-12 18:22:25', NULL);
INSERT INTO `field_display_config` VALUES (2, 'product_type', 1, '2024-07-12 18:22:25', NULL);
INSERT INTO `field_display_config` VALUES (3, 'product_model', 1, '2024-07-12 18:22:25', NULL);
INSERT INTO `field_display_config` VALUES (4, 'product_serial', 1, '2024-07-12 18:22:25', NULL);
INSERT INTO `field_display_config` VALUES (5, 'production_cycle', 1, '2024-07-12 18:22:25', NULL);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_serial` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `production_cycle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '创极宙', '显卡', 'GeForce RTX4090 AI 24G', 'HT00C0012351200001F44D', '23年51周', 0, '2024-07-12 22:23:28', '2024-07-12 22:23:28');
INSERT INTO `product` VALUES (2, '测试品牌001​', '测试产品类型001​', '测试产品型号001', '测试产品序列号001', '测试出厂周期001', 1, '2024-07-12 22:11:31', '2024-07-12 22:13:10');
INSERT INTO `product` VALUES (3, '焕宙0', '键盘', 'Txx', 'xxxxxxxxxxxxxxxxx0', '23年51周', 0, '2024-07-12 22:23:28', '2024-07-12 22:23:28');
INSERT INTO `product` VALUES (4, '焕宙1', '键盘', 'Txx', 'xxxxxxxxxxxxxxxxx1', '23年51周', 0, '2024-07-12 22:23:28', '2024-07-12 22:23:28');
INSERT INTO `product` VALUES (5, '焕宙2', '键盘', 'Txx', 'xxxxxxxxxxxxxxxxx2', '23年51周', 0, '2024-07-12 22:23:28', '2024-07-12 22:23:28');

SET FOREIGN_KEY_CHECKS = 1;
