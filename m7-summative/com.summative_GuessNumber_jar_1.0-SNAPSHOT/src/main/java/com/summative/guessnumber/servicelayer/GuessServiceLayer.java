/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative.guessnumber.servicelayer;

import com.summative.guessnumber.models.Game;
import com.summative.guessnumber.models.Round;
import java.util.List;

/**
 *
 * @author ashleytulloch
 */
public interface GuessServiceLayer {
    Game addGame(Game game);
    
    List <Game> getAllGames();
    
    Game findGameById(int gameId);
    
    void markFinished(int gameId);
    
    String getGameAnswer(int gameId);
    
    Round addRound(Round round);
    
    List <Round> getRounds(int gameId);
    
    String generateAnswer();
    
    String calculateGuess(int gameId, String guess);
    
    boolean checkGuess(String userGuess);
    
    Game startGame(Game game);
    
    public Round updateRound(String userGuess, int guessId);
    
    public void hideUnfinishedGames(List<Game> games);
    
    public void hideAnswer(Game game);
}
