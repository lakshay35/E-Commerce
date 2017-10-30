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
/*!40000 ALTER TABLE `agency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `isbn` int(11) NOT NULL,
  `category` varchar(45) DEFAULT NULL,
  `authorName` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `picture` blob,
  `edition` int(11) DEFAULT NULL,
  `publisher` varchar(45) DEFAULT NULL,
  `publicationYear` int(11) DEFAULT NULL,
  `qtyInStock` int(11) DEFAULT NULL,
  `minThreshold` int(11) DEFAULT NULL,
  `buyingPrice` double DEFAULT NULL,
  `sellingPrice` double DEFAULT NULL,
  `supplierID` int(10) unsigned DEFAULT NULL,
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
  `shippingAddress` int(11) DEFAULT NULL,
  `billingAddress` int(11) DEFAULT NULL,
  `paymentMethod` varchar(45) DEFAULT NULL,
  `confirmationNumber` varchar(45) DEFAULT NULL,
  `transactionID` int(11) DEFAULT NULL,
  `userID` int(10) unsigned NOT NULL,
  `orderTotal` double DEFAULT NULL,
  `CCid` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderNumber`),
  KEY `CCid` (`CCid`),
  KEY `userID` (`userID`),
  KEY `agencyID` (`agencyID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`CCid`) REFERENCES `creditcard` (`CCid`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`agencyID`) REFERENCES `agency` (`agencyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
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
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (9,'Brad','Reeves',NULL,'bkr02962@uga.edu',NULL,'Customer','pass',8714,'verified'),(10,'Brad','Reeves',NULL,'reevesbk@gmail.com',NULL,'Customer','pass',8103,'verified');
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

-- Dump completed on 2017-10-30 19:46:57
