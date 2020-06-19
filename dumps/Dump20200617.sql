-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: medmis
-- ------------------------------------------------------
-- Server version	5.7.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bus_doctortime`
--

DROP TABLE IF EXISTS `bus_doctortime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_doctortime` (
  `doctortime_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医生工作时间id',
  `doctor_id` int(11) NOT NULL COMMENT '医生id',
  `startime` datetime NOT NULL COMMENT '工作时段开始',
  `endtime` datetime NOT NULL COMMENT '工作时段结束',
  `remain` int(11) NOT NULL DEFAULT '20' COMMENT '余号',
  `price` float NOT NULL COMMENT '挂号费用',
  PRIMARY KEY (`doctortime_id`),
  UNIQUE KEY `bus_doctortime_doctortime_id_uindex` (`doctortime_id`),
  KEY `bus_doctortime_sys_user_user_id_fk` (`doctor_id`),
  CONSTRAINT `bus_doctortime_sys_user_user_id_fk` FOREIGN KEY (`doctor_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='医生工作时间';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_doctortime`
--

LOCK TABLES `bus_doctortime` WRITE;
/*!40000 ALTER TABLE `bus_doctortime` DISABLE KEYS */;
INSERT INTO `bus_doctortime` VALUES (1,11,'2020-06-11 12:00:00','2020-06-11 20:00:00',12,20),(11,8,'2020-06-13 12:00:00','2020-06-13 20:00:00',0,100),(12,12,'2020-06-13 12:00:00','2020-06-13 20:00:00',19,20),(13,13,'2020-06-13 12:00:00','2020-06-13 20:00:00',19,20),(14,14,'2020-06-13 12:00:00','2020-06-13 20:00:00',19,20),(15,17,'2020-06-13 12:00:00','2020-06-13 20:00:00',19,20),(16,18,'2020-06-13 12:00:00','2020-06-13 20:00:00',19,20),(17,19,'2020-06-13 12:00:00','2020-06-13 20:00:00',17,20);
/*!40000 ALTER TABLE `bus_doctortime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_exam`
--

DROP TABLE IF EXISTS `bus_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_exam` (
  `exam_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用于标识检查项目id',
  `exam_name` varchar(45) NOT NULL COMMENT '检查项目名称',
  `price` decimal(10,0) NOT NULL COMMENT '每次检查价格',
  `usage_count` int(11) NOT NULL COMMENT '检查项目已用次数',
  PRIMARY KEY (`exam_id`),
  UNIQUE KEY `exam_id_UNIQUE` (`exam_id`),
  UNIQUE KEY `name_UNIQUE` (`exam_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检查项目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_exam`
--

LOCK TABLES `bus_exam` WRITE;
/*!40000 ALTER TABLE `bus_exam` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_examdoc`
--

DROP TABLE IF EXISTS `bus_examdoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_examdoc` (
  `examdoc_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '检查报告文档id',
  `examtodo_id` int(11) DEFAULT NULL COMMENT '生成该报告的检查单',
  `laboratorian_id` int(11) NOT NULL COMMENT '检查医生的id',
  `record_id` int(11) NOT NULL COMMENT '生成该报告的病历',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `available` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `content` varchar(255) DEFAULT NULL COMMENT '检查的结果',
  PRIMARY KEY (`examdoc_id`),
  UNIQUE KEY `bus_examdoc_examdoc_id_uindex` (`examdoc_id`),
  KEY `bus_examdoc_bus_examtodo_examtodo_id_fk` (`examtodo_id`),
  KEY `bus_examdoc_bus_record_record_id_fk` (`record_id`),
  KEY `bus_examdoc_sys_user_id_fk_idx` (`laboratorian_id`),
  CONSTRAINT `bus_examdoc_bus_examtodo_examtodo_id_fk` FOREIGN KEY (`examtodo_id`) REFERENCES `bus_examtodo` (`examtodo_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_examdoc_bus_record_record_id_fk` FOREIGN KEY (`record_id`) REFERENCES `bus_record` (`record_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_examdoc_sys_user_id_fk` FOREIGN KEY (`laboratorian_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='检查报告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_examdoc`
--

LOCK TABLES `bus_examdoc` WRITE;
/*!40000 ALTER TABLE `bus_examdoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_examdoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_examqueue`
--

DROP TABLE IF EXISTS `bus_examqueue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_examqueue` (
  `queue_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '检查队列id',
  `queue_number` int(11) DEFAULT NULL COMMENT '检查号',
  `examregister_id` int(11) NOT NULL COMMENT '检查挂号id',
  `available` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`queue_id`),
  KEY `bus_examqueue_bus_examregister_examregister_id_fk` (`examregister_id`),
  CONSTRAINT `bus_examqueue_bus_examregister_examregister_id_fk` FOREIGN KEY (`examregister_id`) REFERENCES `bus_examregister` (`examregister_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='排队看病';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_examqueue`
--

LOCK TABLES `bus_examqueue` WRITE;
/*!40000 ALTER TABLE `bus_examqueue` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_examqueue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_examregister`
--

DROP TABLE IF EXISTS `bus_examregister`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_examregister` (
  `examregister_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '检查挂号id',
  `exam_id` int(11) NOT NULL COMMENT '检查项目id',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `payment_id` int(11) NOT NULL COMMENT '付款单id',
  `payment_ifdone` tinyint(4) NOT NULL COMMENT '是否付款',
  `available` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `examtime_id` int(11) NOT NULL COMMENT '检查时间id',
  `examtodo_id` int(11) NOT NULL COMMENT '检查单id',
  PRIMARY KEY (`examregister_id`),
  UNIQUE KEY `bus_examregister_examregister_id_uindex` (`examregister_id`),
  KEY `bus_examregister_bus_exam_exam_id_fk` (`exam_id`),
  KEY `bus_examregister_bus_examtime_eaxmtime_id_fk` (`examtime_id`),
  KEY `bus_examregister_bus_payment_id_fk_idx` (`payment_id`),
  KEY `bus_examregister_bus_examtodo_id_fk_idx` (`examtodo_id`),
  CONSTRAINT `bus_examregister_bus_exam_exam_id_fk` FOREIGN KEY (`exam_id`) REFERENCES `bus_exam` (`exam_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_examregister_bus_examtime_eaxmtime_id_fk` FOREIGN KEY (`examtime_id`) REFERENCES `bus_examtime` (`eaxmtime_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_examregister_bus_examtodo_id_fk` FOREIGN KEY (`examtodo_id`) REFERENCES `bus_examtodo` (`examtodo_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_examregister_bus_payment_id_fk` FOREIGN KEY (`payment_id`) REFERENCES `bus_payment` (`payment_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='检查预约单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_examregister`
--

LOCK TABLES `bus_examregister` WRITE;
/*!40000 ALTER TABLE `bus_examregister` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_examregister` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_examtime`
--

DROP TABLE IF EXISTS `bus_examtime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_examtime` (
  `eaxmtime_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '检查时间id',
  `exam_id` int(11) NOT NULL COMMENT '检查项目id',
  `startime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '开始时间',
  `endtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `remain` int(11) NOT NULL COMMENT '余号',
  `price` double NOT NULL COMMENT '价格',
  PRIMARY KEY (`eaxmtime_id`),
  UNIQUE KEY `bus_laboratoriantime_doctortime_id_uindex` (`eaxmtime_id`),
  KEY `bus_examtime_bus_exam_id_fk_idx` (`exam_id`),
  CONSTRAINT `bus_examtime_bus_exam_id_fk` FOREIGN KEY (`exam_id`) REFERENCES `bus_exam` (`exam_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='检验工作时间';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_examtime`
--

LOCK TABLES `bus_examtime` WRITE;
/*!40000 ALTER TABLE `bus_examtime` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_examtime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_examtodo`
--

DROP TABLE IF EXISTS `bus_examtodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_examtodo` (
  `examtodo_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '检查单id',
  `record_id` int(11) NOT NULL COMMENT '创建该检查单的病历报告id',
  `exam_id` int(11) NOT NULL COMMENT '检查项目id',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `available` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `pay_ifdone` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否付款',
  PRIMARY KEY (`examtodo_id`),
  KEY `bus_examtodo_bus_record_record_id_fk` (`record_id`),
  KEY `bus_examtodo_bus_exam_exam_id_fk` (`exam_id`),
  CONSTRAINT `bus_examtodo_bus_exam_exam_id_fk` FOREIGN KEY (`exam_id`) REFERENCES `bus_exam` (`exam_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_examtodo_bus_record_record_id_fk` FOREIGN KEY (`record_id`) REFERENCES `bus_record` (`record_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生给患者开具的待检查项目单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_examtodo`
--

LOCK TABLES `bus_examtodo` WRITE;
/*!40000 ALTER TABLE `bus_examtodo` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_examtodo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_meddoc`
--

DROP TABLE IF EXISTS `bus_meddoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_meddoc` (
  `meddoc_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '取药报告文档id',
  `medtodo_id` int(11) DEFAULT NULL COMMENT '生成该报告的药品单',
  `pharmacist_id` int(11) NOT NULL COMMENT '开药医生的id',
  `record_id` int(11) NOT NULL COMMENT '生成该报告的病历',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `available` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `ifdone` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否完成给药',
  `ifdelivery` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否选择快递',
  `deliveryaddr` varchar(255) DEFAULT NULL COMMENT '快递地址',
  PRIMARY KEY (`meddoc_id`),
  UNIQUE KEY `bus_meddoc_meddoc_id_uindex` (`meddoc_id`),
  KEY `bus_meddoc_bus_medtodo_medtodo_id_fk` (`medtodo_id`),
  KEY `bus_meddoc_bus_record_record_id_fk` (`record_id`),
  KEY `bus_meddoc_sys_user_id_fk_idx` (`pharmacist_id`),
  CONSTRAINT `bus_meddoc_bus_medtodo_medtodo_id_fk` FOREIGN KEY (`medtodo_id`) REFERENCES `bus_medtodo` (`medtodo_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_meddoc_bus_record_record_id_fk` FOREIGN KEY (`record_id`) REFERENCES `bus_record` (`record_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_meddoc_sys_user_id_fk` FOREIGN KEY (`pharmacist_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='药品报告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_meddoc`
--

LOCK TABLES `bus_meddoc` WRITE;
/*!40000 ALTER TABLE `bus_meddoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_meddoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_medicine`
--

DROP TABLE IF EXISTS `bus_medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_medicine` (
  `med_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用于标识药品id',
  `med_name` varchar(45) NOT NULL COMMENT '药品名称',
  `price` decimal(10,0) NOT NULL COMMENT '药品每份价格',
  `stock` int(11) NOT NULL COMMENT '药品剩余数量',
  PRIMARY KEY (`med_id`),
  UNIQUE KEY `name_UNIQUE` (`med_name`),
  UNIQUE KEY `med_id_UNIQUE` (`med_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='药品类目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_medicine`
--

LOCK TABLES `bus_medicine` WRITE;
/*!40000 ALTER TABLE `bus_medicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_medqueue`
--

DROP TABLE IF EXISTS `bus_medqueue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_medqueue` (
  `queue_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '取药排队id',
  `queue_number` int(11) DEFAULT NULL COMMENT '取药号码',
  `medtodo_id` int(11) NOT NULL COMMENT '取药单id',
  `available` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`queue_id`),
  KEY `bus_medqueue_bus_medtodo_medtodo_id_fk` (`medtodo_id`),
  CONSTRAINT `bus_medqueue_bus_medtodo_medtodo_id_fk` FOREIGN KEY (`medtodo_id`) REFERENCES `bus_medtodo` (`medtodo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='排队取药';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_medqueue`
--

LOCK TABLES `bus_medqueue` WRITE;
/*!40000 ALTER TABLE `bus_medqueue` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_medqueue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_medtodo`
--

DROP TABLE IF EXISTS `bus_medtodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_medtodo` (
  `medtodo_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '取药单id',
  `record_id` int(11) NOT NULL COMMENT '创建该单的病历报告id',
  `med_id` int(11) NOT NULL COMMENT '药品id',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `available` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `pay_ifdone` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否支付',
  PRIMARY KEY (`medtodo_id`),
  KEY `bus_medtodo_bus_exam_exam_id_fk` (`med_id`),
  KEY `bus_medtodo_bus_record_record_id_fk` (`record_id`),
  CONSTRAINT `bus_medtodo_bus_exam_exam_id_fk` FOREIGN KEY (`med_id`) REFERENCES `bus_medicine` (`med_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_medtodo_bus_record_record_id_fk` FOREIGN KEY (`record_id`) REFERENCES `bus_record` (`record_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生给患者开具的药物单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_medtodo`
--

LOCK TABLES `bus_medtodo` WRITE;
/*!40000 ALTER TABLE `bus_medtodo` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_medtodo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_payment`
--

DROP TABLE IF EXISTS `bus_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '付款单id',
  `paymentitem_id` int(11) NOT NULL COMMENT '缴费项目编号',
  `patient_id` int(11) NOT NULL COMMENT '缴费患者id',
  `info` varchar(255) DEFAULT NULL COMMENT '缴费备注',
  `createtime` datetime NOT NULL COMMENT '缴费单创建时间',
  `donetime` datetime DEFAULT NULL COMMENT '缴费完成时间',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '是否可用，默认1',
  `amount` float NOT NULL COMMENT '缴费项目金额',
  `ifdone` tinyint(1) NOT NULL DEFAULT '0' COMMENT '缴费是否完成',
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `bus_payment_payment_id_uindex` (`payment_id`),
  KEY `bus_payment_sys_user_user_id_fk` (`patient_id`),
  KEY `bus_payment_bus_paymentitem_paymentitem_id_fk` (`paymentitem_id`),
  CONSTRAINT `bus_payment_bus_paymentitem_paymentitem_id_fk` FOREIGN KEY (`paymentitem_id`) REFERENCES `bus_paymentitem` (`paymentitem_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_payment_sys_user_user_id_fk` FOREIGN KEY (`patient_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COMMENT='缴费单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_payment`
--

LOCK TABLES `bus_payment` WRITE;
/*!40000 ALTER TABLE `bus_payment` DISABLE KEYS */;
INSERT INTO `bus_payment` VALUES (1,1,2,NULL,'2020-06-20 00:00:00','2020-06-17 01:15:13',1,23.9,1),(46,1,2,NULL,'2020-06-14 04:13:26','2020-06-17 01:15:13',1,20,1),(48,1,2,NULL,'2020-06-14 04:15:44','2020-06-17 01:15:13',1,20,1),(55,1,2,'挂号医生为李时珍,挂号时间为2020-06-13 20:00:00~2020-06-14 04:00:00','2020-06-14 09:33:28','2020-06-17 01:15:13',1,20,1),(56,1,2,'挂号医生为春野樱,挂号时间为2020-06-13 20:00:00~2020-06-14 04:00:00','2020-06-17 00:43:10','2020-06-17 01:15:13',1,20,1),(57,1,2,'挂号医生为春野樱,挂号时间为2020-06-13 20:00:00~2020-06-14 04:00:00','2020-06-17 00:43:14','2020-06-17 01:15:13',1,20,1),(58,1,2,'挂号医生为张仲景,挂号时间为2020-06-11 20:00:00~2020-06-12 04:00:00','2020-06-17 00:43:18','2020-06-17 01:15:13',1,20,1),(59,1,2,'挂号医生为高露洁,挂号时间为2020-06-13 20:00:00~2020-06-14 04:00:00','2020-06-17 00:43:29','2020-06-17 01:15:13',1,20,1),(60,1,2,'挂号医生为李时珍,挂号时间为2020-06-13 20:00:00~2020-06-14 04:00:00','2020-06-17 00:43:34','2020-06-17 01:15:13',1,20,1),(61,1,2,'挂号医生为张仲景,挂号时间为2020-06-11 20:00:00~2020-06-12 04:00:00','2020-06-17 00:52:26','2020-06-17 01:15:13',1,20,1),(62,1,2,'挂号医生为华佗,挂号时间为2020-06-13 20:00:00~2020-06-14 04:00:00','2020-06-17 00:54:08','2020-06-17 01:15:13',1,20,1),(63,1,2,'挂号医生为张仲景,挂号时间为2020-06-11 20:00:00~2020-06-12 04:00:00','2020-06-17 00:54:45','2020-06-17 01:15:13',1,20,1),(64,1,2,'挂号医生为张仲景,挂号时间为2020-06-11 20:00:00~2020-06-12 04:00:00','2020-06-17 00:56:58','2020-06-17 01:15:13',1,20,1),(65,1,2,'挂号医生为张仲景,挂号时间为2020-06-11 20:00:00~2020-06-12 04:00:00','2020-06-17 00:57:16','2020-06-17 01:15:13',1,20,1),(66,1,2,'挂号医生为张仲景,挂号时间为2020-06-11 20:00:00~2020-06-12 04:00:00','2020-06-17 01:10:10','2020-06-17 01:15:13',1,20,1),(67,1,2,'挂号医生为李一宏,挂号时间为2020-06-13 20:00:00~2020-06-14 04:00:00','2020-06-17 01:10:47','2020-06-17 01:15:13',1,100,1),(68,1,2,'挂号医生为李一宏,挂号时间为2020-06-13 20:00:00~2020-06-14 04:00:00','2020-06-17 01:11:42','2020-06-17 01:15:13',1,100,1),(69,1,2,'挂号医生为张仲景,挂号时间为2020-06-11 20:00:00~2020-06-12 04:00:00','2020-06-17 01:13:33','2020-06-17 01:15:13',1,20,1),(70,1,2,'挂号医生为野原琳,挂号时间为2020-06-13 20:00:00~2020-06-14 04:00:00','2020-06-17 01:13:42','2020-06-17 01:15:13',1,20,1),(71,1,2,'挂号医生为张仲景,挂号时间为2020-06-11 20:00:00~2020-06-12 04:00:00','2020-06-17 01:14:08','2020-06-17 01:15:13',1,20,1),(72,1,2,'挂号医生为张仲景,挂号时间为2020-06-11 20:00:00~2020-06-12 04:00:00','2020-06-17 01:14:51','2020-06-17 01:15:13',1,20,1);
/*!40000 ALTER TABLE `bus_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_paymentitem`
--

DROP TABLE IF EXISTS `bus_paymentitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_paymentitem` (
  `paymentitem_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '付款项目id',
  `paymentitem_name` varchar(20) NOT NULL COMMENT '付款项目名称',
  PRIMARY KEY (`paymentitem_id`),
  UNIQUE KEY `bus_paymentitem_paymentitem_id_uindex` (`paymentitem_id`),
  UNIQUE KEY `bus_paymentitem_paymentitem_name_uindex` (`paymentitem_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='缴费项目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_paymentitem`
--

LOCK TABLES `bus_paymentitem` WRITE;
/*!40000 ALTER TABLE `bus_paymentitem` DISABLE KEYS */;
INSERT INTO `bus_paymentitem` VALUES (1,'挂号'),(2,'检查'),(3,'治疗'),(4,'药品');
/*!40000 ALTER TABLE `bus_paymentitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_record`
--

DROP TABLE IF EXISTS `bus_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用于标识唯一病历id',
  `patient_id` int(11) DEFAULT NULL COMMENT '病人id',
  `doctor_id` int(11) DEFAULT NULL COMMENT '医生id',
  `diagnosis` varchar(255) DEFAULT NULL COMMENT '诊断详情',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `ifexam` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否需要检查',
  `ifdrug` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否需要用药',
  `iftreat` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否需要治疗',
  `available` tinyint(4) DEFAULT '1' COMMENT '是否可用',
  `ifdone` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否完成整个治疗流程',
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `record_id_UNIQUE` (`record_id`),
  KEY `record_ibfk_1_idx` (`patient_id`),
  KEY `record_ibfk_2_idx` (`doctor_id`),
  CONSTRAINT `record_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `sys_user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `record_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `sys_user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='病历记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_record`
--

LOCK TABLES `bus_record` WRITE;
/*!40000 ALTER TABLE `bus_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_register`
--

DROP TABLE IF EXISTS `bus_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_register` (
  `register_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '挂号id',
  `patient_id` int(11) NOT NULL COMMENT '病人id',
  `doctor_id` int(11) NOT NULL COMMENT '医生id',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `payment_id` int(11) NOT NULL COMMENT '付款单id',
  `payment_ifdone` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否付款',
  `available` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `doctortime_id` int(11) NOT NULL COMMENT '医生时间id',
  PRIMARY KEY (`register_id`),
  UNIQUE KEY `bus_register_register_id_uindex` (`register_id`),
  KEY `bus_register_sys_user_id_fk_idx` (`patient_id`),
  KEY `bus_register_sys_user_id_fk1_idx` (`doctor_id`),
  CONSTRAINT `bus_register_sys_user_id_fk1` FOREIGN KEY (`patient_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_register_sys_user_id_fk2` FOREIGN KEY (`doctor_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COMMENT='挂号单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_register`
--

LOCK TABLES `bus_register` WRITE;
/*!40000 ALTER TABLE `bus_register` DISABLE KEYS */;
INSERT INTO `bus_register` VALUES (41,2,12,'2020-06-14 04:13:26',46,0,1,12),(43,2,19,'2020-06-14 04:15:44',48,0,1,17),(50,2,19,'2020-06-14 09:33:28',55,1,1,17),(51,2,17,'2020-06-17 00:43:10',56,1,1,15),(52,2,17,'2020-06-17 00:43:14',57,1,1,15),(53,2,11,'2020-06-17 00:43:18',58,1,1,1),(54,2,18,'2020-06-17 00:43:29',59,1,1,16),(55,2,19,'2020-06-17 00:43:34',60,1,1,17),(56,2,11,'2020-06-17 00:52:26',61,1,1,1),(57,2,13,'2020-06-17 00:54:08',62,1,1,13),(58,2,11,'2020-06-17 00:54:45',63,1,1,1),(59,2,11,'2020-06-17 00:56:58',64,1,1,1),(60,2,11,'2020-06-17 00:57:16',65,1,1,1),(61,2,11,'2020-06-17 01:10:10',66,1,1,1),(62,2,8,'2020-06-17 01:10:47',67,1,1,11),(63,2,8,'2020-06-17 01:11:42',68,1,1,11),(64,2,11,'2020-06-17 01:13:33',69,1,1,1),(65,2,14,'2020-06-17 01:13:42',70,1,1,14),(66,2,11,'2020-06-17 01:14:08',71,1,1,1),(67,2,11,'2020-06-17 01:14:51',72,1,1,1);
/*!40000 ALTER TABLE `bus_register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_registerqueue`
--

DROP TABLE IF EXISTS `bus_registerqueue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_registerqueue` (
  `queue_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '挂号排队id',
  `queue_number` int(11) NOT NULL COMMENT '当前排在第几位',
  `register_id` int(11) NOT NULL COMMENT '挂号单号',
  `available` tinyint(4) DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`queue_id`),
  UNIQUE KEY `bus_registerqueue_queue_id_uindex` (`queue_id`),
  KEY `registerqueue_bus_register_register_id_fk` (`register_id`),
  CONSTRAINT `registerqueue_bus_register_register_id_fk` FOREIGN KEY (`register_id`) REFERENCES `bus_register` (`register_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='排队看病';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_registerqueue`
--

LOCK TABLES `bus_registerqueue` WRITE;
/*!40000 ALTER TABLE `bus_registerqueue` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_registerqueue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_treatdoc`
--

DROP TABLE IF EXISTS `bus_treatdoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_treatdoc` (
  `treatdoc_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '治疗报告文档id',
  `treattodo_id` int(11) DEFAULT NULL COMMENT '生成该报告的治疗单',
  `nurse_id` int(11) NOT NULL COMMENT '治疗医生的id',
  `record_id` int(11) NOT NULL COMMENT '生成该报告的病历',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `available` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `content` varchar(255) DEFAULT NULL COMMENT '治疗的结果',
  `ifdone` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否完成治疗',
  PRIMARY KEY (`treatdoc_id`),
  UNIQUE KEY `bus_treatdoc_treatdoc_id_uindex` (`treatdoc_id`),
  KEY `bus_treatdoc_bus_treattodo_treattodo_id_fk` (`treattodo_id`),
  KEY `bus_treatdoc_bus_record_record_id_fk` (`record_id`),
  KEY `bus_treatdoc_sys_user_id_fk_idx` (`nurse_id`),
  CONSTRAINT `bus_treatdoc_bus_record_record_id_fk` FOREIGN KEY (`record_id`) REFERENCES `bus_record` (`record_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_treatdoc_bus_treattodo_treattodo_id_fk` FOREIGN KEY (`treattodo_id`) REFERENCES `bus_treattodo` (`treattodo_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_treatdoc_sys_user_id_fk` FOREIGN KEY (`nurse_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='治疗报告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_treatdoc`
--

LOCK TABLES `bus_treatdoc` WRITE;
/*!40000 ALTER TABLE `bus_treatdoc` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_treatdoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_treatment`
--

DROP TABLE IF EXISTS `bus_treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_treatment` (
  `treatment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用于标识治疗项目id',
  `treatment_name` varchar(45) NOT NULL COMMENT '治疗项目名称',
  `price` decimal(10,0) NOT NULL COMMENT '每次治疗价格',
  `usage_count` int(11) NOT NULL COMMENT '治疗项目已用次数',
  PRIMARY KEY (`treatment_id`),
  UNIQUE KEY `treatment_id_UNIQUE` (`treatment_id`),
  UNIQUE KEY `treatment_name_UNIQUE` (`treatment_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='治疗项目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_treatment`
--

LOCK TABLES `bus_treatment` WRITE;
/*!40000 ALTER TABLE `bus_treatment` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_treatqueue`
--

DROP TABLE IF EXISTS `bus_treatqueue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_treatqueue` (
  `queue_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '治疗排队id',
  `queue_number` int(11) DEFAULT NULL COMMENT '治疗排队号',
  `treattodo_id` int(11) NOT NULL COMMENT '治疗单id',
  `available` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`queue_id`),
  KEY `bus_treatqueue_bus_treattodo_treattodo_id_fk` (`treattodo_id`),
  CONSTRAINT `bus_treatqueue_bus_treattodo_treattodo_id_fk` FOREIGN KEY (`treattodo_id`) REFERENCES `bus_treattodo` (`treattodo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='排队治疗';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_treatqueue`
--

LOCK TABLES `bus_treatqueue` WRITE;
/*!40000 ALTER TABLE `bus_treatqueue` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_treatqueue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_treattodo`
--

DROP TABLE IF EXISTS `bus_treattodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_treattodo` (
  `treattodo_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '治疗单id',
  `record_id` int(11) NOT NULL COMMENT '创建该治疗的病历报告id',
  `treatment_id` int(11) NOT NULL COMMENT '治疗项目id',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `available` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `pay_ifdone` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否支付',
  PRIMARY KEY (`treattodo_id`),
  KEY `bus_treattodo_bus_exam_exam_id_fk` (`treatment_id`),
  KEY `bus_treattodo_bus_record_record_id_fk` (`record_id`),
  CONSTRAINT `bus_treattodo_bus_exam_exam_id_fk` FOREIGN KEY (`treatment_id`) REFERENCES `bus_treatment` (`treatment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bus_treattodo_bus_record_record_id_fk` FOREIGN KEY (`record_id`) REFERENCES `bus_record` (`record_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='医生给患者开具的待检查项目单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_treattodo`
--

LOCK TABLES `bus_treattodo` WRITE;
/*!40000 ALTER TABLE `bus_treattodo` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_treattodo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `pid` int(11) DEFAULT NULL COMMENT '父部门id',
  `dept_name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `opened` tinyint(1) DEFAULT NULL COMMENT '菜单是否展开',
  `remark` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `address` varchar(255) DEFAULT NULL COMMENT '部门地址',
  `available` tinyint(1) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  `order_num` int(11) DEFAULT NULL COMMENT '排序码【为了调试显示顺序】',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='医院部门';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (1,0,'总院',1,'总院','深圳',1,1,'2019-04-10 14:06:32'),(2,1,'门诊部',0,'门诊部','武汉',1,2,'2019-04-10 14:06:32'),(3,1,'药房',0,'无','武汉',1,3,'2019-04-10 14:06:32'),(4,1,'化验室',0,'无','武汉',1,4,'2019-04-10 14:06:32'),(5,2,'内科',0,'内科','武汉',1,5,'2019-04-10 14:06:32'),(6,2,'外科',0,'外科','武汉',1,6,'2019-04-10 14:06:32'),(7,2,'妇科',0,'妇科','武汉',1,7,'2019-04-10 14:06:32'),(8,2,'眼科',0,'眼科','11',1,8,'2019-04-10 14:06:32'),(9,2,'耳鼻喉科',0,'耳鼻喉科','222',1,9,'2019-04-10 14:06:32'),(10,2,'口腔科',0,'口腔科','33',1,10,'2019-04-10 14:06:32'),(19,2,'中医科',0,'中医科','武汉',1,12,'2019-04-13 09:49:38'),(20,3,'门诊药房',0,'门诊药房','武汉',1,13,'2019-04-13 09:49:38'),(21,3,'急诊药房',0,'急诊药房','武汉',1,14,'2019-04-13 09:49:38'),(22,3,'住院药房',0,'住院药房','武汉',1,15,'2019-04-13 09:49:38'),(23,4,'生化室',0,'生化室','武汉',1,16,'2019-04-13 09:49:38'),(24,4,'病理室',0,'病理室','武汉',1,17,'2019-04-13 09:49:38'),(25,4,'检验室',0,'检验室','武汉',1,18,'2019-04-13 09:49:38');
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_loginfo`
--

DROP TABLE IF EXISTS `sys_loginfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_loginfo` (
  `li_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登陆信息id',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登录名字',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登录ip',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB AUTO_INCREMENT=485 DEFAULT CHARSET=utf8 COMMENT='登录记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_loginfo`
--

LOCK TABLES `sys_loginfo` WRITE;
/*!40000 ALTER TABLE `sys_loginfo` DISABLE KEYS */;
INSERT INTO `sys_loginfo` VALUES (14,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-25 14:45:25'),(15,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-25 15:21:52'),(18,'落亦--luoyi','127.0.0.1','2019-11-25 15:53:00'),(19,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 08:32:41'),(20,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 09:18:06'),(21,'超级管理员-system','0:0:0:0:0:0:0:1','2019-11-26 09:59:19'),(22,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 10:48:05'),(23,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 15:15:03'),(24,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 15:42:02'),(25,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 16:56:54'),(26,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 18:07:44'),(27,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 18:08:08'),(28,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 19:23:12'),(29,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 20:46:57'),(30,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 21:17:48'),(31,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 21:21:16'),(32,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-27 20:13:56'),(33,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-27 20:29:17'),(34,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-27 20:30:38'),(35,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:04:41'),(36,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:25:07'),(37,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:26:50'),(38,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:27:28'),(39,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:28:43'),(40,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:37:27'),(41,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:58:05'),(42,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:59:20'),(43,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 16:16:21'),(44,'管理员-system','127.0.0.1','2020-06-05 04:35:34'),(45,'管理员-system','127.0.0.1','2020-06-05 04:38:14'),(46,'管理员-system','127.0.0.1','2020-06-05 05:32:08'),(47,'普通病人-patient','127.0.0.1','2020-06-05 06:25:05'),(48,'普通病人-patient','127.0.0.1','2020-06-05 08:35:26'),(49,'管理员-system','127.0.0.1','2020-06-05 08:40:22'),(50,'管理员-system','127.0.0.1','2020-06-05 08:47:11'),(51,'管理员-system','127.0.0.1','2020-06-05 09:09:00'),(52,'普通病人-patient','127.0.0.1','2020-06-05 09:09:04'),(53,'普通病人-patient','127.0.0.1','2020-06-05 09:13:30'),(54,'管理员-system','127.0.0.1','2020-06-05 09:13:33'),(55,'管理员-system','127.0.0.1','2020-06-05 09:32:06'),(56,'管理员-system','127.0.0.1','2020-06-05 10:00:19'),(57,'管理员-system','127.0.0.1','2020-06-05 10:04:35'),(58,'普通病人-patient','127.0.0.1','2020-06-05 10:04:47'),(59,'管理员-system','127.0.0.1','2020-06-05 10:33:51'),(60,'管理员-system','127.0.0.1','2020-06-05 10:34:19'),(61,'管理员-system','127.0.0.1','2020-06-05 11:23:10'),(62,'管理员-system','127.0.0.1','2020-06-05 11:39:30'),(63,'管理员-system','127.0.0.1','2020-06-05 11:46:32'),(366,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-12 14:23:26'),(367,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-12 14:25:35'),(368,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-12 15:45:44'),(369,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 14:21:12'),(370,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 14:35:46'),(371,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 14:50:48'),(372,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 14:53:06'),(373,'普通病人-普通病人','127.0.0.1','2020-06-13 14:54:38'),(374,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 14:59:38'),(375,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:00:42'),(376,'普通病人-普通病人','127.0.0.1','2020-06-13 15:04:04'),(377,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:07:23'),(378,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:08:44'),(379,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:09:36'),(380,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:10:41'),(381,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-13 15:19:44'),(382,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:19:52'),(383,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:22:39'),(384,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:24:33'),(385,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:32:34'),(386,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:37:53'),(387,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-13 15:41:50'),(388,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:41:58'),(389,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:45:51'),(390,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:47:39'),(391,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 15:50:18'),(392,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 16:03:42'),(393,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 16:09:17'),(394,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 16:10:06'),(395,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 16:15:16'),(396,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 16:16:18'),(397,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 16:17:16'),(398,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 16:17:55'),(399,'普通病人-普通病人','127.0.0.1','2020-06-13 16:18:41'),(400,'普通病人-普通病人','127.0.0.1','2020-06-13 16:20:12'),(401,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 16:21:11'),(402,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 16:23:31'),(403,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 16:38:46'),(404,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 16:44:30'),(405,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 16:58:17'),(406,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:00:33'),(407,'普通病人-普通病人','127.0.0.1','2020-06-13 17:02:21'),(408,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:03:40'),(409,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-13 17:05:09'),(410,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:05:19'),(411,'普通病人-普通病人','127.0.0.1','2020-06-13 17:06:18'),(412,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:11:45'),(413,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:15:15'),(414,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:31:42'),(415,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:40:40'),(416,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:47:24'),(417,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:48:42'),(418,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:50:30'),(419,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:54:05'),(420,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:55:39'),(421,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:57:39'),(422,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 17:58:42'),(423,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 18:00:51'),(424,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 18:01:52'),(425,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 18:03:49'),(426,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 18:08:07'),(427,'普通病人-普通病人','127.0.0.1','2020-06-13 18:08:56'),(428,'普通病人-普通病人','127.0.0.1','2020-06-13 18:09:46'),(429,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 18:12:28'),(430,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 18:15:14'),(431,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 18:16:39'),(432,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 18:17:45'),(433,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 18:19:03'),(434,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 18:20:02'),(435,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 18:22:56'),(436,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 18:27:00'),(437,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-13 18:28:42'),(438,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 02:57:59'),(439,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 03:06:58'),(440,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 03:09:31'),(441,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 03:11:40'),(442,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 03:22:09'),(443,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 03:32:24'),(444,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 03:35:52'),(445,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-14 03:41:46'),(446,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-14 03:47:58'),(447,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-14 03:49:36'),(448,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-14 03:55:07'),(449,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 03:57:53'),(450,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 04:00:51'),(451,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 04:12:58'),(452,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 04:15:31'),(453,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 04:17:16'),(454,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 04:20:34'),(455,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 04:21:54'),(456,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 04:28:03'),(457,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 08:55:12'),(458,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 09:22:36'),(459,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 09:28:36'),(460,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 09:31:57'),(461,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 09:33:20'),(462,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 09:41:52'),(463,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-14 09:57:38'),(464,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 09:59:35'),(465,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 10:08:12'),(466,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 10:20:30'),(467,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-14 10:21:21'),(468,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 10:21:58'),(469,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 10:28:32'),(470,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 10:36:21'),(471,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-14 10:39:40'),(472,'普通病人-普通病人','0:0:0:0:0:0:0:1','2020-06-15 15:04:18'),(473,'李一宏-李一宏','0:0:0:0:0:0:0:1','2020-06-15 15:04:29'),(474,'普通病人-普通病人','127.0.0.1','2020-06-17 00:43:02'),(475,'普通病人-普通病人','127.0.0.1','2020-06-17 00:51:36'),(476,'普通病人-普通病人','127.0.0.1','2020-06-17 00:54:00'),(477,'普通病人-普通病人','127.0.0.1','2020-06-17 00:56:50'),(478,'普通病人-普通病人','127.0.0.1','2020-06-17 01:06:38'),(479,'普通病人-普通病人','127.0.0.1','2020-06-17 01:10:03'),(480,'普通病人-普通病人','127.0.0.1','2020-06-17 01:11:33'),(481,'普通病人-普通病人','127.0.0.1','2020-06-17 01:13:23'),(482,'普通病人-普通病人','127.0.0.1','2020-06-17 01:13:51'),(483,'普通病人-普通病人','127.0.0.1','2020-06-17 01:24:24'),(484,'普通病人-普通病人','127.0.0.1','2020-06-17 01:29:24');
/*!40000 ALTER TABLE `sys_loginfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `oper_name` varchar(255) DEFAULT NULL COMMENT '发布人姓名',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COMMENT='系统通知';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
INSERT INTO `sys_notice` VALUES (54,'afds','dasf','2020-03-08 03:48:47','落亦-'),(55,'测试','测试','2020-03-08 03:53:03','落亦-'),(56,'sadf','asdf','2020-03-08 04:17:44','落亦-'),(57,'1','123','2020-05-26 10:40:14','超级管理员');
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `pid` int(11) DEFAULT NULL COMMENT '父权限id',
  `type` varchar(255) DEFAULT NULL COMMENT '权限类型[menu/permission]',
  `title` varchar(255) DEFAULT NULL COMMENT '权限标题',
  `per_code` varchar(255) DEFAULT NULL COMMENT '权限编码[只有type= permission才有  user:view]',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `href` varchar(255) DEFAULT NULL COMMENT '视图链接',
  `target` varchar(255) DEFAULT NULL COMMENT '目标',
  `opened` tinyint(4) DEFAULT NULL COMMENT '菜单是否展开',
  `order_num` int(11) DEFAULT NULL COMMENT '菜单顺序',
  `available` tinyint(4) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COMMENT='用户权限管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` VALUES (1,0,'menu','仓库管理系统',NULL,'&#xe68e;','','',1,1,1),(2,1,'menu','挂号系统',NULL,'&#xe857;','','',0,2,1),(3,1,'menu','就诊系统',NULL,'&#xe645;','',NULL,0,3,1),(4,1,'menu','销售管理',NULL,'&#xe611;','','',0,4,1),(5,1,'menu','系统管理',NULL,'&#xe614;','','',1,5,1),(6,1,'menu','其它管理',NULL,'&#xe628;','','',0,6,1),(7,2,'menu','挂号提交',NULL,'&#xe651;','/bus/patient/toDoctorTimeManager','',0,7,1),(8,2,'menu','挂号管理',NULL,'&#xe658;','/bus/patient/toRegisterManager','',0,8,1),(9,1,'menu','缴费系统',NULL,'&#xe657;','/bus/patient/toPaymentManager','',0,9,1),(10,3,'menu','加入队列',NULL,'&#xe756;','/bus/patient/toRegisterQueue','',0,10,1),(11,3,'menu','病历记录',NULL,'&#xe65a;','/bus/patient/toRecordManager','',0,11,1),(12,4,'menu','商品销售',NULL,'&#xe65b;','/bus/toSalesManager','',0,12,1),(13,4,'menu','销售退货查询',NULL,'&#xe770;','/bus/toSalesbackManager','',0,13,1),(14,5,'menu','部门管理',NULL,'&#xe770;','/sys/toDeptManager','',0,14,1),(15,5,'menu','菜单管理',NULL,'&#xe663;','/sys/toMenuManager','',0,15,1),(16,5,'menu','权限管理','','&#xe857;','/sys/toPermissionManager','',0,16,1),(17,5,'menu','角色管理','','&#xe650;','/sys/toRoleManager','',0,17,1),(18,5,'menu','医生管理','','&#xe612;','/sys/toDoctorManager','',0,18,1),(19,5,'menu','病人管理',NULL,'&#xe675;','/sys/toPatientManager','',0,21,1),(21,6,'menu','登陆日志',NULL,'&#xe675;','/sys/toLoginfoManager','',0,21,1),(22,6,'menu','系统公告',NULL,'&#xe756;','/sys/toNoticeManager',NULL,0,22,1),(23,6,'menu','图标管理',NULL,'&#xe670;','../resources/page/icon.html',NULL,1,23,1),(30,14,'permission','添加部门','dept:create','',NULL,NULL,0,24,1),(31,14,'permission','修改部门','dept:update','',NULL,NULL,0,26,1),(32,14,'permission','删除部门','dept:delete','',NULL,NULL,0,27,1),(34,15,'permission','添加菜单','menu:create','','','',0,29,1),(35,15,'permission','修改菜单','menu:update','',NULL,NULL,0,30,1),(36,15,'permission','删除菜单','menu:delete','',NULL,NULL,0,31,1),(38,16,'permission','添加权限','permission:create','',NULL,NULL,0,33,1),(39,16,'permission','修改权限','permission:update','',NULL,NULL,0,34,1),(40,16,'permission','删除权限','permission:delete','',NULL,NULL,0,35,1),(42,17,'permission','添加角色','role:create','',NULL,NULL,0,37,1),(43,17,'permission','修改角色','role:update','',NULL,NULL,0,38,1),(44,17,'permission','删除角色','role:delete','',NULL,NULL,0,39,1),(46,17,'permission','分配权限','role:selectPermission','',NULL,NULL,0,41,1),(47,18,'permission','添加用户','user:create','',NULL,NULL,0,42,1),(48,18,'permission','修改用户','user:update','',NULL,NULL,0,43,1),(49,18,'permission','删除用户','user:delete','',NULL,NULL,0,44,1),(51,18,'permission','用户分配角色','user:selectRole','',NULL,NULL,0,46,1),(52,18,'permission','重置密码','user:resetPwd',NULL,NULL,NULL,0,47,1),(53,14,'permission','部门查询','dept:view',NULL,NULL,NULL,0,48,1),(54,15,'permission','菜单查询','menu:view',NULL,NULL,NULL,0,49,1),(55,16,'permission','权限查询','permission:view',NULL,NULL,NULL,0,50,1),(56,17,'permission','角色查询','role:view',NULL,NULL,NULL,0,51,1),(57,18,'permission','用户查询','user:view',NULL,NULL,NULL,0,52,1),(68,7,'permission','客户查询','customer:view',NULL,NULL,NULL,NULL,60,1),(69,7,'permission','客户添加','customer:create',NULL,NULL,NULL,NULL,61,1),(70,7,'permission','客户修改','customer:update',NULL,NULL,NULL,NULL,62,1),(71,7,'permission','客户删除','customer:delete',NULL,NULL,NULL,NULL,63,1),(73,21,'permission','日志查询','info:view',NULL,NULL,NULL,NULL,65,1),(74,21,'permission','日志删除','info:delete',NULL,NULL,NULL,NULL,66,1),(75,21,'permission','日志批量删除','info:batchdelete',NULL,NULL,NULL,NULL,67,1),(76,22,'permission','公告查询','notice:view',NULL,NULL,NULL,NULL,68,1),(77,22,'permission','公告添加','notice:create',NULL,NULL,NULL,NULL,69,1),(78,22,'permission','公告修改','notice:update',NULL,NULL,NULL,NULL,70,1),(79,22,'permission','公告删除','notice:delete',NULL,NULL,NULL,NULL,71,1),(81,8,'permission','供应商查询','provider:view',NULL,NULL,NULL,NULL,73,1),(82,8,'permission','供应商添加','provider:create',NULL,NULL,NULL,NULL,74,1),(83,8,'permission','供应商修改','provider:update',NULL,NULL,NULL,NULL,75,1),(84,8,'permission','供应商删除','provider:delete',NULL,NULL,NULL,NULL,76,1),(86,22,'permission','公告查看','notice:viewnotice',NULL,NULL,NULL,NULL,78,1),(91,9,'permission','商品查询','goods:view',NULL,NULL,NULL,0,79,1),(92,9,'permission','商品添加','goods:create',NULL,NULL,NULL,0,80,1),(116,9,'permission','商品删除','goods:delete',NULL,NULL,NULL,0,84,1),(117,9,'permission','商品修改','goods:update',NULL,NULL,NULL,0,85,1),(118,9,'permission','商品查询','goods:view',NULL,NULL,NULL,0,86,1),(119,22,'permission','公告批量删除','notice:batchdelete',NULL,NULL,NULL,0,87,1),(122,6,'menu','缓存管理',NULL,'&#xe630','/sys/toCacheManager','_black',1,88,1),(123,122,'permission','同步缓存','cache:syncCache',NULL,NULL,NULL,0,89,1),(124,122,'permission','清空缓存','cache:removeAllCache',NULL,NULL,NULL,0,90,1),(125,3,'menu','管理排队',NULL,'&#xe756;','/bus/doctor/toRegisterQueue','',0,10,1),(126,3,'menu','病历记录',NULL,'&#xe65a;','/bus/doctor/toRecordManager','',0,11,1),(127,3,'menu','待检查项',NULL,'&#xe651;','/bus/patient/toExamToDoManager','',0,7,1),(128,3,'menu','待取药项',NULL,'&#xe65b;','/bus/patient/toMedToDoManager','',0,7,1),(129,3,'menu','待治疗项',NULL,'&#xe770;','/bus/patient/toTREATToDoManager','',0,7,1),(130,3,'menu','待检查项',NULL,'&#xe651;','/bus/doctor/toExamToDoManager','',0,7,1),(131,3,'menu','待取药项',NULL,'&#xe65b;','/bus/doctor/toMedToDoManager','',0,7,1),(132,3,'menu','待治疗项',NULL,'&#xe770;','/bus/doctor/toTREATToDoManager','',0,7,1);
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `role_code` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '角色权限码',
  `role_name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '角色权限名',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id_UNIQUE` (`role_id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'DOCTOR','门诊医师'),(2,'PHARMACIST','药剂医师'),(3,'LABORATORIAN','检验医师'),(4,'NURSE','护士'),(5,'PATIENT','患者'),(6,'ADMIN','系统管理员');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_role_permission` (
  `r_p_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`r_p_id`),
  UNIQUE KEY `r_p_id_UNIQUE` (`r_p_id`),
  UNIQUE KEY `role_permission_UNIQUE` (`role_id`,`permission_id`),
  KEY `ibfk_2_idx` (`permission_id`),
  KEY `ibfk_1_idx` (`role_id`),
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`permission_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='角色-权限连接';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` VALUES (97,1,3),(101,1,125),(102,1,126),(98,1,130),(99,1,131),(100,1,132),(69,5,2),(93,5,3),(74,5,7),(75,5,8),(76,5,9),(91,5,10),(92,5,11),(94,5,127),(95,5,128),(96,5,129),(68,6,1),(70,6,3),(71,6,4),(72,6,5),(73,6,6),(77,6,10),(78,6,11),(79,6,12),(80,6,13),(81,6,14),(82,6,15),(83,6,16),(84,6,17),(85,6,18),(90,6,19),(86,6,21),(87,6,22),(88,6,23),(89,6,122);
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) NOT NULL COMMENT '真实姓名',
  `login_name` varchar(255) NOT NULL COMMENT '登录名',
  `user_password` varchar(255) NOT NULL COMMENT '密码',
  `gender` int(11) NOT NULL COMMENT '性别.1为男，0为女',
  `id_num` varchar(255) NOT NULL COMMENT '身份证号码',
  `med_num` varchar(255) DEFAULT NULL COMMENT '医保卡号',
  `addr` varchar(255) NOT NULL COMMENT '地址',
  `dept_id` int(11) DEFAULT NULL COMMENT '医生的部门id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色。0为管理员，1为门诊医生，2为药剂医生，3为检验医师，4为护士，5为病人',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话号码',
  `info` varchar(255) DEFAULT NULL COMMENT '医生个人信息',
  `birthday` date NOT NULL COMMENT '生日',
  `job` varchar(255) DEFAULT NULL COMMENT '病人工作',
  `available` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1为可用，0为不可用',
  `job_title` varchar(255) DEFAULT NULL COMMENT '医生职称',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `sys_user_id_uindex` (`user_id`),
  UNIQUE KEY `sys_user_loginname_uindex` (`login_name`),
  UNIQUE KEY `id_num_UNIQUE` (`id_num`),
  UNIQUE KEY `med_num_UNIQUE` (`med_num`),
  KEY `sys_user_ibfk_1_idx` (`dept_id`),
  KEY `sys_user_ibfk_1_idx1` (`role_id`),
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`dept_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `sys_user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'管理员','system','e10adc3949ba59abbe56e057f20f883e',1,'1','1','木叶村',1,6,'1311',NULL,'1999-03-07',NULL,1,NULL),(2,'普通病人','patient','e10adc3949ba59abbe56e057f20f883e',0,'2','2','贝岗',NULL,5,'2422',NULL,'1999-04-28',NULL,1,NULL),(8,'李一宏','liyihong','e10adc3949ba59abbe56e057f20f883e',1,'123',NULL,'岩隐村',7,1,NULL,'','2020-05-23',NULL,1,''),(11,'张仲景','zhangzhongjing','e10adc3949ba59abbe56e057f20f883e',1,'1265',NULL,'小谷围',5,1,'8967',NULL,'2020-06-12',NULL,1,'见习'),(12,'钟南山','zhongnanshan','e10adc3949ba59abbe56e057f20f883e',1,'12234','124124','小谷围',5,1,'1111',NULL,'2020-06-12',NULL,1,NULL),(13,'华佗','huatuo','e10adc3949ba59abbe56e057f20f883e',1,'124','453','小谷围',6,1,'453',NULL,'2020-06-12',NULL,1,NULL),(14,'野原琳','yeyuanlin','e10adc3949ba59abbe56e057f20f883e',0,'yeyuanlin','yeyuanlin','yeyuanlin',8,1,'yeyuanlin',NULL,'2020-06-12',NULL,1,NULL),(17,'春野樱','chunyeying','e10adc3949ba59abbe56e057f20f883e',0,'chunyeying','chunyeying','chunyeying',6,1,'chunyeying',NULL,'2020-06-12',NULL,1,NULL),(18,'高露洁','gaolujie','e10adc3949ba59abbe56e057f20f883e',0,'gaolujie','gaolujie','gaolujie',10,1,'gaolujie',NULL,'2020-06-12',NULL,1,NULL),(19,'李时珍','lishizhen','e10adc3949ba59abbe56e057f20f883e',0,'lishizhen','lishizhen','lishizhen',19,1,'lishizhen',NULL,'2020-06-12',NULL,1,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-17  9:32:00