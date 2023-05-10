/*
 Navicat Premium Data Transfer

 Source Server         : 本地-MySQL
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : master_zero

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 10/05/2023 17:40:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父菜单id',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单url',
  `permission` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限标识',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型 0：目录 1：菜单 2：按钮',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '删除状态 0：存在 1：删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '系统设置', 0, NULL, 'admin:user:read', '0', 12, ' el-icon-setting', '0', NULL, '2022-01-25 09:19:15');
INSERT INTO `menu` VALUES (3, '用户管理', 1, '/admin/user', 'admin:user:delete', '1', 4, 'el-icon-user', '0', NULL, '2021-09-24 09:43:11');
INSERT INTO `menu` VALUES (9, '角色管理', 1, '/admin/role', NULL, '1', 2, 'el-icon-bank-card', '0', NULL, '2021-09-23 17:53:07');
INSERT INTO `menu` VALUES (10, '菜单管理', 1, '/admin/menu', NULL, '1', 1, 'el-icon-s-operation', '0', NULL, '2021-09-23 17:53:48');
INSERT INTO `menu` VALUES (36, '字典管理', 1, '/admin/dict', NULL, '1', 8, 'el-icon-collection', '0', '2021-09-09 16:30:57', '2023-04-28 10:57:01');
INSERT INTO `menu` VALUES (37, '通知管理', 0, '/notification', NULL, '1', 10, 'el-icon-postcard', '1', '2021-09-09 16:32:31', '2022-01-27 15:39:18');
INSERT INTO `menu` VALUES (43, '操作日志', 1, '/admin/operateLog', NULL, '1', 1, 'el-icon-user', '0', '2021-09-26 10:56:58', '2021-09-26 10:57:13');
INSERT INTO `menu` VALUES (60, '打卡管理', 0, NULL, NULL, '0', 1, 'el-icon-bank-card', '0', '2023-04-28 10:57:22', '2023-04-28 10:58:41');
INSERT INTO `menu` VALUES (61, '打卡记录', 60, '/clock/clockIn', NULL, '1', 1, 'el-icon-bank-card', '0', '2023-04-28 10:58:33', '2023-04-28 10:58:46');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色描述',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据范围',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `uk_role_key`(`role_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_ADMIN', '系统管理员', '拥有系统最高权限', '1', NULL, '2021-08-30 16:50:27');
INSERT INTO `role` VALUES (2, 'ROLE_USER', '普通用户', '普通用户', '2', NULL, '2021-10-21 15:58:37');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色id',
  `menu_id` bigint NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (2, 60);
INSERT INTO `role_menu` VALUES (2, 61);

-- ----------------------------
-- Table structure for role_unit
-- ----------------------------
DROP TABLE IF EXISTS `role_unit`;
CREATE TABLE `role_unit`  (
  `role_id` bigint NOT NULL COMMENT '角色id',
  `unit_id` bigint NOT NULL COMMENT '单位id',
  PRIMARY KEY (`role_id`, `unit_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色单位' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_unit
-- ----------------------------
INSERT INTO `role_unit` VALUES (2, 1);
INSERT INTO `role_unit` VALUES (2, 2);
INSERT INTO `role_unit` VALUES (2, 3);
INSERT INTO `role_unit` VALUES (2, 4);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典id',
  `dict_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `dict_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (11, 'Clock-In-State', '打卡记录状态', ' ', '2023-04-28 11:52:17', '2023-04-28 12:24:22');

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `dict_item_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典数据id',
  `dict_id` bigint NULL DEFAULT NULL COMMENT '字典id',
  `dict_label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典标签',
  `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典数据',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`dict_item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (17, 11, '未打卡', '0', 'danger', 1, '2023-04-28 11:56:11', '2023-04-29 17:18:21');
INSERT INTO `sys_dict_item` VALUES (18, 11, '已打卡', '1', 'success', 1, '2023-04-28 11:56:27', '2023-04-29 17:18:05');

-- ----------------------------
-- Table structure for sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log`  (
  `log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志标题',
  `request_method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求URI',
  `request_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
  `response_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '响应结果',
  `ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `browser` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `operating_system` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `operate_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作状态',
  `operate_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  `operate_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `execute_time` bigint NULL DEFAULT NULL COMMENT '执行耗时',
  `exception_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '异常信息',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1528 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operate_log
-- ----------------------------
INSERT INTO `sys_operate_log` VALUES (1306, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-18 17:36:09', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1307, '新增单位', 'POST', '/api/unit', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 11:02:19', 29, NULL);
INSERT INTO `sys_operate_log` VALUES (1308, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102006', '2022-02-21 11:02:27', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1309, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 11:04:51', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1310, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 14:32:52', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1311, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 14:33:12', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1312, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 14:35:13', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1313, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 14:35:24', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1314, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 14:35:45', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1315, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 14:35:59', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1316, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 14:37:27', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1317, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 14:37:42', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1318, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 14:38:13', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1319, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 14:42:00', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1320, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-21 15:51:53', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1321, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-21 15:52:04', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1322, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 15:53:22', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1323, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 15:53:50', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1324, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-21 15:53:57', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1325, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-21 15:54:45', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1326, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-21 15:56:06', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1327, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-21 15:56:55', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1328, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-21 15:57:46', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1329, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-21 15:58:31', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1330, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-21 15:59:25', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1331, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 15:59:39', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1332, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-21 16:00:41', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1333, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-21 16:00:57', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1334, '登录失败', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '1', '102004', '2022-02-21 16:01:05', 0, '用户名或密码错误');
INSERT INTO `sys_operate_log` VALUES (1335, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-21 16:01:09', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1336, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-22 16:37:12', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1337, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-22 16:42:20', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1338, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-22 16:42:29', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1339, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-22 16:42:59', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1340, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102001', '2022-02-22 16:43:12', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1341, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-22 16:43:32', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1342, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-22 16:43:40', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1343, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-22 16:44:15', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1344, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-22 16:51:31', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1345, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-22 16:52:03', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1346, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-22 17:31:47', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1347, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-23 13:34:28', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1348, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-23 13:34:34', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1349, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-23 15:50:19', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1350, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 09:51:07', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1351, '新增单位', 'POST', '/api/unit', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 09:51:30', 23, NULL);
INSERT INTO `sys_operate_log` VALUES (1352, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102007', '2022-02-28 09:51:36', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1353, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 09:55:24', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1354, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102006', '2022-02-28 10:22:46', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1355, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 10:23:44', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1356, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-28 10:24:24', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1357, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 10:24:51', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1358, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 10:25:21', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1359, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-28 10:25:31', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1360, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 10:25:40', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1361, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 10:39:35', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1362, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-28 10:45:12', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1363, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 10:45:35', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1364, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 10:50:46', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1365, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-28 10:51:07', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1366, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 10:58:33', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1367, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 11:19:12', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1368, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 11:31:15', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1369, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102001', '2022-02-28 12:09:42', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1370, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102001001', '2022-02-28 12:10:56', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1371, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102001001', '2022-02-28 12:11:09', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1372, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-02-28 12:15:18', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1373, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102001', '2022-02-28 12:50:26', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1374, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-02-28 13:04:57', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1375, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-03-01 09:27:16', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1376, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-03-01 09:27:22', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1377, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-03-01 09:28:24', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1378, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-03-01 09:29:17', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1379, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-03-01 09:29:35', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1380, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-03-01 09:38:01', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1381, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-03-01 09:38:56', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1382, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-03-01 10:18:56', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1383, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-03-01 11:10:56', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1384, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-03-01 11:11:07', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1385, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-03-01 11:24:26', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1386, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-03-01 14:59:39', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1387, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '0:0:0:0:0:0:0:1', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-03-01 14:59:53', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1388, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102005', '2022-03-02 17:43:04', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1389, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102005', '2022-03-02 17:43:15', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1390, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-03-02 17:56:05', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1391, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-03-02 18:03:57', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1392, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-03-02 18:04:08', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1393, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-03-02 18:05:41', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1394, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-03-03 12:05:28', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1395, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102005', '2022-03-03 12:05:44', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1396, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-03-03 12:05:59', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1397, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102', '2022-03-03 12:10:54', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1398, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 98.0.4758.102', 'Windows 10.0', '0', '102004', '2022-03-03 12:11:03', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1399, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102', '2022-03-09 14:27:50', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1400, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.100', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102004', '2022-03-09 14:32:23', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1401, '登录失败', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.51', 'Windows 10.0', '1', '102004', '2022-03-14 14:07:51', 0, '用户名或密码错误');
INSERT INTO `sys_operate_log` VALUES (1402, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102004', '2022-03-14 14:07:56', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1403, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102004', '2022-03-14 14:08:20', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1404, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102', '2022-03-15 15:52:18', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1405, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102002001', '2022-03-15 15:52:35', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1406, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102002001', '2022-03-15 15:52:46', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1407, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102002', '2022-03-15 15:53:07', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1408, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102002001', '2022-03-15 15:53:19', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1409, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102002', '2022-03-15 15:57:00', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1410, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102', '2022-03-15 15:57:32', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1411, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102002', '2022-03-15 15:59:15', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1412, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.120', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102002', '2022-03-15 16:13:45', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1413, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.128', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-15 16:14:54', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1414, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.120', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102002001', '2022-03-15 16:15:50', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1415, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.120', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102002', '2022-03-15 16:16:44', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1416, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.128', 'Firefox 98.0', 'Windows 10.0', '0', '102004', '2022-03-15 16:17:13', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1417, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.128', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-15 16:18:12', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1418, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.128', 'Firefox 98.0', 'Windows 10.0', '0', '102004', '2022-03-15 16:19:53', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1419, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.128', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-15 16:20:42', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1420, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.128', 'Firefox 98.0', 'Windows 10.0', '0', '102002002', '2022-03-15 16:22:57', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1421, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.120', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102', '2022-03-15 16:36:54', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1422, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.120', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102004', '2022-03-15 16:38:14', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1423, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.128', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-15 16:43:06', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1424, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.120', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102', '2022-03-15 16:43:57', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1425, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.128', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-15 16:47:07', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1426, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.128', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-15 16:50:21', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1427, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.128', 'Firefox 98.0', 'Windows 10.0', '0', '102002002', '2022-03-15 16:52:50', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1428, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.120', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102004', '2022-03-15 16:53:21', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1429, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.120', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102', '2022-03-15 16:53:39', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1430, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.120', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102', '2022-03-16 09:37:23', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1431, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102004', '2022-03-16 12:22:08', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1432, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102004', '2022-03-16 12:30:49', 1, NULL);
INSERT INTO `sys_operate_log` VALUES (1433, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.1.120', 'Chrome 99.0.4844.51', 'Windows 10.0', '0', '102004', '2022-03-16 15:51:29', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1434, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002002', '2022-03-16 17:04:33', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1435, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-16 17:06:52', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1436, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-16 17:11:49', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1437, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-16 17:13:14', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1438, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-17 09:04:17', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1439, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-17 09:05:12', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1440, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-17 09:25:14', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1441, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-17 10:28:13', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1442, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-17 10:44:47', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1443, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 16:22:15', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1444, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 16:24:59', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1445, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102004', '2022-03-29 16:25:52', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1446, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 16:26:17', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1447, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 16:28:08', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1448, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-29 16:30:55', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1449, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-29 16:55:07', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1450, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-29 16:56:27', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1451, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-29 16:58:40', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1452, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 16:58:57', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1453, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-29 16:59:58', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1454, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 17:00:10', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1455, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-29 17:11:29', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1456, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-29 17:17:37', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1457, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 17:20:11', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1458, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102004', '2022-03-29 17:23:01', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1459, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 17:24:57', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1460, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 17:34:03', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1461, '登录失败', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '1', '102002004', '2022-03-29 17:35:39', 1, '用户名或密码错误');
INSERT INTO `sys_operate_log` VALUES (1462, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-29 17:35:45', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1463, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-29 17:39:12', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1464, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 17:40:51', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1465, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-29 17:40:58', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1466, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 17:46:46', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1467, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 17:46:56', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1468, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 17:54:28', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1469, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-29 17:55:17', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1470, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-29 17:57:07', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1471, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-29 17:58:24', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1472, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 08:34:35', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1473, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-30 08:36:09', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1474, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 08:58:11', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1475, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-30 08:58:21', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1476, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 09:11:44', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1477, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 10:01:18', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1478, '登录失败', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '1', '102002004', '2022-03-30 10:01:44', 0, '用户名或密码错误');
INSERT INTO `sys_operate_log` VALUES (1479, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-30 10:01:49', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1480, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-30 10:12:50', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1481, '登录失败', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '1', '102004001', '2022-03-30 10:15:53', 0, '用户名或密码错误');
INSERT INTO `sys_operate_log` VALUES (1482, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-30 10:15:57', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1483, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-30 10:17:07', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1484, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 10:17:51', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1485, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-30 10:18:58', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1486, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-30 11:43:40', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1487, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-30 11:44:15', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1488, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 11:45:01', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1489, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-30 11:45:41', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1490, '登录失败', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '1', '102002004', '2022-03-30 11:46:34', 0, '用户名或密码错误');
INSERT INTO `sys_operate_log` VALUES (1491, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-30 11:46:38', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1492, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-30 11:47:57', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1493, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 11:48:26', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1494, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-30 11:48:43', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1495, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-30 11:49:02', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1496, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 11:51:42', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1497, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-30 11:52:12', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1498, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002001', '2022-03-30 11:53:11', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1499, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-30 11:58:36', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1500, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 11:59:58', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1501, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-30 12:00:34', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1502, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 12:01:23', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1503, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102002', '2022-03-30 12:02:25', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1504, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 13:53:08', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1505, '新增单位', 'POST', '/api/unit', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 16:42:15', 129, NULL);
INSERT INTO `sys_operate_log` VALUES (1506, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102008', '2022-03-30 16:42:58', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1507, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 16:43:34', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1508, '登录失败', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '1', '102008', '2022-03-30 16:44:35', 0, '用户名或密码错误');
INSERT INTO `sys_operate_log` VALUES (1509, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102008', '2022-03-30 16:44:40', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1510, '登录失败', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '1', '102002', '2022-03-30 16:45:40', 0, '用户名或密码错误');
INSERT INTO `sys_operate_log` VALUES (1511, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102008', '2022-03-30 16:45:52', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1512, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 16:47:00', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1513, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Firefox 98.0', 'Windows 10.0', '0', '102', '2022-03-30 17:30:44', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1514, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.82', 'Windows 10.0', '0', '102004', '2022-03-30 17:56:26', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1515, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.84', 'Windows 10.0', '0', '102', '2022-04-12 16:49:30', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1516, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.84', 'Windows 10.0', '0', '102', '2022-04-12 17:42:45', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1517, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.84', 'Windows 10.0', '0', '102', '2022-04-14 16:58:17', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1518, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.84', 'Windows 10.0', '0', '102', '2022-04-14 17:01:02', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1519, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.84', 'Windows 10.0', '0', '102', '2022-04-14 17:10:01', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1520, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 99.0.4844.84', 'Windows 10.0', '0', '102', '2022-04-14 17:24:38', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1521, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 112.0.0.0', 'Windows 10.0', '0', '102', '2023-04-26 13:34:27', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1522, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 112.0.0.0', 'Windows 10.0', '0', '102', '2023-04-28 10:55:51', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1523, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 112.0.0.0', 'Windows 10.0', '0', '102', '2023-04-28 10:56:34', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1524, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '192.168.214.179', 'Chrome 87.0.4280.141', 'Android 11', '0', '102', '2023-04-28 11:33:00', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1525, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 112.0.0.0', 'Windows 10.0', '0', '102', '2023-04-29 15:54:46', 0, NULL);
INSERT INTO `sys_operate_log` VALUES (1526, '登录失败', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 112.0.0.0', 'Windows 10.0', '1', '102', '2023-04-29 20:52:43', 0, '用户名或密码错误');
INSERT INTO `sys_operate_log` VALUES (1527, '登录成功', 'POST', '/api/auth/login', NULL, NULL, '127.0.0.1', 'Chrome 112.0.0.0', 'Windows 10.0', '0', '102', '2023-04-30 10:43:25', 0, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `unit_id` bigint NULL DEFAULT NULL COMMENT '单位id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `enabled` tinyint NULL DEFAULT NULL COMMENT '是否可用',
  `approved` tinyint NULL DEFAULT NULL COMMENT '是否审批',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (53, '102', '{bcrypt}$2a$10$.ElI11Xn9us.YHshzgQun.3KOsv7xUh/80fFxXwn6LUvB2uFsTSu6', 1, '超级管理员', 1, 1, '2021-09-23 14:51:25', '2021-12-20 09:57:16');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (53, 1);

SET FOREIGN_KEY_CHECKS = 1;
