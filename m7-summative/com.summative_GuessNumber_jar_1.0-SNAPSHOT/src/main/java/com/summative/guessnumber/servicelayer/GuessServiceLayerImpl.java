/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative.guessnumber.servicelayer;

import com.summative.guessnumber.data.GuessDao;
import com.summative.guessnumber.models.Game;
import com.summative.guessnumber.models.Round;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ashleytulloch
 */
@Service
public class GuessServiceLayerImpl implements GuessServiceLayer {
    
    @Autowired
    GuessDao dao;

    @Override
    public Game addGame(Game game) {
        return dao.addGame(game);
    }

    @Override
    public List<Game> getAllGames() {
        return dao.getAllGames();
    }

    @Override
    public Game findGameById(int gameId) {
        return dao.findGameById(gameId);
    }

    @Override
    public void markFinished(int gameId) {
        dao.markFinished(gameId);
    }

    @Override
    public String getGameAnswer(int gameId) {
        return dao.getGameAnswer(gameId);
    }

    @Override
    public Round addRound(Round round) {
        return dao.addRound(round);
    }

    @Override
    public List<Round> getRounds(int gameId) {
        return dao.getRounds(gameId);
    }

    @Override
    public String generateAnswer() {
                // generate random four digit number    
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        String result = "";
        for(int i = 0; i < 4; i++){
            result += numbers.get(i).toString();
        }
        return result;
    }

    @Override
    public String calculateGuess(int gameId, String guess) {
        String answer = getGameAnswer(gameId);
        
        int exactAnswer = 0;
        int partialAnswer = 0;
        
        
        for (int i = 0; i < 4; i++) {
        
            if (guess.equals(answer)) {
                exactAnswer++;
            }
            else if (answer.contains(String.valueOf(guess.charAt(i))))  {
                partialAnswer++;
            }
        }
        return String.format("e:" + exactAnswer + ":p:" + partialAnswer);
    }

    @Override
    public boolean checkGuess(String userGuess) {
        return userGuess.equals("e:4:p:0");
    }

    @Override
    public Game startGame(Game game) {
        return dao.startGame(game);
    }

    @Override
    public Round updateRound(String userGuess, int guessId) {
        return dao.updateRound(userGuess, guessId);
    }

    @Override
    public void hideUnfinishedGames(List<Game> games) {
        dao.hideUnfinishedGames(games);
    }
    
    @Override
    public void hideAnswer(Game game) {
        dao.hideAnswer(game);
    }
    
}
