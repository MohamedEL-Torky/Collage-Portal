-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: collage
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class_rooms`
--

DROP TABLE IF EXISTS `class_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class_rooms` (
  `ROOM_NUMBER` char(9) DEFAULT NULL,
  `COURSE_ID` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_rooms`
--

LOCK TABLES `class_rooms` WRITE;
/*!40000 ALTER TABLE `class_rooms` DISABLE KEYS */;
INSERT INTO `class_rooms` VALUES ('200','CS111'),('202','CS211'),('203','CS311'),('203','CS244');
/*!40000 ALTER TABLE `class_rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_department`
--

DROP TABLE IF EXISTS `course_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_department` (
  `COURSE_ID` varchar(50) DEFAULT NULL,
  `DEPARTMENT` char(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_department`
--

LOCK TABLES `course_department` WRITE;
/*!40000 ALTER TABLE `course_department` DISABLE KEYS */;
INSERT INTO `course_department` VALUES ('CS111','CS'),('CS211','CS'),('CS311','CS');
/*!40000 ALTER TABLE `course_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_period_day`
--

DROP TABLE IF EXISTS `course_period_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_period_day` (
  `EnrollToken` varchar(50) NOT NULL,
  `PERIOD_ID` char(9) DEFAULT NULL,
  `DAY_ID` char(9) DEFAULT NULL,
  `COURSE_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`EnrollToken`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_period_day`
--

LOCK TABLES `course_period_day` WRITE;
/*!40000 ALTER TABLE `course_period_day` DISABLE KEYS */;
INSERT INTO `course_period_day` VALUES ('1','3','SUNDAY','CS244'),('10','12','SUNDAY','CS311'),('11','6','MONDAY','CS311'),('12','9','WEDNESDAY','CS311'),('2','5','MONDAY','CS244'),('3','8','TUESDAY','CS244'),('4','1','SUNDAY','CS111'),('5','5','MONDAY','CS111'),('6','8','TUESDAY','CS111'),('7','2','SUNDAY','CS211'),('8','3','MONDAY','CS211'),('9','10','TUESDAY','CS111');
/*!40000 ALTER TABLE `course_period_day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_textbooks`
--

DROP TABLE IF EXISTS `course_textbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_textbooks` (
  `COURSE_ID` varchar(50) DEFAULT NULL,
  `TEXTBOOKS_ISBN` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_textbooks`
--

LOCK TABLES `course_textbooks` WRITE;
/*!40000 ALTER TABLE `course_textbooks` DISABLE KEYS */;
INSERT INTO `course_textbooks` VALUES ('CS111','11BCM0007811'),('CS211','12BCM8887811'),('CS244','31BCM5557811'),('CS311','15BCM9997811');
/*!40000 ALTER TABLE `course_textbooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `COURSE_ID` varchar(50) NOT NULL,
  `COURSE_NAME` varchar(50) NOT NULL,
  `TERM` int(11) NOT NULL,
  `CREDITS_HOUR` int(11) NOT NULL,
  `SEATS_LEFT` int(11) NOT NULL,
  PRIMARY KEY (`COURSE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('CS111','Intro. to CS',1,3,10),('CS211','Intro. to PS',2,3,50),('CS244','Adcanced Application',4,3,50),('CS311','Introduction to OOB',3,3,49);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `days`
--

DROP TABLE IF EXISTS `days`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `days` (
  `DAY_ID` char(9) NOT NULL,
  PRIMARY KEY (`DAY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `days`
--

LOCK TABLES `days` WRITE;
/*!40000 ALTER TABLE `days` DISABLE KEYS */;
INSERT INTO `days` VALUES ('FRIDAY'),('MONDAY'),('SATURDAY'),('SUNDAY'),('THURSDAY'),('TUESDAY'),('WEDENSDAY');
/*!40000 ALTER TABLE `days` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `DEPARTMENT_ID` char(9) NOT NULL,
  `DEPARTMENT_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('CS','Collage of Computer Science'),('Eng','Collage of Engineering'),('Logistics','Collage of Logistics');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enrollment` (
  `STUDENT_ID` char(9) DEFAULT NULL,
  `COURSE_ID` varchar(50) DEFAULT NULL,
  `DATEREGISTERED` date NOT NULL,
  `EnrollToken` varchar(50) DEFAULT NULL,
  `GREADE` varchar(225) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
INSERT INTO `enrollment` VALUES ('444111110','CS111','2005-03-10','5','A'),('444111110','CS211','2005-03-10','7','A+'),('444111111','CS111','2005-03-10','6','W'),('123123','213213','2005-03-10','213213213','A'),('444111110','CS311','2017-05-13','10','W'),('444111110','CS311','2017-05-13','10','U'),('444111112','CS111','2017-05-13','4','U');
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gender` (
  `GENDER_ID` int(11) NOT NULL,
  `GENDER_YPE` char(6) NOT NULL,
  PRIMARY KEY (`GENDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES (1,'MALE'),(2,'FEMALE');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instructor` (
  `COURSE_ID` varchar(50) DEFAULT NULL,
  `STAFF_ID` char(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `period`
--

DROP TABLE IF EXISTS `period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `period` (
  `PERIOD_ID` char(9) NOT NULL,
  PRIMARY KEY (`PERIOD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `period`
--

LOCK TABLES `period` WRITE;
/*!40000 ALTER TABLE `period` DISABLE KEYS */;
INSERT INTO `period` VALUES ('1'),('10'),('11'),('12'),('2'),('3'),('4'),('5'),('6'),('7'),('8'),('9');
/*!40000 ALTER TABLE `period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prerequisite`
--

DROP TABLE IF EXISTS `prerequisite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prerequisite` (
  `COURSE_ID` varchar(50) DEFAULT NULL,
  `PREREQUISITE_COURSE_ID` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prerequisite`
--

LOCK TABLES `prerequisite` WRITE;
/*!40000 ALTER TABLE `prerequisite` DISABLE KEYS */;
INSERT INTO `prerequisite` VALUES ('CS311','CS211'),('CS311','CS111'),('CS244','CS311'),('CS244','CS211'),('CS244','CS111'),('CS211','CS111'),('CS111',NULL);
/*!40000 ALTER TABLE `prerequisite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `ROOM_NUMBER` int(11) NOT NULL,
  `ROOM_TYPE` varchar(225) NOT NULL,
  PRIMARY KEY (`ROOM_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (200,'A'),(202,'B'),(203,'C');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `STAFF_ID` char(9) NOT NULL,
  `FIRSTNAME` varchar(50) NOT NULL,
  `LASTNAME` varchar(50) NOT NULL,
  `DATE_OF_BIRTH` date NOT NULL,
  `ADDRESS` varchar(100) NOT NULL,
  `WORK_SINCE` date NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `IMAGE` blob,
  `DEPARTMENT` char(9) DEFAULT NULL,
  `GENDER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`STAFF_ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES ('111221110','Mohamed','Magdy','1985-01-09','99 Kingston Street','2007-04-09','mohamed','cs244','Mohamed@aast.edu',NULL,'CS',1),('111221111','Maram','Shoman','1993-03-09','99 Kingston Street','2014-04-09','maram','cs244','Maram@aast.edu',NULL,'CS',2);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `STUDENT_ID` char(9) NOT NULL,
  `FIRSTNAME` varchar(50) NOT NULL,
  `LASTNAME` varchar(50) NOT NULL,
  `DATE_OF_BIRTH` date NOT NULL,
  `ADDRESS` varchar(100) NOT NULL,
  `ENREOLLED` date NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `GPA` double DEFAULT NULL,
  `IMAGE` blob,
  `DEPARTMENT` char(9) DEFAULT NULL,
  `GENDER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`STUDENT_ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES ('444111110','Jacob','Smith','1997-03-10','Semouha','2016-04-09','abc1','abc','mohamedtarekeltorky@gmail.com',4,NULL,'CS',1),('444111111','John','Stevenson','1985-04-09','99 Kingston Street','2015-04-09','abc2','abc','Johnb@aast.edu',3,NULL,'Logistics',1),('444111112','Jayden','Heintz','1985-04-09','99 Kingston Street','2014-04-09','abc3','abc','Jayden@aast.edu',2,NULL,'Eng',2);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teatch`
--

DROP TABLE IF EXISTS `teatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teatch` (
  `COURSE_ID` varchar(50) DEFAULT NULL,
  `STAFF_ID` char(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teatch`
--

LOCK TABLES `teatch` WRITE;
/*!40000 ALTER TABLE `teatch` DISABLE KEYS */;
INSERT INTO `teatch` VALUES ('CS111','111221110'),('CS211','111221110'),('CS244','111221111'),('CS311','111221111');
/*!40000 ALTER TABLE `teatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `textbooks`
--

DROP TABLE IF EXISTS `textbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `textbooks` (
  `TEXTBOOKS_ISBN` varchar(50) NOT NULL,
  `TEXTBOOKS_NAME` varchar(50) NOT NULL,
  `TEXTBOOKS_TYPE` varchar(50) NOT NULL,
  PRIMARY KEY (`TEXTBOOKS_ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `textbooks`
--

LOCK TABLES `textbooks` WRITE;
/*!40000 ALTER TABLE `textbooks` DISABLE KEYS */;
INSERT INTO `textbooks` VALUES ('11BCM0007811','C/C++ Programming','CS'),('12BCM8887811','Problem Solving in C','CS'),('15BCM9997811','Intro to Java Programming','CS'),('31BCM5557811','Advanced Applications in Java','CS');
/*!40000 ALTER TABLE `textbooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `works`
--

DROP TABLE IF EXISTS `works`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `works` (
  `DEPARTMENT_ID` char(9) DEFAULT NULL,
  `STAFF_ID` char(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `works`
--

LOCK TABLES `works` WRITE;
/*!40000 ALTER TABLE `works` DISABLE KEYS */;
/*!40000 ALTER TABLE `works` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-14  1:21:27
