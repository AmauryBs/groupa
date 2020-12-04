CREATE DATABASE IF NOT EXISTS `cloudgroupa` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `Position`;

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
