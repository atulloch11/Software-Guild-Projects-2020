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
public class DogGenetics {
    public static void main(String[] args) {
        //print variables
        String userInput;
        int [] array = null;
        
        userInput = getDogsName();

        System.out.println("Well then, I have this highly reliable report on ");
        System.out.print(userInput + "'s prestigious background right here.");
        
        
        createArray();
        shuffleArray(array);
 
    }
    
    public static String getDogsName() {
       Scanner sc = new Scanner(System.in);
       
        System.out.println("What is your dog's name?");
        String userInput = sc.next();
       
        return userInput;
        
    }
    
    public static void createArray() {
        String [] dogBreeds = {"dalmation", "goldenRetriever", "greatPyrenees", "minPin", "husky"};
        int[] dogBreedsInt = new int[dogBreeds.length];
        for (int i = 0; i < dogBreeds.length; i++) {
            dogBreedsInt[i] = i;
        }
        
        int[] dogBreedsIntsShuffled = shuffleArray(dogBreedsInt);
        for (int breed:dogBreedsIntsShuffled) {
            System.out.println(breed);
        }
    }
    
    public static int[] shuffleArray(int[] array) {
       Random randomizer = new Random();
       
       for (int i = 0; i < array.length; i++) {
           int randomBreedSwap = randomizer.nextInt(array.length);
           int temp = array[randomBreedSwap];
           array[randomBreedSwap] = array[i];
           array[randomBreedSwap] = temp;
       }
        return array;


    }

    
    
    
}
