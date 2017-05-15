-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: catpass
-- ------------------------------------------------------
-- Server version	5.7.17-log

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

DROP DATABASE IF EXISTS `catpass`;
CREATE DATABASE `catpass`;
USE `catpass`;

--
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `animal` (
  `a_id` varchar(45) NOT NULL,
  `a_nom` varchar(45) NOT NULL,
  `a_age` int(11) DEFAULT NULL,
  `a_fk_m_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`a_id`),
  KEY `m_id_idx` (`a_fk_m_id`),
  CONSTRAINT `m_id` FOREIGN KEY (`a_fk_m_id`) REFERENCES `maison` (`m_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` VALUES ('1234567989','toto',5,1);
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capteur`
--

DROP TABLE IF EXISTS `capteur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `capteur` (
  `c_id` varchar(45) NOT NULL,
  `c_fk_m_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `c_fk_m_id_idx` (`c_fk_m_id`),
  CONSTRAINT `c_fk_m_id` FOREIGN KEY (`c_fk_m_id`) REFERENCES `maison` (`m_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capteur`
--

LOCK TABLES `capteur` WRITE;
/*!40000 ALTER TABLE `capteur` DISABLE KEYS */;
INSERT INTO `capteur` VALUES ('azerty',1);
/*!40000 ALTER TABLE `capteur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historique`
--

DROP TABLE IF EXISTS `historique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historique` (
  `h_id` int(11) NOT NULL AUTO_INCREMENT,
  `h_horodatage` datetime NOT NULL,
  `h_sortie` tinyint(4) NOT NULL,
  `h_fk_a_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`h_id`),
  KEY `a_id_idx` (`h_fk_a_id`),
  CONSTRAINT `a_id` FOREIGN KEY (`h_fk_a_id`) REFERENCES `animal` (`a_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historique`
--

LOCK TABLES `historique` WRITE;
/*!40000 ALTER TABLE `historique` DISABLE KEYS */;
/*!40000 ALTER TABLE `historique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maison`
--

DROP TABLE IF EXISTS `maison`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maison` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT,
  `m_adresse` varchar(45) NOT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maison`
--

LOCK TABLES `maison` WRITE;
/*!40000 ALTER TABLE `maison` DISABLE KEYS */;
INSERT INTO `maison` VALUES (1,'1 rue test');
/*!40000 ALTER TABLE `maison` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur` (
  `u_mail` varchar(45) NOT NULL,
  `u_nom` varchar(45) NOT NULL,
  `u_prenom` varchar(45) DEFAULT NULL,
  `u_password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`u_mail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES ('jordan.dejoux@outlook.fr','Dejoux','jordan','azerty');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur-maison`
--

DROP TABLE IF EXISTS `utilisateur-maison`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur-maison` (
  `um_fk_u_mail` varchar(45) NOT NULL,
  `um_fk_m_id` int(11) NOT NULL,
  PRIMARY KEY (`um_fk_u_mail`,`um_fk_m_id`),
  KEY `u_mail_idx` (`um_fk_u_mail`),
  KEY `m_id_idx` (`um_fk_m_id`),
  CONSTRAINT `m_id1` FOREIGN KEY (`um_fk_m_id`) REFERENCES `maison` (`m_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `u_mail` FOREIGN KEY (`um_fk_u_mail`) REFERENCES `utilisateur` (`u_mail`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur-maison`
--

LOCK TABLES `utilisateur-maison` WRITE;
/*!40000 ALTER TABLE `utilisateur-maison` DISABLE KEYS */;
INSERT INTO `utilisateur-maison` VALUES ('jordan.dejoux@outlook.fr',1);
/*!40000 ALTER TABLE `utilisateur-maison` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-21 14:46:32
