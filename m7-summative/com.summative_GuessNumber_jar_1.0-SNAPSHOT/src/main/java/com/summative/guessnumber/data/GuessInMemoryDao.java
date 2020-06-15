/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative.guessnumber.data;

import com.summative.guessnumber.models.Game;
import com.summative.guessnumber.models.Round;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashleytulloch
 */
public class GuessInMemoryDao implements GuessDao {
    
    private static final List<Game> games = new ArrayList<>();
    private static final List<Round> rounds = new ArrayList<>();

    @Override
    public Game addGame(Game game) {
        int nextId = games.stream()
                .mapToInt(i -> i.getGameId())
                .max()
                .orElse(0) + 1;
        
        game.setGameId(nextId);
        games.add(game);
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        return new ArrayList<>(games);
    }

    @Override
    public Game findGameById(int gameId) {
        return games.stream()
                .filter(i -> i.getGameId() == gameId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void markFinished(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getGameAnswer(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Round addRound(Round round) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Round> getRounds(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Game startGame(Game game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Round updateRound(String userGuess, int guessId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hideUnfinishedGames(List<Game> games) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hideAnswer(Game game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
