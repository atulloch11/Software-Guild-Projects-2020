DROP DATABASE IF EXISTS guessgameDBTest;
CREATE DATABASE guessgameDBTest;
USE guessgameDBTest;

CREATE TABLE game(
gameID INT PRIMARY KEY AUTO_INCREMENT,
answer VARCHAR(40) NOT NULL,
finishedGame BOOLEAN DEFAULT false);

CREATE TABLE round(
roundID INT PRIMARY KEY AUTO_INCREMENT,
gameID INT NOT NULL,
userGuess VARCHAR(40) NOT NULL,
guessResults VARCHAR(40) NOT NULL,
guessTime Timestamp NOT NULL,
FOREIGN KEY (gameID) REFERENCES game(gameID));