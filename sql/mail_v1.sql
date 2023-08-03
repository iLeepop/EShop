/*
 Navicat MySQL Data Transfer

 Source Server         : lzh
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : mail_v1

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 03/08/2023 16:20:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(66) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `password` varchar(66) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'ilee', '123123');

-- ----------------------------
-- Table structure for t_bigtype
-- ----------------------------
DROP TABLE IF EXISTS `t_bigtype`;
CREATE TABLE `t_bigtype`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `remark` varchar(765) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `image` varchar(765) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_bigtype
-- ----------------------------
INSERT INTO `t_bigtype` VALUES (1, '手机', '手机描述', '1.jpg');
INSERT INTO `t_bigtype` VALUES (2, '电脑平板', '电脑平板描述', '2.jpg');
INSERT INTO `t_bigtype` VALUES (3, '智能穿戴', '智能穿戴描述', '3.jpg');
INSERT INTO `t_bigtype` VALUES (4, '电视', '电视描述hh', '4.jpg');
INSERT INTO `t_bigtype` VALUES (5, '大家电', '大家电描述', '5.jpg');
INSERT INTO `t_bigtype` VALUES (6, '小家电', '小家电描述', '6.jpg');
INSERT INTO `t_bigtype` VALUES (7, '智能家居', '智能家居描述', '7.jpg');
INSERT INTO `t_bigtype` VALUES (8, '户外出行', '户外出行描述', '8.jpg');
INSERT INTO `t_bigtype` VALUES (9, '日用百货', '日用百货描述', '9.jpg');
INSERT INTO `t_bigtype` VALUES (10, '儿童用品', '儿童用品描述', '10.jpg');
INSERT INTO `t_bigtype` VALUES (12, '123', '123123hhh', '20230801020731000000771.jpg');
INSERT INTO `t_bigtype` VALUES (13, '123', '123321', 'default.jpg');
INSERT INTO `t_bigtype` VALUES (14, '3444', '444', 'default.jpg');
INSERT INTO `t_bigtype` VALUES (15, '123', '123', 'default.jpg');
INSERT INTO `t_bigtype` VALUES (16, '123', '123', 'default.jpg');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `userId` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `totalPrice` decimal(8, 2) NULL DEFAULT NULL,
  `address` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `consignee` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `telNumber` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `createDate` datetime NULL DEFAULT NULL,
  `payDate` datetime NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 147 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (144, 'ILEE20230722051230000000822', 'otdFl5dCZdplh1W_csjm3NwiG7yY', 2599.00, '广东省广州市海珠区新港中路397号', '张三', '020-81167888', '2023-07-22 17:12:31', NULL, 1);
INSERT INTO `t_order` VALUES (145, 'ILEE20230724073029000000266', 'otdFl5dCZdplh1W_csjm3NwiG7yY', 1999.00, '广东省广州市海珠区新港中路397号', '张三', '020-81167888', '2023-07-24 19:30:29', NULL, 2);

-- ----------------------------
-- Table structure for t_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_order_detail`;
CREATE TABLE `t_order_detail`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `mId` int NULL DEFAULT NULL,
  `goodsId` int NULL DEFAULT NULL,
  `goodsNumber` int NULL DEFAULT NULL,
  `goodsPrice` decimal(8, 2) NULL DEFAULT NULL,
  `goodsName` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `goodsPic` varchar(765) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 193 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_detail
-- ----------------------------
INSERT INTO `t_order_detail` VALUES (190, 144, 14, 1, 2599.00, 'Xiaomi Civi', '9.png');
INSERT INTO `t_order_detail` VALUES (191, 145, 16, 1, 1999.00, '小米平板5', '13.png');

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `name` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `price` decimal(8, 2) NULL DEFAULT NULL,
  `stock` int NULL DEFAULT NULL,
  `proPic` varchar(765) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `isHot` bit(1) NULL DEFAULT NULL,
  `isSwiper` bit(1) NULL DEFAULT NULL,
  `swiperPic` varchar(765) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `swiperSort` int NULL DEFAULT NULL,
  `typeId` int NULL DEFAULT NULL,
  `hotDateTime` datetime NULL DEFAULT NULL,
  `productIntroImgs` text CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL,
  `productParaImgs` text CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES (0000000001, '小米电视大师 82英寸', 10999.00, 3459, '20230802053002000000182.png', b'0', b'1', '2.jpg', 3, 14, NULL, '\"\"', '\"\"', '\"123\"');
INSERT INTO `t_product` VALUES (0000000004, 'Xiaomi 11', 3799.00, 3232, '6.png', b'1', b'0', '20230802053431000000494.jpg', 4, 2, '2021-07-28 21:36:34', '<img width=\'100%\' src=\'http://localhost:8080/image/productIntroImgs/11.jpg\'></img>', '<img width=\'100%\' src=\'http://localhost:8080/image/productParaImgs/11.jpg\'></img>', '「全版本领券立减200元，券后价3299元起；享至高24期免息；赠手机保护壳*1;【全款支付套装】赠果冻包」');
INSERT INTO `t_product` VALUES (0000000005, 'Redmi K40 游戏增强版', 2299.00, 2005, '11.png', b'0', b'1', '20230802053019000000621.jpg', 1, 39, NULL, '\"\"', '\"\"', '\"\"');
INSERT INTO `t_product` VALUES (0000000006, 'Xiaomi 11 Pro', 4499.00, 2343, '1.png', b'1', b'0', 'default.jpg', 0, 2, '2021-09-28 21:36:34', '\"\"', NULL, NULL);
INSERT INTO `t_product` VALUES (0000000007, 'Xiaomi MIX FOLD折叠屏手机', 7999.00, 2222, '2.png', b'1', b'0', 'default.jpg', 0, 3, '2021-10-28 21:36:34', '\"\"', NULL, NULL);
INSERT INTO `t_product` VALUES (0000000008, 'Note 9 5G', 1199.00, 1111, '3.png', b'1', b'0', 'default.jpg', 0, 40, '2021-09-28 21:36:34', '\"\"', NULL, NULL);
INSERT INTO `t_product` VALUES (0000000009, 'Xiaomi 10S', 2999.00, 1111, '4.png', b'1', b'0', 'default.jpg', 0, 2, '2021-12-28 21:36:34', '\"\"', NULL, NULL);
INSERT INTO `t_product` VALUES (0000000010, 'Note 9 Pro 5G', 1399.00, 2222, '5.png', b'1', b'0', 'default.jpg', 0, 40, '2021-11-28 21:36:34', '\"\"', NULL, NULL);
INSERT INTO `t_product` VALUES (0000000011, '黑鲨4', 2499.00, 3322, '7.png', b'1', b'0', 'default.jpg', 0, 41, '2021-11-28 21:36:34', '\"\"', NULL, NULL);
INSERT INTO `t_product` VALUES (0000000012, 'Redmi  K40 Pro 系列', 2499.00, 3244, '8.png', b'1', b'0', 'default.jpg', 0, 39, '2021-11-28 21:36:34', NULL, NULL, NULL);
INSERT INTO `t_product` VALUES (0000000014, 'Xiaomi Civi', 2599.00, 4444, '9.png', b'0', b'0', 'default.jpg', 0, 1, NULL, '<img width=\'100%\' src=\'http://localhost:8080/image/productIntroImgs/1.jpg\'></img><img width=\'100%\' src=\'http://localhost:8080/image/productIntroImgs/2.jpg\'></img><img width=\'100%\' src=\'http://localhost:8080/image/productIntroImgs/3.jpg\'></img>', '<img width=\'100%\' src=\'http://localhost:8080/image/productParaImgs/1.jpg\'></img><img width=\'100%\' src=\'http://localhost:8080/image/productParaImgs/2.jpg\'></img>', '「购机至高享24期免息；赠Redmi AirDots 2真无线蓝牙耳机；赠Keep会员7天体验卡；+110元得Air2 SE蓝牙耳机」');
INSERT INTO `t_product` VALUES (0000000015, 'Xiaomi 11 Ultra', 5499.00, 4444, '10.png', b'0', b'0', 'default.jpg', 0, 2, NULL, NULL, NULL, NULL);
INSERT INTO `t_product` VALUES (0000000016, '小米平板5', 1999.00, 444, '13.png', b'0', b'0', 'default.jpg', 0, 5, NULL, '<img src=\'http://localhost:8080/image/productIntroImgs/111.jpg\'></img><img width=\'100%\' src=\'http://localhost:8080/image/productIntroImgs/222.jpg\'></img><img width=\'100%\' src=\'http://localhost:8080/image/productIntroImgs/333.jpg\'></img>', '<img width=\'100%\' src=\'http://localhost:8080/image/productParaImgs/111.jpg\'></img>', '11英寸大屏 2.5K超清显示 120Hz高刷新率');
INSERT INTO `t_product` VALUES (0000000017, '小米平板5 Pro', 2499.00, 444, '14.png', b'0', b'0', 'default.jpg', 0, 5, NULL, NULL, NULL, NULL);
INSERT INTO `t_product` VALUES (0000000019, 'RedmiBook Pro 15 增强版', 5299.00, 444, '15.png', b'0', b'0', 'default.jpg', 0, 6, NULL, NULL, NULL, NULL);
INSERT INTO `t_product` VALUES (0000000020, 'Redmi G 2021', 6499.00, 1999, '16.png', b'0', b'1', '3.jpg', 2, 6, NULL, NULL, NULL, NULL);
INSERT INTO `t_product` VALUES (0000000021, 'Redmi G 2021 锐龙版', 7499.00, 2000, '17.png', b'0', b'0', 'default.jpg', 0, 6, NULL, NULL, NULL, NULL);
INSERT INTO `t_product` VALUES (0000000022, 'RedmiBook Pro 14 增强版', 4999.00, 777, '18.png', b'0', b'0', 'default.jpg', 0, 6, NULL, NULL, NULL, NULL);
INSERT INTO `t_product` VALUES (0000000023, '小米笔记本 Pro 14 锐龙版', 5499.00, 666, '19.png', b'0', b'0', 'default.jpg', 0, 7, NULL, NULL, NULL, NULL);
INSERT INTO `t_product` VALUES (0000000024, '小米笔记本 Pro 14 增强版', 5499.00, 666, '20.png', b'0', b'0', 'default.jpg', 0, 7, NULL, NULL, NULL, NULL);
INSERT INTO `t_product` VALUES (0000000026, '1', 1.00, 1, 'default.jpg', b'0', b'0', 'default.jpg', 0, 5, NULL, '<p>1</p>', '<p>1</p>', '123');
INSERT INTO `t_product` VALUES (0000000032, 'qwe', 1233.00, 123, 'default.jpg', b'0', b'0', 'default.jpg', 0, 1, NULL, '<p>123</p>', '<p>&lt;img width=\"100%\" src=\"http://localhost:8080/image/productParaImgs/1.jpg\"&gt;&lt;/img&gt;&lt;img width=\"100%\" src=\"http://localhost:8080/image/productParaImgs/2.jpg\"&gt;&lt;/img&gt;</p>', '\"qwe\"');

-- ----------------------------
-- Table structure for t_product_swiper_image
-- ----------------------------
DROP TABLE IF EXISTS `t_product_swiper_image`;
CREATE TABLE `t_product_swiper_image`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(765) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `sort` int NULL DEFAULT NULL,
  `productId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_product_swiper_image
-- ----------------------------
INSERT INTO `t_product_swiper_image` VALUES (1, '1.jpg', 1, 14);
INSERT INTO `t_product_swiper_image` VALUES (2, '2.jpg', 2, 14);
INSERT INTO `t_product_swiper_image` VALUES (3, '3.jpg', 3, 14);
INSERT INTO `t_product_swiper_image` VALUES (4, '4.jpg', 4, 14);
INSERT INTO `t_product_swiper_image` VALUES (5, '5.jpg', 5, 14);
INSERT INTO `t_product_swiper_image` VALUES (6, '6.jpg', 6, 14);
INSERT INTO `t_product_swiper_image` VALUES (7, '7.jpg', 7, 14);
INSERT INTO `t_product_swiper_image` VALUES (8, '11.jpg', 1, 4);
INSERT INTO `t_product_swiper_image` VALUES (9, '22.jpg', 2, 4);
INSERT INTO `t_product_swiper_image` VALUES (10, '33.jpg', 3, 4);
INSERT INTO `t_product_swiper_image` VALUES (11, '44.jpg', 4, 4);
INSERT INTO `t_product_swiper_image` VALUES (12, '111.jpg', 1, 16);
INSERT INTO `t_product_swiper_image` VALUES (13, '222.jpg', 2, 16);
INSERT INTO `t_product_swiper_image` VALUES (14, '333.jpg', 3, 16);

-- ----------------------------
-- Table structure for t_smalltype
-- ----------------------------
DROP TABLE IF EXISTS `t_smalltype`;
CREATE TABLE `t_smalltype`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `remark` varchar(765) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `bigTypeId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_smalltype
-- ----------------------------
INSERT INTO `t_smalltype` VALUES (1, 'Xiaomi Civi', 'Xiaomi Civi', 1);
INSERT INTO `t_smalltype` VALUES (2, 'Xiaomi 数字系列', 'Xiaomi 数字系列', 1);
INSERT INTO `t_smalltype` VALUES (3, 'Xiaomi MIX系列', 'Xiaomi MIX系列', 1);
INSERT INTO `t_smalltype` VALUES (4, 'hhh', 'jhhh', 3);
INSERT INTO `t_smalltype` VALUES (5, '小米平板', '小米平板', 2);
INSERT INTO `t_smalltype` VALUES (6, 'RedmiBook', 'RedmiBook', 2);
INSERT INTO `t_smalltype` VALUES (7, '小米笔记本', '小米笔记本', 2);
INSERT INTO `t_smalltype` VALUES (8, 'jk', 'jkk', 8);
INSERT INTO `t_smalltype` VALUES (10, '耳机', '耳机', 3);
INSERT INTO `t_smalltype` VALUES (11, '手表', '手表', 3);
INSERT INTO `t_smalltype` VALUES (12, '手环', '手环', 3);
INSERT INTO `t_smalltype` VALUES (13, '电视', '电视', 4);
INSERT INTO `t_smalltype` VALUES (14, '小米电视大师', '小米电视大师', 4);
INSERT INTO `t_smalltype` VALUES (15, '电视配件', '电视配件', 4);
INSERT INTO `t_smalltype` VALUES (16, '空调', '空调', 5);
INSERT INTO `t_smalltype` VALUES (17, '洗衣机', '洗衣机', 5);
INSERT INTO `t_smalltype` VALUES (18, '冰箱', '冰箱', 5);
INSERT INTO `t_smalltype` VALUES (19, '厨房大电', '厨房大电', 5);
INSERT INTO `t_smalltype` VALUES (20, '厨房小电', '厨房小电', 6);
INSERT INTO `t_smalltype` VALUES (21, '清洁电器', '清洁电器', 6);
INSERT INTO `t_smalltype` VALUES (22, '环境电器', '环境电器', 6);
INSERT INTO `t_smalltype` VALUES (23, '生活电器', '生活电器', 6);
INSERT INTO `t_smalltype` VALUES (24, '小爱音箱', '小爱音箱', 7);
INSERT INTO `t_smalltype` VALUES (25, '路由器', '路由器', 7);
INSERT INTO `t_smalltype` VALUES (26, '智能安防', '智能安防', 7);
INSERT INTO `t_smalltype` VALUES (27, '智能控制', '智能控制', 7);
INSERT INTO `t_smalltype` VALUES (28, '户外出行', '户外出行', 8);
INSERT INTO `t_smalltype` VALUES (29, '箱包', '箱包', 8);
INSERT INTO `t_smalltype` VALUES (30, '家具日用', '家具日用', 9);
INSERT INTO `t_smalltype` VALUES (31, '防护清洁', '防护清洁', 9);
INSERT INTO `t_smalltype` VALUES (32, '会员定制', '会员定制', 9);
INSERT INTO `t_smalltype` VALUES (33, '个人护理', '个人护理', 9);
INSERT INTO `t_smalltype` VALUES (34, '健康', '健康', 9);
INSERT INTO `t_smalltype` VALUES (35, '鞋服配饰', '鞋服配饰', 9);
INSERT INTO `t_smalltype` VALUES (36, '床品家居', '床品家居', 9);
INSERT INTO `t_smalltype` VALUES (37, '礼品周边', '礼品周边', 9);
INSERT INTO `t_smalltype` VALUES (38, '儿童用品', '儿童用品', 10);
INSERT INTO `t_smalltype` VALUES (39, 'Redmi K系列', 'K系列', 1);
INSERT INTO `t_smalltype` VALUES (40, 'Redmi Note系列', 'Note系列', 1);
INSERT INTO `t_smalltype` VALUES (41, '游戏手机', '游戏手机', 1);
INSERT INTO `t_smalltype` VALUES (44, '22', '22', 3);
INSERT INTO `t_smalltype` VALUES (45, '是', '3 是', 2);
INSERT INTO `t_smalltype` VALUES (46, 'ewew', 'ew', 3);
INSERT INTO `t_smalltype` VALUES (47, 'rrr', 'rrr', 2);

-- ----------------------------
-- Table structure for t_wxuserinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_wxuserinfo`;
CREATE TABLE `t_wxuserinfo`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `openid` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `nickName` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `avatarUrl` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_zh_0900_as_cs NULL DEFAULT NULL,
  `registerDate` datetime NULL DEFAULT NULL,
  `lastLoginDate` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_zh_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_wxuserinfo
-- ----------------------------
INSERT INTO `t_wxuserinfo` VALUES (6, 'otdFl5dCZdplh1W_csjm3NwiG7yY', '李lune', 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', '2023-07-20 20:39:27', '2023-07-25 18:29:58');
INSERT INTO `t_wxuserinfo` VALUES (7, 'dplh1W_csjm', '李lune', 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', '2023-07-20 20:39:27', '2023-07-25 18:29:58');
INSERT INTO `t_wxuserinfo` VALUES (8, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', NULL, NULL);
INSERT INTO `t_wxuserinfo` VALUES (9, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', NULL, NULL);
INSERT INTO `t_wxuserinfo` VALUES (10, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', NULL, NULL);
INSERT INTO `t_wxuserinfo` VALUES (11, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', NULL, NULL);
INSERT INTO `t_wxuserinfo` VALUES (12, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', NULL, NULL);
INSERT INTO `t_wxuserinfo` VALUES (13, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', NULL, NULL);
INSERT INTO `t_wxuserinfo` VALUES (14, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', NULL, NULL);
INSERT INTO `t_wxuserinfo` VALUES (15, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', NULL, NULL);
INSERT INTO `t_wxuserinfo` VALUES (16, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', NULL, NULL);
INSERT INTO `t_wxuserinfo` VALUES (17, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', NULL, NULL);
INSERT INTO `t_wxuserinfo` VALUES (18, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', NULL, NULL);
INSERT INTO `t_wxuserinfo` VALUES (19, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', NULL, NULL);
INSERT INTO `t_wxuserinfo` VALUES (20, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/3Zia7bxwNaS6hrP0yfS6Da4K1YSwxcicwGRicUouVCacWI7K1Lm7mogIGnbptGqfTM8UxFjLTd9nn1JdyYscFWQ4g/132', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
