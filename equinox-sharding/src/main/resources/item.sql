/*
Navicat MySQL Data Transfer

Source Server         : MYSQL
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : equinox_sharding

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2022-06-07 17:04:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_item_0
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_0`;
CREATE TABLE `tb_item_0` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `item_id` bigint(20) NOT NULL COMMENT '商品id',
                             `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品标题',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `IX_itemid` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品表';

-- ----------------------------
-- Table structure for tb_item_1
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_1`;
CREATE TABLE `tb_item_1` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `item_id` bigint(20) NOT NULL COMMENT '商品id',
                             `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品标题',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `IX_itemid` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品表';

-- ----------------------------
-- Table structure for tb_item_2
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_2`;
CREATE TABLE `tb_item_2` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `item_id` bigint(20) NOT NULL COMMENT '商品id',
                             `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品标题',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `IX_itemid` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品表';

-- ----------------------------
-- Table structure for tb_item_3
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_3`;
CREATE TABLE `tb_item_3` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `item_id` bigint(20) NOT NULL COMMENT '商品id',
                             `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品标题',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `IX_itemid` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品表';

-- ----------------------------
-- Table structure for tb_item_4
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_4`;
CREATE TABLE `tb_item_4` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `item_id` bigint(20) NOT NULL COMMENT '商品id',
                             `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品标题',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `IX_itemid` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品表';

-- ----------------------------
-- Table structure for tb_item_5
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_5`;
CREATE TABLE `tb_item_5` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `item_id` bigint(20) NOT NULL COMMENT '商品id',
                             `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品标题',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `IX_itemid` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品表';

-- ----------------------------
-- Table structure for tb_item_6
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_6`;
CREATE TABLE `tb_item_6` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `item_id` bigint(20) NOT NULL COMMENT '商品id',
                             `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品标题',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `IX_itemid` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品表';

-- ----------------------------
-- Table structure for tb_item_7
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_7`;
CREATE TABLE `tb_item_7` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `item_id` bigint(20) NOT NULL COMMENT '商品id',
                             `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品标题',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `IX_itemid` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品表';

-- ----------------------------
-- Table structure for tb_item_8
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_8`;
CREATE TABLE `tb_item_8` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `item_id` bigint(20) NOT NULL COMMENT '商品id',
                             `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品标题',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `IX_itemid` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品表';

-- ----------------------------
-- Table structure for tb_item_9
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_9`;
CREATE TABLE `tb_item_9` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `item_id` bigint(20) NOT NULL COMMENT '商品id',
                             `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品标题',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `IX_itemid` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品表';
SET FOREIGN_KEY_CHECKS=1;
