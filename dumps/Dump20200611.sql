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
  `doctortime_id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `startime` datetime NOT NULL COMMENT '工作时段开始',
  `endtime` datetime NOT NULL COMMENT '工作时段结束',
  `remain` int(11) NOT NULL DEFAULT '20' COMMENT '余号',
  `price` float NOT NULL COMMENT '挂号费用',
  PRIMARY KEY (`doctortime_id`),
  UNIQUE KEY `bus_doctortime_doctortime_id_uindex` (`doctortime_id`),
  KEY `bus_doctortime_sys_user_user_id_fk` (`doctor_id`),
  CONSTRAINT `bus_doctortime_sys_user_user_id_fk` FOREIGN KEY (`doctor_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='医生工作时间';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_doctortime`
--

LOCK TABLES `bus_doctortime` WRITE;
/*!40000 ALTER TABLE `bus_doctortime` DISABLE KEYS */;
INSERT INTO `bus_doctortime` VALUES (1,11,'2020-06-11 12:00:00','2020-06-11 20:00:00',20,20);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_exam`
--

LOCK TABLES `bus_exam` WRITE;
/*!40000 ALTER TABLE `bus_exam` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_exam` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_medicine`
--

LOCK TABLES `bus_medicine` WRITE;
/*!40000 ALTER TABLE `bus_medicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_payment`
--

DROP TABLE IF EXISTS `bus_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='缴费单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_payment`
--

LOCK TABLES `bus_payment` WRITE;
/*!40000 ALTER TABLE `bus_payment` DISABLE KEYS */;
INSERT INTO `bus_payment` VALUES (1,1,2,NULL,'2020-06-20 00:00:00',NULL,1,23.9,0);
/*!40000 ALTER TABLE `bus_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_paymentitem`
--

DROP TABLE IF EXISTS `bus_paymentitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bus_paymentitem` (
  `paymentitem_id` int(11) NOT NULL AUTO_INCREMENT,
  `paymentitem_name` varchar(20) NOT NULL,
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
  `patient_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `diagnosis` varchar(255) DEFAULT NULL,
  `prescription` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `exam_report` varchar(255) DEFAULT NULL,
  `paid_up` tinyint(4) DEFAULT '0',
  `charge` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `record_id_UNIQUE` (`record_id`),
  KEY `record_ibfk_1_idx` (`patient_id`),
  KEY `record_ibfk_2_idx` (`doctor_id`),
  CONSTRAINT `record_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `sys_user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `record_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `sys_user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `register_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  `payment_id` int(11) NOT NULL,
  `payment_ifdone` tinyint(1) NOT NULL DEFAULT '0',
  `available` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`register_id`),
  UNIQUE KEY `bus_register_register_id_uindex` (`register_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='挂号单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_register`
--

LOCK TABLES `bus_register` WRITE;
/*!40000 ALTER TABLE `bus_register` DISABLE KEYS */;
INSERT INTO `bus_register` VALUES (1,2,3,'2020-06-11 00:00:00',1,0,1);
/*!40000 ALTER TABLE `bus_register` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_treatment`
--

LOCK TABLES `bus_treatment` WRITE;
/*!40000 ALTER TABLE `bus_treatment` DISABLE KEYS */;
/*!40000 ALTER TABLE `bus_treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  `opened` tinyint(1) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  `order_num` int(11) DEFAULT NULL COMMENT '排序码【为了调试显示顺序】',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
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
  `li_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) DEFAULT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB AUTO_INCREMENT=317 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_loginfo`
--

LOCK TABLES `sys_loginfo` WRITE;
/*!40000 ALTER TABLE `sys_loginfo` DISABLE KEYS */;
INSERT INTO `sys_loginfo` VALUES (14,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-25 14:45:25'),(15,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-25 15:21:52'),(18,'落亦--luoyi','127.0.0.1','2019-11-25 15:53:00'),(19,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 08:32:41'),(20,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 09:18:06'),(21,'超级管理员-system','0:0:0:0:0:0:0:1','2019-11-26 09:59:19'),(22,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 10:48:05'),(23,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 15:15:03'),(24,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 15:42:02'),(25,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 16:56:54'),(26,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 18:07:44'),(27,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 18:08:08'),(28,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 19:23:12'),(29,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 20:46:57'),(30,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 21:17:48'),(31,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-26 21:21:16'),(32,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-27 20:13:56'),(33,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-27 20:29:17'),(34,'落亦--luoyi','0:0:0:0:0:0:0:1','2019-11-27 20:30:38'),(35,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:04:41'),(36,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:25:07'),(37,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:26:50'),(38,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:27:28'),(39,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:28:43'),(40,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:37:27'),(41,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:58:05'),(42,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 14:59:20'),(43,'管理员-system','0:0:0:0:0:0:0:1','2020-06-02 16:16:21'),(44,'管理员-system','127.0.0.1','2020-06-05 04:35:34'),(45,'管理员-system','127.0.0.1','2020-06-05 04:38:14'),(46,'管理员-system','127.0.0.1','2020-06-05 05:32:08'),(47,'普通病人-patient','127.0.0.1','2020-06-05 06:25:05'),(48,'普通病人-patient','127.0.0.1','2020-06-05 08:35:26'),(49,'管理员-system','127.0.0.1','2020-06-05 08:40:22'),(50,'管理员-system','127.0.0.1','2020-06-05 08:47:11'),(51,'管理员-system','127.0.0.1','2020-06-05 09:09:00'),(52,'普通病人-patient','127.0.0.1','2020-06-05 09:09:04'),(53,'普通病人-patient','127.0.0.1','2020-06-05 09:13:30'),(54,'管理员-system','127.0.0.1','2020-06-05 09:13:33'),(55,'管理员-system','127.0.0.1','2020-06-05 09:32:06'),(56,'管理员-system','127.0.0.1','2020-06-05 10:00:19'),(57,'管理员-system','127.0.0.1','2020-06-05 10:04:35'),(58,'普通病人-patient','127.0.0.1','2020-06-05 10:04:47'),(59,'管理员-system','127.0.0.1','2020-06-05 10:33:51'),(60,'管理员-system','127.0.0.1','2020-06-05 10:34:19'),(61,'管理员-system','127.0.0.1','2020-06-05 11:23:10'),(62,'管理员-system','127.0.0.1','2020-06-05 11:39:30'),(63,'管理员-system','127.0.0.1','2020-06-05 11:46:32'),(64,'管理员-system','127.0.0.1','2020-06-05 12:09:53'),(65,'普通病人-patient','127.0.0.1','2020-06-05 12:09:58'),(66,'普通病人-patient','127.0.0.1','2020-06-05 12:10:46'),(67,'普通病人-patient','127.0.0.1','2020-06-05 12:13:04'),(68,'普通病人-patient','127.0.0.1','2020-06-05 12:13:07'),(69,'管理员-system','127.0.0.1','2020-06-05 12:13:17'),(70,'管理员-system','127.0.0.1','2020-06-05 12:13:20'),(71,'管理员-system','127.0.0.1','2020-06-05 12:13:22'),(72,'管理员-system','127.0.0.1','2020-06-05 12:13:28'),(73,'管理员-system','127.0.0.1','2020-06-05 12:13:32'),(74,'普通病人-patient','127.0.0.1','2020-06-05 12:13:34'),(75,'普通病人-patient','127.0.0.1','2020-06-05 12:13:44'),(76,'普通病人-patient','127.0.0.1','2020-06-05 12:14:53'),(77,'管理员-system','127.0.0.1','2020-06-05 12:15:02'),(78,'普通病人-patient','127.0.0.1','2020-06-05 12:15:06'),(79,'普通病人-patient','127.0.0.1','2020-06-05 12:22:37'),(80,'普通病人-patient','127.0.0.1','2020-06-05 12:22:40'),(81,'管理员-system','127.0.0.1','2020-06-05 12:23:55'),(82,'管理员-system','127.0.0.1','2020-06-05 12:23:58'),(83,'普通病人-patient','127.0.0.1','2020-06-05 12:24:05'),(84,'普通病人-patient','127.0.0.1','2020-06-05 12:55:34'),(85,'管理员-system','127.0.0.1','2020-06-05 13:11:32'),(86,'普通病人-patient','127.0.0.1','2020-06-05 13:11:44'),(87,'管理员-system','127.0.0.1','2020-06-05 13:11:50'),(88,'普通病人-patient','127.0.0.1','2020-06-05 13:12:43'),(89,'管理员-system','127.0.0.1','2020-06-05 13:23:32'),(90,'普通病人-patient','127.0.0.1','2020-06-05 13:23:41'),(91,'普通病人-patient','127.0.0.1','2020-06-05 13:27:22'),(92,'管理员-system','127.0.0.1','2020-06-05 13:27:28'),(93,'管理员-system','127.0.0.1','2020-06-05 13:27:38'),(94,'管理员-system','127.0.0.1','2020-06-05 13:28:17'),(95,'普通病人-patient','127.0.0.1','2020-06-05 13:28:21'),(96,'普通病人-patient','127.0.0.1','2020-06-05 13:28:31'),(97,'管理员-system','127.0.0.1','2020-06-05 13:28:37'),(98,'普通病人-patient','127.0.0.1','2020-06-05 13:28:42'),(99,'管理员-system','127.0.0.1','2020-06-05 13:28:48'),(100,'管理员-system','127.0.0.1','2020-06-05 13:29:28'),(101,'管理员-system','127.0.0.1','2020-06-05 13:29:42'),(102,'普通病人-patient','127.0.0.1','2020-06-05 13:29:47'),(103,'普通病人-patient','127.0.0.1','2020-06-05 13:32:00'),(104,'普通病人-patient','127.0.0.1','2020-06-05 13:33:33'),(105,'管理员-system','127.0.0.1','2020-06-05 13:33:37'),(106,'管理员-system','127.0.0.1','2020-06-05 13:36:37'),(107,'普通病人-patient','127.0.0.1','2020-06-05 13:36:42'),(108,'管理员-system','127.0.0.1','2020-06-05 13:36:57'),(109,'管理员-system','127.0.0.1','2020-06-05 23:40:56'),(110,'管理员-system','127.0.0.1','2020-06-05 23:44:13'),(111,'管理员-system','127.0.0.1','2020-06-06 00:24:45'),(112,'普通病人-patient','127.0.0.1','2020-06-06 00:53:03'),(113,'管理员-system','127.0.0.1','2020-06-06 00:53:07'),(114,'管理员-system','127.0.0.1','2020-06-06 00:53:14'),(115,'管理员-system','127.0.0.1','2020-06-06 00:55:24'),(116,'管理员-system','127.0.0.1','2020-06-06 01:04:06'),(117,'管理员-system','127.0.0.1','2020-06-06 01:39:34'),(118,'管理员-system','127.0.0.1','2020-06-07 09:48:59'),(119,'管理员-system','127.0.0.1','2020-06-07 09:49:34'),(120,'普通病人-patient','127.0.0.1','2020-06-07 09:49:39'),(121,'管理员-system','127.0.0.1','2020-06-08 01:39:41'),(122,'管理员-system','127.0.0.1','2020-06-08 01:44:25'),(123,'管理员-system','127.0.0.1','2020-06-08 01:53:12'),(124,'管理员-system','127.0.0.1','2020-06-08 01:58:37'),(125,'管理员-system','127.0.0.1','2020-06-08 02:01:21'),(126,'管理员-system','127.0.0.1','2020-06-08 02:05:50'),(127,'管理员-system','127.0.0.1','2020-06-08 02:14:13'),(128,'管理员-system','127.0.0.1','2020-06-08 02:26:12'),(129,'管理员-system','127.0.0.1','2020-06-08 02:26:25'),(130,'管理员-system','127.0.0.1','2020-06-08 02:26:34'),(131,'管理员-system','127.0.0.1','2020-06-08 02:26:36'),(132,'管理员-system','127.0.0.1','2020-06-08 02:26:38'),(133,'管理员-system','127.0.0.1','2020-06-08 02:35:52'),(134,'普通病人-patient','127.0.0.1','2020-06-08 02:36:25'),(135,'管理员-system','127.0.0.1','2020-06-08 02:36:33'),(136,'普通病人-patient','127.0.0.1','2020-06-08 02:36:37'),(137,'管理员-system','127.0.0.1','2020-06-08 02:36:39'),(138,'管理员-system','127.0.0.1','2020-06-08 02:36:52'),(139,'普通病人-patient','127.0.0.1','2020-06-08 02:37:40'),(140,'普通病人-patient','127.0.0.1','2020-06-08 02:38:03'),(141,'管理员-system','127.0.0.1','2020-06-08 02:38:05'),(142,'管理员-system','127.0.0.1','2020-06-08 02:38:10'),(143,'普通病人-patient','127.0.0.1','2020-06-08 02:38:11'),(144,'管理员-system','127.0.0.1','2020-06-08 02:56:15'),(145,'管理员-system','127.0.0.1','2020-06-08 08:54:20'),(146,'管理员-system','127.0.0.1','2020-06-08 09:03:46'),(147,'xxxx-system11111','127.0.0.1','2020-06-08 09:15:51'),(148,'管理员-system','127.0.0.1','2020-06-08 09:18:59'),(149,'管理员-system','127.0.0.1','2020-06-08 09:21:36'),(150,'管理员-system','127.0.0.1','2020-06-08 09:27:25'),(151,'管理员-system','127.0.0.1','2020-06-08 09:33:26'),(152,'管理员-system','127.0.0.1','2020-06-08 09:36:28'),(153,'李一宏-李一宏','127.0.0.1','2020-06-08 09:49:28'),(154,'管理员-管理员','127.0.0.1','2020-06-08 09:50:36'),(155,'管理员-管理员','127.0.0.1','2020-06-08 09:58:04'),(156,'管理员-system','127.0.0.1','2020-06-08 10:07:12'),(157,'管理员-system','127.0.0.1','2020-06-08 10:09:45'),(158,'管理员-管理员','127.0.0.1','2020-06-08 10:11:32'),(159,'管理员-管理员','127.0.0.1','2020-06-08 10:15:17'),(160,'管理员-管理员','127.0.0.1','2020-06-08 10:17:02'),(161,'管理员-管理员','127.0.0.1','2020-06-08 10:18:52'),(162,'管理员-管理员','127.0.0.1','2020-06-08 10:21:51'),(163,'管理员-管理员','127.0.0.1','2020-06-08 10:29:48'),(164,'管理员-管理员','127.0.0.1','2020-06-08 10:30:16'),(165,'管理员-管理员','127.0.0.1','2020-06-08 10:32:02'),(166,'管理员-管理员','127.0.0.1','2020-06-08 10:34:05'),(167,'管理员-管理员','127.0.0.1','2020-06-08 10:35:46'),(168,'管理员-管理员','127.0.0.1','2020-06-08 10:39:40'),(169,'管理员-管理员','127.0.0.1','2020-06-08 10:43:19'),(170,'管理员-管理员','127.0.0.1','2020-06-08 10:45:46'),(171,'管理员-管理员','127.0.0.1','2020-06-08 10:47:54'),(172,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 09:55:34'),(173,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 13:41:48'),(174,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 14:34:15'),(175,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 14:36:37'),(176,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 14:39:37'),(177,'管理员-管理员','127.0.0.1','2020-06-09 14:40:43'),(178,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 14:47:08'),(179,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 14:49:11'),(180,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 14:50:31'),(181,'管理员-管理员','127.0.0.1','2020-06-09 14:54:16'),(182,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 14:55:18'),(183,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 14:57:36'),(184,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:00:13'),(185,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:01:53'),(186,'管理员-管理员','127.0.0.1','2020-06-09 15:04:49'),(187,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:05:57'),(188,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:08:09'),(189,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:12:27'),(190,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:14:59'),(191,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:16:07'),(192,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:18:41'),(193,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:22:08'),(194,'管理员-管理员','127.0.0.1','2020-06-09 15:23:16'),(195,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:30:56'),(196,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:33:16'),(197,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:39:20'),(198,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:42:01'),(199,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-09 15:42:53'),(200,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 02:35:49'),(201,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 02:46:44'),(202,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 03:10:03'),(203,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 03:25:03'),(204,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 03:25:58'),(205,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 03:30:39'),(206,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 03:32:08'),(207,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 03:39:34'),(208,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 03:45:30'),(209,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 03:47:03'),(210,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 03:52:42'),(211,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 03:56:42'),(212,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 04:07:17'),(213,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 04:13:21'),(214,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 04:17:10'),(215,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 04:19:29'),(216,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 04:22:05'),(217,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 04:47:11'),(218,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 06:05:23'),(219,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 06:05:27'),(220,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 06:08:14'),(221,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 06:09:49'),(222,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 06:20:45'),(223,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 06:26:25'),(224,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 06:27:40'),(225,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 06:39:09'),(226,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 06:43:58'),(227,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 06:50:16'),(228,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 06:53:05'),(229,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 06:55:32'),(230,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 06:58:56'),(231,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:00:14'),(232,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:04:44'),(233,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:07:08'),(234,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:28:40'),(235,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:29:44'),(236,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:30:25'),(237,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:32:46'),(238,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:34:25'),(239,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:36:55'),(240,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:39:12'),(241,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:41:35'),(242,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:46:34'),(243,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:52:28'),(244,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:54:33'),(245,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 07:59:41'),(246,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 08:02:09'),(247,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 08:03:53'),(248,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:11:41'),(249,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:15:32'),(250,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:16:20'),(251,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:19:14'),(252,'管理员-管理员','127.0.0.1','2020-06-10 09:21:46'),(253,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:24:26'),(254,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:26:28'),(255,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:27:21'),(256,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:28:49'),(257,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:39:53'),(258,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:44:16'),(259,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:48:14'),(260,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:49:37'),(261,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:51:38'),(262,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:54:01'),(263,'管理员-管理员','127.0.0.1','2020-06-10 09:56:46'),(264,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 09:57:47'),(265,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 10:07:33'),(266,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 10:16:59'),(267,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 10:19:05'),(268,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 10:21:24'),(269,'管理员-管理员','127.0.0.1','2020-06-10 10:22:25'),(270,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 10:35:36'),(271,'管理员-管理员','127.0.0.1','2020-06-10 10:39:08'),(272,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 10:48:43'),(273,'管理员-管理员','127.0.0.1','2020-06-10 10:52:33'),(274,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 10:55:39'),(275,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 10:57:03'),(276,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 10:58:02'),(277,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 11:02:11'),(278,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 11:03:29'),(279,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 11:05:52'),(280,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 11:06:51'),(281,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 11:11:02'),(282,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 11:11:47'),(283,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 11:13:23'),(284,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 11:14:27'),(285,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 11:16:05'),(286,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 11:19:04'),(287,'管理员-管理员','127.0.0.1','2020-06-10 11:20:40'),(288,'管理员-管理员','127.0.0.1','2020-06-10 11:24:24'),(289,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 11:25:44'),(290,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-10 15:55:43'),(291,'管理员-管理员','0:0:0:0:0:0:0:1','2020-06-11 06:15:08'),(292,'管理员-管理员','127.0.0.1','2020-06-11 11:19:46'),(293,'管理员-管理员','127.0.0.1','2020-06-11 13:23:46'),(294,'普通病人-普通病人','127.0.0.1','2020-06-11 13:23:55'),(295,'普通病人-普通病人','127.0.0.1','2020-06-11 13:27:48'),(296,'普通病人-普通病人','127.0.0.1','2020-06-11 13:29:22'),(297,'普通病人-普通病人','127.0.0.1','2020-06-11 13:32:52'),(298,'普通病人-普通病人','127.0.0.1','2020-06-11 13:36:00'),(299,'管理员-管理员','127.0.0.1','2020-06-11 13:57:24'),(300,'管理员-管理员','127.0.0.1','2020-06-11 13:59:28'),(301,'管理员-管理员','127.0.0.1','2020-06-11 14:00:28'),(302,'管理员-管理员','127.0.0.1','2020-06-11 14:01:56'),(303,'普通病人-普通病人','127.0.0.1','2020-06-11 14:05:14'),(304,'普通病人-普通病人','127.0.0.1','2020-06-11 14:23:27'),(305,'普通病人-普通病人','127.0.0.1','2020-06-11 14:33:40'),(306,'普通病人-普通病人','127.0.0.1','2020-06-11 14:35:59'),(307,'普通病人-普通病人','127.0.0.1','2020-06-11 14:37:17'),(308,'普通病人-普通病人','127.0.0.1','2020-06-11 14:41:32'),(309,'普通病人-普通病人','127.0.0.1','2020-06-11 14:45:20'),(310,'普通病人-普通病人','127.0.0.1','2020-06-11 14:46:43'),(311,'普通病人-普通病人','127.0.0.1','2020-06-11 15:14:18'),(312,'普通病人-普通病人','127.0.0.1','2020-06-11 15:18:09'),(313,'普通病人-普通病人','127.0.0.1','2020-06-11 15:20:47'),(314,'普通病人-普通病人','127.0.0.1','2020-06-11 15:24:19'),(315,'普通病人-普通病人','127.0.0.1','2020-06-11 15:30:55'),(316,'普通病人-普通病人','127.0.0.1','2020-06-11 15:34:49');
/*!40000 ALTER TABLE `sys_loginfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `oper_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
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
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '权限类型[menu/permission]',
  `title` varchar(255) DEFAULT NULL,
  `per_code` varchar(255) DEFAULT NULL COMMENT '权限编码[只有type= permission才有  user:view]',
  `icon` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `opened` tinyint(4) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `available` tinyint(4) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` VALUES (1,0,'menu','仓库管理系统',NULL,'&#xe68e;','','',1,1,1),(2,1,'menu','挂号系统',NULL,'&#xe857;','','',0,2,1),(3,1,'menu','进货管理',NULL,'&#xe645;','',NULL,0,3,1),(4,1,'menu','销售管理',NULL,'&#xe611;','','',0,4,1),(5,1,'menu','系统管理',NULL,'&#xe614;','','',1,5,1),(6,1,'menu','其它管理',NULL,'&#xe628;','','',0,6,1),(7,2,'menu','挂号提交',NULL,'&#xe651;','/bus/patient/toDoctorTimeManager','',0,7,1),(8,2,'menu','挂号管理',NULL,'&#xe658;','/bus/patient/toRegisterManager','',0,8,1),(9,2,'menu','挂号缴费',NULL,'&#xe657;','/bus/patient/toPaymentManager','',0,9,1),(10,3,'menu','商品进货',NULL,'&#xe756;','/bus/toInportManager','',0,10,1),(11,3,'menu','商品退货查询',NULL,'&#xe65a;','/bus/toOutportManager','',0,11,1),(12,4,'menu','商品销售',NULL,'&#xe65b;','/bus/toSalesManager','',0,12,1),(13,4,'menu','销售退货查询',NULL,'&#xe770;','/bus/toSalesbackManager','',0,13,1),(14,5,'menu','部门管理',NULL,'&#xe770;','/sys/toDeptManager','',0,14,1),(15,5,'menu','菜单管理',NULL,'&#xe663;','/sys/toMenuManager','',0,15,1),(16,5,'menu','权限管理','','&#xe857;','/sys/toPermissionManager','',0,16,1),(17,5,'menu','角色管理','','&#xe650;','/sys/toRoleManager','',0,17,1),(18,5,'menu','医生管理','','&#xe612;','/sys/toDoctorManager','',0,18,1),(19,5,'menu','病人管理',NULL,'&#xe675;','/sys/toPatientManager','',0,21,1),(21,6,'menu','登陆日志',NULL,'&#xe675;','/sys/toLoginfoManager','',0,21,1),(22,6,'menu','系统公告',NULL,'&#xe756;','/sys/toNoticeManager',NULL,0,22,1),(23,6,'menu','图标管理',NULL,'&#xe670;','../resources/page/icon.html',NULL,1,23,1),(30,14,'permission','添加部门','dept:create','',NULL,NULL,0,24,1),(31,14,'permission','修改部门','dept:update','',NULL,NULL,0,26,1),(32,14,'permission','删除部门','dept:delete','',NULL,NULL,0,27,1),(34,15,'permission','添加菜单','menu:create','','','',0,29,1),(35,15,'permission','修改菜单','menu:update','',NULL,NULL,0,30,1),(36,15,'permission','删除菜单','menu:delete','',NULL,NULL,0,31,1),(38,16,'permission','添加权限','permission:create','',NULL,NULL,0,33,1),(39,16,'permission','修改权限','permission:update','',NULL,NULL,0,34,1),(40,16,'permission','删除权限','permission:delete','',NULL,NULL,0,35,1),(42,17,'permission','添加角色','role:create','',NULL,NULL,0,37,1),(43,17,'permission','修改角色','role:update','',NULL,NULL,0,38,1),(44,17,'permission','删除角色','role:delete','',NULL,NULL,0,39,1),(46,17,'permission','分配权限','role:selectPermission','',NULL,NULL,0,41,1),(47,18,'permission','添加用户','user:create','',NULL,NULL,0,42,1),(48,18,'permission','修改用户','user:update','',NULL,NULL,0,43,1),(49,18,'permission','删除用户','user:delete','',NULL,NULL,0,44,1),(51,18,'permission','用户分配角色','user:selectRole','',NULL,NULL,0,46,1),(52,18,'permission','重置密码','user:resetPwd',NULL,NULL,NULL,0,47,1),(53,14,'permission','部门查询','dept:view',NULL,NULL,NULL,0,48,1),(54,15,'permission','菜单查询','menu:view',NULL,NULL,NULL,0,49,1),(55,16,'permission','权限查询','permission:view',NULL,NULL,NULL,0,50,1),(56,17,'permission','角色查询','role:view',NULL,NULL,NULL,0,51,1),(57,18,'permission','用户查询','user:view',NULL,NULL,NULL,0,52,1),(68,7,'permission','客户查询','customer:view',NULL,NULL,NULL,NULL,60,1),(69,7,'permission','客户添加','customer:create',NULL,NULL,NULL,NULL,61,1),(70,7,'permission','客户修改','customer:update',NULL,NULL,NULL,NULL,62,1),(71,7,'permission','客户删除','customer:delete',NULL,NULL,NULL,NULL,63,1),(73,21,'permission','日志查询','info:view',NULL,NULL,NULL,NULL,65,1),(74,21,'permission','日志删除','info:delete',NULL,NULL,NULL,NULL,66,1),(75,21,'permission','日志批量删除','info:batchdelete',NULL,NULL,NULL,NULL,67,1),(76,22,'permission','公告查询','notice:view',NULL,NULL,NULL,NULL,68,1),(77,22,'permission','公告添加','notice:create',NULL,NULL,NULL,NULL,69,1),(78,22,'permission','公告修改','notice:update',NULL,NULL,NULL,NULL,70,1),(79,22,'permission','公告删除','notice:delete',NULL,NULL,NULL,NULL,71,1),(81,8,'permission','供应商查询','provider:view',NULL,NULL,NULL,NULL,73,1),(82,8,'permission','供应商添加','provider:create',NULL,NULL,NULL,NULL,74,1),(83,8,'permission','供应商修改','provider:update',NULL,NULL,NULL,NULL,75,1),(84,8,'permission','供应商删除','provider:delete',NULL,NULL,NULL,NULL,76,1),(86,22,'permission','公告查看','notice:viewnotice',NULL,NULL,NULL,NULL,78,1),(91,9,'permission','商品查询','goods:view',NULL,NULL,NULL,0,79,1),(92,9,'permission','商品添加','goods:create',NULL,NULL,NULL,0,80,1),(116,9,'permission','商品删除','goods:delete',NULL,NULL,NULL,0,84,1),(117,9,'permission','商品修改','goods:update',NULL,NULL,NULL,0,85,1),(118,9,'permission','商品查询','goods:view',NULL,NULL,NULL,0,86,1),(119,22,'permission','公告批量删除','notice:batchdelete',NULL,NULL,NULL,0,87,1),(122,6,'menu','缓存管理',NULL,'&#xe630','/sys/toCacheManager','_black',1,88,1),(123,122,'permission','同步缓存','cache:syncCache',NULL,NULL,NULL,0,89,1),(124,122,'permission','清空缓存','cache:removeAllCache',NULL,NULL,NULL,0,90,1);
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL,
  `role_code` varchar(45) COLLATE utf8_bin NOT NULL,
  `role_name` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id_UNIQUE` (`role_id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
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
  `r_p_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`r_p_id`),
  UNIQUE KEY `r_p_id_UNIQUE` (`r_p_id`),
  UNIQUE KEY `role_permission_UNIQUE` (`role_id`,`permission_id`),
  KEY `ibfk_2_idx` (`permission_id`),
  KEY `ibfk_1_idx` (`role_id`),
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`permission_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` VALUES (69,5,2),(74,5,7),(75,5,8),(76,5,9),(68,6,1),(70,6,3),(71,6,4),(72,6,5),(73,6,6),(77,6,10),(78,6,11),(79,6,12),(80,6,13),(81,6,14),(82,6,15),(83,6,16),(84,6,17),(85,6,18),(90,6,19),(86,6,21),(87,6,22),(88,6,23),(89,6,122);
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL COMMENT '真实姓名',
  `login_name` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `gender` int(11) NOT NULL COMMENT '性别.1为男，0为女',
  `id_num` varchar(255) NOT NULL COMMENT '身份证号码',
  `med_num` varchar(255) DEFAULT NULL COMMENT '医保卡号',
  `addr` varchar(255) NOT NULL COMMENT '地址',
  `dept_id` int(11) DEFAULT NULL COMMENT '医生的部门id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色。0为管理员，1为门诊医生，2为药剂医生，3为检验医师，4为护士，5为病人',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话号码',
  `info` varchar(255) DEFAULT NULL COMMENT '医生个人信息',
  `birthday` date NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'管理员','system','e10adc3949ba59abbe56e057f20f883e',1,'1','1','木叶村',1,6,'1311',NULL,'1999-03-07',NULL,1,NULL),(2,'普通病人','patient','e10adc3949ba59abbe56e057f20f883e',0,'2','2','贝岗',NULL,5,'2422',NULL,'1999-04-28',NULL,1,NULL),(8,'test','test','e10adc3949ba59abbe56e057f20f883e',1,'123',NULL,'岩隐村',2,1,NULL,'','2020-05-23',NULL,1,''),(11,'门诊医师','doctor','e10adc3949ba59abbe56e057f20f883e',0,'1265',NULL,'小谷围',5,2,'8967',NULL,'2020-06-12',NULL,1,'见习');
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

-- Dump completed on 2020-06-11 23:50:44
