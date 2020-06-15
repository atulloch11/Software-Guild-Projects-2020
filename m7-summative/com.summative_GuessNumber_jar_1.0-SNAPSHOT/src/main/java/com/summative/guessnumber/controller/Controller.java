/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative.guessnumber.controller;

import com.summative.guessnumber.models.Game;
import com.summative.guessnumber.models.Round;
import com.summative.guessnumber.servicelayer.GuessServiceLayer;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ashleytulloch
 */
@RestController
@RequestMapping("/guessnum")
public class Controller {
    
    private final GuessServiceLayer service;
    
    public Controller(GuessServiceLayer service) {
        this.service = service;
    }
    
    
    @GetMapping
    public List<Game> all() {
        List<Game> games = service.getAllGames();
        service.hideUnfinishedGames(games);
        return games;
    }
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Game> startGame() {
        Game game = new Game();
        service.startGame(game);
        service.hideAnswer(game);
        return ResponseEntity.ok(game);
    }
    
    @PostMapping("/guess{gameId}")
    public ResponseEntity<Round> userGuess(@PathVariable int gameId, @RequestBody String userGuess) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        Game game = service.findGameById(gameId);
        game.getAnswer();
        if (userGuess == null) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
        Round round = service.updateRound(userGuess, gameId);
        String guessResults = round.getGuessResults();
        boolean checkGuess = service.checkGuess(guessResults);
        
        if (checkGuess == true) {
            service.markFinished(gameId);
        }
        return ResponseEntity.ok(round); 
        }
        return response;
    }
    
    @GetMapping("/rounds/{gameId}")
    public ResponseEntity<List<Round>> getRoundsByGameId(@PathVariable int gameId) {
        Game game = service.findGameById(gameId);
        List<Round> roundList = service.getRounds(gameId);
        if (roundList == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(roundList);
    }
    
    
    @GetMapping("game/{gameId}")
    public ResponseEntity<Game> findById(@PathVariable int gameId) {
        Game result = service.findGameById(gameId);
        service.hideAnswer(result);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
}
