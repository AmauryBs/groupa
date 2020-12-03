USE `bltnqtwwulzbsp3yfedg`;

DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Position;

 CREATE TABLE Position (
    `PositionID` int NOT NULL AUTO_INCREMENT,
    `lat` FLOAT,
    `lon` FLOAT,
 PRIMARY KEY (PositionID));
 
CREATE TABLE User (
	`id` int NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(255),
    `lastName` VARCHAR(255),
    `birthDay` DATE,
    `PositionID` int,
    PRIMARY KEY (id),
    FOREIGN KEY (PositionID) REFERENCES Position (PositionID));
