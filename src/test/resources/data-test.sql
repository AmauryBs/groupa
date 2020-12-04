CREATE SCHEMA `dbtest`;
USE `dbtest`;
CREATE TABLE `Position` (
                            `positionId` int NOT NULL AUTO_INCREMENT,
                            `lat` DECIMAL(11,8),
                            `lon` DECIMAL(11,8),
                            PRIMARY KEY (positionId));

CREATE TABLE `User` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `firstName` VARCHAR(255),
                        `lastName` VARCHAR(255),
                        `birthDay` DATE,
                        `position` int,
                        PRIMARY KEY (id),
                        FOREIGN KEY (position) REFERENCES `Position` (positionId)) ENGINE=InnoDB DEFAULT CHARSET=utf8;