-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: news
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1
USE news;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONarticlesE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `brief` varchar(255) DEFAULT NULL,
  `content` text,
  `image` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `time` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` VALUES (1,'thuan dep trai','','12.png','ai dep trai','2024-03-24 00:00:00.000000','duong nhien roi'),(2,'thuan dang e','','wp10910015-kotaro-lives-alone-wallpapers.jpg','Mobile','2024-03-12 00:00:00.000000','tinh trang hon nhan cua thuan'),(3,'\n','','Screenshot_2024-02-14_17-49-39.png','Mobile','2024-03-28 10:20:44.939000','em gai thuan cung dep nua'),(5,'\n','','https://mohinhfigure.com/wp-content/uploads/2021/12/loli-la-gi-1.jpg','Mobile','2024-03-28 15:35:46.998000','\n'),(7,'\n','','1685709988979792.png','Mobile','2024-03-29 10:50:20.058000','bai viet nao do 1'),(8,'\n','','https://mohinhfigure.com/wp-content/uploads/2021/12/loli-la-gi-2.jpg','Mobile','2024-03-29 11:14:15.315000','rtert'),(9,'la bai de','','1685709988979792.png','Mobile','2024-03-29 18:05:00.704000','bai 2'),(10,'katsura','<p>asdfasdfasdfasdfasdfa asdf asdfaj sdilf oas hdfihasdf asdfhaksdfhk ashdkfhskadhfiasdfv as hfhsijvbasfijolvhsdvbas;jifdvhikawehviqaujwe iuqawehifuj; oqaijwseg ijqasedhiovjoik;adcvhjiosdhiohasdflhijvbn;jisdcbvjiasdhfl;ivjnas;cjvno;kaSDNFLJNQAWVKMNASDCO;KVJ AOIWSDfp&#39;oaws jdfio;ASOP&#39;DFO&#39;Ajfgkojsdj&#39;pfoj:&quot;JQP</p>\n\n<p>WERGOLKSDJF;OJIJ WRP[O</p>\n\n<p>BS;OERJG&#39;OKDSFP[</p>\n\n<p>VOKQE&#39;ORPGJS;OD&#39;FJG&#39;KOWERJGP[</p>\n\n<p>NJLKHNP&#39;&#39;OLn&#39;p;</p>\n\n<p>lsfmbolke gl&#39;kjsrfp[</p>\n\n<p>ghk &#39;opsefgh&#39;lkkf</p>\n\n<p>[bn;k&#39;w erpt</p>\n\n<p>hok p[</p>\n\n<p>fgh&#39;pqreysrg&#39;phoped&#39;fg;&#39;sdfglk;&#39;sdfjgjwdlfkgjoedfgjoier jo[pejrfogdfg ksdf g;la jefog p&#39;jwfedo[pg uq-3rjg;olahewjfdg;knwertp</p>\n\n<p>&#39;ogjakl,dsjcvbokjwe&#39;pkolfhj&#39;paefjop&#39;aej&#39;op dfg owdfbpio&#39;ajfg&#39;oa hefd;o&#39;sd&#39;iopfg olwkrefghoegoijwefo&#39;gj we&#39;oprgj &#39;wpoerjg &#39;owetjrhp&#39;ef</p>\n\n<p>[hp&#39;</p>\n\n<p>aefjgp&#39;owejrgh;lqejfgo&#39;piwjerhtgokqnefgo;kqejrwtyopiqjew ioWEO;&#39;JiopjPO&#39;G JAOGJASDKERIOAokefgweroiwerg&#39;co&#39;greo</p>\n','katsura kotonoha school days konachancom Konachancom Anime.jpg','Mobile','2024-03-31 03:49:58.798000','school days');
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`),
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'123456','admin'),(2,'123456','user');
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

-- Dump completed on 2024-03-31 14:03:23
