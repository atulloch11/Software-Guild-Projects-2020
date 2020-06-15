DROP DATABASE IF EXISTS guessgameDB;
CREATE DATABASE guessgameDB;

USE guessgameDB;

CREATE TABLE game(
gameID INT PRIMARY KEY AUTO_INCREMENT,
answer VARCHAR(40) NOT NULL,
finishedGame BOOLEAN DEFAULT false,
gameStartTime Timestamp NOT NULL);

CREATE TABLE round(
roundID INT PRIMARY KEY AUTO_INCREMENT,
gameID INT NOT NULL,
userGuess VARCHAR(40) NOT NULL,
guessResults VARCHAR(40) NOT NULL,
guessTime Timestamp NOT NULL,
FOREIGN KEY (gameID) REFERENCES game(gameID));

INSERT INTO game(gameID, answer, finishedGame, gameStartTime)
VALUES(1, "1234", false, '2020-02-03 12:10:01');
