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
public class Game {
    int gameId;
    String answer;
    boolean finishedGame;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isFinishedGame() {
        return finishedGame;
    }

    public void setFinishedGame(boolean finishedGame) {
        this.finishedGame = finishedGame;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.gameId;
        hash = 97 * hash + Objects.hashCode(this.answer);
        hash = 97 * hash + (this.finishedGame ? 1 : 0);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.finishedGame != other.finishedGame) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }

}
