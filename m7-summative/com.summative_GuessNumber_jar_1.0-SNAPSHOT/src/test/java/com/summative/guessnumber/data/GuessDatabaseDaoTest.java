/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative.guessnumber.data;

import com.summative.guessnumber.models.Game;
import com.summative.guessnumber.models.Round;
import com.summative.guessnumber.servicelayer.GuessServiceLayer;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ashleytulloch
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuessDatabaseDaoTest {
    @Autowired
    GuessDao dao;
    
    @Autowired
    GuessServiceLayer service;
    
    @Autowired
    JdbcTemplate jdbc = new JdbcTemplate();
    
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
    
    public GuessDatabaseDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        /*
        System.out.println("SETUP");
        jdbc.update("DROP TABLE game");
        jdbc.update("CREATE TABLE game(" +
                "gameID INT PRIMARY KEY AUTO_INCREMENT," +
                    "answer VARCHAR(40) NOT NULL," +
                    "finishedGame BOOLEAN DEFAULT false);");
        */
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addGame method, of class GuessDatabaseDao.
     */
    @Test
    @Transactional
    public void testAddGame() {
        Game game = new Game();
        game.setGameId(1);
        game.setAnswer("1234");
        game.setFinishedGame(true);
        game = dao.addGame(game);
        
        Game fromDao = dao.findGameById(game.getGameId());
        
        assertEquals(game, fromDao);
        
    }

    /**
     * Test of getAllGames method, of class GuessDatabaseDao.
     */
    @Test
    public void testGetAllGames() {
        Game game1 = new Game();
        game1.setGameId(1);
        game1.setAnswer("1234");
        game1.setFinishedGame(true);
        dao.addGame(game1);
        
        Game game2 = new Game();
        game2.setGameId(1);
        game2.setAnswer("1234");
        game2.setFinishedGame(true);
        dao.addGame(game2);
        
        List<Game> games = dao.getAllGames();
        
        assertEquals(2, games.size());
        assertTrue(games.contains(game1));
        assertTrue(games.contains(game2));
        
    }

    /**
     * Test of findGameById method, of class GuessDatabaseDao.
     */
    @Test
    public void testFindGameById() {
        Game game1 = new Game();
        game1.setGameId(1);
        game1.setAnswer("1234");
        game1.setFinishedGame(true);
        dao.addGame(game1);
        
        int gameId = game1.getGameId();
        
        Game game2 = dao.findGameById(gameId);
        
        assertTrue(game1.equals(game2));
    }

    /**
     * Test of markFinished method, of class GuessDatabaseDao.
     */
    @Test
    public void testMarkFinished() {
    }

    /**
     * Test of getGameAnswer method, of class GuessDatabaseDao.
     */
    @Test
    public void testGetGameAnswer() {
    }

    /**
     * Test of addRound method, of class GuessDatabaseDao.
     */
    @Test
    public void testAddRound() {
        /* need to finish
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        Round round = new Round();
        round.setGameId(1);
        round.setGetGuessTime(timestamp);
        round.setUserGuess("1234");
        round.setGuessResults("e:4:p:0");
        dao.addRound(round);
        
        */
        
    }

    /**
     * Test of getRounds method, of class GuessDatabaseDao.
     */
    @Test
    public void testGetRounds() {
    }

    /**
     * Test of hideUnfinishedGames method, of class GuessDatabaseDao.
     */
    @Test
    public void testHideUnfinishedGames() {
    }

    /**
     * Test of hideAnswer method, of class GuessDatabaseDao.
     */
    @Test
    public void testHideAnswer() {
    }

    /**
     * Test of startGame method, of class GuessDatabaseDao.
     */
    @Test
    public void testStartGame() {
    }

    /**
     * Test of updateRound method, of class GuessDatabaseDao.
     */
    @Test
    public void testUpdateRound() {
    }
    
}
