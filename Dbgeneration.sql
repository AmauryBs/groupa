USE `bltnqtwwulzbsp3yfedg`;
CREATE TABLE Users (
    `id` MEDIUMINT  NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(255),
    `lastName` VARCHAR(255),
    `birthDay` DATE,
    `lat` FLOAT,
    `lon` FLOAT,
    PRIMARY KEY (id));
