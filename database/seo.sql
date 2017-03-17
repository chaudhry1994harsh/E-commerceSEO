-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: seo
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admins` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  PRIMARY KEY (`id`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_mobiles`
--

DROP TABLE IF EXISTS `c_mobiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_mobiles` (
  `id` int(10) unsigned NOT NULL,
  `cid` int(10) unsigned NOT NULL,
  `rom` smallint(5) unsigned DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  `specs` varchar(100) DEFAULT NULL,
  `price` int(10) unsigned NOT NULL,
  `photo` varchar(50) DEFAULT NULL,
  `q_sold` int(10) unsigned DEFAULT NULL,
  `stars` int(10) unsigned DEFAULT NULL,
  `q_rated` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`,`cid`),
  CONSTRAINT `c_mobiles_ibfk_1` FOREIGN KEY (`id`) REFERENCES `plist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_mobiles`
--

LOCK TABLES `c_mobiles` WRITE;
/*!40000 ALTER TABLE `c_mobiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `c_mobiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_d`
--

DROP TABLE IF EXISTS `client_d`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_d` (
  `id` int(10) unsigned NOT NULL,
  `pid` int(10) unsigned NOT NULL,
  `phone` int(10) unsigned DEFAULT NULL,
  `add_lin1` varchar(50) DEFAULT NULL,
  `add_lin2` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `pin` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`,`pid`),
  CONSTRAINT `client_d_ibfk_1` FOREIGN KEY (`id`) REFERENCES `client_m` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_d`
--

LOCK TABLES `client_d` WRITE;
/*!40000 ALTER TABLE `client_d` DISABLE KEYS */;
INSERT INTO `client_d` VALUES (1,1,NULL,NULL,NULL,NULL,NULL),(1,2,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `client_d` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_m`
--

DROP TABLE IF EXISTS `client_m`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_m` (
  `email` varchar(50) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `block` enum('T','F') DEFAULT 'F',
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `auth` enum('T','F') DEFAULT 'F',
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`email`,`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_m`
--

LOCK TABLES `client_m` WRITE;
/*!40000 ALTER TABLE `client_m` DISABLE KEYS */;
INSERT INTO `client_m` VALUES ('harsh@gmail.com','harsh','F',1,'F','');
/*!40000 ALTER TABLE `client_m` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plist`
--

DROP TABLE IF EXISTS `plist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plist` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `descrip` varchar(150) DEFAULT NULL,
  `catg` enum('smartphones and mobiles','stationary','apperals:jeans','other/misc') DEFAULT 'other/misc',
  `mtitle` varchar(50) DEFAULT NULL,
  `mdescrip` varchar(100) DEFAULT NULL,
  `mkeywrd` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`,`title`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plist`
--

LOCK TABLES `plist` WRITE;
/*!40000 ALTER TABLE `plist` DISABLE KEYS */;
/*!40000 ALTER TABLE `plist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-17 20:17:10
