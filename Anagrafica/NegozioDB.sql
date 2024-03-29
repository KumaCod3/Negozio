-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: negoziodb
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acquisti`
--

DROP TABLE IF EXISTS `acquisti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acquisti` (
  `ID_ACQUISTO` int NOT NULL AUTO_INCREMENT,
  `ID_FORNITORE` int DEFAULT NULL,
  `Moment` date DEFAULT NULL,
  `Price` decimal(7,2) DEFAULT NULL,
  `Invoice` int DEFAULT NULL,
  `Note` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`ID_ACQUISTO`),
  KEY `ID_FORNITORE` (`ID_FORNITORE`),
  CONSTRAINT `acquisti_ibfk_1` FOREIGN KEY (`ID_FORNITORE`) REFERENCES `fornitori` (`ID_FORNITORE`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acquisti`
--

LOCK TABLES `acquisti` WRITE;
/*!40000 ALTER TABLE `acquisti` DISABLE KEYS */;
/*!40000 ALTER TABLE `acquisti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clienti`
--

DROP TABLE IF EXISTS `clienti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clienti` (
  `ID_CLIENTE` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(25) DEFAULT NULL,
  `Last_Name` varchar(50) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT '00000',
  `Mail` varchar(25) DEFAULT 'mail',
  `State` varchar(2) DEFAULT 'XX',
  `City` varchar(15) DEFAULT 'city',
  `Street` varchar(30) DEFAULT 'street',
  `VATn` varchar(10) DEFAULT '000',
  `Tot_sold` decimal(7,2) DEFAULT '0.00',
  `Note` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clienti`
--

LOCK TABLES `clienti` WRITE;
/*!40000 ALTER TABLE `clienti` DISABLE KEYS */;
INSERT INTO `clienti` VALUES (0,'GUEST','GUEST','00000','mail','XX','city','street','000',0.00,NULL);
/*!40000 ALTER TABLE `clienti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornitori`
--

DROP TABLE IF EXISTS `fornitori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornitori` (
  `ID_FORNITORE` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(25) DEFAULT NULL,
  `Last_Name` varchar(50) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Mail` varchar(25) DEFAULT NULL,
  `State` varchar(2) DEFAULT NULL,
  `City` varchar(15) DEFAULT NULL,
  `Street` varchar(30) DEFAULT NULL,
  `VATn` varchar(10) DEFAULT NULL,
  `Tot_purchased` decimal(7,2) DEFAULT '0.00',
  `Note` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`ID_FORNITORE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornitori`
--

LOCK TABLES `fornitori` WRITE;
/*!40000 ALTER TABLE `fornitori` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornitori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forniture`
--

DROP TABLE IF EXISTS `forniture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forniture` (
  `ID_FORNITORE` int NOT NULL,
  `ID_MERCE` int NOT NULL,
  `Price` decimal(7,2) DEFAULT '0.00',
  PRIMARY KEY (`ID_FORNITORE`,`ID_MERCE`),
  KEY `ID_MERCE` (`ID_MERCE`),
  CONSTRAINT `forniture_ibfk_1` FOREIGN KEY (`ID_FORNITORE`) REFERENCES `fornitori` (`ID_FORNITORE`) ON DELETE CASCADE,
  CONSTRAINT `forniture_ibfk_2` FOREIGN KEY (`ID_MERCE`) REFERENCES `merci` (`ID_MERCE`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forniture`
--

LOCK TABLES `forniture` WRITE;
/*!40000 ALTER TABLE `forniture` DISABLE KEYS */;
/*!40000 ALTER TABLE `forniture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merci`
--

DROP TABLE IF EXISTS `merci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merci` (
  `ID_MERCE` int NOT NULL AUTO_INCREMENT,
  `Product` varchar(15) DEFAULT NULL,
  `Unity` varchar(10) DEFAULT NULL,
  `Quantity` decimal(7,2) DEFAULT '0.00',
  `Deal` decimal(3,2) DEFAULT '1.00',
  `Note` varchar(150) DEFAULT NULL,
  `increase` decimal(3,2) DEFAULT '1.20',
  `price` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`ID_MERCE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merci`
--

LOCK TABLES `merci` WRITE;
/*!40000 ALTER TABLE `merci` DISABLE KEYS */;
/*!40000 ALTER TABLE `merci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merci_acquisti`
--

DROP TABLE IF EXISTS `merci_acquisti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merci_acquisti` (
  `ID_ACQUISTO` int NOT NULL,
  `ID_MERCE` int NOT NULL,
  `Quantity` decimal(7,2) DEFAULT NULL,
  `Price` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`ID_ACQUISTO`,`ID_MERCE`),
  KEY `ID_MERCE` (`ID_MERCE`),
  CONSTRAINT `merci_acquisti_ibfk_1` FOREIGN KEY (`ID_MERCE`) REFERENCES `merci` (`ID_MERCE`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merci_acquisti`
--

LOCK TABLES `merci_acquisti` WRITE;
/*!40000 ALTER TABLE `merci_acquisti` DISABLE KEYS */;
/*!40000 ALTER TABLE `merci_acquisti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merci_vendite`
--

DROP TABLE IF EXISTS `merci_vendite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merci_vendite` (
  `ID_VENDITA` int NOT NULL,
  `ID_MERCE` int NOT NULL,
  `Quantity` decimal(7,2) DEFAULT NULL,
  `Price` decimal(7,2) DEFAULT NULL,
  PRIMARY KEY (`ID_VENDITA`,`ID_MERCE`),
  KEY `ID_MERCE` (`ID_MERCE`),
  CONSTRAINT `merci_vendite_ibfk_1` FOREIGN KEY (`ID_MERCE`) REFERENCES `merci` (`ID_MERCE`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merci_vendite`
--

LOCK TABLES `merci_vendite` WRITE;
/*!40000 ALTER TABLE `merci_vendite` DISABLE KEYS */;
/*!40000 ALTER TABLE `merci_vendite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendite`
--

DROP TABLE IF EXISTS `vendite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendite` (
  `ID_VENDITA` int NOT NULL AUTO_INCREMENT,
  `ID_CLIENTE` int DEFAULT NULL,
  `Moment` date DEFAULT NULL,
  `Price` decimal(7,2) DEFAULT NULL,
  `Invoice` varchar(25) DEFAULT 'NomeFile',
  `Note` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`ID_VENDITA`),
  KEY `ID_CLIENTE` (`ID_CLIENTE`),
  CONSTRAINT `vendite_ibfk_1` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clienti` (`ID_CLIENTE`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendite`
--

LOCK TABLES `vendite` WRITE;
/*!40000 ALTER TABLE `vendite` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendite` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-18 14:49:56
