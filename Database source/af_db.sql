-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: af_db
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `candidature`
--

DROP TABLE IF EXISTS `candidature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidature` (
  `IdCandidatura` int NOT NULL AUTO_INCREMENT,
  `Curriculum` varchar(128) NOT NULL,
  `DocumentiAggiuntivi` varchar(64) DEFAULT NULL,
  `Stato` varchar(32) NOT NULL,
  `DataCandidatura` date NOT NULL,
  `DataOraColloquio` datetime DEFAULT NULL,
  `IdCandidato` int NOT NULL,
  `IdHR` int DEFAULT NULL,
  PRIMARY KEY (`IdCandidatura`),
  KEY `IdCandidato` (`IdCandidato`),
  KEY `IdHR` (`IdHR`),
  CONSTRAINT `candidature_ibfk_1` FOREIGN KEY (`IdCandidato`) REFERENCES `utenti` (`IdUtente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `candidature_ibfk_2` FOREIGN KEY (`IdHR`) REFERENCES `utenti` (`IdUtente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidature`
--

LOCK TABLES `candidature` WRITE;
/*!40000 ALTER TABLE `candidature` DISABLE KEYS */;
INSERT INTO `candidature` VALUES (2,'\\AgencyFormationFile\\Candidature\\\\IdUtente-1\\cv.pdf','\\AgencyFormationFile\\Candidature\\\\IdUtente-1\\certificazione.pdf','Non Revisionato','2021-12-31',NULL,1,NULL),(3,'\\AgencyFormationFile\\Candidature\\\\IdUtente-5\\cv.pdf',NULL,'Non Revisionato','2021-12-31',NULL,5,NULL),(4,'\\AgencyFormationFile\\Candidature\\\\IdUtente-6\\smallBrainAI.pdf',NULL,'Non Revisionato','2021-12-31',NULL,6,NULL),(5,'\\AgencyFormationFile\\Candidature\\\\IdUtente-7\\ListaMovimenti.pdf',NULL,'Non Revisionato','2022-01-01',NULL,7,NULL);
/*!40000 ALTER TABLE `candidature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dipendenti`
--

DROP TABLE IF EXISTS `dipendenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dipendenti` (
  `IdDipendente` int NOT NULL,
  `Residenza` varchar(128) DEFAULT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `Stato` tinyint(1) NOT NULL,
  `AnnoDiNascita` int NOT NULL,
  `IdTeam` int DEFAULT NULL,
  PRIMARY KEY (`IdDipendente`),
  KEY `IdTeam` (`IdTeam`),
  CONSTRAINT `dipendenti_ibfk_1` FOREIGN KEY (`IdTeam`) REFERENCES `team` (`IdTeam`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dipendenti_ibfk_2` FOREIGN KEY (`IdDipendente`) REFERENCES `utenti` (`IdUtente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dipendenti`
--

LOCK TABLES `dipendenti` WRITE;
/*!40000 ALTER TABLE `dipendenti` DISABLE KEYS */;
INSERT INTO `dipendenti` VALUES (2,'Fisciano','118',0,2000,1);
/*!40000 ALTER TABLE `dipendenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documenti`
--

DROP TABLE IF EXISTS `documenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documenti` (
  `IdDocumento` int NOT NULL AUTO_INCREMENT,
  `MaterialeDiFormazione` varchar(32) NOT NULL,
  `IdUtente` int NOT NULL,
  `IdTeam` int NOT NULL,
  PRIMARY KEY (`IdDocumento`),
  KEY `IdUtente` (`IdUtente`),
  KEY `IdTeam` (`IdTeam`),
  CONSTRAINT `documenti_ibfk_1` FOREIGN KEY (`IdUtente`) REFERENCES `dipendenti` (`IdDipendente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `documenti_ibfk_2` FOREIGN KEY (`IdTeam`) REFERENCES `team` (`IdTeam`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documenti`
--

LOCK TABLES `documenti` WRITE;
/*!40000 ALTER TABLE `documenti` DISABLE KEYS */;
INSERT INTO `documenti` VALUES (1,'\\',2,1);
/*!40000 ALTER TABLE `documenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skill`
--

DROP TABLE IF EXISTS `skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skill` (
  `IdSkill` int NOT NULL AUTO_INCREMENT,
  `NomeSkill` varchar(64) NOT NULL,
  `DescrizioneSkill` varchar(512) NOT NULL,
  PRIMARY KEY (`IdSkill`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill`
--

LOCK TABLES `skill` WRITE;
/*!40000 ALTER TABLE `skill` DISABLE KEYS */;
INSERT INTO `skill` VALUES (1,'HTML','Conoscenze generali di HTML'),(2,'CSS','Conoscenze basilari di CSS');
/*!40000 ALTER TABLE `skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skillsdipendenti`
--

DROP TABLE IF EXISTS `skillsdipendenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skillsdipendenti` (
  `IdDipendente` int NOT NULL,
  `IdSkill` int NOT NULL,
  `Livello` int NOT NULL,
  PRIMARY KEY (`IdDipendente`,`IdSkill`),
  KEY `IdSkill` (`IdSkill`),
  CONSTRAINT `skillsdipendenti_ibfk_1` FOREIGN KEY (`IdDipendente`) REFERENCES `dipendenti` (`IdDipendente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `skillsdipendenti_ibfk_2` FOREIGN KEY (`IdSkill`) REFERENCES `skill` (`IdSkill`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skillsdipendenti`
--

LOCK TABLES `skillsdipendenti` WRITE;
/*!40000 ALTER TABLE `skillsdipendenti` DISABLE KEYS */;
INSERT INTO `skillsdipendenti` VALUES (2,1,5),(2,2,3);
/*!40000 ALTER TABLE `skillsdipendenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `IdTeam` int NOT NULL AUTO_INCREMENT,
  `NomeProgetto` varchar(32) NOT NULL,
  `NumeroDipendenti` int NOT NULL,
  `NomeTeam` varchar(32) NOT NULL,
  `Descrizione` varchar(128) NOT NULL,
  `Competenza` varchar(512) DEFAULT NULL,
  `IdTM` int NOT NULL,
  PRIMARY KEY (`IdTeam`),
  KEY `IdTM` (`IdTM`),
  CONSTRAINT `team_ibfk_1` FOREIGN KEY (`IdTM`) REFERENCES `utenti` (`IdUtente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,'Fitdiary',8,'Bastoncini Fitnuss','Vendiamo bastoncini di pesce','HTML',2);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utenti` (
  `IdUtente` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(32) NOT NULL,
  `Cognome` varchar(32) NOT NULL,
  `Pwd` varchar(16) NOT NULL,
  `Mail` varchar(32) NOT NULL,
  `Ruolo` int NOT NULL,
  PRIMARY KEY (`IdUtente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti`
--

LOCK TABLES `utenti` WRITE;
/*!40000 ALTER TABLE `utenti` DISABLE KEYS */;
INSERT INTO `utenti` VALUES (1,'Luigi','Giacchetti','lol','l.giacchetti@studenti.unisa.it',1),(2,'Pasquale','Severino','lol','p.severino@studenti.unisa.it',2),(3,'Manuel','Nocerino','lol','m.nocerino@studenti.unisa.it',3),(4,'Domenico','Pagliuca','lol','d.pagliuca@studenti.unisa.it',4),(5,'Mario','Rossi','lol','mario@gmail.com',1),(6,'Antonio','Esposito','lol','antonio@gmail.com',1),(7,'Chiara','Devez','lol','chiara@gmail.com',1);
/*!40000 ALTER TABLE `utenti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-01 17:55:49
