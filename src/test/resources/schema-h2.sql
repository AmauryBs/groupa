CREATE SCHEMA `dbtest`;
USE `dbtest`;
CREATE TABLE `Position` (
                            `positionId` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                            `lat` DECIMAL(12,8),
                            `lon` DECIMAL(12,8));

CREATE TABLE `User` (
                        `id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        `firstName` VARCHAR(255),
                        `lastName` VARCHAR(255),
                        `birthDay` DATE,
                        `position` int,
                        FOREIGN KEY (position) REFERENCES `Position` (positionId));