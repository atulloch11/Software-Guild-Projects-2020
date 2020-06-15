/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative.guessnumber.data;

import com.summative.guessnumber.models.Game;
import com.summative.guessnumber.models.Round;
import com.summative.guessnumber.servicelayer.GuessServiceLayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ashleytulloch
 */
@Repository
public class GuessDatabaseDao implements GuessDao {
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public GuessDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Autowired
    GuessServiceLayer service;

    @Override
    public Game addGame(Game game) {
        final String sql = "INSERT INTO game(answer, finishedGame) VALUES (?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            statement.setBoolean(2, game.isFinishedGame());
            return statement;

        }, keyHolder);
        
        game.setGameId(keyHolder.getKey().intValue());
        
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT * FROM game";
        return jdbcTemplate.query(sql, new GameMapper());
    }
    

    @Override
    public Game findGameById(int gameId) {
        final String sql = "SELECT * FROM game WHERE gameID = ?;";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), gameId);
    }

    @Override
    public void markFinished(int gameId) {
        final String UPDATE_GAME = "UPDATE game SET finishedGame = true WHERE gameID = ?;";
        jdbcTemplate.update(UPDATE_GAME, gameId);
    }
    

    @Override
    public String getGameAnswer(int gameId) {
        final String SELECT_GAME = "SELECT answer FROM game WHERE gameID = ?;";
        return jdbcTemplate.queryForObject(SELECT_GAME, String.class, gameId);
    }

    @Override
    public Round addRound(Round round) {
        final String sql = "INSERT INTO round(gameID, userGuess, guessResults, guessTime)"
                                        + "VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
            sql,
            PreparedStatement.RETURN_GENERATED_KEYS);
            
            
            statement.setInt(1, round.getGameId());
            statement.setString(2, round.getUserGuess());
            statement.setString(3, round.getGuessResults());
            statement.setTimestamp(4, round.getGetGuessTime());
            return statement;
        }, keyHolder);
        
        round.setRoundId(keyHolder.getKey().intValue());
        
        return round;
    }

    @Override
    public List<Round> getRounds(int gameId) {
        final String sql = "SELECT * FROM round WHERE gameID = ?;";
        return jdbcTemplate.query(sql, new RoundMapper(), gameId);
    }

    @Override
    public void hideUnfinishedGames(List<Game> games) {
        for (int i =0; i < games.size(); i++){
            if (!games.get(i).isFinishedGame()){
                games.get(i).setAnswer("Game In Progress: Cannot See Answer");
            }
        } 
    }
    
    @Override
    public void hideAnswer(Game game) {
        if(!game.isFinishedGame()) {
            game.setAnswer("Game In Progress");
        }
    }


    @Override
    public Game startGame(Game game) {
        String gameAnswer = service.generateAnswer();
        game.setAnswer(gameAnswer);
        Game createGame = addGame(game);
        return createGame;
    }
    
    @Override
    public Round updateRound(String userGuess, int gameId) {
        Round round = new Round();
        round.setGameId(gameId);
        round.setUserGuess(userGuess);
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        String guessResults = service.calculateGuess(gameId, userGuess);
        round.setGuessResults(guessResults);
        round.setGetGuessTime(ts);
        Round createRound = addRound(round);
        return createRound;
    }

    
    private static final class GameMapper implements RowMapper<Game> {
        
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameID"));
            game.setAnswer(rs.getString("answer"));
            game.setFinishedGame(rs.getBoolean("finishedGame"));
            return game;
        }
    }
    
    private static final class RoundMapper implements RowMapper<Round> {
        
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundID"));
            round.setGameId(rs.getInt("gameID"));
            round.setUserGuess(rs.getString("userGuess"));
            round.setGuessResults(rs.getString("guessResults"));
            round.setGetGuessTime(rs.getTimestamp("guessTime"));
            return round;
            
        }
    
    }
}
