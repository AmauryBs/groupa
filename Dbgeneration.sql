USE `bltnqtwwulzbsp3yfedg`;
CREATE TABLE Users (
 `id` VARCHAR(255) NOT NULL,
 `firstName` VARCHAR(255),
 `lastName` VARCHAR(255),
 `birthDay` DATE,
 `lat` FLOAT,
 `long` FLOAT, 
 PRIMARY KEY (id));