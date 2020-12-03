USE `bltnqtwwulzbsp3yfedg`;

 CREATE TABLE Position (
    `PositionID` int NOT NULL AUTO_INCREMENT,
    `lat` FLOAT,
    `lon` FLOAT,
 PRIMARY KEY (PositionID));
 
CREATE TABLE Users (
	`id` int NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(255),
    `lastName` VARCHAR(255),
    `birthDay` DATE,
    `PositionID` int,
    PRIMARY KEY (id),
    FOREIGN KEY (PositionID) REFERENCES Position (PositionID));
