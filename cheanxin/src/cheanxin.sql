/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : cheanxin

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-04-27 17:34:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `enabled` tinyint(1) unsigned NOT NULL COMMENT '是否启用',
  `level` tinyint(1) unsigned NOT NULL COMMENT '部门层级',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `parent_dept_id` int(10) unsigned NOT NULL COMMENT '上级部门',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_dept_id` (`parent_dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '1234567890', '1', '1', '车安心', '0', '1492391023');
INSERT INTO `dept` VALUES ('2', '1234567890', '1', '2', '金融中心', '1', '1491544906');
INSERT INTO `dept` VALUES ('3', '1234567890', '1', '3', '华南', '2', '1492742717');
INSERT INTO `dept` VALUES ('11', '1491544536', '0', '3', '华东', '2', '1491544607');
INSERT INTO `dept` VALUES ('12', '1491544536', '0', '3', '华东', '2', '1491544651');
INSERT INTO `dept` VALUES ('13', '1491545607', '0', '3', '华东', '2', '1491730007');
INSERT INTO `dept` VALUES ('14', '1492501045', '1', '3', '华东', '2', '1492501045');

-- ----------------------------
-- Table structure for `dept_city`
-- ----------------------------
DROP TABLE IF EXISTS `dept_city`;
CREATE TABLE `dept_city` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `city_id` int(10) unsigned NOT NULL COMMENT '城市id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `creator_username` int(10) unsigned DEFAULT NULL COMMENT '创建用户',
  `dept_id` int(10) unsigned NOT NULL COMMENT '部门ID',
  `enabled` tinyint(1) unsigned NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_dept_id_city_id` (`dept_id`,`city_id`),
  KEY `idx_city_id` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept_city
-- ----------------------------
INSERT INTO `dept_city` VALUES ('1', '258', '1491470608', '273', '1', '1');
INSERT INTO `dept_city` VALUES ('2', '253', '1491470608', '273', '1', '1');
INSERT INTO `dept_city` VALUES ('17', '256', '1491536475', '273', '1', '1');
INSERT INTO `dept_city` VALUES ('18', '241', '1491545607', '273', '13', '1');
INSERT INTO `dept_city` VALUES ('19', '242', '1491545607', '273', '13', '1');
INSERT INTO `dept_city` VALUES ('20', '36', '1491545607', '273', '13', '1');
INSERT INTO `dept_city` VALUES ('21', '41', '1491545607', '273', '13', '1');
INSERT INTO `dept_city` VALUES ('22', '238', '1491545607', '273', '13', '1');
INSERT INTO `dept_city` VALUES ('23', '96', '1491545852', '273', '3', '1');
INSERT INTO `dept_city` VALUES ('24', '98', '1491545852', '273', '3', '1');
INSERT INTO `dept_city` VALUES ('25', '94', '1491545852', '273', '3', '1');
INSERT INTO `dept_city` VALUES ('26', '257', '1492308884', '273', '1', '1');
INSERT INTO `dept_city` VALUES ('27', '261', '1492308884', '273', '1', '1');
INSERT INTO `dept_city` VALUES ('28', '32', '1492501045', '273', '14', '1');
INSERT INTO `dept_city` VALUES ('29', '33', '1492501045', '273', '14', '1');
INSERT INTO `dept_city` VALUES ('30', '34', '1492501045', '273', '14', '1');
INSERT INTO `dept_city` VALUES ('31', '36', '1492501045', '273', '14', '1');
INSERT INTO `dept_city` VALUES ('32', '37', '1492501045', '273', '14', '1');
INSERT INTO `dept_city` VALUES ('33', '38', '1492501045', '273', '14', '1');
INSERT INTO `dept_city` VALUES ('34', '40', '1492501045', '273', '14', '1');
INSERT INTO `dept_city` VALUES ('35', '41', '1492501045', '273', '14', '1');
INSERT INTO `dept_city` VALUES ('36', '42', '1492501045', '273', '14', '1');
INSERT INTO `dept_city` VALUES ('37', '257', '1492502855', '273', '3', '1');
INSERT INTO `dept_city` VALUES ('38', '258', '1492502855', '273', '3', '1');
INSERT INTO `dept_city` VALUES ('39', '253', '1492502855', '273', '3', '0');

-- ----------------------------
-- Table structure for `loan`
-- ----------------------------
DROP TABLE IF EXISTS `loan`;
CREATE TABLE `loan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `applicant_address` varchar(200) DEFAULT NULL COMMENT '现居住地',
  `applicant_birth_year_month` char(10) DEFAULT NULL COMMENT '出生年月',
  `applicant_census_city_id` int(10) unsigned DEFAULT NULL COMMENT '申请人户籍所在城市',
  `applicant_certificate_file_ids` varchar(100) DEFAULT NULL COMMENT '证件图片',
  `applicant_certificate_number` varchar(20) DEFAULT NULL COMMENT '证件号码',
  `applicant_certificate_type` tinyint(2) unsigned DEFAULT NULL COMMENT '证件类型',
  `applicant_employer_address` varchar(200) DEFAULT NULL COMMENT '单位所在地',
  `applicant_employer_industry` varchar(100) DEFAULT NULL COMMENT '工作单位所属行业',
  `applicant_employer_name` varchar(200) DEFAULT NULL COMMENT '工作单位名称',
  `applicant_employer_telephone` varchar(20) DEFAULT NULL COMMENT '单位电话',
  `applicant_employer_type` varchar(50) DEFAULT NULL COMMENT '工作单位性质(国企、私企、外企等等)',
  `applicant_estate_file_ids` varchar(200) DEFAULT NULL COMMENT '房产图片',
  `applicant_first_emergency_contact` varchar(30) DEFAULT NULL COMMENT '紧急联系人1',
  `applicant_first_emergency_contact_address` varchar(200) DEFAULT NULL COMMENT '紧急联系人1地址',
  `applicant_first_emergency_contact_mobile_number` char(11) DEFAULT NULL COMMENT '紧急联系人1手机号码',
  `applicant_first_emergency_contact_relationship` varchar(10) DEFAULT NULL COMMENT '紧急联系人1与申请人的关系',
  `applicant_gender` tinyint(1) unsigned DEFAULT NULL COMMENT '性别',
  `applicant_income_file_ids` varchar(500) DEFAULT NULL COMMENT '收入证明图片',
  `applicant_income_per_month` int(10) DEFAULT NULL COMMENT '月收入',
  `applicant_job_title` varchar(50) DEFAULT NULL COMMENT '申请人职称',
  `applicant_loan_price` int(10) unsigned DEFAULT NULL COMMENT '客户想要的贷款金额',
  `applicant_loan_rate` tinyint(2) unsigned DEFAULT NULL COMMENT '客户想要的贷款比率',
  `applicant_marriage` tinyint(1) unsigned DEFAULT NULL COMMENT '婚姻状态',
  `applicant_mobile_number` char(11) DEFAULT NULL COMMENT '申请人手机号',
  `applicant_name` varchar(30) DEFAULT NULL COMMENT '贷款申请人姓名',
  `applicant_occupation` varchar(50) DEFAULT NULL COMMENT '申请人职业',
  `applicant_other_file_ids` varchar(500) DEFAULT NULL COMMENT '其他图片',
  `applicant_position` varchar(50) DEFAULT NULL COMMENT '申请人职务',
  `applicant_post` varchar(50) DEFAULT NULL COMMENT '申请人工作岗位',
  `applicant_post_address` varchar(200) DEFAULT NULL COMMENT '邮寄地址',
  `applicant_post_code` char(6) DEFAULT NULL COMMENT '邮编',
  `applicant_qualification` varchar(30) DEFAULT NULL COMMENT '学历',
  `applicant_qualification_file_ids` varchar(200) DEFAULT NULL COMMENT '学历证明图片',
  `applicant_second_emergency_contact` varchar(30) DEFAULT NULL COMMENT '紧急联系人2',
  `applicant_second_emergency_contact_address` varchar(200) DEFAULT NULL COMMENT '紧急联系人2地址',
  `applicant_second_emergency_contact_mobile_number` char(11) DEFAULT NULL COMMENT '紧急联系人2手机号码',
  `applicant_second_emergency_contact_relationship` varchar(10) DEFAULT NULL COMMENT '紧急联系人1与申请人的关系',
  `applicant_telephone` varchar(20) DEFAULT NULL COMMENT '申请人电话',
  `applicant_vehicle_file_ids` varchar(200) DEFAULT NULL COMMENT '车辆图片',
  `applicant_work_years` varchar(20) DEFAULT NULL COMMENT '申请人工作年限',
  `application_pic_url` varchar(1000) DEFAULT NULL COMMENT '申请图片列表',
  `co_applicant_address` varchar(200) DEFAULT NULL COMMENT '共同申请人现居住地',
  `co_applicant_census_city_id` int(10) unsigned DEFAULT NULL COMMENT '共同申请人户籍所在城市',
  `co_applicant_certificate_file_ids` varchar(100) DEFAULT NULL COMMENT '共同申请人证件图片',
  `co_applicant_certificate_number` varchar(20) DEFAULT NULL COMMENT '共同申请人证件号码',
  `co_applicant_employer_address` varchar(200) DEFAULT NULL COMMENT '共同申请人单位所在地',
  `co_applicant_employer_name` varchar(200) DEFAULT NULL COMMENT '共同申请人工作单位名称',
  `co_applicant_employer_telephone` varchar(20) DEFAULT NULL COMMENT '共同申请人单位电话',
  `co_applicant_estate_file_ids` varchar(200) DEFAULT NULL COMMENT '共同申请人房产图片',
  `co_applicant_income_file_ids` varchar(500) DEFAULT NULL COMMENT '共同申请人收入证明图片',
  `co_applicant_income_per_month` int(10) DEFAULT NULL COMMENT '共同申请人月收入',
  `co_applicant_mobile_number` char(11) DEFAULT NULL COMMENT '共同申请人手机号',
  `co_applicant_name` varchar(200) DEFAULT NULL COMMENT '共同申请人姓名',
  `co_applicant_other_file_ids` varchar(500) DEFAULT NULL COMMENT '共同申请人其他图片',
  `co_applicant_qualification` varchar(30) DEFAULT NULL COMMENT '共同申请人学历',
  `co_applicant_relationship` varchar(10) DEFAULT NULL COMMENT '共同申请人与申请人的关系',
  `co_applicant_telephone` varchar(20) DEFAULT NULL COMMENT '共同申请人电话',
  `contract_created_time` int(10) unsigned DEFAULT NULL COMMENT '合同签约日期',
  `contract_file_ids` varchar(500) DEFAULT NULL COMMENT '合同照片',
  `contract_number` varchar(20) DEFAULT NULL COMMENT '合同编号',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `creator_username` varchar(20) DEFAULT NULL COMMENT '创建贷款草稿用户',
  `guarantor_census_city_id` int(10) unsigned DEFAULT NULL COMMENT '担保人户籍所在城市',
  `guarantor_certificate_file_ids` varchar(100) DEFAULT NULL COMMENT '证件图片',
  `guarantor_certificate_number` varchar(20) DEFAULT NULL COMMENT '担保人证件号码',
  `guarantor_estate_file_ids` varchar(200) DEFAULT NULL COMMENT '房产图片',
  `guarantor_income_file_ids` varchar(500) DEFAULT NULL COMMENT '收入证明图片',
  `guarantor_income_per_month` int(10) DEFAULT NULL COMMENT '担保人月收入',
  `guarantor_mobile_number` char(11) DEFAULT NULL COMMENT '担保人手机号',
  `guarantor_name` varchar(20) DEFAULT NULL COMMENT '担保人姓名',
  `guarantor_other_file_ids` varchar(500) DEFAULT NULL COMMENT '其他图片',
  `guarantor_real_estate_own_type` varchar(20) DEFAULT NULL COMMENT '担保人房产权属',
  `guarantor_relationship` varchar(10) DEFAULT NULL COMMENT '担保人与申请人的关系',
  `loan_terms` smallint(3) unsigned DEFAULT NULL COMMENT '贷款期数',
  `materials_file_ids` varchar(500) DEFAULT NULL COMMENT '补充材料照片',
  `materials_remark` varchar(255) DEFAULT NULL COMMENT '补充材料重新申请理由',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `mortgage_created_time` int(10) unsigned DEFAULT NULL COMMENT '抵押日期',
  `mortgage_file_ids` varchar(500) DEFAULT NULL COMMENT '抵押材料',
  `product_id` int(10) unsigned DEFAULT NULL COMMENT '产品id',
  `release_amount` int(10) unsigned DEFAULT NULL COMMENT '放款金额',
  `release_created_time` int(10) unsigned DEFAULT NULL COMMENT '放款日期',
  `release_file_ids` varchar(500) DEFAULT NULL COMMENT '放款材料',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `review_loan_price` int(10) unsigned DEFAULT NULL COMMENT '审批的贷款金额',
  `review_loan_rate` tinyint(2) unsigned DEFAULT NULL COMMENT '审批核准的最高贷款比率',
  `source_channel` tinyint(2) unsigned DEFAULT NULL COMMENT '来源渠道',
  `source_city_id` int(10) unsigned DEFAULT NULL COMMENT '城市id',
  `source_financial_commissioner` varchar(30) DEFAULT NULL COMMENT '跟单金融专员',
  `source_person_name` varchar(30) DEFAULT NULL COMMENT '来源联系人姓名',
  `source_person_tel` varchar(20) DEFAULT NULL COMMENT '来源联系人电话',
  `source_receiver` varchar(30) DEFAULT NULL COMMENT '收单员',
  `status` tinyint(2) unsigned DEFAULT NULL COMMENT '贷款状态',
  `transfer_card_number` varchar(20) DEFAULT NULL COMMENT '过户后车牌号',
  `transfer_created_time` int(10) unsigned DEFAULT NULL COMMENT '过户日期',
  `transfer_file_ids` varchar(500) DEFAULT NULL COMMENT '过户材料',
  `transfergpsfile_ids` varchar(500) DEFAULT NULL COMMENT '过户GPS安装材料',
  `transfer_insurance_file_ids` varchar(500) DEFAULT NULL COMMENT '过户保险合同',
  `vehicle_brand` int(10) unsigned DEFAULT NULL COMMENT '品牌',
  `vehicle_deal_price` int(10) unsigned DEFAULT NULL COMMENT '车辆成交价格',
  `vehicle_emission` varchar(10) DEFAULT NULL COMMENT '排放标准',
  `vehicle_file_ids` varchar(2000) DEFAULT NULL COMMENT '车辆图片',
  `vehicle_kilometers` decimal(7,4) unsigned DEFAULT NULL COMMENT '公里数',
  `vehicle_license_file_ids` varchar(100) DEFAULT NULL COMMENT '机动车行驶证图片',
  `vehicle_manufacturers` varchar(20) DEFAULT NULL COMMENT '厂家',
  `vehicle_predict_price` int(10) unsigned DEFAULT NULL COMMENT '车辆评估价格',
  `vehicle_production_year_month` varchar(10) DEFAULT NULL COMMENT '生产年月',
  `vehicle_registration_certificate_file_ids` varchar(100) DEFAULT NULL COMMENT '车辆登记证书图片',
  `vehicle_registration_year_month` varchar(10) DEFAULT NULL COMMENT '首次登记年月',
  `vehicle_series` int(10) unsigned DEFAULT NULL COMMENT '车系',
  `vehicle_type` int(10) unsigned DEFAULT NULL COMMENT '车型',
  `vehicle_utility_type` varchar(20) DEFAULT NULL COMMENT '使用性质',
  `vehicle_vin` char(17) DEFAULT NULL COMMENT '车辆vin码',
  `product_loan_monthly_interest_rate` decimal(5,4) unsigned DEFAULT NULL COMMENT '贷款月利率',
  `review_loan_terms` smallint(3) unsigned DEFAULT NULL COMMENT '审批的贷款期数',
  `vehicle_desc` varchar(100) DEFAULT NULL COMMENT '车型',
  `release_status` tinyint(1) unsigned DEFAULT '0' COMMENT '放款状态',
  PRIMARY KEY (`id`),
  KEY `idx_creator_username` (`creator_username`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_applicant_name` (`applicant_name`),
  KEY `idx_source_city_id` (`source_city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loan
-- ----------------------------
INSERT INTO `loan` VALUES ('1', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', null, '40000', '5', '0', '13075881402', '地方法', null, '', null, null, null, null, null, '', null, null, null, null, null, '', null, 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', null, null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, null, null, null, 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, null, '', null, null, null, '1491436800', 'gp1/M00/00/04/rBABDlj0VfiAHbrHAABGL6VEcTU969.jpg', '4564789874', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, null, '', null, null, '12', null, null, '1492484329', '1492473600', 'gp1/M00/00/05/rBABDlj1Z2WAM0xjAABGL6VEcTU295.jpg', '3', '40000', '1434567892', '123.jpg,23.jpg,533.jpg', '#release!#', '18013', '6', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '31', '闽AD001D', '1491436800', 'gp1/M00/00/05/rBABDlj0i-yAGiHCAABKuR8W_ac891.jpg', 'gp1/M00/00/05/rBABDlj0i_OADb1rAABKuR8W_ac338.jpg', 'gp1/M00/00/05/rBABDlj0i_WAOxWYAABGL6VEcTU153.jpg', '1', '80000', '国4', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '30023', '2004', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-13', '2', '3', '非营运', '45645645647897895', '0.8700', '60', null, '0');
INSERT INTO `loan` VALUES ('2', null, null, null, 'gp1/M00/00/04/rBABDljyveSAKqeDAABGL6VEcTU157.jpg', '350100198812120901', '0', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyvemAf8CCAABxX89qvNE542.jpg', '9000', '', '24000', '4', '0', '13222345443', '建军节', '', '', '', '', '', null, '', 'gp1/M00/00/04/rBABDljyveeAUuI3AABoJvQ-m-0715.jpg', null, '', null, '', '', 'gp1/M00/00/04/rBABDljyveuAWLnKAABGL6VEcTU431.jpg', '', 'gp1/M00/00/04/rBABDljyviGAaLm0AABGL6VEcTU723.jpg', '', null, 'gp1/M00/00/04/rBABDljyve-AYb85AABKuR8W_ac675.jpg', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyvfSAGJ5AAABGL6VEcTU732.jpg', null, null, '', '', '', '', '', null, '', '', '1492303425', '273', null, 'gp1/M00/00/04/rBABDljyvfaADgsBAABxX89qvNE624.jpg', null, '', '', null, null, '', '', '', '', '6', 'gp1/M00/00/04/rBABDlj0MGaAeCSkAABGL6VEcTU804.jpg', 'ADMIN try', '1492412713', null, '', '5', null, null, '', '无', '13399', '6', '2', '253', '管理员', '何足道', '13212345678', 'Tang Wenjie', '16', '', null, '', '', '', '1', '60000', '欧5', 'gp1/M00/00/04/rBABDljyvhOAJPzNAABoJvQ-m-0488.jpg', '2.3000', 'gp1/M00/00/04/rBABDljyvhGAdAtnAABoJvQ-m-0547.jpg', '上汽', '22332', '2004', 'gp1/M00/00/04/rBABDljyvg6AfhruAABoJvQ-m-0786.jpg', '2017-04-05', '2', '3', '营转非', '45263254897895623', '0.7900', '60', null, '0');
INSERT INTO `loan` VALUES ('3', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492308533', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '0', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('4', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1491264000', 'gp1/M00/00/05/rBABDlj0gq-AeGPXAABoJvQ-m-0001.jpg', '33333333', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492419276', null, '', '6', null, null, '', '', '10001', '5', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '37', '', null, '', '', '', '1', '80000', '国3带OBD', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '20003', '2004', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-08', '2', '3', '教练车', '45645645647897895', '0.8000', '12', null, '0');
INSERT INTO `loan` VALUES ('5', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1492041600', 'gp1/M00/00/04/rBABDlj0VheAbN5KAABoJvQ-m-0996.jpg', '65487895235478', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1493275112', null, '', '3', null, '1492128000', 'gp1/M00/00/07/rBABDlkBoSCAbOVNAACjdwlRxBU719.jpg', '#release!#', '17888', '7', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '24', '02254', '1468800', 'gp1/M00/00/05/rBABDlj0juuAKLTsAABxX89qvNE706.jpg', 'gp1/M00/00/05/rBABDlj0ju6AVBGVAABGL6VEcTU060.jpg', 'gp1/M00/00/05/rBABDlj0jvCAA5ccAABKuR8W_ac756.jpg,gp1/M00/00/05/rBABDlj1mzuAJm71AABGL6VEcTU730.jpg,gp1/M00/00/05/rBABDlj1m0SAUjAZAABGL6VEcTU987.jpg', '1', '80000', '欧1', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '25555', '2006', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-03-29', '2', '3', '营运', '45645645647897895', '0.8700', '12', null, '3');
INSERT INTO `loan` VALUES ('6', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', 'gp1/M00/00/04/rBABDlj0MnKAY649AABoJvQ-m-0911.jpg', '66666', '1492399575', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '11', '', null, '', '', '', '1', '80000', '国3带OBD', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '35465', '2004', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-01-18', '2', '3', '非营运', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('7', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1491955200', 'gp1/M00/00/04/rBABDlj0Vi2AKI4SAABxX89qvNE567.jpg', '4562358789556', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1493274922', '1493164800', 'gp1/M00/00/05/rBABDlj1f9-AdX29AABKuR8W_ac884.jpg,gp1/M00/00/05/rBABDlj1f-CAKfgEAABoJvQ-m-0906.jpg', '3', '40000', '1491609600', 'gp1/M00/00/05/rBABDlj1Z1iADh0hAABxX89qvNE594.jpg', '', '15000', '5', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '31', '56487', '1491436800', 'gp1/M00/00/05/rBABDlj0jv-ALe2IAABGL6VEcTU356.jpg', 'gp1/M00/00/05/rBABDlj0jwSAAsDrAABKuR8W_ac724.jpg', 'gp1/M00/00/05/rBABDlj0jwaAQ9KZAABxX89qvNE615.jpg', '1', '80000', '国3', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '30000', '2012', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-06', '2', '3', '教练车', '45645645647897895', '0.8700', '12', null, '0');
INSERT INTO `loan` VALUES ('8', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492391233', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '9', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('9', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', 'gp1/M00/00/04/rBABDlj0NiGAeXKoAABKuR8W_ac251.jpg', '要再次拒绝', '1492399704', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '11', '', null, '', '', '', '1', '80000', '国2', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '50000', '2002', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-06', '2', '3', '营运', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('10', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1492128000', 'gp1/M00/00/04/rBABDlj0VkCAE7ENAABGL6VEcTU909.jpg', '32545658778584', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492490838', '1468800', 'gp1/M00/00/05/rBABDlj1gXuAWowXAABxX89qvNE612.jpg', '6', '40000', '1491523200', 'gp1/M00/00/05/rBABDlj1mjmAb71EAABoJvQ-m-0318.jpg', '', '10000', '5', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '36', '455523', '1468800', 'gp1/M00/00/05/rBABDlj1gWiAM1HXAABoJvQ-m-0572.jpg', 'gp1/M00/00/05/rBABDlj1gW2AWRM-AABGL6VEcTU903.jpg', 'gp1/M00/00/05/rBABDlj1gXKAD-TTAABoJvQ-m-0967.jpg,gp1/M00/00/05/rBABDlj1igGAVYF8AABGL6VEcTU374.jpg', '1', '80000', '国5', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '20000', '2016', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-13', '2', '3', '非营运', '45645645647897895', '0.8000', '60', null, '0');
INSERT INTO `loan` VALUES ('11', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1491350400', 'gp1/M00/00/04/rBABDlj0VlKAFsLjAABoJvQ-m-0410.jpg,gp1/M00/00/04/rBABDlj0VkyAXZ2uAABoJvQ-m-0076.jpg', '111111111', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492484936', null, '', '6', null, null, '', '', '22833', '5', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '27', '', null, '', '', '', '1', '80000', '欧2', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '45667', '2006', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-21', '2', '3', '非营运', '45645645647897895', '0.8000', '12', null, '0');
INSERT INTO `loan` VALUES ('12', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1492214400', 'gp1/M00/00/04/rBABDlj0Vm-ABdc3AABKuR8W_ac889.jpg', '32222', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492485045', null, '', '6', null, null, '', '', '35000', '7', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '27', '', null, '', '', '', '1', '80000', '欧4', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '50001', '2002', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-13', '2', '3', '非营运', '45645645647897895', '0.8000', '60', null, '0');
INSERT INTO `loan` VALUES ('13', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492391274', null, '', '6', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '3', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8000', null, null, '0');
INSERT INTO `loan` VALUES ('14', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492400469', null, '', '6', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '15', '', null, '', '', '', '1', '80000', '欧3', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '25469', '2006', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-21', '2', '3', '非营运', '45645645647897895', '0.8000', null, null, '0');
INSERT INTO `loan` VALUES ('15', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492396606', null, '', '6', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '5', '', null, '', '', '', '1', '80000', '国3', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '74589', '2010', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-28', '2', '3', '非营运', '45645645647897895', '0.8000', null, null, '0');
INSERT INTO `loan` VALUES ('16', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492396364', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '5', '', null, '', '', '', '1', '80000', '国3', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '20000', '2010', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-22', '2', '3', '营运', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('17', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492262578', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '1', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('18', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1491868800', 'gp1/M00/00/05/rBABDlj0VwGAXHaPAABxX89qvNE152.jpg', '4', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492485271', '1493337600', 'gp1/M00/00/05/rBABDlj1g76AS6KAAABKuR8W_ac174.jpg,gp1/M00/00/05/rBABDlj1g8CAFcYbAABGL6VEcTU080.jpg', '6', null, null, '', '', '40000', '9', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '30', '45665', '1491436800', 'gp1/M00/00/05/rBABDlj1g7SAVHSgAABoJvQ-m-0629.jpg', 'gp1/M00/00/05/rBABDlj1g7aAUbNpAABoJvQ-m-0880.jpg', 'gp1/M00/00/05/rBABDlj1g7iARrKMAABGL6VEcTU273.jpg,gp1/M00/00/05/rBABDlj1g7qAOwQ2AABxX89qvNE844.jpg', '1', '80000', '国4', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '50000', '2005', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-15', '2', '3', '非营运', '45645645647897895', '0.8000', '24', null, '0');
INSERT INTO `loan` VALUES ('19', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', 'gp1/M00/00/05/rBABDlj0gm6Ab9FoAABoJvQ-m-0019.jpg', '新的复审', '1492419247', null, '', '3', null, null, '', '', '15274', '6', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '6', '', null, '', '', '', '1', '80000', '国4', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '25457', '2006', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-08', '2', '3', '非营运', '45645645647897895', '0.8700', '12', null, '0');
INSERT INTO `loan` VALUES ('20', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1493424000', 'gp1/M00/00/04/rBABDlj0VmSAbwl3AABKuR8W_ac981.jpg', '55687445555', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1493274864', '1491436800', 'gp1/M00/00/07/rBABDlkBkF-AICdzAACjdwlRxBU505.jpg', '3', '40000', '1434567892', '123.jpg,23.jpg,533.jpg', '', '40000', '7', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '31', '542654', '1492041600', 'gp1/M00/00/05/rBABDlj1ZFKAAOEtAABoJvQ-m-0287.jpg', 'gp1/M00/00/05/rBABDlj1ZFeAacq_AABoJvQ-m-0969.jpg', 'gp1/M00/00/05/rBABDlj1ZFmAMd54AABxX89qvNE897.jpg', '1', '80000', '国3带OBD', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '69999', '2007', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-05', '2', '3', '非营运', '45645645647897895', '0.8700', '12', null, '0');
INSERT INTO `loan` VALUES ('21', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1492128000', 'gp1/M00/00/05/rBABDlj0VteATTVwAABxX89qvNE980.jpg', '665544888', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492485964', '1492732800', 'gp1/M00/00/05/rBABDlj1hYWAPBp6AABGL6VEcTU828.jpg', '6', null, null, '', '', '40000', '8', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '30', '763786', '1491436800', 'gp1/M00/00/05/rBABDlj1hXuAdMEkAABxX89qvNE215.jpg', 'gp1/M00/00/05/rBABDlj1hX6ADtsAAABKuR8W_ac455.jpg', 'gp1/M00/00/05/rBABDlj1hYCAIlUxAABxX89qvNE784.jpg', '1', '80000', '国5', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '50002', '2016', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-09', '2', '3', '非营运', '45645645647897895', '0.8000', '48', null, '0');
INSERT INTO `loan` VALUES ('22', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492396694', null, '', '6', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '5', '', null, '', '', '', '1', '80000', '欧2', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '32222', '2007', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-08', '2', '3', '非营运', '45645645647897895', '0.8000', null, null, '0');
INSERT INTO `loan` VALUES ('23', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492414520', null, '', '6', null, null, '', '', '24999', '5', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '16', '', null, '', '', '', '1', '80000', '国3带OBD', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '49999', '2004', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-21', '2', '3', '非营运', '45645645647897895', '0.8000', '24', null, '0');
INSERT INTO `loan` VALUES ('24', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '48000', '6', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492412800', null, '', '3', null, null, '', '', '26666', '4', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '12', '', null, '', '', '', '1', '80000', '国3带OBD', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '66666', '2006', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-15', '2', '3', '营转非', '45645645647897895', '0.8700', '60', null, '0');
INSERT INTO `loan` VALUES ('25', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1491868800', 'gp1/M00/00/05/rBABDlj0ggiAO5IKAABxX89qvNE258.jpg', '5569844', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492419110', null, '', '6', null, null, '', '', '20518', '8', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '37', '', null, '', '', '', '1', '80000', '欧2', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '25648', '2006', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2016-08-10', '2', '3', '营运', '45645645647897895', '0.8000', '24', null, '0');
INSERT INTO `loan` VALUES ('26', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492391364', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '3', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('27', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492391532', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '3', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('28', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492397189', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '5', '', null, '', '', '', '1', '80000', '国3带OBD', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '20', '2005', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-15', '2', '3', '营转非', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('29', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1492646400', 'gp1/M00/00/05/rBABDlj0VqKAIw55AABKuR8W_ac732.jpg', '3545545555', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492477474', null, '', '3', null, null, '', '', '21928', '6', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '20', '', null, '', '', '', '1', '80000', '国3', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '36548', '2004', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-22', '2', '3', '非营运', '45645645647897895', '0.8700', '60', null, '0');
INSERT INTO `loan` VALUES ('30', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1491264000', 'gp1/M00/00/05/rBABDlj0VzyAFnAkAABoJvQ-m-0044.jpg', '65598845', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492477586', null, '', '3', null, null, '', '', '33893', '6', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '20', '', null, '', '', '', '1', '80000', '欧6', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '56489', '2008', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2016-11-16', '2', '3', '非营运', '45645645647897895', '0.8700', '12', null, '0');
INSERT INTO `loan` VALUES ('31', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1492732800', 'gp1/M00/00/04/rBABDlj0VpWADrGmAABGL6VEcTU595.jpg', '33255455', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492491331', '1468800', 'gp1/M00/00/05/rBABDlj1haOASvoUAABKuR8W_ac760.jpg', '6', null, null, '', '', '30003', '5', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '30', '56622', '1468800', 'gp1/M00/00/05/rBABDlj1hZCAIEjmAABKuR8W_ac009.jpg,gp1/M00/00/05/rBABDlj1imWACkJ_AABGL6VEcTU215.jpg', 'gp1/M00/00/05/rBABDlj1hZuAQaPyAABxX89qvNE824.jpg', 'gp1/M00/00/05/rBABDlj1hZ6AamARAABKuR8W_ac101.jpg', '1', '80000', '欧3', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '60007', '2007', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-15', '2', '3', '非营运', '45645645647897895', '0.8000', '18', null, '0');
INSERT INTO `loan` VALUES ('32', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492262578', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '1', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('33', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1487116800', 'gp1/M00/00/04/rBABDlj0VoiAem_1AABGL6VEcTU442.jpg', '55522', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492491373', '1641600', 'gp1/M00/00/05/rBABDlj1m-OAbAZWAABKuR8W_ac327.jpg,gp1/M00/00/05/rBABDlj1nFCAU5nQAABoJvQ-m-0491.jpg,gp1/M00/00/05/rBABDlj1nFGACxUmAABKuR8W_ac914.jpg', '6', null, null, '', '', '40000', '6', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '33', '65548', '1468800', 'gp1/M00/00/05/rBABDlj1m9qAdRBIAABxX89qvNE829.jpg', 'gp1/M00/00/05/rBABDlj1m9yAEUKkAABKuR8W_ac074.jpg', 'gp1/M00/00/05/rBABDlj1m96AQiwVAABGL6VEcTU308.jpg', '1', '80000', '欧3', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '78545', '2004', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-22', '2', '3', '', '45645645647897895', '0.8000', '24', null, '0');
INSERT INTO `loan` VALUES ('34', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1492128000', 'gp1/M00/00/04/rBABDlj0VneABBTwAABxX89qvNE339.jpg', '1112233', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492477535', null, '', '3', null, null, '', '', '28000', '4', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '19', '', null, '', '', '', '1', '80000', '欧2', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '70002', '2010', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-22', '2', '3', '教练车', '45645645647897895', '0.8700', '60', null, '0');
INSERT INTO `loan` VALUES ('35', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1492646400', 'gp1/M00/00/05/rBABDlj0VrCAJUOoAABGL6VEcTU083.jpg', '456678999', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1493275048', '1492041600', 'gp1/M00/00/07/rBABDlkBkYKABoGzAACjdwlRxBU594.jpg', '3', null, null, '', '', '40000', '5', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '38', '654897', '1492732800', 'gp1/M00/00/05/rBABDlj1Z0KAWijqAABKuR8W_ac476.jpg', 'gp1/M00/00/05/rBABDlj1Z0OARI6XAABGL6VEcTU122.jpg', 'gp1/M00/00/05/rBABDlj1Z0WAIcldAABoJvQ-m-0349.jpg', '1', '80000', '欧2', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '80000', '2006', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2011-04-15', '2', '3', '非营运', '45645645647897895', '0.8700', '12', null, '1');
INSERT INTO `loan` VALUES ('36', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492394352', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '3', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('37', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492397037', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '5', '', null, '', '', '', '1', '80000', '欧2', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '20222', '2006', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-13', '2', '3', '营运', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('38', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1491955200', 'gp1/M00/00/05/rBABDlj0VyyAerZbAABGL6VEcTU941.jpg', '56655774', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492491438', '1491523200', 'gp1/M00/00/05/rBABDlj1nJCAOKfMAABKuR8W_ac081.jpg', '6', null, null, '', '', '39148', '6', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '29', '222222', '1491004800', 'gp1/M00/00/05/rBABDlj1nIiAXEeDAABxX89qvNE156.jpg', 'gp1/M00/00/05/rBABDlj1nIqAYpJxAABoJvQ-m-0672.jpg', 'gp1/M00/00/05/rBABDlj1nIyAegpQAABxX89qvNE001.jpg', '1', '80000', '国5', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '65248', '2016', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-15', '2', '3', '非营运', '45645645647897895', '0.8000', '36', null, '0');
INSERT INTO `loan` VALUES ('39', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1492560000', 'gp1/M00/00/05/rBABDlj0VuSACG0AAABxX89qvNE459.jpg', '64888555', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492484215', null, '', '3', null, null, '', '', '40000', '6', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '23', '52568', '1491609600', 'gp1/M00/00/05/rBABDlj1bv2ANJc0AABxX89qvNE823.jpg,gp1/M00/00/05/rBABDlj1bv-ASZvBAABGL6VEcTU944.jpg', 'gp1/M00/00/05/rBABDlj1bwmAIFHWAABoJvQ-m-0048.jpg,gp1/M00/00/05/rBABDlj1bweAQM76AABxX89qvNE926.jpg', 'gp1/M00/00/05/rBABDlj1bwOARJpkAABxX89qvNE473.jpg,gp1/M00/00/05/rBABDlj1bwWAb0QkAABGL6VEcTU863.jpg', '1', '80000', '国4', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '77777', '2008', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-15', '2', '3', '营转非', '45645645647897895', '0.8700', '12', null, '0');
INSERT INTO `loan` VALUES ('40', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1490745600', 'gp1/M00/00/05/rBABDlj0gjSAaSYaAABoJvQ-m-0631.jpg', '00000', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492419171', null, '', '3', null, null, '', '', '34343', '5', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '8', '', null, '', '', '', '1', '80000', '国3带OBD', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '68686', '2006', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-14', '2', '3', '教练车', '45645645647897895', '0.8700', '12', null, '0');
INSERT INTO `loan` VALUES ('41', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1491609600', 'gp1/M00/00/05/rBABDlj0VvOADAM1AABxX89qvNE115.jpg', '0', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492419174', null, '', '6', null, null, '', '', '24840', '7', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '25', '', null, '', '', '', '1', '80000', '国3带OBD', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '35487', '2007', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-15', '2', '3', '教练车', '45645645647897895', '0.8000', '36', null, '0');
INSERT INTO `loan` VALUES ('42', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492414613', null, '', '3', null, null, '', '', '26617', '4', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '16', '', null, '', '', '', '1', '80000', '国3带OBD', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '66544', '2007', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-12', '2', '3', '营运', '45645645647897895', '0.8700', '12', null, '0');
INSERT INTO `loan` VALUES ('43', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492397059', null, '', '6', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '5', '', null, '', '', '', '1', '80000', '欧5', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '65245', '2014', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-21', '2', '3', '教练车', '45645645647897895', '0.8000', null, null, '0');
INSERT INTO `loan` VALUES ('44', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492407236', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '4', '', null, '', '', '', '1', '80000', '国3带OBD', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '79875', '2012', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-05', '2', '3', '非营运', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('45', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492419091', null, '', '6', null, null, '', '', '32744', '5', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '6', '', null, '', '', '', '1', '80000', '欧3', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '65489', '2017', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-29', '2', '3', '非营运', '45645645647897895', '0.8000', '48', null, '0');
INSERT INTO `loan` VALUES ('46', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492394404', null, '', '6', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '3', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8000', null, null, '0');
INSERT INTO `loan` VALUES ('47', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492408109', null, '', '3', null, null, '', '', '30001', '5', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '17', '', null, '', '', '', '1', '80000', '国3', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '60003', '2006', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-08', '2', '3', '非营运', '45645645647897895', '0.8700', '60', null, '0');
INSERT INTO `loan` VALUES ('48', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492414722', null, '', '6', null, null, '', '', '6022', '5', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '17', '', null, '', '', '', '1', '80000', '欧6', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '12045', '2016', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-05-05', '2', '3', '教练车', '45645645647897895', '0.8000', '18', null, '0');
INSERT INTO `loan` VALUES ('49', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492394568', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '2', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('50', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', '1491955200', 'gp1/M00/00/05/rBABDlj0gkCAQeHEAABGL6VEcTU270.jpg', '66555', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492419165', null, '', '6', null, null, '', '', '40000', '7', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '37', '', null, '', '', '', '1', '80000', '国3', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '66698', '2007', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-21', '2', '3', '非营运', '45645645647897895', '0.8000', '48', null, '0');
INSERT INTO `loan` VALUES ('51', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492407364', null, '', '3', null, null, '', '', '33333', '6', '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '6', '', null, '', '', '', '1', '80000', '欧6', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '55555', '2016', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-02', '2', '3', '非营运', '45645645647897895', '0.8700', '60', null, '0');
INSERT INTO `loan` VALUES ('52', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492394429', null, '', '6', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '3', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8000', null, null, '0');
INSERT INTO `loan` VALUES ('53', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492407329', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '4', '', null, '', '', '', '1', '80000', '欧6', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', '45678', '2015', 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', '2017-04-04', '2', '3', '非营运', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('54', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492394439', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '3', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('55', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492262578', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '1', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('56', null, null, null, 'gp1/M00/00/04/rBABDljyHiWAS5b5AABoJvQ-m-0032.jpg', '350122198912035912', '1', null, null, null, null, null, '', null, null, null, null, null, 'gp1/M00/00/04/rBABDljyHkiAFu_WAABGL6VEcTU380.jpg', '8000', '', '40000', '5', '0', '13075881402', '地方法', '', '', '', '', '', null, '', '', null, '', null, '', '', '', '', 'gp1/M00/00/04/rBABDljyHfSAcVZYAABxX89qvNE541.jpg', '', null, 'gp1/M00/00/04/rBABDljyHiCAJQjBAABoJvQ-m-0360.jpg', null, '', '', '', 'gp1/M00/00/04/rBABDljyHj2AUhQcAABoJvQ-m-0553.jpg', '', null, null, '', '', '', '', '', null, '', '', '1492262578', '273', null, 'gp1/M00/00/04/rBABDljyHhyAAF20AABKuR8W_ac321.jpg', null, 'gp1/M00/00/04/rBABDljyHjWAaEUyAABKuR8W_ac775.jpg', '', null, null, '', '', '', '', '12', '', '', '1492394458', null, '', '3', null, null, '', '', null, null, '0', '258', '管理员', '太妃糖', '13075881402', 'Tang Wenjie', '9', '', null, '', '', '', '1', '80000', '', 'gp1/M00/00/04/rBABDljyHfyAMMrNAABxX89qvNE775.jpg', '30.0000', 'gp1/M00/00/04/rBABDljyHgCAWAiwAABxX89qvNE204.jpg', '广汽', null, null, 'gp1/M00/00/04/rBABDljyHf6ANqyqAABGL6VEcTU804.jpg', null, '2', '3', '', '45645645647897895', '0.8700', null, null, '0');
INSERT INTO `loan` VALUES ('57', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1492503020', 'luwei', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1492503553', null, null, null, null, null, null, null, null, null, '1', '258', 'luwei', null, null, 'luwei', '1', null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2', '3', null, null, null, null, null, '0');
INSERT INTO `loan` VALUES ('58', 'fujianshengfuzhoushigulouqu', '2013-03-09', '0', 'gp1/M00/00/06/rBABDlj1zE-ACU9QAABlgLBAku8187.jpg', '433127199003094057', '0', '福建省福州市鼓楼区软件园', '建筑业', '273', '05915623265', '党政机关', 'gp1/M00/00/06/rBABDlj1zLCAIp0BAAKerRc6mPE316.jpg', 'jimkkk', '吞吞吐吐拖拖拖拖拖拖', '17750252356', '朋友', '1', 'gp1/M00/00/06/rBABDlj1zFyASsEUAAa_GrllQCY757.png', '10000', '高级职称', '6000', '6', '0', '17750252340', 'jack', '社会服务业', null, '处级科级', '单位主管', '福建省福州市闽侯大学城', '632654', '本科', null, null, null, null, null, '059112345698', '', '1到3年', 'gp1/M00/00/06/rBABDlj1zM-AS7SjAAFKgHAW2To882.jpg', null, '1', 'gp1/M00/00/06/rBABDlj1zGuATzG1AAa_GrllQCY461.png', '433127199003094057', null, null, null, null, null, '3000', '17750252365', 'jjjm', null, '本二', '父子', null, '1491955200', 'gp1/M00/00/06/rBABDlj10tSAbvTpAABTFnUF1QE017.jpg,gp1/M00/00/06/rBABDlj10t6Afb2AAAI5l3Plm6I891.jpg', '189389239823', '1492503767', 'luwei', '1', 'gp1/M00/00/06/rBABDlj1zHqAPe7WAAPoY1Eq-0U692.jpg', '433127199003094057', null, null, '20000', '17750252340', 'ko', null, '自有', '母子', '6', null, null, '1492505748', '1491523200', 'gp1/M00/00/06/rBABDlj107aAJo5nAABVjsG_4mc078.jpg', '10', '6000', '1492128000', 'gp1/M00/00/06/rBABDlj11IyAaN0uAABILHH9Zxw276.jpg', 'zzzzzz', '6000', '7', '1', '258', 'luwei', 'jim', '17750252340', 'luwei', '36', '233423', '1491609600', 'gp1/M00/00/06/rBABDlj105SAPqdKAABlgLBAku8408.jpg', 'gp1/M00/00/06/rBABDlj105-AKzTFAAKerRc6mPE349.jpg', 'gp1/M00/00/06/rBABDlj106mAP7n_AABTFnUF1QE550.jpg', '1', '10000', '国4', 'gp1/M00/00/06/rBABDlj1zLSANIw_AABm39EIQ4c179.jpg', '4.0000', 'gp1/M00/00/06/rBABDlj1zKWAE_wEAAD84V1NNtk075.jpg', '广汽', '10000', '2013', 'gp1/M00/00/06/rBABDlj1zJuABIrVAABTFnUF1QE341.jpg', '2009-03-09', '2', '3', '非营运', '01234567890123456', '0.8000', '6', null, '0');
INSERT INTO `loan` VALUES ('59', 'dddddd', '2013-03-09', '1', 'gp1/M00/00/06/rBABDlj4JeiAb10OAABlgLBAku8653.jpg', '456232', '1', '福建省福州市哈哈哈', '采矿业制造业电力、燃气及水的生产和供应业', '273', '059458538896', '党政机关', null, '日日日', '通天塔', '17750252360', '信息', '0', 'gp1/M00/00/06/rBABDlj4JfSATZqWAAa_GrllQCY610.png', '2000', '高级职称', '6000', '6', '0', '17750232652', '霹雳', '采掘业', null, '处级科级', '一般职员', '福建省福州市哈哈哈', '236563', '本科', null, null, null, null, null, '56326545', null, '3年及以上', 'gp1/M00/00/06/rBABDlj4JomAALkPAAFKgHAW2To858.jpg', null, '1', 'gp1/M00/00/06/rBABDlj4JgOABFeBAAPoY1Eq-0U714.jpg', '5254322', null, null, null, null, null, '3000', '17750252360', 'ppp', 'gp1/M00/00/06/rBABDlj4JgmAEQB7AAGLx2InICU642.jpg', '高中', 'dd', null, null, null, null, '1492657810', '273', '1', 'gp1/M00/00/06/rBABDlj4JhOAJAmCAAI5l3Plm6I060.jpg', '741253', null, null, '2000', '17750252360', 'ooo', null, '点多', '试试', '6', null, null, '1492670179', null, null, '10', null, null, null, '好哈哈哈哈或或或或', null, null, '1', '258', '管理员', '向奇', '17750252340', '手單元', '3', null, null, null, null, null, '1', '10000', '国5', 'gp1/M00/00/06/rBABDlj4JmKAc3PmAAKerRc6mPE792.jpg', '3.5000', 'gp1/M00/00/06/rBABDlj4JleAFPegAAD84V1NNtk100.jpg', null, null, '2011', 'gp1/M00/00/06/rBABDlj4JkyAe6jGAABTFnUF1QE319.jpg', '1990-03-02', '2', '3', '非营运', '01234567890123456', '0.8000', null, '奥迪/A4/A42012先锋版', '0');

-- ----------------------------
-- Table structure for `loan_log`
-- ----------------------------
DROP TABLE IF EXISTS `loan_log`;
CREATE TABLE `loan_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `from_status` tinyint(2) unsigned NOT NULL COMMENT '原贷款状态',
  `loan_id` int(10) unsigned DEFAULT NULL COMMENT '贷款id',
  `operator_username` varchar(20) NOT NULL COMMENT '操作人username',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `to_status` tinyint(2) unsigned NOT NULL COMMENT '目标贷款状态',
  PRIMARY KEY (`id`),
  KEY `idx_operator_username` (`operator_username`),
  KEY `idx_loan_id_from_status_to_status` (`loan_id`,`from_status`,`to_status`)
) ENGINE=InnoDB AUTO_INCREMENT=338 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loan_log
-- ----------------------------
INSERT INTO `loan_log` VALUES ('1', '1492262578', '1', '1', '273', null, '1');
INSERT INTO `loan_log` VALUES ('2', '1492303425', '1', '2', '273', '无', '1');
INSERT INTO `loan_log` VALUES ('3', '1492304730', '1', '3', '273', null, '2');
INSERT INTO `loan_log` VALUES ('4', '1492308533', '2', '3', '273', null, '0');
INSERT INTO `loan_log` VALUES ('5', '1492308588', '1', '9', '273', null, '2');
INSERT INTO `loan_log` VALUES ('6', '1492308602', '2', '9', '273', '材料不全', '9');
INSERT INTO `loan_log` VALUES ('7', '1492308629', '9', '9', '273', null, '2');
INSERT INTO `loan_log` VALUES ('8', '1492308638', '2', '9', '273', null, '3');
INSERT INTO `loan_log` VALUES ('9', '1492308769', '1', '7', '273', null, '2');
INSERT INTO `loan_log` VALUES ('10', '1492316564', '2', '7', '2731', null, '3');
INSERT INTO `loan_log` VALUES ('11', '1492316602', '1', '10', '273', null, '2');
INSERT INTO `loan_log` VALUES ('12', '1492316617', '2', '10', '2731', null, '3');
INSERT INTO `loan_log` VALUES ('13', '1492327377', '3', '9', '273', null, '4');
INSERT INTO `loan_log` VALUES ('14', '1492329220', '1', '5', '273', null, '2');
INSERT INTO `loan_log` VALUES ('15', '1492329225', '1', '12', '273', null, '2');
INSERT INTO `loan_log` VALUES ('16', '1492329232', '1', '16', '273', null, '2');
INSERT INTO `loan_log` VALUES ('17', '1492329244', '2', '16', '273', null, '3');
INSERT INTO `loan_log` VALUES ('18', '1492329261', '2', '12', '273', null, '3');
INSERT INTO `loan_log` VALUES ('19', '1492329267', '2', '5', '273', null, '3');
INSERT INTO `loan_log` VALUES ('20', '1492329318', '3', '12', '273', null, '4');
INSERT INTO `loan_log` VALUES ('21', '1492329345', '3', '7', '273', null, '4');
INSERT INTO `loan_log` VALUES ('22', '1492329404', '3', '10', '273', null, '4');
INSERT INTO `loan_log` VALUES ('23', '1492329427', '3', '16', '273', null, '4');
INSERT INTO `loan_log` VALUES ('24', '1492332167', '4', '7', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('25', '1492332179', '4', '9', '2731', '就是决绝', '5');
INSERT INTO `loan_log` VALUES ('26', '1492332195', '4', '10', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('27', '1492332203', '4', '12', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('28', '1492391089', '1', '2', '273', null, '2');
INSERT INTO `loan_log` VALUES ('29', '1492391096', '1', '13', '273', null, '2');
INSERT INTO `loan_log` VALUES ('30', '1492391103', '1', '6', '273', null, '2');
INSERT INTO `loan_log` VALUES ('31', '1492391112', '1', '11', '273', null, '2');
INSERT INTO `loan_log` VALUES ('32', '1492391125', '1', '14', '273', null, '2');
INSERT INTO `loan_log` VALUES ('33', '1492391136', '1', '27', '273', null, '2');
INSERT INTO `loan_log` VALUES ('34', '1492391142', '1', '20', '273', null, '2');
INSERT INTO `loan_log` VALUES ('35', '1492391147', '1', '4', '273', null, '2');
INSERT INTO `loan_log` VALUES ('36', '1492391152', '1', '15', '273', null, '2');
INSERT INTO `loan_log` VALUES ('37', '1492391158', '1', '18', '273', null, '2');
INSERT INTO `loan_log` VALUES ('38', '1492391163', '1', '21', '273', null, '2');
INSERT INTO `loan_log` VALUES ('39', '1492391168', '1', '19', '273', null, '2');
INSERT INTO `loan_log` VALUES ('40', '1492391180', '1', '26', '273', null, '2');
INSERT INTO `loan_log` VALUES ('41', '1492391189', '1', '25', '273', null, '2');
INSERT INTO `loan_log` VALUES ('42', '1492391194', '1', '22', '273', null, '2');
INSERT INTO `loan_log` VALUES ('43', '1492391198', '1', '29', '273', null, '2');
INSERT INTO `loan_log` VALUES ('44', '1492391202', '1', '8', '273', null, '2');
INSERT INTO `loan_log` VALUES ('45', '1492391233', '2', '8', '273', '材料不全', '9');
INSERT INTO `loan_log` VALUES ('46', '1492391258', '1', '30', '273', null, '2');
INSERT INTO `loan_log` VALUES ('47', '1492391261', '1', '31', '273', null, '2');
INSERT INTO `loan_log` VALUES ('48', '1492391265', '1', '35', '273', null, '2');
INSERT INTO `loan_log` VALUES ('49', '1492391274', '2', '13', '273', null, '3');
INSERT INTO `loan_log` VALUES ('50', '1492391285', '2', '11', '273', null, '3');
INSERT INTO `loan_log` VALUES ('51', '1492391291', '2', '14', '273', null, '3');
INSERT INTO `loan_log` VALUES ('52', '1492391302', '2', '20', '273', null, '3');
INSERT INTO `loan_log` VALUES ('53', '1492391307', '2', '15', '273', null, '3');
INSERT INTO `loan_log` VALUES ('54', '1492391315', '2', '2', '273', null, '3');
INSERT INTO `loan_log` VALUES ('55', '1492391322', '1', '1', '273', null, '2');
INSERT INTO `loan_log` VALUES ('56', '1492391329', '2', '6', '273', null, '3');
INSERT INTO `loan_log` VALUES ('57', '1492391340', '1', '33', '273', null, '2');
INSERT INTO `loan_log` VALUES ('58', '1492391345', '1', '36', '273', null, '2');
INSERT INTO `loan_log` VALUES ('59', '1492391349', '1', '34', '273', null, '2');
INSERT INTO `loan_log` VALUES ('60', '1492391354', '2', '22', '273', null, '3');
INSERT INTO `loan_log` VALUES ('61', '1492391360', '2', '18', '273', null, '3');
INSERT INTO `loan_log` VALUES ('62', '1492391364', '2', '26', '273', null, '3');
INSERT INTO `loan_log` VALUES ('63', '1492391369', '2', '21', '273', null, '3');
INSERT INTO `loan_log` VALUES ('64', '1492391375', '1', '24', '273', null, '2');
INSERT INTO `loan_log` VALUES ('65', '1492391380', '1', '37', '273', null, '2');
INSERT INTO `loan_log` VALUES ('66', '1492391384', '1', '41', '273', null, '2');
INSERT INTO `loan_log` VALUES ('67', '1492391387', '1', '43', '273', null, '2');
INSERT INTO `loan_log` VALUES ('68', '1492391391', '1', '38', '273', null, '2');
INSERT INTO `loan_log` VALUES ('69', '1492391395', '1', '44', '273', null, '2');
INSERT INTO `loan_log` VALUES ('70', '1492391400', '1', '42', '273', null, '2');
INSERT INTO `loan_log` VALUES ('71', '1492391404', '1', '47', '273', null, '2');
INSERT INTO `loan_log` VALUES ('72', '1492391408', '1', '40', '273', null, '2');
INSERT INTO `loan_log` VALUES ('73', '1492391412', '1', '45', '273', null, '2');
INSERT INTO `loan_log` VALUES ('74', '1492391416', '1', '50', '273', null, '2');
INSERT INTO `loan_log` VALUES ('75', '1492391422', '1', '51', '273', null, '2');
INSERT INTO `loan_log` VALUES ('76', '1492391434', '1', '23', '273', null, '2');
INSERT INTO `loan_log` VALUES ('77', '1492391439', '2', '19', '273', null, '3');
INSERT INTO `loan_log` VALUES ('78', '1492391443', '1', '46', '273', null, '2');
INSERT INTO `loan_log` VALUES ('79', '1492391449', '2', '23', '273', null, '3');
INSERT INTO `loan_log` VALUES ('80', '1492391454', '2', '25', '273', null, '3');
INSERT INTO `loan_log` VALUES ('81', '1492391513', '1', '39', '273', null, '2');
INSERT INTO `loan_log` VALUES ('82', '1492391518', '1', '48', '273', null, '2');
INSERT INTO `loan_log` VALUES ('83', '1492391524', '1', '56', '273', null, '2');
INSERT INTO `loan_log` VALUES ('84', '1492391532', '2', '27', '273', null, '3');
INSERT INTO `loan_log` VALUES ('85', '1492391536', '1', '54', '273', null, '2');
INSERT INTO `loan_log` VALUES ('86', '1492391542', '1', '52', '273', null, '2');
INSERT INTO `loan_log` VALUES ('87', '1492391547', '1', '53', '273', null, '2');
INSERT INTO `loan_log` VALUES ('88', '1492391680', '2', '24', '273', null, '3');
INSERT INTO `loan_log` VALUES ('89', '1492391730', '2', '30', '273', null, '3');
INSERT INTO `loan_log` VALUES ('90', '1492391808', '2', '4', '273', null, '3');
INSERT INTO `loan_log` VALUES ('91', '1492391812', '2', '29', '273', null, '3');
INSERT INTO `loan_log` VALUES ('92', '1492391824', '2', '35', '273', null, '3');
INSERT INTO `loan_log` VALUES ('93', '1492391829', '2', '37', '273', null, '3');
INSERT INTO `loan_log` VALUES ('94', '1492391839', '2', '41', '273', null, '3');
INSERT INTO `loan_log` VALUES ('95', '1492391844', '2', '33', '273', null, '3');
INSERT INTO `loan_log` VALUES ('96', '1492391851', '2', '38', '273', null, '3');
INSERT INTO `loan_log` VALUES ('97', '1492391856', '2', '34', '273', null, '3');
INSERT INTO `loan_log` VALUES ('98', '1492394118', '2', '40', '273', null, '3');
INSERT INTO `loan_log` VALUES ('99', '1492394344', '2', '31', '273', null, '3');
INSERT INTO `loan_log` VALUES ('100', '1492394348', '2', '39', '273', null, '3');
INSERT INTO `loan_log` VALUES ('101', '1492394352', '2', '36', '273', null, '3');
INSERT INTO `loan_log` VALUES ('102', '1492394358', '2', '1', '273', null, '3');
INSERT INTO `loan_log` VALUES ('103', '1492394362', '2', '42', '273', null, '3');
INSERT INTO `loan_log` VALUES ('104', '1492394366', '2', '43', '273', null, '3');
INSERT INTO `loan_log` VALUES ('105', '1492394371', '2', '44', '273', null, '3');
INSERT INTO `loan_log` VALUES ('106', '1492394399', '2', '45', '273', null, '3');
INSERT INTO `loan_log` VALUES ('107', '1492394404', '2', '46', '273', null, '3');
INSERT INTO `loan_log` VALUES ('108', '1492394408', '2', '47', '273', null, '3');
INSERT INTO `loan_log` VALUES ('109', '1492394414', '2', '48', '273', null, '3');
INSERT INTO `loan_log` VALUES ('110', '1492394418', '2', '50', '273', null, '3');
INSERT INTO `loan_log` VALUES ('111', '1492394422', '2', '51', '273', null, '3');
INSERT INTO `loan_log` VALUES ('112', '1492394429', '2', '52', '273', null, '3');
INSERT INTO `loan_log` VALUES ('113', '1492394433', '2', '53', '273', null, '3');
INSERT INTO `loan_log` VALUES ('114', '1492394439', '2', '54', '273', null, '3');
INSERT INTO `loan_log` VALUES ('115', '1492394458', '2', '56', '273', '修改修改', '9');
INSERT INTO `loan_log` VALUES ('116', '1492394468', '1', '28', '273', null, '2');
INSERT INTO `loan_log` VALUES ('117', '1492394544', '1', '49', '273', null, '2');
INSERT INTO `loan_log` VALUES ('118', '1492394559', '2', '49', '273', '修改23', '9');
INSERT INTO `loan_log` VALUES ('119', '1492394568', '9', '49', '273', null, '2');
INSERT INTO `loan_log` VALUES ('120', '1492394584', '2', '28', '273', null, '3');
INSERT INTO `loan_log` VALUES ('121', '1492394640', '3', '1', '273', null, '4');
INSERT INTO `loan_log` VALUES ('122', '1492394719', '3', '4', '273', null, '4');
INSERT INTO `loan_log` VALUES ('123', '1492394752', '3', '2', '273', null, '4');
INSERT INTO `loan_log` VALUES ('124', '1492395850', '3', '5', '273', null, '4');
INSERT INTO `loan_log` VALUES ('125', '1492395869', '3', '6', '273', null, '4');
INSERT INTO `loan_log` VALUES ('126', '1492395891', '3', '11', '273', null, '4');
INSERT INTO `loan_log` VALUES ('127', '1492395910', '3', '14', '273', null, '4');
INSERT INTO `loan_log` VALUES ('128', '1492395941', '3', '19', '273', null, '4');
INSERT INTO `loan_log` VALUES ('129', '1492395959', '3', '15', '273', null, '4');
INSERT INTO `loan_log` VALUES ('130', '1492395999', '3', '18', '273', null, '4');
INSERT INTO `loan_log` VALUES ('131', '1492396064', '3', '23', '273', null, '4');
INSERT INTO `loan_log` VALUES ('132', '1492396088', '3', '22', '273', null, '4');
INSERT INTO `loan_log` VALUES ('133', '1492396110', '3', '25', '273', null, '4');
INSERT INTO `loan_log` VALUES ('134', '1492396143', '3', '21', '273', null, '4');
INSERT INTO `loan_log` VALUES ('135', '1492396163', '3', '31', '273', null, '4');
INSERT INTO `loan_log` VALUES ('136', '1492396180', '3', '34', '273', null, '4');
INSERT INTO `loan_log` VALUES ('137', '1492396219', '3', '35', '273', null, '4');
INSERT INTO `loan_log` VALUES ('138', '1492396275', '3', '29', '273', null, '4');
INSERT INTO `loan_log` VALUES ('139', '1492396302', '3', '30', '273', null, '4');
INSERT INTO `loan_log` VALUES ('140', '1492396355', '4', '4', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('141', '1492396364', '4', '16', '2731', 'taichale', '5');
INSERT INTO `loan_log` VALUES ('142', '1492396373', '4', '1', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('143', '1492396397', '4', '2', '2731', '材料虚假\n客户信用不良\n客户还款能力不足\n没有原因', '5');
INSERT INTO `loan_log` VALUES ('144', '1492396406', '4', '5', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('145', '1492396418', '4', '21', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('146', '1492396426', '4', '25', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('147', '1492396433', '4', '30', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('148', '1492396441', '4', '29', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('149', '1492396449', '4', '34', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('150', '1492396458', '4', '35', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('151', '1492396469', '4', '31', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('152', '1492396476', '4', '23', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('153', '1492396588', '4', '6', '2731', '客户信用不良\n客户还款能力不足\n使得', '5');
INSERT INTO `loan_log` VALUES ('154', '1492396597', '4', '14', '2731', '不好', '5');
INSERT INTO `loan_log` VALUES ('155', '1492396606', '4', '15', '2731', '哦哦哦', '5');
INSERT INTO `loan_log` VALUES ('156', '1492396617', '4', '19', '2731', '就是看你', '5');
INSERT INTO `loan_log` VALUES ('157', '1492396694', '4', '22', '2731', '2222', '5');
INSERT INTO `loan_log` VALUES ('158', '1492396703', '4', '18', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('159', '1492396742', '3', '24', '2733', null, '4');
INSERT INTO `loan_log` VALUES ('160', '1492396761', '3', '39', '2733', null, '4');
INSERT INTO `loan_log` VALUES ('161', '1492396774', '3', '40', '2733', null, '4');
INSERT INTO `loan_log` VALUES ('162', '1492396789', '3', '33', '2733', null, '4');
INSERT INTO `loan_log` VALUES ('163', '1492396806', '3', '20', '2733', null, '4');
INSERT INTO `loan_log` VALUES ('164', '1492396823', '3', '42', '2733', null, '4');
INSERT INTO `loan_log` VALUES ('165', '1492396840', '3', '43', '2733', null, '4');
INSERT INTO `loan_log` VALUES ('166', '1492396857', '3', '41', '2733', null, '4');
INSERT INTO `loan_log` VALUES ('167', '1492396872', '3', '28', '2733', null, '4');
INSERT INTO `loan_log` VALUES ('168', '1492396890', '3', '37', '2733', null, '4');
INSERT INTO `loan_log` VALUES ('169', '1492396913', '3', '48', '2733', null, '4');
INSERT INTO `loan_log` VALUES ('170', '1492396928', '3', '50', '2733', null, '4');
INSERT INTO `loan_log` VALUES ('171', '1492396948', '3', '47', '2733', null, '4');
INSERT INTO `loan_log` VALUES ('172', '1492396983', '4', '11', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('173', '1492396991', '4', '42', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('174', '1492396998', '4', '41', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('175', '1492397005', '4', '48', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('176', '1492397012', '4', '50', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('177', '1492397018', '4', '47', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('178', '1492397026', '4', '39', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('179', '1492397032', '4', '40', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('180', '1492397037', '4', '37', '2731', 'undefined', '5');
INSERT INTO `loan_log` VALUES ('181', '1492397050', '4', '33', '2731', null, '6');
INSERT INTO `loan_log` VALUES ('182', '1492397059', '4', '43', '2731', 'undefined', '5');
INSERT INTO `loan_log` VALUES ('183', '1492397189', '4', '28', '2731', 'undefined', '5');
INSERT INTO `loan_log` VALUES ('184', '1492398216', '5', '2', '273', null, '10');
INSERT INTO `loan_log` VALUES ('185', '1492398602', '10', '2', '273', null, '6');
INSERT INTO `loan_log` VALUES ('186', '1492398741', '5', '6', '273', null, '10');
INSERT INTO `loan_log` VALUES ('187', '1492399575', '10', '6', '273', '再次拒绝', '11');
INSERT INTO `loan_log` VALUES ('188', '1492399689', '5', '9', '273', null, '10');
INSERT INTO `loan_log` VALUES ('189', '1492399704', '10', '9', '273', '再次拒绝', '11');
INSERT INTO `loan_log` VALUES ('190', '1492400469', '5', '14', '273', null, '15');
INSERT INTO `loan_log` VALUES ('191', '1492407236', '3', '44', '273', null, '4');
INSERT INTO `loan_log` VALUES ('192', '1492407254', '3', '38', '273', null, '4');
INSERT INTO `loan_log` VALUES ('193', '1492407272', '3', '45', '273', null, '4');
INSERT INTO `loan_log` VALUES ('194', '1492407297', '3', '51', '273', null, '4');
INSERT INTO `loan_log` VALUES ('195', '1492407329', '3', '53', '273', null, '4');
INSERT INTO `loan_log` VALUES ('196', '1492407342', '4', '24', '273', null, '6');
INSERT INTO `loan_log` VALUES ('197', '1492407353', '4', '20', '273', null, '6');
INSERT INTO `loan_log` VALUES ('198', '1492407364', '4', '51', '273', null, '6');
INSERT INTO `loan_log` VALUES ('199', '1492407386', '4', '38', '273', null, '6');
INSERT INTO `loan_log` VALUES ('200', '1492407586', '6', '2', '273', '就是要放弃', '17');
INSERT INTO `loan_log` VALUES ('201', '1492407605', '6', '4', '273', '44444', '17');
INSERT INTO `loan_log` VALUES ('202', '1492407847', '6', '1', '273', null, '37');
INSERT INTO `loan_log` VALUES ('203', '1492407871', '6', '5', '273', null, '37');
INSERT INTO `loan_log` VALUES ('204', '1492407885', '6', '7', '273', null, '37');
INSERT INTO `loan_log` VALUES ('205', '1492407900', '6', '10', '273', null, '37');
INSERT INTO `loan_log` VALUES ('206', '1492407919', '6', '11', '273', null, '37');
INSERT INTO `loan_log` VALUES ('207', '1492407937', '6', '20', '273', null, '37');
INSERT INTO `loan_log` VALUES ('208', '1492407947', '6', '12', '273', null, '37');
INSERT INTO `loan_log` VALUES ('209', '1492407957', '6', '34', '273', null, '37');
INSERT INTO `loan_log` VALUES ('210', '1492407972', '6', '33', '273', null, '37');
INSERT INTO `loan_log` VALUES ('211', '1492407986', '6', '31', '273', null, '37');
INSERT INTO `loan_log` VALUES ('212', '1492407998', '6', '29', '273', null, '37');
INSERT INTO `loan_log` VALUES ('213', '1492408013', '6', '35', '273', null, '37');
INSERT INTO `loan_log` VALUES ('214', '1492408017', '6', '24', '273', null, '17');
INSERT INTO `loan_log` VALUES ('215', '1492408032', '6', '40', '273', 'javascript:void(0);', '17');
INSERT INTO `loan_log` VALUES ('216', '1492408051', '6', '21', '273', null, '37');
INSERT INTO `loan_log` VALUES ('217', '1492408072', '6', '39', '273', null, '37');
INSERT INTO `loan_log` VALUES ('218', '1492408084', '6', '41', '273', null, '37');
INSERT INTO `loan_log` VALUES ('219', '1492408093', '6', '18', '273', null, '37');
INSERT INTO `loan_log` VALUES ('220', '1492408109', '6', '47', '273', null, '17');
INSERT INTO `loan_log` VALUES ('221', '1492408115', '6', '42', '273', '333222', '17');
INSERT INTO `loan_log` VALUES ('222', '1492408144', '6', '38', '273', null, '37');
INSERT INTO `loan_log` VALUES ('223', '1492408155', '6', '30', '273', null, '37');
INSERT INTO `loan_log` VALUES ('224', '1492412526', '17', '4', '273', '确认放弃', '12');
INSERT INTO `loan_log` VALUES ('225', '1492412713', '17', '2', '273', null, '16');
INSERT INTO `loan_log` VALUES ('226', '1492412769', '17', '40', '273', null, '12');
INSERT INTO `loan_log` VALUES ('227', '1492412800', '17', '24', '273', null, '12');
INSERT INTO `loan_log` VALUES ('228', '1492412913', '6', '23', '273', null, '17');
INSERT INTO `loan_log` VALUES ('229', '1492414520', '17', '23', '273', '就是要放弃', '16');
INSERT INTO `loan_log` VALUES ('230', '1492414534', '17', '42', '273', '又同意', '6');
INSERT INTO `loan_log` VALUES ('231', '1492414613', '6', '42', '273', '又放弃', '16');
INSERT INTO `loan_log` VALUES ('232', '1492414722', '6', '48', '273', '第一次放弃', '17');
INSERT INTO `loan_log` VALUES ('233', '1492417609', '37', '1', '273', '通过', '8');
INSERT INTO `loan_log` VALUES ('234', '1492417617', '37', '5', '273', null, '6');
INSERT INTO `loan_log` VALUES ('235', '1492417627', '37', '7', '273', '通过', '8');
INSERT INTO `loan_log` VALUES ('236', '1492417631', '37', '10', '273', null, '25');
INSERT INTO `loan_log` VALUES ('237', '1492417634', '37', '11', '273', null, '25');
INSERT INTO `loan_log` VALUES ('238', '1492417636', '37', '12', '273', null, '25');
INSERT INTO `loan_log` VALUES ('239', '1492417639', '37', '18', '273', null, '25');
INSERT INTO `loan_log` VALUES ('240', '1492417641', '37', '20', '273', null, '8');
INSERT INTO `loan_log` VALUES ('241', '1492417644', '37', '21', '273', null, '25');
INSERT INTO `loan_log` VALUES ('242', '1492417646', '37', '29', '273', null, '8');
INSERT INTO `loan_log` VALUES ('243', '1492417648', '37', '30', '273', null, '8');
INSERT INTO `loan_log` VALUES ('244', '1492417651', '37', '31', '273', null, '25');
INSERT INTO `loan_log` VALUES ('245', '1492417653', '37', '33', '273', null, '25');
INSERT INTO `loan_log` VALUES ('246', '1492417655', '37', '34', '273', null, '8');
INSERT INTO `loan_log` VALUES ('247', '1492417657', '37', '35', '273', null, '8');
INSERT INTO `loan_log` VALUES ('248', '1492417660', '37', '38', '273', null, '25');
INSERT INTO `loan_log` VALUES ('251', '1492418629', '6', '5', '273', null, '37');
INSERT INTO `loan_log` VALUES ('252', '1492418634', '37', '5', '273', null, '6');
INSERT INTO `loan_log` VALUES ('253', '1492418638', '6', '5', '273', null, '37');
INSERT INTO `loan_log` VALUES ('254', '1492418642', '37', '5', '273', null, '8');
INSERT INTO `loan_log` VALUES ('255', '1492419066', '12', '40', '273', null, '6');
INSERT INTO `loan_log` VALUES ('256', '1492419078', '12', '4', '273', null, '6');
INSERT INTO `loan_log` VALUES ('257', '1492419091', '4', '45', '273', null, '6');
INSERT INTO `loan_log` VALUES ('258', '1492419110', '6', '25', '273', null, '37');
INSERT INTO `loan_log` VALUES ('259', '1492419153', '6', '40', '273', null, '37');
INSERT INTO `loan_log` VALUES ('260', '1492419165', '6', '50', '273', null, '37');
INSERT INTO `loan_log` VALUES ('261', '1492419171', '37', '40', '273', null, '8');
INSERT INTO `loan_log` VALUES ('262', '1492419174', '37', '41', '273', null, '25');
INSERT INTO `loan_log` VALUES ('263', '1492419176', '37', '39', '273', null, '8');
INSERT INTO `loan_log` VALUES ('264', '1492419213', '5', '19', '273', null, '10');
INSERT INTO `loan_log` VALUES ('265', '1492419247', '10', '19', '273', null, '6');
INSERT INTO `loan_log` VALUES ('266', '1492419276', '6', '4', '273', null, '37');
INSERT INTO `loan_log` VALUES ('267', '1492421651', '8', '1', '273', null, '22');
INSERT INTO `loan_log` VALUES ('268', '1492421941', '22', '1', '273', null, '23');
INSERT INTO `loan_log` VALUES ('269', '1492422413', '8', '5', '273', null, '22');
INSERT INTO `loan_log` VALUES ('270', '1492422436', '8', '7', '273', null, '22');
INSERT INTO `loan_log` VALUES ('271', '1492422448', '22', '5', '273', '不需要原因', '21');
INSERT INTO `loan_log` VALUES ('272', '1492477046', '8', '20', '273', null, '22');
INSERT INTO `loan_log` VALUES ('273', '1492477073', '8', '29', '273', '无法过户', '19');
INSERT INTO `loan_log` VALUES ('274', '1492477105', '8', '30', '273', '303030', '19');
INSERT INTO `loan_log` VALUES ('275', '1492477474', '19', '29', '273', '就是无法过户', '20');
INSERT INTO `loan_log` VALUES ('276', '1492477490', '19', '30', '273', '误点', '8');
INSERT INTO `loan_log` VALUES ('277', '1492477535', '8', '34', '273', '测试无法过户', '19');
INSERT INTO `loan_log` VALUES ('278', '1492477586', '8', '30', '273', '两次无法过户', '20');
INSERT INTO `loan_log` VALUES ('279', '1492477614', '22', '7', '273', null, '23');
INSERT INTO `loan_log` VALUES ('280', '1492477639', '23', '1', '273', null, '24');
INSERT INTO `loan_log` VALUES ('281', '1492477793', '8', '35', '273', null, '22');
INSERT INTO `loan_log` VALUES ('282', '1492477800', '22', '20', '273', null, '23');
INSERT INTO `loan_log` VALUES ('283', '1492477813', '23', '7', '273', null, '24');
INSERT INTO `loan_log` VALUES ('284', '1492477826', '24', '1', '273', null, '38');
INSERT INTO `loan_log` VALUES ('285', '1492479782', '8', '39', '273', null, '22');
INSERT INTO `loan_log` VALUES ('286', '1492484092', '24', '7', '273', null, '38');
INSERT INTO `loan_log` VALUES ('287', '1492484215', '22', '39', '273', null, '23');
INSERT INTO `loan_log` VALUES ('288', '1492484232', '23', '20', '273', null, '24');
INSERT INTO `loan_log` VALUES ('289', '1492484329', '38', '1', '273', null, '31');
INSERT INTO `loan_log` VALUES ('290', '1492484507', '25', '10', '273', null, '29');
INSERT INTO `loan_log` VALUES ('291', '1492484835', '25', '11', '273', '第一次无法过户', '26');
INSERT INTO `loan_log` VALUES ('292', '1492484936', '26', '11', '273', '确认无法办理', '27');
INSERT INTO `loan_log` VALUES ('293', '1492485017', '25', '12', '273', '第一次无法办理', '26');
INSERT INTO `loan_log` VALUES ('294', '1492485030', '26', '12', '273', '急需办理', '25');
INSERT INTO `loan_log` VALUES ('295', '1492485045', '25', '12', '273', '第二次无法办理', '27');
INSERT INTO `loan_log` VALUES ('296', '1492485084', '25', '18', '273', null, '29');
INSERT INTO `loan_log` VALUES ('297', '1492485096', '29', '10', '273', '就是退回', '28');
INSERT INTO `loan_log` VALUES ('298', '1492485271', '29', '18', '273', null, '30');
INSERT INTO `loan_log` VALUES ('299', '1492485539', '25', '21', '273', null, '29');
INSERT INTO `loan_log` VALUES ('300', '1492485568', '25', '31', '273', null, '29');
INSERT INTO `loan_log` VALUES ('301', '1492485964', '29', '21', '273', null, '30');
INSERT INTO `loan_log` VALUES ('302', '1492486687', '28', '10', '273', null, '33');
INSERT INTO `loan_log` VALUES ('303', '1492486779', '29', '31', '273', '313113', '28');
INSERT INTO `loan_log` VALUES ('304', '1492486787', '28', '31', '273', null, '33');
INSERT INTO `loan_log` VALUES ('305', '1492490823', '33', '10', '273', null, '30');
INSERT INTO `loan_log` VALUES ('306', '1492490838', '30', '10', '273', null, '36');
INSERT INTO `loan_log` VALUES ('307', '1492491105', '21', '5', '273', null, '32');
INSERT INTO `loan_log` VALUES ('308', '1492491264', '25', '33', '273', null, '29');
INSERT INTO `loan_log` VALUES ('309', '1492491331', '33', '31', '273', null, '30');
INSERT INTO `loan_log` VALUES ('310', '1492491351', '29', '33', '273', '3333333333', '28');
INSERT INTO `loan_log` VALUES ('311', '1492491373', '28', '33', '273', null, '33');
INSERT INTO `loan_log` VALUES ('312', '1492491438', '25', '38', '273', null, '29');
INSERT INTO `loan_log` VALUES ('313', '1492503020', '1', '57', 'luwei', null, '1');
INSERT INTO `loan_log` VALUES ('314', '1492503767', '1', '58', 'luwei', 'zzzzzz', '1');
INSERT INTO `loan_log` VALUES ('315', '1492503767', '1', '58', 'luwei', null, '2');
INSERT INTO `loan_log` VALUES ('316', '1492504807', '2', '58', 'jiang', null, '3');
INSERT INTO `loan_log` VALUES ('317', '1492504928', '3', '58', 'zhang', null, '4');
INSERT INTO `loan_log` VALUES ('318', '1492505053', '4', '58', 'xiang', null, '6');
INSERT INTO `loan_log` VALUES ('319', '1492505316', '6', '58', 'luwei', null, '37');
INSERT INTO `loan_log` VALUES ('320', '1492505368', '37', '58', 'jiang', null, '25');
INSERT INTO `loan_log` VALUES ('321', '1492505537', '25', '58', 'luwei', null, '29');
INSERT INTO `loan_log` VALUES ('322', '1492505614', '29', '58', '273', null, '30');
INSERT INTO `loan_log` VALUES ('323', '1492505748', '30', '58', '2733', null, '36');
INSERT INTO `loan_log` VALUES ('324', '1492657810', '1', '59', '273', '好哈哈哈哈或或或或', '1');
INSERT INTO `loan_log` VALUES ('325', '1492658369', '1', '59', '273', null, '2');
INSERT INTO `loan_log` VALUES ('326', '1492670179', '2', '59', '273', null, '3');
INSERT INTO `loan_log` VALUES ('327', '1492484232', '24', '20', '273', '', '24');
INSERT INTO `loan_log` VALUES ('328', '1492484329', '31', '1', '273', null, '31');
INSERT INTO `loan_log` VALUES ('329', '1492484329', '31', '1', '273', '#release!#', '31');
INSERT INTO `loan_log` VALUES ('330', '1492484329', '31', '1', '273', '#release!#', '31');
INSERT INTO `loan_log` VALUES ('331', '1493274759', '24', '20', '273', null, '38');
INSERT INTO `loan_log` VALUES ('332', '1493274864', '38', '20', '273', null, '31');
INSERT INTO `loan_log` VALUES ('333', '1493274922', '38', '7', '273', null, '31');
INSERT INTO `loan_log` VALUES ('334', '1493274947', '22', '35', '273', null, '24');
INSERT INTO `loan_log` VALUES ('335', '1493275048', '24', '35', '273', null, '38');
INSERT INTO `loan_log` VALUES ('336', '1493275112', '32', '5', '273', null, '24');
INSERT INTO `loan_log` VALUES ('337', '1493275112', '24', '5', '273', '#release!#', '24');

-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `enabled` tinyint(1) unsigned NOT NULL COMMENT '是否启用',
  `name` varchar(20) NOT NULL COMMENT '岗位名称',
  `post_type_id` int(10) unsigned NOT NULL COMMENT '岗位类型',
  `editable` tinyint(1) unsigned NOT NULL COMMENT '是否可编辑',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '1234567890', '1', '金融专员', '3', '0');
INSERT INTO `post` VALUES ('2', '1234567890', '1', '意向分配员', '3', '0');
INSERT INTO `post` VALUES ('3', '1234567890', '1', '大区经理', '2', '0');
INSERT INTO `post` VALUES ('4', '1234567890', '1', 'ADMIN', '0', '0');
INSERT INTO `post` VALUES ('5', '1234567890', '1', 'CEO', '1', '0');
INSERT INTO `post` VALUES ('6', '1234567890', '1', 'CFO', '1', '0');
INSERT INTO `post` VALUES ('7', '1234567890', '1', 'COO', '1', '0');
INSERT INTO `post` VALUES ('8', '1234567890', '1', 'CRO', '1', '0');
INSERT INTO `post` VALUES ('9', '1234567890', '1', '财务', '2', '0');
INSERT INTO `post` VALUES ('10', '1234567890', '1', '风控总监', '2', '0');
INSERT INTO `post` VALUES ('11', '1234567890', '1', '风控经理', '2', '0');
INSERT INTO `post` VALUES ('13', '1234567890', '1', '定价师', '3', '0');
INSERT INTO `post` VALUES ('14', '1234567890', '1', '初审', '3', '0');
INSERT INTO `post` VALUES ('15', '1234567890', '1', '业务回访', '3', '0');
INSERT INTO `post` VALUES ('16', '1234567890', '1', '业务总监', '3', '0');
INSERT INTO `post` VALUES ('17', '1234567890', '1', '复审', '3', '0');
INSERT INTO `post` VALUES ('24', '1234567890', '1', '收单员', '3', '0');
INSERT INTO `post` VALUES ('27', '1492501200', '1', '测试岗位', '3', '1');

-- ----------------------------
-- Table structure for `post_authority`
-- ----------------------------
DROP TABLE IF EXISTS `post_authority`;
CREATE TABLE `post_authority` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `authority` varchar(64) NOT NULL COMMENT '权限(等同于角色)',
  `post_id` int(10) unsigned NOT NULL COMMENT '岗位id',
  `city_check` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '管辖城市权限校验',
  `self_check` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '创建人权限校验',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_post_id_authority` (`post_id`,`authority`),
  UNIQUE KEY `uk_post_id_authority` (`post_id`,`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post_authority
-- ----------------------------
INSERT INTO `post_authority` VALUES ('1', 'ROLE_PRODUCT_REVIEW', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('5', 'ROLE_LOAN_DRAFT_UPDATE', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('6', 'ROLE_LOAN_FIRST_REVIEW', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('7', 'ROLE_LOAN_PRICE_UPDATE', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('8', 'ROLE_LOAN_SECOND_REVIEW', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('9', 'ROLE_LOAN_MATERIALS_UPDATE', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('10', 'ROLE_LOAN_CONTRACT_REVIEW', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('11', 'ROLE_LOAN_SCHEME_UPDATE', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('12', 'ROLE_LOAN_TRANSFER_UPDATE', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('13', 'ROLE_LOAN_CONTRACT_ABORT_REVIEW', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('14', 'ROLE_LOAN_SCHEME_UPDATE_REVIEW', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('15', 'ROLE_LOAN_TRANSFER_ABORT_REVIEW', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('16', 'ROLE_LOAN_RELEASE_REVIEW', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('17', 'ROLE_LOAN_RELEASE_UPDATE', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('18', 'ROLE_LOAN_RELEASE_MORTGAGE_UPDATE', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('19', 'ROLE_LOAN_MORTGAGE_UPDATE', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('20', 'ROLE_LOAN_MORTGAGE_ABORT_REVIEW', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('21', 'ROLE_LOAN_MORTGAGE_RELEASE_REVIEW', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('22', 'ROLE_LOAN_MORTGAGE_RELEASE_UPDATE', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('23', 'ROLE_ADMIN', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('24', 'ROLE_PRODUCT_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('25', 'ROLE_PRODUCT_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('26', 'ROLE_PRODUCT_READ', '16', '0', '0');
INSERT INTO `post_authority` VALUES ('28', 'ROLE_SECOND_REVIEWER', '17', '1', '0');
INSERT INTO `post_authority` VALUES ('29', 'ROLE_PRODUCT_READ', '11', '0', '0');
INSERT INTO `post_authority` VALUES ('30', 'ROLE_PRODUCT_READ', '10', '0', '0');
INSERT INTO `post_authority` VALUES ('31', 'ROLE_PRODUCT_READ', '9', '0', '0');
INSERT INTO `post_authority` VALUES ('34', 'ROLE_PRODUCT_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('35', 'ROLE_PRODUCT_REVIEW', '16', '0', '0');
INSERT INTO `post_authority` VALUES ('39', 'ROLE_LOAN_CONTRACT_ACCEPT_REVIEW', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('41', 'ROLE_PRODUCT_TEMPLATE_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('42', 'ROLE_PRODUCT_TEMPLATE_READ', '11', '0', '0');
INSERT INTO `post_authority` VALUES ('43', 'ROLE_PRODUCT_TEMPLATE_READ', '16', '0', '0');
INSERT INTO `post_authority` VALUES ('44', 'ROLE_PRODUCT_TEMPLATE_UPDATE', '16', '0', '0');
INSERT INTO `post_authority` VALUES ('45', 'ROLE_PRODUCT_TEMPLATE_UPDATE', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('46', 'ROLE_PRODUCT_UPDATE', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('47', 'ROLE_PRODUCT_UPDATE', '16', '0', '0');
INSERT INTO `post_authority` VALUES ('48', 'ROLE_PRODUCT_UPDATE', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('49', 'ROLE_LOAN_DRAFT_UPDATE', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('50', 'ROLE_LOAN_FIRST_REVIEW', '14', '1', '0');
INSERT INTO `post_authority` VALUES ('51', 'ROLE_LOAN_PRICE_UPDATE', '13', '0', '0');
INSERT INTO `post_authority` VALUES ('52', 'ROLE_LOAN_SECOND_REVIEW', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('54', 'ROLE_LOAN_SECOND_REVIEW', '10', '0', '0');
INSERT INTO `post_authority` VALUES ('55', 'ROLE_LOAN_SECOND_REVIEW', '11', '0', '0');
INSERT INTO `post_authority` VALUES ('56', 'ROLE_LOAN_SECOND_REVIEW', '17', '1', '0');
INSERT INTO `post_authority` VALUES ('57', 'ROLE_LOAN_MATERIALS_UPDATE', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('58', 'ROLE_LOAN_CONTRACT_REVIEW', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('59', 'ROLE_LOAN_CONTRACT_ACCEPT_REVIEW', '14', '1', '0');
INSERT INTO `post_authority` VALUES ('60', 'ROLE_LOAN_CONTRACT_ABORT_REVIEW', '15', '0', '0');
INSERT INTO `post_authority` VALUES ('61', 'ROLE_LOAN_TRANSFER_UPDATE', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('62', 'ROLE_LOAN_MORTGAGE_UPDATE', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('63', 'ROLE_LOAN_RELEASE_REVIEW', '14', '1', '0');
INSERT INTO `post_authority` VALUES ('64', 'ROLE_LOAN_TRANSFER_ABORT_REVIEW', '15', '0', '0');
INSERT INTO `post_authority` VALUES ('65', 'ROLE_LOAN_RELEASE_UPDATE', '9', '0', '0');
INSERT INTO `post_authority` VALUES ('66', 'ROLE_LOAN_RELEASE_MORTGAGE_UPDATE', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('67', 'ROLE_LOAN_RELEASE_MORTGAGE_REVIEW', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('68', 'ROLE_LOAN_RELEASE_MORTGAGE_REVIEW', '14', '1', '0');
INSERT INTO `post_authority` VALUES ('69', 'ROLE_LOAN_MORTGAGE_RELEASE_REVIEW', '14', '1', '0');
INSERT INTO `post_authority` VALUES ('70', 'ROLE_LOAN_MORTGAGE_ABORT_REVIEW', '15', '0', '0');
INSERT INTO `post_authority` VALUES ('71', 'ROLE_LOAN_MORTGAGE_RELEASE_UPDATE', '9', '0', '0');
INSERT INTO `post_authority` VALUES ('72', 'ROLE_PRODUCT_TEMPLATE_READ', '10', '0', '0');
INSERT INTO `post_authority` VALUES ('73', 'ROLE_PRODUCT_TEMPLATE_READ', '9', '0', '0');
INSERT INTO `post_authority` VALUES ('74', 'ROLE_PRODUCT_TEMPLATE_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('76', 'ROLE_LOAN_DRAFT_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('77', 'ROLE_LOAN_DRAFT_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('78', 'ROLE_LOAN_DRAFT_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('79', 'ROLE_LOAN_DRAFT_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('80', 'ROLE_LOAN_DRAFT_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('81', 'ROLE_LOAN_FIRST_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('82', 'ROLE_LOAN_FIRST_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('83', 'ROLE_LOAN_FIRST_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('84', 'ROLE_LOAN_FIRST_READ', '14', '1', '0');
INSERT INTO `post_authority` VALUES ('85', 'ROLE_LOAN_FIRST_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('86', 'ROLE_LOAN_FIRST_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('87', 'ROLE_LOAN_PRICE_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('88', 'ROLE_LOAN_PRICE_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('89', 'ROLE_APPRAISER', '13', '0', '0');
INSERT INTO `post_authority` VALUES ('90', 'ROLE_LOAN_PRICE_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('91', 'ROLE_LOAN_PRICE_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('92', 'ROLE_LOAN_PRICE_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('93', 'ROLE_LOAN_SECOND_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('94', 'ROLE_LOAN_SECOND_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('95', 'ROLE_LOAN_SECOND_READ', '10', '0', '0');
INSERT INTO `post_authority` VALUES ('96', 'ROLE_LOAN_SECOND_READ', '11', '0', '0');
INSERT INTO `post_authority` VALUES ('97', 'ROLE_LOAN_SECOND_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('98', 'ROLE_LOAN_SECOND_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('99', 'ROLE_LOAN_SECOND_READ', '17', '1', '0');
INSERT INTO `post_authority` VALUES ('100', 'ROLE_LOAN_SECOND_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('101', 'ROLE_LOAN_CONTRACT_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('102', 'ROLE_LOAN_CONTRACT_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('103', 'ROLE_LOAN_CONTRACT_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('104', 'ROLE_LOAN_CONTRACT_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('105', 'ROLE_LOAN_CONTRACT_ACCEPT_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('106', 'ROLE_LOAN_CONTRACT_ACCEPT_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('107', 'ROLE_LOAN_CONTRACT_ACCEPT_READ', '14', '1', '0');
INSERT INTO `post_authority` VALUES ('108', 'ROLE_LOAN_CONTRACT_ACCEPT_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('109', 'ROLE_LOAN_CONTRACT_ACCEPT_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('110', 'ROLE_LOAN_CONTRACT_ACCEPT_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('111', 'ROLE_LOAN_CONTRACT_ABORT_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('112', 'ROLE_LOAN_CONTRACT_ABORT_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('113', 'ROLE_LOAN_CONTRACT_ABORT_READ', '15', '0', '0');
INSERT INTO `post_authority` VALUES ('114', 'ROLE_LOAN_CONTRACT_ABORT_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('115', 'ROLE_LOAN_CONTRACT_ABORT_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('116', 'ROLE_LOAN_CONTRACT_ABORT_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('117', 'ROLE_LOAN_TRANSFER_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('118', 'ROLE_LOAN_TRANSFER_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('119', 'ROLE_LOAN_TRANSFER_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('120', 'ROLE_LOAN_TRANSFER_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('121', 'ROLE_LOAN_TRANSFER_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('122', 'ROLE_LOAN_CONTRACT_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('123', 'ROLE_LOAN_RELEASE_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('124', 'ROLE_LOAN_RELEASE_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('125', 'ROLE_LOAN_RELEASE_READ', '14', '1', '0');
INSERT INTO `post_authority` VALUES ('126', 'ROLE_LOAN_RELEASE_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('127', 'ROLE_LOAN_RELEASE_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('128', 'ROLE_LOAN_RELEASE_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('129', 'ROLE_LOAN_TRANSFER_ABORT_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('130', 'ROLE_LOAN_TRANSFER_ABORT_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('131', 'ROLE_LOAN_TRANSFER_ABORT_READ', '15', '0', '0');
INSERT INTO `post_authority` VALUES ('132', 'ROLE_LOAN_TRANSFER_ABORT_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('133', 'ROLE_LOAN_TRANSFER_ABORT_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('134', 'ROLE_LOAN_TRANSFER_ABORT_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('136', 'ROLE_LOAN_RELEASE_UPDATE_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('137', 'ROLE_LOAN_RELEASE_UPDATE_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('138', 'ROLE_LOAN_RELEASE_UPDATE_READ', '9', '0', '0');
INSERT INTO `post_authority` VALUES ('139', 'ROLE_LOAN_RELEASE_UPDATE_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('140', 'ROLE_LOAN_RELEASE_UPDATE_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('141', 'ROLE_LOAN_RELEASE_UPDATE_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('144', 'ROLE_LOAN_RELEASE_MORTGAGE_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('145', 'ROLE_LOAN_RELEASE_MORTGAGE_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('147', 'ROLE_LOAN_RELEASE_MORTGAGE_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('148', 'ROLE_LOAN_RELEASE_MORTGAGE_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('149', 'ROLE_LOAN_RELEASE_MORTGAGE_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('150', 'ROLE_LOAN_MORTGAGE_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('151', 'ROLE_LOAN_MORTGAGE_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('152', 'ROLE_LOAN_MORTGAGE_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('153', 'ROLE_LOAN_MORTGAGE_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('154', 'ROLE_LOAN_MORTGAGE_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('155', 'ROLE_LOAN_MORTGAGE_RELEASE_REVIEW_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('156', 'ROLE_LOAN_MORTGAGE_RELEASE_REVIEW_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('158', 'ROLE_LOAN_MORTGAGE_RELEASE_REVIEW_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('159', 'ROLE_LOAN_MORTGAGE_RELEASE_REVIEW_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('160', 'ROLE_LOAN_MORTGAGE_RELEASE_REVIEW_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('161', 'ROLE_LOAN_MORTGAGE_ABORT_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('162', 'ROLE_LOAN_MORTGAGE_ABORT_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('163', 'ROLE_LOAN_MORTGAGE_ABORT_READ', '15', '0', '0');
INSERT INTO `post_authority` VALUES ('164', 'ROLE_LOAN_MORTGAGE_ABORT_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('165', 'ROLE_LOAN_MORTGAGE_ABORT_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('166', 'ROLE_LOAN_MORTGAGE_ABORT_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('167', 'ROLE_LOAN_MORTGAGE_RELEASE_READ', '4', '0', '0');
INSERT INTO `post_authority` VALUES ('168', 'ROLE_LOAN_MORTGAGE_RELEASE_READ', '5', '0', '0');
INSERT INTO `post_authority` VALUES ('169', 'ROLE_LOAN_MORTGAGE_RELEASE_READ', '9', '0', '0');
INSERT INTO `post_authority` VALUES ('170', 'ROLE_LOAN_MORTGAGE_RELEASE_READ', '3', '1', '0');
INSERT INTO `post_authority` VALUES ('171', 'ROLE_LOAN_MORTGAGE_RELEASE_READ', '16', '1', '0');
INSERT INTO `post_authority` VALUES ('172', 'ROLE_LOAN_MORTGAGE_RELEASE_READ', '1', '0', '1');
INSERT INTO `post_authority` VALUES ('176', 'ROLE_LOAN_CONTRACT_READ\r\n', '10', '0', '0');
INSERT INTO `post_authority` VALUES ('177', 'ROLE_LOAN_CONTRACT_READ\r\n', '11', '0', '0');
INSERT INTO `post_authority` VALUES ('178', 'ROLE_LOAN_CONTRACT_READ\r\nROLE_LOAN_TRANSFER_READ', '17', '1', '0');
INSERT INTO `post_authority` VALUES ('181', 'ROLE_PRODUCT_CITY_READ', '1', '1', '0');
INSERT INTO `post_authority` VALUES ('182', 'ROLE_FINANCE', '9', '0', '0');
INSERT INTO `post_authority` VALUES ('184', 'ROLE_FIRST_REVIEWER', '14', '1', '0');

-- ----------------------------
-- Table structure for `post_type`
-- ----------------------------
DROP TABLE IF EXISTS `post_type`;
CREATE TABLE `post_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(20) NOT NULL COMMENT '岗位类型名称',
  `sort_index` int(10) unsigned NOT NULL COMMENT '岗位类型排序索引',
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post_type
-- ----------------------------
INSERT INTO `post_type` VALUES ('1', '高层领导岗', '1', '1');
INSERT INTO `post_type` VALUES ('2', '中层领导岗', '2', '1');
INSERT INTO `post_type` VALUES ('3', '执行岗', '3', '1');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `available_terms` varchar(100) NOT NULL COMMENT '可贷期数',
  `city_id` int(10) unsigned NOT NULL COMMENT '城市id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `creator_username` varchar(20) DEFAULT NULL COMMENT '创建人',
  `loan_monthly_interest_rate` decimal(5,4) unsigned NOT NULL COMMENT '贷款月利率',
  `loan_policy` tinyint(2) unsigned NOT NULL COMMENT '贷款政策',
  `max_available_rate` tinyint(1) unsigned NOT NULL COMMENT '最高可贷成数',
  `max_available_vehicle_year` int(10) unsigned DEFAULT NULL COMMENT '产品最长可用车辆年限',
  `min_available_rate` tinyint(1) unsigned NOT NULL COMMENT '最低可贷成数',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `name` varchar(50) NOT NULL COMMENT '产品名称',
  `product_template_id` int(10) unsigned NOT NULL COMMENT '产品模板id，如果productTemplateId为0表示自身为产品模板',
  `product_type` tinyint(2) unsigned NOT NULL COMMENT '产品类型',
  `repayment_method` tinyint(2) unsigned NOT NULL COMMENT '还款类型',
  `status` tinyint(1) unsigned NOT NULL COMMENT '产品状态',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`),
  KEY `idx_product_template_id` (`product_template_id`),
  KEY `idx_city_id` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '6,12,60', '181', '1492225410', '273', '0.8100', '0', '7', '8', '3', '1492225410', '车安心1号', '1', '0', '0', '0', '1');
INSERT INTO `product` VALUES ('2', '6,60', '155', '1492225441', '273', '0.7900', '0', '6', '3', '3', '1492225441', '车安心1号', '1', '0', '0', '0', '1');
INSERT INTO `product` VALUES ('3', '12,60', '258', '1492225469', '273', '0.8700', '0', '7', '5', '3', '1492225469', '车安心1号', '1', '0', '0', '1', '1');
INSERT INTO `product` VALUES ('4', '6,12,60', '253', '1492225907', '273', '0.8000', '0', '7', '10', '3', '1492225907', '车安心1号', '1', '0', '0', '2', '1');
INSERT INTO `product` VALUES ('5', '6,12,60', '253', '1492303241', '273', '0.7900', '0', '7', '9', '3', '1492303241', '车安心1号', '1', '0', '0', '1', '1');
INSERT INTO `product` VALUES ('6', '6,12,18,24,36,48,60', '258', '1492390684', '273', '0.8000', '1', '9', '10', '1', '1492390684', '车安心2号', '2', '0', '0', '1', '1');
INSERT INTO `product` VALUES ('7', '6,12,18,24,36,48,60', '253', '1492390702', '273', '0.7700', '1', '9', '10', '1', '1492390702', '车安心2号', '2', '0', '0', '1', '1');
INSERT INTO `product` VALUES ('8', '6,12,18,24,36,48,60', '108', '1492390733', '273', '0.7600', '1', '9', '9', '1', '1492390733', '车安心2号', '2', '0', '0', '1', '1');
INSERT INTO `product` VALUES ('9', '6,12,60', '79', '1492390750', '273', '0.7200', '0', '7', '10', '3', '1492390750', '车安心1号', '1', '0', '0', '2', '1');
INSERT INTO `product` VALUES ('10', '6,18', '258', '1492502600', '273', '0.8000', '1', '7', '10', '3', '1492502636', '车安心3号', '3', '0', '0', '1', '1');
INSERT INTO `product` VALUES ('11', '6,12,24,36,48,60', '258', '1492506233', '273', '0.8000', '0', '5', '8', '1', '1492506233', '车安心2号', '2', '0', '0', '1', '1');
INSERT INTO `product` VALUES ('12', '6,12,36,48,60', '258', '1492506337', '273', '0.8100', '1', '5', '4', '3', '1492506337', '车安心2号', '2', '0', '0', '1', '1');

-- ----------------------------
-- Table structure for `product_log`
-- ----------------------------
DROP TABLE IF EXISTS `product_log`;
CREATE TABLE `product_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `operator_type` tinyint(1) unsigned NOT NULL COMMENT '操作类型',
  `operator_username` varchar(20) NOT NULL COMMENT '操作人username',
  `product_id` int(10) unsigned NOT NULL COMMENT '产品id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_operator_username` (`operator_username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_log
-- ----------------------------
INSERT INTO `product_log` VALUES ('1', '1492261418', '1', '273', '3', '通过');
INSERT INTO `product_log` VALUES ('2', '1492303254', '1', '273', '5', '通过');
INSERT INTO `product_log` VALUES ('3', '1492303265', '2', '273', '4', '拒绝');
INSERT INTO `product_log` VALUES ('4', '1492390759', '1', '273', '6', '通过');
INSERT INTO `product_log` VALUES ('5', '1492390766', '1', '273', '7', '通过');
INSERT INTO `product_log` VALUES ('6', '1492390778', '1', '273', '8', '通过审核');
INSERT INTO `product_log` VALUES ('7', '1492390786', '2', '273', '9', '拒绝审核');
INSERT INTO `product_log` VALUES ('8', '1492502718', '1', '273', '10', '通过');
INSERT INTO `product_log` VALUES ('9', '1492506348', '1', '273', '11', 'ff');
INSERT INTO `product_log` VALUES ('10', '1492506352', '1', '273', '12', 'eee');

-- ----------------------------
-- Table structure for `product_template`
-- ----------------------------
DROP TABLE IF EXISTS `product_template`;
CREATE TABLE `product_template` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `available_terms` varchar(100) NOT NULL COMMENT '可贷期数',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `creator_username` varchar(20) DEFAULT NULL COMMENT '创建人',
  `loan_monthly_interest_rate` decimal(5,4) unsigned NOT NULL COMMENT '贷款月利率',
  `loan_policy` tinyint(2) unsigned NOT NULL COMMENT '贷款政策',
  `max_available_rate` tinyint(1) unsigned NOT NULL COMMENT '最高可贷成数',
  `min_available_rate` tinyint(1) unsigned NOT NULL COMMENT '最低可贷成数',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `name` varchar(50) NOT NULL COMMENT '产品名称',
  `product_type` tinyint(2) unsigned NOT NULL COMMENT '产品类型',
  `repayment_method` tinyint(2) unsigned NOT NULL COMMENT '还款类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_template
-- ----------------------------
INSERT INTO `product_template` VALUES ('1', '6,12,60', '1492072401', '2731', '0.8100', '0', '7', '3', '1492072401', '车安心1号', '0', '0');
INSERT INTO `product_template` VALUES ('2', '6,12,18,24,36,48,60', '1492390653', '273', '0.7900', '1', '9', '1', '1492506313', '车安心2号', '0', '0');
INSERT INTO `product_template` VALUES ('3', '6,12,18', '1492502456', '273', '0.7900', '1', '8', '3', '1492502456', '车安心3号', '0', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `address` varchar(300) NOT NULL COMMENT '地址',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `dept_id` int(10) unsigned NOT NULL COMMENT '部门id',
  `email` varchar(64) NOT NULL COMMENT '邮箱',
  `emergency_contact` varchar(30) NOT NULL COMMENT '紧急联系人',
  `emergency_contact_mobile_number` char(11) NOT NULL COMMENT '紧急联系人手机号码',
  `identity_number` char(18) NOT NULL COMMENT '身份证号码',
  `identity_photo` varchar(100) NOT NULL COMMENT '身份证照片',
  `mobile_number` char(11) NOT NULL COMMENT '手机号',
  `password` char(80) NOT NULL COMMENT '用户密码（登录用）,使用Spring Security的BaseEncoder加密',
  `photo` varchar(50) NOT NULL COMMENT '用户照片',
  `real_name` varchar(30) NOT NULL COMMENT '用户姓名',
  `region` varchar(20) NOT NULL COMMENT '地区',
  `username` varchar(20) NOT NULL COMMENT '用户账号（登录用）',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('7', '福建省鼓楼区软件园B区', '1491878637', '1', 'tangwenjie@gmail.com', 'Tang Wenjie', '13075881402', '35012219891203591X', 'gp1/M00/00/03/rBABDljsQoGAecK-AABoJvQ-m-0600.jpg', '13075881401', '43e25c38fd680db7346d60e1005c48401313bccdd27e664334971d453965931eb29971d177ffa933', 'gp1/M00/00/03/rBABDljsQnCACpKFAABoJvQ-m-0445.jpg', '管理员', '华南', '273', '1', '1491878997');
INSERT INTO `user` VALUES ('8', '58 Hot Spring Branch Road', '1491893396', '1', 'tangwenjie0@gmail.com', 'Tang Wenjie', '13075881402', '35012219891203591X', 'gp1/M00/00/03/rBABDljsfHmAEqPmAABGL6VEcTU449.jpg', '13075881402', '3673e058fb60346eb7f24517e21c65a4c00ee5fde92c49bc45a9b47b971d67c3ee9066d6add4249b', 'gp1/M00/00/03/rBABDljsfG2AZWUhAABGL6VEcTU932.jpg', '手單元', '华南', '9200180', '1', '1492497754');
INSERT INTO `user` VALUES ('9', '58 Hot Spring Branch Road', '1491894315', '3', 'tangwenjie0@gmail.com', 'Tang Wenjie', '13075881402', '35012219891203591X', 'gp1/M00/00/03/rBABDljsfwGAUfsNAABKuR8W_ac237.jpg', '13075881403', '958131395a001bd0bf2f6a6e4e993e51ea80df31d006fae0579e284a1883b8a47cebcd02aaffdb4d', 'gp1/M00/00/03/rBABDljsfvmANm0UAABoJvQ-m-0857.jpg', 'Tang Wenjie', '华南', '2731', '1', '1493285009');
INSERT INTO `user` VALUES ('10', '58 Hot Spring Branch Road', '1491894576', '1', 'tangwenjie0@gmail.com', 'Tang Wenjie', '13075881402', '35012219891203591X', 'gp1/M00/00/03/rBABDljsgRCAJX7-AABKuR8W_ac891.jpg,gp1/M00/00/07/rBABDlj9oyOAeE1IAACjdwlRxBU907.jpg', '13075881404', '51a84fe26f3e158c2ca5223ec0f3c17df6e60176ff17d031e2e0f8127c49981060afa85f6eb59f96', 'gp1/M00/00/07/rBABDlj9lxGAM_nWAAfhSFIV0k4695.jpg', 'Tang Wenjie', '华南', '2733', '1', '1493017419');
INSERT INTO `user` VALUES ('11', '福州市鼓楼区急急急急急急', '1492501478', '3', 'luwei@163.com', '急急急', '13075881411', '35', 'gp1/M00/00/05/rBABDlj1w4uAP3rVAAHgw04-J34446.jpg', '13078882334', 'f74cc24ca41e084ebc2b01ce049a3c923a34c17b66d08c329ac3a0e05d2c81d534851df26e8ee8ef', 'gp1/M00/00/05/rBABDlj1w0KAIALjAABlgLBAku8987.jpg', 'luwei', '华南', 'luwei', '1', '1492502917');
INSERT INTO `user` VALUES ('12', 'ffffffffffffffff', '1492501649', '3', 'zhang@gmail.com', 'sssssssssss', '13456789988', '350122839428234953', 'gp1/M00/00/05/rBABDlj1xGCAWVCkAABTFnUF1QE785.jpg', '13212233345', '17e9663d994caae724c536cae278b822f0fccc924b05d1658c98cb8dc214f37ea6c1164b2c7d11be', 'gp1/M00/00/05/rBABDlj1xDyAZcQ7AAD84V1NNtk185.jpg', 'zhangsiyi', '华南', 'zhang', '1', '1492501649');
INSERT INTO `user` VALUES ('13', '江哥 范德萨发生大dddd', '1492501969', '3', 'jiang@sina.com', '紧急我', '13075882234', '350122198812222202', 'gp1/M00/00/05/rBABDlj1xaWAEkA7AAHgw04-J34310.jpg', '13078223412', '2939787d1a5f70fa77ff5a5cdac246934e29aa077e6b9ae419b16f9843e32282e29017c1843ead0a', 'gp1/M00/00/05/rBABDlj1xVqAWIjZAAa_GrllQCY944.png', 'jiang', '华南', 'jiang', '1', '1492501969');
INSERT INTO `user` VALUES ('14', 'fffffffffffffffffff', '1492502243', '3', 'xiangqi@11.com', 'ssssssss', '13078998833', '350000199909092231', 'gp1/M00/00/05/rBABDlj1xtCAeFtMAABm39EIQ4c727.jpg', '13075881033', 'd5f87b1d002b5e42b67b67a4bd48e35aef784ceaa74d438559d1e1a2ede58147b4643a571da34cf8', 'gp1/M00/00/05/rBABDlj1xq6AXG4TAAKerRc6mPE986.jpg', 'xiangqi', '华南', 'xiang', '1', '1492502243');
INSERT INTO `user` VALUES ('15', '58 Hot Spring Branch Road', '1493285140', '3', 'tangwj@273.cn', 'Tang Wenjie', '13075881402', '350122198912035912', 'gp1/M00/00/07/rBABDlkBuOmAP8HQAACjdwlRxBU154.jpg', '13075881409', 'ea8c173ceeb0d394668574831bcfbf0a4953cac2dfe3bbc45a69fb6e7596783a8d067fc7101e9087', 'gp1/M00/00/07/rBABDlkBuM2Aep8rAACjdwlRxBU247.jpg', 'deng', '华南', 'deng', '1', '1493285140');

-- ----------------------------
-- Table structure for `user_post`
-- ----------------------------
DROP TABLE IF EXISTS `user_post`;
CREATE TABLE `user_post` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `post_id` int(10) unsigned NOT NULL COMMENT '岗位id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username_post_id` (`username`,`post_id`),
  UNIQUE KEY `uk_username_post_id` (`username`,`post_id`),
  KEY `idx_post_id` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_post
-- ----------------------------
INSERT INTO `user_post` VALUES ('1', '1234567890', '4', '273', '1');
INSERT INTO `user_post` VALUES ('2', '1234567878', '1', '273', '0');
INSERT INTO `user_post` VALUES ('3', '1324567890', '2', '2731', '0');
INSERT INTO `user_post` VALUES ('4', '1490753469', '4', '10000', '0');
INSERT INTO `user_post` VALUES ('5', '1490753469', '5', '10000', '0');
INSERT INTO `user_post` VALUES ('6', '1490753469', '17', '10000', '0');
INSERT INTO `user_post` VALUES ('13', '1490755518', '2', '10000', '0');
INSERT INTO `user_post` VALUES ('17', '1490756213', '1', '10000', '0');
INSERT INTO `user_post` VALUES ('18', '1490756465', '19', '10000', '0');
INSERT INTO `user_post` VALUES ('19', '1490756465', '23', '10000', '0');
INSERT INTO `user_post` VALUES ('20', '1490756785', '15', '10000', '0');
INSERT INTO `user_post` VALUES ('21', '1490758589', '6', '10000', '0');
INSERT INTO `user_post` VALUES ('22', '1491877564', '4', '2733', '0');
INSERT INTO `user_post` VALUES ('23', '1491893396', '23', '9200180', '0');
INSERT INTO `user_post` VALUES ('24', '1491983409', '5', '2731', '0');
INSERT INTO `user_post` VALUES ('25', '1492072315', '24', '2731', '0');
INSERT INTO `user_post` VALUES ('26', '1492072620', '3', '2731', '0');
INSERT INTO `user_post` VALUES ('27', '1492135631', '1', '2731', '1');
INSERT INTO `user_post` VALUES ('28', '1492261283', '24', '2733', '0');
INSERT INTO `user_post` VALUES ('29', '1492308792', '14', '2731', '0');
INSERT INTO `user_post` VALUES ('30', '1492329541', '17', '2731', '0');
INSERT INTO `user_post` VALUES ('31', '1492330612', '16', '2731', '0');
INSERT INTO `user_post` VALUES ('32', '1492330632', '16', '2733', '0');
INSERT INTO `user_post` VALUES ('33', '1492332545', '13', '2733', '0');
INSERT INTO `user_post` VALUES ('34', '1492391072', '24', '9200180', '1');
INSERT INTO `user_post` VALUES ('35', '1492497404', '1', '9200180', '1');
INSERT INTO `user_post` VALUES ('36', '1492501470', '1', 'luwei', '1');
INSERT INTO `user_post` VALUES ('37', '1492501612', '13', 'zhang', '1');
INSERT INTO `user_post` VALUES ('38', '1492501964', '14', 'jiang', '1');
INSERT INTO `user_post` VALUES ('39', '1492502243', '17', 'xiang', '1');
INSERT INTO `user_post` VALUES ('40', '1492502917', '24', 'luwei', '1');
INSERT INTO `user_post` VALUES ('41', '1492505666', '9', '2733', '1');
INSERT INTO `user_post` VALUES ('42', '1493285140', '5', 'deng', '1');
