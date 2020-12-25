/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : yami_shops_chr

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 24/12/2020 14:07:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chryl_user
-- ----------------------------
DROP TABLE IF EXISTS `chryl_user`;
CREATE TABLE `chryl_user` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of chryl_user
-- ----------------------------
BEGIN;
INSERT INTO `chryl_user` VALUES ('1', '社保局啊', 'wpkrpwjrpwejpf', '2020-12-30');
INSERT INTO `chryl_user` VALUES ('2', '32411ffff2', 'sadlnf;nsn2', '2020-12-17');
INSERT INTO `chryl_user` VALUES ('cac81f6d8446c', 'zzz', '1', '2020-11-02');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
