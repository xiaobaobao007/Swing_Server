/*
Navicat MySQL Data Transfer

Source Server         : music
Source Server Version : 50556
Source Host           : 127.0.0.1:3306
Source Database       : game

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2019-05-11 13:39:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gamer_coefficient
-- ----------------------------
DROP TABLE IF EXISTS `gamer_coefficient`;
CREATE TABLE `gamer_coefficient` (
  `id` int(11) DEFAULT NULL,
  `imgId` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `leave` int(11) DEFAULT NULL,
  `power` int(11) DEFAULT NULL,
  `magic` int(11) DEFAULT NULL,
  `skill` int(11) DEFAULT NULL,
  `speed` int(11) DEFAULT NULL,
  `physical` int(11) DEFAULT NULL,
  `armor` int(11) DEFAULT NULL,
  `resitance` int(11) DEFAULT NULL,
  `con_phy_attack` int(11) DEFAULT NULL,
  `con_mag_attack` int(11) DEFAULT NULL,
  `con_MP` int(11) DEFAULT NULL,
  `con_blow` int(11) DEFAULT NULL,
  `con_shooting` int(11) DEFAULT NULL,
  `con_dodge` int(11) DEFAULT NULL,
  `con_speed_action` int(11) DEFAULT NULL,
  `con_HP` int(11) DEFAULT NULL,
  `con_phy_defence` int(11) DEFAULT NULL,
  `con_mag_defence` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of gamer_coefficient
-- ----------------------------
INSERT INTO `gamer_coefficient` VALUES ('1', '4', '小包包', '35', '35', '9998901', '3', '10', '20', '100', '1', '100000', '1', '1', '1', '1', '1', '1', '1', '600', '2', '1', '1', '1');
INSERT INTO `gamer_coefficient` VALUES ('2', '4', '小包包', '50', '30', '9999999', '3', '10', '20', '100', '1', '100000', '1', '1', '1', '1', '1', '1', '1', '600', '2', '1', '1', '1');
INSERT INTO `gamer_coefficient` VALUES ('3', '4', '大红红', '70', '30', '9999999', '3', '10', '20', '100', '1', '100000', '1', '1', '1', '1', '1', '1', '1', '600', '2', '1', '1', '1');

-- ----------------------------
-- Table structure for game_leave
-- ----------------------------
DROP TABLE IF EXISTS `game_leave`;
CREATE TABLE `game_leave` (
  `leave` int(10) DEFAULT NULL,
  `tip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `monster` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of game_leave
-- ----------------------------
INSERT INTO `game_leave` VALUES ('1', '血量不足时候记得补充血量哦，在商店里有众多的药品供你选择。', '2;1_1,2_1,3_1:1_1,2_1,3_1');
INSERT INTO `game_leave` VALUES ('2', '蓝量不足时候记得补充蓝量哦，在商店里有众多的药品供你选择。', '2;1_1,2_1,3_1:1_1,2_1,3_1');
INSERT INTO `game_leave` VALUES ('3', '每一关卡的地图都是不一样的，记得通关时记得浏览风景哦', '2;1_1,2_1,3_1:1_1,2_1,3_1');
INSERT INTO `game_leave` VALUES ('4', '打不过时，要有耐心哦。', '2;1_1,2_1,3_1:1_1,2_1,3_1');
INSERT INTO `game_leave` VALUES ('5', '不要怂，一起上。', '2;1_1,2_1,3_1:1_1,2_1,3_1');
INSERT INTO `game_leave` VALUES ('6', '记得和怪物拉开距离。', '2;1_1,2_1,3_1:1_1,2_1,3_1');
INSERT INTO `game_leave` VALUES ('7', '金钱很重要。', '2;1_1,2_1,3_1:1_1,2_1,3_1');
INSERT INTO `game_leave` VALUES ('8', '和朋友一起来闯关吧。', '2;1_1,2_1,3_1:1_1,2_1,3_1');
INSERT INTO `game_leave` VALUES ('9', '总共有10个关卡哦。', '2;1_1,2_1,3_1:1_1,2_1,3_1');
INSERT INTO `game_leave` VALUES ('10', '这是最后一关了，保重请珍惜。', '2;1_1,2_1,3_1:1_1,2_1,3_1');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `leave` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `id` int(11) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `note` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `power` int(11) DEFAULT NULL,
  `magic` int(11) DEFAULT NULL,
  `skill` int(11) DEFAULT NULL,
  `speed` int(11) DEFAULT NULL,
  `physical` int(11) DEFAULT NULL,
  `armor` int(11) DEFAULT NULL,
  `resistance` int(11) DEFAULT NULL,
  `effect` int(11) DEFAULT NULL,
  `effect_leave` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('沧溟法冠', '1', '0', '1', '2', '让你头发变得漂亮', '3', '3', '3', '0', '4', '3', '4', '0', '0');
INSERT INTO `goods` VALUES ('慈云冠', '1', '0', '2', '5', '让你头发变得漂亮', '4', '4', '4', '1', '5', '4', '5', '1', '1');
INSERT INTO `goods` VALUES ('帝释锦盔', '2', '0', '3', '37', '让你头发变得漂亮', '5', '5', '5', '2', '6', '5', '6', '2', '2');
INSERT INTO `goods` VALUES ('浮生', '2', '0', '4', '47', '让你头发变得漂亮', '6', '6', '6', '3', '7', '6', '7', '3', '3');
INSERT INTO `goods` VALUES ('混元斗', '3', '0', '5', '74', '让你头发变得漂亮', '7', '7', '7', '4', '8', '7', '8', '4', '4');
INSERT INTO `goods` VALUES ('剑胆盔', '4', '0', '6', '254', '让你头发变得漂亮', '8', '8', '8', '5', '9', '8', '9', '5', '5');
INSERT INTO `goods` VALUES ('雷鸣战盔', '4', '0', '7', '435', '让你头发变得漂亮', '9', '9', '9', '6', '10', '9', '10', '6', '6');
INSERT INTO `goods` VALUES ('般若法袍', '1', '2', '1', '5', '让你能飞起来', '0', '3', '4', '0', '3', '2', '0', '0', '0');
INSERT INTO `goods` VALUES ('冰蝉护甲', '2', '2', '2', '35', '让你能飞起来', '1', '4', '5', '1', '4', '3', '1', '1', '1');
INSERT INTO `goods` VALUES ('冰魄铠', '2', '2', '3', '34', '让你能飞起来', '2', '5', '6', '2', '5', '4', '2', '2', '2');
INSERT INTO `goods` VALUES ('布衣', '3', '2', '4', '62', '让你能飞起来', '3', '6', '7', '3', '6', '5', '3', '3', '3');
INSERT INTO `goods` VALUES ('苍莽甲', '4', '2', '5', '112', '让你能飞起来', '4', '7', '8', '4', '7', '6', '4', '4', '4');
INSERT INTO `goods` VALUES ('苍云', '5', '2', '6', '734', '让你能飞起来', '5', '8', '9', '5', '8', '7', '5', '5', '5');
INSERT INTO `goods` VALUES ('沧溟法袍', '5', '2', '7', '863', '让你能飞起来', '6', '9', '10', '6', '9', '8', '6', '6', '6');
INSERT INTO `goods` VALUES ('微小瓶蓝染草', '1', '9', '1', '20', '恢复你的蓝量', '0', '0', '0', '0', '0', '0', '0', '10', '0');
INSERT INTO `goods` VALUES ('小瓶蓝染草', '2', '9', '2', '59', '恢复你的蓝量', '0', '0', '0', '0', '0', '0', '0', '50', '0');
INSERT INTO `goods` VALUES ('中瓶蓝染草', '3', '9', '3', '82', '恢复你的蓝量', '0', '0', '0', '0', '0', '0', '0', '100', '0');
INSERT INTO `goods` VALUES ('大瓶蓝染草', '4', '9', '4', '112', '恢复你的蓝量', '0', '0', '0', '0', '0', '0', '0', '500', '0');
INSERT INTO `goods` VALUES ('巨大瓶蓝染草', '5', '9', '5', '1044', '恢复你的蓝量', '0', '0', '0', '0', '0', '0', '0', '1000', '0');
INSERT INTO `goods` VALUES ('六道法冠', '5', '0', '8', '2547', '让你头发变得漂亮', '1', '2', '3', '0', '3', '0', '0', '5000', '0');
INSERT INTO `goods` VALUES ('白银项圈', '1', '1', '1', '6', '不让你跑掉', '3', '5', '5', '0', '4', '1', '4', '0', '0');
INSERT INTO `goods` VALUES ('冰魄护符', '1', '1', '2', '5', '不让你跑掉', '4', '6', '6', '1', '5', '2', '5', '1', '1');
INSERT INTO `goods` VALUES ('沧溟宝玉', '1', '1', '3', '13', '不让你跑掉', '5', '7', '7', '2', '6', '3', '6', '2', '2');
INSERT INTO `goods` VALUES ('帝释元珠', '2', '1', '4', '42', '不让你跑掉', '6', '8', '8', '3', '7', '4', '7', '3', '3');
INSERT INTO `goods` VALUES ('飞电', '3', '1', '5', '68', '不让你跑掉', '7', '9', '9', '4', '8', '5', '8', '4', '4');
INSERT INTO `goods` VALUES ('骨灵项圈', '4', '1', '6', '178', '不让你跑掉', '8', '10', '10', '5', '9', '6', '9', '5', '5');
INSERT INTO `goods` VALUES ('化龙护符', '4', '1', '7', '146', '不让你跑掉', '9', '11', '11', '6', '10', '7', '10', '6', '6');
INSERT INTO `goods` VALUES ('幻灭', '5', '1', '8', '924', '不让你跑掉', '5', '3', '0', '0', '1', '5', '0', '0', '0');
INSERT INTO `goods` VALUES ('微小瓶血之怒', '1', '10', '1', '2', '恢复你的血量', '0', '0', '0', '0', '0', '0', '0', '50', '0');
INSERT INTO `goods` VALUES ('小瓶血之怒', '2', '10', '2', '63', '恢复你的血量', '0', '0', '0', '0', '0', '0', '0', '100', '0');
INSERT INTO `goods` VALUES ('中瓶血之怒', '3', '10', '3', '83', '恢复你的血量', '0', '0', '0', '0', '0', '0', '0', '200', '0');
INSERT INTO `goods` VALUES ('大瓶血之怒', '4', '10', '4', '153', '恢复你的血量', '0', '0', '0', '0', '0', '0', '0', '400', '0');
INSERT INTO `goods` VALUES ('巨大瓶血之怒', '5', '10', '5', '1374', '恢复你的血量', '0', '0', '0', '0', '0', '0', '0', '1000', '0');
INSERT INTO `goods` VALUES ('慈云锦衣', '5', '2', '8', '852', ' 我加上你两个人却并不等于我们', '4', '3', '2', '0', '0', '0', '2', '0', '0');
INSERT INTO `goods` VALUES ('飞蝶裙衣', '1', '3', '1', '2', '保暖', '4', '4', '2', '0', '1', '5', '3', '0', '0');
INSERT INTO `goods` VALUES ('光启飞衣', '1', '3', '2', '6', '保暖', '5', '5', '3', '0', '0', '3', '0', '0', '0');
INSERT INTO `goods` VALUES ('黑甲布衣', '1', '3', '3', '14', '保暖', '6', '6', '4', '1', '1', '4', '1', '1', '1');
INSERT INTO `goods` VALUES ('九天战袍', '1', '3', '4', '25', '保暖', '7', '7', '5', '2', '2', '5', '2', '2', '2');
INSERT INTO `goods` VALUES ('灵光羽衣', '2', '3', '5', '48', '保暖', '8', '8', '6', '3', '3', '6', '3', '3', '3');
INSERT INTO `goods` VALUES ('麻布衣', '3', '3', '6', '75', '保暖', '9', '9', '7', '4', '4', '7', '4', '4', '4');
INSERT INTO `goods` VALUES ('青霞衣', '4', '3', '7', '465', '保暖', '10', '10', '8', '5', '5', '8', '5', '5', '5');
INSERT INTO `goods` VALUES ('铁甲', '5', '3', '8', '2847', '保暖', '11', '11', '9', '6', '6', '9', '6', '6', '6');
INSERT INTO `goods` VALUES ('雷公钉', '1', '4', '1', '26', '锤醒你', '5', '2', '0', '0', '3', '1', '3', '0', '0');
INSERT INTO `goods` VALUES ('雷临之怒', '1', '4', '2', '27', '锤醒你', '4', '4', '3', '0', '2', '3', '0', '0', '0');
INSERT INTO `goods` VALUES ('六道修罗', '2', '4', '3', '52', '锤醒你', '0', '3', '1', '0', '3', '0', '3', '0', '0');
INSERT INTO `goods` VALUES ('冥禅杖', '2', '4', '4', '57', '锤醒你', '1', '4', '2', '1', '4', '1', '4', '1', '1');
INSERT INTO `goods` VALUES ('如梦', '3', '4', '5', '78', '锤醒你', '2', '5', '3', '2', '5', '2', '5', '2', '2');
INSERT INTO `goods` VALUES ('朔风刺', '3', '4', '6', '79', '锤醒你', '3', '6', '4', '3', '6', '3', '6', '3', '3');
INSERT INTO `goods` VALUES ('邪皇之刃', '4', '4', '7', '487', '锤醒你', '4', '7', '5', '4', '7', '4', '7', '4', '4');
INSERT INTO `goods` VALUES ('游仙羽扇', '5', '4', '8', '3299', '锤醒你', '5', '8', '6', '5', '8', '5', '8', '5', '5');
INSERT INTO `goods` VALUES ('沧溟履', '1', '5', '1', '2', '跑的更快喽', '2', '2', '4', '0', '0', '3', '4', '0', '0');
INSERT INTO `goods` VALUES ('帝释战靴', '2', '5', '2', '43', '跑的更快喽', '6', '3', '1', '0', '3', '2', '1', '0', '0');
INSERT INTO `goods` VALUES ('幻法道靴', '3', '5', '3', '71', '跑的更快喽', '7', '4', '2', '1', '4', '3', '2', '1', '1');
INSERT INTO `goods` VALUES ('幻真履', '3', '5', '4', '72', '跑的更快喽', '8', '5', '3', '2', '5', '4', '3', '2', '2');
INSERT INTO `goods` VALUES ('混元履', '4', '5', '5', '231', '跑的更快喽', '9', '6', '4', '3', '6', '5', '4', '3', '3');
INSERT INTO `goods` VALUES ('净梵履', '4', '5', '6', '362', '跑的更快喽', '10', '7', '5', '4', '7', '6', '5', '4', '4');
INSERT INTO `goods` VALUES ('空明', '5', '5', '7', '1748', '跑的更快喽', '11', '8', '6', '5', '8', '7', '6', '5', '5');
INSERT INTO `goods` VALUES ('雷鳞履', '5', '5', '8', '2374', '跑的更快喽', '12', '9', '7', '6', '9', '8', '7', '6', '6');
INSERT INTO `goods` VALUES ('沧溟戒指', '1', '6', '1', '4', '定情信物哦', '4', '4', '2', '0', '1', '0', '0', '0', '0');
INSERT INTO `goods` VALUES ('如妄', '4', '6', '10', '476', '定情信物哦', '5', '5', '3', '1', '2', '1', '1', '1', '1');
INSERT INTO `goods` VALUES ('邪皇之眼', '4', '6', '11', '492', '定情信物哦', '6', '6', '4', '2', '3', '2', '2', '2', '2');
INSERT INTO `goods` VALUES ('阳炎指轮', '5', '6', '12', '3194', '定情信物哦', '7', '7', '5', '3', '4', '3', '3', '3', '3');
INSERT INTO `goods` VALUES ('骨龙指环', '1', '6', '2', '2', '定情信物哦', '0', '3', '3', '0', '4', '1', '0', '0', '0');
INSERT INTO `goods` VALUES ('幻真戒指', '1', '6', '3', '8', '定情信物哦', '5', '5', '2', '0', '0', '0', '3', '0', '0');
INSERT INTO `goods` VALUES ('混元一体', '1', '6', '4', '10', '定情信物哦', '6', '3', '4', '0', '2', '4', '4', '0', '0');
INSERT INTO `goods` VALUES ('雷纹戒指', '2', '6', '5', '47', '定情信物哦', '7', '4', '5', '1', '3', '5', '5', '1', '1');
INSERT INTO `goods` VALUES ('流光戒指', '2', '6', '6', '46', '定情信物哦', '8', '5', '6', '2', '4', '6', '6', '2', '2');
INSERT INTO `goods` VALUES ('末日之戒', '2', '6', '7', '54', '定情信物哦', '9', '6', '7', '3', '5', '7', '7', '3', '3');
INSERT INTO `goods` VALUES ('破阵指环', '3', '6', '8', '76', '定情信物哦', '10', '7', '8', '4', '6', '8', '8', '4', '4');
INSERT INTO `goods` VALUES ('青鸾指环', '3', '6', '9', '77', '定情信物哦', '11', '8', '9', '5', '7', '9', '9', '5', '5');
INSERT INTO `goods` VALUES ('苍刃', '1', '7', '1', '7', '手腕更强壮哦', '5', '1', '3', '0', '2', '2', '0', '0', '0');
INSERT INTO `goods` VALUES ('沧溟护腕', '1', '7', '2', '8', '手腕更强壮哦', '0', '2', '0', '0', '0', '0', '3', '0', '0');
INSERT INTO `goods` VALUES ('赤炼腕轮', '2', '7', '3', '32', '手腕更强壮哦', '1', '3', '1', '1', '1', '1', '4', '1', '1');
INSERT INTO `goods` VALUES ('慈云护腕', '2', '7', '4', '33', '手腕更强壮哦', '2', '4', '2', '2', '2', '2', '5', '2', '2');
INSERT INTO `goods` VALUES ('帝释护腕', '3', '7', '5', '67', '手腕更强壮哦', '3', '5', '3', '3', '3', '3', '6', '3', '3');
INSERT INTO `goods` VALUES ('风火', '3', '7', '6', '66', '手腕更强壮哦', '4', '6', '4', '4', '4', '4', '7', '4', '4');
INSERT INTO `goods` VALUES ('混元护腕', '4', '7', '7', '274', '手腕更强壮哦', '5', '7', '5', '5', '5', '5', '8', '5', '5');
INSERT INTO `goods` VALUES ('雷光腕轮', '5', '7', '8', '2184', '手腕更强壮哦', '6', '8', '6', '6', '6', '6', '9', '6', '6');
INSERT INTO `goods` VALUES ('碧血腰带', '1', '8', '1', '7', '瘦身', '3', '3', '5', '0', '0', '0', '3', '0', '0');
INSERT INTO `goods` VALUES ('苍岚', '1', '8', '2', '4', '瘦身', '3', '4', '4', '0', '3', '2', '3', '0', '0');
INSERT INTO `goods` VALUES ('慈云腰带', '2', '8', '3', '36', '瘦身', '4', '5', '5', '1', '4', '3', '4', '1', '1');
INSERT INTO `goods` VALUES ('帝释鳞带', '2', '8', '4', '38', '瘦身', '5', '6', '6', '2', '5', '4', '5', '2', '2');
INSERT INTO `goods` VALUES ('骨龙之息', '3', '8', '5', '66', '瘦身', '6', '7', '7', '3', '6', '5', '6', '3', '3');
INSERT INTO `goods` VALUES ('幻真腰带', '3', '8', '6', '73', '瘦身', '7', '8', '8', '4', '7', '6', '7', '4', '4');
INSERT INTO `goods` VALUES ('混元丝带', '4', '8', '7', '294', '瘦身', '8', '9', '9', '5', '8', '7', '8', '5', '5');
INSERT INTO `goods` VALUES ('寂空', '5', '8', '8', '1022', '瘦身', '9', '10', '10', '6', '9', '8', '9', '6', '6');

-- ----------------------------
-- Table structure for monster_leave
-- ----------------------------
DROP TABLE IF EXISTS `monster_leave`;
CREATE TABLE `monster_leave` (
  `leave` int(11) NOT NULL,
  `imgId` int(11) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `power` int(11) DEFAULT NULL,
  `magic` int(11) DEFAULT NULL,
  `skill` int(11) DEFAULT NULL,
  `speed` int(11) DEFAULT NULL,
  `physical` int(11) DEFAULT NULL,
  `armor` int(11) DEFAULT NULL,
  `resitance` int(11) DEFAULT NULL,
  `con_phy_attack` int(11) DEFAULT NULL,
  `con_mag_attack` int(11) DEFAULT NULL,
  `con_MP` int(11) DEFAULT NULL,
  `con_blow` int(11) DEFAULT NULL,
  `con_shooting` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `con_dodge` int(11) DEFAULT NULL,
  `con_speed_action` int(11) DEFAULT NULL,
  `con_HP` int(11) DEFAULT NULL,
  `con_phy_defence` int(11) DEFAULT NULL,
  `con_mag_defence` int(11) DEFAULT NULL,
  PRIMARY KEY (`leave`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of monster_leave
-- ----------------------------
INSERT INTO `monster_leave` VALUES ('1', '1', '10', '史莱姆', '10', '20', '1', '1', '100', '1', '1', '1', '1', '1', '1', '1', '1', '2', '1', '1', '1');
INSERT INTO `monster_leave` VALUES ('2', '2', '10', '狼人精', '10', '20', '1', '1', '100', '1', '1', '1', '1', '1', '1', '1', '1', '2', '1', '1', '1');
INSERT INTO `monster_leave` VALUES ('3', '3', '10', '蘑菇精', '10', '20', '1', '1', '100', '1', '1', '1', '1', '1', '1', '1', '1', '2', '1', '1', '1');

-- ----------------------------
-- Table structure for own_goods
-- ----------------------------
DROP TABLE IF EXISTS `own_goods`;
CREATE TABLE `own_goods` (
  `id` int(11) NOT NULL,
  `goods` varchar(600) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of own_goods
-- ----------------------------
INSERT INTO `own_goods` VALUES ('1', '000202000503010401000104020303020202010701020614030501020401000301000603000200010200020500040600050200060700080700');
INSERT INTO `own_goods` VALUES ('2', '020101');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '1');
INSERT INTO `user` VALUES ('2', '2', '2');
