-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinebookstoredb
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `street` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zipcode` varchar(45) DEFAULT NULL,
  `userID` int(10) unsigned DEFAULT NULL,
  `agencyID` int(10) unsigned DEFAULT NULL,
  `supplierID` int(10) unsigned DEFAULT NULL,
  `addressID` int(11) NOT NULL,
  PRIMARY KEY (`addressID`),
  KEY `userID` (`userID`),
  KEY `agencyID` (`agencyID`),
  KEY `supplierID` (`supplierID`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `address_ibfk_2` FOREIGN KEY (`agencyID`) REFERENCES `agency` (`agencyID`),
  CONSTRAINT `address_ibfk_3` FOREIGN KEY (`supplierID`) REFERENCES `supplier` (`supplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES ('101 Davis St','Athens','GA','30606',16,NULL,NULL,1),('827 Windy Ridge Ln SE','Atlanta','GA','30339',16,NULL,NULL,2);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agency`
--

DROP TABLE IF EXISTS `agency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agency` (
  `agencyID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `aName` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `contactName` varchar(45) DEFAULT NULL,
  `contactPhone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`agencyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency`
--

LOCK TABLES `agency` WRITE;
/*!40000 ALTER TABLE `agency` DISABLE KEYS */;
INSERT INTO `agency` VALUES (0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `agency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `isbn` int(45) NOT NULL,
  `category` varchar(45) DEFAULT NULL,
  `authorName` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `picture` text,
  `edition` int(11) DEFAULT NULL,
  `publisher` varchar(45) DEFAULT NULL,
  `publicationYear` int(11) DEFAULT NULL,
  `qtyInStock` int(11) DEFAULT NULL,
  `minThreshold` int(11) DEFAULT NULL,
  `buyingPrice` double DEFAULT NULL,
  `sellingPrice` double DEFAULT NULL,
  `supplierID` int(10) unsigned DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`isbn`),
  KEY `supplierID` (`supplierID`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`supplierID`) REFERENCES `supplier` (`supplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1234112,'Fantasy','Brad','Alice','https://images-na.ssl-images-amazon.com/images/I/61OXoGt7EBL._SY346_.jpg',1,'Me',2017,15,0,1,2.11,NULL,'Hey there'),(60254920,'Children','Maurice Sendak','Where the Wild Things Are','https://images-na.ssl-images-amazon.com/images/I/61ulN35aErL._SY452_BO1,204,203,200_.jpg',1,'Harper Collins',2012,4,5,1,5,NULL,'A kid meets a bunch of monsters in the woods. Stuff happens.'),(1222222222,'Fiction','Edgar Rice Burroughs','Tarzan of the Apes','https://images-na.ssl-images-amazon.com/images/I/61beB3RAzbL.jpg',1,'Dover Publications',2012,25,12,5,6,NULL,'It is Tarzan. Everybody already knows what this book is about.'),(1234567892,'Fantasy','Lewis Carrol','Alice in Wonderland','https://images-na.ssl-images-amazon.com/images/I/61OXoGt7EBL._SY346_.jpg',12,'Random House Books for Young Readers',2009,50,0,2,10,NULL,'Hey there'),(1450517161,'Historical','Herman Melville','Moby Dick','https://images-na.ssl-images-amazon.com/images/I/51-3tPs3VdL.jpg',13,'Dover Publications',2012,49,0,8.5,10.5,NULL,'Hey there');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `cartID` int(11) NOT NULL,
  `userID` int(10) unsigned NOT NULL,
  `promoID` int(10) unsigned DEFAULT NULL,
  `isbn` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`cartID`),
  KEY `isbn` (`isbn`),
  KEY `promoID` (`promoID`),
  KEY `userID` (`userID`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`promoID`) REFERENCES `promotion` (`promoID`),
  CONSTRAINT `cart_ibfk_3` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,16,0,1450517161,1,10.5);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcard` (
  `userID` int(10) unsigned NOT NULL,
  `CCnumber` int(11) DEFAULT NULL,
  `CCtype` varchar(45) DEFAULT NULL,
  `expireDate` datetime DEFAULT NULL,
  `CCid` int(11) NOT NULL,
  PRIMARY KEY (`CCid`),
  KEY `userID` (`userID`),
  CONSTRAINT `creditcard_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
INSERT INTO `creditcard` VALUES (16,1122334455,'MasterCard','2017-12-31 00:00:00',443),(16,1111222211,'Visa','2017-12-31 00:00:00',555);
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderNumber` int(11) NOT NULL,
  `agencyID` int(10) unsigned DEFAULT NULL,
  `orderStatus` varchar(45) DEFAULT NULL,
  `orderDate` datetime DEFAULT NULL,
  `shippingAddress` char(34) DEFAULT NULL,
  `billingAddress` char(34) DEFAULT NULL,
  `paymentMethod` varchar(45) DEFAULT NULL,
  `confirmationNumber` varchar(45) DEFAULT NULL,
  `userID` int(10) unsigned NOT NULL,
  `orderTotal` double DEFAULT NULL,
  PRIMARY KEY (`orderNumber`),
  KEY `userID` (`userID`),
  KEY `agencyID` (`agencyID`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`agencyID`) REFERENCES `agency` (`agencyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,0,'completed','2017-11-26 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf1',16,20.5),(2,0,'completed','2017-11-26 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf2',16,2.11),(3,0,'completed','2017-11-26 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf3',16,11),(4,0,'completed','2017-11-26 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf4',16,54.5),(5,0,'completed','2017-11-27 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf5',16,8.11),(6,0,'completed','2017-11-27 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf6',16,50.5),(7,0,'completed','2017-11-27 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf7',16,16.5),(8,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf8',16,14.11),(9,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf9',16,14.11),(10,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf10',16,14.11),(11,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf11',16,14.11),(12,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf12',16,14.11),(13,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf13',16,10.5),(14,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf14',16,8.44),(15,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf15',16,5),(16,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf16',16,21),(17,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf17',16,20.5),(18,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf18',16,6),(19,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf19',16,10.5),(20,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf20',16,129.8),(21,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf21',16,141.91000000000003),(22,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf22',16,6),(23,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf23',16,30),(24,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf24',16,30),(25,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf25',16,30),(26,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf26',16,6),(27,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf27',16,10.5),(28,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf28',16,10),(29,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf29',16,1.899),(30,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf30',16,32),(31,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','MasterCard 4433','Conf31',16,10.5),(32,0,'completed','2017-11-28 00:00:00','101 Davis St, Athens, GA, 30606','101 Davis St, Athens, GA, 30606','Visa 2211','Conf32',16,5.25);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion` (
  `promoID` int(10) unsigned NOT NULL,
  `pName` varchar(45) DEFAULT NULL,
  `percentage` double DEFAULT NULL,
  `expiration` datetime DEFAULT NULL,
  PRIMARY KEY (`promoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` VALUES (0,'No Promo',0,NULL),(1,'getTen',10,'2017-10-31 00:00:00'),(2,'gethalf',50,'2017-12-31 00:00:00');
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supplierID` int(10) unsigned NOT NULL,
  `sName` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `contactName` varchar(45) DEFAULT NULL,
  `contactPhone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`supplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `orderNumber` int(11) NOT NULL,
  `transactionID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `isbn` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  `promoID` int(10) unsigned DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`transactionID`),
  KEY `isbn` (`isbn`),
  KEY `promoID` (`promoID`),
  KEY `orderNumber` (`orderNumber`),
  CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`),
  CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`promoID`) REFERENCES `promotion` (`promoID`),
  CONSTRAINT `transactions_ibfk_3` FOREIGN KEY (`orderNumber`) REFERENCES `orders` (`orderNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,1,60254920,2,0,10),(1,2,1450517161,1,0,10.5),(2,3,1234112,1,0,2.11),(3,4,1222222222,1,0,6),(3,5,60254920,1,0,5),(4,6,1234567892,2,0,20),(4,7,1222222222,4,0,24),(4,8,1450517161,1,0,10.5),(5,9,1234112,1,0,2.11),(5,10,1222222222,1,0,6),(6,11,1234567892,4,0,40),(6,12,1450517161,1,0,10.5),(7,13,1222222222,1,0,6),(7,14,1450517161,1,0,10.5),(8,15,1234112,1,0,2.11),(8,16,1222222222,2,0,12),(13,17,1450517161,1,0,10.5),(14,18,1234112,4,0,8.44),(15,19,60254920,1,0,5),(16,20,1450517161,2,0,21),(17,21,1234567892,1,0,10),(17,22,1450517161,1,0,10.5),(18,23,1222222222,1,0,6),(19,24,1450517161,1,0,10.5),(20,25,1234567892,1,0,10),(21,26,1234112,1,0,2.11),(22,27,1222222222,1,0,6),(23,28,1222222222,5,0,30),(26,29,1222222222,1,0,6),(27,30,1450517161,1,0,10.5),(28,31,1234567892,1,0,10),(29,32,1234112,1,0,2.11),(30,33,1450517161,2,0,21),(30,34,60254920,1,0,5),(30,35,1222222222,1,0,6),(31,36,1450517161,1,0,10.5),(32,37,1450517161,1,0,10.5);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fName` varchar(45) DEFAULT NULL,
  `lName` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `paymentInfo` varchar(45) DEFAULT NULL,
  `userType` varchar(45) DEFAULT NULL,
  `userPassword` varchar(45) DEFAULT NULL,
  `userCode` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'unverified',
  `subscribed` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (14,'Bradley','Reeves',NULL,'reevesbk@gmail.com',NULL,'SystemAdmin','pass',3618,'verified',0),(15,'Brad','Reeves',NULL,'bkr02962@uga.edu',NULL,'Customer','pass',1048,'verified',0),(16,'Dhanashree','Joshi',NULL,'dhanashree.joshi0@gmail.com',NULL,'Customer','abc',1988,'verified',0),(17,'Dany','Jo',NULL,'dpj88416@uga.edu',NULL,'Customer','abc',1144,'verified',0);
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

-- Dump completed on 2017-12-01 13:39:16
