-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: grocery_store_v1
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Branded Foods'),(2,'Households'),(3,'Vegetables'),(4,'Fruits'),(5,'Kitchen'),(6,'Short Codes'),(7,'Beverages'),(8,'Soft Drinks'),(9,'Juices'),(10,'Pet Food'),(11,'Frozen Foods'),(12,'Frozen Snacks'),(13,'Frozen Nonveg'),(14,'Bread & Bakery');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkout`
--

DROP TABLE IF EXISTS `checkout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkout` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int DEFAULT NULL,
  `address_type` varchar(255) DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `landmark` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `created_datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  `list_id_order` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_id_user_checkout` (`id_user`),
  CONSTRAINT `FK_id_user_checkout` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkout`
--

LOCK TABLES `checkout` WRITE;
/*!40000 ALTER TABLE `checkout` DISABLE KEYS */;
INSERT INTO `checkout` VALUES (1,1,'Home',20,'Nguyễn Văn A','090900101010','Landmark 81','Hồ Chí Mình','2024-01-06 09:16:28','1,2'),(2,1,'Home',20,'Nguyễn Văn A','090900101010','Landmark 82','Hồ Chí Mình','2024-02-18 08:04:48','3'),(3,1,'Office',15,'Nguyễn Văn A','090900101010','Gigamall','Hồ Chí Mình','2024-02-18 08:07:03','4'),(4,1,'Office',27,'Nguyễn Văn A','090900101010','Gigamall','Hồ Chí Mình','2024-02-18 08:07:03','5'),(5,1,'Office',100,'Nguyễn Văn A','090900101010','Sai Gon Center','Hồ Chí Mình','2024-02-18 08:07:32','6');
/*!40000 ALTER TABLE `checkout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_product` int DEFAULT NULL,
  `id_user` int DEFAULT NULL,
  `purchase_price` float DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `created_datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_checked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_id_product_order_detail` (`id_product`),
  KEY `FK_id_user_order_detail` (`id_user`),
  CONSTRAINT `FK_id_product_order_detail` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`),
  CONSTRAINT `FK_id_user_order_detail` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,1,1,3,5,'2024-01-06 09:13:57',1),(2,2,1,5,1,'2024-01-06 09:14:12',1),(3,4,1,8,1,'2024-02-18 05:17:45',1),(4,3,1,3,2,'2024-02-18 05:17:45',1),(5,6,1,11,1,'2024-02-18 05:17:45',1),(6,7,1,9,3,'2024-02-18 05:17:45',1),(7,8,1,7,10,'2024-02-18 05:17:45',1),(8,9,1,8,1,'2024-02-18 05:17:45',1),(9,10,1,5,1,'2024-02-18 05:17:45',1);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_checkout` int DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `id_user_card` int DEFAULT NULL,
  `created_datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_paid` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_id_checkout_payment` (`id_checkout`),
  KEY `FK_id_user_card_payment` (`id_user_card`),
  CONSTRAINT `FK_id_checkout_payment` FOREIGN KEY (`id_checkout`) REFERENCES `checkout` (`id`),
  CONSTRAINT `FK_id_user_card_payment` FOREIGN KEY (`id_user_card`) REFERENCES `user_card` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,1,'COD',NULL,'2024-01-06 09:35:47',1),(2,2,'COD',NULL,'2024-02-18 08:14:50',1),(3,3,'CIA',NULL,'2024-02-18 08:16:01',1),(4,4,'CIA',NULL,'2024-02-18 08:16:01',1),(5,5,'CIA',NULL,'2024-02-18 08:16:01',1);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_category` int DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `average_rating` double DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `original_price` float DEFAULT NULL,
  `discount_percent` float DEFAULT NULL,
  `is_activated` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_id_category_product` (`id_category`),
  CONSTRAINT `FK_id_category_product` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,1,'knor_instant_soup.jpg','Knor Instant Soup','100 Gram',3,5000,4.5,NULL,5,0,1),(2,1,'Chings_Noodles.jpg','Chings Noodles ','75 Gram',5,8,4.7,NULL,8,0,1),(3,1,'maggie_instant_noodles.jpg','Maggi Instant Noodles','80 Gram',5,194,3.8,NULL,6,0,1),(4,1,NULL,'Yippee Noodles','70 Gram',7,192,4.1,NULL,5,0,1),(5,1,NULL,'Top Ramen Noodles','85 Gram',4,374,4.9,NULL,7,0,1),(6,1,'Sunfeast_Yippee_Magic_Masala_Noodles.jpg','Sunfeast Yippee Magic Masala Noodles','80 Gram',9,237,3.6,NULL,6,0,1),(7,1,'Maggi_2_Minute_Noodles.jpg','Maggi 2-Minute Noodles','70 Gram',8,185,4.3,NULL,5,0,1),(8,1,NULL,'Wai Wai Instant Noodles','75 Gram',6,286,3.5,NULL,4,0,1),(9,1,NULL,'Nissin Cup Noodles','60 Gram',9,328,4.8,NULL,7,0,1),(10,1,NULL,'Knorr Chinese Noodles','90 Gram',7,444,4.7,NULL,8,0,1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN',NULL),(2,'ROLE_USER',NULL),(5,'ROLE_MARKETING','For telesale, saler and broker');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_card`
--

DROP TABLE IF EXISTS `user_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_card` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int DEFAULT NULL,
  `name_card` varchar(255) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  `cardholder_name` varchar(255) DEFAULT NULL,
  `valid_thru` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_id_user_user_card` (`id_user`),
  CONSTRAINT `FK_id_user_user_card` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_card`
--

LOCK TABLES `user_card` WRITE;
/*!40000 ALTER TABLE `user_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id_role` int NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `is_activated` tinyint(1) DEFAULT '1',
  `created_datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_id_roles_users` (`id_role`),
  CONSTRAINT `FK_id_roles_users` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,'nguyenvana@gmail.com','$2a$12$ierwwFEE1T2rfNwUyvDLfe4He7QYYg1Bdii.32aD7w42Javo7Zs2C',2,'Nguyễn Văn A',NULL,NULL,1,'2024-01-06 09:13:00'),(2,NULL,'khang@gmail.com','$2a$12$n4B.Mg7zZeohyYuUk2D4sOk1d6f6AL4OdL04Ka9m9UBWuGbn7tr.u',1,NULL,NULL,NULL,1,'2024-01-20 17:30:11'),(4,'khangtictoc','danh@gmail.com','$2a$10$lMHTQl4hxRYvdwtzTqjEMesTq5z2d4CTdFAMNbr7bN4RZze87NvAu',2,NULL,NULL,'1234567890',0,NULL),(10,'test','godknowwhere@gmail.com','$2a$10$gxt1wPmEIzyNI0caFWQQLO7FtBiKyO8Uos9jIj2KoZBwOogNFc1Om',1,'test','test','123',1,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'grocery_store_v1'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-03 23:25:05
