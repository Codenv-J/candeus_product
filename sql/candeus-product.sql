/*
 Navicat Premium Data Transfer

 Source Server         : Candeus产品服务测试服务器
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : 47.97.81.200:17380
 Source Schema         : candeus-product

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 22/07/2024 23:17:16
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
INSERT INTO `administrator` VALUES ('candeus001', 'Candeus.4044', '_', '_', 0, '2024-07-11 15:45:45', '2024-07-22 22:23:19', 0);
INSERT INTO `administrator` VALUES ('candeus002', 'Candeus.002', '_', '_', 0, '2024-07-22 22:24:23', '2024-07-22 23:06:37', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of custom_text_config
-- ----------------------------
INSERT INTO `custom_text_config` VALUES (1, 'header', '您本次查询产品为官方正品，感谢您的支持!', '2024-07-12 18:29:01', NULL);
INSERT INTO `custom_text_config` VALUES (2, 'end', '如有疑问请拨打400电话咨询', '2024-07-12 18:29:01', NULL);

-- ----------------------------
-- Table structure for field_display_config
-- ----------------------------
DROP TABLE IF EXISTS `field_display_config`;
CREATE TABLE `field_display_config`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `field_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `field_text_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_display` tinyint(1) NOT NULL DEFAULT 1,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of field_display_config
-- ----------------------------
INSERT INTO `field_display_config` VALUES (1, 'brand', '品牌', 1, '2024-07-12 18:22:25', NULL);
INSERT INTO `field_display_config` VALUES (2, 'product_type', '产品类型', 1, '2024-07-12 18:22:25', NULL);
INSERT INTO `field_display_config` VALUES (3, 'product_model', '产品型号', 1, '2024-07-12 18:22:25', NULL);
INSERT INTO `field_display_config` VALUES (4, 'product_serial', '产品序列号', 1, '2024-07-12 18:22:25', NULL);
INSERT INTO `field_display_config` VALUES (5, 'production_cycle', '出厂日期', 0, '2024-07-12 18:22:25', NULL);

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
  `proxy_factory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '创极宙', '显卡', 'GeForce RTX4090 AI 24G', 'HT00C0012351200001F44D', '23年51周', NULL, 1, '2024-07-12 22:23:28', '2024-07-16 00:00:50');
INSERT INTO `product` VALUES (2, '测试品牌001​', '测试产品类型001​', '测试产品型号001', '测试产品序列号001', '测试出厂周期001', NULL, 1, '2024-07-12 22:11:31', '2024-07-12 22:13:10');
INSERT INTO `product` VALUES (3, '焕宙0', '键盘', 'Txx', 'xxxxxxxxxxxxxxxxx0', '23年51周', NULL, 1, '2024-07-12 22:23:28', '2024-07-12 22:23:28');
INSERT INTO `product` VALUES (4, '焕宙1', '键盘', 'Txx', 'xxxxxxxxxxxxxxxxx1', '23年51周', NULL, 1, '2024-07-12 22:23:28', '2024-07-12 22:23:28');
INSERT INTO `product` VALUES (5, '焕宙2', '键盘', 'Txx', 'xxxxxxxxxxxxxxxxx2', '23年51周', NULL, 1, '2024-07-12 22:23:28', '2024-07-12 22:23:28');
INSERT INTO `product` VALUES (6, '测试1', '键盘', 'AAA', 'HT00C0012351200001F44D', '23年51周', NULL, 1, '2024-07-16 00:05:42', '2024-07-16 00:05:42');
INSERT INTO `product` VALUES (7, '测试2', '键盘', 'BBB', 'HT00C0012351200001F44D', '23年51周', NULL, 1, '2024-07-16 00:05:42', '2024-07-16 00:05:42');
INSERT INTO `product` VALUES (8, '测试3', '键盘', 'CCC', 'HT00C0012351200001F44D', '23年51周', NULL, 1, '2024-07-16 00:10:29', '2024-07-16 00:10:29');
INSERT INTO `product` VALUES (9, '测试4', '键盘', 'DDD', 'HT00C0012351200001F44D', '23年51周', NULL, 1, '2024-07-16 00:10:29', '2024-07-16 00:10:29');
INSERT INTO `product` VALUES (10, '测试5', '键盘', 'EEE', 'HT00C0012351200001F44D', '23年51周', NULL, 1, '2024-07-16 00:13:15', '2024-07-16 00:13:15');
INSERT INTO `product` VALUES (11, '测试6', '键盘', 'FFF', 'HT00C0012351200001F44D', '23年51周', NULL, 1, '2024-07-16 00:13:15', '2024-07-16 00:13:15');
INSERT INTO `product` VALUES (12, '测试7', '键盘', 'GGG', 'HT00C0012351200001F44D', '23年51周', NULL, 1, '2024-07-16 00:14:55', '2024-07-16 00:14:55');
INSERT INTO `product` VALUES (13, '测试8', '键盘', 'HHH', 'HT00C0012351200001F44D', '23年51周', NULL, 1, '2024-07-16 00:14:55', '2024-07-16 00:14:55');
INSERT INTO `product` VALUES (14, '测试7', '键盘', 'GGG', 'HT00C0012351200001F44D', '23年51周', NULL, 1, '2024-07-16 00:15:11', '2024-07-16 00:15:11');
INSERT INTO `product` VALUES (15, '测试8', '键盘', 'HHH', 'HT00C0012351200001F44D', '23年51周', NULL, 1, '2024-07-16 00:15:11', '2024-07-16 00:15:11');
INSERT INTO `product` VALUES (16, '创极宙', '显卡', 'GeForce RTX4090 AI 24G', 'HT00C0012351200001F44D', NULL, NULL, 1, '2024-07-17 14:53:49', '2024-07-17 14:53:49');
INSERT INTO `product` VALUES (17, '焕宙', '键盘', 'Txx', 'xxxxxxxxxxxxxxxx11', NULL, NULL, 1, '2024-07-17 14:53:49', '2024-07-17 14:53:49');
INSERT INTO `product` VALUES (18, '焕宙', '键盘', 'Txx', 'xxxxxxxxxxxxxxxx12', NULL, NULL, 1, '2024-07-17 14:53:49', '2024-07-17 14:53:49');
INSERT INTO `product` VALUES (19, '焕宙', '键盘', 'Txx', 'xxxxxxxxxxxxxxxx13', NULL, NULL, 1, '2024-07-17 14:53:49', '2024-07-17 14:53:49');
INSERT INTO `product` VALUES (20, '焕宙', '键盘', 'Txx', 'xxxxxxxxxxxxxxxx14', NULL, NULL, 1, '2024-07-17 14:53:49', '2024-07-17 14:53:49');
INSERT INTO `product` VALUES (21, '创极宙', '显卡', 'GeForce RTX4090 AI 24G', 'HT00C0012351200001F44D', NULL, NULL, 1, '2024-07-17 14:54:23', '2024-07-17 14:54:23');
INSERT INTO `product` VALUES (22, '焕宙', '键盘', 'Txx', 'xxxxxxxxxxxxxxxx11', NULL, NULL, 1, '2024-07-17 14:54:23', '2024-07-17 14:54:23');
INSERT INTO `product` VALUES (23, '焕宙', '键盘', 'Txx', 'xxxxxxxxxxxxxxxx12', NULL, NULL, 1, '2024-07-17 14:54:23', '2024-07-17 14:54:23');
INSERT INTO `product` VALUES (24, '焕宙', '键盘', 'Txx', 'xxxxxxxxxxxxxxxx13', NULL, NULL, 1, '2024-07-17 14:54:23', '2024-07-17 14:54:23');
INSERT INTO `product` VALUES (25, '焕宙', '键盘', 'Txx', 'xxxxxxxxxxxxxxxx14', NULL, NULL, 1, '2024-07-17 14:54:23', '2024-07-17 14:54:23');
INSERT INTO `product` VALUES (26, '创极宙', '显卡', 'GeForce RTX4090 AI 24G', 'HT00C0012351200001F44D', NULL, NULL, 1, '2024-07-17 14:56:36', '2024-07-17 14:56:36');
INSERT INTO `product` VALUES (27, '焕宙', '键盘', 'Txx', 'xxxxxxxxxxxxxxxx11', NULL, NULL, 1, '2024-07-17 14:56:36', '2024-07-17 14:56:36');
INSERT INTO `product` VALUES (28, '焕宙', '键盘', 'Txx', 'xxxxxxxxxxxxxxxx12', NULL, NULL, 1, '2024-07-17 14:56:36', '2024-07-17 14:56:36');
INSERT INTO `product` VALUES (29, '焕宙', '键盘', 'Txx', 'xxxxxxxxxxxxxxxx13', NULL, NULL, 1, '2024-07-17 14:56:36', '2024-07-17 14:56:36');
INSERT INTO `product` VALUES (30, '焕宙', '键盘', 'Txx', 'xxxxxxxxxxxxxxxx14', NULL, NULL, 1, '2024-07-17 14:56:36', '2024-07-17 14:56:36');
INSERT INTO `product` VALUES (31, '创极宙', '显卡', 'GeForce RTX4090 AI 24G', 'HT00C0012351200001F44A', NULL, NULL, 1, '2024-07-22 13:55:57', '2024-07-22 13:55:57');
INSERT INTO `product` VALUES (32, '焕宙', '键盘', 'Txx', 'HT00C0012351200001F45B', NULL, NULL, 1, '2024-07-22 13:55:57', '2024-07-22 13:55:57');
INSERT INTO `product` VALUES (33, '焕宙', '键盘', 'Txx', 'HT00C0012351200001F46C', NULL, NULL, 1, '2024-07-22 13:55:57', '2024-07-22 13:55:57');
INSERT INTO `product` VALUES (34, '焕宙', '键盘', 'Txx', 'HT00C0012351200001F47D', NULL, NULL, 1, '2024-07-22 13:55:57', '2024-07-22 13:55:57');
INSERT INTO `product` VALUES (35, '焕宙', '键盘', 'Txx', 'HT00C0012351200001F48E', NULL, NULL, 1, '2024-07-22 13:55:57', '2024-07-22 13:55:57');
INSERT INTO `product` VALUES (36, '创极宙', '显卡', 'GeForce RTX4090 AI 24G', 'HT00C0012351200001F44A', NULL, NULL, 1, '2024-07-22 14:07:06', '2024-07-22 14:07:06');
INSERT INTO `product` VALUES (37, '焕宙', '键盘', 'Txx', 'HT00C0012351200001F45B', NULL, NULL, 1, '2024-07-22 14:07:06', '2024-07-22 14:07:06');
INSERT INTO `product` VALUES (38, '焕宙', '键盘', 'Txx', 'HT00C0012351200001F46C', NULL, NULL, 1, '2024-07-22 14:07:06', '2024-07-22 14:07:06');
INSERT INTO `product` VALUES (39, '焕宙', '键盘', 'Txx', 'HT00C0012351200001F47D', NULL, NULL, 1, '2024-07-22 14:07:06', '2024-07-22 14:07:06');
INSERT INTO `product` VALUES (40, '焕宙', '键盘', 'Txx', 'HT00C0012351200001F48E', NULL, NULL, 1, '2024-07-22 14:07:06', '2024-07-22 14:07:06');
INSERT INTO `product` VALUES (41, '创极宙', '显卡', 'GeForce RTX4090 AI 24G', 'HT00C0012351200001F44A', NULL, NULL, 0, '2024-07-22 14:49:12', '2024-07-22 14:49:12');
INSERT INTO `product` VALUES (42, '焕宙', '键盘', 'Txx', 'HT00C0012351200001F45B', NULL, NULL, 0, '2024-07-22 14:49:12', '2024-07-22 14:49:12');
INSERT INTO `product` VALUES (43, '焕宙', '键盘', 'Txx', 'HT00C0012351200001F46C', NULL, NULL, 0, '2024-07-22 14:49:12', '2024-07-22 14:49:12');
INSERT INTO `product` VALUES (44, '焕宙', '键盘', 'Txx', 'HT00C0012351200001F47D', NULL, NULL, 0, '2024-07-22 14:49:12', '2024-07-22 14:49:12');
INSERT INTO `product` VALUES (45, '焕宙', '键盘', 'Txx', 'HT00C0012351200001F48E', NULL, NULL, 0, '2024-07-22 14:49:12', '2024-07-22 14:49:12');

SET FOREIGN_KEY_CHECKS = 1;
