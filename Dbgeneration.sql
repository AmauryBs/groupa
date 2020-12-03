USE `bltnqtwwulzbsp3yfedg`;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Position;

 CREATE TABLE Position (
    `positionId` int NOT NULL AUTO_INCREMENT,
    `lat` FLOAT,
    `lon` FLOAT,
 PRIMARY KEY (positionId));
 
CREATE TABLE User (
	`id` int NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(255),
    `lastName` VARCHAR(255),
    `birthDay` DATE,
    `positionId` int,
    PRIMARY KEY (id),
    FOREIGN KEY (positionId) REFERENCES Position (positionId));
