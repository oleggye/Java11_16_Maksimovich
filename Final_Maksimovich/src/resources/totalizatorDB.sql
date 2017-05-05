CREATE DATABASE  IF NOT EXISTS `totalizator` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `totalizator`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: totalizator
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `betting`
--

DROP TABLE IF EXISTS `betting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `betting` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_competition` int(10) unsigned NOT NULL,
  `id_client` int(10) unsigned NOT NULL,
  `bet_type` enum('H','D','A') NOT NULL,
  `bet_rate` decimal(4,2) unsigned NOT NULL,
  `bet_size` decimal(9,2) unsigned NOT NULL,
  `bet_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gain` decimal(11,2) unsigned DEFAULT NULL,
  `status` enum('A','D') NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`),
  KEY `idx_fk_betting_competition` (`id_competition`),
  KEY `idx_fk_betting_user` (`id_client`),
  CONSTRAINT `betting_competition_fk_1` FOREIGN KEY (`id_competition`) REFERENCES `competition` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `betting_user_fk_2` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `betting`
--

LOCK TABLES `betting` WRITE;
/*!40000 ALTER TABLE `betting` DISABLE KEYS */;
INSERT INTO `betting` VALUES (1,9,2,'H',2.20,30.00,'2017-02-04 21:18:06',0.00,'A'),(2,10,3,'D',1.45,90.00,'2017-02-01 08:33:12',0.00,'A'),(3,10,2,'H',2.54,10.00,'2017-02-01 08:34:11',25.40,'A'),(4,11,2,'A',2.54,17.00,'2017-02-01 09:43:51',43.18,'A'),(5,11,3,'A',2.54,42.00,'2017-02-01 12:26:11',106.68,'A'),(6,12,2,'D',2.54,44.00,'2017-02-02 16:32:21',0.00,'A'),(7,12,3,'H',2.32,90.00,'2017-02-02 17:12:21',208.80,'A'),(8,13,3,'H',2.32,12.00,'2017-02-02 17:00:01',0.00,'A'),(9,13,2,'H',2.32,50.00,'2017-02-02 17:16:22',0.00,'A'),(10,14,2,'A',2.32,60.00,'2017-02-02 12:12:22',0.00,'A'),(11,14,3,'A',2.32,123.00,'2017-02-02 17:16:22',0.00,'A'),(12,1,2,'A',2.32,40.00,'2017-02-05 22:16:22',NULL,'A'),(13,2,3,'D',2.32,32.00,'2017-02-03 09:16:22',NULL,'A'),(14,1,5,'H',2.32,7777.00,'2017-02-05 23:00:21',NULL,'A'),(15,2,5,'H',2.32,7777.00,'2017-02-05 23:00:45',NULL,'A');
/*!40000 ALTER TABLE `betting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club` (
  `id` smallint(4) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `name_ru` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `name_ru_UNIQUE` (`name_ru`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'Real Madrid','Реал Мадрид'),(2,'Barcelona','Барселона'),(3,'Atletico Madrid','Атлетико Мадрид'),(4,'Benfica','Бенфика'),(5,'Borussia Dortmund','Боруссия Дортмунд'),(6,'Bayern Munich','Бавария Мюнхен'),(7,'Arsenal London','Арсенал Лондон'),(8,'Bayer Leverkusen','Баер Леверкузен'),(9,'Manchester City','Манчестер Сити'),(10,'AS Monaco','Монако'),(11,'Porto','Порто'),(12,'Juventus','Ювентус'),(13,'Sevilla','Севилья'),(14,'Leicester City','Лестер Сити'),(15,'PSG','ПСЖ'),(16,'Napoli','Наполи'),(17,'Pittsburgh Penguins','Питтсбург Пингвинз'),(18,'Detroit Red Wings','Детройт Ред Уингз'),(19,'Columbus Blue Jackets','Коламбус Блю Джекетс'),(20,'Carolina Hurricanes','Каролина Харрикейнз'),(21,'Montreal Canadiens','Монреаль Канадиенс'),(22,'Washington Capitals','Вашингтон Кэпиталз'),(23,'Dinamo Minsk','Динамо Минск'),(24,'Avtomobilist','Автомобилист'),(25,'Avangard','Авангард'),(26,'Vityaz','Витязь'),(27,'Canada','Канада'),(28,'Great Britain','Великобритания'),(29,'USA','США'),(30,'Switzerland','Швейцария'),(31,'Toronto Raptors','Торонто Рэпторс'),(32,'Chicago Bulls','Чикаго Буллз'),(33,'Boston Celtics','Бостон Селтикс'),(34,'Los Angeles Lakers','Лос-Анджелес Лейкерс'),(35,'CSKA Moscow','ЦСКА Москва'),(36,'Panathinaikos','Панатинаикос'),(37,'Fenerbahce','Фенербахче'),(38,'Galatasaray','Галатасарай'),(39,'Belarus','Беларусь'),(40,'Russia','Россия'),(41,'Ukraine','Украина'),(42,'Spain','Испания'),(43,'Germany','Германия'),(44,'France','Франция');
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_sport` tinyint(2) unsigned NOT NULL,
  `id_tournament` tinyint(2) unsigned NOT NULL,
  `home_team` smallint(4) unsigned NOT NULL,
  `away_team` smallint(4) unsigned NOT NULL,
  `id_country` tinyint(3) unsigned NOT NULL,
  `start_time` datetime NOT NULL,
  `win_home_rate` decimal(4,2) unsigned DEFAULT NULL,
  `draw_rate` decimal(4,2) unsigned DEFAULT NULL,
  `win_away_rate` decimal(4,2) unsigned DEFAULT NULL,
  `result` enum('H','D','A') DEFAULT NULL,
  `is_prepared` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `status` enum('A','D') NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_key` (`id_sport`,`id_tournament`,`home_team`,`away_team`,`start_time`),
  KEY `competition_country_idx_fk_` (`id_country`),
  KEY `competition_away_team_fk_3_idx` (`id_sport`,`away_team`),
  KEY `competition_home_team_fk_3_idx` (`id_sport`,`home_team`),
  KEY `competition_tournament_fk4_idx` (`id_sport`,`id_tournament`),
  CONSTRAINT `competition_away_team_fk_2` FOREIGN KEY (`id_sport`, `away_team`) REFERENCES `team` (`id_sport`, `id_club`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `competition_country_fk_1` FOREIGN KEY (`id_country`) REFERENCES `country` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `competition_home_team_fk_3` FOREIGN KEY (`id_sport`, `home_team`) REFERENCES `team` (`id_sport`, `id_club`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `competition_tournament_fk_4` FOREIGN KEY (`id_sport`, `id_tournament`) REFERENCES `tournament` (`id_sport`, `id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition`
--

LOCK TABLES `competition` WRITE;
/*!40000 ALTER TABLE `competition` DISABLE KEYS */;
INSERT INTO `competition` VALUES (1,1,1,4,5,6,'2017-02-14 21:45:00',3.60,3.45,2.09,'H',1,'A'),(2,1,1,15,2,4,'2017-02-14 21:45:00',3.45,3.55,2.08,'H',1,'A'),(3,1,1,6,7,2,'2017-02-15 21:45:00',1.53,4.50,6.00,'H',1,'A'),(4,1,1,1,16,1,'2017-02-15 21:45:00',1.50,4.50,5.50,'H',1,'A'),(5,1,1,8,3,2,'2017-02-21 21:45:00',3.50,3.20,2.21,'A',1,'A'),(6,1,1,9,10,3,'2017-02-21 21:45:00',1.52,4.30,6.25,'H',1,'A'),(7,1,1,11,12,6,'2017-02-22 21:45:00',3.40,3.05,2.31,'A',1,'A'),(8,1,1,13,14,1,'2017-02-22 21:45:00',1.50,4.10,7.10,'H',1,'A'),(9,4,8,17,19,10,'2017-02-04 21:45:00',1.22,3.20,1.30,'D',1,'A'),(10,3,7,1,2,1,'2017-02-01 12:45:00',2.20,3.44,2.20,'H',1,'A'),(11,3,7,37,35,14,'2017-02-01 12:45:00',1.90,4.00,2.54,'A',1,'A'),(12,2,5,39,43,7,'2017-02-02 17:20:00',3.41,0.00,2.32,'H',1,'A'),(13,2,5,42,40,1,'2017-02-02 17:20:00',1.12,0.00,2.59,'A',1,'A'),(14,2,5,41,44,9,'2017-02-02 17:20:00',4.22,0.00,1.49,'H',1,'A');
/*!40000 ALTER TABLE `competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `name_ru` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `name_ru_UNIQUE` (`name_ru`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Spain','Испания'),(2,'Germany','Германия'),(3,'England','Англия'),(4,'France','Франция'),(5,'Italia','Италия'),(6,'Portugal','Португалия'),(7,'Belarus','Беларусь'),(8,'Russia','Россия'),(9,'Ukraine','Украина'),(10,'USA','США'),(11,'United Kingdom','Великобритания'),(12,'China','Китай'),(13,'Turkey','Турция'),(14,'Greece','Греция');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `name` char(3) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES ('BYN'),('CNY'),('EUR'),('GBP'),('RUB'),('UAH'),('USD');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `international_code`
--

DROP TABLE IF EXISTS `international_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `international_code` (
  `code` char(3) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `international_code`
--

LOCK TABLES `international_code` WRITE;
/*!40000 ALTER TABLE `international_code` DISABLE KEYS */;
INSERT INTO `international_code` VALUES ('1'),('33'),('34'),('351'),('375'),('380'),('39'),('44'),('49'),('7'),('86'),('90');
/*!40000 ALTER TABLE `international_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secret_question`
--

DROP TABLE IF EXISTS `secret_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `secret_question` (
  `id` tinyint(1) unsigned NOT NULL AUTO_INCREMENT,
  `text` varchar(26) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secret_question`
--

LOCK TABLES `secret_question` WRITE;
/*!40000 ALTER TABLE `secret_question` DISABLE KEYS */;
INSERT INTO `secret_question` VALUES (1,'Mother\'s maiden name'),(2,'Father\'s birthday dd/mm/yy'),(3,'My first school'),(4,'PIN code (4 digits)');
/*!40000 ALTER TABLE `secret_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sport`
--

DROP TABLE IF EXISTS `sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sport` (
  `id` tinyint(2) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(17) COLLATE utf8_bin NOT NULL,
  `name_ru` varchar(17) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `name_ru_UNIQUE` (`name_ru`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sport`
--

LOCK TABLES `sport` WRITE;
/*!40000 ALTER TABLE `sport` DISABLE KEYS */;
INSERT INTO `sport` VALUES (1,'Football','Футбол'),(2,'Tennis','Теннис'),(3,'Basketball','Баскетбол'),(4,'Hockey','Хоккей');
/*!40000 ALTER TABLE `sport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id_club` smallint(4) unsigned NOT NULL,
  `id_country` tinyint(3) unsigned NOT NULL,
  `id_sport` tinyint(2) unsigned NOT NULL,
  PRIMARY KEY (`id_sport`,`id_club`,`id_country`),
  KEY `idx_fk_team_country` (`id_country`),
  KEY `idx_fk_team_sport` (`id_sport`),
  KEY `team_club_fk_3_idx` (`id_club`),
  CONSTRAINT `team_club_fk_3` FOREIGN KEY (`id_club`) REFERENCES `club` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `team_country_fk_1` FOREIGN KEY (`id_country`) REFERENCES `country` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `team_sport_fk_2` FOREIGN KEY (`id_sport`) REFERENCES `sport` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,1,1),(2,1,1),(3,1,1),(13,1,1),(42,1,2),(1,1,3),(2,1,3),(5,2,1),(6,2,1),(8,2,1),(43,2,2),(7,3,1),(9,3,1),(14,3,1),(10,4,1),(15,4,1),(44,4,2),(12,5,1),(16,5,1),(4,6,1),(11,6,1),(39,7,2),(40,8,2),(35,8,3),(41,9,2),(17,10,4),(19,10,4),(37,13,3),(36,14,3);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tournament`
--

DROP TABLE IF EXISTS `tournament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournament` (
  `id` tinyint(2) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `name_ru` varchar(30) NOT NULL,
  `id_sport` tinyint(2) unsigned NOT NULL,
  PRIMARY KEY (`id`,`id_sport`),
  UNIQUE KEY `unique_key` (`name`,`id_sport`),
  KEY `idx_fk_tournament_sport` (`id_sport`),
  CONSTRAINT `tournament_sport_fk_1` FOREIGN KEY (`id_sport`) REFERENCES `sport` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournament`
--

LOCK TABLES `tournament` WRITE;
/*!40000 ALTER TABLE `tournament` DISABLE KEYS */;
INSERT INTO `tournament` VALUES (1,'UEFA Champions League','Лига Чемпионов UEFA',1),(2,'UEFA Europa League','Лига Европы UEFA',1),(3,'Spain. Primera Division','Испанская Примера',1),(4,'England. Premier League','Английская Премьерлига',1),(5,'Davis Cup','Кубок Девиса',2),(6,'NBA','НБА',3),(7,'Men. Euroleague','Евролига. Мужчины',3),(8,'NHL','НХЛ',4),(9,'KHL','КХЛ',4);
/*!40000 ALTER TABLE `tournament` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `date_of_birth` date NOT NULL,
  `email` varchar(254) NOT NULL,
  `id_country` tinyint(3) unsigned NOT NULL,
  `city` varchar(16) NOT NULL,
  `code` char(3) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `currency` char(3) NOT NULL,
  `balance` decimal(14,2) unsigned NOT NULL DEFAULT '0.00',
  `user_type` enum('C','B','A') NOT NULL DEFAULT 'C',
  `registration_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `locale` enum('en','ru') NOT NULL DEFAULT 'en',
  `is_banned` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `status` enum('A','D') NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`),
  KEY `idx_email` (`email`),
  KEY `fk_user_country1_idx` (`id_country`),
  KEY `fk_user_curency1_idx` (`currency`),
  KEY `fk_user_international_code1_idx` (`code`),
  CONSTRAINT `user_country_fk_1` FOREIGN KEY (`id_country`) REFERENCES `country` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_curency_fk_2` FOREIGN KEY (`currency`) REFERENCES `currency` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_international_code_fk_3` FOREIGN KEY (`code`) REFERENCES `international_code` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','1981-02-08','admin@example.com',7,'Minsk','375','172020327','BYN',0.00,'A','2017-02-04 00:00:01','ru',0,'A'),(2,'Иван','Иванов','1990-10-21','vanya1990@example.com',9,'Самара','7','9653847678','RUB',2320.00,'C','2017-02-04 12:28:34','ru',0,'A'),(3,'Adam','Smith','1790-07-17','admamSmith@example.com',12,'Edinburgh','44','1522327834','GBP',1234.00,'C','2017-02-04 18:35:03','en',0,'A'),(4,'Antonio','Stradivari','1644-12-18','antonioStradivari@outlook.com',5,'Cremona','39','8947878209','EUR',30000.00,'C','2017-02-04 23:35:03','en',0,'A'),(5,'Jose','Mourinho','1963-02-26','specialone@google.com',6,'Setubal','351','1111111111','GBP',7777777.00,'B','2017-02-05 12:00:17','en',0,'A'),(6,'Napoleon','Bonaparte','1769-10-15','elba@hotmail.com',4,'Ajaccio','33','1234567899','EUR',999999999.00,'C','2017-02-05 12:10:17','en',0,'A'),(7,'Jackie','Chan','1954-04-07','kungFu@baidu.com',12,'Hong Kong','86','7593232321','CNY',52635734.00,'C','2017-02-01 06:19:48','en',0,'A'),(8,'Bob','Kelso','1963-07-08','super_bobo@hotmail.com',10,'Nevada','1','8768768768','USD',0.00,'C','2017-04-14 00:47:52','en',0,'A'),(9,'Peter','Walker','1979-12-02','4ydak2008@mail.ru',5,'Milano','34','123456788','EUR',0.00,'C','2017-04-16 16:40:49','en',0,'A'),(10,'Котофей','Иванович','1981-08-16','kotofei@mail.ru',2,'Berlin','34','777777777','EUR',0.00,'C','2017-04-16 16:50:50','en',0,'A'),(12,'Контантин','Федоров','1992-08-19','kostia@gmail.com',8,'Иваново','7','312312312','RUB',0.00,'C','2017-05-02 00:34:14','ru',0,'A');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_private`
--

DROP TABLE IF EXISTS `user_private`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_private` (
  `id_user` int(10) unsigned NOT NULL,
  `id_sec_question` tinyint(1) unsigned NOT NULL,
  `answer` char(64) NOT NULL,
  `password` char(64) NOT NULL,
  PRIMARY KEY (`id_user`),
  KEY `idx_fk_user_private_secret_question` (`id_sec_question`),
  CONSTRAINT `user_private_secret_question_fk_1` FOREIGN KEY (`id_sec_question`) REFERENCES `secret_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_private_user_fk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_private`
--

LOCK TABLES `user_private` WRITE;
/*!40000 ALTER TABLE `user_private` DISABLE KEYS */;
INSERT INTO `user_private` VALUES (1,3,'31207a2065f46a5b948fce6fe5c13e85abaf5631e2f894b47dcd4fce14f6c57b','06e1eb40bdb7ac9715b3f4366d26cfbc92083819c2bf553b46cf155a64e849a9'),(2,4,'a6d7a047486452909bfbdb6491ea6fb917cc6d20c481ce3a738c8a4079314c14','06e1eb40bdb7ac9715b3f4366d26cfbc92083819c2bf553b46cf155a64e849a9'),(3,3,'f3966925d3b328c7fda7ebf6bc87747f2f15040ae6c16502a76d02e57003eb10','e24df920078c3dd4e7e8d2442f00e5c9ab2a231bb3918d65cc50906e49ecaef4'),(4,4,'03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','ce1eaa86b4e270954dc58f2b9545a13c6551dbd9375407461a5fa6ede97308fe'),(5,4,'41c991eb6a66242c0454191244278183ce58cf4a6bcd372f799e4b9cc01886af','06e1eb40bdb7ac9715b3f4366d26cfbc92083819c2bf553b46cf155a64e849a9'),(6,4,'2fcff7dd0889e5363cdb5b212ec610eeab0f9c05ad3746420d2ebef353abbdd6','b38e6c4382f9778832919e5dc214b65326b2feff5362ba50edfb6bda5d6e37f5'),(7,4,'46eaa26621e4955c1675b55d446c6d03325f458b59a465f898d42924010e7286','9b5f8025b05e94c162ff7f441776d4435a33d53000778b930f9476079b226ec4'),(9,1,'de1c6b5e204d6d0cb06688e00459049964efd0b842351c0431f92f2548b23670','06e1eb40bdb7ac9715b3f4366d26cfbc92083819c2bf553b46cf155a64e849a9'),(10,1,'5c72c118c276894771efe786910b21305ab31d61cd0d02634dda14c4149457b0','06e1eb40bdb7ac9715b3f4366d26cfbc92083819c2bf553b46cf155a64e849a9'),(12,4,'03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','06e1eb40bdb7ac9715b3f4366d26cfbc92083819c2bf553b46cf155a64e849a9');
/*!40000 ALTER TABLE `user_private` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'totalizator'
--
/*!50003 DROP PROCEDURE IF EXISTS `increase_user_balance` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `increase_user_balance`(idUser Int(10), amount DECIMAL(10,2))
BEGIN
	declare new_balance decimal(14,2);
    set new_balance = (select balance from totalizator.user where id = idUser) + amount;
    UPDATE `totalizator`.`user` SET `balance`=new_balance WHERE `id`=idUser;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-05 14:35:12
