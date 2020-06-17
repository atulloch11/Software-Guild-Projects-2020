/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice.demo.controller;

import com.practice.demo.dao.BeerRepository;
import com.practice.demo.dao.UserRepository;
import com.practice.demo.model.Beer;
import com.practice.demo.model.User;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ashleytulloch
 */
@Controller
public class BeerController {
    
    @Autowired
    BeerRepository beerRepo;
    
    @Autowired
    UserRepository userRepo;
    
    @GetMapping("/brewery")
    public String displayBreweryPage() {
        return "brewery";
    }
    
    @GetMapping("/cocktail")
    public String displayCocktailPage() {
        return "cocktail";
    }
    
    
    
    @GetMapping("addBeer")
    public String displayAddBeerPage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
       User user = userRepo.getUserByUsername(currentUser.getUsername());
       model.addAttribute("currentUser", user);
       
       
        return "addBeer";
    }
    
    
    @PostMapping("addBeer")
    public String addBeer(Beer beer, String idUser) {
       User user = userRepo.getUserById(Integer.parseInt(idUser));
       
       int beerid = beer.getId();
       
       
       Set<Beer> userBeers = user.getBeers();
       userBeers.add(beer);
       user.setBeers(userBeers);
        
        
        beerRepo.save(beer);
        
        return "redirect:/content";
    }
    
    @GetMapping("deleteBeer")
    public String displayDeleteBeerPage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userRepo.getUserByUsername(currentUser.getUsername());
        model.addAttribute("currentUser", user);
        
        Set<Beer> userSet = user.getBeers();
       
       
        List<Beer> adminBeer = beerRepo.findAll();
        
        model.addAttribute("beers", userSet);
        model.addAttribute("adminBeers", adminBeer);
        
        
        return "deleteBeer";
    }
    
    
   @GetMapping("confirmDeleteBeer")
   public String deleteBeer(Integer id, @AuthenticationPrincipal UserDetails currentUser) {
       User user = userRepo.getUserByUsername(currentUser.getUsername());
       
       Set<Beer> usersBeers = user.getBeers();
       
       Beer beer = beerRepo.findById(id).orElse(null);
       
       usersBeers.remove(beer);
       
       
       beerRepo.deleteById(id);
       
       return "redirect:/content";
       
   }
   
   @GetMapping("editBeerDisplay")
   public String editBeerButton(Model model, @AuthenticationPrincipal UserDetails currentUser) {
       User user = userRepo.getUserByUsername(currentUser.getUsername());
        model.addAttribute("currentUser", user);
        
        Set<Beer> userSet = user.getBeers();
       
       
        List<Beer> adminBeer = beerRepo.findAll();
        
        model.addAttribute("beers", userSet);
        model.addAttribute("adminBeers", adminBeer);
        return "editBeerDisplay";
   }
   
   @GetMapping("editBeer{id}")
   public String displayEditBeerPage(Integer id, Model model, @AuthenticationPrincipal UserDetails currentUser) {
       User user = userRepo.getUserByUsername(currentUser.getUsername());
       model.addAttribute("currentUser", user);
       Set<Beer> usersBeers = user.getBeers();
       
       Beer beer = beerRepo.findById(id).orElse(null);
       
       model.addAttribute("beer", beer);
       
       return "editBeer";
   }
   
   @PostMapping("editBeer")
   public String editBeer(Beer beer) {
       
       int beerid = beer.getId();
       
       
       
       beerRepo.save(beer);
       
       return "redirect:/content";
   }
       
}
