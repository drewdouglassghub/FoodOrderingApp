-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: foodapp
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `CUSTOMER_ID` int(11) NOT NULL,
  `CUSTOMER_FIRSTNAME` varchar(20) NOT NULL,
  `CUSTOMER_LASTNAME` varchar(20) NOT NULL,
  `CUSTOMER_VISITDATE` datetime NOT NULL,
  `CUSTOMER_EMAIL` varchar(40) NOT NULL,
  `CUSTOMER_PHONE` int(11) NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_cart`
--

DROP TABLE IF EXISTS `customer_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer_cart` (
  `ORDER_ID` int(11) NOT NULL,
  `PROD_ID` int(11) NOT NULL,
  `PROD_NAME` varchar(20) NOT NULL,
  `PROD_QTY` int(2) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`ORDER_ID`,`PROD_ID`),
  KEY `PROD_IDFK_idx` (`PROD_ID`),
  CONSTRAINT `CART_IDFK` FOREIGN KEY (`ORDER_ID`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `PROD_IDFK` FOREIGN KEY (`PROD_ID`) REFERENCES `menu_items` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_cart`
--

LOCK TABLES `customer_cart` WRITE;
/*!40000 ALTER TABLE `customer_cart` DISABLE KEYS */;
INSERT INTO `customer_cart` VALUES (1,1,'Nachos',1,2);
/*!40000 ALTER TABLE `customer_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_departments`
--

DROP TABLE IF EXISTS `menu_departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `menu_departments` (
  `DEPARTMENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEPARTMENT_NAME` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`DEPARTMENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_departments`
--

LOCK TABLES `menu_departments` WRITE;
/*!40000 ALTER TABLE `menu_departments` DISABLE KEYS */;
INSERT INTO `menu_departments` VALUES (1,'Appetizers'),(2,'Seafood');
/*!40000 ALTER TABLE `menu_departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_items`
--

DROP TABLE IF EXISTS `menu_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `menu_items` (
  `ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_NAME` varchar(20) NOT NULL,
  `ITEM_PRICE` double NOT NULL,
  `ITEM_IMAGE` varchar(40) DEFAULT NULL,
  `ITEM_DESCRIPTION` varchar(200) NOT NULL,
  `DEPARTMENT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ITEM_ID`),
  KEY `department` (`DEPARTMENT`),
  CONSTRAINT `menu_items_ibfk_1` FOREIGN KEY (`DEPARTMENT`) REFERENCES `menu_departments` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_items`
--

LOCK TABLES `menu_items` WRITE;
/*!40000 ALTER TABLE `menu_items` DISABLE KEYS */;
INSERT INTO `menu_items` VALUES (1,'Nachos',3,NULL,'Nacho chips with nacho cheese',1),(2,'Pickle',1,'','One big pickle',1),(3,'Lobster',15,'','Delicious Lobster',2);
/*!40000 ALTER TABLE `menu_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_items` (
  `ORDER_ID` int(11) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `ORDER_IDFK` (`ORDER_ID`),
  KEY `ITEM_ID_FK` (`ITEM_ID`),
  CONSTRAINT `ITEM_ID_FK` FOREIGN KEY (`ITEM_ID`) REFERENCES `menu_items` (`item_id`),
  CONSTRAINT `order_items_orders_fk` FOREIGN KEY (`ORDER_ID`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,2,1,1),(1,1,1,2),(1,1,1,3),(1,3,1,4),(1,1,2,5),(1,1,3,6),(1,1,1,7),(1,1,2,8);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `ORDER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`ORDER_ID`),
  KEY `CUSTOMER_IDFK` (`USER_ID`),
  CONSTRAINT `orders_user_fk` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `promotion` (
  `PROMOTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROMOTION_NAME` varchar(30) DEFAULT NULL,
  `PROMOTION_TYPE` varchar(30) DEFAULT NULL,
  `PROMOTION_AMOUNT` double DEFAULT NULL,
  `ACTIVE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`PROMOTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` VALUES (2,'save15','P',0.15,'Y');
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_FIRSTNAME` varchar(20) NOT NULL,
  `USER_LASTNAME` varchar(20) NOT NULL,
  `USER_VISITDATE` datetime NOT NULL,
  `USER_EMAIL` varchar(40) NOT NULL,
  `USER_PHONE` varchar(11) NOT NULL,
  `USER_NAME` varchar(50) NOT NULL,
  `USER_PASSWORD` varchar(50) NOT NULL,
  `USER_AUTH` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','2019-11-11 00:00:00','drew@hotmail.com','5555555555','admin','admin','ADMIN'),(2,'customer','customer','2019-11-11 00:00:00','drew@hotmail.com','5555555555','customer','customer','CUSTOMER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-22  1:22:02
