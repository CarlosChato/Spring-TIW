-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: bd82_08_ticketsell
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `chats`
--

DROP TABLE IF EXISTS `chats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chats` (
  `idChat` int NOT NULL AUTO_INCREMENT,
  `user1_id` int NOT NULL,
  `user2_id` int NOT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idChat`),
  KEY `user1_id` (`user1_id`),
  KEY `user2_id` (`user2_id`),
  CONSTRAINT `chats_ibfk_1` FOREIGN KEY (`user1_id`) REFERENCES `users` (`idUsers`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chats_ibfk_2` FOREIGN KEY (`user2_id`) REFERENCES `users` (`idUsers`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chats`
--

LOCK TABLES `chats` WRITE;
/*!40000 ALTER TABLE `chats` DISABLE KEYS */;
INSERT INTO `chats` VALUES (1,1,2,'2022-12-11 11:07:49');
/*!40000 ALTER TABLE `chats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `idEvent` int NOT NULL AUTO_INCREMENT,
  `category` varchar(150) NOT NULL,
  `fecha` date NOT NULL,
  `name_event` varchar(150) NOT NULL,
  `city` varchar(150) NOT NULL,
  `sala` varchar(150) NOT NULL,
  `photo_path` text,
  `photo` longtext,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idEvent`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'fest','2023-10-10','mad cool','Madrid','Ifema','assets/public/events/mad-cool.png',NULL,'2022-12-11 11:07:49'),(2,'fest','2023-07-06','arenal sound','Valencia','Playa Valencia','assets/public/events/arenal-sound.png',NULL,'2022-12-11 11:07:49'),(3,'fest','2023-02-08','resurrection fest','Ourense','La Poza','assets/public/events/resurrection.jpg',NULL,'2022-12-11 11:07:49');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `message` text NOT NULL,
  `chat` int NOT NULL,
  `sendBy` int NOT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `chat` (`chat`),
  KEY `sendBy` (`sendBy`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`chat`) REFERENCES `chats` (`idChat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`sendBy`) REFERENCES `users` (`idUsers`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'hola, estoy interesado en la entrada',1,1,'2022-12-11 11:07:49'),(2,'hola, que le gustaria saber?',1,2,'2022-12-11 11:07:49'),(3,'En que parte del escensario es??',1,1,'2022-12-11 11:07:49');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `idTickets` int NOT NULL AUTO_INCREMENT,
  `type` varchar(150) NOT NULL,
  `code` varchar(150) NOT NULL,
  `price` int NOT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `Users_id` int NOT NULL,
  `Event_id` int NOT NULL,
  PRIMARY KEY (`idTickets`),
  KEY `Users_id` (`Users_id`),
  KEY `Event_id` (`Event_id`),
  CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`Users_id`) REFERENCES `users` (`idUsers`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`Event_id`) REFERENCES `event` (`idEvent`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (1,'ticket','60',24,'2022-12-11 11:07:49',1,3),(2,'ticket','15',45,'2022-12-11 11:07:49',20,3),(3,'ticket','58',56,'2022-12-11 11:07:49',2,2),(4,'ticket','73',25,'2022-12-11 11:07:49',10,3),(5,'ticket','77',99,'2022-12-11 11:07:49',18,3),(6,'ticket','13',31,'2022-12-11 11:07:49',12,3),(7,'ticket','49',61,'2022-12-11 11:07:49',11,3),(8,'ticket','35',70,'2022-12-11 11:07:49',12,1),(9,'ticket','52',29,'2022-12-11 11:07:49',18,3),(10,'ticket','49',99,'2022-12-11 11:07:49',6,3),(11,'ticket','79',74,'2022-12-11 11:07:49',12,2),(12,'ticket','85',33,'2022-12-11 11:07:49',16,3),(13,'ticket','90',46,'2022-12-11 11:07:49',11,2),(14,'ticket','55',27,'2022-12-11 11:07:49',17,1),(15,'ticket','38',87,'2022-12-11 11:07:49',8,1),(16,'ticket','8',92,'2022-12-11 11:07:49',12,3),(17,'ticket','22',64,'2022-12-11 11:07:49',7,2),(18,'ticket','84',53,'2022-12-11 11:07:49',20,3),(19,'ticket','24',28,'2022-12-11 11:07:49',15,2),(20,'ticket','73',39,'2022-12-11 11:07:49',15,3);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `idUsers` int NOT NULL AUTO_INCREMENT,
  `isAdmin` tinyint(1) NOT NULL,
  `name_user` varchar(150) NOT NULL,
  `password_user` varchar(150) NOT NULL,
  `username` varchar(150) NOT NULL,
  `surname` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL,
  `phone` int NOT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idUsers`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'root','password','root','leaf','root@root.com',600000000,'2022-12-11 11:07:49'),(2,0,'u','u','u','u','u@gmail.com',694200443,'2022-12-11 11:07:49'),(3,0,'Frankq','alwes','Psychochn','LynchBen','nrlwo@gmail.com',638926370,'2022-12-11 11:07:49'),(4,0,'Fredt','btbcb','Bruiseccm','Big PapaElena','sqizi@gmail.com',615492186,'2022-12-11 11:07:49'),(5,0,'Elenav','dcsfc','Benderxrx','ODoylePepe','uuujc@gmail.com',648916003,'2022-12-11 11:07:49'),(6,0,'Davidt','nhacr','Bowserxsp','KrakenAlberto','fqoja@gmail.com',689373208,'2022-12-11 11:07:49'),(7,0,'Jorgep','ibkwp','Bowserpnr','CannonPeppo','afqgo@gmail.com',657604405,'2022-12-11 11:07:49'),(8,0,'Peppoo','clspm','ODoylerbq','BruiseJorge','jeuej@gmail.com',690363435,'2022-12-11 11:07:49'),(9,0,'Frede','phnkd','Bowserxsq','Big PapaBen','qhucz@gmail.com',611910350,'2022-12-11 11:07:49'),(10,0,'Albertos','cpgci','Psychohoa','BruiseJose','ezmjo@gmail.com',615977249,'2022-12-11 11:07:49'),(11,0,'Jorger','dnqcu','Lynchkoi','BruiseFred','qgyxy@gmail.com',695360478,'2022-12-11 11:07:49'),(12,0,'Albertos','pireh','Krakenqql','LynchPepe','lpasv@gmail.com',603093006,'2022-12-11 11:07:49'),(13,0,'Josel','iausu','Benderyip','BowserPeppo','fxwyc@gmail.com',604714715,'2022-12-11 11:07:49'),(14,0,'Albertou','pcekg','Aspectdtf','PsychoElena','novlv@gmail.com',641843142,'2022-12-11 11:07:49'),(15,0,'Josei','softu','Bowserznr','Big PapaFrank','ylpar@gmail.com',664740376,'2022-12-11 11:07:49'),(16,0,'Jorgen','zxysl','Cannonfie','Big PapaPepe','hqadi@gmail.com',640057088,'2022-12-11 11:07:49'),(17,0,'Benf','kgikj','Bowserjyq','BenderJose','jhxhp@gmail.com',618093108,'2022-12-11 11:07:49'),(18,0,'Benj','xaroy','ODoylenmy','BenderBen','gpgwi@gmail.com',615421156,'2022-12-11 11:07:49'),(19,0,'Davidc','mhmhr','ODoyleofh','CannonFred','ykrcv@gmail.com',669365977,'2022-12-11 11:07:49'),(20,0,'Joseb','pbumw','Benderisb','Big PapaJose','qtfao@gmail.com',625028546,'2022-12-11 11:07:49'),(21,0,'Frankd','qkwzi','Mad Dogmhs','Big PapaBen','nzxtk@gmail.com',688795343,'2022-12-11 11:07:49');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-11 11:10:11
