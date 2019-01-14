/*
Navicat MySQL Data Transfer

Source Server         : mysql8
Source Server Version : 80013
Source Host           : 127.0.0.1:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-01-15 01:13:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area` (
  `area_id` int(2) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(200) NOT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `UK_AREA` (`area_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_area
-- ----------------------------
INSERT INTO `tb_area` VALUES ('1', '东桃园', '1', '2018-12-25 22:17:03', '2018-12-25 22:17:06');
INSERT INTO `tb_area` VALUES ('2', '西桃园', '2', '2018-12-25 22:17:20', '2018-12-25 22:17:22');

-- ----------------------------
-- Table structure for tb_head_line
-- ----------------------------
DROP TABLE IF EXISTS `tb_head_line`;
CREATE TABLE `tb_head_line` (
  `line_id` int(100) NOT NULL AUTO_INCREMENT,
  `line_name` varchar(1000) DEFAULT NULL,
  `line_link` varchar(2000) NOT NULL,
  `line_img` varchar(2000) NOT NULL,
  `priority` int(2) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`line_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_head_line
-- ----------------------------

-- ----------------------------
-- Table structure for tb_local_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_local_auth`;
CREATE TABLE `tb_local_auth` (
  `local_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `user_name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`local_auth_id`),
  UNIQUE KEY `uk_local_profile` (`user_name`),
  KEY `fk_local_profile` (`user_id`),
  CONSTRAINT `fk_local_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_local_auth
-- ----------------------------

-- ----------------------------
-- Table structure for tb_person_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_person_info`;
CREATE TABLE `tb_person_info` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `profile_img` varchar(256) DEFAULT NULL,
  `email` varchar(256) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `enable_status` int(2) DEFAULT '0' COMMENT '0:forbidden,1:enable',
  `user_type` int(2) DEFAULT '1' COMMENT '1:顾客 2:店家 3:超管',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_person_info` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_person_info
-- ----------------------------
INSERT INTO `tb_person_info` VALUES ('1', 'vn001', '图像地址', '111@11.com', '1', '1', '2', null, null);

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `product_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `product_desc` varchar(2000) DEFAULT NULL,
  `img_addr` varchar(2000) DEFAULT '',
  `normal_price` varchar(100) DEFAULT NULL,
  `promotion_price` varchar(100) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `point` int(10) DEFAULT NULL,
  `product_category_id` int(11) DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`),
  KEY `fk_product_shop` (`shop_id`),
  KEY `fk_product_procate` (`product_category_id`),
  CONSTRAINT `fk_product_procate` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`product_category_id`),
  CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product
-- ----------------------------

-- ----------------------------
-- Table structure for tb_product_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_category`;
CREATE TABLE `tb_product_category` (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(100) NOT NULL,
  `product_category_desc` varchar(500) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_category_id`),
  KEY `fk_procate_shop` (`shop_id`),
  CONSTRAINT `fk_procate_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_category
-- ----------------------------
INSERT INTO `tb_product_category` VALUES ('16', '店铺商品类别1', '店铺商品类别1', '20', '2019-01-14 21:57:04', null, '31');
INSERT INTO `tb_product_category` VALUES ('17', '店铺商品类别2', '店铺商品类别2', '0', '2019-01-14 21:57:24', null, '31');
INSERT INTO `tb_product_category` VALUES ('19', '店铺商品类别3', '店铺商品类别3', '2', '2019-01-14 21:57:46', null, '31');
INSERT INTO `tb_product_category` VALUES ('20', 'test类别1', 'test描述1', '14', '2019-01-14 16:32:49', null, '31');
INSERT INTO `tb_product_category` VALUES ('21', 'test类别2', 'test描述2', '24', '2019-01-14 16:32:49', null, '31');
INSERT INTO `tb_product_category` VALUES ('22', 'test1', null, '2', null, null, '31');
INSERT INTO `tb_product_category` VALUES ('23', 'test2', null, '3', null, null, '31');

-- ----------------------------
-- Table structure for tb_product_img
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_img`;
CREATE TABLE `tb_product_img` (
  `product_img_id` int(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(2000) NOT NULL,
  `img_desc` varchar(2000) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`product_img_id`),
  KEY `fk_proimg_product` (`product_id`),
  CONSTRAINT `fk_proimg_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_img
-- ----------------------------

-- ----------------------------
-- Table structure for tb_shop
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop` (
  `shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(10) NOT NULL COMMENT '店铺创建人',
  `area_id` int(5) DEFAULT NULL,
  `shop_category_id` int(11) DEFAULT NULL,
  `shop_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `shop_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_addr` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `advice` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`shop_id`),
  KEY `fk_shop_profile` (`owner_id`),
  KEY `fk_shop_area` (`area_id`),
  KEY `fk_shop_shopcate` (`shop_category_id`),
  CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`),
  CONSTRAINT `fk_shop_profile` FOREIGN KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_shop_shopcate` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_shop
-- ----------------------------
INSERT INTO `tb_shop` VALUES ('31', '1', '2', '34', '绿茶街角', '好喝的奶茶1', '拐弯100米', '1871732333', '奶茶铺图片链接', '1', '2018-12-27 15:33:44', '2019-01-10 16:37:13', '1', '加糖');
INSERT INTO `tb_shop` VALUES ('32', '1', '2', '34', '绿茶街', '好喝的奶茶', '拐弯10米', '18717322343', '奶茶铺地址', '1', '2018-12-27 16:34:06', '2018-12-27 16:34:06', '1', '加糖');
INSERT INTO `tb_shop` VALUES ('42', '1', '2', '34', '咖啡街', '醇厚的咖啡', '足浴对面', '12717334633', '\\upload\\images\\item\\shop\\42\\2018123011291144974.png', '1', '2018-12-30 03:29:12', '2018-12-30 03:29:12', '0', '苦咖啡');
INSERT INTO `tb_shop` VALUES ('43', '1', '2', '34', '咖啡街', '醇厚的咖啡', '足浴对面', '12717334633', '\\upload\\images\\item\\shop\\43\\2018123011292280921.png', '1', '2018-12-30 03:29:22', '2018-12-30 03:29:22', '0', '苦咖啡');
INSERT INTO `tb_shop` VALUES ('44', '1', '2', '34', '咖啡街', '醇厚的咖啡', '足浴对面', '12717334633', '\\upload\\images\\item\\shop\\44\\2018123011304589619.png', '1', '2018-12-30 03:30:46', '2018-12-30 03:30:46', '0', '苦咖啡');
INSERT INTO `tb_shop` VALUES ('45', '1', '2', '34', '咖啡街', '醇厚的咖啡111111', '足浴对面', '12717334633', '\\upload\\images\\item\\shop\\45\\2019010400344372517.png', '1', '2019-01-03 16:34:43', '2019-01-03 16:34:43', '0', '苦咖啡1111');
INSERT INTO `tb_shop` VALUES ('48', '1', '2', '37', '22', '22222', '2222', '222', '\\upload\\images\\item\\shop\\48\\2019010602190980854.png', null, '2019-01-05 18:19:09', '2019-01-05 18:19:09', '0', null);
INSERT INTO `tb_shop` VALUES ('49', '1', '2', '37', '绿茶', '金克斯的商铺', '北门', '029-8888888', '\\upload\\images\\item\\shop\\49\\2019010616011171973.jpg', null, '2019-01-06 08:01:11', '2019-01-06 08:01:11', '0', null);
INSERT INTO `tb_shop` VALUES ('50', '1', '2', '37', '果汁街', '好喝的果汁', '拐弯200米', '18717322343', '果汁地址', '1', '2019-01-07 15:41:05', '2019-01-07 15:41:05', '1', '加糖');
INSERT INTO `tb_shop` VALUES ('51', '1', '2', '34', '咖啡街', '醇厚的咖啡111111', '足浴对面', '12717334633', '\\upload\\images\\item\\shop\\51\\2019010723433481811.png', '1', '2019-01-07 15:43:34', '2019-01-07 15:43:34', '0', '苦咖啡1111');
INSERT INTO `tb_shop` VALUES ('52', '1', '2', '34', '咖啡街', '醇厚的咖啡111111', '足浴对面', '12717334633', '\\upload\\images\\item\\shop\\52\\2019010823553728470.png', '1', '2019-01-08 15:55:37', '2019-01-08 15:55:37', '0', '苦咖啡1111');
INSERT INTO `tb_shop` VALUES ('53', '1', '2', '34', '果汁街', '好喝的果汁', '拐弯200米', '18717322343', '果汁地址', '1', '2019-01-12 10:33:03', '2019-01-12 10:33:03', '1', '加糖');
INSERT INTO `tb_shop` VALUES ('54', '1', '2', '34', '果汁街', '好喝的果汁', '拐弯200米', '18717322343', '果汁地址', '1', '2019-01-12 10:42:35', '2019-01-12 10:42:35', '1', '加糖');
INSERT INTO `tb_shop` VALUES ('55', '1', '2', '34', '果汁街', '好喝的果汁', '拐弯200米', '18717322343', '果汁地址', '1', '2019-01-12 10:44:59', '2019-01-12 10:44:59', '1', '加糖');

-- ----------------------------
-- Table structure for tb_shop_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_category`;
CREATE TABLE `tb_shop_category` (
  `shop_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_category_name` varchar(100) NOT NULL DEFAULT '',
  `shop_category_desc` varchar(1000) DEFAULT '',
  `shop_category_img` varchar(2000) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`shop_category_id`),
  KEY `fk_shop_category_self` (`parent_id`),
  CONSTRAINT `fk_shop_category_self` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop_category
-- ----------------------------
INSERT INTO `tb_shop_category` VALUES ('34', '饮品', '好喝的饮品', '图片链接', '1', '2018-12-27 23:09:38', '2018-12-27 23:09:40', null);
INSERT INTO `tb_shop_category` VALUES ('37', '小吃', '沙县小吃', '图片地址', '0', '2019-01-03 22:43:23', '2019-01-03 22:43:25', '34');
INSERT INTO `tb_shop_category` VALUES ('38', '蒸饺', '好吃的蒸饺', 'dddd', '2', '2019-01-03 22:44:55', '2019-01-03 22:44:58', '34');

-- ----------------------------
-- Table structure for tb_shop_copy
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_copy`;
CREATE TABLE `tb_shop_copy` (
  `shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(10) NOT NULL COMMENT '店铺创建人',
  `area_id` int(5) DEFAULT NULL,
  `shop_category_id` int(11) DEFAULT NULL,
  `parent_category_id` int(11) DEFAULT NULL,
  `shop_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `shop_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_addr` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `longitude` double(16,12) DEFAULT NULL,
  `latitude` double(16,12) DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `advice` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`shop_id`),
  KEY `fk_shop_profile` (`owner_id`),
  KEY `fk_shop_area` (`area_id`),
  KEY `fk_shop_shopcate` (`shop_category_id`),
  KEY `fk_shop_parentcate` (`parent_category_id`),
  CONSTRAINT `tb_shop_copy_ibfk_1` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`),
  CONSTRAINT `tb_shop_copy_ibfk_2` FOREIGN KEY (`parent_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`),
  CONSTRAINT `tb_shop_copy_ibfk_3` FOREIGN KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `tb_shop_copy_ibfk_4` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_shop_copy
-- ----------------------------

-- ----------------------------
-- Table structure for tb_wechat_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_wechat_auth`;
CREATE TABLE `tb_wechat_auth` (
  `wechat_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `open_id` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`wechat_auth_id`),
  UNIQUE KEY `open_id` (`open_id`),
  KEY `fk_oauth_profile` (`user_id`),
  CONSTRAINT `fk_oauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_wechat_auth
-- ----------------------------
