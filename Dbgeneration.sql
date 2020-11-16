CREATE DATABASE IF NOT EXISTS Projet_cloud;
CREATE TABLE Users (
 id VARCHAR(255) NOT NULL,
 firstName VARCHAR(255),
 lastName VARCHAR(255),
 birthDay DATE,
 latitude FLOAT,
 longitude FLOAT, 
 PRIMARY KEY (ActorId));