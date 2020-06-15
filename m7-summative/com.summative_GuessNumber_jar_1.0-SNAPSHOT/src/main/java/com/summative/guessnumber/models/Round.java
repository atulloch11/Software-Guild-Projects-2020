/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative.guessnumber.models;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author ashleytulloch
 */
public class Round {
    int roundId;
    int gameId;
    String userGuess;
    String guessResults;
    Timestamp getGuessTime;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(String userGuess) {
        this.userGuess = userGuess;
    }

    public String getGuessResults() {
        return guessResults;
    }

    public void setGuessResults(String guessResults) {
        this.guessResults = guessResults;
    }

    public Timestamp getGetGuessTime() {
        return getGuessTime;
    }

    public void setGetGuessTime(Timestamp getGuessTime) {
        this.getGuessTime = getGuessTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.roundId;
        hash = 37 * hash + this.gameId;
        hash = 37 * hash + Objects.hashCode(this.userGuess);
        hash = 37 * hash + Objects.hashCode(this.guessResults);
        hash = 37 * hash + Objects.hashCode(this.getGuessTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.userGuess, other.userGuess)) {
            return false;
        }
        if (!Objects.equals(this.guessResults, other.guessResults)) {
            return false;
        }
        if (!Objects.equals(this.getGuessTime, other.getGuessTime)) {
            return false;
        }
        return true;
    }
    
    
    
}
