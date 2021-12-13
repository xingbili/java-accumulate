-- ----------------------------
-- Table structure for tb_api_info
-- ----------------------------
DROP TABLE IF EXISTS `fusion-fin-mock.tb_api_info`;
CREATE TABLE `fusion-fin-mock.tb_api_info`  (
                                `id` bigint(20) NOT NULL COMMENT 'id',
                                `api_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'api请求路径',
                                `api_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'api名称描述',
                                `api_keyword` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口请求关键字',
                                PRIMARY KEY (`id`) USING BTREE,
                                UNIQUE INDEX `tb_api_keyword_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_api_response
-- ----------------------------
DROP TABLE IF EXISTS `fusion-fin-mock.tb_api_response`;
CREATE TABLE `fusion-fin-mock.tb_api_response`  (
                                    `id` bigint(20) NOT NULL COMMENT 'id',
                                    `api_response_body` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '返回信息body',
                                    `api_keyword_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口关键字值信息',
                                    `api_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'api请求路径关联 api信息表',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    UNIQUE INDEX `tb_api_response_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
