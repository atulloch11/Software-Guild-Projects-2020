/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative1.rockpapersissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ashleytulloch
 */
public class RockPaperSissors {
    public static void main(String[] args) {

        //Rock Paper Sissors
        //Rock beats Sissors
        //Paper covers Rock
        //Sissors cut Paper

        //Scanner is needed to get user input
        Scanner sc = new Scanner(System.in);
        //Random number generator is needed in order find a random variable for computer
        Random randNum = new Random();
        //Define your variables
        int numRounds = 0;
        int playWins = 0;
        int compWins = 0;
        int ties = 0;
        String userPlay;
        String computerPlay;
        String playAgain;


        //while loops allows the player to play again
        while (true) {
            numRounds = getUserNumRound();
            
            //if the number of rounds are less than 10 and greater than 1 the loop will continue    
            if (numRounds <= 10 && numRounds >= 1) {
                //for loop to keep the game going for as many rounds as the user inputed
                for (int i = 0; i < numRounds; i++) {
                   System.out.println("Pick your weapon:");
                   System.out.println("Rock, Paper, or Sissors?");
                   userPlay = sc.next(); 

                computerPlay = getComputerChoice();

                //if/else if statements to decide if there is a tie, computer win, or player win       
                if (userPlay.equalsIgnoreCase(computerPlay)) {
                    System.out.println("Wow - it's a tie! I also choose " + computerPlay + "!");
                    ties++;
                } else if (userPlay.equalsIgnoreCase("Rock") && computerPlay.equalsIgnoreCase("Sissors")) {
                    System.out.println("Rock smashes Sissors!! You win!!");
                    playWins++;
                } else if (userPlay.equalsIgnoreCase("Rock") && computerPlay.equalsIgnoreCase("Paper")) {
                    System.out.println("Paper covers Rock!! You lose!");
                    compWins++;
                } else if (userPlay.equalsIgnoreCase("Sissors") && computerPlay.equalsIgnoreCase("Paper")) {
                    System.out.println("Sissors cut Paper!! You win!!");
                    playWins++;
                } else if (userPlay.equalsIgnoreCase("Sissors") && computerPlay.equalsIgnoreCase("Rock")) {
                    System.out.println("Rock smashes Sissors!! You lose!!");
                    compWins++;
                } else if (userPlay.equalsIgnoreCase("Paper") && computerPlay.equalsIgnoreCase("Sissors")) {
                    System.out.println("Sissors cut Paper!! You lose!!");
                    compWins++;
                } else if (userPlay.equalsIgnoreCase("Paper") && computerPlay.equalsIgnoreCase("Rock")) {
                    System.out.println("Paper covers Rock!! You win!!");
                    playWins++;
                } else {
                    System.out.println("Invalid Command.");
                }

                }
            //this is the else statement if the user does not enter an int from 1 - 10
            } else {
                System.out.println("Error: Please enter a value between 1 and 10.");
                System.exit(0);
            }

             //print out the number of player wins, computer wins, and ties        
            System.out.println("The number of Player wins: " + playWins);
            System.out.println("The number of Computer wins: " + compWins);
            System.out.println("The number of ties: " + ties);

        //if/else if statements to decide the winner of the game        
        if (playWins > compWins) {
            System.out.println("You won! Congrats!");
        } else if (playWins < compWins)
                System.out.println("You lose! I won hehe!");
        else {
            System.out.println("There is a Tie!!! WOWOW - We both win!");
        }
        //ask user if they would like to play again?    
        System.out.println("Would you like to play again? (y or n)");
        playAgain = sc.next();
        if (playAgain.equalsIgnoreCase("n"))
            break;

        }  
    }

        public static int getUserNumRound() { 
            Scanner sc = new Scanner(System.in);
            //beginning message to explain the rules of the game        
            System.out.println("Welcome to the magical game: Rock, Paper, Scissors");
            System.out.println("Same rules apply as our classic Rock, Paper, Scissors.");
            System.out.println("We can play as many rounds as you would like (between range of 1 to 10)");
            System.out.println("and at the end we will total up the wins, losses, and ties");
            System.out.println("to detemine if you win or IF I WIN!!");
            System.out.println("How many rounds of the game would you like to play?");
            int numRounds = sc.nextInt();
            return numRounds;
        }

        public static String getComputerChoice() {
            Random randNum = new Random();
            String computerPlay = "";
                //random number generator to choose what the computer will input        
               int cChoice = randNum.nextInt(3) + 1;
                //switch statment to compare user's input with the computer        
                switch (cChoice) {
                    case 1:
                        computerPlay = "Rock";
                    case 2:
                        computerPlay = "Paper";
                    case 3:
                        computerPlay = "Sissors";
                        break;
                    default:
                        break;
                }
                return computerPlay;
        }

}