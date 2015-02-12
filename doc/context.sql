-- MySQL dump 10.9
--
-- Host: localhost    Database: context
-- ------------------------------------------------------
-- Server version	4.1.22-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `context`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `context` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `context`;

--
-- Table structure for table `sensor_context`
--

DROP TABLE IF EXISTS `sensor_context`;
CREATE TABLE `sensor_context` (
  `sensor_id` int(11) default '0',
  `sensor_type` varchar(100) default '',
  `value` double default '0',
  `serial` bigint(20) NOT NULL auto_increment,
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`serial`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sensor_context`
--

LOCK TABLES `sensor_context` WRITE;
/*!40000 ALTER TABLE `sensor_context` DISABLE KEYS */;
INSERT INTO `sensor_context` VALUES (0,'humidity',50,27,'2008-05-06 15:30:54'),(0,'temperature',30.2,28,'2008-05-06 15:30:54'),(0,'humidity',50,29,'2008-05-06 15:31:04'),(0,'temperature',30.2,30,'2008-05-06 15:31:04'),(2,'humidity',50.32,31,'2008-05-06 15:31:47'),(2,'temperature',30.32,32,'2008-05-06 15:31:47'),(1,'humidity',50.32,33,'2008-05-06 15:31:52'),(1,'temperature',30.32,34,'2008-05-06 15:31:52'),(4,'humidity',50,35,'2008-05-06 15:32:37'),(4,'temperature',30,36,'2008-05-06 15:32:37'),(3,'humidity',50,37,'2008-05-06 15:32:42'),(3,'temperature',30,38,'2008-05-06 15:32:42'),(1,'humidity',50.32,39,'2008-05-06 15:32:47'),(1,'temperature',30.32,40,'2008-05-06 15:32:47'),(2,'humidity',50.32,41,'2008-05-06 15:32:52'),(2,'temperature',30.32,42,'2008-05-06 15:32:52'),(3,'humidity',50.64,43,'2008-05-06 15:32:57'),(3,'temperature',30.64,44,'2008-05-06 15:32:57'),(2,'humidity',50.64,45,'2008-05-06 15:33:02'),(2,'temperature',30.64,46,'2008-05-06 15:33:02'),(5,'humidity',50.64,47,'2008-05-06 15:33:07'),(5,'temperature',30.64,48,'2008-05-06 15:33:07'),(1,'humidity',50.64,49,'2008-05-06 15:33:12'),(1,'temperature',30.64,50,'2008-05-06 15:33:12'),(3,'humidity',50.96,51,'2008-05-06 15:33:18'),(3,'temperature',30.96,52,'2008-05-06 15:33:18'),(1,'humidity',50.96,53,'2008-05-06 15:33:23'),(1,'temperature',30.96,54,'2008-05-06 15:33:23'),(3,'humidity',50.96,55,'2008-05-06 15:33:28'),(3,'temperature',30.96,56,'2008-05-06 15:33:28'),(5,'humidity',50.96,57,'2008-05-06 15:33:33'),(5,'temperature',30.96,58,'2008-05-06 15:33:33'),(2,'humidity',50.96,59,'2008-05-06 15:33:38'),(2,'temperature',30.96,60,'2008-05-06 15:33:38'),(5,'humidity',50.96,61,'2008-05-06 15:33:43'),(5,'temperature',30.96,62,'2008-05-06 15:33:43'),(3,'humidity',50.96,63,'2008-05-06 15:33:48'),(3,'temperature',30.96,64,'2008-05-06 15:33:48'),(1,'humidity',50.64,65,'2008-05-06 15:33:53'),(1,'temperature',30.64,66,'2008-05-06 15:33:53'),(5,'humidity',50.32,67,'2008-05-06 15:33:58'),(5,'temperature',30.32,68,'2008-05-06 15:33:58'),(4,'humidity',50.32,69,'2008-05-06 15:34:03'),(4,'temperature',30.32,70,'2008-05-06 15:34:03'),(4,'humidity',50.32,71,'2008-05-06 15:34:08'),(4,'temperature',30.32,72,'2008-05-06 15:34:08'),(5,'humidity',50.64,73,'2008-05-06 15:34:13'),(5,'temperature',30.64,74,'2008-05-06 15:34:13'),(4,'humidity',50.96,75,'2008-05-06 15:34:18'),(4,'temperature',30.96,76,'2008-05-06 15:34:18'),(5,'humidity',51.28,77,'2008-05-06 15:34:23'),(5,'temperature',31.28,78,'2008-05-06 15:34:23'),(1,'humidity',51.28,79,'2008-05-06 15:34:28'),(1,'temperature',31.28,80,'2008-05-06 15:34:28'),(2,'humidity',51.6,81,'2008-05-06 15:34:33'),(2,'temperature',31.6,82,'2008-05-06 15:34:33'),(2,'humidity',51.92,83,'2008-05-06 15:34:38'),(2,'temperature',31.92,84,'2008-05-06 15:34:38'),(2,'humidity',50,85,'2008-05-06 15:52:19'),(2,'temperature',30,86,'2008-05-06 15:52:19'),(1,'humidity',50,87,'2008-05-06 15:52:24'),(1,'temperature',30,88,'2008-05-06 15:52:24'),(3,'humidity',50,89,'2008-05-06 15:52:29'),(3,'temperature',30,90,'2008-05-06 15:52:29'),(1,'humidity',49.68,91,'2008-05-06 15:52:44'),(1,'temperature',29.68,92,'2008-05-06 15:52:44'),(4,'humidity',50,93,'2008-05-06 15:52:49'),(4,'temperature',30,94,'2008-05-06 15:52:49');
/*!40000 ALTER TABLE `sensor_context` ENABLE KEYS */;
UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

