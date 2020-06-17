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
import java.util.Set;
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
public class ContentController {
    
    @Autowired
    UserRepository userRepo;
    
    @Autowired
    BeerRepository beerRepo;
    
    @GetMapping("/content")
    public String displayContentPage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
       // User user = userRepo.getUserById(id);
       User user = userRepo.getUserByUsername(currentUser.getUsername());
       model.addAttribute("currentUser", user);
       
       int id = user.getId();
        System.out.println(id);
       
       Set<Beer> userSet = user.getBeers();
       
        System.out.println(userSet);
       
       List<Beer> adminBeer = beerRepo.findAll();
        
        model.addAttribute("beers", userSet);
        model.addAttribute("adminBeers", adminBeer);
        
        return "content";
    }
}
