CREATE DATABASE onlineBookstoredb;
USE onlineBookstoredb;

CREATE TABLE Agency
(
agencyID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
aName VARCHAR(45),
address VARCHAR(45),
phone VARCHAR(45),
contactName VARCHAR(45),
contactAddress VARCHAR(45),
contactPhone VARCHAR(45)
);

CREATE TABLE Promotion
(
promoID INT UNSIGNED PRIMARY KEY,
pName VARCHAR(45),
percentage DOUBLE,
expiration DATETIME
);

CREATE TABLE Supplier
(
supplierID INT UNSIGNED PRIMARY KEY,
sName VARCHAR(45),
address VARCHAR(45),
phone VARCHAR(45),
contactName VARCHAR(45),
contactAddress VARCHAR(45),
contactPhone VARCHAR(45)
);

CREATE TABLE User
(
userID INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
fName VARCHAR(45),
lName VARCHAR(45),
phone VARCHAR(45),
address VARCHAR(45),
email VARCHAR(45),
paymentInfo VARCHAR(45),
userType VARCHAR(45),
userPassword VARCHAR(45)
);

CREATE TABLE CreditCard
(
userID INT UNSIGNED NOT NULL,
CCnumber INT,
CCtype VARCHAR(45),
expireDate DATETIME,
CCid INT PRIMARY KEY,
FOREIGN KEY (userID) REFERENCES User(userID)
);

CREATE TABLE Address
(
street VARCHAR(45),
city VARCHAR(45),
state VARCHAR(45),
zipcode VARCHAR(45),
userID INT UNSIGNED NOT NULL,
FOREIGN KEY (userID) REFERENCES User(userID)
);

CREATE TABLE Book
(
isbn INT NOT NULL PRIMARY KEY,
category VARCHAR(45),
authorName VARCHAR(45),
title VARCHAR(45),
picture BLOB,
edition INT,
publisher VARCHAR(45),
publicationYear INT,
qtyInStock INT,
minThreshold INT,
buyingPrice DOUBLE,
sellingPrice DOUBLE,
supplierID INT UNSIGNED,
FOREIGN KEY (supplierID) REFERENCES Supplier(supplierID)
);

CREATE TABLE Cart
(
cartID INT NOT NULL PRIMARY KEY,
userID INT UNSIGNED NOT NULL,
promoID INT UNSIGNED,
isbn INT NOT NULL,
qty INT,
total DOUBLE,
FOREIGN KEY (isbn) REFERENCES Book(isbn),
FOREIGN KEY (promoID) REFERENCES Promotion(promoID),
FOREIGN KEY (userID) REFERENCES User(userID)
);

CREATE TABLE Orders
(
orderNumber INT NOT NULL PRIMARY KEY,
agencyID INT UNSIGNED,
orderStatus VARCHAR(45),
orderDate DATETIME,
shippingAddress VARCHAR(45),
billingAddress VARCHAR(45),
paymentMethod VARCHAR(45),
confirmationNumber VARCHAR(45),
transactionID INT,
userID INT UNSIGNED NOT NULL,
orderTotal DOUBLE,
CCid INT,
FOREIGN KEY (CCid) REFERENCES CreditCard(CCid),
FOREIGN KEY (userID) REFERENCES User(userID),
FOREIGN KEY (agencyID) REFERENCES Agency(agencyID)
);

CREATE TABLE Transactions
(
orderNumber INT NOT NULL,
transactionID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
isbn INT NOT NULL,
qty INT,
promoID INT UNSIGNED,
total DOUBLE,
FOREIGN KEY (isbn) REFERENCES Book(isbn),
FOREIGN KEY (promoID) REFERENCES Promotion(promoID),
FOREIGN KEY (orderNumber) REFERENCES Orders(orderNumber)
);